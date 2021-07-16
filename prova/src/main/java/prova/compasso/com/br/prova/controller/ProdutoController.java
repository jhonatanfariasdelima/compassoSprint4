package prova.compasso.com.br.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import prova.compasso.com.br.prova.dto.PessoaDto;
import prova.compasso.com.br.prova.dto.ProdutoDto;
import prova.compasso.com.br.prova.form.atualizacao.AtualizacaoPessoaForm;
import prova.compasso.com.br.prova.form.atualizacao.AtualizacaoProdutoForm;
import prova.compasso.com.br.prova.form.criacao.ProdutoForm;
import prova.compasso.com.br.prova.model.Endereco;
import prova.compasso.com.br.prova.model.Pessoa;
import prova.compasso.com.br.prova.model.Produto;
import prova.compasso.com.br.prova.repository.ProdutoRepository;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("protected/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoDto>> listProdutos(UriComponentsBuilder uriComponentsBuilder){
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProdutoDto.convert(produtos));
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<List<ProdutoDto>> listProdutoEspecifico(@PathVariable("id") Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ProdutoDto.convert(produto));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<List<ProdutoDto>> delet(@PathVariable("id") Long id){

        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            produtoRepository.deleteById(id);
            if (produtoRepository.findById(id).isPresent()){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizacaoProdutoForm form){

        Produto produtoAtualizado = form.atualizar(id, produtoRepository);

        return ResponseEntity.ok(new ProdutoDto(produtoAtualizado));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriComponentsBuilder){

        Produto newProduto = form.convert();

        produtoRepository.save(newProduto);

        URI uri = uriComponentsBuilder.path("protected/produto/{id}").buildAndExpand(newProduto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(newProduto));
    }

}

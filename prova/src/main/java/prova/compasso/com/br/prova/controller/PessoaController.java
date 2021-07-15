package prova.compasso.com.br.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import prova.compasso.com.br.prova.dto.PessoaDto;
import prova.compasso.com.br.prova.form.atualizacao.AtualizacaoPessoaForm;
import prova.compasso.com.br.prova.form.criacao.PessoaForm;
import prova.compasso.com.br.prova.model.Endereco;
import prova.compasso.com.br.prova.model.Pessoa;
import prova.compasso.com.br.prova.repository.EnderecoRepository;
import prova.compasso.com.br.prova.repository.PessoaRepository;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<PessoaDto>> listPessoas(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        if (pessoas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(PessoaDto.convert(pessoas));
    }

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<List<PessoaDto>> listPessoaPeloID(@PathVariable("id") Long id){
        Optional<Pessoa> pessoas = pessoaRepository.findById(id);
        if (pessoas.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(PessoaDto.convert(pessoas));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<List<PessoaDto>> deletaPessoaPeloID(@PathVariable("id") Long id){
        Optional<Pessoa> pessoas = pessoaRepository.findById(id);
        if (pessoas.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            pessoaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PessoaDto> atualizar(@PathVariable("id") Long id, @RequestBody AtualizacaoPessoaForm form){
        Pessoa pessoaAtualizada = form.atualizar(id, pessoaRepository);

        return ResponseEntity.ok(new PessoaDto(pessoaAtualizada));
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<PessoaDto> adicionarPessoa(@RequestBody PessoaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder){
        Pessoa newPessoa = pessoaForm.convert();
        pessoaRepository.save(newPessoa);

        Endereco enderecoNovaPessoa = pessoaForm.getEndereco();
        enderecoNovaPessoa.setPessoa(newPessoa);
        enderecoRepository.save(enderecoNovaPessoa);

        newPessoa.setEndereco(Collections.singletonList(enderecoNovaPessoa));


        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(newPessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaDto(newPessoa));

    }

}

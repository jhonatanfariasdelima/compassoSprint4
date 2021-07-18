package prova.compasso.com.br.prova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import prova.compasso.com.br.prova.dto.PedidoDto;
import prova.compasso.com.br.prova.form.atualizacao.PedidoFormAtualizacao;
import prova.compasso.com.br.prova.form.criacao.PedidoForm;
import prova.compasso.com.br.prova.model.Pedido;
import prova.compasso.com.br.prova.repository.PedidoRepository;
import prova.compasso.com.br.prova.repository.ProdutoRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("protected/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<PedidoDto>> listaTodosPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PedidoDto> pedidosListDto = PedidoDto.getPedidosDto(pedidos, produtoRepository);
        return ResponseEntity.ok().body(pedidosListDto);
    }

    @GetMapping("/ativos")
    @ResponseBody
    public ResponseEntity<List<PedidoDto>> listaPedidosAtivos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PedidoDto> pedidosListDto = PedidoDto.filtroPedidosAtivos(pedidos, produtoRepository);
        return ResponseEntity.ok().body(pedidosListDto);
    }

    @GetMapping("/inativos")
    @ResponseBody
    public ResponseEntity<List<PedidoDto>> ListaPedidosInativos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PedidoDto> pedidosListDto = PedidoDto.filtroPedidosInativos(pedidos, produtoRepository);
        return ResponseEntity.ok().body(pedidosListDto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<List<PedidoDto>> listaPedidoEspecifico(@PathVariable("id") Long id) {
        Optional<Pedido> pedidos = pedidoRepository.findById(id);
        List<Pedido> p = pedidos.stream().toList();
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PedidoDto> pedidosListDto = PedidoDto.getPedidosDto(p, produtoRepository);
        return ResponseEntity.ok().body(pedidosListDto);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<List<PedidoDto>> inativaPedido(@PathVariable("id") Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            pedido.get().setStatus(Boolean.FALSE);
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<?> adicionaPedido(@RequestBody @Valid PedidoForm pedidoForm, UriComponentsBuilder uriComponentsBuilder) {
        Pedido newPedido = pedidoForm.convert();
        newPedido.setStatus(Boolean.TRUE);
        pedidoRepository.save(newPedido);
        if (pedidoRepository.findById(newPedido.getId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(newPedido.getId()).toUri();
        return ResponseEntity.created(uri).body(PedidoDto.getPedidosDto(Collections.singletonList(newPedido), produtoRepository));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizaPedido(@PathVariable("id") Long id, @RequestBody @Valid PedidoFormAtualizacao formAtualizacao) {
        Pedido pedidoAtualizado = formAtualizacao.atualizar(id, pedidoRepository);
        return ResponseEntity.ok().build();
    }
}

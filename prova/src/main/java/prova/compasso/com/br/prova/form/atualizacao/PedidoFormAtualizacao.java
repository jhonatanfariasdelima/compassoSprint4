package prova.compasso.com.br.prova.form.atualizacao;

import prova.compasso.com.br.prova.model.Pedido;
import prova.compasso.com.br.prova.repository.PedidoRepository;

import javax.validation.constraints.NotBlank;

public class PedidoFormAtualizacao {
    @NotBlank
    private String listaDeProdutos;


    public Pedido atualizar(Long id, PedidoRepository pedidoRepository) {
        Pedido p = pedidoRepository.getById(id);
        p.setListaDePedidos(this.listaDeProdutos);
        return p;
    }

    public String getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(String listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }
}

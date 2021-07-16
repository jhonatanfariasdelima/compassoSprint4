package prova.compasso.com.br.prova.form.criacao;

import prova.compasso.com.br.prova.model.Pedido;

import java.util.Date;

public class PedidoForm {
    private String listaDeProdutos;

    public Pedido convert() {
        Pedido p = new Pedido();
        p.setListaDePedidos(this.listaDeProdutos);
        p.setData(String.valueOf(new Date()));
        return p;
    }

    public String getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(String listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }
}

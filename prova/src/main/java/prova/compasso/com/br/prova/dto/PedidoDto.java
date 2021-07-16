package prova.compasso.com.br.prova.dto;

import prova.compasso.com.br.prova.model.Pedido;
import prova.compasso.com.br.prova.model.Produto;
import prova.compasso.com.br.prova.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


public class PedidoDto {
    private Long id;
    private BigDecimal total = BigDecimal.ZERO;
    private String dataDoPedido;
    private List<ProdutoDto> produtos = new ArrayList<>();

    public PedidoDto(){}

    public static List<PedidoDto> getPedidosDto(List<Pedido> pedidos, ProdutoRepository produtoRepository) {
        List<PedidoDto> pedidosListDto = new ArrayList<>();

        int i;
        for (i=0; i<pedidos.size(); i++){  // para cada pedido na lista de pedidos
            PedidoDto pedidoDto = new PedidoDto();
            pedidoDto.constructPedidos(pedidos.get(i), produtoRepository);
            pedidoDto.setId(pedidos.get(i).getId());
            pedidosListDto.add(pedidoDto);
        }

        return pedidosListDto;
    }

    public void constructPedidos(Pedido p, ProdutoRepository produtoRepository) {
        String produtosPedido = p.getListaDePedidos();
        List<Integer> listaDeProdutos = convertStringList(produtosPedido);
        this.dataDoPedido = p.getData();

        int j;
        for (j=0;j<listaDeProdutos.size();j++){  // para cada produto
            Optional<Produto> produto = produtoRepository.findById(Long.valueOf(listaDeProdutos.get(j)));  // produto
            this.produtos.add(new ProdutoDto(produto));  //Dto
            this.total = total.add(produto.get().getPrecoUnitario());
        }
    }

    private static List<Integer> convertStringList(String stringProdutos) {  // transforma a string pedidos em lista
        return Arrays.stream(stringProdutos.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(String dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }

    public List<ProdutoDto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDto> produtos) {
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

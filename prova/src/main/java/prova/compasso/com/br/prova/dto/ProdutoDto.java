package prova.compasso.com.br.prova.dto;

import prova.compasso.com.br.prova.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    private String descricao;
    private BigDecimal precoUnitario;

    public ProdutoDto(){}

    public ProdutoDto(Produto produto) {
        this.descricao = produto.getDescricao();
        this.id = produto.getId();
        this.precoUnitario = produto.getPrecoUnitario();
    }

    public ProdutoDto(Optional<Produto> produto) {
        this.descricao = produto.get().getDescricao();
        this.id = produto.get().getId();
        this.precoUnitario = produto.get().getPrecoUnitario();
    }


    public static List<ProdutoDto> convert(Optional<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public static List<ProdutoDto> convert(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}

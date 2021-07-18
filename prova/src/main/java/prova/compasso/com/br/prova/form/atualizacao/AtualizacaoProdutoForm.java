package prova.compasso.com.br.prova.form.atualizacao;

import prova.compasso.com.br.prova.model.Pessoa;
import prova.compasso.com.br.prova.model.Produto;
import prova.compasso.com.br.prova.repository.ProdutoRepository;

import java.math.BigDecimal;

public class AtualizacaoProdutoForm {
    private String descricao;
    private BigDecimal precoUnitario;

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

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getById(id);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(this.precoUnitario);
        return produto;
    }
}

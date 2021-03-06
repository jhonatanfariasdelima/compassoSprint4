package prova.compasso.com.br.prova.form.criacao;

import prova.compasso.com.br.prova.model.Produto;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ProdutoForm {
    @NotBlank
    private String descricao;
    @NotBlank
    private BigDecimal precoUnitario;


    public Produto convert() {
        Produto produto = new Produto(this.descricao, this.precoUnitario);
        return produto;
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

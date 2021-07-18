package prova.compasso.com.br.prova.form.criacao;

import prova.compasso.com.br.prova.model.Endereco;
import prova.compasso.com.br.prova.model.Pessoa;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PessoaForm {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotNull
    private BigDecimal salario;
    @NotBlank
    private String sexo;

    private @Valid EnderecoForm endereco;


    public Pessoa convert() {
        Pessoa Pessoa = new Pessoa(this.nome, this.cpf, this.salario, this.sexo);
        return Pessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco.convert();
    }

    public void setEndereco(EnderecoForm endereco) {
        this.endereco = endereco;
    }
}

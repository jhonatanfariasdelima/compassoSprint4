package prova.compasso.com.br.prova.form.atualizacao;

import prova.compasso.com.br.prova.model.Pessoa;
import prova.compasso.com.br.prova.repository.PessoaRepository;

import java.math.BigDecimal;

public class AtualizacaoPessoaForm {

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private String sexo;

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

    public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
        Pessoa p = pessoaRepository.getById(id);

        p.setCpf(this.cpf);
        p.setNome(this.nome);
        p.setSalario(this.salario);
        p.setSexo(this.sexo);
        return p;
    }

    public void atualizar() { }
}

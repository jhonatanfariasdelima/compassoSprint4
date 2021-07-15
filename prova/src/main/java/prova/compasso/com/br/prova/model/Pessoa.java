package prova.compasso.com.br.prova.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pessoa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private String sexo;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
    private List<Endereco> endereco;


    public Pessoa(){}

    public Pessoa(String nome, String cpf, BigDecimal salario, String sexo){
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }
}

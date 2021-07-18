package prova.compasso.com.br.prova.dto;

import prova.compasso.com.br.prova.model.Endereco;
import prova.compasso.com.br.prova.repository.PessoaRepository;

public class EnderecoDto {

    private PessoaRepository pessoaRepository;

    private String cidade;
    private String rua;

    public String getCidade() {
        return cidade;
    }

    public EnderecoDto(Endereco endereco) {
        this.cidade = endereco.getCidade();
        this.rua = endereco.getRua();
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}

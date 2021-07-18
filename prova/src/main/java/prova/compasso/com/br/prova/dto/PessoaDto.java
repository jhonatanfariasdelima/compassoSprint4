package prova.compasso.com.br.prova.dto;

import prova.compasso.com.br.prova.model.Pessoa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PessoaDto {

    private String nome;
    private String cpf;
    private List<EnderecoDto> enderecoDto;


    public PessoaDto(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.enderecoDto = pessoa.getEndereco().stream().map(EnderecoDto::new).collect(Collectors.toList());
    }

    public static List<PessoaDto> convert(List<Pessoa> pessoas) {
        return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
    }

    public static List<PessoaDto> convert(Optional<Pessoa> pessoas) {
        return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
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

    public List<EnderecoDto> getEnderecoDto() {
        return enderecoDto;
    }

    public void setEnderecoDto(List<EnderecoDto> enderecoDto) {
        this.enderecoDto = enderecoDto;
    }
}

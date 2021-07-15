package prova.compasso.com.br.prova.form.criacao;

import prova.compasso.com.br.prova.model.Endereco;


public class EnderecoForm {
    private String pais;
    private String estado;
    private String cidade;
    private String cep;
    private String rua;

    public Endereco convert() {
        Endereco endereco = new Endereco(this.pais, this.cep, this.cidade, this.rua, this.estado);
        return endereco;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}

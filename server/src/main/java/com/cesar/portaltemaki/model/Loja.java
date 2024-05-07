package com.cesar.portaltemaki.model;

public class Loja {

    private String cnpj;
    private String ie;
    private String nome;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String telefone;
    private String email;
    private String pontoReferencia;
    private Boolean filial;

    public Loja() {
    }
    public Loja(String cnpj, String ie, String nome, String rua, Integer numero, String bairro, String cidade, String uf,
                String cep, String telefone, String email, String pontoReferencia, Boolean filial) {
        this.cnpj = cnpj;
        this.ie = ie;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.pontoReferencia = pontoReferencia;
        this.filial = filial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public Boolean getFilial() {
        return filial;
    }

    public void setFilial(Boolean filial) {
        this.filial = filial;
    }
}
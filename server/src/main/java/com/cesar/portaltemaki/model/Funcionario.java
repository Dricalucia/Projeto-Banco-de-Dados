package com.cesar.portaltemaki.model;

import java.time.LocalDateTime;

public class Funcionario {
    private int matricula;
    private String cpf;
    private String nome;
    private LocalDateTime dataAdmissao;
    private String funcao;
    private double salario;
    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String status;
    private String senha;
    private String lojasCnpj;

    public Funcionario() {
    }

    public Funcionario(int matricula, String cpf, String nome, LocalDateTime dataAdmissao, String funcao, double salario,
                       String rua, Integer numero, String bairro, String cidade, String uf, String cep, String status,
                       String senha, String lojasCnpj) {
        this.matricula = matricula;
        this.cpf = cpf;
        this.nome = nome;
        this.dataAdmissao = dataAdmissao;
        this.funcao = funcao;
        this.salario = salario;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
        this.status = status;
        this.senha = senha;
        this.lojasCnpj = lojasCnpj;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDateTime dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLojasCnpj() {
        return lojasCnpj;
    }

    public void setLojasCnpj(String lojasCnpj) {
        this.lojasCnpj = lojasCnpj;
    }
}

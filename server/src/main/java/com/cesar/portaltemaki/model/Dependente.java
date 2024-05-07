package com.cesar.portaltemaki.model;

import java.sql.Date;
import java.time.LocalDate;

public class Dependente {
    private String cpfDependente;
    private int matriculaFuncionario;
    private String nome;
    private Date dataNascimento;

    public Dependente() {
    }
    public Dependente(String cpfDependente, int matriculaFuncionario, String nome, Date dataNascimento) {
        this.cpfDependente = cpfDependente;
        this.matriculaFuncionario = matriculaFuncionario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getCpfDependente() {
        return cpfDependente;
    }

    public void setCpfDependente(String cpfDependente) {
        this.cpfDependente = cpfDependente;
    }

    public int getMatriculaFuncionario() {
        return matriculaFuncionario;
    }

    public void setMatriculaFuncionario(int matriculaFuncionario) {
        this.matriculaFuncionario = matriculaFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = Date.valueOf(dataNascimento);
    }
}

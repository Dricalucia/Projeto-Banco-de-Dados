package com.cesar.portaltemaki.model;

import java.time.LocalDateTime;

public class Promocao {
    private int idPromocao;
    private int idItem;
    private LocalDateTime dataPromocao;
    private LocalDateTime dataValidade;
    private double precoPromocao;
    private String observacao;

    public Promocao() {
    }
    public Promocao(int idPromocao, int idItem, LocalDateTime dataPromocao, LocalDateTime dataValidade,
                    double precoPromocao, String observacao) {
        this.idPromocao = idPromocao;
        this.idItem = idItem;
        this.dataPromocao = dataPromocao;
        this.dataValidade = dataValidade;
        this.precoPromocao = precoPromocao;
        this.observacao = observacao;
    }

    public int getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(int idPromocao) {
        this.idPromocao = idPromocao;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public LocalDateTime getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(LocalDateTime dataPromocao) {
        this.dataPromocao = dataPromocao;
    }

    public LocalDateTime getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDateTime dataValidade) {
        this.dataValidade = dataValidade;
    }

    public double getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(double precoPromocao) {
        this.precoPromocao = precoPromocao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}

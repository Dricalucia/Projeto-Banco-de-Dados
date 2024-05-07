package com.cesar.portaltemaki.model;

public class Item {
    private int idItem;
    private String nomeItem;
    private String descricaoItem;
    private boolean itemAtivo;
    private double precoItem;
    private int idCategoria;


    public Item() {
    }

    public Item(int idItem, String nomeItem, String descricaoItem, boolean itemAtivo, double precoItem, int idCategoria)
    {
        this.idItem = idItem;
        this.nomeItem = nomeItem;
        this.descricaoItem = descricaoItem;
        this.itemAtivo = itemAtivo;
        this.precoItem = precoItem;
        this.idCategoria = idCategoria;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public boolean isItemAtivo() {
        return itemAtivo;
    }

    public void setItemAtivo(boolean itemAtivo) {
        this.itemAtivo = itemAtivo;
    }

    public double getPrecoItem() {
        return precoItem;
    }

    public void setPrecoItem(double precoItem) {
        this.precoItem = precoItem;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
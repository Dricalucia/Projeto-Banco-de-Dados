package com.cesar.portaltemaki.model;

public class ItensPedido {
    private int pedidoNrPedido;
    private int itemIdItem;
    private int quantidadeItem;

    public ItensPedido() {

    }

    public ItensPedido(int pedidoNrPedido, int itemIdItem, int quantidadeItem) {
        this.pedidoNrPedido = pedidoNrPedido;
        this.itemIdItem = itemIdItem;
        this.quantidadeItem = quantidadeItem;
    }

    public int getPedidoNrPedido() {
        return pedidoNrPedido;
    }

    public void setPedidoNrPedido(int pedidoNrPedido) {
        this.pedidoNrPedido = pedidoNrPedido;
    }

    public int getItemIdItem() {
        return itemIdItem;
    }

    public void setItemIdItem(int itemIdItem) {
        this.itemIdItem = itemIdItem;
    }

    public int getQuantidadeItem() {
        return quantidadeItem;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidadeItem = quantidadeItem;
    }
}

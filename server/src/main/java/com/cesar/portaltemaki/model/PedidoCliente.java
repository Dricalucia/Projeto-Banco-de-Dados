package com.cesar.portaltemaki.model;

public class PedidoCliente {
    private int clienteIdCliente;
    private int pedidoNrPedido;

    public PedidoCliente() {

    }

    public PedidoCliente(int clienteIdCliente, int pedidoNrPedido) {
        this.clienteIdCliente = clienteIdCliente;
        this.pedidoNrPedido = pedidoNrPedido;
    }

    public int getClienteIdCliente() {
        return clienteIdCliente;
    }

    public void setClienteIdCliente(int clienteIdCliente) {
        this.clienteIdCliente = clienteIdCliente;
    }

    public int getPedidoNrPedido() {
        return pedidoNrPedido;
    }

    public void setPedidoNrPedido(int pedidoNrPedido) {
        this.pedidoNrPedido = pedidoNrPedido;
    }
}

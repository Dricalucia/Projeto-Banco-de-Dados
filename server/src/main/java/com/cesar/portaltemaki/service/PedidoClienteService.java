package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.PedidoCliente;

import java.util.List;

public interface PedidoClienteService {
    void addPedidoCliente(PedidoCliente pedidoCliente);
    void deletePedidoCliente(int idCliente);
    List<PedidoCliente> findAllPedidosClientes();
    PedidoCliente findPedidoClienteByClienteId(int idCliente);
}

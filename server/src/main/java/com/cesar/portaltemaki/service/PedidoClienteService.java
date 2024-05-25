package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.PedidoCliente;

import java.util.List;
import java.util.Map;


public interface PedidoClienteService {
    void addPedidoCliente(PedidoCliente pedidoCliente);
    void deletePedidoCliente(int idCliente);
    List<PedidoCliente> findAllPedidosClientes();
    List<PedidoCliente> findPedidoClienteByClienteId(int idCliente);
    List<Map<String, Object>> getPedidoDetalhes(int nrPedido);
    List<Map<String, Object>> getPedidoClientePorPeriodo(String dataInicial, String dataFinal, int idCliente);
    List<Map<String, Object>> getPedidoClienteCountEntreDatas(String dataInicial, String dataFinal);
    List<Map<String, Object>> getPedidoComTotalMaxEntreDatas(String dataInicial, String dataFinal);
}

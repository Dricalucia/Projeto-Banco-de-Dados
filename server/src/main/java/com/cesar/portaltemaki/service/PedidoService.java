package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Pedido;

import java.util.List;
import java.util.Map;

public interface PedidoService {
    Pedido findByNrPedido(int nrPedido);
    List<Pedido> findAll();
    void savePedido(Pedido pedido);
    void deletePedido(int nrPedido);
    void updatePedido(Pedido pedido);
    List<Map<String, Object>> findPedidosPorPeriodo(String dataInicial, String dataFinal);  
    List<Map<String, Object>> findPedidosPendentes();
}

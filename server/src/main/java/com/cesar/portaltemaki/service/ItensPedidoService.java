package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.ItensPedido;

import java.util.List;

public interface ItensPedidoService {
    void savePedido(ItensPedido itensPedido);
    void deletePedido(int nrPedido);
    List<ItensPedido> findAllPedidos();
    ItensPedido findPedidoByNrPedido(int nrPedido);
}

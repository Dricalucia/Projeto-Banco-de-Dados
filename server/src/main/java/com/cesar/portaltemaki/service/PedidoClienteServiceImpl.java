package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.PedidoCliente;
import com.cesar.portaltemaki.repository.PedidoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidoClienteServiceImpl implements PedidoClienteService {
    @Autowired
    private final PedidoClienteRepository pedidoClienteRepository;

    public PedidoClienteServiceImpl(PedidoClienteRepository pedidoClienteRepository) {
        this.pedidoClienteRepository = pedidoClienteRepository;
    }

    @Override
    public void addPedidoCliente(PedidoCliente pedidoCliente) {
        pedidoClienteRepository.savePedidoCliente(pedidoCliente);
    }

    @Override
    public void deletePedidoCliente(int idCliente) {
        pedidoClienteRepository.deletePedidoCliente(idCliente);
    }

    @Override
    public List<PedidoCliente> findAllPedidosClientes() {
        return pedidoClienteRepository.findAll();
    }

    @Override
    public List<PedidoCliente> findPedidoClienteByClienteId(int idCliente) {
        try {
            return pedidoClienteRepository.findByClienteId(idCliente);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getPedidoDetalhes(int nrPedido) {
        return pedidoClienteRepository.findPedidoDetailsByNrPedido(nrPedido);
    }

    @Override
    public List<Map<String, Object>> getPedidoClientePorPeriodo(String dataInicial, String dataFinal, int idCliente) {
        return pedidoClienteRepository.findPedidosPorClienteNoPeriodo(dataInicial, dataFinal, idCliente);
    }

    @Override
    public List<Map<String, Object>> getPedidoClienteCountEntreDatas(String dataInicial, String dataFinal) {
        return pedidoClienteRepository.findPedidoClienteCountEntreDatas(dataInicial, dataFinal);
    }

    @Override
    public List<Map<String, Object>> getPedidoComTotalMaxEntreDatas(String dataInicial, String dataFinal) {
        return pedidoClienteRepository.findPedidoComTotalMaxEntreDatas(dataInicial, dataFinal);
    }
}

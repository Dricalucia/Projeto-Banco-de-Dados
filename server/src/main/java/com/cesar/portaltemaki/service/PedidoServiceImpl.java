package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Pedido;
import com.cesar.portaltemaki.repository.PedidoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido findByNrPedido(int nrPedido) {
        try {
            return pedidoRepository.findByNrPedido(nrPedido);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public void savePedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    @Override
    public void deletePedido(int nrPedido) {
        pedidoRepository.delete(nrPedido);
    }

    @Override
    public void updatePedido(Pedido pedido) {
        pedidoRepository.update(pedido);
    }
}

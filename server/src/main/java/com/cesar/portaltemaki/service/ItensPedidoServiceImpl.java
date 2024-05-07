package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.ItensPedido;
import com.cesar.portaltemaki.repository.ItensPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItensPedidoServiceImpl implements ItensPedidoService{

    @Autowired
    private final ItensPedidoRepository itensPedidoRepository;

    public ItensPedidoServiceImpl(ItensPedidoRepository itensPedidoRepositoryl) {
        this.itensPedidoRepository = itensPedidoRepositoryl;
    }

    @Override
    public void savePedido(ItensPedido itensPedido) {
        itensPedidoRepository.savePedido(itensPedido);
    }

    @Override
    public void deletePedido(int nrPedido) {
        itensPedidoRepository.deletePedido(nrPedido);
    }

    @Override
    public List<ItensPedido> findAllPedidos() {
        return itensPedidoRepository.findAll();
    }

    @Override
    public ItensPedido findPedidoByNrPedido(int nrPedido) {
        try {
            return itensPedidoRepository.findPedidoByNrPedido(nrPedido);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

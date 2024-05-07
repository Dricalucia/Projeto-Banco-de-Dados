package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Cliente;
import com.cesar.portaltemaki.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findByClientId(int idCliente) {
        return clienteRepository.findByClientId(idCliente);
    }

    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void update(Cliente cliente) {
        clienteRepository.update(cliente);
    }

    @Override
    public void delete(int idCliente) {
        clienteRepository.delete(idCliente);
    }
}

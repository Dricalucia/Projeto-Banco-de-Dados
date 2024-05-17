package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> findAll();
    Cliente findByClientId(int idCliente);
    Cliente findByEmailAndSenha(String email, String senha);
    void save(Cliente cliente);
    void update(Cliente cliente);
    void delete(int idCliente);
}

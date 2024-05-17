package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Cliente;
import com.cesar.portaltemaki.service.ClienteService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<Cliente> verificarCliente(@PathVariable String email, @PathVariable String senha) {
            Cliente cliente = clienteService.findByEmailAndSenha(email, senha);
            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                ResponseEntity.notFound().build();
            }
        return null;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> getClientById(@PathVariable int idCliente) {
        Cliente cliente = clienteService.findByClientId(idCliente);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addCliente(@RequestBody Cliente cliente) {
        clienteService.save(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable int idCliente) {
        clienteService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> updateCliente(@PathVariable int idCliente, @RequestBody Cliente cliente) {
        Cliente clienteExistente = clienteService.findByClientId(idCliente);

        if (clienteExistente != null) {
            cliente.setIdCliente(idCliente);
            clienteService.update(cliente);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

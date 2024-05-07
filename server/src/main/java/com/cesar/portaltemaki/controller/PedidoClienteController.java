package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.PedidoCliente;
import com.cesar.portaltemaki.service.PedidoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido-cliente")
public class PedidoClienteController {
    @Autowired
    private final PedidoClienteService pedidoClienteService;

    public PedidoClienteController(PedidoClienteService pedidoClienteService) {
        this.pedidoClienteService = pedidoClienteService;
    }

    @PostMapping()
    public ResponseEntity<Void> postPedidoCliente(@RequestBody PedidoCliente pedidoCliente) {
        pedidoClienteService.addPedidoCliente(pedidoCliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PedidoCliente>> getAllPedidosClientes() {
        List<PedidoCliente> pedidosClientes = pedidoClienteService.findAllPedidosClientes();
        return ResponseEntity.ok(pedidosClientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<PedidoCliente> getPedidoClienteByIdCliente(@PathVariable int idCliente) {
        PedidoCliente pedidoCliente = pedidoClienteService.findPedidoClienteByClienteId(idCliente);
        if (pedidoCliente != null) {
            return ResponseEntity.ok(pedidoCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletePedidoCliente(@PathVariable int idCliente) {
        pedidoClienteService.deletePedidoCliente(idCliente);
        return ResponseEntity.noContent().build();
    }
}

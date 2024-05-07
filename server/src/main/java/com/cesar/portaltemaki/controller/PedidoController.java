package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Pedido;
import com.cesar.portaltemaki.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/{nrPedido}")
    public ResponseEntity<Pedido> findByNrPedido(@PathVariable int nrPedido) {
        Pedido pedido = pedidoService.findByNrPedido(nrPedido);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Void> addPedido(@RequestBody Pedido pedido) {
        pedidoService.savePedido(pedido);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{nrPedido}")
    public ResponseEntity<Void> deletePedido(@PathVariable int nrPedido) {
        pedidoService.deletePedido(nrPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{nrPedido}")
    public ResponseEntity<Void> updatePedido(@PathVariable int nrPedido, @RequestBody Pedido pedido) {
        Pedido pedidoExistente = pedidoService.findByNrPedido(nrPedido);

        if (pedidoExistente != null) {
            pedido.setNrPedido(nrPedido);
            pedidoService.updatePedido(pedido);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

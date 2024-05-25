package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Pedido;
import com.cesar.portaltemaki.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5500")
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
        if (pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
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

    @GetMapping("/por-periodo/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Map<String, Object>>> findPedidosPorPeriodo(@PathVariable String dataInicial,
            @PathVariable String dataFinal) {
        List<Map<String, Object>> pedidos = pedidoService.findPedidosPorPeriodo(dataInicial, dataFinal);
        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view-pendentes")
    public ResponseEntity<List<Map<String, Object>>> getPedidosPendentes() {
        List<Map<String, Object>> pedidosPendentes = pedidoService.findPedidosPendentes();
        if (pedidosPendentes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosPendentes);
    }
}

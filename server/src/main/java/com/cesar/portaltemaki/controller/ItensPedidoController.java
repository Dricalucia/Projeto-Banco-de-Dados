package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.ItensPedido;
import com.cesar.portaltemaki.service.ItensPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-pedido")
public class ItensPedidoController {
    private final ItensPedidoService itensPedidoService;

    public ItensPedidoController(ItensPedidoService itensPedidoService) {
        this.itensPedidoService = itensPedidoService;
    }

    @PostMapping()
    public ResponseEntity<Void> postItensPedido(@RequestBody ItensPedido itensPedido) {
        itensPedidoService.savePedido(itensPedido);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{nrPedido}")
    public ResponseEntity<Void> deleteItensPedido(@PathVariable int nrPedido) {
        itensPedidoService.deletePedido(nrPedido);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<ItensPedido>> getAllPedidos() {
        List<ItensPedido> itensPedidos = itensPedidoService.findAllPedidos();
        return ResponseEntity.ok(itensPedidos);
    }

    @GetMapping("/{nrPedido}")
    public ResponseEntity<List<ItensPedido>> getPedidoByNrPedido(@PathVariable int nrPedido) {
        List<ItensPedido> itensPedido = itensPedidoService.findPedidoByNrPedido(nrPedido);
        return ResponseEntity.ok(itensPedido);
    }
}

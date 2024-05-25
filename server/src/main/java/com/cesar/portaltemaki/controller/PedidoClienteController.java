package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.PedidoCliente;
import com.cesar.portaltemaki.service.PedidoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        if (pedidosClientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosClientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<List<PedidoCliente>> getPedidoClienteByIdCliente(@PathVariable int idCliente) {
        List<PedidoCliente> pedidoCliente = pedidoClienteService.findPedidoClienteByClienteId(idCliente);
        if (pedidoCliente != null && !pedidoCliente.isEmpty()) {
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

    @GetMapping("/{nrPedido}/detalhes")
    public ResponseEntity<List<Map<String, Object>>> getPedidoDetalhes(@PathVariable int nrPedido) {
        List<Map<String, Object>> detalhesPedido = pedidoClienteService.getPedidoDetalhes(nrPedido);
        if (detalhesPedido.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalhesPedido);
    }

    @GetMapping("/cliente-por-periodo/{dataInicial}/{dataFinal}/{idCliente}")
    public ResponseEntity<List<Map<String, Object>>> getPedidosClientesByPeriodo(@PathVariable String dataInicial,
            @PathVariable String dataFinal, @PathVariable int idCliente) {
        List<Map<String, Object>> pedidosClientePeriodo = pedidoClienteService.getPedidoClientePorPeriodo(dataInicial,
                dataFinal, idCliente);
        if (pedidosClientePeriodo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosClientePeriodo);
    }

    @GetMapping("/count-entre-datas/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Map<String, Object>>> getPedidoClienteCountEntreDatas(@PathVariable String dataInicial,
            @PathVariable String dataFinal) {
        List<Map<String, Object>> pedidosClienteEntreDatas = pedidoClienteService
                .getPedidoClienteCountEntreDatas(dataInicial, dataFinal);
        if (pedidosClienteEntreDatas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosClienteEntreDatas);
    }

    @GetMapping("/total-max-entre-datas/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Map<String, Object>>> getPedidoComTotalMaxEntreDatas(@PathVariable String dataInicial,
            @PathVariable String dataFinal) {
        List<Map<String, Object>> pedidosMaxTotalEntreDatas = pedidoClienteService
                .getPedidoComTotalMaxEntreDatas(dataInicial, dataFinal);
        if (pedidosMaxTotalEntreDatas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidosMaxTotalEntreDatas);
    }

}

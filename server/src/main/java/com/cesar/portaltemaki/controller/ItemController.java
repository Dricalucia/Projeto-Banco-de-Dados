package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Item;
import com.cesar.portaltemaki.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/itens")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping()
    public ResponseEntity<Void> addItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> deleteItem(@PathVariable int idItem) {
        itemService.deleteItem(idItem);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idItem}")
    public ResponseEntity<Void> updateItem(@PathVariable int idItem, @RequestBody Item item) {
        Item itemExistente = itemService.findItemById(idItem);

        if (itemExistente != null) {
            item.setIdItem(idItem);
            itemService.updateItem(item);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.findAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Item> getItemById(@PathVariable int idItem) {
        Item item = itemService.findItemById(idItem);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Item>> getItemsByCategoria(@PathVariable int idCategoria) {
        List<Item> items = itemService.findItemsByCategoria(idCategoria);
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/vendidos-entre/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<Map<String, Object>>> getQuantidadeItensVendidosEntreDatas(
            @PathVariable String dataInicial, @PathVariable String dataFinal) {
        List<Map<String, Object>> quantidadeItensVendidosData = itemService
                .findQuantidadeItensVendidosEntreDatas(dataInicial, dataFinal);
        if (quantidadeItensVendidosData.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quantidadeItensVendidosData);
    }
}

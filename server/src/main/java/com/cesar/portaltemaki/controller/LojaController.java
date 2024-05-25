package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Loja;
import com.cesar.portaltemaki.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @GetMapping
    public ResponseEntity<List<Loja>> getAllLojas() {
        List<Loja> lojas = lojaService.getAllLojas();
        if (lojas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lojas);
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deleteLoja(@PathVariable String cnpj) {
        lojaService.delete(cnpj);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> addLoja(@RequestBody Loja loja) {
        lojaService.save(loja);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<Void> updateLoja(@PathVariable String cnpj, @RequestBody Loja loja) {
        Loja lojaExistente = lojaService.findByLojasCnpj(cnpj);

        if (lojaExistente != null) {
            loja.setCnpj(cnpj);
            lojaService.update(loja);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

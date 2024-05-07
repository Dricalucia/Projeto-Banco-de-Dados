package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Promocao;
import com.cesar.portaltemaki.service.PromocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promocoes")
public class PromocaoController {
    @Autowired
    private final PromocaoService promocaoService;

    public PromocaoController(PromocaoService promocaoService) {
        this.promocaoService = promocaoService;
    }

    @GetMapping
    public ResponseEntity<List<Promocao>> findAllPromocao() {
        List<Promocao> promocoes = promocaoService.findAllPromocao();
        return ResponseEntity.ok(promocoes);
    }

    @GetMapping("/{idPromocao}")
    public ResponseEntity<Promocao> findPromocaoById(@PathVariable int idPromocao) {
        Promocao promocao = promocaoService.findPromocaoById(idPromocao);
        if (promocao != null) {
            return ResponseEntity.ok(promocao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/item/{idItem}")
    public ResponseEntity<Promocao> findPromocaoByItemId(@PathVariable int idItem) {
        Promocao promocao = promocaoService.findPromocaoByItemId(idItem);
        if (promocao != null) {
            return ResponseEntity.ok(promocao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<Void> addPromocao(@RequestBody Promocao promocao) {
        promocaoService.addPromocao(promocao);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{idPromocao}")
    public ResponseEntity<Void> deletePromocao(int idPromocao) {
        promocaoService.deletePromocao(idPromocao);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idPromocao}")
    public ResponseEntity<Void> updatePromocao(@PathVariable int idPromocao, @RequestBody Promocao promocao) {
        Promocao promocaoExistente = promocaoService.findPromocaoById(idPromocao);

        if (promocaoExistente != null) {
            promocao.setIdPromocao(idPromocao);
            promocaoService.updatePromocao(promocao);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}

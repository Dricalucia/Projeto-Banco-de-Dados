package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Categoria;
import com.cesar.portaltemaki.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;
    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int idCategoria) {
        Categoria categoria = categoriaService.findCategoriaById(idCategoria);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping()
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.findAllCategorias();
        return ResponseEntity.ok(categorias);
    }
    @PostMapping()
    public ResponseEntity<Void> addCategoria(@RequestBody Categoria categoria) {
        categoriaService.saveCategoria(categoria);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable int idCategoria) {
        categoriaService.deleteCategoria(idCategoria);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{idCategoria}")
    public ResponseEntity<Void> updateCategoria(@PathVariable int idCategoria, @RequestBody Categoria categoria) {
        Categoria categoriaExistente = categoriaService.findCategoriaById(idCategoria);

        if (categoriaExistente != null) {
            categoria.setIdCategoria(idCategoria);
            categoriaService.updateCategoria(categoria);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

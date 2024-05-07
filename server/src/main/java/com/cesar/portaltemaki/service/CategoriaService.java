package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> findAllCategorias();
    Categoria findCategoriaById(int idCategoria);
    void saveCategoria(Categoria categoria);
    void deleteCategoria(int idCategoria);
    void updateCategoria(Categoria categoria);
}

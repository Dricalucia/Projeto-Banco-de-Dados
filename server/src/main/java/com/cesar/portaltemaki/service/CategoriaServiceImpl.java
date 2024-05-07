package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Categoria;
import com.cesar.portaltemaki.repository.CategoriaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> findAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findCategoriaById(int idCategoria) {
        try {
            return categoriaRepository.findByCategoriaId(idCategoria);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void saveCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(int idCategoria) {
        categoriaRepository.delete(idCategoria);
    }

    @Override
    public void updateCategoria(Categoria categoria) {
        categoriaRepository.update(categoria);
    }
}

package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Promocao;
import com.cesar.portaltemaki.repository.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PromocaoServiceImpl implements PromocaoService{

    @Autowired
    private final PromocaoRepository promocaoRepository;

    public PromocaoServiceImpl(PromocaoRepository promocaoRepository) {
        this.promocaoRepository = promocaoRepository;
    }

    @Override
    public void addPromocao(Promocao promocao) {
        promocaoRepository.addPromocao(promocao);
    }

    @Override
    public void deletePromocao(int idPromocao) {
        promocaoRepository.deletePromocao(idPromocao);
    }

    @Override
    public void updatePromocao(Promocao promocao) {
        promocaoRepository.updatePromocao(promocao);
    }

    @Override
    public List<Promocao> findAllPromocao() {
        return promocaoRepository.findAll();
    }

    @Override
    public Promocao findPromocaoById(int idPromocao) {
        try {
            return promocaoRepository.findPromocaoById(idPromocao);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Promocao findPromocaoByItemId(int idItem) {
        try {
            return promocaoRepository.promocaoByItemId(idItem);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

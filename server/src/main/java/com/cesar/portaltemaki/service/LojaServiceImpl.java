package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Loja;
import com.cesar.portaltemaki.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LojaServiceImpl implements LojaService {
    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public List<Loja> getAllLojas() {
        return lojaRepository.findAll();
    }

    @Override
    public Loja findByLojasCnpj(String cnpj) {
        return lojaRepository.findByCnpj(cnpj);
    }

    @Override
    public void save(Loja loja) {
        lojaRepository.save(loja);
    }

    @Override
    public void delete(String cnpj) {
        lojaRepository.delete(cnpj);
    }

    @Override
    public void update(Loja loja) {
        lojaRepository.update(loja);
    }
}

package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Loja;

import java.util.List;

public interface LojaService {
    List<Loja> getAllLojas();
    Loja findByLojasCnpj(String cnpj);
    void save(Loja loja);
    void delete(String cnpj);
    void update(Loja loja);
}

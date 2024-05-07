package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Promocao;

import java.util.List;

public interface PromocaoService {
    void addPromocao(Promocao promocao);
    void deletePromocao(int idPromocao);
    void updatePromocao(Promocao promocao);
    List<Promocao> findAllPromocao();
    Promocao findPromocaoById(int idPromocao);
    Promocao findPromocaoByItemId(int idItem);
}

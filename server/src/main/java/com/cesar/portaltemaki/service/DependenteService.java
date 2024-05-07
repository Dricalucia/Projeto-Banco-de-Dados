package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Dependente;

import java.util.List;

public interface DependenteService {

    List<Dependente> findDependentebyMatricula(int matricula);
    List<Dependente> findAll();
    void save(Dependente dependente);
    void update(Dependente dependente);
    void delete(String cpfDependente);
}

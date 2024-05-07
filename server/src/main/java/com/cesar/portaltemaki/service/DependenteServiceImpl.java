package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Dependente;
import com.cesar.portaltemaki.repository.DependenteRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DependenteServiceImpl implements DependenteService{

    private final DependenteRepository dependenteRepository;

    public DependenteServiceImpl(DependenteRepository dependenteRepository) {
        this.dependenteRepository = dependenteRepository;
    }

    @Override
    public List<Dependente> findDependentebyMatricula(int matricula) {
        return dependenteRepository.findByFuncionarioMatricula(matricula);
    }

    @Override
    public List<Dependente> findAll() {
        return dependenteRepository.findAll();
    }

    @Override
    public void save(Dependente dependente) {
        dependenteRepository.save(dependente);
    }

    @Override
    public void update(Dependente dependente) {
        dependenteRepository.update(dependente);
    }


    @Override
    public void delete(String cpfDependente) {
        dependenteRepository.delete(cpfDependente);
    }
}

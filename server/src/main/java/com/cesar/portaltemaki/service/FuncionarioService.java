package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Funcionario;
import java.util.List;

public interface FuncionarioService {
    Funcionario findByMatricula(int matricula);
    List<Funcionario> findAll();
    List<Funcionario> findByLojasCnpj(String cnpj);
    void save(Funcionario funcionario);
    void delete(int matricula);
    void update(Funcionario funcionario);
}

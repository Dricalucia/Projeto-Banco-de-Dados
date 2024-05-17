package com.cesar.portaltemaki.service;

import com.cesar.portaltemaki.model.Funcionario;
import com.cesar.portaltemaki.repository.FuncionarioRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario findByMatricula(int matricula) {
        try {
            return funcionarioRepository.findByMatricula(matricula);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    @Override
    public List<Funcionario> findByLojasCnpj(String cnpj) {
        return funcionarioRepository.findByLojasCnpj(cnpj);
    }


    @Override
    public void save(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Override
    public void delete(int matricula) {
        funcionarioRepository.delete(matricula);
    }

    @Override
    public void update(Funcionario funcionario) {
        funcionarioRepository.update(funcionario);
    }

    @Override
    public Funcionario findByCpfAndSenha(String cpf, String senha) {
        try {
            return funcionarioRepository.findByCpfAndSenha(cpf, senha);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

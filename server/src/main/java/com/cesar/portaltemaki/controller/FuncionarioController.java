package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Funcionario;
import com.cesar.portaltemaki.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Funcionario> getFuncionarioByMatricula(@PathVariable int matricula) {
        Funcionario funcionario = funcionarioService.findByMatricula(matricula);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.findAll();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{cpf}/{senha}")
    public ResponseEntity<Funcionario> getFuncionarioByCpfAndSenha(@PathVariable String cpf,
            @PathVariable String senha) {
        Funcionario funcionario = funcionarioService.findByCpfAndSenha(cpf, senha);
        if (funcionario != null) {
            return ResponseEntity.ok(funcionario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/loja/{cnpj}")
    public ResponseEntity<List<Funcionario>> getFuncionariosByLojasCnpj(@PathVariable String cnpj) {
        List<Funcionario> funcionarios = funcionarioService.findByLojasCnpj(cnpj);
        if (funcionarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable int matricula) {
        funcionarioService.delete(matricula);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable int matricula,
            @RequestBody Funcionario novoFuncionario) {
        Funcionario funcionarioExistente = funcionarioService.findByMatricula(matricula);

        if (funcionarioExistente != null) {
            novoFuncionario.setMatricula(matricula);
            funcionarioService.update(novoFuncionario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Void> addFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioService.save(funcionario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

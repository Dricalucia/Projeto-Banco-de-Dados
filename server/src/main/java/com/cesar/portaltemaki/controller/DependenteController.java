package com.cesar.portaltemaki.controller;

import com.cesar.portaltemaki.model.Dependente;
import com.cesar.portaltemaki.service.DependenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependentes")
public class DependenteController {

    private final DependenteService dependenteService;

    @Autowired
    public DependenteController(DependenteService dependenteService) {
        this.dependenteService = dependenteService;
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<List<Dependente>> getDependentesByMatricula(@PathVariable int matricula) {
        List<Dependente> dependetes = dependenteService.findDependentebyMatricula(matricula);
        if (dependetes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependetes);
    }

    @GetMapping
    public ResponseEntity<List<Dependente>> getAllDependentes() {
        List<Dependente> dependentes = dependenteService.findAll();
        if (dependentes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dependentes);
    }

    @PostMapping
    public ResponseEntity<Void> addDependente(@RequestBody Dependente dependente) {
        dependenteService.save(dependente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{cpf}/{matricula}")
    public ResponseEntity<Void> updateDependente(@PathVariable String cpf, @PathVariable int matricula,
            @RequestBody Dependente novoDependente) {
        List<Dependente> dependentes = dependenteService.findDependentebyMatricula(matricula);

        for (Dependente dependenteExistente : dependentes) {
            if (dependenteExistente.getCpfDependente().equals(cpf)) {
                novoDependente.setCpfDependente(cpf);
                novoDependente.setMatriculaFuncionario(matricula);
                dependenteService.update(novoDependente);
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteDependente(@PathVariable String cpf) {
        dependenteService.delete(cpf);
        return ResponseEntity.noContent().build();
    }

}

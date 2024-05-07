package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Dependente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DependenteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("deprecation")
    public List<Dependente> findByFuncionarioMatricula(int matricula) {
        String sql = "SELECT d.cpf_dependente, d.funcionario_matricula, d.nome, d.data_nascimento " +
                "FROM dependentes d " +
                "INNER JOIN funcionario f ON d.funcionario_matricula = f.matricula " +
                "WHERE f.matricula = ?";
        return jdbcTemplate.query(sql, new Object[]{matricula}, this::mapRowToDependente);
    }
    public void save(Dependente dependente) {
        String sql = "INSERT INTO dependentes (cpf_dependente, funcionario_matricula, nome, data_nascimento) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dependente.getCpfDependente(), dependente.getMatriculaFuncionario(), dependente.getNome(), dependente.getDataNascimento());
    }

    public void update(Dependente dependente) {
        String sql = "UPDATE dependentes SET nome = ?, data_nascimento = ? WHERE cpf_dependente = ? AND funcionario_matricula = ?";
        jdbcTemplate.update(sql, dependente.getNome(), dependente.getDataNascimento(), dependente.getCpfDependente(),
                dependente.getMatriculaFuncionario());
    }

    public List<Dependente> findAll() {
        String sql = "SELECT * FROM dependentes";
        return jdbcTemplate.query(sql, this::mapRowToDependente);
    }
    public void delete(String cpfDependente) {
        String sql = "DELETE FROM dependentes WHERE cpf_dependente = ?";
        jdbcTemplate.update(sql, cpfDependente);
    }

    private Dependente mapRowToDependente(ResultSet rs, int rowNum) throws SQLException {
        Dependente dependente = new Dependente();
        dependente.setCpfDependente(rs.getString("cpf_dependente"));
        dependente.setMatriculaFuncionario(rs.getInt("funcionario_matricula"));
        dependente.setNome(rs.getString("nome"));
        dependente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        return dependente;
    }
}

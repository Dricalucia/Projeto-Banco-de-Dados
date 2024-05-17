package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @SuppressWarnings("deprecation")
    public Funcionario findByMatricula(int matricula) {
        String sql = "SELECT * FROM funcionario WHERE matricula = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{matricula}, this::mapRowToFuncionario);
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        return jdbcTemplate.query(sql, this::mapRowToFuncionario);
    }

    @SuppressWarnings("deprecation")
    public Funcionario findByCpfAndSenha(String cpf, String senha) {
        String sql = "SELECT * FROM funcionario WHERE cpf = ? AND senha = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cpf, senha}, this::mapRowToFuncionario);
    }

    @SuppressWarnings("deprecation")
    public List<Funcionario> findByLojasCnpj(String cnpj) {
        String sql = "SELECT f.*, l.* FROM funcionario f " +
                "JOIN lojas l ON f.lojas_cnpj = l.cnpj " +
                "WHERE l.cnpj = ?";
        return jdbcTemplate.query(sql, new Object[]{cnpj}, this::mapRowToFuncionario);
    }

    public void delete(int matricula) {
        String sql = "DELETE FROM funcionario WHERE matricula = ?";
        jdbcTemplate.update(sql, matricula);
    }

    public void update(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET cpf = ?, nome = ?, data_admissao = ?, " +
                "funcao = ?, salario = ?, rua = ?, numero = ?, bairro = ?, " +
                "cidade = ?, uf = ?, cep = ?, status = ?, senha = ?, lojas_cnpj = ? " +
                "WHERE matricula = ?";
        jdbcTemplate.update(sql, funcionario.getCpf(), funcionario.getNome(),
                funcionario.getDataAdmissao(), funcionario.getFuncao(),
                funcionario.getSalario(), funcionario.getRua(),
                funcionario.getNumero(), funcionario.getBairro(),
                funcionario.getCidade(), funcionario.getUf(),
                funcionario.getCep(), funcionario.getStatus(),
                funcionario.getSenha(), funcionario.getLojasCnpj(),
                funcionario.getMatricula());
    }

    public void save(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (cpf, nome, data_admissao, funcao, salario, rua, numero, " +
                "bairro, cidade, uf, cep, status, senha, lojas_cnpj) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, funcionario.getCpf(), funcionario.getNome(),
                funcionario.getDataAdmissao(), funcionario.getFuncao(), funcionario.getSalario(),
                funcionario.getRua(), funcionario.getNumero(), funcionario.getBairro(),
                funcionario.getCidade(), funcionario.getUf(), funcionario.getCep(),
                funcionario.getStatus(), funcionario.getSenha(), funcionario.getLojasCnpj());
    }

    private Funcionario mapRowToFuncionario(ResultSet rs, int rowNum) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula(rs.getInt("matricula"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setDataAdmissao(rs.getTimestamp("data_admissao").toLocalDateTime());
        funcionario.setFuncao(rs.getString("funcao"));
        funcionario.setSalario(rs.getDouble("salario"));
        funcionario.setRua(rs.getString("rua"));
        funcionario.setNumero(rs.getInt("numero"));
        funcionario.setBairro(rs.getString("bairro"));
        funcionario.setCidade(rs.getString("cidade"));
        funcionario.setUf(rs.getString("uf"));
        funcionario.setCep(rs.getString("cep"));
        funcionario.setStatus(rs.getString("status"));
        funcionario.setSenha(rs.getString("senha"));
        funcionario.setLojasCnpj(rs.getString("lojas_cnpj"));
        return funcionario;
    }
}

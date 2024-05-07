package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente findByClientId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCliente}, this::mapRowToCliente);
    }

    public List<Cliente> findAll() {
        String sql = "SELECT * FROM cliente";
        return jdbcTemplate.query(sql, this::mapRowToCliente);
    }

    public void save(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, sobrenome, rua, numero, complemento, bairro, cidade, uf, telefone, email, ponto_referencia, senha, observacao, data_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getSobrenome(), cliente.getRua(),
                cliente.getNumero(), cliente.getComplemento(), cliente.getBairro(), cliente.getCidade(), cliente.getUf(),
                cliente.getTelefone(), cliente.getEmail(), cliente.getPontoReferencia(), cliente.getSenha(),
                cliente.getObservacao(), cliente.getDataCadastro());
    }

    public void delete(int idCliente) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        jdbcTemplate.update(sql, idCliente);
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, sobrenome = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ?, uf = ?, telefone = ?, email = ?, ponto_referencia = ?, senha = ?, observacao = ?, data_cadastro = ? WHERE idCliente = ?";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getSobrenome(), cliente.getRua(), cliente.getNumero(),
                cliente.getComplemento(), cliente.getBairro(), cliente.getCidade(), cliente.getUf(),
                cliente.getTelefone(), cliente.getEmail(), cliente.getPontoReferencia(), cliente.getSenha(),
                cliente.getObservacao(), cliente.getDataCadastro(), cliente.getIdCliente());
    }

    private Cliente mapRowToCliente(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("idCliente"));
        cliente.setNome(rs.getString("nome"));
        cliente.setSobrenome(rs.getString("sobrenome"));
        cliente.setRua(rs.getString("rua"));
        cliente.setNumero(rs.getInt("numero"));
        cliente.setComplemento(rs.getString("complemento"));
        cliente.setBairro(rs.getString("bairro"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setUf(rs.getString("uf"));
        cliente.setTelefone(rs.getString("telefone"));
        cliente.setEmail(rs.getString("email"));
        cliente.setPontoReferencia(rs.getString("ponto_referencia"));
        cliente.setSenha(rs.getString("senha"));
        cliente.setObservacao(rs.getString("observacao"));
        cliente.setDataCadastro(rs.getTimestamp("data_cadastro").toLocalDateTime());
        return cliente;
    }
}

package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Loja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LojaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Loja> findAll() {
        String sql = "SELECT * FROM lojas";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Loja(
                        rs.getString("cnpj"),
                        rs.getString("ie"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("ponto_referencia"),
                        rs.getBoolean("filial")
                ));
    }
    public Loja findByCnpj(String cnpj) {
        String sql = "SELECT * FROM lojas WHERE cnpj = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cnpj}, (rs, rowNum) ->
                new Loja(
                        rs.getString("cnpj"),
                        rs.getString("ie"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("uf"),
                        rs.getString("cep"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("ponto_referencia"),
                        rs.getBoolean("filial")
                ));
    }
    public void save(Loja loja) {
        String sql = "INSERT INTO lojas (cnpj, ie, nome, rua, numero, bairro, cidade, uf, cep, telefone, email, ponto_referencia, filial) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, loja.getCnpj(), loja.getIe(), loja.getNome(), loja.getRua(), loja.getNumero(),
                loja.getBairro(), loja.getCidade(), loja.getUf(), loja.getCep(), loja.getTelefone(), loja.getEmail(),
                loja.getPontoReferencia(), loja.getFilial());
    }
    public void delete(String cnpj) {
        String sql = "DELETE FROM lojas WHERE cnpj = ?";
        jdbcTemplate.update(sql, cnpj);
    }
    public void update(Loja loja) {
        String sql = "UPDATE lojas SET ie = ?, nome = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, cep = ?, " +
                "telefone = ?, email = ?, ponto_referencia = ?, filial = ? WHERE cnpj = ?";
        jdbcTemplate.update(sql, loja.getIe(), loja.getNome(), loja.getRua(), loja.getNumero(), loja.getBairro(),
                loja.getCidade(), loja.getUf(), loja.getCep(), loja.getTelefone(), loja.getEmail(),
                loja.getPontoReferencia(), loja.getFilial(), loja.getCnpj());
    }
}

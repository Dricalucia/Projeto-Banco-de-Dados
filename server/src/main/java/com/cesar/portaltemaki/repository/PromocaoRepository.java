package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Promocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PromocaoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Promocao> findAll() {
        String sql = "SELECT * FROM promocao";
        return jdbcTemplate.query(sql, this::mapRowToPromocao);
    }

    public Promocao findPromocaoById(int idPromocao) {
        String sql = "SELECT * FROM promocao WHERE idPromocao = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idPromocao}, this::mapRowToPromocao);
    }

    public Promocao promocaoByItemId(int idItem) {
        String sql = "SELECT p.*, i.* FROM promocao p JOIN item i ON p.item_idItem = i.idItem WHERE p.item_idItem = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idItem}, this::mapRowToPromocao);
    }

    public void addPromocao(Promocao promocao) {
        String sql = "INSERT INTO promocao (item_idItem, data_promocao, data_validade, preco, observacao) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, promocao.getIdItem(),
                promocao.getDataPromocao(), promocao.getDataValidade(),
                promocao.getPrecoPromocao(), promocao.getObservacao());
    }

    public void deletePromocao(int idPromocao) {
        String sql = "DELETE FROM promocao WHERE idPromocao = ?";
        jdbcTemplate.update(sql, idPromocao);
    }

    public void updatePromocao(Promocao promocao) {
        String sql = "UPDATE promocao SET data_promocao = ?, data_validade = ?, preco = ?, observacao = ? " +
                "WHERE idPromocao = ?";
        jdbcTemplate.update(sql, promocao.getDataPromocao(), promocao.getDataValidade(),
                promocao.getPrecoPromocao(), promocao.getObservacao(),
                promocao.getIdPromocao());
    }

    private Promocao mapRowToPromocao(ResultSet rs, int rowNum) throws SQLException {
        Promocao promocao = new Promocao();
        promocao.setIdPromocao(rs.getInt("idPromocao"));
        promocao.setIdItem(rs.getInt("item_idItem"));
        promocao.setDataPromocao(rs.getTimestamp("data_promocao").toLocalDateTime());
        promocao.setDataValidade(rs.getTimestamp("data_validade").toLocalDateTime());
        promocao.setPrecoPromocao(rs.getDouble("preco"));
        promocao.setObservacao(rs.getString("observacao"));
        return promocao;
    }
}

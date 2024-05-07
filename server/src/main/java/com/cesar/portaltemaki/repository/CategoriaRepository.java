package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoriaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Categoria> findAll() {
        String sql = "SELECT * FROM categoria";
        return jdbcTemplate.query(sql, this::mapRowToCategoria);
    }
    @SuppressWarnings("deprecation")
    public Categoria findByCategoriaId(int idCategoria) {
        String sql = "SELECT * FROM categoria WHERE idCategoria = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCategoria}, this::mapRowToCategoria);
    }

    public void save(Categoria categoria) {
        String sql = "INSERT INTO categoria (descricao) VALUES(?)";
        jdbcTemplate.update(sql, categoria.getDescricao());
    }

    public void update(Categoria categoria) {
        String sql = "UPDATE categoria SET descricao = ?";
        jdbcTemplate.update(sql, categoria.getDescricao());
    }

    public void delete(int idCategoria) {
        String sql = "DELETE FROM categoria WHERE idCategoria = ?";
        jdbcTemplate.update(sql, idCategoria);
    }

    private Categoria mapRowToCategoria(ResultSet rs, int rowNum) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("idCategoria"));
        categoria.setDescricao(rs.getString("descricao"));
        return categoria;
    }
}

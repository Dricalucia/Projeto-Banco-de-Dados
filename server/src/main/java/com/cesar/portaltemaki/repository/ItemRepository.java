package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addItem(Item item) {
        String sql = "INSERT INTO item (nome_item, descricao, ativo, preco, categoria_idCategoria) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getNomeItem(), item.getDescricaoItem(), item.isItemAtivo(),
                item.getPrecoItem(), item.getIdCategoria());
    }

    public void deleteItem(int idItem) {
        String sql = "DELETE FROM item WHERE idItem = ?";
        jdbcTemplate.update(sql, idItem);
    }

    public void updateItem(Item item) {
        String sql = "UPDATE item SET nome_item = ?, descricao = ?, ativo = ?, preco = ?, categoria_idCategoria = ? " +
                "WHERE idItem = ?";
        jdbcTemplate.update(sql, item.getNomeItem(), item.getDescricaoItem(), item.isItemAtivo(), item.getPrecoItem(),
                item.getIdCategoria(), item.getIdItem());
    }

    public List<Item> findAll() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, this::mapRowToItem);
    }
    @SuppressWarnings("deprecation")
    public Item findItemById(int idItem) {
        String sql = "SELECT * FROM item WHERE idItem = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idItem}, this::mapRowToItem);
    }
    
    @SuppressWarnings("deprecation")
    public List<Item> findItemsByCategoria(int idCategoria) {
        String sql = "SELECT * FROM item WHERE categoria_idCategoria = ?";
        return jdbcTemplate.query(sql, new Object[]{idCategoria}, this::mapRowToItem);
    }

    public List<Map<String, Object>> findQuantidadeItensVendidosEntreDatas(String dataInicial, String dataFinal) {
        String sql = "SELECT pit.item_idItem, i.nome_item, SUM(pit.qtde_item) AS total_vendas " +
                     "FROM pedido_itens pit " +
                     "JOIN item i ON pit.item_idItem = i.idItem " +
                     "JOIN pedido p ON pit.pedido_nrPedido = p.nrPedido " +
                     "WHERE p.data_hora_pedido BETWEEN ? AND ? " +
                     "GROUP BY pit.item_idItem, i.nome_item";
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }

    private Item mapRowToItem(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setIdItem(rs.getInt("idItem"));
        item.setNomeItem(rs.getString("nome_item"));
        item.setDescricaoItem(rs.getString("descricao"));
        item.setItemAtivo(rs.getBoolean("ativo"));
        item.setPrecoItem(rs.getDouble("preco"));
        item.setIdCategoria(rs.getInt("categoria_idCategoria"));
        return item;
    }
}

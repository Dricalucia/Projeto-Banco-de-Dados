package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.ItensPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItensPedidoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePedido(ItensPedido itensPedido) {
        String sql = "INSERT INTO pedido_itens (pedido_nrPedido, item_idItem, qtde_item) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, itensPedido.getPedidoNrPedido(), itensPedido.getItemIdItem(), itensPedido.getQuantidadeItem());
    }
    @SuppressWarnings("deprecation")
    public List<ItensPedido> findPedidoByNrPedido(int nrPedido) {
        String sql = "SELECT pi.pedido_nrPedido, pi.item_idItem, pi.qtde_item, " +
                "p.data_hora_pedido, p.data_hora_prevista_entrega, " +
                "p.data_hora_saida_entrega, p.data_hora_entrega, " +
                "p.status_pedido, p.canal_solicitacao_pedido, p.canal_entrega, " +
                "p.forma_pagamento, p.valor_total_pedido, i.nome_item, i.descricao, " +
                "i.ativo, i.preco " +
                "FROM pedido_itens pi " +
                "JOIN pedido p ON pi.pedido_nrPedido = p.nrPedido " +
                "JOIN item i ON pi.item_idItem = i.idItem " +
                "WHERE pi.pedido_nrPedido = ?";
        return jdbcTemplate.query(sql, new Object[]{nrPedido}, this::mapRowToItensPedido);
    }

    public List<ItensPedido> findAll() {
        String sql = "SELECT pi.pedido_nrPedido, pi.item_idItem, pi.qtde_item, " +
                "p.data_hora_pedido, p.data_hora_prevista_entrega, " +
                "p.data_hora_saida_entrega, p.data_hora_entrega, " +
                "p.status_pedido, p.canal_solicitacao_pedido, p.canal_entrega, " +
                "p.forma_pagamento, p.valor_total_pedido, i.nome_item, i.descricao, " +
                "i.ativo, i.preco " +
                "FROM pedido_itens pi " +
                "JOIN pedido p ON pi.pedido_nrPedido = p.nrPedido " +
                "JOIN item i ON pi.item_idItem = i.idItem";
        return jdbcTemplate.query(sql, this::mapRowToItensPedido);
    }
    public void deletePedido(int nrPedido) {
        String sql = "DELETE FROM pedido_itens WHERE pedido_nrPedido = ?";
        jdbcTemplate.update(sql, nrPedido);
    }
    private ItensPedido mapRowToItensPedido(ResultSet rs, int rowNum) throws SQLException {
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setPedidoNrPedido(rs.getInt("pedido_nrPedido"));
        itensPedido.setItemIdItem(rs.getInt("item_idItem"));
        itensPedido.setQuantidadeItem(rs.getInt("qtde_item"));
        return itensPedido;
    }
}

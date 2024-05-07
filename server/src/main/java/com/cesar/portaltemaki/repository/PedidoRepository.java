package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @SuppressWarnings("deprecation")
    public Pedido findByNrPedido(int nrPedido) {
        String sql = "SELECT * FROM pedido WHERE nrPedido = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{nrPedido}, this::mapRowToPedido);
    }

    public List<Pedido> findAll() {
        String sql = "SELECT * FROM pedido";
        return jdbcTemplate.query(sql, this::mapRowToPedido);
    }

    public void save(Pedido pedido) {
        String sql = "INSERT INTO pedido (data_hora_pedido, data_hora_prevista_entrega, data_hora_saida_entrega, " +
                "data_hora_entrega, status_pedido, canal_solicitacao_pedido, canal_entrega, forma_pagamento, " +
                "valor_total_pedido) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, pedido.getDataHoraPedido(), pedido.getDataHoraPrevistaEntrega(),
                pedido.getDataHoraSaidaEntrega(), pedido.getDataHoraEntrega(), pedido.getStatusPedido(),
                pedido.getCanalSolicitacaoPedido(), pedido.getCanalEntrega(), pedido.getFormaPagamento(),
                pedido.getValorTotalPedido());
    }

    public void delete(int nrPedido) {
        String sql = "DELETE FROM pedido WHERE nrPedido = ?";
        jdbcTemplate.update(sql, nrPedido);
    }

    public void update(Pedido pedido) {
        String sql = "UPDATE pedido SET data_hora_pedido = ?, data_hora_prevista_entrega = ?, " +
                "data_hora_saida_entrega = ?, data_hora_entrega = ?, status_pedido = ?, " +
                "canal_solicitacao_pedido = ?, canal_entrega = ?, forma_pagamento = ?, " +
                "valor_total_pedido = ? WHERE nrPedido = ?";
        jdbcTemplate.update(sql, pedido.getDataHoraPedido(), pedido.getDataHoraPrevistaEntrega(),
                pedido.getDataHoraSaidaEntrega(), pedido.getDataHoraEntrega(), pedido.getStatusPedido(),
                pedido.getCanalSolicitacaoPedido(), pedido.getCanalEntrega(), pedido.getFormaPagamento(),
                pedido.getValorTotalPedido(), pedido.getNrPedido());
    }

    private Pedido mapRowToPedido(ResultSet rs, int rowNum) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setNrPedido(rs.getInt("nrPedido"));
        pedido.setDataHoraPedido(rs.getTimestamp("data_hora_pedido").toLocalDateTime());
        pedido.setDataHoraPrevistaEntrega(rs.getTimestamp("data_hora_prevista_entrega").toLocalDateTime());
        pedido.setDataHoraSaidaEntrega(rs.getTimestamp("data_hora_saida_entrega") != null ? rs.getTimestamp
                ("data_hora_saida_entrega").toLocalDateTime() : null);
        pedido.setDataHoraEntrega(rs.getTimestamp("data_hora_entrega") != null ? rs.getTimestamp
                ("data_hora_entrega").toLocalDateTime() : null);
        pedido.setStatusPedido(rs.getString("status_pedido"));
        pedido.setCanalSolicitacaoPedido(rs.getString("canal_solicitacao_pedido"));
        pedido.setCanalEntrega(rs.getString("canal_entrega"));
        pedido.setFormaPagamento(rs.getString("forma_pagamento"));
        pedido.setValorTotalPedido(rs.getDouble("valor_total_pedido"));
        return pedido;
    }
}

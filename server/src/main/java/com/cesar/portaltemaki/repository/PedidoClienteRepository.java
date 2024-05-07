package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.PedidoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PedidoClienteRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void savePedidoCliente(PedidoCliente pedidoCliente) {
        String sql = "INSERT INTO pedido_cliente (cliente_idCliente, pedido_nrPedido) VALUES (?, ?)";
        jdbcTemplate.update(sql, pedidoCliente.getClienteIdCliente(), pedidoCliente.getPedidoNrPedido());
    }

    public void deletePedidoCliente(int clienteId) {
        String sql = "DELETE FROM pedido_cliente WHERE cliente_idCliente = ?";
        jdbcTemplate.update(sql, clienteId);
    }

    public List<PedidoCliente> findAll() {
        String sql = "SELECT pc.cliente_idCliente, pc.pedido_nrPedido FROM pedido_cliente pc " +
                "JOIN cliente c ON pc.cliente_idCliente = c.idCliente " +
                "JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido";
        return jdbcTemplate.query(sql, this::mapRowToPedidoCliente);
    }
    @SuppressWarnings("deprecation")
    public PedidoCliente findByClienteId(int idCliente) {
        String sql = "SELECT pc.cliente_idCliente, pc.pedido_nrPedido FROM pedido_cliente pc " +
                "JOIN cliente c ON pc.cliente_idCliente = c.idCliente " +
                "JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido " +
                "WHERE pc.cliente_idCliente = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{idCliente}, this::mapRowToPedidoCliente);
    }

    private PedidoCliente mapRowToPedidoCliente(ResultSet rs, int rowNum) throws SQLException {
        PedidoCliente pedidoCliente = new PedidoCliente();
        pedidoCliente.setClienteIdCliente(rs.getInt("cliente_idCliente"));
        pedidoCliente.setPedidoNrPedido(rs.getInt("pedido_nrPedido"));
        return pedidoCliente;
    }
}

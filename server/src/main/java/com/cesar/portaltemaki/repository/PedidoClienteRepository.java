package com.cesar.portaltemaki.repository;

import com.cesar.portaltemaki.model.PedidoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
        return jdbcTemplate.queryForObject(sql, new Object[] { idCliente }, this::mapRowToPedidoCliente);
    }

    public List<Map<String, Object>> findPedidoDetailsByNrPedido(int nrPedido) {
        String sql = "SELECT " +
                "    p.nrPedido, " +
                "    p.data_hora_pedido, " +
                "    p.valor_total_pedido, " +
                "    c.nome AS nome_cliente, " +
                "    c.sobrenome AS sobrenome_cliente, " +
                "    i.nome_item AS item, " +
                "    pit.qtde_item AS qtde, " +
                "    i.preco AS preco, " +
                "    p.data_hora_prevista_entrega, " +
                "    p.data_hora_saida_entrega, " +
                "    p.data_hora_entrega " +
                "FROM pedido p " +
                "JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido " +
                "JOIN cliente c ON pc.cliente_idCliente = c.idCliente " +
                "JOIN pedido_itens pit ON p.nrPedido = pit.pedido_nrPedido " +
                "JOIN item i ON pit.item_idItem = i.idItem " +
                "WHERE p.nrPedido = ?";
        return jdbcTemplate.queryForList(sql, nrPedido);
    }

    public List<Map<String, Object>> findPedidosPorClienteNoPeriodo(String dataInicial, String dataFinal, int idCliente) {
        String sql = "CALL sp_pedidos_cliente_por_periodo(?, ?, ?)";
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal, idCliente);
    }

    public List<Map<String, Object>> findPedidoComTotalMaxEntreDatas(String dataInicial, String dataFinal) {
        String sql = "SELECT p.data_hora_pedido, pc.pedido_nrPedido, c.nome AS nome_cliente, c.sobrenome AS sobrenome_cliente, p.valor_total_pedido " +
                     "FROM pedido_cliente pc " +
                     "JOIN cliente c ON pc.cliente_idCliente = c.idCliente " +
                     "JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido " +
                     "WHERE p.valor_total_pedido = ( " +
                     "    SELECT MAX(valor_total_pedido) " +
                     "    FROM pedido " +
                     "    WHERE data_hora_pedido BETWEEN ? AND ? " +
                     ") " +
                     "LIMIT 1";
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }

    public List<Map<String, Object>> findPedidoClienteCountEntreDatas(String dataInicial, String dataFinal) {
        String sql = "SELECT c.nome AS nome_cliente, c.sobrenome AS sobrenome_cliente, COUNT(pc.pedido_nrPedido) AS quantidade_pedidos " +
        "FROM pedido_cliente pc " +
        "JOIN cliente c ON pc.cliente_idCliente = c.idCliente " +
        "JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido " +
        "WHERE p.data_hora_pedido BETWEEN ? AND ? " +
        "GROUP BY c.nome, c.sobrenome";
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }

    private PedidoCliente mapRowToPedidoCliente(ResultSet rs, int rowNum) throws SQLException {
        PedidoCliente pedidoCliente = new PedidoCliente();
        pedidoCliente.setClienteIdCliente(rs.getInt("cliente_idCliente"));
        pedidoCliente.setPedidoNrPedido(rs.getInt("pedido_nrPedido"));
        return pedidoCliente;
    }
}

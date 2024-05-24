	/* Scripts SQL Avançado - RELATORIOS  */
	

/* View para listar todos os pedidos que estão com status 'em aberto' */
/* para ser usado na tela de funcionarios */
create view vw_pedidos_pendentes as
select
    nrPedido,
    data_hora_pedido,
    valor_total_pedido,
    status_pedido
from pedido
where status_pedido <> 'Concluido' -- definir os niveis de status: Em aberto, Em preparo, Saída para entrega, Concluído
order by nrPedido;  


select * from vw_pedidos_pendentes; -- chamada da view
select * from pedido p;
/* Visualizar os detalhes do pedido */
select
    p.nrPedido,
    p.data_hora_pedido,
    p.valor_total_pedido,
    c.nome as nome_cliente,
    c.sobrenome as sobrenome_cliente,
    i.nome_item as item,
	pit.qtde_item as qtde,
    i.preco as Preco,
    p.data_hora_prevista_entrega,
    p.data_hora_saida_entrega,
    p.data_hora_entrega
from pedido p
join pedido_cliente pc on p.nrPedido = pc.pedido_nrPedido
join cliente c on pc.cliente_idCliente = c.idCliente
join pedido_itens pit on p.nrPedido = pit.pedido_nrPedido
join item i on pit.item_idItem = i.idItem
where p.nrPedido = ?; -- substituir '?' pelo parametro do nrpedido, que será passado na lista de pedido

-- essa procedure retorna os pedidos realizados dentro de um determinado período de tempo
CREATE PROCEDURE sp_pedidos_por_periodo(IN data_inicial DATETIME, IN data_final DATETIME)
BEGIN
    SELECT
        p.nrPedido AS Nr_pedido,
        p.data_hora_pedido AS Data_Hora_Pedido,
        p.valor_total_pedido AS Total_Pedido,
        c.nome AS Nome_Cliente,
        c.sobrenome AS Sobrenome_Cliente
    FROM
        pedido p
        JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido
        JOIN cliente c ON pc.cliente_idCliente = c.idCliente
    WHERE
        p.data_hora_pedido BETWEEN data_inicial AND data_final;
END;



DELIMITER $$
	
-- essa procedure retorna os pedidos realizados por um cliente específico dentro de um determinado período de tempo
CREATE PROCEDURE sp_pedidos_cliente_por_periodo(IN data_inicial DATE, IN data_final DATE, IN cliente_id INT)
BEGIN
    SELECT
        p.nrPedido AS nr_Pedido,
        p.data_hora_pedido AS data_hora_pedido,
        p.valor_total_pedido AS total_pedido,
        c.nome AS nome_cliente,
        c.sobrenome AS sobrenome_cliente
    FROM
        pedido p
        JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido
        JOIN cliente c ON pc.cliente_idCliente = c.idCliente
    WHERE
        p.data_hora_pedido BETWEEN data_inicial AND data_final
        AND c.idCliente = cliente_id;
END $$

DELIMITER ;

/* listar o pedido de maior valor realizado em determinado periodo */

SELECT p.data_hora_pedido, pc.pedido_nrPedido, c.nome AS nome_cliente, c.sobrenome AS sobrenome_cliente, p.valor_total_pedido
FROM pedido_cliente pc
JOIN cliente c ON pc.cliente_idCliente = c.idCliente
JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido
WHERE p.valor_total_pedido = (
    SELECT MAX(valor_total_pedido)
    FROM pedido
    WHERE data_hora_pedido BETWEEN ? AND ? -- substituir '?' por variaveis que tenha a data inicial e final
)
)
LIMIT 1;


/* Listar por cliente a quantidade de pedidos realizados em determinado periodo */
SELECT c.nome AS nome_cliente, c.sobrenome AS sobrenome_cliente, COUNT(pc.pedido_nrPedido) AS quantidade_pedidos
FROM pedido_cliente pc
JOIN cliente c ON pc.cliente_idCliente = c.idCliente
JOIN pedido p ON pc.pedido_nrPedido = p.nrPedido
WHERE p.data_hora_pedido BETWEEN ? AND ? -- substituir '?' por variaveis que tenha a data inicial e final
GROUP BY c.nome, c.sobrenome;


/* Listar o total de vendas por item determinado periodo */
SELECT pit.item_idItem, i.nome_item, SUM(pit.qtde_item) AS total_vendas
FROM pedido_itens pit
JOIN item i ON pit.item_idItem = i.idItem
JOIN pedido p ON pit.pedido_nrPedido = p.nrPedido
WHERE p.data_hora_pedido BETWEEN ? AND ? -- substituir '?' por variaveis que tenha a data inicial e final
GROUP BY pit.item_idItem, i.nome_item;

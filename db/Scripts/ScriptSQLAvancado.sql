/* Scripts SQL Avançado 
 * Relatorios
 */


/* View para listar todos os pedidos que estão com status 'em aberto' */
/* para ser usado na tela de funcionarios */
CREATE VIEW vw_pedidos_pendentes AS
SELECT
    nrPedido,
    data_hora_pedido,
    valor_total_pedido,
    status_pedido
FROM pedido
WHERE status_pedido <> 'Concluido' -- definir os niveis de status: Em aberto, Em preparo, Saída para entrega, Concluido
ORDER BY nrPedido;  


SELECT * FROM vw_pedidos_pendentes; -- chamada da view

/* Visualizar os detalhes do pedido */
SELECT
    p.nrPedido,
    p.data_hora_pedido,
    p.valor_total_pedido,
    c.nome AS nome_cliente,
    c.sobrenome AS sobrenome_cliente,
    i.nome_item AS item,
    pi.qtde_item AS qtde,
    i.preco AS Preco,
    p.data_hora_prevista_entrega,
    p.data_hora_saida_entrega,
    p.data_hora_entrega
FROM pedido p
JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido
JOIN cliente c ON pc.cliente_idCliente = c.idCliente
JOIN pedido_itens pi ON p.nrPedido = pi.pedido_nrPedido
JOIN item i ON pi.item_idItem = i.idItem
WHERE p.nrPedido = ?; -- Substituir '?' pelo parametro do nrPedido, que será passado na lista de pedido


/* Função para exibir a lista de pedidos por determinado periodo
 * Data inicial e final devem ser passados como paramentros  */
DELIMITER $$
CREATE FUNCTION fn_pedidos_por_periodo(@data_inicial DATE, @data_final DATE)
RETURNS TABLE
AS
BEGIN
    RETURN (
        SELECT
            p.nrPedido as Nr_pedido,
            p.data_hora_pedido as Data_Hora_Pedido,
            p.valor_total_pedido as Valor_Total_Pedido,
            c.nome AS nome_cliente,
            c.sobrenome AS sobrenome_cliente
        FROM pedido p
        JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido
		JOIN cliente c ON pc.cliente_idCliente = c.idCliente
        WHERE p.data_hora_pedido BETWEEN @data_inicial AND @data_final
    );
END $$
DELIMITER ;

SELECT *
FROM fn_pedidos_por_periodo(?, ?); -- substituir '?' por variaveis que tenha a data inicial e final da seleção de pedidos

/* Função para exibir a lista de pedidos por cliente e determinado periodo
 * Data inicial e final devem ser passados como paramentros  */
DELIMITER $$
CREATE FUNCTION fn_pedidos_por_periodo(@data_inicial DATE, @data_final DATE, @cliente_id INT)
RETURNS TABLE
AS
BEGIN
    RETURN (
        SELECT
            p.nrPedido AS Nr_pedido,
            p.data_hora_pedido AS Data_Hora_Pedido,
            p.valor_total_pedido AS Valor_Total_Pedido,
            c.nome AS Nome_Cliente,
            c.sobrenome AS Sobrenome_Cliente
        FROM pedido p
        JOIN pedido_cliente pc ON p.nrPedido = pc.pedido_nrPedido
        JOIN cliente c ON pc.cliente_idCliente = c.idCliente
        WHERE p.data_hora_pedido BETWEEN @data_inicial AND @data_final
          AND c.idCliente = @cliente_id
    );
END $$
DELIMITER ;

-- chamada da função
SELECT *
FROM fn_pedidos_por_periodo(?, ?); -- substituir '?' por variaveis que tenha a data inicial e final e o codigo do cliente


/* Script para uso Trigger */
/* Criação de uma tabela log_item para registrar as alterações do cadastro dos itens
 */
DELIMITER $$
CREATE TRIGGER tr_item_to_log_itens
AFTER UPDATE ON item
FOR EACH ROW
BEGIN
    INSERT INTO log_itens (idItem, nome_item, descricao, ativo, preco, categoria_idCategoria)
    VALUES (NEW.idItem, NEW.nome_item, NEW.descricao, NEW.ativo, NEW.preco, NEW.categoria_idCategoria);
END $$
DELIMITER ;

/* Criar a tabela de Log da tabela item */
create table log_itens (						
    idItem integer,
    nome_item varchar(20),
    descricao varchar(255),
    ativo boolean,
    preco decimal(10,2),
    categoria_idCategoria integer,
    constraint fk_categoria_idCategoria foreign key (categoria_idCategoria) references categoria(idCategoria)
);
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


/* Função para exibir a lista de pedidos por determinado periodo
 * data inicial e final devem ser passados como paramentros  */
delimiter $$
create function fn_pedidos_por_periodo(@data_inicial date, @data_final date)
returns table
as
begin
    return (
        select
            p.nrPedido as Nr_pedido,
            p.data_hora_pedido as Data_Hora_Pedido,
            p.valor_total_pedido as Total_Pedido,
            c.nome as nome_cliente,
            c.sobrenome as sobrenome_cliente
        from pedido p
        join pedido_cliente pc on p.nrPedido = pc.pedido_nrPedido
		join cliente c on pc.cliente_idCliente = c.idCliente
        where p.data_hora_pedido between @data_inicial and @data_final
    );
end $$
delimiter ;

select *
from fn_pedidos_por_periodo(?, ?); -- substituir '?' por variaveis que tenha a data inicial e final da seleção de pedidos

/* Função para exibir a lista de pedidos por cliente e determinado periodo
 * data inicial e final devem ser passados como paramentros  */
delimiter $$
create function fn_pedidos_cliente_por_periodo(@data_inicial date, @data_final date, @cliente_id int)
returns table
as
begin
    return (
        select
            p.nrPedido as nr_Pedido,
            p.data_hora_pedido as data_hora_pedido,
            p.valor_total_pedido as total_pedido,
            c.nome as nome_cliente,
            c.sobrenome as sobrenome_cliente
        from pedido p
        join pedido_cliente pc on p.nrPedido = pc.pedido_nrPedido
        join cliente c on pc.cliente_idCliente = c.idCliente
        where p.data_hora_pedido between @data_inicial and @data_final
          and c.idCliente = @cliente_id
    );
end $$
delimiter ;

-- chamada da função
select *
from fn_pedidos_cliente_por_periodo(?, ?, ?); -- substituir '?' por variaveis que tenha a data inicial e final e o codigo do cliente


/* listar o pedido de maior valor realizado em determinado periodo */
select p.data_hora_pedido, pc.pedido_nrPedido, c.nome as nome_cliente, c.sobrenome as sobrenome_cliente, p.valor_total_pedido
from pedido_cliente pc
join cliente c on pc.cliente_idCliente = c.idCliente
join pedido p on pc.pedido_nrPedido = p.nrPedido
where p.valor_total_pedido = (
    select max(valor_total_pedido)
    from pedido
    where data_hora_pedido between ? and ?        -- substituir '?' por variaveis que tenha a data inicial e final
)
limit 1;

/* Listar por cliente a quantidade de pedidos realizados em determinado periodo */
select c.nome as nome_cliente, c.sobrenome as sobrenome_cliente, count(pc.pedido_nrPedido) as quantidade_pedidos
from pedido_cliente pc
join cliente c on pc.cliente_idCliente = c.idCliente
join pedido p on pc.pedido_nrPedido = p.nrPedido
where p.data_hora_pedido between ? and ?        -- substituir '?' por variaveis que tenha a data inicial e final
group by c.nome;

/* Listar o total de vendas por item determinado periodo */
select pit.item_idItem, i.nome_item, sum(pit.qtde_item) as total_vendas
from pedido_itens pit
join item i on pit.item_idItem = i.idItem
join pedido p on pit.pedido_nrPedido = p.nrPedido
where p.data_hora_pedido between ? and ?        -- substituir '?' por variaveis que tenha a data inicial e final
group by pit.item_idItem, i.nome_item;

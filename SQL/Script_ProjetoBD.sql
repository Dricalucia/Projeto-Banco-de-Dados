/* Projeto BD Restaurante */

create database restaurante;
use restaurante;

create table lojas (
    cnpj varchar(15) primary key,
    ie varchar(15),
    nome varchar(10),
    sobrenome varchar(10),
    rua varchar(40),
    uf varchar(2),
    numero integer,
    bairro varchar(15),
    cidade varchar(15),
    telefone varchar(11),
    complemento varchar(30),
    email varchar(50),
    ponto_referencia text,
    filial boolean
);

create table funcionario (
    matricula integer primary key,
    cpf varchar(11),
    nome varchar(50),
    data_admissao date,
    salario decimal(10,2),
    funcao varchar(20),
    rua varchar(50),
    numero integer,
    bairro varchar(20),
    cidade varchar(20),
    uf varchar(2),
    status char(1),
    lojas_cnpj varchar(15),
    constraint fk_lojas_cnpj foreign key (lojas_cnpj) references lojas(cnpj)
);



create table dependente (
    cpf_dependente varchar(11),
    funcionario_matricula integer,
    nome varchar(50),
    data_nascimento date,
    constraint fk_funcionario_matricula foreign key (funcionario_matricula) references funcionario(matricula),
    primary key (cpf_dependente, funcionario_matricula)
);

drop table dependente ;

create table cliente (
    idCliente integer primary key, 
	nome varchar(10),
    sobrenome varchar(10),
    rua varchar(40),
    uf varchar(2),
    numero integer,
    bairro varchar(15),
    cidade varchar(15),
    telefone varchar(11) unique,
    complemento varchar(30),
    email varchar(50),
    ponto_referencia text,
    observacao text,
    data_cadastro date
);

create table item (						# temaki salmão, urumaki, jôjô
    idItem integer primary key,
    nome_item varchar(20),
    descricao varchar(255),
    ativo boolean,
    preco decimal(10,2)
);

create table categoria (				  # Bebidas, Proteinas, Temperos, Carboidrato
    idCategoria integer primary key,
    descricao varchar(20)
);

create table produto (					# arroz, shoyo, refrigerante, salmão
    idProduto integer primary key,
    nome_produto varchar(50),
    categoria_idCategoria integer, 
    constraint fk_categoria_idCategoria foreign key (categoria_idCategoria) references categoria(idCategoria)
);

create table estoque (
	idEstoque integer,    
	produto_idProduto integer,
    estoque_minimo decimal(5,2),
	constraint fk_produto_idProduto foreign key (produto_idProduto) references produto(idProduto),
	primary key(idEstoque, produto_idProduto)
);

create table estoque_disponivel (			
    estoque_idEstoque integer,
    item_idItem integer,
    dt_validade date,
    qtd_estoque integer,
    constraint fk_estoque_idEstoque foreign key (estoque_idEstoque) references estoque(idEstoque),
    constraint fk_item_idItem_disponivel foreign key (item_idItem) references item(idItem),
	primary key(estoque_idEstoque, item_idItem,dt_validade)
);


create table pedido (
    nrPedido integer primary key,
    data_hora_pedido datetime,
    data_hora_entrega datetime,
    canal_soliticacao_pedido varchar(20) default 'site',	#Padrão será site
    status_pedido varchar(20),								#em preparo, saiu para entrega, entrega realizada
    canal_entrega varchar(20),								#em domicilio, retirada na loja
    data_hora_saida_entrega datetime,
    forma_pagamento varchar(20),							#cartão credito, cartão débito, vale alimentação, dinheiro
    valor_total_pedido decimal(10,2),
    cliente_idCliente integer,
    constraint fk_cliente_idCliente foreign key (cliente_idCliente) references cliente(idCliente)
);

create table itens_pedido (
    pedido_nrPedido integer,
    item_idItem integer,
    constraint fk_pedido_nrPedido foreign key (pedido_nrPedido) references pedido(nrPedido),
    constraint fk_item_idItem foreign key (item_idItem) references item(idItem),
    primary key(pedido_nrPedido, item_idItem)    
);

create table item_produtos (		#relacionamento N:N
    item_idItem integer,
    produto_idProduto integer,
    constraint fk_item_idItem foreign key (item_idItem) references item(idItem),
    constraint fk_produto_idProduto foreign key (produto_idProduto) references produto(idProduto),
    primary key(item_idItem, produto_idProduto)    
);


create table promocao (
    idPromocao integer primary key,
    item_idItem integer,
    data_promocao date,
    data_validade date,
    preco decimal(10,2),
    observacao varchar(100),
    constraint fk_item_idItem_promocao foreign key (item_idItem) references item(idItem)
);



create table atende_pedido (
	funcionario_matricula integer,
	pedido_nrPedido integer,
	constraint fk_pedido_nrPedido_atende foreign key (pedido_nrPedido) references pedido(nrPedido),
	constraint fk_funcionario_matricula_atende foreign key (funcionario_matricula) references funcionario(matricula),
    primary key(funcionario_matricula, pedido_nrPedido)
);





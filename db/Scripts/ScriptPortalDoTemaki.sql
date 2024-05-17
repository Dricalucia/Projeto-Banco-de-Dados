/* Projeto BD Restaurante */

create database restaurante;
use restaurante;

create table lojas (
    cnpj varchar(15) primary key,
    ie varchar(15) not null,
    nome varchar(10) not null,
    rua varchar(40) not null,
    numero integer,
    bairro varchar(15) not null,
    cidade varchar(15) not null,
    uf varchar(2) not null,    
    cep varchar(8) not null,
    telefone varchar(11),
    email varchar(50),
    ponto_referencia text,
    filial boolean
);

create table funcionario (
    matricula integer auto_increment primary key,
    cpf varchar(11) not null,
    nome varchar(50) not null,
    data_admissao datetime not null default current_timestamp,
    funcao varchar(20) not null,
    salario decimal(10,2),
    rua varchar(50) not null,
    numero integer,
    bairro varchar(20) not null,
    cidade varchar(20) not null,
    uf varchar(2) not null,
    cep varchar(8) not null,    
    status char(1) not null,
    senha varchar(20) not null, 
    lojas_cnpj varchar(15) not null,
    constraint fk_lojas_cnpj foreign key (lojas_cnpj) references lojas(cnpj)
);


create table dependentes (
    cpf_dependente varchar(11),
    funcionario_matricula integer,
    nome varchar(50) not null,
    data_nascimento date not null,
    constraint fk_funcionario_matricula foreign key (funcionario_matricula) references funcionario(matricula),
    primary key (cpf_dependente, funcionario_matricula)
);

create table cliente (
    idCliente integer auto_increment primary key, 
	nome varchar(50) not null,
    sobrenome varchar(50) not null,
    rua varchar(50) not null,
    numero integer,
    complemento varchar(50),
    bairro varchar(15) not null,
    cidade varchar(15) not null,
    uf varchar(2) not null,
    telefone varchar(11) not null,
    email varchar(50) unique not null,
    ponto_referencia text not null,
    senha varchar(20) not null,
    observacao text,
    data_cadastro datetime not null default current_timestamp
);

create table pedido (
    nrPedido integer auto_increment primary key,
    data_hora_pedido datetime not null default current_timestamp,
    data_hora_prevista_entrega datetime not null,
    data_hora_saida_entrega datetime,
    data_hora_entrega datetime,
    status_pedido varchar(20),		
    canal_solicitacao_pedido varchar(20) default 'site',	#Padrão será site						
    canal_entrega varchar(20) default 'domicilio',			#em domicilio, retirada na loja
    forma_pagamento varchar(20) default 'cartao credito',	#cartão credito, cartão débito, vale alimentação, dinheiro
    valor_total_pedido decimal(10,2)
  );

 create table categoria (				  # Bebidas, Proteinas, Temperos, Carboidrato
    idCategoria integer auto_increment primary key,
    descricao varchar(255)
);

create table item (						# temaki salmão, urumaki, jôjô
    idItem integer auto_increment primary key,
    nome_item varchar(100),
    descricao varchar(255),
    ativo boolean,
    preco decimal(10,2),
    categoria_idCategoria integer,
    constraint fk_categoria_idCategoria foreign key (categoria_idCategoria) references categoria(idCategoria)
);

create table promocao (
    idPromocao integer auto_increment,
    item_idItem integer,
    data_promocao datetime not null default current_timestamp,
    data_validade datetime not null,
    preco decimal(10,2),
    observacao varchar(255),
    constraint fk_item_idItem_promocao foreign key (item_idItem) references item(idItem),
    primary key (idPromocao, item_idItem, data_promocao)
);

create table pedido_itens (
    pedido_nrPedido integer,
    item_idItem integer,
    qtde_item integer,
    constraint fk_pedido_nrPedido_itens foreign key (pedido_nrPedido) references pedido(nrPedido),
    constraint fk_item_idItem_itens foreign key (item_idItem) references item(idItem),
    primary key(pedido_nrPedido, item_idItem)    
);


 
 create table pedido_cliente (
    cliente_idCliente integer,
    pedido_nrPedido integer,   
    constraint fk_cliente_idCliente_pedidos foreign key (cliente_idCliente) references cliente(idCliente),
    constraint fk_pedido_nrPedido_pedidos foreign key (pedido_nrPedido) references pedido(nrPedido),
    primary key (cliente_idCliente, pedido_nrPedido)
);
use restaurante;

-- Populando a tabela de lojas
INSERT INTO lojas (cnpj, ie, nome, rua, numero, bairro, cidade, uf, cep, telefone, email, ponto_referencia, filial)
VALUES
('12345678901234', '987654321', 'Loja1', 'Rua Principal', 100, 'Centro', 'São Paulo', 'SP', '01000100', '1122334455', 'loja1@email.com', 'Próximo ao metrô', false),
('98765432109876', '123456789', 'Loja2', 'Rua Secundaria', 101, 'Leste', 'São Paulo', 'SP', '02000200', '1122334466', 'loja2@email.com', 'Ao lado do shopping', true);

-- Populando a tabela de funcionarios
INSERT INTO funcionario (cpf, nome, data_admissao, funcao, salario, rua, numero, bairro, cidade, uf, cep, status, senha, lojas_cnpj)
VALUES
('12345678901', 'José Silva', NOW(), 'Gerente', 5000.00, 'Rua A', 1, 'Bairro A', 'Cidade A', 'SP', '01010101', 'A', 'senha123', '12345678901234'),
('98765432109', 'Maria Oliveira', NOW(), 'Caixa', 3000.00, 'Rua B', 2, 'Bairro B', 'Cidade B', 'SP', '02020202', 'A', 'senha123', '98765432109876');

-- Populando a tabela de dependentes
INSERT INTO dependentes (cpf_dependente, funcionario_matricula, nome, data_nascimento)
VALUES
('11122233344', 1, 'Filho de José', '2000-06-01'),
('55566677788', 2, 'Filha de Maria', '2003-09-15');

-- Populando a tabela de clientes
INSERT INTO cliente (nome, sobrenome, rua, numero, bairro, cidade, uf, telefone, email, ponto_referencia, senha)
VALUES
('Carlos', 'Ferreira', 'Rua C', 10, 'Bairro C', 'Cidade C', 'SP', '11987654321', 'carlos@email.com', 'Perto da praça', 'senha456');

-- Populando a tabela de pedidos
INSERT INTO pedido (data_hora_prevista_entrega, status_pedido, valor_total_pedido)
VALUES
(NOW() + INTERVAL 1 HOUR, 'Em preparo', 150.00);

-- Populando a tabela de categorias
INSERT INTO categoria (descricao)
VALUES
('Bebidas'), ('Proteinas');

-- Populando a tabela de itens
INSERT INTO item (nome_item, descricao, ativo, preco, categoria_idCategoria)
VALUES
('Coca-Cola', 'Refrigerante de cola 350ml', true, 5.00, 1),
('Picanha', '250g de picanha na brasa', true, 50.00, 2);

-- Populando a tabela de promocao
INSERT INTO promocao (item_idItem, data_validade, preco, observacao)
VALUES
(1, NOW() + INTERVAL 30 DAY, 4.50, 'Promoção de verão');

-- Populando a tabela de pedido_itens
INSERT INTO pedido_itens (pedido_nrPedido, item_idItem, qtde_item)
VALUES
(1, 1, 3),
(1, 2, 1);

-- Populando a tabela de pedido_cliente
INSERT INTO pedido_cliente (cliente_idCliente, pedido_nrPedido)
VALUES
(1, 1);

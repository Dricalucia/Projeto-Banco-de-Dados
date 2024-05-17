use restaurante;

-- Populando a tabela de lojas
INSERT INTO lojas (cnpj, ie, nome, rua, numero, bairro, cidade, uf, cep, telefone, email, ponto_referencia, filial)
VALUES
('12345678901234', '987654321', 'Loja 1', 'Rua A', 10, 'Centro', 'Cidade A', 'UF', '12345678', '1234567890', 'email1@teste.com', 'Ponto 1', TRUE),
('23456789012345', '876543210', 'Loja 2', 'Rua B', 20, 'Centro', 'Cidade B', 'UF', '23456789', '0987654321', 'email2@teste.com', 'Ponto 2', TRUE),
('34567890123456', '765432109', 'Loja 3', 'Rua C', 30, 'Centro', 'Cidade C', 'UF', '34567890', '5678901234', 'email3@teste.com', 'Ponto 3', FALSE);

-- Populando a tabela de funcionarios
INSERT INTO funcionario (cpf, nome, data_admissao, funcao, salario, rua, numero, bairro, cidade, uf, cep, status, senha, lojas_cnpj)
VALUES
(1, '12345678901', 'Gabriela Almeida', NOW(), 'Gerente', 5000.00, 'Rua A', 10, 'Centro', 'Cidade A', 'UF', '12345678', 'A', 'senha123', '12345678901234'),
(2, '23456789012', 'Sofia Santos', NOW(), 'Nutricionsita', 4000.00, 'Rua B', 20, 'Centro', 'Cidade B', 'UF', '23456789', 'A', 'senha456', '12345678901234'),
(3, '34567890123', 'Juliana Pereira', NOW(), 'Chef', 2500.00, 'Rua C', 30, 'Centro', 'Cidade C', 'UF', '34567890', 'A', 'senha789', '12345678901234');
(4, '78901234567', 'Camila Oliveira', NOW(), 'Atendente', 1500.00, 'Rua D', 40, 'Centro', 'Cidade D', 'UF', '45678901', 'A', 'senha456', '12345678901234'),
(5, '89012345678', 'Beatriz Costa', NOW(), 'Assistente Cozinha', 1400.00, 'Rua E', 50, 'Centro', 'Cidade E', 'UF', '56789012', 'A', 'senha567', '12345678901234'),
(6, '90123456789', 'Isabella Martins', NOW(), 'Chef', 2500.00, 'Rua F', 60, 'Centro', 'Cidade F', 'UF', '67890123', 'A', 'senha678', '23456789012345'),
(7, '01234567890', 'Laura Rodrigues', NOW(), 'Atendente', 1400.00, 'Rua G', 70, 'Centro', 'Cidade G', 'UF', '78901234', 'A', 'senha789', '23456789012345'),
(8, '12345678901', 'Mariana Fernandes', NOW(), 'Chef', 2500.00, 'Rua H', 80, 'Centro', 'Cidade H', 'UF', '89012345', 'A', 'senha890', '34567890123456'),
(9, '23456789012', 'Gabriela Almeida', NOW(), 'Atendente', 1400.00, 'Rua I', 90, 'Centro', 'Cidade I', 'UF', '90123456', 'A', 'senha901', '34567890123456'),
(10, '34567890123', 'Ana Silva', NOW(), 'Assistente Cozinha', 1400.00, 'Rua J', 100, 'Centro', 'Cidade J', 'UF', '01234567', 'A', 'senha012', '34567890123456');

-- Populando a tabela de dependentes
INSERT INTO dependentes (cpf_dependente, funcionario_matricula, nome, data_nascimento)
VALUES
('12345678901', 1, 'Lucas Carvalho', '2020-01-01'),
('23456789012', 2, 'Ana Santos', '2008-02-02'),
('34567890123', 3, 'Mateus Oliveira', '1998-03-03');

-- Populando a tabela de clientes
INSERT INTO cliente (nome, sobrenome, rua, numero, bairro, cidade, uf, telefone, email, ponto_referencia, senha)
VALUES
(1, 'Daniel', 'Rodrigues', 'Rua X', 10, 'Apto 1', 'Bairro A', 'Cidade A', 'UF', '1234567890', 'cliente1@teste.com', 'Ponto X', 'senha123', 'Observacao 1', NOW()),
(2, 'Leonardo', 'Ferreira', 'Rua Y', 20, 'Apto 2', 'Bairro B', 'Cidade B', 'UF', '2345678901', 'cliente2@teste.com', 'Ponto Y', 'senha456', 'Observacao 2', NOW()),
(3, 'Manuela', 'Oliveira', 'Rua Z', 30, 'Apto 3', 'Bairro C', 'Cidade C', 'UF', '3456789012', 'cliente3@teste.com', 'Ponto Z', 'senha789', 'Observacao 3', NOW());
(4, 'Giovanna', 'Santos', 'Rua M', 40, 'Apto 4', 'Bairro A', 'Cidade A', 'UF', '1234567890', 'cliente4@teste.com', 'Ponto M', 'senha123', 'Observacao 4', NOW()),
(5, 'André', 'Almeida', 'Rua N', 50, 'Apto 5', 'Bairro B', 'Cidade B', 'UF', '2345678901', 'cliente5@teste.com', 'Ponto N', 'senha456', 'Observacao 5', NOW()),
(6, 'Rodrigo', 'Costa', 'Rua O', 60, 'Apto 6', 'Bairro C', 'Cidade C', 'UF', '3456789012', 'cliente6@teste.com', 'Ponto O', 'senha789', 'Observacao 6', NOW()),
(7, 'Carolina', 'Silva', 'Rua P', 70, 'Apto 7', 'Bairro D', 'Cidade D', 'UF', '4567890123', 'cliente7@teste.com', 'Ponto P', 'senha012', 'Observacao 7', NOW()),
(8, 'Eduardo', 'Pereira', 'Rua Q', 80, 'Apto 8', 'Bairro E', 'Cidade E', 'UF', '5678901234', 'cliente8@teste.com', 'Ponto Q', 'senha345', 'Observacao 8', NOW()),
(9, 'Thiago', 'Cardoso', 'Rua R', 90, 'Apto 9', 'Bairro F', 'Cidade F', 'UF', '6789012345', 'cliente9@teste.com', 'Ponto R', 'senha678', 'Observacao 9', NOW()),
(10, 'Larissa', 'Martins', 'Rua S', 100, 'Apto 10', 'Bairro G', 'Cidade G', 'UF', '7890123456', 'cliente10@teste.com', 'Ponto S', 'senha890', 'Observacao 10', NOW()),
(11, 'Bruna', 'Fernandes', 'Rua T', 110, 'Apto 11', 'Bairro H', 'Cidade H', 'UF', '8901234567', 'cliente11@teste.com', 'Ponto T', 'senha901', 'Observacao 11', NOW()),
(12, 'Rafael', 'Gonçalves', 'Rua U', 120, 'Apto 12', 'Bairro I', 'Cidade I', 'UF', '9012345678', 'cliente12@teste.com', 'Ponto U', 'senha234', 'Observacao 12', NOW()),
(13, 'Vanessa', 'Sousa', 'Rua V', 130, 'Apto 13', 'Bairro J', 'Cidade J', 'UF', '0123456789', 'cliente13@teste.com', 'Ponto V', 'senha567', 'Observacao 13', NOW()),
(14, 'Mariana', 'Mendes', 'Rua W', 140, 'Apto 14', 'Bairro K', 'Cidade K', 'UF', '1234567890', 'cliente14@teste.com', 'Ponto W', 'senha890', 'Observacao 14', NOW());

-- Populando a tabela de pedidos
INSERT INTO pedido (data_hora_prevista_entrega, status_pedido, valor_total_pedido)
VALUES
(1, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Liberado Entrega', 'site', 'domicilio', 'cartao credito', 100.00),
(2, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Concluído', 'site', 'domicilio', 'cartao debito', 150.00),
(3, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em andamento', 'site', 'domicilio', 'dinheiro', 200.00);
(4, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao credito', 50.00),
(5, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao debito', 75.00),
(6, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'vale alimentacao', 100.00),
(7, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'dinheiro', 125.00),
(8, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao credito', 150.00),
(9, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao debito', 175.00),
(10, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'vale alimentacao', 200.00),
(11, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'dinheiro', 225.00),
(12, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao credito', 250.00),
(13, NOW(), DATE_ADD(NOW(), INTERVAL 1 HOUR), NOW(), NOW(), 'Em preparo', 'site', 'domicilio', 'cartao debito', 275.00);


-- Populando a tabela de categorias
INSERT INTO categoria (descricao)
VALUES
(1, 'Bebidas'),
(2, 'Supremos'),
(3, 'Entradas'),
(4, 'Invenções do Portal'),
(5, 'Batatas'),
(6, 'Combinados'),
(7, 'Especiais'),
(8, 'Pratos Especiais'),
(9, 'Petiscos'),
(10, 'Hot Holls'),
(19, 'Sucos'),

/* Mais dados para categoria - FALTA INSERIR os itens de 11 a 21 */
(11, 'Molhos'),
(12, 'Temakis'),
(13, 'Rolinhos'),
(14, 'Uramakis'),
(15, 'Hossomakis'),
(16, 'Niguiri (6und)'),
(17, 'Chinesa'),
(18, 'Sashimi'),
(20, 'Promoção do dia'),
(21, 'Festival Ogro 2023');

-- Populando a tabela de itens
INSERT INTO item (nome_item, descricao, ativo, preco, categoria_idCategoria)
VALUES
(1, 'Abacaxi com Hortelã', 'Jarra Abacaxi com Hortelã', TRUE, 16.00, 19),
(2, 'Acerola', 'Jarra Acerola', TRUE, 16.00, 19),
(3, 'Cajá', 'Jarra Cajá', TRUE, 16.00, 19),
(4, 'Laranja', 'Jarra de laranja', TRUE, 16.00, 19),
(5, 'Acerola', 'Jarra de Acerola', TRUE, 16.00, 19),
(6, 'Goiaba', 'Jarra de Goiaba', TRUE, 16.00, 19),
(7, 'Graviola', 'Jarra de Graviola', TRUE, 16.00, 19),
(8, 'Limão', 'Jarra de Limão', TRUE, 16.00, 19),
(9, 'Uva', 'Jarra de Uva', TRUE, 16.00, 19),
(10, 'Tangerina', 'Jarra de Tangerina', TRUE, 16, 19),

-- Populando tabela item com itens da categoria Supremos
(11, 'Big EbiJô', 'Deliciosa manta de salmão maçaricada recheada com camarão empanado, cheese, arroz, regado ao molho teriyaki, gergelim torrado e cebolinho', TRUE, 79.90, 2),
(12, 'Big EbiSkin', 'Manta de Salmão maçaricada, recheada camarão empanado, skin, cream cheese, arroz, cebolinho regado ao molho teriaky e gergelim.', TRUE, 74.90, 2),
(13, 'Big Jôjô', 'Manta de Salmão maçaricada, recheada com pasta de salmão, cream cheese, arroz, base de hot sucesso, cebolinho regado ao molho teriaky e gergelim.', TRUE, 81.90, 2),
(14, 'Mega Supremo 1,5kg', 'Manta de salmão maçaricada recheada com pasta de kani, base de hot sucesso, coberto com camarões emapanados, cebolinho regado ao molho teriaky e gergelin.', TRUE, 85.90, 2),
(15, 'Mega Supremo 700g', 'Manta de salmão maçaricada recheada com pasta de Kani, mix de peixes fritos, cream cheese, arroz, camarão empanado, regado ao molho teriyaki, gergelim e cebolinho. (acompanha 1 teriaki de 50 ml e 4 shoyu sachê)', TRUE, 59.90, 2),
(16, 'Spremo Ogro EbiSkin 2kg', 'Manta de salmão maçaricada recheado com camarão empanado, skin, cream cheese, arroz, cebolinho regado ao molho teriyaki e gergelim. Acompanha 3 teriyakis e 5 shoyus.', TRUE, 101.90, 2),
(17, 'Super Ebi 1,5kg', 'Manta de Salmão maçaricada com camarão empanado cream cheese, arroz, cebolinho regado ao molho teriaky e gergelim', TRUE, 85.90, 2),
(18, 'Super Ebi 700g', 'Manta de salmão maçariada recheada com camarão empanado, cream cheese,arroz, cebolinho regado ao molho teriaky e gergelim acompanha 2 bisnarga de teriki 30ml ,3 shoyu sachê', TRUE, 59.90, 2),
(19, 'Super EbiSkin 1,5kg', 'Manta de Salmão maçaricada com camarão empanado, skin, cream cheese, arroz, cebolinho regado ao molho teriaky e gergelim', TRUE, 79.90, 2),
(20, 'Supremo Ebi do Portal Jr 400g', 'Manta de salmão maçariada recheada com camarão empanado, cream cheese, cebolinho regado ao molho teriaky e gergelim acompanha 1 copinho de teriki 30ml ,2 shoyu', TRUE, 34.90, 2),
(21, 'Supremo EbiSkin 700g', 'Manta de salmão maçariada recheada com camarão empanado, skin frito, cream cheese,arroz, cebolinho regado ao molho teriaky e gergelim. Acompanha 1 teriyaki 30ml e 2 shoyu sachê', TRUE, 54.90, 2),
(22, 'Supremo Filadelfia Jr 400g', 'Manta de salmão maçariada recheada com salmão empanado, cream cheese, cebolinho regado ao molho teriaky e gergelim acompanha 1 copinho de teriki 30ml ,2 shoyu', TRUE, 35.90, 2),

-- Itens da categoria Bebidas
(23, 'Água com gás', 'Água com gás 500ml', TRUE, 4.00, 1),
(24, 'Água sem gás', 'Água sem gás 500ml', TRUE, 3.00, 1),
(25, 'Antartica lata', 'Lata de Antartica', TRUE, 5.90, 1),
(26, 'Budweiser latão', 'Latão de Budweiser', TRUE, 10.00, 1),
(27, 'Coca Lata', 'Lata de Coca-Cola', TRUE, 5.90, 1),
(28, 'Fanta/Sukita lata', 'Lata de Fanta/Sukita', TRUE, 5.90, 1),
(29, 'Heinecken zero', 'Lata de Heinecken Zero', TRUE, 11.00, 1),
(30, 'Heineken long', 'Long Neck de Heineken', TRUE, 10.00, 1),

-- Itens da categoria Batatas
(31, 'Batata Frita com cheddar', 'Batata frita com cheddar - aproximadamente 350g', TRUE, 20.00, 5),
(32, 'Batata tradicional', 'Batata frita - aproximadamente 350g', TRUE, 15.00, 5),

-- itens da categoria Invenções do Portal
(33, 'DogSushi Camarão', 'Holll frito recheado com cream cheese coberto com camarão empanado e natural, cebolinho, gergelim torrado e nosso fabuloso molho teriaky. acompanha 1 teriaki 25 ml e 2 shoyu sachê', TRUE, 32.90, 4),
(34, 'DogSushi Kani', 'Holll frito recheado com cream cheese coberto com kani, cebolinho, gergelim torrado e nosso fabuloso molho teriaky. acompanha 1 teriaki 25 ml e 2 shoyu sachê', TRUE, 29.90, 4),
(35, 'DogSushi Mix', 'Holll frito recheado com cream cheese coberto com salmão, kani, camarão empanado, cebolinho, gergelim torrado e nosso fabuloso molho teriaky. acompanha 1 teriaki 25 ml e 2 shoyu sachê', TRUE, 35.90, 4),
(36, 'DogSushi Salmão', 'Holll frito recheado com cream cheese coberto com salmão, cebolinho, gergelim torrado e nosso fabuloso molho teriaky. acompanha 1 teriaki 25 ml e 2 shoyu sachê', TRUE, 32.90, 4),
(37, 'Sanduba do Portal', 'Pãozinho de arroz e nori empanado na farinha panko, recheado com cream cheese e salmão, regado ao molho teriaky, cebolinho e gergelim torrado. acompanha 1 teriaky 30ml e shoyu sachê', TRUE, 39.90, 4),
(38, 'SushiBurguer 500g', 'Delicioso sushiburguer - com arroz, cheese, camarão empanado, salmão natural, regado ao molho teriaky, gergelim torrado e cebolinho. acompanha 1 teriaki 2 shoyu', TRUE, 39.90, 4),

-- itens da categoria Combinados
(39, 'Combo Frito', '14 cariocas de kani - 13 cariocas salmão - 13 cariocas camarão acompanha 2 shoyu sachê 1 bisnaga 30ml', TRUE, 45.00, 6),
(40, 'Combo Kawasaki', '1-Temaki mix do portal -recheado com arrox salmão batido com cream cheese e kani e camarão rosado 05 uramaki de camarão 05 hossomaki de kani 12 cariocas de salmão acompanha 1 teriaki de 30 ml 2 shoyu sachê', TRUE, 54.90, 6),
(41, 'Combo Niva', '13 cariocas de salmão, 11 uramakis de camarão empanado, 02 especias dragon, 02 especiais jojo e 02 especiais ebijo. Acompanha 1 teriaky e 2 shoyus', TRUE, 49.90, 6),
(42, 'Combo Samurai', '14 cariocas de kani - 13 cariocas salmão - 13 cariocas camarão empanado - 10 uramakis de kani- 10 uramakis skin acompanha 1 teriaki e 3 shoyu sachê', TRUE, 65.00, 6),
(43, 'Combo Xodó', '02 especiais jôjô, 02 especiais Ebijô, 2 niguiri de salmão , 06 cariocas de salmão, 05 especiais Harujô, 05 uramakis de kani, 10 hossomakis de skin. Acompanha01 teriaky e 02 shoyus', TRUE, 54.90, 6),
(44, 'Fuji', '10 uramakis de salmão, 06 cariocas de salmão, 05 jôjô, 10 uramakis camarão especial.', TRUE, 59.90, 6),
(45, 'Katmandu', '06 Cariocas de salmão, 05 Uramakis de salmão, 05 Hossomakis de salmão.', TRUE, 28.90, 6),
(46, 'Kobe', '06 cariocas de salmão, 05 hossomakis de atum, 05 uramakis de kani, 2 niguiri de peixe branco, 02 niguiri de salmão.', TRUE, 26.90, 6),
(47, 'Nikko', '05 cariocas de salmão, 05 uramakis de salmão, 05 jôjô, 05 niguiri de salmão, 05 sashimi de salmão.', TRUE, 59.90, 6),
(48, 'Rodizio em casa -Combo Gueixa', '02 hot sucesso 06 cariocas de salmão 05 uramakis de camarão 05 hossomakis de kani 02 especiais JôJô 04 especiais Harujô 1 temaki mix do Portal 2 palitos de cream cheese Acompanha 1 teriaki 30 ml shoyu sachê 2 unidades', TRUE, 75.50, 6),
(49, 'Sendai', '26 cariocas de salmão, 05 uramakis de salmão, 05 uramakis skin, 10 hossomakis atum, 03 niguiri de atum, 03 niguiri de peixe branco, 04 niguiri de salmão. Acompanha 4 shoyus e 2 teriakis', TRUE, 72.90, 6),
(50, 'Sumô', '13 cariocas de salmão, 05 hossomakis de atum, 05 uramakis de kani, 05 uramakis salmão, 05 uramakis skin, 03 niguiri atum, 04 niguiri de salmão.', TRUE, 52.90, 6),

-- itens da categoria Especiais
(51, 'Dragon (3und)', 'Lamina de salmão maçaricada envolto com cream cheese, com géleia de maracujá, camarão e cebolinho', TRUE, 17.90, 7),
(52, 'EbiJô (4ud)', 'Lamina de salmão maçaricada envolto no arroz, camarão, cream cheese e cebolinho - Acompanha 1 shoyu sachê', TRUE, 17.90, 7),
(53, 'JôJô (4und)', 'Lamina de salmão maçaricada envolto no arroz, com pasta de salmão, cream cheese e cebolinho - Acompanha 1 shoyu sachê', TRUE, 17.90, 7),
(54, 'Kani keijo (4und)', 'Kani envolto com cream cheese - Acompanha 1 shoyu sachê', TRUE, 12.00, 7),
(55, 'Uramaki Especial do Portal 10und - Camarão', 'Enrolados de arroz por fora e recheados por dentro e recheado com camarão empanado e cream cheese e coberto com uma manta de salmão maçaricada 10 unidades - Acompanha 1 shoyu sachê', TRUE, 25.00, 7),
(56, 'Uramaki Especial do Portal 10und - Salmão', 'Enrolados de arroz por fora e recheados por dentro e recheado com salmão e cream cheese e coberto com uma manta de salmão maçaricada 10 unidades - Acompanha 1 shoyu sachê', TRUE, 25.00, 7),

-- itens da categoria Pratos Especiais
(61, 'Camarão Internacional', 'Risoto recheado com arroz, cream cheese, camarão, queijo mussarela, presunto, ervilha e batata palha', TRUE, 69.90, 8),
(62, 'Camarão Olinda', 'Delicioso camarão cremoso a base de cream cheese 400g, acompanha batata frita 250G', TRUE, 55.90, 8),
(63, 'Frango Especial', 'Risoto recheado com arroz, frango em cubos, cream cheese, queijo mussarela, presunto, ervilha e batata palha', TRUE, 65.90, 8),


-- itens da categoria Petiscos
(71, 'Tempurá de camarão', '10 camarões empanados na farinha panko - acompanha 1 shoyu sachê', TRUE, 22.90, 9),
(72, 'Tempurá de Peixe', '8 tiras de peixe empanado na farinha panko - acompanha 1 shoyu sachê', TRUE, 24.90, 9),
(73, 'Tempurá Legumes', '6 bolinhos de mix de legumes empanado - acompanha 1 shoyu sachê', TRUE, 19.90, 9),

-- itens da categoria Entradas
(89, 'Camarão Perfeito', '6 deliciosos camarões empanados e fritos com cream cheese', TRUE, 22.00, 3),
(90, 'Carpaccio de salmão', 'Finas fatias frescas de salmão com molho especial cítrico da casa. Aproximadamente 150g', TRUE, 29.90, 3),
(91, 'Cerviche', 'Mix de peixes marinados em azeite extra, limão, tempero japonês e cebola', TRUE, 31.90, 3),
(92, 'Hot Sucesso', 'Bolinhos fritos com mix de peixes fritos - 6 unidades - acompanha 1 shoyu sachê', TRUE, 18.90, 3),
(93, 'Sunomono Atum', 'Salada de pepino japonês em conserva com atum. Acompanha 1 shoyu sachê', TRUE, 24.90, 3),
(94, 'Sunomono Camarão', 'Salada de pepino japonês em conserva com camarões. Acompanha 1 shoyu sachê', TRUE, 26.90, 3),
(95, 'Sunomono de Kani', 'Salada de pepino japonês em conserva com kani kama e gergelim. Acompanha 1 shoyu sachê', TRUE, 18.90, 3),
(96, 'Sunomono de Pepino', 'Salada de pepino japonês em conserva. Acompanha 1 shoyu sachê', TRUE, 12.90, 3),
(97, 'Sunomono de Salmão', 'Salada de pepino japonês em conserva com cubos de salmão. Acompanha 1 shoyu sachê', TRUE, 26.90, 3),
(98, 'Sunomono Misto', 'Salada de pepino japonês em conserva com mix de peixes. Acompanha 1 shoyu sachê', TRUE, 24.90, 3),
(99, 'Sunomono Peixe Branco', 'Salada de pepino japonês em conserva com Peixe Branco. Acompanha 1 shoyu sachê', TRUE, 24.90, 3),
(100, 'Sunomono Polvo', 'Salada de pepino japonês em conserva com polvo. Acompanha 1 shoyu sachê', TRUE, 24.90, 3),
(101, 'Tataki Salmão Trufado', '5 Lâminas de salmão maçaricadas com azeite trufado - acompanha 1 shoyu sachê', TRUE, 32.90, 3),

-- itens da categoria Hot Holls
(116, 'Carioca de Atum', '10 unidades de carioca recheado com atum e queijo', TRUE, 19.90, 10),
(117, 'Carioca de Camarão 10 unidades', 'Sushi empanado e frito recheado com camarão empanado e queijo - acompanha 1 shoyu sachê', TRUE, 19.90, 10),
(118, 'Carioca de Cebola empanada', '10 cariocas recheados com cebola empanada e cream cheese', TRUE, 17.90, 10),
(119, 'Carioca de Kani10 unidades', 'Sushi empanado e frito recheado com kani e cream cheese - acompanha 1 shoyu sachê', TRUE, 18.90, 10),
(120, 'Carioca de Salmão 10 unidades', 'Sushi empanado e frito recheado com salmão e queijo - acompanha 1 shoyu sachê', TRUE, 19.90, 10),
(121, 'Carioca de Shitake', '10 cariocas de cogumelo', TRUE, 18.90, 10),
(122, 'Carioca Skin - 10 unidades', 'Sushi empanado e frito recheado com skin e cream cheese - acompanha 1 shoyu sachê', TRUE, 18.90, 10),
(123, 'Harumaki de Kani (10 und)', 'Sushi na massa harumaki com recheio de kani e cream cheese - acompanha 1 shoyu sachê', TRUE, 21.00, 10),
(124, 'Harumaki de Salmão (10 unid)', 'Sushi na massa harumaki com recheio de salmão e cream cheese - acompanha 1 shoyu sachê', TRUE, 22.00, 10),
(125, 'Harumaki Especial - sem arroz 8 und', 'Sushi na massa harumaki com recheio de salmão, kani e cream cheese - sem arroz', TRUE, 29.00, 10);

-- itens da categoria Temakis
(144, 'Temaki Camarão', 'Sushi em forma de cone recheado com camarão e cream cheese - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(145, 'Temaki de Atum', 'Sushi empanado e frito recheado com Atum e queijo - acompanha 1 shoyu sachê', TRUE, 25.90, 12),
(146, 'Temaki Ebi Salmão', 'Sushi em forma de cone recheado com salmão, camarão e cream cheese - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(147, 'Temaki Ebi Top', 'Sushi em forma de cone recheado com salmão batido com cream cheese e camarão empanado - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(148, 'Temaki Filadelfia', 'Sushi em forma de cone recheado com pasta de salmão batida com cream cheese e cebolinho - Acompanha 1 shoyu sachê', TRUE, 25.90, 12),
(149, 'Temaki Hot Ebi', 'Sushi em forma de cone recheado com camarão empanado e cream cheese - Acompanha 1 shoyu sachê', TRUE, 23.90, 6),
(150, 'Temaki Hot Salmão', 'Sushi em forma de cone recheado com salmão empanado, cream cheese e cebolinho - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(151, 'Temaki Mix do Portal', 'Sushi em forma de cone recheado com salmão, kani e camarão batidos com cream cheese - Acompanha 1 shoyu sachê', TRUE, 23.90, 12),
(152, 'Temaki Peixe Branco', 'Sushi em forma de cone recheado com peixe branco em cubos, cream cheese e cebolinho', TRUE, 25.90, 12),
(153, 'Temaki Polvo', 'Sushi em forma de cone recheado com polvo e cream cheese', TRUE, 25.90, 12),
(154, 'Temaki Portal Kani', 'Sushi em forma de cone recheado com kani e cream cheese - Acompanha 1 shoyu sachê', TRUE, 22.90, 12),
(155, 'Temaki Salmão Fire', 'Sushi em forma de cone recheado com 6 laminas maçaricadas e recheadas com cream cheese', TRUE, 28.90, 12),
(156, 'Temaki Salmão Simples', 'Sushi em forma de cone recheado com salmão em cubos, cebolinho e gergelim - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(157, 'Temaki Shitake', 'Sushi em forma de cone recheado com shitake, cream cheese, cebolinho e gergelim - Acompanha 1 shoyu sachê', TRUE, 25.90, 12),
(158, 'Temaki Skin', 'Sushi em forma de cone recheado com skin e cream cheese - Acompanha 1 shoyu sachê', TRUE, 20.90, 12),
(159, 'Temaki Top Mix', 'Sushi em forma de cone recheado com três proteínas a sua escolha (colocar na observação qual sabor você deseja) - Acompanha 1 shoyu sachê', TRUE, 27.90, 12),
(160, 'Temaki Vegano', 'Sushi em forma de cone recheado com arroz com cream cheese, cenoura, cebola empanada e pepino - acompanha 1 shoyu', TRUE, 19.90, 12),

-- itens da categoria Rolinhos
(161, 'Rolinho Doce Leite - 2 unidades', 'Rolinho na massa harumaki com doce de leite e polvilhado com açúcar e canela', TRUE, 14.00, 13),
(162, 'Rolinho Frango e Cream Cheese - 2 unidades', 'Rolinho na massa harumaki com frango e cream cheese - acompanha molho agridoce', TRUE, 14.00, 13),
(163, 'Rolinho Primavera - 2 unidades', 'Rolinho na massa harumaki com carne, repolho e cenoura - acompanha molho agridoce', TRUE, 14.00, 13),
(164, 'Rolinho Queijo - 2 unidades', 'Rolinho na massa harumaki com queijo - acompanha molho agridoce', TRUE, 14.00, 13),
(165, 'Rolinho Romeu e Julieta - 2 unidades', 'Rolinho na massa harumaki com queijo e goiabada - acompanha molho agridoce', TRUE, 14.00, 13),
(166, 'Rolinhos de Camarão - 2 unidades', 'Rolinhos recheados com camarão e cream cheese - acompanha 1 shoyu sachê', TRUE, 18.00, 13),
(167, 'Rolinhos Nutella - 2 unidades', 'Rolinhos recheados com creme de avelã', TRUE, 18.00, 13),

-- itens da categoria Uramakis
(168, 'Uramaki de Atum 10und', 'Enrolados de arroz por fora e recheados por dentro e recheado com atum e cream cheese - Acompanha 1 shoyu sachê', TRUE, 19.90, 14),
(169, 'Uramaki de Camarão empanado (10und)', 'Enrolados de arroz por fora e recheados por dentro e recheado com camarão empanado e cream cheese - Acompanha 1 shoyu sachê', TRUE, 19.90, 14),
(170, 'Uramaki de Cebola empanada 10und', 'Enrolados de arroz por fora e recheados por dentro e recheado com cebola empanada - Acompanha 1 shoyu sachê', TRUE, 17.90, 14),
(171, 'Uramaki de Kani (10und)', 'Enrolados de arroz por fora e recheados por dentro e recheado com kani e cream cheese - Acompanha 1 shoyu sachê', TRUE, 18.90, 14),
(172, 'Uramaki de Salmão 10und', 'Enrolados de arroz por fora e recheados por dentro e recheado com salmão e cream cheese - Acompanha 1 shoyu sachê 10 unidades', TRUE, 19.90, 14),
(173, 'Uramaki de Skin 10und', 'Enrolados de arroz por fora e recheados por dentro e recheado com salmão skin e cream cheese 10 unidades - Acompanha 1 shoyu sachê', TRUE, 18.90, 14),
(174, 'Uramaki Shitake 10und', 'Enrolados de arroz por fora e recheados por dentro e recheado com shitake, cream cheese e cebolinho 10 unidades - Acompanha 1 shoyu sachê', TRUE, 18.90, 14),

-- itens da categoria Hossomakis
(180, 'Hossomaki Camarão empanado 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com camarão empanado', TRUE, 19.90, 15),
(181, 'Hossomaki Cebola Empanada 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com cebola empanada e cream cheese', TRUE, 17.90, 15),
(182, 'Hossomaki de Atum 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com atum', TRUE, 19.90, 15),
(183, 'Hossomaki de Salmão 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com salmão - acompanha 1 shoyu sachê', TRUE, 19.90, 15),
(184, 'Hossomaki de Shitake', 'Enrolados de arroz por dentro da alga e recheado por dentro com cogumelo - sem cream cheese', TRUE, 18.90, 15),
(185, 'Hossomaki Kani 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com kani kama - acompanha 1 shoyu sachê', TRUE, 18.90, 15),
(186, 'Hossomaki Skin 10und', 'Enrolados de arroz por dentro da alga e recheado por dentro com salmão skin', TRUE, 18.90, 15),

-- itens da categoria Niguiri (6und)
(190, 'Niguiri de peixe branco', 'Bolinho de arroz coberto, 06 peças.', TRUE, 18.90, 16),
(191, 'Niguiri de Polvo', 'Bolinho de arroz coberto com polvo - Acompanha shoyu', TRUE, 19.90, 16),
(192, 'Niguiri de salmão', 'Bolinho de arroz coberto com lamina de salmão - acompanha 1 shoyu sachê', TRUE, 19.90, 16),
(193, 'Niguiri Kani Kama', 'Bolinho de arroz coberto com lamina kani kama - acompanha 1 shoyu sachê', TRUE, 17.90, 16),
(194, 'Niguiri Skin', 'Bolinho de arroz coberto com lamina salmão skin - acompanha 1 shoyu sachê', TRUE, 17.90, 16),
(195, 'Niguri Atum', 'Bolinho de arroz coberto com lamina de atum - acompanha 1 shoyu sachê', TRUE, 19.90, 16),

-- itens da categoria Chinesa
(196, 'Bifum Familia', 'Macarrão de arroz, legumes, carne, frango e camarão - acompanha molho agridoce', TRUE, 55.00, 17),
(197, 'Bifum P', 'Macarrão de arroz, legumes, carne, frango e camarão - acompanha molho agridoce', TRUE, 29.00, 17),
(198, 'Carne Acebolada', 'Carne acebolada, acompanha arroz a grega e batata frita - porção total em torno de 1kg', TRUE, 45.00, 17),
(199, 'Frango Xadrez', 'Frango em cubos com pimentões e cebola, amendoim torrado, molho chinês. Acompanha arroz a grega e batata frita - porção total tem em torno de 1kg', TRUE, 45.00, 17),
(200, 'Yakisoba Especial G', 'Macarrão Chinês com legumes, carne, camarão e frango ao molho shoyu', TRUE, 55.00, 17),
(201, 'Yakisoba Especial P', 'Macarrão Chinês com legumes, carne, camarão e frango ao molho shoyu - Acompanha agridoce', TRUE, 29.00, 17),
(202, 'Yakisoba Simples G', 'Macarrão Chinês com legumes, carne e frango ao molho shoyu - Acompanha agridoce', TRUE, 51.00, 17),
(203, 'Yakisoba Simples P', 'Macarrão Chinês com legumes, carne e frango ao molho shoyu - Acompanha agridoce', TRUE, 25.00, 17),
(204, 'Yakisoba Vegano Familia', 'Macarrão Chinês com legumes ao molho shoyu - acompanha molho agridoce', TRUE, 40.00, 17),
(205, 'Yakisoba Vegano P', 'Macarrão Chinês com legumes ao molho shoyu - acompanha molho agridoce', TRUE, 22.00, 17),

-- itens da categoria Sashimi
(206, 'Sashimi Atum', '05 Laminas de atum - Acompanha 1 shoyu sachê', TRUE, 24.90, 18),
(207, 'Sashimi Kani Kama', '5 Laminas de kani kama - Acompanha 1 shoyu sachê', TRUE, 21.90, 18),
(208, 'Sashimi Misto', 'Sashimi Misto', TRUE, 24.90, 18),
(209, 'Sashimi Peixe Branco', '05 Laminas de peixe branco', TRUE, 21.90, 18),
(210, 'Sashimi Salmão', '05 Laminas de salmão - Acompanha 1 shoyu sachê', TRUE, 24.90, 18),

-- item da categoria Molhos
(211, 'Creme cheese 40g', 'Molho de creme cheese com 40g', TRUE, 3.50, 11);


-- item da categoria Promoção do dia
(212, 'Combo 50 peças Sortidas', 'Combo 50 peças Sortidas fritas e naturais (A cozinha que define) acompanha 2 teiyaki 3 shoyu sachê', TRUE, 49.90, 20);
(213, 'Ogro Ebi Skin 2KG', 'Manta de salmão maçaricada recheada com camarão, skin, cream cheese, arroz, cebolinho. Regado ao molho teriaky e gergelin. acompanha 3 teriaky e 5 shoyu.', TRUE, 99.90, 20),

-- Adicionar itens da categoria Festival Ogro 2023
(214, 'Spremo Ogro Ebi 2kg', 'Manta de salmão maçaricada recheado com camarão empanado, cream cheese, arroz, cebolinho regado ao molho teriyaki e gergelim. Acompanha 3 teriyakis e 5 shoyus.', TRUE, 104.90, 21),
(215, 'Supremo Ogro Salmão 2kg', 'Manta de salmão maçaricada recheada com pasta de salmão, base de hot sucesso, cebolinho regado ao molho teriaky e gergelin. 3 teriyakis e 5 shoyus.', TRUE, 109.90, 21);



-- Populando a tabela de promocao
INSERT INTO promocao (item_idItem, data_validade, preco, observacao)
VALUES
(1, 1, NOW(), NOW(), 8.00, 'Promoção Verao'),
(2, 2, NOW(), NOW(), 15.00, 'Promoção Item 2'),
(3, 3, NOW(), NOW(), 25.00, 'Promoção Item 3');
(4, 4, NOW(), NOW(), 35.00, 'Promoção Item 4'),
(5, 5, NOW(), NOW(), 45.00, 'Promoção Item 5'),
(6, 6, NOW(), NOW(), 55.00, 'Promoção Item 6'),
(7, 7, NOW(), NOW(), 65.00, 'Promoção Item 7'),
(8, 8, NOW(), NOW(), 75.00, 'Promoção Item 8'),
(9, 9, NOW(), NOW(), 85.00, 'Promoção Item 9'),
(10, 10, NOW(), NOW(), 95.00, 'Promoção Item 10'),
(11, 11, NOW(), NOW(), 105.00, 'Promoção Item 11'),
(12, 12, NOW(), NOW(), 115.00, 'Promoção Item 12'),
(13, 13, NOW(), NOW(), 125.00, 'Promoção Item 13');

-- Populando a tabela de pedido_itens
INSERT INTO pedido_itens (pedido_nrPedido, item_idItem, qtde_item)
VALUES
(1, 1, 3),
(1, 2, 1);
(2, 2, 2),
(2, 3, 1);
(2, 3, 1);
(2, 4, 2),
(3, 5, 2),

-- Populando a tabela de pedido_cliente
INSERT INTO pedido_cliente (cliente_idCliente, pedido_nrPedido)
VALUES
(1, 1);
(2, 2),
(3, 3);
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13);
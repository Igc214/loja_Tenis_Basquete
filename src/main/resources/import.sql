
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Nike','2352435','RJ');    -- id=1
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Adidas','2352435','SP');  -- id=2
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Puma','2352435','SP');    -- id=3
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Under Armour','2352435','SP');  -- id=4  
insert into pessoa (apelido, telefone, endereco) values ('361°', '123456789', 'SP'); -- id=5
INSERT INTO fornecedor (id,cnpj) VALUES (1,'36.226.675/0001-09');
INSERT INTO fornecedor (id,cnpj) VALUES (2,'42.274.696/0025-61');
INSERT INTO fornecedor (id,cnpj) VALUES (3,'05.406.034/0001-02');
INSERT INTO fornecedor (id,cnpj) VALUES (4,'51.686.779/0001-80');
INSERT INTO fornecedor (id,cnpj) VALUES (5,'20.022.861/0005-01');
                       
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('T','T','T');  -- id=3
INSERT INTO cliente (id,cpf,email,numeroDeCompra) VALUES (3,'T','T',1); -- id=3, não 2 



INSERT INTO fabricante (marca, cnpj,fornecedor_id) VALUES ('Nike Brasil Marketing e Licenciamento Esportivo Ltda', '36.226.675/0001-09', 1);
INSERT INTO fabricante (marca, cnpj,fornecedor_id) VALUES ('Adidas do Brasil Ltda', '42.274.696/0025-61', 2);
INSERT INTO fabricante (marca, cnpj,fornecedor_id) VALUES ('PUMA Sports Ltda', '05.406.034/0001-02', 3);
INSERT INTO fabricante (marca, cnpj,fornecedor_id) VALUES ('Companhia Industrial Brasileira de Calçados Vulcanizados S.A.', '51.686.779/0001-80', 4);
INSERT INTO fabricante (marca, cnpj,fornecedor_id) VALUES ('361° Sports', '20.022.861/0005-01', 5);

INSERT INTO usuario (login, nome, senha_hash, perfil, version) VALUES ('admin', 'Administrador', '$2a$10$uUhWhlzOiH3nQjMFjgai..Y8U4SOj31fRoXd6AoEgqZPgb2yMbHqS', 'ADMIN', 0);
INSERT INTO usuario (login, nome, senha_hash, perfil, version) VALUES ('teste', 'Usuário Teste', '$2a$10$Ku42m9rw6Gta8hdniq4Sk.FS8/VIC9633VJwEkGJCpjtIxLCkQgqW', 'CLIENTE', 0); 




 INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Nike Ja 3 "Channel 12"', 40, 'Azul e Amarelo (Colorido)', 'O Nike Ja 3 "Channel 12" traz blocos de cores descombinados para o terceiro tênis exclusivo de Ja Morant. O tênis direito apresenta cabedal laranja com mesh e peças em TPU. O esquerdo combina azul-ardósia com sobreposições texturizadas em azul-marinho.',
 'https://youtu.be/ERfpLOnMH6U?si=O6PaG7R8GC9tlHvj', 899.99, 10, 1, false, 1, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Lebron XX', 41, 'Bege', 'Quase duas décadas de carreira, LeBron James recusa menos que a grandeza. A versão de seu tênis exclusivo oferece sofisticação, com detalhes em couro e camadas de textura. O design de corte baixo foi criado para o estilo de jogo frenético de hoje.',
 'https://youtu.be/XbmE1fBMEuY?si=21jF3aDcB6GUdWwi', 1097.24, 10, 1, false, 3, 2, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('KD 17', 42, 'Roxo', 'Esforce-se para ser ótimo com o KD17, um tênis para ratos de academia e quem insiste em correr de volta. Uma unidade Air Zoom no antepé melhora seu primeiro passo, combinada ao amortecimento Nike Air para alimentar sprints e paradas defensivas na quadra.',
 'https://youtu.be/gyN0eiHFTy0?si=_I8NwL8bjlUhk5kw', 899.99, 10, 1, false, 4, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Giannis Freak 7', 43, 'Azul', 'Obcecado. Determinado. Dedicado. Para os madrugadores, o Giannis Freak 7 foi feito para o trabalho que leva seu jogo ao próximo nível. Com responsividade leve, tração incrível e design fluido, libere o jogador de elite que existe em cada um de nós.', 
'https://youtu.be/hY2jAVdAPyo?si=FfyxFB7bx_Y8N0HX', 967.09, 10, 1, false, 5, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Adidas AE 2 Anthony Edwards', 44, 'Laranja e Preto', 'Desenvolvido para suportar a ação de alta velocidade de Edwards, o AE 2 combina o retorno de energia do BOOST com o amortecimento ultraleve do Lightstrike. Os logotipos exclusivos de Anthony Edwards completam o visual único deste tênis de performance.', 'https://www.youtube.com/watch?v=ERrgQZ9HzGU', 899.99, 10, 1, false, 1, 1, 2, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Tênis de Basquete Hali 1', 45, 'Azul', 'Este é o Hali 1, modelo de Tyrese Haliburton por Salehe Bembury. Mistura estética futurista e performance. O cabedal dinâmico repousa sobre uma entressola de NITROFOAM para potência. Equipado com TPU Midfoot Shank e solado PUMAGRIP de alta tração.', 'https://www.youtube.com/watch?v=Oeer7UKdUug', 1199.99, 10, 1, false, 1, 1, 3, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Nike Book 1', 40, 'Preto e Laranja', 'O Nike Book 1 é a primeira geração do tênis de Devin Booker. Traz uma visão moderna do basquete clássico ajustada para as quadras. Inspirado no skate e carros clássicos, apresenta unidade Air Zoom no antepé e entressola Cushlon 3.0 para amortecimento.', 'https://youtu.be/A-mx82bkXjA?si=zNKnr2X622jMv_ma', 899.99, 10, 1, false, 2, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('361 Joker 2', 41, 'Branco e Preto', 'O 361 Joker 2 é o segundo tênis exclusivo de Nikola Jokic, feito para controle e força. A entressola combina CQT Qu!kTech e Qu!k Light para amortecimento responsivo. Painéis de torção garantem rigidez e o solado Diamond Grip oferece tração total.', 'https://youtu.be/oLT74n8pquY?si=goxfEbsc5PWqG66r', 799.99, 10, 1, false, 5, 3, 5, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('361 AG5 Aaron Gordon', 42, 'Preto e Branco', 'O AG 5 é o quinto modelo de Aaron Gordon, focado em explosão e estabilidade. A entressola ENRG-X com amortecimento CQTEXTREM3 maximiza o retorno de energia nas aterrissagens. A placa de carbono SOAR PLATE garante excelente propulsão nos movimentos.', 'https://youtu.be/WHubj51cmEE?si=2exSUJ5r5BjWXV2x', 899.99, 10, 1, false, 4, 1, 5, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Jordan Zion 4', 43, 'Preto e Dourado', 'Criado para o jogo explosivo, o Zion 4 traz Air Zoom de comprimento total e entressola drop-in para amortecimento responsivo. Com superfície ampla e estrutura cupsole, oferece estabilidade máxima. A espuma Cushlon 3.0 suporta movimentos verticais.', 'https://youtu.be/3CndV6Hd52I?si=lNicmPPeo4cmXBNT', 799.99, 10, 1, false, 5, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Adidas D.O.N. Issue 6', 44, 'Vermelho e Preto', 'O D.O.N. Issue 6 suporta os movimentos dinâmicos de Donovan Mitchell. O amortecimento Lightstrike Pro oferece retorno de energia e responsividade para absorver impactos sem peso. A sola de borracha espinha de peixe permite cortes rápidos e pivots.', 'hhttps://youtu.be/_eemPkWuyOk?si=UNc6DbCbuO1g7V6S', 899.99, 10, 1, false, 2, 1, 2, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Puma MB.05', 45, 'Rosa e Multicolorido', 'O MB.05 é o quinto modelo de LaMelo Ball. Apresenta entressola NITROFOAM com infusão de nitrogênio para máxima responsividade em um pacote leve. Tiras moldadas em TPU no antepé dão suporte lateral e o solado PUMAGRIP garante tração em alta abrasão.', 'não tem url', 1299.99, 10, 1, false, 1, 1, 3, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Jordan Luka 5', 40, 'Verde e Roxo', 'O Jordan Luka 5 traz melhorias fundamentais: o primeiro Zoom Strobel de comprimento total para propulsão, suporte ISOband integrado para estabilidade em múltiplos ângulos e entressola ultra-macia Cushlon 3.0. Borracha HART garante paradas súbitas.', 'https://www.youtube.com/watch?v=j9osG5oJ04M', 999.99, 10, 1, false, 1, 1, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Under Armour Curry 13', 41, 'Azul e Amarelo', 'O Curry 13 integra inovações históricas em uma silhueta de 351g com a armação SPLASH cage. O amortecimento supercrítico oferece ótimo retorno de energia com peso reduzido, enquanto a plataforma UA Flow garante tração de elite em cortes e paradas.', 'https://www.youtube.com/watch?v=GZRjLrmEGhE', 1299.99, 10, 1, false, 1, 1, 4, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Nike GT Cut Academy', 44, 'Preto e Verde', 'O Nike G.T. Cut Academy ajuda a criar espaço para step-backs e cortes. A unidade Zoom Air no antepé oferece velocidade e a entressola com duas espumas garante alto conforto. O padrão espinha de peixe entrega tração constante do calcanhar aos dedos.', 'https://www.youtube.com/watch?v=mAmw-ZsrxOk', 899.99, 10, 0, false, 2, 3, 1, 0);

INSERT INTO tenis_performance (nome, numerodope, cor, descricao, url, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tiposolado, fabricante_id, version)
VALUES ('Adidas Dame Certified 3', 42, 'Preto e Amarelo', 'Domine as quadras com o Adidas Dame Certified 3. Inspirado na confiança de Damian Lillard, sua estrutura leve traz agilidade enquanto o amortecimento BOUNCE absorve impactos. Design exclusivo para alto desempenho aliado a um visual marcante.', 'https://www.youtube.com/watch?v=ERrgQZ9HzGU', 699.99, 10, 0, false, 2, 1, 2, 0);
 



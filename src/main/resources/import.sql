
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Nike','2352435','RJ');
INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('Adidas','2352435','SP');
INSERT INTO fornecedor (id,cnpj) VALUES (1,'36.226.675/0001-09');
INSERT INTO fornecedor (id,cnpj) VALUES (2,'42.274.696/0025-61');   



INSERT INTO fabricante (marca, cnpj, fornecedor_id) VALUES ('Nike Brasil Marketing e Licenciamento Esportivo Ltda', '36.226.675/0001-09', 1);
INSERT INTO fabricante (marca, cnpj, fornecedor_id) VALUES ('Adidas do Brasil Ltda', '42.274.696/0025-61', 2);

INSERT INTO pessoa (apelido,telefone, endereco) VALUES ('T','T','T');
INSERT INTO cliente (id,cpf,email,numeroDeCompra) VALUES (2,'T','T',1);

INSERT INTO usuario (id, login, senha_hash, perfil) VALUES (1, 'admin', '$2a$10$gr4f8hDs7tHXChfGOzl.HOsVthUb1db0Lj26EtFK4kBZD.aCJDav2', 'ADMIN');
INSERT INTO usuario (id, login, senha_hash, perfil) VALUES (2, 'teste', '$2a$10$3RctlnHsNWgd5OoX5TTfxu7NBcW6wygfal3nF.23Ny.67taMBXANC', 'TESTE');

-- Tenis padrão para visualização no Swagger
-- codigo_posicao: 2 = SG (Ala-Armador)
-- codigo_tipo_solado: 2 = OUTDOOR
-- IMPORTANTE: Use IDs numéricos, não strings! Os converters só funcionam na app, não no SQL puro.
INSERT INTO tenis_performance (id, nome, numero_do_pe, cor, descricao, preco, estoque, edicao_limitada, autografado, codigo_posicao, codigo_tipo_solado, fabricante_id) 
VALUES (1, 'Nike Air Jordan 1 High', 42, 'Vermelho e Preto', 'Tênis premium para basquete de alta performance', 850.00, 50, 0, false, 2, 2, 1);



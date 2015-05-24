-- # SENHA COMUM E PREFERENCIAL
-- INSERT INTO senha(id, data_emissao, senha, tipo) VALUES (1, (SELECT current_date), '1', '0');
-- INSERT INTO senha(id, data_emissao, senha, tipo) VALUES (2, (SELECT current_date), '2', '1');

-- # USUÁRIOS
-- INSERT INTO usuario(id, login, senha) VALUES (1, 'admin', 'admin');

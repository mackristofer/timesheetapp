
-- Inserindo CATEGORIAS
INSERT INTO TB_CATEGORIA (DESCRICAO, VALOR_HORA) VALUES ('JUNIOR', 120.00);
INSERT INTO TB_CATEGORIA (DESCRICAO, VALOR_HORA) VALUES ('PLENO', 220.00);
INSERT INTO TB_CATEGORIA (DESCRICAO, VALOR_HORA) VALUES ('SENIOR', 450.00);
INSERT INTO TB_CATEGORIA (DESCRICAO, VALOR_HORA) VALUES ('SOCIO', 800.00);

-- Inserindo ADVOGADOS
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('MACK', '00000000001', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '2');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('DANNY', '00000000002', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '1');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('DEBORAH', '00000000003', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '4');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('LAURA', '00000000004', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '3');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('SAMARA', '00000000005', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '1');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('JUNIM', '00000000006', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '2');
INSERT INTO TB_ADVOGADO (NOME, CPF, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, TELEFONE, CATEGORIA_ID) VALUES ('LEILA', '00000000007', 'RUA DESEMBARGADOR CONTINENTINO, 120', 'CASA', 'CAICARA', 'BH', 'MG','30720170', '999999999', '3');

-- Inserindo USUARIOS
INSERT INTO TB_USUARIO (USUARIO, PASSWORD, ADVOGADO_ID) VALUES ('macks', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 1);
INSERT INTO TB_USUARIO (USUARIO, PASSWORD, ADVOGADO_ID) VALUES ('deborah', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 3);
INSERT INTO TB_USUARIO (USUARIO, PASSWORD, ADVOGADO_ID) VALUES ('laura', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 4);


-- Inserindo CLIENTES
INSERT INTO TB_CLIENTE (RAZAO, CNPJ_CPF, CONTATO, TELEFONE, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, OBS) VALUES ('M3TECH', '0000000000000','MACK','999999999','RUA DE CASA, 150',' ','CAICARA','BH','MG','35050100','CLIENTE BOM');
INSERT INTO TB_CLIENTE (RAZAO, CNPJ_CPF, CONTATO, TELEFONE, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, OBS) VALUES ('M3INFO', '0000000000000','DEBORAH','999999999','RUA DE CASA, 150',' ','CAICARA','BH','MG','35050100','CLIENTE BOM');
INSERT INTO TB_CLIENTE (RAZAO, CNPJ_CPF, CONTATO, TELEFONE, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, OBS) VALUES ('SOFTHOUSE', '0000000000000','LAURA','999999999','RUA DE CASA, 150',' ','CAICARA','BH','MG','35050100','CLIENTE BOM');
INSERT INTO TB_CLIENTE (RAZAO, CNPJ_CPF, CONTATO, TELEFONE, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, OBS) VALUES ('CAOS', '0000000000000','JUNIM','999999999','RUA DE CASA, 150',' ','CAICARA','BH','MG','35050100','CLIENTE BOM'); 
INSERT INTO TB_CLIENTE (RAZAO, CNPJ_CPF, CONTATO, TELEFONE, ENDERECO, COMPLEMENTO, BAIRRO, CIDADE, UF, CEP, OBS) VALUES ('EMPRESA TESTE', '0000000000000','DEBORAH','999999999','RUA DE CASA, 150',' ','CAICARA','BH','MG','35050100','CLIENTE BOM');



-- Inserindo CASO
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('CONTRATO POR HORA DO ADVOGADO', 0, 0, null, 0, 1);
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('CONTRATO POR HORA DO CASO', 0, 0, null, 170, 1);
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('CONTRATO POR HORA FECHADA', 0, 1, null, 3500.00, 1);
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('CONTRATO COM DESCONTO HORA FECHADA', 0, 1, 10, 3000, 1);
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('EXEMPLO 5', 0, 0, null, 150, 3);
INSERT INTO TB_CASO (DESCRICAO, STATUS_COBRANCA, TIPO_CONTRATO, DESCONTO, VALOR, CLIENTE_ID) VALUES ('EXEMPLO 6', 0, 0, null, 100, 2);



-- Inserindo TIMESHEET
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-10', '2:00', 'Lorenipsonlorenipsonloreipson', 1, 1, 1);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-10-10', '1:00', 'Lorenipsonlorenipsonloreipson', 1, 1, 1);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-11-12', '1:40', 'Lorenipsonlorenipsonloreipson', 2, 1, 2);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-20', '0:30', 'Lorenipsonlorenipsonloreipson', 2, 0, 1);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-10-21', '5:30', 'Lorenipsonlorenipsonloreipson', 3, 0, 3);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-13', '2:10', 'Lorenipsonlorenipsonloreipson', 2, 1, 4);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-11-09', '0:40', 'Lorenipsonlorenipsonloreipson', 2, 0, 2);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-11-10', '1:00', 'Lorenipsonlorenipsonloreipson', 2, 0, 6);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-11-15', '2:40', 'Lorenipsonlorenipsonloreipson', 2, 0, 6);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-10-16', '3:10', 'Lorenipsonlorenipsonloreipson', 3, 0, 5);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-18', '1:10', 'Lorenipsonlorenipsonloreipson', 4, 1, 4);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-19', '0:40', 'Lorenipsonlorenipsonloreipson', 4, 1, 4);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-20', '1:00', 'Lorenipsonlorenipsonloreipson', 4, 1, 4);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-18', '1:10', 'Lorenipsonlorenipsonloreipson', 1, 1, 4);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-18', '1:10', 'Lorenipsonlorenipsonloreipson', 4, 1, 1);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-18', '1:10', 'Lorenipsonlorenipsonloreipson', 4, 1, 2);
INSERT INTO TB_TIMESHEET (DATA, TEMPO, DESCRICAO, ADVOGADO_ID, COBRANCA, CASO_ID) VALUES ('2020-09-18', '1:10', 'Lorenipsonlorenipsonloreipson', 4, 1, 3);



--Inserindo ROLES
INSERT INTO TB_ROLE (AUTHORITY) VALUES ('ROLE_OPERATOR');
INSERT INTO TB_ROLE (AUTHORITY) VALUES ('ROLE_ADMIN');

--Inserindo USUARIO_ROLES
INSERT INTO TB_USUARIO_ROLE (USUARIO_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO TB_USUARIO_ROLE (USUARIO_ID, ROLE_ID) VALUES (2, 1);
INSERT INTO TB_USUARIO_ROLE (USUARIO_ID, ROLE_ID) VALUES (2, 2);
INSERT INTO TB_USUARIO_ROLE (USUARIO_ID, ROLE_ID) VALUES (3, 1);

-- =========================================================
-- MODELAGEM SQL - PROJETO "FEIRA EM CASA"
-- =========================================================

-- (opcional)
CREATE DATABASE IF NOT EXISTS feira_em_casa;
USE feira_em_casa;

-- =========================================================
-- TABELA ASSINANTE
-- =========================================================
CREATE TABLE Assinante (
    id_assinante       BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome               VARCHAR(120) NOT NULL,
    celular            VARCHAR(20)  NOT NULL,
    email              VARCHAR(120) NOT NULL UNIQUE
);

-- =========================================================
-- TABELA PLANO
-- =========================================================
CREATE TABLE Plano (
    id_plano                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome                      VARCHAR(80)  NOT NULL,
    valor_base                DECIMAL(10,2) NOT NULL,
    qtd_frutas_permitidas     INT NOT NULL,
    qtd_legumes_permitidos    INT NOT NULL,
    qtd_verduras_permitidas   INT NOT NULL
);

-- =========================================================
-- TABELA PRODUTO
-- tipo_produto: FRUTA / LEGUME / VERDURA
-- =========================================================
CREATE TABLE Produto (
    id_produto    BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome          VARCHAR(100) NOT NULL,
    tipo_produto  ENUM('FRUTA','LEGUME','VERDURA') NOT NULL
);

-- =========================================================
-- TABELA CATALOGO
-- lista de produtos da semana
-- =========================================================
CREATE TABLE Catalogo (
    id_catalogo  BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_semana  DATE NOT NULL
);

-- Relacionamento N:N entre catálogo e produtos
CREATE TABLE Catalogo_Produto (
    id_catalogo  BIGINT NOT NULL,
    id_produto   BIGINT NOT NULL,
    PRIMARY KEY (id_catalogo, id_produto),
    FOREIGN KEY (id_catalogo) REFERENCES Catalogo(id_catalogo),
    FOREIGN KEY (id_produto)  REFERENCES Produto(id_produto)
);

-- =========================================================
-- TABELA CESTA
-- cesta semanal do assinante
-- =========================================================
CREATE TABLE Cesta (
    id_cesta     BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_semana  DATE NOT NULL
);

-- Itens da cesta
CREATE TABLE Item_Cesta (
    id_cesta     BIGINT NOT NULL,
    id_produto   BIGINT NOT NULL,
    quantidade   INT    NOT NULL,
    PRIMARY KEY (id_cesta, id_produto),
    FOREIGN KEY (id_cesta)   REFERENCES Cesta(id_cesta),
    FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)
);

-- =========================================================
-- TABELA ENDERECO
-- =========================================================
CREATE TABLE Endereco (
    id_endereco   BIGINT PRIMARY KEY AUTO_INCREMENT,
    logradouro    VARCHAR(150) NOT NULL,
    numero        VARCHAR(20)  NOT NULL,
    complemento   VARCHAR(60),
    bairro        VARCHAR(80)  NOT NULL,
    cidade        VARCHAR(80)  NOT NULL,
    cep           VARCHAR(15)  NOT NULL
);

-- =========================================================
-- TABELA CARTAO
-- =========================================================
CREATE TABLE Cartao (
    id_cartao        BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_assinante     BIGINT NOT NULL,
    nome_impresso    VARCHAR(120) NOT NULL,
    numero_mascarado VARCHAR(30) NOT NULL,
    bandeira         VARCHAR(20) NOT NULL,
    validade_mes     INT NOT NULL,
    validade_ano     INT NOT NULL,
    FOREIGN KEY (id_assinante) REFERENCES Assinante(id_assinante)
);

-- =========================================================
-- TABELA ASSINATURA
-- =========================================================
CREATE TABLE Assinatura (
    id_assinatura  BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_assinante   BIGINT NOT NULL,
    id_plano       BIGINT NOT NULL,
    id_cesta       BIGINT NOT NULL,
    id_endereco    BIGINT NOT NULL,
    protocolo      VARCHAR(40) NOT NULL UNIQUE,
    status         ENUM('ATIVA','CANCELADA','SUSPENSA') NOT NULL DEFAULT 'ATIVA',
    date_criacao   DATE NOT NULL,
    FOREIGN KEY (id_assinante) REFERENCES Assinante(id_assinante),
    FOREIGN KEY (id_plano)     REFERENCES Plano(id_plano),
    FOREIGN KEY (id_cesta)     REFERENCES Cesta(id_cesta),
    FOREIGN KEY (id_endereco)  REFERENCES Endereco(id_endereco)
);

-- =========================================================
-- TABELA PAGAMENTO
-- =========================================================
CREATE TABLE Pagamento (
    id_pagamento   BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_assinatura  BIGINT NOT NULL,
    id_cartao      BIGINT NOT NULL,
    valor          DECIMAL(10,2) NOT NULL,
    status         ENUM('PENDENTE','APROVADO','RECUSADO') NOT NULL,
    data_pagamento DATETIME,
    FOREIGN KEY (id_assinatura) REFERENCES Assinatura(id_assinatura),
    FOREIGN KEY (id_cartao)     REFERENCES Cartao(id_cartao)
);

-- =========================================================
-- TABELA ENTREGA
-- =========================================================
CREATE TABLE Entrega (
    id_entrega     BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_assinatura  BIGINT NOT NULL,
    data_prevista  DATE NOT NULL,
    janela_horario VARCHAR(40) NOT NULL,
    status         ENUM('PENDENTE','EM_ROTA','ENTREGUE','PROBLEMA') 
                    NOT NULL DEFAULT 'PENDENTE',
    FOREIGN KEY (id_assinatura) REFERENCES Assinatura(id_assinatura)
);

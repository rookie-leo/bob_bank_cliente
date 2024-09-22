CREATE DATABASE IF NOT EXISTS clientesdb;

USE clientesdb;

CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    neighborhood VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(10) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip VARCHAR(20) NOT NULL
);

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    sobre_nome VARCHAR(255) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE telefones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ddd VARCHAR(2),
    numero VARCHAR(9) UNIQUE,
    clientes_id BIGINT,
    FOREIGN KEY (clientes_id) REFERENCES clientes(id)
);

INSERT INTO address (neighborhood, street, number, city, state, zip)
VALUES ('Centro', 'Rua A', '123', 'São Paulo', 'SP', '01000-000');

INSERT INTO address (neighborhood, street, number, city, state, zip)
VALUES ('Jardim das Flores', 'Avenida B', '456', 'Rio de Janeiro', 'RJ', '20000-000');

INSERT INTO clientes (nome, sobre_nome, cpf, senha, address_id)
VALUES ('João', 'Silva', '12345678900', 'testeSenha123456', 1);

INSERT INTO clientes (nome, sobre_nome, cpf, senha, address_id)
VALUES ('Maria', 'Oliveira', '98765432100', 'testando123456Senha', 2);

INSERT INTO telefones (ddd, numero, clientes_id)
VALUES ('12', '3456789', 1);

INSERT INTO telefones (ddd, numero, clientes_id)
VALUES ('11', '7654321', 1);

INSERT INTO telefones (ddd, numero, clientes_id)
VALUES ('11', '2234455', 2);

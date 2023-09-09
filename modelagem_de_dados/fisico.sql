CREATE DATABASE agencia_de_viagens;
USE agencia_de_viagens;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    rg VARCHAR(20),
    cpf VARCHAR(14),  
    dataNascimento DATE,
    endereco VARCHAR(100),
    numero INT,
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    celular VARCHAR(11),  
    whatsapp BOOLEAN
);

CREATE TABLE destino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    dataPartida DATE,
    horaPartida TIME,
    origem VARCHAR(100),
    companhiaAereaPartida VARCHAR(100),
    numVooPartida VARCHAR(20),
    dataRetorno DATE,
    horaRetorno TIME,
    destino VARCHAR(100),
    companhiaAereaRetorno VARCHAR(100),
    numVooRetorno VARCHAR(20),
    qtdPessoas INT,
    qtdQuartos INT,
    categoriaQuartos VARCHAR(20),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE
);

CREATE TABLE contato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    celular VARCHAR(11),
    email VARCHAR(100),
    mensagem TEXT,
    preferencia_contato VARCHAR(50),
    melhor_periodo_contato VARCHAR(100),
    descontos_exclusivos BOOLEAN,
    receber_novidades_email BOOLEAN
);

DESCRIBE cliente;
DESCRIBE destino;
DESCRIBE contato;
-- Criar a base de dados se ela ainda não existir
CREATE DATABASE IF NOT EXISTS agencia_de_viagens;

-- Usar a base de dados agencia_de_viagens
USE agencia_de_viagens;

-- Tabela de cliente
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
    email VARCHAR(50),
    celular VARCHAR(15),  
    whatsapp BOOLEAN
);

-- Tabela de Destino
CREATE TABLE destino (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeDestino VARCHAR(100),
    descricaoDestino VARCHAR(255) 
);

-- Tabela de Passagem
CREATE TABLE passagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idDestino INT,
    ciaAereaDestino VARCHAR(25),
    vooDestino VARCHAR(20),
    dataDestino DATE,
    horaDestino TIME,
    origem VARCHAR(100),
    ciaAereaOrigem VARCHAR(25),
    vooOrigem VARCHAR(20),
    dataOrigem DATE,
    horaOrigem TIME,    
    precoPassagem DECIMAL(10, 2),
    FOREIGN KEY (idDestino) REFERENCES destino(id) 
);

-- Tabela de Hotel
CREATE TABLE hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeHotel VARCHAR(100),
    idDestino INT,
    tipoQuarto VARCHAR(50),
    qtdeQuarto VARCHAR(10),
    precoPorNoite DECIMAL(10, 2),
    FOREIGN KEY (idDestino) REFERENCES destino(id) 
);

-- Tabela de Compra
CREATE TABLE compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idDestino INT,
    dataCompra DATE,
    tipoCompra ENUM('Passagem', 'Hotel', 'Ambos'),
    qtdPassagens INT,
    qtdNoitesHotel INT,
    precoTotal DECIMAL(10, 2),
    FOREIGN KEY (idCliente) REFERENCES cliente(id), 
    FOREIGN KEY (idDestino) REFERENCES destino(id) ON DELETE CASCADE
);

-- Tabela de Pesquisa
CREATE TABLE pesquisa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    idDestino INT,
    dataPesquisa DATE,
    dataSimulacao DATE,
    qtdPassagens INT,
    qtdNoitesHotel INT,
    simularPassagem BOOLEAN,
    simularHotel BOOLEAN,
    clienteExistente BOOLEAN, -- Indica se a pessoa é um cliente existente
    FOREIGN KEY (idCliente) REFERENCES cliente(id),
    FOREIGN KEY (idDestino) REFERENCES destino(id)
);

-- Tabela de Mensagem
CREATE TABLE mensagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT,
    nomeRemetente VARCHAR(100),
    telefoneRemetente VARCHAR(15),
    emailRemetente VARCHAR(255),
    mensagem TEXT,
    preferenciaContato ENUM('Telefone', 'E-mail', 'WhatsApp'),
    melhorPeriodoContato ENUM('Manhã', 'Tarde', 'Noite'),
    receberDescontos BOOLEAN,
    receberNovidades BOOLEAN,
    FOREIGN KEY (idCliente) REFERENCES cliente(id)
);

DESCRIBE cliente;
DESCRIBE destino;
DESCRIBE passagem;
DESCRIBE hotel;
DESCRIBE compra;
DESCRIBE pesquisa;
DESCRIBE mensagem;

select * from cliente;
select * from destino;
select * from passagem;
select * from hotel;
select * from compra;
select * from pesquisa;
select * from mensagem;
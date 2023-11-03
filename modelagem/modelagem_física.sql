-- Criar a base de dados se ela ainda não existir
CREATE DATABASE IF NOT EXISTS viagens;

-- Usar a base de dados 
USE viagens;

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

-- Tabela de Pacote
CREATE TABLE pacote (
    id INT AUTO_INCREMENT PRIMARY KEY,
    destino VARCHAR(100),
    descricao VARCHAR(250),
    dataPartida DATE,
    dataRetorno DATE,
    nomeHotel VARCHAR(50),
    valor DECIMAL(10, 2)
);

CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT, 
    idPacote INT,
    FOREIGN KEY (idCliente) REFERENCES cliente(id) ,
    FOREIGN KEY (idPacote) REFERENCES pacote(id)    
);

-- Tabela de Mensagem
CREATE TABLE mensagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nomeRemetente VARCHAR(100),
    telefoneRemetente VARCHAR(15),
    emailRemetente VARCHAR(255),
    mensagemRemetente TEXT,
    preferenciaContato VARCHAR(255),
    melhorPeriodoContato VARCHAR(255),
    receberDescontos BOOLEAN
);

DESCRIBE cliente;
DESCRIBE pacote;
DESCRIBE reserva;
DESCRIBE mensagem;

select * from cliente;
select * from pacote;
select * from reserva;
select * from mensagem;
-- Criar a base de dados se ela ainda não existir
CREATE DATABASE IF NOT EXISTS agencia_de_viagens;

-- Usar a base de dados agencia_de_viagens
USE agencia_de_viagens;

-- Tabela de cliente
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100),
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
    nome_destino VARCHAR(100),
    descricao_destino VARCHAR(255) 
);

-- Tabela de Passagem
CREATE TABLE passagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_destino INT,
    cia_aerea_destino VARCHAR(25),
    voo_destino VARCHAR(20),
    data_destino DATE,
    hora_destino TIME,
    origem VARCHAR(100),
    cia_aerea_origem VARCHAR(25),
    voo_origem VARCHAR(20),
    data_origem DATE,
    hora_origem TIME,    
    preco_passagem DECIMAL(10, 2),
    FOREIGN KEY (id_destino) REFERENCES destino(id) 
);

-- Tabela de Hotel
CREATE TABLE hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_hotel VARCHAR(100),
    id_destino INT,
    tipo_quarto VARCHAR(50),
    qtde_quarto VARCHAR(10),
    preco_por_noite DECIMAL(10, 2),
    FOREIGN KEY (id_destino) REFERENCES destino(id) 
);

-- Tabela de Compra
CREATE TABLE compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_destino INT,
    data_compra DATE,
    tipo_compra ENUM('Passagem', 'Hotel', 'Ambos'),
    qtd_passagens INT,
    qtd_noites_hotel INT,
    preco_total DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id), 
    FOREIGN KEY (id_destino) REFERENCES destino(id) ON DELETE CASCADE
);

-- Tabela de Pesquisa
CREATE TABLE pesquisa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_destino INT,
    data_pesquisa DATE,
    data_simulacao DATE,
    qtd_passagens INT,
    qtd_noites_hotel INT,
    simular_passagem BOOLEAN,
    simular_hotel BOOLEAN,
    cliente_existente BOOLEAN, -- Indica se a pessoa é um cliente existente
    FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    FOREIGN KEY (id_destino) REFERENCES destino(id)
);

-- Tabela de Mensagem
CREATE TABLE mensagem (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    nome_remetente VARCHAR(100),
    telefone_remetente VARCHAR(15),
    email_remetente VARCHAR(255),
    mensagem TEXT,
    preferencia_contato ENUM('Telefone', 'E-mail', 'WhatsApp'),
    melhor_periodo_contato ENUM('Manhã', 'Tarde', 'Noite'),
    receber_descontos BOOLEAN,
    receber_novidades BOOLEAN,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

DESCRIBE cliente;
DESCRIBE destino;
DESCRIBE passagem;
DESCRIBE hotel;
DESCRIBE compra;
DESCRIBE pesquisa;
DESCRIBE mensagem;
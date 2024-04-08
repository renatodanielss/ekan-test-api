-- liquibase formatted sql

-- changeset renato:create_tables_beneficio_and_documento
CREATE TABLE beneficiario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    data_nascimento DATE,
    data_inclusao DATETIME,
    data_atualizacao DATETIME
);

CREATE TABLE documento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo_documento VARCHAR(30) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    beneficiario_id INT,
    data_inclusao DATETIME,
    data_atualizacao DATETIME,
    FOREIGN KEY (beneficiario_id) REFERENCES beneficiario(id)
);
INSERT INTO beneficiario (id, nome, telefone, data_nascimento, data_inclusao, data_atualizacao)
    VALUES (1, 'Renato', '11978547747', '2024-04-03', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO documento (id, tipo_documento, descricao, beneficiario_id, data_inclusao, data_atualizacao)
    VALUES (1, 'RG', '41534541', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- rollback DROP TABLE IF EXISTS documento;
-- rollback DROP TABLE IF EXISTS beneficiario;
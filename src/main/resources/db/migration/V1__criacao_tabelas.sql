-- =============================
-- TABELA: enderecos
-- =============================
CREATE TABLE IF NOT EXISTS enderecos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    estado VARCHAR(255) NOT NULL,
    cidade VARCHAR(50),
    complemento VARCHAR(255),
    rua VARCHAR(255),
    numero INT,
    PRIMARY KEY (id)
);

-- =============================
-- TABELA: espacos
-- =============================
CREATE TABLE IF NOT EXISTS espacos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    capacidade INT,
    ambiente VARCHAR(255) NOT NULL,
    endereco_id BIGINT NOT NULL,
    andar VARCHAR(255) NOT NULL,
    referencia VARCHAR(255) NOT NULL,
    disponivel BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id),
    CONSTRAINT fk_espaco_endereco
        FOREIGN KEY (endereco_id)
        REFERENCES enderecos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- =============================
-- TABELA: usuarios
-- =============================
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL DEFAULT 'USUARIO',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (id)
);

-- =============================
-- TABELA: reservas
-- =============================
CREATE TABLE IF NOT EXISTS reservas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    espaco_id BIGINT NOT NULL,
    inicio DATETIME NOT NULL,
    fim DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_reserva_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_reserva_espaco
        FOREIGN KEY (espaco_id)
        REFERENCES espacos(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

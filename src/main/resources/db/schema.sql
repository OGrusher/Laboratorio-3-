-- script a mano igual a lo que hace hibernate
-- la app mete estos datos sola si las tablas estan vacias
CREATE DATABASE IF NOT EXISTS aulas_ufg CHARACTER SET utf8mb4;
USE aulas_ufg;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    apellido   VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol        VARCHAR(30)  NOT NULL
);

CREATE TABLE IF NOT EXISTS plantas (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tipos_aula (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS estados_aula (
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS aulas (
    id_aula        BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre_aula    VARCHAR(100) NOT NULL,
    edificio       VARCHAR(100) NOT NULL,
    planta         INT          NOT NULL,
    capacidad      INT          NOT NULL,
    tipo_aula_id   BIGINT       NOT NULL,
    responsable    VARCHAR(100) NOT NULL,
    estado_aula_id BIGINT       NOT NULL,
    CONSTRAINT uk_aula_nombre_edificio UNIQUE (nombre_aula, edificio),
    CONSTRAINT fk_aula_tipo   FOREIGN KEY (tipo_aula_id)   REFERENCES tipos_aula (id),
    CONSTRAINT fk_aula_estado FOREIGN KEY (estado_aula_id) REFERENCES estados_aula (id)
);

INSERT IGNORE INTO plantas (numero) VALUES (1),(2),(3),(4),(5),(6),(7),(8);
INSERT IGNORE INTO tipos_aula (nombre) VALUES ('Normal'),('Centro de computo'),('Laboratorio'),('Especial');
INSERT IGNORE INTO estados_aula (nombre) VALUES ('Asignada'),('Asignada temporalmente'),('En mantenimiento'),('Disponible');
-- usuario de prueba el admin de abajo
INSERT IGNORE INTO usuarios (nombre, apellido, email, contrasena, rol)
VALUES ('Admin', 'UFG', 'admin@ufg.edu.sv', SHA2('admin123', 256), 'ADMIN');

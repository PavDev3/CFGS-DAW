CREATE DATABASE er1_vuelta;

CREATE TABLE etapas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero_etapa INT NOT NULL,
    fecha DATE NOT NULL,
    hora_salida TIME NOT NULL,
    hora_estimada_llegada TIME NOT NULL,
    kilometros INT NOT NULL,
    lugar_salida VARCHAR(60) NOT NULL,
    lugar_llegada VARCHAR(60) NOT NULL
);

CREATE TABLE puertos_montana (
    id INT PRIMARY KEY AUTO_INCREMENT,
    etapa_id INT NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    categoria VARCHAR(60) NOT NULL,
    FOREIGN KEY (etapa_id) REFERENCES etapas(id)
);

CREATE TABLE equipos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(60) NOT NULL,
    nacionalidad VARCHAR(60) NOT NULL,
    director VARCHAR(60) NOT NULL,
    patrocinador VARCHAR(60)
);

CREATE TABLE ciclistas (
    dorsal INT PRIMARY KEY,
    equipo_id INT NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    nacionalidad VARCHAR(60) NOT NULL,
    mejores_clasificaciones VARCHAR(60),
    FOREIGN KEY (equipo_id) REFERENCES equipos(id)
);

CREATE TABLE tiempos_etapas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ciclista_dorsal INT,
    etapa_id INT NOT NULL,
    tiempo TIME NOT NULL,
    FOREIGN KEY (ciclista_dorsal) REFERENCES ciclistas(dorsal),
    FOREIGN KEY (etapa_id) REFERENCES etapas(id)
);

CREATE TABLE entrevistas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    ciclista_dorsal INT NOT NULL,
    cadena VARCHAR(60) NOT NULL,
    numero_entrevistas INT NOT NULL,
    FOREIGN KEY (ciclista_dorsal) REFERENCES ciclistas(dorsal)
);
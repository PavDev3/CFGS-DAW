CREATE DATABASE IF NOT EXISTS EJERCICIO1;


CREATE TABLE persona (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    pais VARCHAR(50) NOT NULL
);


CREATE TABLE equipo (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);


CREATE TABLE piloto (
    id_persona INT PRIMARY KEY,
    dorsal INT NOT NULL,
    apodo VARCHAR(50),
    FOREIGN KEY (id_persona) REFERENCES persona(id) ON DELETE CASCADE
);


CREATE TABLE jefe_equipo (
    id_persona INT PRIMARY KEY,
    FOREIGN KEY (id_persona) REFERENCES persona(id) ON DELETE CASCADE
);


CREATE TABLE coche (
    id INT PRIMARY KEY,
    motor VARCHAR(100) NOT NULL,
    id_equipo INT,
    s_n VARCHAR(50),
    FOREIGN KEY (id_equipo) REFERENCES equipo(id) ON DELETE SET NULL
);


CREATE TABLE campeonato (
    annio INT PRIMARY KEY
);


CREATE TABLE carreras (
    id INT PRIMARY KEY,
    ciudad VARCHAR(100) NOT NULL,
    fecha DATE NOT NULL,
    n_carrera INT NOT NULL,
    annio INT NOT NULL,
    FOREIGN KEY (annio) REFERENCES campeonato(annio) ON DELETE CASCADE
);


CREATE TABLE miembro_equipo (
    id_persona INT,
    id_equipo INT,
    PRIMARY KEY (id_persona, id_equipo),
    FOREIGN KEY (id_persona) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (id_equipo) REFERENCES equipo(id) ON DELETE CASCADE
);


CREATE TABLE piloto_coche (
    id_piloto INT,
    id_coche INT,
    PRIMARY KEY (id_piloto, id_coche),
    FOREIGN KEY (id_piloto) REFERENCES piloto(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (id_coche) REFERENCES coche(id) ON DELETE CASCADE
);


CREATE TABLE jefe_equipo_equipo (
    id_jefe_equipo INT PRIMARY KEY,
    id_equipo INT NOT NULL UNIQUE,
    FOREIGN KEY (id_jefe_equipo) REFERENCES jefe_equipo(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (id_equipo) REFERENCES equipo(id) ON DELETE CASCADE
);


CREATE TABLE resultado_carrera (
    id_piloto INT,
    id_carrera INT,
    id_dorsal INT NOT NULL,
    hora TIME,
    posicion INT,
    PRIMARY KEY (id_piloto, id_carrera),
    FOREIGN KEY (id_piloto) REFERENCES piloto(id_persona) ON DELETE CASCADE,
    FOREIGN KEY (id_carrera) REFERENCES carreras(id) ON DELETE CASCADE
);



CREATE DATABASE IF NOT EXISTS EJ1;

USE EJ1;

-- 1. Borrado de tablas (en orden inverso para respetar las FK)
DROP TABLE IF EXISTS M;
DROP TABLE IF EXISTS I;
DROP TABLE IF EXISTS E;
DROP TABLE IF EXISTS D;

-- 2. Creación de tablas PADRE (Las que son referenciadas)

-- Tabla D: Ahora la PK es solo PROFESOR para poder ser referenciada por I
CREATE TABLE D (
    PROFESOR VARCHAR(50),
    DEPARTAMENTO VARCHAR(50),
    PRIMARY KEY (PROFESOR)
);

-- Tabla E: Ahora la PK es solo MODULO (Un módulo solo pertenece a un ciclo)
CREATE TABLE E (
    CICLO VARCHAR(50),
    MODULO VARCHAR(50),
    PRIMARY KEY (MODULO)
);

-- 3. Creación de tablas HIJA (Las que tienen Foreign Keys)

-- Tabla I: Relaciona Profesores y Módulos con integridad referencial
CREATE TABLE I (
    PROFESOR VARCHAR(50),
    MODULO VARCHAR(50),
    PRIMARY KEY (PROFESOR, MODULO),
    -- Restricción: El profesor debe existir en la tabla D
    CONSTRAINT FK_I_PROFESOR FOREIGN KEY (PROFESOR) 
        REFERENCES D(PROFESOR)
        ON DELETE CASCADE ON UPDATE CASCADE,
    -- Restricción: El módulo debe existir en la tabla E
    CONSTRAINT FK_I_MODULO FOREIGN KEY (MODULO) 
        REFERENCES E(MODULO)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tabla M: Relaciona Alumnos y Módulos con integridad referencial
CREATE TABLE M (
    ALUMNO VARCHAR(50),
    MODULO VARCHAR(50),
    NOTA DECIMAL(4, 2),
    PRIMARY KEY (ALUMNO, MODULO),
    -- Restricción: El módulo debe existir en la tabla E
    CONSTRAINT FK_M_MODULO FOREIGN KEY (MODULO) 
        REFERENCES E(MODULO)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO D (PROFESOR, DEPARTAMENTO) VALUES 
('Alan Turing', 'Informática'),
('Ada Lovelace', 'Informática'),
('Isaac Newton', 'Física'),       -- Este profesor no tendrá asignación (para probar tu consulta A)
('Grace Hopper', 'Redes');

INSERT INTO E (CICLO, MODULO) VALUES 
('DAW', 'Programación'),
('DAW', 'Bases de Datos'),
('DAM', 'Interfaces'),
('DAM', 'Móviles'),
('ASIR', 'Redes');

-- Alan Turing imparte 2 módulos (Para probar tu consulta B)
INSERT INTO I (PROFESOR, MODULO) VALUES ('Alan Turing', 'Programación');
INSERT INTO I (PROFESOR, MODULO) VALUES ('Alan Turing', 'Bases de Datos');

-- Ada y Grace imparten 1 módulo
INSERT INTO I (PROFESOR, MODULO) VALUES ('Ada Lovelace', 'Interfaces');
INSERT INTO I (PROFESOR, MODULO) VALUES ('Grace Hopper', 'Redes');

-- NOTA: No insertamos nada para 'Isaac Newton' intencionadamente.

-- Juan: Aprueba todo (Para probar consulta D)
INSERT INTO M (ALUMNO, MODULO, NOTA) VALUES 
('Juan', 'Programación', 8.50),
('Juan', 'Bases de Datos', 9.00);

-- Maria: Aprueba una y suspende otra (Aprueba el 50%)
INSERT INTO M (ALUMNO, MODULO, NOTA) VALUES 
('Maria', 'Programación', 4.00),
('Maria', 'Bases de Datos', 7.00);

-- Luis: Aprueba una y suspende otra con nota muy baja
INSERT INTO M (ALUMNO, MODULO, NOTA) VALUES 
('Luis', 'Programación', 3.00),
('Luis', 'Bases de Datos', 8.00);

-- Ana: Suspende todo (Para verificar que no salga en las medias de aprobados)
INSERT INTO M (ALUMNO, MODULO, NOTA) VALUES 
('Ana', 'Programación', 2.00),
('Ana', 'Bases de Datos', 4.50);
CREATE DATABASE PUBS;


CREATE TABLE Localidad (
    cod_localidad INT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);


CREATE TABLE Pub (
    cod_pub INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    licencia_fiscal VARCHAR(255) NOT NULL UNIQUE,
    domicilio VARCHAR(255),
    fecha_apertura DATE NOT NULL,
    horario enum ('HOR1', 'HOR2', 'HOR3') NOT NULL,
    cod_localidad INT NOT NULL,
    FOREIGN KEY (cod_localidad) REFERENCES Localidad(cod_localidad)
);

CREATE TABLE Titular (
    dni_titular VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    domicilio VARCHAR(120),
    cod_pub INT NOT NULL,
    FOREIGN KEY (cod_pub) REFERENCES Pub(cod_pub)
);

CREATE TABLE Empleado (
    dni_empleado VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    domicilio VARCHAR(120)
);

CREATE TABLE Existencias (
    cod_articulo INT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    cod_pub INT NOT NULL,
    FOREIGN KEY (cod_pub) REFERENCES Pub(cod_pub)
);

CREATE TABLE Pub_Empleado (
    cod_pub INT NOT NULL,
    dni_empleado VARCHAR(9) NOT NULL,
    funcion enum ('CAMARERO', 'SEGURIDAD', 'LIMPIEZA') NOT NULL,
    PRIMARY KEY (cod_pub, dni_empleado, funcion),
    FOREIGN KEY (dni_empleado) REFERENCES Empleado(dni_empleado)
);


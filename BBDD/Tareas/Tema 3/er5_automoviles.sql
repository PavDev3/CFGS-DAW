create database automoviles;

use automoviles;

create table coche (
    matricula VARCHAR(255) NOT NULL UNIQUE PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    precio_venta DECIMAL(10, 2) NOT NULL
);

create table cliente (
    codigo INT NOT NULL UNIQUE PRIMARY KEY,
    nif VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL
);

create table revision (
    codigo INT NOT NULL UNIQUE PRIMARY KEY,
    coche_matricula VARCHAR(255) NOT NULL,
    cambio_filtro BOOLEAN NOT NULL,
    cambio_aceite BOOLEAN NOT NULL,
    cambio_frenos BOOLEAN NOT NULL,
    otros TEXT NOT NULL,
    FOREIGN KEY (coche_matricula) REFERENCES coche(matricula)
);

create table compra (
    cliente_codigo INT NOT NULL ,
    coche_matricula VARCHAR(255) NOT NULL ,
    FOREIGN KEY (cliente_codigo) REFERENCES cliente(codigo),
    FOREIGN KEY (coche_matricula) REFERENCES coche(matricula),
    PRIMARY KEY (cliente_codigo, coche_matricula)
);

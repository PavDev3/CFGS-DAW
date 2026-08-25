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

-- Coches
insert into coche (matricula, marca, modelo, color, precio_venta) values ('1234ABC', 'Seat', 'Ibiza', 'Blanco', 18500.00);
insert into coche (matricula, marca, modelo, color, precio_venta) values ('5678DEF', 'Volkswagen', 'Golf', 'Negro', 24500.00);
insert into coche (matricula, marca, modelo, color, precio_venta) values ('9012GHI', 'Ford', 'Focus', 'Rojo', 19900.00);
insert into coche (matricula, marca, modelo, color, precio_venta) values ('3456JKL', 'Renault', 'Clio', 'Azul', 16800.00);
insert into coche (matricula, marca, modelo, color, precio_venta) values ('7890MNO', 'Peugeot', '208', 'Gris', 17200.00);

-- Clientes
insert into cliente (codigo, nif, nombre, direccion, ciudad, telefono) values (1001, '12345678A', 'Pedro Sánchez García', 'Calle Mayor 12, 1ºA', 'Madrid', '912345678');
insert into cliente (codigo, nif, nombre, direccion, ciudad, telefono) values (1002, '23456789B', 'Carmen López Martín', 'Avenida de la Paz 45', 'Barcelona', '923456789');
insert into cliente (codigo, nif, nombre, direccion, ciudad, telefono) values (1003, '34567890C', 'Miguel Torres Ruiz', 'Plaza España 8, 3ºB', 'Valencia', '934567890');
insert into cliente (codigo, nif, nombre, direccion, ciudad, telefono) values (1004, '45678901D', 'Isabel Moreno Díaz', 'Calle San Juan 23', 'Sevilla', '945678901');
insert into cliente (codigo, nif, nombre, direccion, ciudad, telefono) values (1005, '56789012E', 'Roberto Jiménez Pérez', 'Gran Vía 67', 'Bilbao', '956789012');

-- Revisiones
insert into revision (codigo, coche_matricula, cambio_filtro, cambio_aceite, cambio_frenos, otros) values (2001, '1234ABC', TRUE, TRUE, FALSE, 'Revisión de neumáticos y alineación');
insert into revision (codigo, coche_matricula, cambio_filtro, cambio_aceite, cambio_frenos, otros) values (2002, '5678DEF', TRUE, TRUE, TRUE, 'Cambio de pastillas de freno delanteras');
insert into revision (codigo, coche_matricula, cambio_filtro, cambio_aceite, cambio_frenos, otros) values (2003, '9012GHI', FALSE, TRUE, FALSE, 'Revisión general y limpieza de filtro de aire');
insert into revision (codigo, coche_matricula, cambio_filtro, cambio_aceite, cambio_frenos, otros) values (2004, '3456JKL', TRUE, TRUE, TRUE, 'Cambio de discos y pastillas traseros');
insert into revision (codigo, coche_matricula, cambio_filtro, cambio_aceite, cambio_frenos, otros) values (2005, '7890MNO', TRUE, TRUE, FALSE, 'Revisión de sistema de escape');

-- Compras
insert into compra (cliente_codigo, coche_matricula) values (1001, '1234ABC');
insert into compra (cliente_codigo, coche_matricula) values (1002, '5678DEF');
insert into compra (cliente_codigo, coche_matricula) values (1003, '9012GHI');
insert into compra (cliente_codigo, coche_matricula) values (1004, '3456JKL');
insert into compra (cliente_codigo, coche_matricula) values (1005, '7890MNO');

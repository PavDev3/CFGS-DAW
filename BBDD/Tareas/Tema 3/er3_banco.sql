Create database banco;


create table sucursal (
    id_sucursal INT PRIMARY KEY AUTO_INCREMENT,
    ciudad VARCHAR(255) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

create table cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    ciudad VARCHAR(255) NOT NULL
);

create table cuenta (
    id_cuenta INT PRIMARY KEY AUTO_INCREMENT,
    saldo DECIMAL(10, 2) NOT NULL,
    id_sucursal INT NOT NULL,
    FOREIGN KEY (id_sucursal) REFERENCES sucursal(id_sucursal)
);  

create table transaccion (
    id_transaccion INT PRIMARY KEY AUTO_INCREMENT,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo ENUM('deposito', 'retiro') NOT NULL,
    cantidad DECIMAL(10, 2) NOT NULL,
    id_cuenta INT NOT NULL,
    FOREIGN KEY (id_cuenta) REFERENCES cuenta(id_cuenta)
);

create table cliente_cuenta (
    id_cliente INT NOT NULL,
    id_cuenta INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_cuenta) REFERENCES cuenta(id_cuenta)
);

-- Sucursales
insert into sucursal (ciudad, activo) values ('Madrid', TRUE);
insert into sucursal (ciudad, activo) values ('Barcelona', TRUE);
insert into sucursal (ciudad, activo) values ('Valencia', TRUE);
insert into sucursal (ciudad, activo) values ('Sevilla', TRUE);
insert into sucursal (ciudad, activo) values ('Bilbao', FALSE);

-- Clientes
insert into cliente (dni, nombre, apellido, direccion, ciudad) values ('12345678A', 'María', 'García López', 'Calle Gran Vía 45, 3ºB', 'Madrid');
insert into cliente (dni, nombre, apellido, direccion, ciudad) values ('23456789B', 'Carlos', 'Rodríguez Martín', 'Avenida Diagonal 123', 'Barcelona');
insert into cliente (dni, nombre, apellido, direccion, ciudad) values ('34567890C', 'Ana', 'Martínez Sánchez', 'Plaza del Ayuntamiento 8', 'Valencia');
insert into cliente (dni, nombre, apellido, direccion, ciudad) values ('45678901D', 'Javier', 'López Fernández', 'Calle Sierpes 23', 'Sevilla');
insert into cliente (dni, nombre, apellido, direccion, ciudad) values ('56789012E', 'Laura', 'Fernández Torres', 'Gran Vía 67, 2ºA', 'Bilbao');

-- Cuentas
insert into cuenta (saldo, id_sucursal) values (2500.50, 1);
insert into cuenta (saldo, id_sucursal) values (1800.75, 2);
insert into cuenta (saldo, id_sucursal) values (3200.00, 1);
insert into cuenta (saldo, id_sucursal) values (950.25, 3);
insert into cuenta (saldo, id_sucursal) values (4500.80, 2);

-- Transacciones
insert into transaccion (fecha, tipo, cantidad, id_cuenta) values ('2024-01-15 10:30:00', 'deposito', 500.00, 1);
insert into transaccion (fecha, tipo, cantidad, id_cuenta) values ('2024-01-16 14:20:00', 'retiro', 200.00, 2);
insert into transaccion (fecha, tipo, cantidad, id_cuenta) values ('2024-01-17 09:15:00', 'deposito', 1000.00, 3);
insert into transaccion (fecha, tipo, cantidad, id_cuenta) values ('2024-01-18 16:45:00', 'retiro', 150.50, 4);
insert into transaccion (fecha, tipo, cantidad, id_cuenta) values ('2024-01-19 11:00:00', 'deposito', 750.25, 5);

-- Cliente-Cuenta (relación muchos a muchos)
insert into cliente_cuenta (id_cliente, id_cuenta) values (1, 1);
insert into cliente_cuenta (id_cliente, id_cuenta) values (2, 2);
insert into cliente_cuenta (id_cliente, id_cuenta) values (3, 3);
insert into cliente_cuenta (id_cliente, id_cuenta) values (4, 4);
insert into cliente_cuenta (id_cliente, id_cuenta) values (5, 5);


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


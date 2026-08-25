DROP DATABASE IF EXISTS supermercado_daw;
CREATE DATABASE supermercado_daw;
USE supermercado_daw;

CREATE TABLE empleado(
    nif VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    domicilio VARCHAR(50)
);

CREATE TABLE supermercado(
    idSupermercado INT PRIMARY KEY,
    superficie FLOAT,
    domicilio VARCHAR(50)
);

CREATE TABLE cliente (
    nif VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);


CREATE TABLE pedido(
    numeroPedido INT,
    idSupermercado INT,
    fecha DATETIME NOT NULL,
    total FLOAT NOT NULL,
    nifCliente VARCHAR(9) NOT NULL,
    nifEmpleado VARCHAR(9) NOT NULL,

    PRIMARY KEY (idSupermercado, numeroPedido),
    CONSTRAINT FK_pedido_cliente FOREIGN KEY (nifCliente) REFERENCES cliente(nif),
    CONSTRAINT FK_pedido_empleado FOREIGN KEY (nifEmpleado) REFERENCES Empleado(nif) 
);

-- ################################################
-- DATOS DE PRUEBA
-- ################################################

INSERT INTO empleado (nif, nombre, domicilio) VALUES
('11111111A', 'Laura Gómez', 'C/ Sol 15'),  -- Empleado con pedidos
('22222222B', 'David Pérez', 'Av. Luna 22'), -- Empleado sin pedidos
('33333333C', 'Elena Ruiz', 'Plaza Mayor 5');

INSERT INTO cliente (nif, nombre) VALUES
('44444444D', 'Ana García');

INSERT INTO supermercado (idSupermercado, superficie, domicilio) VALUES
(10, 1500.00, 'Av. Gran Vía 100');

-- Pedido asignado a '11111111A' (para prueba de DELETE)
INSERT INTO pedido (numeroPedido, idSupermercado, fecha, total, nifCliente, nifEmpleado) VALUES
(1, 10, '2025-12-01', 45.75, '44444444D', '11111111A');

-- Pedido antiguo (para prueba de UPDATE)
INSERT INTO pedido (numeroPedido, idSupermercado, fecha, total, nifCliente, nifEmpleado) VALUES
(2, 10, '2024-11-21', 89.34, '44444444D', '11111111A');


------
USE jardineria;

-- ============================================================
-- FUNCIÓN: calcular_precio_total_pedido
-- Suma cantidad * precio_unidad de todas las líneas del pedido
-- ============================================================

DROP FUNCTION IF EXISTS calcular_precio_total_pedido;

DELIMITER //
CREATE FUNCTION calcular_precio_total_pedido(p_codigo_pedido INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_total DECIMAL(15,2);

    SELECT SUM(cantidad * precio_unidad)
    INTO v_total
    FROM detalle_pedido
    WHERE codigo_pedido = p_codigo_pedido;

    RETURN IFNULL(v_total, 0);
END //
DELIMITER ;

-- Prueba
SELECT calcular_precio_total_pedido(1)  AS total_pedido_1;
SELECT calcular_precio_total_pedido(2)  AS total_pedido_2;
SELECT calcular_precio_total_pedido(10) AS total_pedido_10;


-- ============================================================
-- FUNCIÓN: calcular_suma_pedidos_cliente
-- Suma el total de todos los pedidos del cliente usando
-- la función calcular_precio_total_pedido
-- ============================================================

DROP FUNCTION IF EXISTS calcular_suma_pedidos_cliente;

DELIMITER //
CREATE FUNCTION calcular_suma_pedidos_cliente(p_codigo_cliente INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_total       DECIMAL(15,2) DEFAULT 0;
    DECLARE v_cod_pedido  INT;
    DECLARE v_fin         INT DEFAULT 0;

    DECLARE cur_pedidos CURSOR FOR
        SELECT codigo_pedido
        FROM pedido
        WHERE codigo_cliente = p_codigo_cliente;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_fin = 1;

    OPEN cur_pedidos;

    WHILE v_fin = 0 DO
        FETCH cur_pedidos INTO v_cod_pedido;
        IF v_fin = 0 THEN
            SET v_total = v_total + calcular_precio_total_pedido(v_cod_pedido);
        END IF;
    END WHILE;

    CLOSE cur_pedidos;

    RETURN v_total;
END //
DELIMITER ;

-- Prueba
SELECT calcular_suma_pedidos_cliente(1)  AS suma_pedidos_cliente_1;
SELECT calcular_suma_pedidos_cliente(4)  AS suma_pedidos_cliente_4;
SELECT calcular_suma_pedidos_cliente(7)  AS suma_pedidos_cliente_7;


-- ============================================================
-- FUNCIÓN: calcular_suma_pagos_cliente
-- Suma el campo total de todos los pagos del cliente
-- ============================================================

DROP FUNCTION IF EXISTS calcular_suma_pagos_cliente;

DELIMITER //
CREATE FUNCTION calcular_suma_pagos_cliente(p_codigo_cliente INT)
RETURNS DECIMAL(15,2)
DETERMINISTIC
BEGIN
    DECLARE v_total DECIMAL(15,2);

    SELECT SUM(total)
    INTO v_total
    FROM pago
    WHERE codigo_cliente = p_codigo_cliente;

    RETURN IFNULL(v_total, 0);
END //
DELIMITER ;

-- Prueba
SELECT calcular_suma_pagos_cliente(1)  AS suma_pagos_cliente_1;
SELECT calcular_suma_pagos_cliente(4)  AS suma_pagos_cliente_4;
SELECT calcular_suma_pagos_cliente(7)  AS suma_pagos_cliente_7;


-- ============================================================
-- PROCEDIMIENTO: calcular_pagos_pendientes
-- Recorre todos los clientes, compara suma de pedidos con
-- suma de pagos e inserta en clientes_con_pagos_pendientes
-- los que deben más de lo que han pagado
-- ============================================================

DROP TABLE IF EXISTS clientes_con_pagos_pendientes;
CREATE TABLE clientes_con_pagos_pendientes (
    codigo_cliente      INT            NOT NULL,
    suma_total_pedidos  DECIMAL(15,2)  NOT NULL,
    suma_total_pagos    DECIMAL(15,2)  NOT NULL,
    pendiente_de_pago   DECIMAL(15,2)  NOT NULL,
    PRIMARY KEY (codigo_cliente)
);

DROP PROCEDURE IF EXISTS calcular_pagos_pendientes;

DELIMITER //
CREATE PROCEDURE calcular_pagos_pendientes()
BEGIN
    DECLARE v_codigo_cliente INT;
    DECLARE v_suma_pedidos   DECIMAL(15,2);
    DECLARE v_suma_pagos     DECIMAL(15,2);
    DECLARE v_fin            INT DEFAULT 0;

    DECLARE cur_clientes CURSOR FOR
        SELECT codigo_cliente FROM cliente;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_fin = 1;

    TRUNCATE TABLE clientes_con_pagos_pendientes;

    OPEN cur_clientes;

    WHILE v_fin = 0 DO
        FETCH cur_clientes INTO v_codigo_cliente;
        IF v_fin = 0 THEN
            SET v_suma_pedidos = calcular_suma_pedidos_cliente(v_codigo_cliente);
            SET v_suma_pagos   = calcular_suma_pagos_cliente(v_codigo_cliente);

            IF v_suma_pedidos > v_suma_pagos THEN
                INSERT INTO clientes_con_pagos_pendientes
                    (codigo_cliente, suma_total_pedidos, suma_total_pagos, pendiente_de_pago)
                VALUES
                    (v_codigo_cliente, v_suma_pedidos, v_suma_pagos,
                     v_suma_pedidos - v_suma_pagos);
            END IF;
        END IF;
    END WHILE;

    CLOSE cur_clientes;
END //
DELIMITER ;

-- Ejecutar y comprobar
CALL calcular_pagos_pendientes();
SELECT * FROM clientes_con_pagos_pendientes ORDER BY pendiente_de_pago DESC;


-- ============================================================
-- TABLA: notificaciones
-- ============================================================

DROP TABLE IF EXISTS notificaciones;
CREATE TABLE notificaciones (
    id              INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    fecha_hora      DATETIME        NOT NULL,
    total           DECIMAL(15,2)   NOT NULL,
    codigo_cliente  INT             NOT NULL,
    PRIMARY KEY (id)
);


-- ============================================================
-- TRIGGER: trigger_notificar_pago
-- Se dispara AFTER INSERT en la tabla pago e inserta un
-- registro en notificaciones con los datos del nuevo pago
-- ============================================================

DROP TRIGGER IF EXISTS trigger_notificar_pago;

DELIMITER //
CREATE TRIGGER trigger_notificar_pago
AFTER INSERT ON pago
FOR EACH ROW
BEGIN
    INSERT INTO notificaciones (fecha_hora, total, codigo_cliente)
    VALUES (NOW(), NEW.total, NEW.codigo_cliente);
END //
DELIMITER ;


-- ============================================================
-- PRUEBAS DEL TRIGGER
-- ============================================================

-- Ver pagos actuales del cliente 1
SELECT * FROM pago WHERE codigo_cliente = 1;

-- Ver notificaciones antes de insertar (debe estar vacía)
SELECT * FROM notificaciones;

-- Insertar un pago de prueba para el cliente 1
INSERT INTO pago (codigo_cliente, forma_pago, id_transaccion, fecha_pago, total)
VALUES (1, 'Transferencia', 'TEST-TRG-001', CURDATE(), 1500.00);

-- El trigger debe haber añadido una fila en notificaciones
SELECT * FROM notificaciones;

-- Insertar otro pago para comprobar que se acumulan registros
INSERT INTO pago (codigo_cliente, forma_pago, id_transaccion, fecha_pago, total)
VALUES (4, 'PayPal', 'TEST-TRG-002', CURDATE(), 3200.50);

SELECT * FROM notificaciones;

-- Limpiar datos de prueba (opcional)
-- DELETE FROM pago WHERE id_transaccion IN ('TEST-TRG-001','TEST-TRG-002');

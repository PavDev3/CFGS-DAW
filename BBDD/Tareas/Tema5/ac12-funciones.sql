USE empresa;

-- Limpieza previa (opcional)
DROP PROCEDURE IF EXISTS ac12actualizaInforme;
DROP FUNCTION IF EXISTS ac12categoriaDepartamento;
DROP PROCEDURE IF EXISTS ac12mediaSalarial;
DROP TABLE IF EXISTS informe_salarial;

-- 1) Procedimiento: salario medio por departamento
DELIMITER //

CREATE PROCEDURE ac12mediaSalarial(
    IN p_codDep CHAR(5),
    OUT p_media DECIMAL(12,2)
)
BEGIN
    SELECT IFNULL(AVG(e.Salario), 0)
    INTO p_media
    FROM empleados e
    WHERE e.CodDep = p_codDep;
END //

DELIMITER ;

-- 2) Función: categoría según salario medio
DELIMITER //

CREATE FUNCTION ac12categoriaDepartamento(
    p_codDep CHAR(5)
)
RETURNS VARCHAR(10)
DETERMINISTIC
BEGIN
    DECLARE v_media DECIMAL(12,2);
    DECLARE v_categoria VARCHAR(10);

    -- La función invoca al procedimiento
    CALL ac12mediaSalarial(p_codDep, v_media);

    IF v_media < 2000000 THEN
        SET v_categoria = 'bajo';
    ELSEIF v_media <= 5000000 THEN
        SET v_categoria = 'medio';
    ELSE
        SET v_categoria = 'alto';
    END IF;

    RETURN v_categoria;
END //

DELIMITER ;

-- 3) Tabla de informe
CREATE TABLE informe_salarial (
    CodDep CHAR(5) PRIMARY KEY,
    NomDep VARCHAR(40),
    NumEmpleados INT,
    SalarioMedio DECIMAL(12,2),
    Categoria VARCHAR(10)
);

-- 4) Procedimiento: actualiza una fila del informe
DELIMITER //

CREATE PROCEDURE ac12actualizaInforme(
    IN p_codDep CHAR(5)
)
BEGIN
    DECLARE v_media DECIMAL(12,2);
    DECLARE v_categoria VARCHAR(10);
    DECLARE v_num INT;

    -- Invoca ac12mediaSalarial
    CALL ac12mediaSalarial(p_codDep, v_media);

    -- Calcula nº empleados
    SELECT COUNT(*)
    INTO v_num
    FROM empleados e
    WHERE e.CodDep = p_codDep;

    -- Invoca ac12categoriaDepartamento
    SET v_categoria = ac12categoriaDepartamento(p_codDep);

    -- Actualiza la fila ya existente en informe_salarial
    UPDATE informe_salarial i
    SET i.NumEmpleados = v_num,
        i.SalarioMedio = v_media,
        i.Categoria = v_categoria
    WHERE i.CodDep = p_codDep;
END //

DELIMITER ;

-- 5) Prueba pedida en el enunciado
INSERT INTO informe_salarial (CodDep, NomDep)
VALUES ('PROZS', 'Nombre Departamento PROZS');

CALL ac12actualizaInforme('PROZS');

SELECT * FROM informe_salarial WHERE CodDep = 'PROZS';
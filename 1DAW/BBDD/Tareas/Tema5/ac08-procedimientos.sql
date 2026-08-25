-- 1) 
DROP PROCEDURE IF EXISTS ac08listDepartamentos;

DELIMITER //
CREATE PROCEDURE ac08listDepartamentos(IN pCodCen CHAR(4))
BEGIN
    SELECT d.CodDep, d.NomDep, d.PreAnu, d.TiDir
    FROM departamento d
    WHERE d.CodCen = pCodCen
    ORDER BY d.NomDep;
END //
DELIMITER ;
-- Prueba
CALL ac08listDepartamentos('FAZS');

-- 2) 
DROP PROCEDURE IF EXISTS ac08listDepartamentosPlus;

DELIMITER //
CREATE PROCEDURE ac08listDepartamentosPlus(IN pCodCen CHAR(4))
BEGIN
    IF pCodCen IS NULL THEN
        SELECT d.CodDep, d.NomDep, d.CodCen, d.PreAnu, d.TiDir
        FROM departamento d
        ORDER BY d.CodCen, d.NomDep;
    ELSE
        SELECT d.CodDep, d.NomDep, d.CodCen, d.PreAnu, d.TiDir
        FROM departamento d
        WHERE d.CodCen = pCodCen
        ORDER BY d.NomDep;
    END IF;
END //
DELIMITER ;

-- Prueba con centro concreto
CALL ac08listDepartamentosPlus('OFZS');
-- Prueba con NULL (todos los departamentos)
CALL ac08listDepartamentosPlus(NULL);

-- 3) 
DROP PROCEDURE IF EXISTS ac08updSalarioEmpleadosParam;

DELIMITER //
CREATE PROCEDURE ac08updSalarioEmpleadosParam(IN pIncremento DECIMAL(12,2))
BEGIN
    UPDATE empleado
    SET SalEmp = SalEmp + pIncremento;
END //
DELIMITER ;

-- Prueba (en transacción para no dejar cambios permanentes)
START TRANSACTION;
SELECT CodEmp, NomEmp, SalEmp FROM empleado ORDER BY CodEmp;
CALL ac08updSalarioEmpleadosParam(100000);
SELECT CodEmp, NomEmp, SalEmp FROM empleado ORDER BY CodEmp;
ROLLBACK;

-- 4) 
DROP PROCEDURE IF EXISTS ac08contarEmpleados;
DELIMITER //
CREATE PROCEDURE ac08contarEmpleados(OUT pTotal INT)
BEGIN
    SELECT COUNT(*) INTO pTotal
    FROM empleado;
END //
DELIMITER ;

-- Prueba
CALL ac08contarEmpleados(@total);
SELECT @total AS total_empleados;

-- 5) 
DROP PROCEDURE IF EXISTS ac08contarEmpleadosDpto;
DELIMITER //
CREATE PROCEDURE ac08contarEmpleadosDpto(IN pCodDep CHAR(5), OUT pTotal INT)
BEGIN
    SELECT COUNT(*) INTO pTotal
    FROM empleado
    WHERE CodDep = pCodDep;
END //
DELIMITER ;

-- Prueba
CALL ac08contarEmpleadosDpto('PROZS', @total);
SELECT @total AS total_empleados_dpto;

-- 6) 
DROP PROCEDURE IF EXISTS ac08sueldosSet;
DELIMITER //
CREATE PROCEDURE ac08sueldosSet(OUT pMin DECIMAL(12,2), OUT pMax DECIMAL(12,2), OUT pProm DECIMAL(12,2))
BEGIN
    SET pMin = (SELECT MIN(SalEmp) FROM empleado);
    SET pMax = (SELECT MAX(SalEmp) FROM empleado);
    SET pProm = (SELECT AVG(SalEmp) FROM empleado);
END //
DELIMITER ;

-- Prueba
CALL ac08sueldosSet(@min, @max, @prom);
SELECT @min AS sueldo_min, @max AS sueldo_max, @prom AS sueldo_prom;

-- 7) 
DROP PROCEDURE IF EXISTS ac08sueldosSelectInto;
DELIMITER //
CREATE PROCEDURE ac08sueldosSelectInto(OUT pMin DECIMAL(12,2), OUT pMax DECIMAL(12,2), OUT pProm DECIMAL(12,2))
BEGIN
    SELECT MIN(SalEmp), MAX(SalEmp), AVG(SalEmp)
    INTO pMin, pMax, pProm
    FROM empleado;
END //
DELIMITER ;
-- Prueba
CALL ac08sueldosSelectInto(@min, @max, @prom);
SELECT @min AS sueldo_min, @max AS sueldo_max, @prom AS sueldo_prom;

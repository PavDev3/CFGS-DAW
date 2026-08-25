USE empresa;
-- 1) 
DROP PROCEDURE IF EXISTS ac07listEmpleadosConHijos;

CREATE PROCEDURE ac07listEmpleadosConHijos()
BEGIN
    SELECT e.CodEmp, e.NomEmp, h.NumHij, h.FecNaHi, h.NomHi
    FROM empleado e
    INNER JOIN hijo h ON e.CodEmp = h.CodEmp
    ORDER BY e.CodEmp, h.NumHij;
END
-- Prueba
CALL ac07listEmpleadosConHijos();

-- 2) 
DROP PROCEDURE IF EXISTS ac07contarEmpleados;

CREATE PROCEDURE ac07contarEmpleados()
BEGIN
    SELECT COUNT(*) AS total_empleados
    FROM empleado;
END
-- Prueba
CALL ac07contarEmpleados();

-- 3) 
DROP PROCEDURE IF EXISTS ac07updSalarioEmpleados;

CREATE PROCEDURE ac07updSalarioEmpleados()
BEGIN
    UPDATE empleado
    SET SalEmp = SalEmp * 1.10;
END

-- Prueba (en transaccion para no dejar cambios permanentes)
START TRANSACTION;
SELECT CodEmp, NomEmp, SalEmp FROM empleado ORDER BY CodEmp;
CALL ac07updSalarioEmpleados();
SELECT CodEmp, NomEmp, SalEmp FROM empleado ORDER BY CodEmp;
ROLLBACK;

-- 4) 
-- Lista los procedimientos de la BD empresa
SHOW PROCEDURE STATUS WHERE Db = 'empresa';
-- Lista solo los de esta actividad
SHOW PROCEDURE STATUS WHERE Db = 'empresa' AND Name LIKE 'ac07%';
-- Recupera la definicion de cada uno
SHOW CREATE PROCEDURE ac07listEmpleadosConHijos;
SHOW CREATE PROCEDURE ac07contarEmpleados;
SHOW CREATE PROCEDURE ac07updSalarioEmpleados;
-- 5) 
DROP PROCEDURE ac07updSalarioEmpleados;
-- Prueba borrado
SHOW PROCEDURE STATUS WHERE Db = 'empresa' AND Name = 'ac07updSalarioEmpleados';

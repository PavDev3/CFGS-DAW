USE empresa;

-- ac10contarEmpleados
-- reescribo el procedimiento ac07contarEmpleados pero como funcion
-- ac07 usaba SELECT COUNT(*) directamente sin parametros
-- la diferencia es que ahora devuelvo el valor con RETURN en vez de hacer un SELECT

DROP FUNCTION IF EXISTS ac10contarEmpleados;

DELIMITER //
CREATE FUNCTION ac10contarEmpleados()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM empleado;
    RETURN total;
END //
DELIMITER ;

-- Prueba
SELECT ac10contarEmpleados() AS total_empleados;


-- ac10contarEmpleadosDpto
-- es como ac07contarEmpleados pero filtrando por departamento
-- en act07 no habia version por departamento, esto es ampliarla como funcion con IN y RETURN

DROP FUNCTION IF EXISTS ac10contarEmpleadosDpto;

DELIMITER //
CREATE FUNCTION ac10contarEmpleadosDpto(pCodDep CHAR(5))
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM empleado
    WHERE CodDep = pCodDep;
    RETURN total;
END //
DELIMITER ;

-- Prueba
SELECT ac10contarEmpleadosDpto('PROZS') AS empleados_PROZS;
SELECT ac10contarEmpleadosDpto('DIRGE') AS empleados_DIRGE;
SELECT ac10contarEmpleadosDpto('XXXXX') AS empleados_noexiste;


-- ac10presupuestoCentro
-- a partir del codigo de un centro devuelve la suma de presupuestos de sus departamentos

DROP FUNCTION IF EXISTS ac10presupuestoCentro;

DELIMITER //
CREATE FUNCTION ac10presupuestoCentro(pCodCen CHAR(4))
RETURNS DECIMAL(12,2)
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(12,2);
    SELECT SUM(PreAnu) INTO total
    FROM departamento
    WHERE CodCen = pCodCen;
    RETURN total;
END //
DELIMITER ;

-- Prueba
SELECT ac10presupuestoCentro('DIGE') AS presupuesto_DIGE;
SELECT ac10presupuestoCentro('FAZS') AS presupuesto_FAZS;
SELECT ac10presupuestoCentro('OFZS') AS presupuesto_OFZS;


-- ac10totalHabilidadesEmpleado
-- devuelve cuantas habilidades tiene un empleado

DROP FUNCTION IF EXISTS ac10totalHabilidadesEmpleado;

DELIMITER //
CREATE FUNCTION ac10totalHabilidadesEmpleado(pCodEmp INT)
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM habemp
    WHERE CodEmp = pCodEmp;
    RETURN total;
END //
DELIMITER ;

-- Prueba
SELECT ac10totalHabilidadesEmpleado(1) AS habilidades_emp1;
SELECT ac10totalHabilidadesEmpleado(5) AS habilidades_emp5;
SELECT ac10totalHabilidadesEmpleado(7) AS habilidades_emp7;


-- ac10totalEmpleadosHabilidad
-- devuelve cuantos empleados tienen una habilidad concreta

DROP FUNCTION IF EXISTS ac10totalEmpleadosHabilidad;

DELIMITER //
CREATE FUNCTION ac10totalEmpleadosHabilidad(pCodHab CHAR(5))
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM habemp
    WHERE CodHab = pCodHab;
    RETURN total;
END //
DELIMITER ;

-- Prueba
SELECT ac10totalEmpleadosHabilidad('RELPU') AS empleados_RELPU;
SELECT ac10totalEmpleadosHabilidad('GEREN') AS empleados_GEREN;
SELECT ac10totalEmpleadosHabilidad('INFOR') AS empleados_INFOR;


-- ac10directorCentro
-- devuelve el nombre del director de un centro a partir de su codigo
-- el director esta en centro.CodEmpDir y el nombre en empleado.NomEmp

DROP FUNCTION IF EXISTS ac10directorCentro;

DELIMITER //
CREATE FUNCTION ac10directorCentro(pCodCen CHAR(4))
RETURNS VARCHAR(40)
DETERMINISTIC
BEGIN
    DECLARE nombre VARCHAR(40);
    SELECT e.NomEmp INTO nombre
    FROM centro c
    JOIN empleado e ON c.CodEmpDir = e.CodEmp
    WHERE c.CodCen = pCodCen;
    RETURN nombre;
END //
DELIMITER ;

-- Prueba
SELECT ac10directorCentro('DIGE') AS director_DIGE;
SELECT ac10directorCentro('FAZS') AS director_FAZS;
SELECT ac10directorCentro('OFZS') AS director_OFZS;


-- ac10emailEmpleado
-- devuelve el email del empleado con formato CodEmp@CodDep.CodCen.com
-- necesito el CodDep del empleado y el CodCen del departamento

DROP FUNCTION IF EXISTS ac10emailEmpleado;

DELIMITER //
CREATE FUNCTION ac10emailEmpleado(pCodEmp INT)
RETURNS VARCHAR(60)
DETERMINISTIC
BEGIN
    DECLARE email VARCHAR(60);
    SELECT CONCAT(e.CodEmp, '@', e.CodDep, '.', d.CodCen, '.com') INTO email
    FROM empleado e
    JOIN departamento d ON e.CodDep = d.CodDep
    WHERE e.CodEmp = pCodEmp;
    RETURN email;
END //
DELIMITER ;

-- Prueba
SELECT ac10emailEmpleado(1) AS email_emp1;
SELECT ac10emailEmpleado(5) AS email_emp5;
SELECT ac10emailEmpleado(9) AS email_emp9;


-- ac10validaHijosEmpleados
-- comprueba si el numero de hijos en la tabla empleado (NumHi)
-- coincide con los registros reales de la tabla hijo para ese empleado
-- devuelve 1 si coincide o 0 si no coincide

DROP FUNCTION IF EXISTS ac10validaHijosEmpleados;

DELIMITER //
CREATE FUNCTION ac10validaHijosEmpleados(pCodEmp INT)
RETURNS TINYINT
DETERMINISTIC
BEGIN
    DECLARE numHiEmpleado INT;
    DECLARE numHiTabla INT;

    SELECT NumHi INTO numHiEmpleado
    FROM empleado
    WHERE CodEmp = pCodEmp;

    SELECT COUNT(*) INTO numHiTabla
    FROM hijo
    WHERE CodEmp = pCodEmp;

    IF numHiEmpleado = numHiTabla THEN
        RETURN 1;
    ELSE
        RETURN 0;
    END IF;
END //
DELIMITER ;

-- Prueba
-- emp 1 tiene NumHi=1 y 1 registro en hijo -> deberia dar 1
SELECT ac10validaHijosEmpleados(1) AS valida_emp1;
-- emp 9 tiene NumHi=2 y 2 registros en hijo -> deberia dar 1
SELECT ac10validaHijosEmpleados(9) AS valida_emp9;
-- emp 2 tiene NumHi=0 y 0 registros en hijo -> deberia dar 1
SELECT ac10validaHijosEmpleados(2) AS valida_emp2;
-- comprobacion de todos los empleados a la vez
SELECT CodEmp, NomEmp, NumHi, ac10validaHijosEmpleados(CodEmp) AS datos_ok
FROM empleado
ORDER BY CodEmp;


-- Comprueba las funciones existentes en la base de datos empresa
SHOW FUNCTION STATUS WHERE Db = 'empresa';

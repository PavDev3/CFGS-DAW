-- =============================================
-- Examen Tema 5 - EmployeesDB
-- =============================================

USE EmployeesDB;

-- Ejercicio 1: Crear usuario y asignar permisos
CREATE USER 'nombre'@'%' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON EmployeesDB.* TO 'nombre'@'%';

-- Ejercicio 2: Número de departamentos ubicados en Canadá
DELIMITER //
DROP PROCEDURE IF EXISTS departamentos_canada //
CREATE PROCEDURE departamentos_canada()
BEGIN
    SELECT COUNT(*) AS num_departamentos
    FROM Departments d
    JOIN Locations l ON d.location_id = l.location_id
    JOIN Countries c ON l.country_id = c.country_id
    WHERE c.country_name = 'Canada';
END //
DELIMITER ;

-- Ejercicio 3: Tabla last_employees y procedimiento para los 10 empleados con mayor id
DROP TABLE IF EXISTS last_employees;
CREATE TABLE last_employees (
    employee_id INT(11) PRIMARY KEY
);

DELIMITER //
DROP PROCEDURE IF EXISTS insertar_last_employees //
CREATE PROCEDURE insertar_last_employees()
BEGIN
    DELETE FROM last_employees;
    INSERT INTO last_employees (employee_id)
    SELECT employee_id FROM Employees
    ORDER BY employee_id DESC
    LIMIT 10;
END //
DELIMITER ;

CALL insertar_last_employees();

-- Ejercicio 4: Función que devuelve las iniciales de todos los empleados
DELIMITER //
DROP FUNCTION IF EXISTS iniciales_empleados //
CREATE FUNCTION iniciales_empleados()
RETURNS VARCHAR(1000)
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE v_first_name VARCHAR(20);
    DECLARE v_last_name VARCHAR(25);
    DECLARE resultado VARCHAR(1000) DEFAULT '';

    DECLARE cur CURSOR FOR SELECT first_name, last_name FROM Employees;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    WHILE fin = 0 DO
        FETCH cur INTO v_first_name, v_last_name;
        IF fin = 0 THEN
            SET resultado = CONCAT(resultado, LEFT(IFNULL(v_first_name, '?'), 1), LEFT(v_last_name, 1), ' ');
        END IF;
    END WHILE;
    CLOSE cur;

    RETURN resultado;
END //
DELIMITER ;

SELECT iniciales_empleados() AS iniciales;

-- Ejercicio 5: Aumentar salario mínimo de trabajos con min_salary < 40000
DELIMITER //
DROP PROCEDURE IF EXISTS aumentar_salario_minimo //
CREATE PROCEDURE aumentar_salario_minimo(IN aumento DECIMAL(8,2), OUT total_salarios DECIMAL(10,2))
BEGIN
    UPDATE Jobs
    SET min_salary = min_salary + aumento
    WHERE min_salary < 40000;

    SET total_salarios = (SELECT SUM(min_salary) FROM Jobs);
END //
DELIMITER ;

CALL aumentar_salario_minimo(500, @total);
SELECT @total AS suma_salarios_minimos;

-- Ejercicio 6: Tabla de errores y trigger que valida el salario antes de actualizar
DROP TABLE IF EXISTS errores_salario;
CREATE TABLE errores_salario (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    empleado VARCHAR(50),
    mensaje VARCHAR(200),
    fecha DATETIME
);

DELIMITER //
DROP TRIGGER IF EXISTS before_update_salary //
CREATE TRIGGER before_update_salary
BEFORE UPDATE ON Employees
FOR EACH ROW
BEGIN
    DECLARE v_min_salary DECIMAL(8,2);
    DECLARE v_max_salary DECIMAL(8,2);

    SELECT min_salary, max_salary INTO v_min_salary, v_max_salary
    FROM Jobs
    WHERE job_id = NEW.job_id;

    IF NEW.salary < v_min_salary OR NEW.salary > v_max_salary THEN
        INSERT INTO errores_salario (empleado, mensaje, fecha)
        VALUES (CONCAT(NEW.first_name, ' ', NEW.last_name), 'El salario esta fuera del rango para este puesto', NOW());
    END IF;
END //
DELIMITER ;

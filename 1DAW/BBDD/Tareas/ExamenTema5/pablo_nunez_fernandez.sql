-- 1

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

-- 2
CREATE TABLE IF NOT EXISTS last_employees (
	employee_id INT(11) PRIMARY KEY
);

-- 2.1
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

-- 3 
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

-- 4
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

-- 5.1 Creacion de la tabla para errores
CREATE TABLE IF NOT EXISTS errores_salario(
    employee_id INT(11),
    first_name VARCHAR(20),
    last_name VARCHAR(25),
    mensaje VARCHAR(255),
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP   
);

-- 5 Trigger
DELIMITER //
DROP TRIGGER IF EXISTS antes_aumento_salario //
CREATE TRIGGER antes_aumento_salario
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

-- Prueba del trigger para crear la tabla y registrar el error
SELECT employee_id, first_name, last_name, job_id, salary FROM Employees LIMIT 5;
UPDATE Employees SET salary = 1 WHERE employee_id = 100;
SELECT * FROM errores_salario;
-- ============================================================
-- BBDD - 3ª Evaluación - Tema 05. SQL Avanzado
-- Procedimientos, funciones y triggers
-- Base de datos: EmployeesDB
-- ============================================================

USE EmployeesDB;

DELIMITER $$

-- ============================================================
-- EJERCICIO 1 (1,5 puntos)
-- Procedimiento almacenado: número de departamentos en una ciudad
-- Parámetro de entrada: nombre de la ciudad
-- ============================================================

DROP PROCEDURE IF EXISTS departamentos_por_ciudad$$

CREATE PROCEDURE departamentos_por_ciudad(IN p_ciudad VARCHAR(30))
BEGIN
    SELECT COUNT(*) AS num_departamentos
    FROM Departments d
    JOIN Locations l ON d.location_id = l.location_id
    WHERE l.city = p_ciudad;
END$$

-- Ejemplo de uso:
-- CALL departamentos_por_ciudad('Seattle');


-- ============================================================
-- EJERCICIO 2 (1,5 puntos)
-- Tabla SetEmployees + Trigger AFTER INSERT en Employees
-- Inserta el employee_id cuando el empleado lleva más de 2 años
-- ============================================================

DROP TABLE IF EXISTS SetEmployees$$

CREATE TABLE SetEmployees (
    employee_id INT(11) PRIMARY KEY
)$$

DROP TRIGGER IF EXISTS trg_set_employees_insert$$

CREATE TRIGGER trg_set_employees_insert
AFTER INSERT ON Employees
FOR EACH ROW
BEGIN
    IF NEW.hire_date < DATE_SUB(CURDATE(), INTERVAL 2 YEAR) THEN
        INSERT IGNORE INTO SetEmployees(employee_id)
        VALUES (NEW.employee_id);
    END IF;
END$$

-- Ejemplo de uso (empleado con más de 2 años de antigüedad):
-- INSERT INTO Employees(first_name, last_name, email, hire_date, job_id, salary, department_id)
-- VALUES ('Test', 'User', 'test@test.com', '2020-01-01', 9, 5000.00, 6);


-- ============================================================
-- EJERCICIO 3 (2,5 puntos)
-- Función almacenada: iniciales del nombre + último carácter del apellido
-- Parámetro: employee_id
-- Retorna: iniciales de cada palabra del nombre + último carácter del apellido
-- Ejemplo: "Jose Manuel" Urman -> "J.M.n"
-- ============================================================

DROP FUNCTION IF EXISTS iniciales_empleado$$

CREATE FUNCTION iniciales_empleado(p_employee_id INT)
RETURNS VARCHAR(50)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE v_nombre  VARCHAR(20);
    DECLARE v_apellido VARCHAR(25);
    DECLARE v_resultado VARCHAR(50);
    DECLARE v_palabra  VARCHAR(20);
    DECLARE v_pos      INT;
    DECLARE v_resto    VARCHAR(20);

    SELECT first_name, last_name
    INTO v_nombre, v_apellido
    FROM Employees
    WHERE employee_id = p_employee_id;

    SET v_resultado = '';
    SET v_resto = TRIM(v_nombre);

    -- Extraer la inicial de cada palabra del nombre
    WHILE CHAR_LENGTH(v_resto) > 0 DO
        SET v_pos = LOCATE(' ', v_resto);
        IF v_pos = 0 THEN
            SET v_palabra = v_resto;
            SET v_resto   = '';
        ELSE
            SET v_palabra = LEFT(v_resto, v_pos - 1);
            SET v_resto   = TRIM(SUBSTRING(v_resto, v_pos + 1));
        END IF;
        SET v_resultado = CONCAT(v_resultado, LEFT(v_palabra, 1), '.');
    END WHILE;

    -- Añadir el último carácter del apellido
    SET v_resultado = CONCAT(v_resultado, RIGHT(v_apellido, 1));

    RETURN v_resultado;
END$$

-- Ejemplo de uso:
-- SELECT iniciales_empleado(112);  -- Jose Manuel Urman -> J.M.n
-- SELECT iniciales_empleado(100);  -- Steven King -> S.g


-- ============================================================
-- EJERCICIO 4 (2,5 puntos)
-- Tabla de log + Trigger BEFORE UPDATE en Employees
-- Valida que el nuevo salario esté dentro del rango del puesto
-- Si está fuera de rango: registra en LogSalarioFueraRango y lanza error
-- ============================================================

DROP TABLE IF EXISTS LogSalarioFueraRango$$

CREATE TABLE LogSalarioFueraRango (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    employee_id     INT(11)       NOT NULL,
    nombre_empleado VARCHAR(50)   NOT NULL,
    job_id          INT(11)       NOT NULL,
    salario_actual  DECIMAL(8,2)  NOT NULL,
    salario_nuevo   DECIMAL(8,2)  NOT NULL,
    min_salary      DECIMAL(8,2)  NOT NULL,
    max_salary      DECIMAL(8,2)  NOT NULL,
    fecha           DATETIME DEFAULT CURRENT_TIMESTAMP
)$$

DROP TRIGGER IF EXISTS trg_check_salary_before_update$$

CREATE TRIGGER trg_check_salary_before_update
BEFORE UPDATE ON Employees
FOR EACH ROW
BEGIN
    DECLARE v_min DECIMAL(8,2);
    DECLARE v_max DECIMAL(8,2);

    -- Solo validar si el salario está siendo modificado
    IF NEW.salary != OLD.salary THEN

        SELECT min_salary, max_salary
        INTO v_min, v_max
        FROM Jobs
        WHERE job_id = NEW.job_id;

        IF NEW.salary < v_min OR NEW.salary > v_max THEN

            -- Registrar el intento en la tabla de log
            INSERT INTO LogSalarioFueraRango
                (employee_id, nombre_empleado, job_id, salario_actual,
                 salario_nuevo, min_salary, max_salary)
            VALUES
                (OLD.employee_id,
                 CONCAT(OLD.first_name, ' ', OLD.last_name),
                 NEW.job_id,
                 OLD.salary,
                 NEW.salary,
                 v_min,
                 v_max);

            -- Lanzar el error
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'El salario está fuera de rango para este puesto';
        END IF;
    END IF;
END$$

DELIMITER ;

-- Ejemplo de uso (genera error):
-- UPDATE Employees SET salary = 999.00 WHERE employee_id = 100;
-- SELECT * FROM LogSalarioFueraRango;

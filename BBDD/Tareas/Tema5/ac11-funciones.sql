-- Usar una base de datos de pruebas
-- CREATE DATABASE IF NOT EXISTS pruebas;
-- USE pruebas;


-- 1) Crear tabla alumnado
CREATE TABLE alumnado (
    id INT UNSIGNED PRIMARY KEY,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    curso VARCHAR(50)
);


-- 2) Insertar 5 registros inventados
INSERT INTO alumnado (id, nombre, apellidos, curso) VALUES
(1, 'Alberto', 'Morales', 'BD'),
(2, 'Lucia', 'Serrano', 'DAW'),
(3, 'Carlos', 'Ramirez', 'ASIR'),
(4, 'Marta', 'Lopez', 'SMR'),
(5, 'Diego', 'Navarro', 'DAM');

-- 3) Crear función crearEmail(nombre, apellidos, curso)
DELIMITER //

CREATE FUNCTION crearEmail(
    p_nombre VARCHAR(50),
    p_apellidos VARCHAR(50),
    p_curso VARCHAR(50)
)
RETURNS VARCHAR(150)
DETERMINISTIC
BEGIN
    DECLARE v_email VARCHAR(150);

    SET v_email = CONCAT(
        LEFT(LOWER(p_nombre), 1),      -- primer carácter del nombre
        LEFT(LOWER(p_apellidos), 5),   -- cinco primeros de apellidos
        CHAR_LENGTH(p_apellidos),      -- longitud de apellidos
        '@',
        LOWER(p_curso),
        '.kursal.es'
    );

    RETURN v_email;
END //
DELIMITER ;

-- llamar a la funcion
SELECT crearEmail('Alberto', 'Morales', 'BD') AS email;
SELECT crearEmail('Lucia', 'Serrano', 'DAW') AS email;
SELECT crearEmail('Carlos', 'Ramirez', 'ASIR') AS email;
SELECT crearEmail('Marta', 'Lopez', 'SMR') AS email;
SELECT crearEmail('Diego', 'Navarro', 'DAM') AS email;

-- 4) Añadir columna email a la tabla alumnado
ALTER TABLE alumnado
ADD COLUMN email VARCHAR(150);

-- 5) Crear procedimiento para actualizar el email del alumnado existente
DELIMITER //

CREATE PROCEDURE ac11actualizarColumnaEmail()
BEGIN
    UPDATE alumnado
    SET email = crearEmail(nombre, apellidos, curso);
END //

DELIMITER ;

-- 6) Ejecutar el procedimiento
CALL ac11actualizarColumnaEmail();

-- 7) Comprobar resultado
SELECT * FROM alumnado;

-- mostrar procedimientos
SHOW PROCEDURE STATUS WHERE Db = 'ac11';
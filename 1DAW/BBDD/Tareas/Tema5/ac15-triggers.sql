-- Este trigger llama a la funcion crearEmail del ejercicio 11 de la tabla alumnado
-- Si el email es nulo o vacío se le asigna el valor que devuelve la función crearEmail

CREATE TABLE IF NOT EXISTS logCambiosEmail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT,
    fechaHora DATETIME,
    oldEmail VARCHAR(255),
    newEmail VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS logAlumnosEliminados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT,
    fechaHora DATETIME,
    nombre VARCHAR(255),
    apellido VARCHAR(255),
    email VARCHAR(255)
);

DELIMITER $$

DROP TRIGGER IF EXISTS trg_alumnado_before_insert$$
CREATE TRIGGER trg_alumnado_before_insert
BEFORE INSERT ON alumnado
FOR EACH ROW
BEGIN
    IF NEW.email IS NULL OR NEW.email = '' THEN
        SET NEW.email = crearEmail(NEW.nombre, NEW.apellidos, NEW.curso);
    END IF;
END$$

DROP TRIGGER IF EXISTS trg_alumnado_after_update$$
CREATE TRIGGER trg_alumnado_after_update
AFTER UPDATE ON alumnado
FOR EACH ROW
BEGIN
    IF OLD.email != NEW.email THEN
        INSERT INTO logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail)
        VALUES (NEW.id, NOW(), OLD.email, NEW.email);
    END IF;
END$$

DROP TRIGGER IF EXISTS trg_alumnado_after_insert$$
CREATE TRIGGER trg_alumnado_after_insert
AFTER INSERT ON alumnado
FOR EACH ROW
BEGIN
    INSERT INTO logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail)
    VALUES (NEW.id, NOW(), NULL, NEW.email);
END$$

DROP TRIGGER IF EXISTS ac1104triggerGuardarAlumnosAfterDelete$$
CREATE TRIGGER ac1104triggerGuardarAlumnosAfterDelete
AFTER DELETE ON alumnado
FOR EACH ROW
BEGIN
    INSERT INTO logAlumnosEliminados (idAlumno, fechaHora, nombre, apellido, email)
    VALUES (OLD.id, NOW(), OLD.nombre, OLD.apellidos, OLD.email);
END$$

DELIMITER ;

-- insert alumno para probar el trigger
DELETE FROM alumnado WHERE id = 6;
INSERT INTO alumnado (id, nombre, apellidos, curso)
VALUES (6, 'Juan', 'Perez', '1 ESO');

-- mostrar el log de cambios de email
SELECT * FROM logCambiosEmail;

USE empresa;

CREATE TABLE IF NOT EXISTS salarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    salario DECIMAL(12,2) NOT NULL CHECK (salario > 0),
    codigo_empleado INT(10),
    CONSTRAINT fk_salarios_emp FOREIGN KEY (codigo_empleado) REFERENCES empleado(CodEmp)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

DELIMITER $$

-- Al insertar un hijo, incrementa NumHi del empleado correspondiente
DROP TRIGGER IF EXISTS triggerHolaHijo$$
CREATE TRIGGER triggerHolaHijo
AFTER INSERT ON hijo
FOR EACH ROW
BEGIN
    UPDATE empleado
    SET NumHi = numHi + 1
    WHERE CodEmp = NEW.CodEmp;
END$$

-- Al eliminar un hijo, decrementa NumHi del empleado correspondiente
DROP TRIGGER IF EXISTS triggerAdiosHijo$$
CREATE TRIGGER triggerAdiosHijo
AFTER DELETE ON hijo
FOR EACH ROW
BEGIN
    UPDATE empleado
    SET NumHi = NumHi - 1
    WHERE CodEmp = OLD.CodEmp;
END$$

-- Al insertar un empleado, registra su salario inicial en la tabla salarios
DROP TRIGGER IF EXISTS triggerSalariosEmpleadoAfterInsert$$
CREATE TRIGGER triggerSalariosEmpleadoAfterInsert
AFTER INSERT ON empleado
FOR EACH ROW
BEGIN
    INSERT INTO salarios (salario, codigo_empleado)
    VALUES (NEW.SalEmp, NEW.CodEmp);
END$$

-- Al actualizar un empleado, registra el nuevo salario solo si ha cambiado
DROP TRIGGER IF EXISTS triggerSalariosEmpleadoAfterUpdate$$
CREATE TRIGGER triggerSalariosEmpleadoAfterUpdate
AFTER UPDATE ON empleado
FOR EACH ROW
BEGIN
    IF OLD.SalEmp != NEW.SalEmp THEN
        INSERT INTO salarios (salario, codigo_empleado)
        VALUES (NEW.SalEmp, NEW.CodEmp);
    END IF;
END$$

DELIMITER ;

-- pruebas

-- triggerHolaHijo: insertar un hijo al empleado 2 (actualmente NumHi = 0)
INSERT INTO hijo (CodEmp, NumHij, FecNaHi, NomHi)
VALUES (2, 1, '2000-05-10', 'Bacterio Manrique, Luis');
SELECT CodEmp, NomEmp, NumHi FROM empleado WHERE CodEmp = 2;

-- triggerAdiosHijo: eliminar ese hijo
DELETE FROM hijo WHERE CodEmp = 2 AND NumHij = 1;
SELECT CodEmp, NomEmp, NumHi FROM empleado WHERE CodEmp = 2;

-- triggerSalariosEmpleadoAfterUpdate: subir el salario del empleado 7
UPDATE empleado SET SalEmp = 1800000 WHERE CodEmp = 7;
SELECT * FROM salarios;

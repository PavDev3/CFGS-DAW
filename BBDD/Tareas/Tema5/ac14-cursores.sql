USE empresa;

-- Crear tabla departamentoFamiliar con la misma estructura que departamento
DROP TABLE IF EXISTS departamentoFamiliar;
CREATE TABLE departamentoFamiliar LIKE departamento;

SELECT * FROM departamentoFamiliar;


-- -----------------------------------------------------------------------------
-- ac14departamentoFamiliar
-- Recorre con un cursor los departamentos que tienen 2 o mas trabajadores
-- con hijos (registros en tabla hijo) e inserta en departamentoFamiliar:
--   1) El departamento original con la mitad del presupuesto.
--   2) Un sub-departamento "Familiar" con codigo modificado (5o char = '2'),
--      nombre con sufijo " Familiar", CodDepDep = codigo original y
--      la otra mitad del presupuesto.
-- -----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS ac14departamentoFamiliar;

DELIMITER //
CREATE PROCEDURE ac14departamentoFamiliar()
BEGIN
    DECLARE dep            ROW TYPE OF departamento;
    DECLARE fin            INT DEFAULT 0;
    DECLARE nuevoPresupuesto DECIMAL(12,2);
    DECLARE nuevoCodigo    VARCHAR(5);
    DECLARE nuevoNombre    VARCHAR(100);

    DECLARE cur_dep CURSOR FOR
        SELECT d.CodDep, d.CodEmpDir, d.CodDepDep, d.CodCen, d.NomDep, d.PreAnu, d.TiDir
        FROM   departamento d
        INNER JOIN empleado e ON d.CodDep = e.CodDep
        WHERE  e.NumHi > 0
        GROUP BY d.CodDep
        HAVING COUNT(e.CodEmp) >= 2;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur_dep;
    WHILE fin = 0 DO
        FETCH cur_dep INTO dep;
        IF fin = 0 THEN

            SET nuevoPresupuesto = dep.PreAnu / 2;
            SET nuevoCodigo      = CONCAT(SUBSTRING(dep.CodDep, 1, 4), '2');
            SET nuevoNombre      = CONCAT(dep.NomDep, ' Familiar');

            -- 1) Insertar departamento original copiando la fila completa
            INSERT INTO departamentoFamiliar SELECT * FROM departamento WHERE CodDep = dep.CodDep;
            -- Actualizar la fila recien insertada reduciendo su presupuesto a la mitad
            UPDATE departamentoFamiliar SET PreAnu = nuevoPresupuesto WHERE CodDep = dep.CodDep;

            -- 2) Insertar sub-departamento familiar
            --    Codigo: primeros 4 chars + '2'  (ej. PROZS -> PROZ2)
            --    Nombre: nombre original + ' Familiar'
            --    CodDepDep: codigo del departamento original
            --    Presupuesto: la otra mitad
            INSERT INTO departamentoFamiliar
                (CodDep, CodEmpDir, CodDepDep, CodCen, NomDep, PreAnu, TiDir)
            VALUES
                (nuevoCodigo, dep.CodEmpDir, dep.CodDep, dep.CodCen,
                 nuevoNombre, nuevoPresupuesto, dep.TiDir);

        END IF;
    END WHILE;
    CLOSE cur_dep;
END//
DELIMITER ;

CALL ac14departamentoFamiliar();
SELECT * FROM departamentoFamiliar;

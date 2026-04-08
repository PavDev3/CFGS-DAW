USE empresa;

DROP PROCEDURE IF EXISTS ac09semanaCase;

DELIMITER //
CREATE PROCEDURE ac09semanaCase(IN dia INT)
BEGIN
    DECLARE nombre_dia VARCHAR(15);
    CASE dia
        WHEN 1 THEN SET nombre_dia = 'Lunes';
        WHEN 2 THEN SET nombre_dia = 'Martes';
        WHEN 3 THEN SET nombre_dia = 'Miercoles';
        WHEN 4 THEN SET nombre_dia = 'Jueves';
        WHEN 5 THEN SET nombre_dia = 'Viernes';
        WHEN 6 THEN SET nombre_dia = 'Sabado';
        WHEN 7 THEN SET nombre_dia = 'Domingo';
        ELSE         SET nombre_dia = 'Dia no valido';
    END CASE;
    SELECT nombre_dia AS resultado;
END//
DELIMITER ;

CALL ac09semanaCase(1);
CALL ac09semanaCase(5);
CALL ac09semanaCase(0);
CALL ac09semanaCase(8);
CALL ac09semanaCase(NULL);


DROP PROCEDURE IF EXISTS ac09semanaCasIng;

DELIMITER //
CREATE PROCEDURE ac09semanaCasIng(IN dia INT, IN idioma VARCHAR(3))
BEGIN
    DECLARE nombre_cas VARCHAR(15) DEFAULT NULL;
    DECLARE nombre_ing VARCHAR(15) DEFAULT NULL;

    CASE dia
        WHEN 1 THEN SET nombre_cas = 'Lunes';     SET nombre_ing = 'Monday';
        WHEN 2 THEN SET nombre_cas = 'Martes';    SET nombre_ing = 'Tuesday';
        WHEN 3 THEN SET nombre_cas = 'Miercoles'; SET nombre_ing = 'Wednesday';
        WHEN 4 THEN SET nombre_cas = 'Jueves';    SET nombre_ing = 'Thursday';
        WHEN 5 THEN SET nombre_cas = 'Viernes';   SET nombre_ing = 'Friday';
        WHEN 6 THEN SET nombre_cas = 'Sabado';    SET nombre_ing = 'Saturday';
        WHEN 7 THEN SET nombre_cas = 'Domingo';   SET nombre_ing = 'Sunday';
        ELSE SELECT 'Dia no valido' AS resultado;
    END CASE;

    IF nombre_cas IS NOT NULL THEN
        IF idioma = 'CAS' THEN
            SELECT nombre_cas AS resultado;
        ELSEIF idioma = 'ING' THEN
            SELECT nombre_ing AS resultado;
        ELSE
            SELECT 'Idioma no valido' AS resultado;
        END IF;
    END IF;
END//
DELIMITER ;

CALL ac09semanaCasIng(1, 'CAS');
CALL ac09semanaCasIng(1, 'ING');
CALL ac09semanaCasIng(7, 'CAS');
CALL ac09semanaCasIng(7, 'ING');
CALL ac09semanaCasIng(8, 'CAS');
CALL ac09semanaCasIng(3, 'FRA');
CALL ac09semanaCasIng(NULL, 'CAS');


DROP PROCEDURE IF EXISTS ac09insertaHabilidad;

DELIMITER //
CREATE PROCEDURE ac09insertaHabilidad(IN cod VARCHAR(10), IN des VARCHAR(30))
BEGIN
    IF CHAR_LENGTH(TRIM(cod)) = 5 THEN
        INSERT INTO habilidad (CodHab, DesHab) VALUES (cod, des);
    END IF;
END//
DELIMITER ;

CALL ac09insertaHabilidad('CONTA', 'Contabilidad');
CALL ac09insertaHabilidad('SQL', 'Base de datos');
CALL ac09insertaHabilidad('JAVA1', 'Programacion Java');
SELECT * FROM habilidad;


DROP PROCEDURE IF EXISTS ac09upsertHabilidad;

DELIMITER //
CREATE PROCEDURE ac09upsertHabilidad(IN cod VARCHAR(10), IN des VARCHAR(30))
BEGIN
    IF CHAR_LENGTH(TRIM(cod)) = 5 THEN
        IF EXISTS (SELECT 1 FROM habilidad WHERE CodHab = cod) THEN
            UPDATE habilidad SET DesHab = des WHERE CodHab = cod;
        ELSE
            INSERT INTO habilidad (CodHab, DesHab) VALUES (cod, des);
        END IF;
    END IF;
END//
DELIMITER ;

CALL ac09upsertHabilidad('PYTH0', 'Python basico');
CALL ac09upsertHabilidad('PYTH0', 'Python avanzado');
CALL ac09upsertHabilidad('JS', 'JavaScript');
SELECT * FROM habilidad WHERE CodHab = 'PYTH0';


DROP PROCEDURE IF EXISTS ac09upsertHabilidadPlus;

DELIMITER //
CREATE PROCEDURE ac09upsertHabilidadPlus(IN cod VARCHAR(10), IN des VARCHAR(30))
BEGIN
    IF cod IS NULL OR des IS NULL THEN
        SELECT 'Error: los parametros no pueden ser nulos' AS resultado;
    ELSEIF CHAR_LENGTH(cod) != 5 THEN
        SELECT 'Error: el codigo debe tener exactamente 5 caracteres' AS resultado;
    ELSE
        IF EXISTS (SELECT 1 FROM habilidad WHERE CodHab = cod) THEN
            UPDATE habilidad SET DesHab = des WHERE CodHab = cod;
            SELECT 'Habilidad actualizada correctamente' AS resultado;
        ELSE
            INSERT INTO habilidad (CodHab, DesHab) VALUES (cod, des);
            SELECT 'Habilidad insertada correctamente' AS resultado;
        END IF;
    END IF;
END//
DELIMITER ;

CALL ac09upsertHabilidadPlus('REACT', 'ReactJS');
CALL ac09upsertHabilidadPlus('REACT', 'React avanzado');
CALL ac09upsertHabilidadPlus(NULL, 'algo');
CALL ac09upsertHabilidadPlus('NODEJ', NULL);
CALL ac09upsertHabilidadPlus('', 'vacio');
SELECT * FROM habilidad;

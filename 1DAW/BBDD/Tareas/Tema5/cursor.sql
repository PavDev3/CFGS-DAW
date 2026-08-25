-- MariaDB cursor procedure

CREATE TABLE IF NOT EXISTS cursor_table (
    CodEmp INT,
    NomEmp VARCHAR(40)
);

DELIMITER //

DROP PROCEDURE IF EXISTS cursor_procedure//
CREATE PROCEDURE cursor_procedure()
BEGIN
    DECLARE FIN int default 0;
    DECLARE vCodEmp int;
    DECLARE vNomEmp varchar(40);
    DECLARE cur CURSOR FOR SELECT CodEmp, NomEmp FROM empleado;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = 1;

    OPEN cur;
    WHILE FIN = 0 DO
        FETCH cur INTO vCodEmp, vNomEmp;
        IF FIN = 0 THEN
            INSERT INTO cursor_table (CodEmp, NomEmp) VALUES (vCodEmp, vNomEmp);
        END IF;
    END WHILE;
    CLOSE cur;
END//

DELIMITER ;

CALL cursor_procedure();

-- ejercicoo 2 vamos a rellenar la tercera tabla con el menor identificador, y los datos de la primera 

CREATE TABLE t1 (
  id INT UNSIGNED PRIMARY KEY,
  datos VARCHAR(16)
);

CREATE TABLE t2 (
  id INT UNSIGNED
);

CREATE TABLE t3 (
  datos VARCHAR(16),
  id INT UNSIGNED
);

INSERT INTO t1 VALUES (1, 'A');
INSERT INTO t1 VALUES (2, 'B');
INSERT INTO t1 VALUES (30, 'C');

INSERT INTO t2 VALUES (10);
INSERT INTO t2 VALUES (20);
INSERT INTO t2 VALUES (3); 

DELIMITER //

DROP PROCEDURE IF EXISTS cursor_procedure2//
CREATE PROCEDURE cursor_procedure2()
BEGIN
    DECLARE FIN int default 0;
    DECLARE a VARCHAR(16);
    DECLARE b,c INT;
    DECLARE cur CURSOR FOR SELECT id, datos FROM t1;
    DECLARE cur2 CURSOR FOR SELECT id FROM t2;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FIN = 1;

    OPEN cur;
    OPEN cur2;
    WHILE FIN = 0 DO
        FETCH cur INTO vId, vDatos;
        FETCH cur2 INTO vId2;
        IF FIN = 0 THEN
        IF b < c THEN
            INSERT INTO t3 (a, b) VALUES (a,b);
        ELSE 
            INSERT INTO t3 values (a,c)
        END IF;
    END WHILE;
    CLOSE cur;
    CLOSE cur2;
END//

DELIMITER ;

CALL cursor_procedure2();
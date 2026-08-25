USE empresa;

-- Crear tabla empleado_copia con la misma estructura que empleado
DROP TABLE IF EXISTS empleado_copia;
CREATE TABLE empleado_copia LIKE empleado;

SELECT * FROM empleado_copia;


--2

  DROP PROCEDURE IF EXISTS ac1111empleadosSinHijos;                                                                               
                                                            
  DELIMITER //                                                                                                                    
  CREATE PROCEDURE ac1111empleadosSinHijos()
  BEGIN                                                                                                                           
      DECLARE empl ROW TYPE OF empleado;                    
      DECLARE fin  INT DEFAULT 0;

      DECLARE cur_sin_hijos CURSOR FOR                                                                                            
          SELECT CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp
          FROM   empleado                                                                                                         
          WHERE  NumHi = 0;                                 
                                                                                                                                  
      DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;   

      OPEN cur_sin_hijos;

      WHILE fin = 0 DO                                                                                                            
          FETCH cur_sin_hijos INTO empl;
          IF fin = 0 THEN                                                                                                         
              INSERT INTO empleado_copia                    
                  (CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp)
              VALUES                                                                                                              
                  (empl.CodEmp, empl.CodDep, empl.ExTelEmp, empl.FecInEmp, empl.FecNaEmp,
                   empl.NifEmp, empl.NomEmp, empl.NumHi, empl.SalEmp);                                                            
          END IF;                                                                                                                 
      END WHILE;                                                                                                                  
      CLOSE cur_sin_hijos;                                                                                                        
  END//                                                     
  DELIMITER ;

CALL ac1111empleadosSinHijos();
SELECT * FROM empleado_copia;


-- -----------------------------------------------------------------------------
-- ac1111empleadosNumHijos
-- Recorre con un cursor (con parametro) los empleados que tienen exactamente
-- el numero de hijos indicado en pNumHijos e inserta en empleado_copia.
-- -----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS ac1111empleadosNumHijos;

DELIMITER //
CREATE PROCEDURE ac1111empleadosNumHijos(IN pNumHijos INT)
BEGIN
    DECLARE empl ROW TYPE OF empleado;
    DECLARE fin  INT DEFAULT 0;

    -- El cursor filtra por pNumHijos (parametro IN visible en la declaracion del cursor)
    DECLARE cur CURSOR FOR
        SELECT CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp
        FROM   empleado
        WHERE  NumHi = pNumHijos;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    TRUNCATE TABLE empleado_copia;

    OPEN cur;
    WHILE fin = 0 DO
        FETCH cur INTO empl;
        IF fin = 0 THEN
            INSERT INTO empleado_copia
                (CodEmp, CodDep, ExTelEmp, FecInEmp, FecNaEmp, NifEmp, NomEmp, NumHi, SalEmp)
            VALUES
                (empl.CodEmp, empl.CodDep, empl.ExTelEmp, empl.FecInEmp, empl.FecNaEmp,
                 empl.NifEmp, empl.NomEmp, empl.NumHi, empl.SalEmp);
        END IF;
    END WHILE;
    CLOSE cur;
END//
DELIMITER ;

-- Empleados con 0 hijos
CALL ac1111empleadosNumHijos(0);
SELECT * FROM empleado_copia;

-- Empleados con 1 hijo
CALL ac1111empleadosNumHijos(1);
SELECT * FROM empleado_copia;

-- Empleados con 2 hijos
CALL ac1111empleadosNumHijos(2);
SELECT * FROM empleado_copia;

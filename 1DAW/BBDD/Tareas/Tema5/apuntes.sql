-- 1. Procedimiento que categorice a las personas en diferentes rangos de edad: menor 18 junior, menor de 45 senior y mayor de 45 veterano
DELIMITER $$

CREATE PROCEDURE categorizar_personas()
BEGIN
    SELECT 
        nombre,
        edad,
        CASE
            WHEN edad < 18 THEN 'Junior'
            WHEN edad < 45 THEN 'Senior'
            ELSE 'Veterano'
        END AS categoria
    FROM personas;
END$$

DELIMITER ;


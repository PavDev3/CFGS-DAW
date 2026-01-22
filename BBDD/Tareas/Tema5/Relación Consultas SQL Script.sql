CREATE TABLE PROVEEDORES(
  P VARCHAR(2),
  PNOMBRE VARCHAR(7),
  CATEGORIA INT,
  CIUDAD VARCHAR(10),
  PRIMARY KEY(P)
);

INSERT INTO PROVEEDORES (P, PNOMBRE, CATEGORIA, CIUDAD)
VALUES ('P1', 'CARLOS', 20, 'SEVILLA');
INSERT INTO PROVEEDORES (P, PNOMBRE, CATEGORIA, CIUDAD)
VALUES ('P2', 'JUAN', 10, 'MADRID');
INSERT INTO PROVEEDORES (P, PNOMBRE, CATEGORIA, CIUDAD)
VALUES ('P3', 'JOSE', 30, 'SEVILLA');
INSERT INTO PROVEEDORES (P, PNOMBRE, CATEGORIA, CIUDAD)
VALUES ('P4', 'INMA', 20, 'SEVILLA');
INSERT INTO PROVEEDORES (P, PNOMBRE, CATEGORIA, CIUDAD)
VALUES ('P5', 'EVA', 30, 'CACERES');
 
CREATE TABLE COMPONENTES (
  C VARCHAR(2),
  CNOMBRE VARCHAR(7),
  COLOR VARCHAR(5),
  PESO INT,
  CIUDAD VARCHAR(10),
  PRIMARY KEY(C)
);

INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C1', 'X3A', 'ROJO', 12, 'SEVILLA');
INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C2', 'B85', 'VERDE', 17, 'MADRID');
INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C3', 'C4B', 'AZUL', 17, 'MALAGA');
INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C4', 'C4B', 'ROJO', 14, 'SEVILLA');
INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C5', 'VT8', 'AZUL', 12, 'MADRID');
INSERT INTO COMPONENTES (C, CNOMBRE, COLOR, PESO, CIUDAD)
VALUES ('C6', 'C30', 'ROJO', 19, 'SEVILLA');
 
CREATE TABLE ARTICULOS (
  T VARCHAR(2),
  TNOMBRE VARCHAR(15),
  CIUDAD VARCHAR(10),
  PRIMARY KEY(T)
);

INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T1', 'CLASIFICADORA', 'MADRID');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T2', 'PERFORADORA', 'MALAGA');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T3', 'LECTORA', 'CACERES');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T4', 'CONSOLA', 'CACERES');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T5', 'MEZCLADORA', 'SEVILLA');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T6', 'TERMINAL', 'BARCELONA');
INSERT INTO ARTICULOS (T, TNOMBRE, CIUDAD)
VALUES ('T7', 'CINTA', 'SEVILLA');
 
CREATE TABLE ENVIOS (
  P VARCHAR(2) REFERENCES PROVEEDORES(P) ON DELETE SET NULL ON UPDATE CASCADE,
  C VARCHAR(2) REFERENCES COMPONENTES(C) ON DELETE SET NULL ON UPDATE CASCADE,
  T VARCHAR(2) REFERENCES ARTICULOS(T) ON DELETE SET NULL ON UPDATE CASCADE,
  CANTIDAD INT,
  PRIMARY KEY(P, C, T)
);
 
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P1', 'C1', 'T1', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P1', 'C1', 'T4', 700);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T1', 400);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T2', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T3', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T4', 500);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T5', 600);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T6', 400);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C3', 'T7', 800);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P2', 'C5', 'T2', 100);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P3', 'C3', 'T1', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P3', 'C4', 'T2', 500);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P4', 'C6', 'T3', 300);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P4', 'C6', 'T7', 300);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C2', 'T2', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C2', 'T4', 100);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C5', 'T4', 500);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C5', 'T7', 100);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C6', 'T2', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C1', 'T4', 100);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C3', 'T4', 200);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C4', 'T4', 800);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C5', 'T5', 400);
INSERT INTO ENVIOS(P, C, T, CANTIDAD)
VALUES ('P5', 'C6', 'T4', 500);

/* Ejercicios 
1 - Obtener todos los detalles de todos los artículos de CACERES
SELECT * FROM `ARTICULOS` WHERE CIUDAD = 'CACERES';

2- Obtener todos los valores de P# para los proveedores que abastecen el artículo T1. 
SELECT P FROM ENVIOS WHERE T = 'T1';  

3- Obtener  la  lista  de  pares  de  atributos  (COLOR,  CIUDAD)  de  la  tabla  componentes  eliminando los pares duplicados
SELECT DISTINCT COLOR, CIUDAD FROM `COMPONENTES`;

4- Obtener  de  la  tabla  de  artículos  los  valores  de  T#  y  CIUDAD  donde  el  nombre  de  la  ciudad acaba en D o contiene al menos una E. 
SELECT T, CIUDAD FROM `ARTICULOS` WHERE CIUDAD LIKE '%D%' OR CIUDAD LIKE '%E%';
Obtener  los  valores  de  P#  para  los  proveedores  que  suministran  para  el  artículo  T1  el  componente C1. 
SELECT P FROM ENVIOS WHERE T = 'T1' AND C = 'C1';

6.Obtener los valores de TNOMBRE en orden alfabético para los artículos abastecidos por el proveedor P1. 
SELECT DISTINCT A.TNOMBRE 
FROM ARTICULOS A, ENVIOS E 
WHERE A.T = E.T AND E.P = 'P1' 
ORDER BY A.TNOMBRE;

7.Obtener los valores de C# para los componentes suministrados para cualquier artículo de MADRID. 
SELECT C FROM ENVIOS WHERE T IN (SELECT T FROM ARTICULOS WHERE CIUDAD = 'MADRID');

8.Obtener  todos  los  valores  de  C#  de  los  componentes  tales  que  ningún  otro  componente    tenga un valor de peso inferior. 
SELECT C FROM COMPONENTES WHERE PESO = (SELECT MAX(PESO) FROM COMPONENTES);

9.Obtener los valores de P# para los proveedores que suministren los artículos T1 Y T2 ambos proveedores deben de suministrar los dos artículos
hazlo con una subconsulta que primero consulte t1 y luego t2 y luego los proveedores que suministran los dos artículos
como seria asi 
SELECT P FROM ENVIOS WHERE T = 'T1' AND P IN (SELECT P FROM ENVIOS WHERE T = 'T2');

10.Obtener  los  valores  de  P#  para  los  proveedores  que  suministran  para  un  artículo  de  SEVILLA o MADRID un componente ROJO.
select distinct `ENVIOS`.`P` 
from `ENVIOS` 
join ARTICULOS ON `ENVIOS`.`T` = `ARTICULOS`.`T`
join `COMPONENTES` on `ENVIOS`.`C` = `COMPONENTES`.`C`
where (`ARTICULOS`.`CIUDAD` = 'SEVILLA' OR  `ARTICULOS`.`CIUDAD` = 'MADRID') AND `COMPONENTES`.`COLOR` = 'ROJO';

11. Obtener,  mediante  subconsultas,  los  valores  de  C# para  los  componentes  suministrados  para algún artículo de SEVILLA por un proveedor de SEVILLA

select distinct c from `ENVIOS`
where T in (
  select T from `ARTICULOS`
  where CIUDAD = 'SEVILLA'
)
and P in (
  select P from `PROVEEDORES` where CIUDAD = 'SEVILLA'
);

12. Obtener  los  valores  de  T#  para  los  artículos  que  usan  al  menos  un  componente  que  se  puede obtener con el proveedor P1
SELECT DISTINCT T FROM ENVIOS
WHERE C IN (
SELECT DISTINCT C FROM ENVIOS WHERE P = 'P1' );

13. Obtener todas las ternas (CIUDAD, C#, CIUDAD) tales que un proveedor de la primera ciudad  suministre  el  componente  especificado  para  un  artículo  montado  en  la  segunda  ciudad.
SELECT P.CIUDAD, C.C, A.CIUDAD
FROM PROVEEDORES P, ENVIOS E, COMPONENTES C, ARTICULOS A
WHERE P.P = E.P 
  AND E.C = C.C 
  AND E.T = A.T 
ORDER BY P.CIUDAD, C.C, A.CIUDAD;

14. Repetir el ejercicio anterior pero sin recuperar las ternas en los que los dos valores de ciudad sean los mismos.
SELECT P.CIUDAD, C.C, A.CIUDAD
FROM PROVEEDORES P, ENVIOS E, COMPONENTES C, ARTICULOS A
WHERE P.P = E.P 
  AND E.C = C.C 
  AND E.T = A.T 
  AND P.CIUDAD <> A.CIUDAD
ORDER BY P.CIUDAD, C.C, A.CIUDAD;

15. Obtener el número de suministros, el de artículos distintos suministrados y la cantidad total de artículos suministrados por el proveedor P2.
SELECT 
    COUNT(*) AS NUM_SUMINISTROS,
    COUNT(DISTINCT T) AS ARTICULOS_DISTINTOS,
    SUM(CANTIDAD) AS CANTIDAD_TOTAL
FROM ENVIOS 
WHERE P = 'P2';

16. Para cada artículo y componente suministrado obtener los valores de C#, T# y la cantidad total correspondiente.
SELECT C, T, SUM(CANTIDAD) AS CANTIDAD_TOTAL
FROM ENVIOS 
GROUP BY C, T
ORDER BY C, T;

17. Obtener los valores de T# de los artículos abastecidos al menos por un proveedor que no viva en MADRID y que no esté en la misma ciudad en la que se monta el artículo.
SELECT DISTINCT E.T
FROM ENVIOS E, PROVEEDORES P, ARTICULOS A
WHERE E.P = P.P 
  AND E.T = A.T 
  AND P.CIUDAD <> 'MADRID' 
  AND P.CIUDAD <> A.CIUDAD;

18. Obtener los valores de P# para los proveedores que suministran al menos un componente suministrado al menos por un proveedor que suministra al menos un componente ROJO.
SELECT DISTINCT P
FROM ENVIOS 
WHERE C IN (
    SELECT DISTINCT C 
    FROM ENVIOS 
    WHERE P IN (
        SELECT DISTINCT P 
        FROM ENVIOS E, COMPONENTES C 
        WHERE E.C = C.C AND C.COLOR = 'ROJO'
    )
);

19. Obtener los identificadores de artículos, T#, para los que se ha suministrado algún componente del que se haya suministrado una media superior a 320 artículos.
SELECT DISTINCT T
FROM ENVIOS 
WHERE C IN (
    SELECT C 
    FROM ENVIOS 
    GROUP BY C 
    HAVING AVG(CANTIDAD) > 320
);

20. Seleccionar los identificadores de proveedores que hayan realizado algún envío con Cantidad mayor que la media de los envíos realizados para el componente a que corresponda dicho envío.
SELECT DISTINCT P
FROM ENVIOS E1
WHERE E1.CANTIDAD > (
    SELECT AVG(E2.CANTIDAD) 
    FROM ENVIOS E2 
    WHERE E2.C = E1.C
);

21. Seleccionar los identificadores de componentes suministrados para el artículo 'T2' por el proveedor 'P2'.
SELECT C
FROM ENVIOS 
WHERE T = 'T2' AND P = 'P2';

22. Seleccionar todos los datos de los envíos realizados de componentes cuyo color no sea 'ROJO'.
SELECT E.*
FROM ENVIOS E, COMPONENTES C
WHERE E.C = C.C AND C.COLOR <> 'ROJO';

23. Seleccionar los identificadores de componentes que se suministren para los artículos 'T1' y 'T2'.
SELECT DISTINCT C
FROM ENVIOS 
WHERE T = 'T1' AND C IN (
    SELECT C FROM ENVIOS WHERE T = 'T2'
);

24. Seleccionar el identificador de proveedor y el número de envíos de componentes de color 'ROJO' llevados a cabo por cada proveedor.
SELECT E.P, COUNT(*) AS NUM_ENVIOS_ROJOS
FROM ENVIOS E, COMPONENTES C
WHERE E.C = C.C AND C.COLOR = 'ROJO'
GROUP BY E.P;

25. Seleccionar los colores de componentes suministrados por el proveedor 'P1'.
SELECT DISTINCT C.COLOR
FROM ENVIOS E, COMPONENTES C
WHERE E.P = 'P1' AND E.C = C.C;

26. Seleccionar los datos de envío y nombre de ciudad de aquellos envíos que cumplan que el artículo, proveedor y componente son de la misma ciudad.
SELECT E.*, P.CIUDAD
FROM ENVIOS E, PROVEEDORES P, ARTICULOS A, COMPONENTES C
WHERE E.P = P.P 
  AND E.T = A.T 
  AND E.C = C.C 
  AND P.CIUDAD = A.CIUDAD 
  AND P.CIUDAD = C.CIUDAD;

27. Seleccionar los nombres de los componentes que son suministrados en una cantidad total superior a 500.
SELECT C.CNOMBRE
FROM ENVIOS E, COMPONENTES C
WHERE E.C = C.C
GROUP BY C.C, C.CNOMBRE
HAVING SUM(E.CANTIDAD) > 500;

28. Seleccionar los identificadores de proveedores que residan en Sevilla y no suministren más de dos artículos distintos.
SELECT P
FROM PROVEEDORES
WHERE CIUDAD = 'SEVILLA'
AND P IN (
    SELECT E.P
    FROM ENVIOS E
    GROUP BY E.P
    HAVING COUNT(DISTINCT E.T) <= 2
);

29. Seleccionar los identificadores de artículos para los cuales todos sus componentes se fabrican en una misma ciudad.
SELECT DISTINCT E.T
FROM ENVIOS E
WHERE NOT EXISTS (
    SELECT 1
    FROM ENVIOS E2, COMPONENTES C1, COMPONENTES C2
    WHERE E2.T = E.T 
      AND E2.C = C1.C 
      AND E.C = C2.C 
      AND C1.CIUDAD <> C2.CIUDAD
);

30. Seleccionar los identificadores de artículos para los que se provean envíos de todos los componentes existentes en la base de datos.
SELECT T
FROM ARTICULOS A
WHERE NOT EXISTS (
    SELECT C FROM COMPONENTES
    WHERE C NOT IN (
        SELECT E.C FROM ENVIOS E WHERE E.T = A.T
    )
);

31. Seleccionar los códigos de proveedor y artículo que suministran al menos dos componentes de color 'ROJO'.
SELECT E.P, E.T
FROM ENVIOS E, COMPONENTES C
WHERE E.C = C.C AND C.COLOR = 'ROJO'
GROUP BY E.P, E.T
HAVING COUNT(DISTINCT E.C) >= 2;

32. Propón tu mismo consultas que puedan realizarse sobre esta base de datos de ejemplo.
-- Consulta propuesta: Obtener los proveedores que suministran componentes con mayor cantidad media que la media general
SELECT P, AVG(CANTIDAD) AS MEDIA_PROVEEDOR
FROM ENVIOS
GROUP BY P
HAVING AVG(CANTIDAD) > (
    SELECT AVG(CANTIDAD) FROM ENVIOS
);

-- Consulta propuesta: Obtener los componentes que son suministrados por más proveedores que la media
SELECT C, COUNT(DISTINCT P) AS NUM_PROVEEDORES
FROM ENVIOS
GROUP BY C
HAVING COUNT(DISTINCT P) > (
    SELECT AVG(NUM_PROV) FROM (
        SELECT COUNT(DISTINCT P) AS NUM_PROV 
        FROM ENVIOS 
        GROUP BY C
    ) AS MEDIAS
);

-- Consulta propuesta: Obtener las ciudades que proveen más cantidad total de componentes que otras ciudades
SELECT P.CIUDAD, SUM(E.CANTIDAD) AS CANTIDAD_TOTAL
FROM PROVEEDORES P, ENVIOS E
WHERE P.P = E.P
GROUP BY P.CIUDAD
ORDER BY CANTIDAD_TOTAL DESC;




*/


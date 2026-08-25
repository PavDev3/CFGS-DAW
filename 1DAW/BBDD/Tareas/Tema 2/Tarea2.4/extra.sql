-- Consulta 6: Para cada pelicula, muestra su titulo, el nombre del proveedor que la
-- suministra y el stock total disponible de esa pelicula (sumando el
-- stock de todos sus ejemplares). Muestra solo las peliculas cuyo stock
-- total sea superior al stock medio de todas las peliculas.

SELECT p.titulo, prov.nombre AS proveedor, SUM(e.stock) AS stock_total
FROM pelicula p
INNER JOIN proveedor prov ON p.id_proveedor = prov.id_proveedor
INNER JOIN ejemplar e ON e.id_pelicula = p.id_pelicula
GROUP BY p.id_pelicula, p.titulo, prov.nombre
HAVING SUM(e.stock) > (
    SELECT AVG(stock_total)
    FROM (
        SELECT SUM(e2.stock) AS stock_total
        FROM ejemplar e2
        GROUP BY e2.id_pelicula
    ) AS promedios
);

-- Consulta 7: Muestra los directores cuyas peliculas nunca han sido alquiladas.
-- Incluye el nombre del director, su nacionalidad y el titulo de la
-- pelicula sin alquilar.

SELECT d.nombre AS director, d.nacionalidad, p.titulo
FROM director d
INNER JOIN pelicula p ON d.id_director = p.id_director
LEFT JOIN ejemplar e ON e.id_pelicula = p.id_pelicula
LEFT JOIN alquila a ON a.id_ejemplar = e.id_ejemplar
WHERE a.id_cliente IS NULL
ORDER BY d.nombre;

-- Consulta 8: Crea una tabla resumen llamada 'resumen_alquileres_cliente' que
-- almacene para cada cliente: su nombre, el numero total de alquileres
-- realizados y la fecha de su ultimo alquiler. Inserta los datos
-- usando INSERT INTO ... SELECT.

CREATE TABLE IF NOT EXISTS resumen_alquileres_cliente (
    id_cliente INT PRIMARY KEY,
    nombre_cliente VARCHAR(100),
    total_alquileres INT,
    ultimo_alquiler DATE
) ENGINE=InnoDB;

INSERT INTO resumen_alquileres_cliente (id_cliente, nombre_cliente, total_alquileres, ultimo_alquiler)
SELECT c.id_cliente, c.nombre, COUNT(*) AS total_alquileres, MAX(a.fecha_recogida) AS ultimo_alquiler
FROM cliente c
INNER JOIN alquila a ON c.id_cliente = a.id_cliente
GROUP BY c.id_cliente, c.nombre;

-- Consulta 9: Incrementa en un 10% el salario de los trabajadores que hayan
-- gestionado al menos un alquiler que aun no ha sido devuelto
-- (fecha_entrega IS NULL).

UPDATE trabajador
SET salario = salario * 1.10;
WHERE id_trabajador IN (
    SELECT DISTINCT a.id_trabajador
        FROM alquila a
        WHERE a.fecha_entrega IS NULL
    );

-- Consulta 10: Elimina de la tabla 'resumen_alquileres_cliente' (creada en el
-- ejercicio 8) los registros de aquellos clientes que tengan solo
-- 1 alquiler registrado en la tabla 'alquila'.

DELETE FROM resumen_alquileres_cliente
WHERE id_cliente IN (
    SELECT id_cliente
    FROM alquila
    GROUP BY id_cliente
    HAVING COUNT(*) = 1
);

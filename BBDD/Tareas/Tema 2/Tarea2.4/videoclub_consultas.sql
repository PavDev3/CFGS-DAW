-- ######################################################################
-- EJERCICIOS DE CONSULTAS - VIDEOCLUB (MariaDB)
-- ######################################################################

USE videoclub;

-- Consulta 1: Muestra el titulo de cada pelicula junto con el nombre de su director
-- y el numero total de ejemplares que tiene. Ordena el resultado
-- por numero de ejemplares de mayor a menor.

SELECT p.titulo, d.nombre AS director, COUNT(e.id_ejemplar) AS total_ejemplares
FROM pelicula p
INNER JOIN director d ON p.id_director = d.id_director
INNER JOIN ejemplar e ON e.id_pelicula = p.id_pelicula
GROUP BY p.id_pelicula, p.titulo, d.nombre
ORDER BY total_ejemplares DESC;

-- Consulta 2: Lista todos los clientes junto con la fecha de recogida y el titulo
-- de la pelicula que alquilaron y la fecha de recogida sea null.

SELECT c.nombre, a.fecha_recogida, p.titulo
FROM cliente c
JOIN alquila a ON c.id_cliente = a.id_cliente
JOIN ejemplar e ON a.id_ejemplar = e.id_ejemplar
JOIN pelicula p ON e.id_pelicula = p.id_pelicula
WHERE a.fecha_entrega IS NULL

-- Consulta 3: Muestra todas las categorias y, para cada una, el numero de peliculas
-- que pertenecen a ella. Las categorias sin peliculas no deben aparecer.

SELECT c.nombre, COUNT(p.id_pelicula) AS num_peliculas
FROM videoclub.categoria c
JOIN videoclub.pertenece pe ON c.id_categoria = pe.id_categoria
JOIN videoclub.pelicula p ON pe.id_pelicula = p.id_pelicula
GROUP BY c.nombre
HAVING COUNT(p.id_pelicula) > 0;

-- Consulta 4: Obtiene el nombre y la ciudad de los clientes que han alquilado alguna
-- pelicula del genero 'Ciencia Ficción'.

SELECT c.nombre, c.ciudad
FROM cliente c
INNER JOIN alquila a ON c.id_cliente = a.id_cliente
INNER JOIN ejemplar e ON a.id_ejemplar = e.id_ejemplar
INNER JOIN pelicula p ON e.id_pelicula = p.id_pelicula
WHERE p.genero = 'Ciencia Ficción';

-- Consulta 5: Media de salario de los trabajadores 
SELECT AVG(salario) AS media_salarios
FROM trabajador;

-- Consulta 6: Numero de alquileres gestionados por cada trabajador.
SELECT t.nombre, COUNT(a.id_trabajador) AS num_alquileres
FROM trabajador t
INNER JOIN alquila a ON t.id_trabajador = a.id_trabajador
GROUP BY t.id_trabajador, t.nombre;

-- Consulta 7: Titulos de las peliculas sin alquilar.
SELECT p.titulo
FROM pelicula p
LEFT JOIN ejemplar e ON p.id_pelicula = e.id_pelicula
LEFT JOIN alquila a ON e.id_ejemplar = a.id_ejemplar
WHERE a.id_cliente IS NULL;

-- Consulta 8.

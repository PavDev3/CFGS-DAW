-- EJERCICIOS DE CONSULTAS - VIDEOCLUB (MariaDB)

USE videoclub;

--  Insertar operaciones de insercion de datos en las tablas
INSERT INTO director (nombre, nacionalidad, fecha_nacimiento) VALUES
('Christopher Nolan', 'Británico', '1970-07-30'),
('Steven Spielberg', 'Estadounidense', '1946-12-18'),
('Quentin Tarantino', 'Estadounidense', '1963-03-27'),
('Pedro Almodóvar', 'Español', '1949-09-25'),
('Martin Scorsese', 'Estadounidense', '1942-11-17'),
('Ridley Scott', 'Británico', '1937-11-30'),
('Alejandro Amenábar', 'Español', '1972-03-31'),
('Denis Villeneuve', 'Canadiense', '1967-10-03'),
('Greta Gerwig', 'Estadounidense', '1983-08-04'),
('Bong Joon-ho', 'Surcoreano', '1969-09-14');

INSERT INTO cliente (nombre, email, ciudad, calle, numero) VALUES
('María García López', 'maria.garcia@email.com', 'Madrid', 'Calle Gran Vía', '45'),
('Juan Pérez Martínez', 'juan.perez@email.com', 'Barcelona', 'Avenida Diagonal', '123'),
('Ana Rodríguez Sánchez', 'ana.rodriguez@email.com', 'Valencia', 'Plaza del Ayuntamiento', '8'),
('Carlos Fernández Torres', 'carlos.fernandez@email.com', 'Sevilla', 'Calle Sierpes', '23'),
('Laura Martínez Ruiz', 'laura.martinez@email.com', 'Bilbao', 'Gran Vía', '67'),
('Miguel López González', 'miguel.lopez@email.com', 'Málaga', 'Calle Larios', '12'),
('Sofía Hernández Díaz', 'sofia.hernandez@email.com', 'Zaragoza', 'Paseo Independencia', '34'),
('David Moreno Jiménez', 'david.moreno@email.com', 'Murcia', 'Plaza de las Flores', '5'),
('Elena Castro Ruiz', 'elena.castro@email.com', 'Palma', 'Paseo del Borne', '18'),
('Pablo Sánchez García', 'pablo.sanchez@email.com', 'Las Palmas', 'Calle Triana', '56');

-- Actualizaciones de datos en las tablas
UPDATE cliente SET email = 'maria.garcia@email.com' WHERE id_cliente = 1;
UPDATE cliente SET email = 'juan.perez@email.com' WHERE id_cliente = 2;
UPDATE cliente SET email = 'ana.rodriguez@email.com' WHERE id_cliente = 3;
UPDATE cliente SET email = 'carlos.fernandez@email.com' WHERE id_cliente = 4;
UPDATE cliente SET email = 'laura.martinez@email.com' WHERE id_cliente = 5;
UPDATE cliente SET email = 'miguel.lopez@email.com' WHERE id_cliente = 6;
UPDATE cliente SET email = 'sofia.hernandez@email.com' WHERE id_cliente = 7;

-- Eliminacion de borrado de registro posible de la tabla peliculas sin problema de constraint
-- Película 7 ("Los Otros") no tiene ejemplares alquilados (13 y 14 no aparecen en alquila),
-- así que podemos borrar sus ejemplares primero y luego la película.
DELETE FROM ejemplar WHERE id_pelicula = 7;
DELETE FROM pelicula WHERE id_pelicula = 7;


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

-- Consulta 8. Ejemplares junto con el titulo de la pelicula y el nombre del cliente que los ha alquilado.
SELECT e.id_ejemplar, p.titulo, c.nombre AS cliente
FROM ejemplar e
INNER JOIN pelicula p ON e.id_pelicula = p.id_pelicula
INNER JOIN alquila a ON e.id_ejemplar = a.id_ejemplar
INNER JOIN cliente c ON a.id_cliente = c.id_cliente;

-- Consulta 9: Para cada director, muestra su nombre, nacionalidad, número de películas
-- dirigidas y el stock total de ejemplares.


SELECT d.nombre AS director, d.nacionalidad,
       COUNT(DISTINCT p.id_pelicula) AS num_peliculas,
       SUM(e.stock) AS stock_total
FROM director d
INNER JOIN pelicula p ON d.id_director = p.id_director
INNER JOIN ejemplar e ON p.id_pelicula = e.id_pelicula
GROUP BY d.id_director, d.nombre, d.nacionalidad
HAVING stock_total > (
    SELECT AVG(stock_dir)
    FROM (
        SELECT SUM(e2.stock) AS stock_dir
        FROM pelicula p2
        INNER JOIN ejemplar e2 ON p2.id_pelicula = e2.id_pelicula
        GROUP BY p2.id_director
    ) AS media_stock
);

-- Consulta 10: Muestra todas las categorías junto con el número de películas que tienen

SELECT cat.nombre AS categoria,
       COUNT(pe.id_pelicula) AS num_peliculas
FROM pertenece pe
RIGHT JOIN categoria cat ON pe.id_categoria = cat.id_categoria
GROUP BY cat.id_categoria, cat.nombre
ORDER BY num_peliculas DESC;


-- Vista que muestra el titulo de la pelicula, el nombre del director y el numero de ejemplares que tiene.
CREATE VIEW vista_peliculas_directores AS
SELECT p.titulo, d.nombre AS director, COUNT(e.id_ejemplar) AS total_ejemplares
FROM pelicula p
INNER JOIN director d ON p.id_director = d.id_director
INNER JOIN ejemplar e ON e.id_pelicula = p.id_pelicula
GROUP BY p.id_pelicula, p.titulo, d.nombre
ORDER BY total_ejemplares DESC;

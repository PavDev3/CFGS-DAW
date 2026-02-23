-- TAREA EXAMEN - VIDEOCLUB (MariaDB)
-- Archivo unificado: Estructura + Datos + Operaciones + Consultas + Vista
-- ENGINE=InnoDB: Usamos el motor InnoDB para las tablas y poder usar foreingkeys

-- Crear la base de datos
DROP DATABASE IF EXISTS videoclub;
CREATE DATABASE videoclub;
USE videoclub;

-- TABLAS PRINCIPALES (ENTIDADES)

-- 1. Tabla Director
CREATE TABLE director (
    id_director INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE
) ENGINE=InnoDB;

-- 2. Tabla Cliente
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    ciudad VARCHAR(50),
    calle VARCHAR(100),
    numero VARCHAR(20)
) ENGINE=InnoDB;

-- 3. Tabla Telefono_Cliente (para teléfono multivalorado)
CREATE TABLE telefono_cliente (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
) ENGINE=InnoDB;

-- 4. Tabla Categoría
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
) ENGINE=InnoDB;

-- 5. Tabla Proveedor
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20)
) ENGINE=InnoDB;

-- 6. Tabla Trabajador
CREATE TABLE trabajador (
    id_trabajador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    salario DECIMAL(10, 2)
    -- Nota: antigüedad es un atributo derivado, se calcula a partir de fecha_contratacion
);

-- 7. Tabla Pelicula
CREATE TABLE pelicula (
    id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    año_lanzamiento INT,
    genero VARCHAR(50),
    clasificacion VARCHAR(20),
    id_proveedor INT NOT NULL,
    id_director INT NOT NULL,
    CONSTRAINT fk_pel_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
        ON DELETE RESTRICT,
    CONSTRAINT fk_pel_director FOREIGN KEY (id_director) REFERENCES director(id_director)
        ON DELETE RESTRICT
) ENGINE=InnoDB;

-- 8. Tabla Ejemplar
CREATE TABLE ejemplar (
    id_ejemplar INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50),
    stock INT DEFAULT 0,
    id_pelicula INT NOT NULL,
    CONSTRAINT fk_ej_pelicula FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
        ON DELETE RESTRICT
);

-- TABLAS DE RELACIÓN

-- 9. Tabla dirige (Relación N:M entre Director y Pelicula)
-- Cardinalidad: Director (1,n) - Pelicula (1,1)
CREATE TABLE dirige (
    id_director INT NOT NULL,
    id_pelicula INT NOT NULL,
    PRIMARY KEY (id_director, id_pelicula),
    CONSTRAINT fk_dir_director FOREIGN KEY (id_director) REFERENCES director(id_director),
    CONSTRAINT fk_dir_pelicula FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
);

-- 10. Tabla alquila (Relación N:M entre Cliente y Ejemplar con atributos)
-- Cardinalidad: Cliente (0,n) - Ejemplar (0,n)
CREATE TABLE alquila (
    id_cliente INT NOT NULL,
    id_ejemplar INT NOT NULL,
    fecha_recogida DATE NOT NULL,
    fecha_entrega DATE,
    id_trabajador INT NOT NULL,
    PRIMARY KEY (id_cliente, id_ejemplar, fecha_recogida),
    CONSTRAINT fk_alq_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT fk_alq_ejemplar FOREIGN KEY (id_ejemplar) REFERENCES ejemplar(id_ejemplar)
        ON DELETE RESTRICT,
    CONSTRAINT fk_alq_trabajador FOREIGN KEY (id_trabajador) REFERENCES trabajador(id_trabajador)
        ON DELETE RESTRICT
);

-- 11. Tabla pertenece (Relación N:M entre Pelicula y Categoría)
-- Cardinalidad: Pelicula (1,n) - Categoría (1,n)
CREATE TABLE pertenece (
    id_pelicula INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_pelicula, id_categoria),
    CONSTRAINT fk_per_pelicula FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula),
    CONSTRAINT fk_per_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);


-- 2. MODIFICACIÓN DE TABLAS CON ALTER TABLE
-- Agregar restricciones CASCADE a las claves foráneas

ALTER TABLE telefono_cliente
ADD CONSTRAINT fk_telefono_cliente_cliente
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Modificar tabla pelicula: agregar ON UPDATE CASCADE
ALTER TABLE pelicula
DROP FOREIGN KEY fk_pel_proveedor,
DROP FOREIGN KEY fk_pel_director;

ALTER TABLE pelicula
ADD CONSTRAINT fk_pelicula_proveedor
FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_pelicula_director
FOREIGN KEY (id_director) REFERENCES director(id_director)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Modificar tabla ejemplar: agregar ON UPDATE CASCADE
ALTER TABLE ejemplar
DROP FOREIGN KEY fk_ej_pelicula;

ALTER TABLE ejemplar
ADD CONSTRAINT fk_ejemplar_pelicula
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

-- Modificar tabla dirige: agregar CASCADE a ambas claves foráneas
ALTER TABLE dirige
DROP FOREIGN KEY fk_dir_director,
DROP FOREIGN KEY fk_dir_pelicula;

ALTER TABLE dirige
ADD CONSTRAINT fk_dirige_director
FOREIGN KEY (id_director) REFERENCES director(id_director)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_dirige_pelicula
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Modificar tabla alquila: agregar CASCADE a las claves foráneas correspondientes
ALTER TABLE alquila
DROP FOREIGN KEY fk_alq_cliente,
DROP FOREIGN KEY fk_alq_ejemplar,
DROP FOREIGN KEY fk_alq_trabajador;

ALTER TABLE alquila
ADD CONSTRAINT fk_alquila_cliente
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_alquila_ejemplar
FOREIGN KEY (id_ejemplar) REFERENCES ejemplar(id_ejemplar)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_alquila_trabajador
FOREIGN KEY (id_trabajador) REFERENCES trabajador(id_trabajador)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

-- Modificar tabla pertenece: agregar CASCADE a ambas claves foráneas
ALTER TABLE pertenece
DROP FOREIGN KEY fk_per_pelicula,
DROP FOREIGN KEY fk_per_categoria;

ALTER TABLE pertenece
ADD CONSTRAINT fk_pertenece_pelicula
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_pertenece_categoria
FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- 3. INSERCIÓN DE DATOS DE EJEMPLO
-- 10 registros por cada tabla

-- 1. Insertar Directores
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

-- 2. Insertar Clientes
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

-- 3. Insertar Categorías
INSERT INTO categoria (nombre, descripcion) VALUES
('Acción', 'Películas con escenas de acción, persecuciones y combates'),
('Comedia', 'Películas cómicas y de humor'),
('Drama', 'Películas dramáticas con contenido emocional'),
('Ciencia Ficción', 'Películas de ciencia ficción y futuristas'),
('Terror', 'Películas de terror y suspense'),
('Romance', 'Películas románticas y de amor'),
('Aventura', 'Películas de aventuras y exploración'),
('Thriller', 'Películas de suspense y thriller psicológico'),
('Animación', 'Películas de animación para todas las edades'),
('Documental', 'Documentales y películas basadas en hechos reales');

-- 4. Insertar Proveedores
INSERT INTO proveedor (nombre, direccion, telefono) VALUES
('Distribuidora Cinematográfica S.A.', 'Calle Industria 15, Madrid', '912345678'),
('Films Internacionales', 'Avenida del Cine 200, Barcelona', '934567890'),
('Video Distribuciones', 'Plaza del Cine 5, Valencia', '961234567'),
('Cinema Supply Co.', 'Calle Película 88, Sevilla', '954321098'),
('Movie Distributors Ltd.', 'Paseo del Cine 12, Bilbao', '944567123'),
('Film Express', 'Avenida Cinema 45, Málaga', '952345678'),
('Global Films', 'Calle Distribución 30, Zaragoza', '976543210'),
('Cinema World', 'Plaza Films 7, Murcia', '968765432'),
('Movie Masters', 'Paseo Distribución 22, Palma', '971234567'),
('Film Network', 'Avenida Supply 100, Las Palmas', '928765432');

-- 5. Insertar Trabajadores
INSERT INTO trabajador (nombre, fecha_contratacion, salario) VALUES
('Roberto Martínez', '2020-01-15', 2500.00),
('Carmen López', '2019-03-20', 2800.00),
('Javier Sánchez', '2021-06-10', 2300.00),
('Isabel García', '2018-09-05', 3000.00),
('Manuel Ruiz', '2022-02-14', 2200.00),
('Patricia Fernández', '2020-11-30', 2600.00),
('Antonio Moreno', '2019-07-18', 2700.00),
('Lucía Torres', '2021-04-22', 2400.00),
('Francisco Jiménez', '2020-08-12', 2500.00),
('Marta Díaz', '2022-01-08', 2350.00);

-- 6. Insertar Teléfonos de Clientes (multivalorado)
INSERT INTO telefono_cliente (id_cliente, telefono) VALUES
(1, '612345678'),
(1, '912345678'),
(2, '623456789'),
(3, '634567890'),
(4, '645678901'),
(5, '656789012'),
(6, '667890123'),
(7, '678901234'),
(8, '689012345'),
(9, '690123456'),
(10, '601234567');

-- 7. Insertar Películas
INSERT INTO pelicula (titulo, año_lanzamiento, genero, clasificacion, id_proveedor, id_director) VALUES
('Inception', 2010, 'Ciencia Ficción', 'PG-13', 1, 1),
('El Padrino', 1972, 'Drama', 'R', 2, 2),
('Pulp Fiction', 1994, 'Crimen', 'R', 3, 3),
('Volver', 2006, 'Drama', 'R', 4, 4),
('Taxi Driver', 1976, 'Drama', 'R', 5, 5),
('Blade Runner', 1982, 'Ciencia Ficción', 'R', 6, 6),
('Los Otros', 2001, 'Terror', 'PG-13', 7, 7),
('Arrival', 2016, 'Ciencia Ficción', 'PG-13', 8, 8),
('Lady Bird', 2017, 'Drama', 'R', 9, 9),
('Parásitos', 2019, 'Thriller', 'R', 10, 10);

-- 8. Insertar Ejemplares
INSERT INTO ejemplar (estado, stock, id_pelicula) VALUES
('Disponible', 5, 1),
('Alquilado', 0, 1),
('Disponible', 3, 2),
('Disponible', 4, 2),
('Alquilado', 0, 3),
('Disponible', 6, 3),
('Disponible', 2, 4),
('Alquilado', 0, 4),
('Disponible', 5, 5),
('Disponible', 3, 5),
('Disponible', 4, 6),
('Alquilado', 0, 6),
('Disponible', 7, 7),
('Disponible', 3, 7),
('Alquilado', 0, 8),
('Disponible', 5, 8),
('Disponible', 4, 9),
('Alquilado', 0, 9),
('Disponible', 6, 10),
('Disponible', 2, 10);

-- 9. Insertar Relación dirige (Director - Película)
INSERT INTO dirige (id_director, id_pelicula) VALUES
(1, 1),  -- Christopher Nolan - Inception
(2, 2),  -- Steven Spielberg - El Padrino (aunque realmente es Coppola, es ejemplo)
(3, 3),  -- Quentin Tarantino - Pulp Fiction
(4, 4),  -- Pedro Almodóvar - Volver
(5, 5),  -- Martin Scorsese - Taxi Driver
(6, 6),  -- Ridley Scott - Blade Runner
(7, 7),  -- Alejandro Amenábar - Los Otros
(8, 8),  -- Denis Villeneuve - Arrival
(9, 9),  -- Greta Gerwig - Lady Bird
(10, 10); -- Bong Joon-ho - Parásitos

-- 10. Insertar Relación alquila (Cliente - Ejemplar - Trabajador)
INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador) VALUES
(1, 2, '2024-01-15', '2024-01-18', 1),
(2, 5, '2024-01-20', '2024-01-23', 2),
(3, 8, '2024-02-01', '2024-02-04', 3),
(4, 12, '2024-02-10', '2024-02-13', 4),
(5, 15, '2024-02-15', '2024-02-18', 5),
(6, 18, '2024-02-20', NULL, 6),  -- Alquiler activo (sin fecha de entrega)
(7, 1, '2024-03-01', '2024-03-04', 7),
(8, 3, '2024-03-05', '2024-03-08', 8),
(9, 6, '2024-03-10', '2024-03-13', 9),
(10, 9, '2024-03-15', NULL, 10);  -- Alquiler activo (sin fecha de entrega)

-- 11. Insertar Relación pertenece (Película - Categoría)
INSERT INTO pertenece (id_pelicula, id_categoria) VALUES
(1, 4),  -- Inception - Ciencia Ficción
(1, 8),  -- Inception - Thriller
(2, 3),  -- El Padrino - Drama
(2, 8),  -- El Padrino - Thriller
(3, 3),  -- Pulp Fiction - Drama
(3, 8),  -- Pulp Fiction - Thriller
(4, 3),  -- Volver - Drama
(4, 6),  -- Volver - Romance
(5, 3),  -- Taxi Driver - Drama
(5, 8),  -- Taxi Driver - Thriller
(6, 4),  -- Blade Runner - Ciencia Ficción
(6, 8),  -- Blade Runner - Thriller
(7, 5),  -- Los Otros - Terror
(7, 8),  -- Los Otros - Thriller
(8, 4),  -- Arrival - Ciencia Ficción
(8, 3),  -- Arrival - Drama
(9, 3),  -- Lady Bird - Drama
(9, 2),  -- Lady Bird - Comedia
(10, 8), -- Parásitos - Thriller
(10, 3); -- Parásitos - Drama

-- Tablas principales:
-- - director: 10 registros
-- - cliente: 10 registros
-- - categoria: 10 registros
-- - proveedor: 10 registros
-- - trabajador: 10 registros
-- - pelicula: 10 registros
-- - ejemplar: 20 registros (2 por película)
--
-- Tablas de relación:
-- - telefono_cliente: 11 registros (algunos clientes tienen múltiples teléfonos)
-- - dirige: 10 registros
-- - alquila: 10 registros
-- - pertenece: 20 registros (2 categorías por película en promedio)
--
-- Total: 131 registros insertados


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
WHERE a.fecha_entrega IS NULL;

-- Consulta 3: Muestra todas las categorias y, para cada una, el numero de peliculas
-- que pertenecen a ella. Las categorias sin peliculas no deben aparecer.

SELECT c.nombre, COUNT(p.id_pelicula) AS num_peliculas
FROM categoria c
JOIN pertenece pe ON c.id_categoria = pe.id_categoria
JOIN pelicula p ON pe.id_pelicula = p.id_pelicula
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

-- Consulta 8: Ejemplares junto con el titulo de la pelicula y el nombre del cliente que los ha alquilado.
SELECT e.id_ejemplar, p.titulo, c.nombre AS cliente
FROM ejemplar e
INNER JOIN pelicula p ON e.id_pelicula = p.id_pelicula
INNER JOIN alquila a ON e.id_ejemplar = a.id_ejemplar
INNER JOIN cliente c ON a.id_cliente = c.id_cliente;

-- Consulta 9: Para cada director, muestra su nombre, nacionalidad, número de películas
-- dirigidas y el stock total de ejemplares. Solo incluye directores cuyo stock total

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

-- Consulta 10: Muestra todas las categorías junto con el número de películas que tienen,
-- incluyendo las categorías sin películas asignadas gracias al RIGHT JOIN.

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

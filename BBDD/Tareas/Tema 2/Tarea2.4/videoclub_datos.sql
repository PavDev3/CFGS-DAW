-- ######################################################################
-- INSERCIÓN DE DATOS DE EJEMPLO - VIDEOCLUB
-- 10 registros por cada tabla
-- ######################################################################

USE videoclub;

-- ######################################################################
-- TABLAS PRINCIPALES (sin dependencias)
-- ######################################################################

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

-- ######################################################################
-- TABLAS CON DEPENDENCIAS SIMPLES
-- ######################################################################

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
INSERT INTO pelicula (titulo, año_lanzamiento, genero, clasificacion, id_proveedor) VALUES
('Inception', 2010, 'Ciencia Ficción', 'PG-13', 1),
('El Padrino', 1972, 'Drama', 'R', 2),
('Pulp Fiction', 1994, 'Crimen', 'R', 3),
('Volver', 2006, 'Drama', 'R', 4),
('Taxi Driver', 1976, 'Drama', 'R', 5),
('Blade Runner', 1982, 'Ciencia Ficción', 'R', 6),
('Los Otros', 2001, 'Terror', 'PG-13', 7),
('Arrival', 2016, 'Ciencia Ficción', 'PG-13', 8),
('Lady Bird', 2017, 'Drama', 'R', 9),
('Parásitos', 2019, 'Thriller', 'R', 10);

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

-- ######################################################################
-- TABLAS DE RELACIÓN
-- ######################################################################

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

-- ######################################################################
-- RESUMEN DE INSERCIONES
-- ######################################################################
-- 
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


DROP DATABASE IF EXISTS videoclub_Trabajo;
CREATE DATABASE videoclub_Trabajo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE videoclub_Trabajo;

CREATE TABLE director (
    id_director      INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    nacionalidad     VARCHAR(50),
    fecha_nacimiento DATE
) ENGINE=InnoDB;

CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    email      VARCHAR(100),
    ciudad     VARCHAR(50),
    calle      VARCHAR(100),
    numero     VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE telefono_cliente (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente  INT NOT NULL,
    telefono    VARCHAR(20) NOT NULL,
    CONSTRAINT fk_tel_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    descripcion  TEXT
) ENGINE=InnoDB;

CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    direccion    VARCHAR(200),
    telefono     VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE trabajador (
    id_trabajador      INT AUTO_INCREMENT PRIMARY KEY,
    nombre             VARCHAR(100) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    salario            DECIMAL(10,2)
) ENGINE=InnoDB;

CREATE TABLE pelicula (
    id_pelicula      INT AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(200) NOT NULL,
    anno_lanzamiento INT,
    genero           VARCHAR(50),
    clasificacion    VARCHAR(20),
    precio_dia       DECIMAL(5,2) NOT NULL DEFAULT 2.00,
    id_proveedor     INT NOT NULL,
    id_director      INT NOT NULL,
    CONSTRAINT fk_pel_proveedor FOREIGN KEY (id_proveedor)
        REFERENCES proveedor(id_proveedor) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_pel_director FOREIGN KEY (id_director)
        REFERENCES director(id_director) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE ejemplar (
    id_ejemplar INT AUTO_INCREMENT PRIMARY KEY,
    estado      VARCHAR(20) DEFAULT 'Disponible',
    stock       INT DEFAULT 0,
    id_pelicula INT NOT NULL,
    CONSTRAINT fk_ej_pelicula FOREIGN KEY (id_pelicula)
        REFERENCES pelicula(id_pelicula) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE dirige (
    id_director INT NOT NULL,
    id_pelicula INT NOT NULL,
    PRIMARY KEY (id_director, id_pelicula),
    CONSTRAINT fk_dirige_director FOREIGN KEY (id_director)
        REFERENCES director(id_director) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_dirige_pelicula FOREIGN KEY (id_pelicula)
        REFERENCES pelicula(id_pelicula) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE alquila (
    id_cliente     INT NOT NULL,
    id_ejemplar    INT NOT NULL,
    fecha_recogida DATE NOT NULL,
    fecha_entrega  DATE,
    id_trabajador  INT NOT NULL,
    PRIMARY KEY (id_cliente, id_ejemplar, fecha_recogida),
    CONSTRAINT fk_alq_cliente FOREIGN KEY (id_cliente)
        REFERENCES cliente(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alq_ejemplar FOREIGN KEY (id_ejemplar)
        REFERENCES ejemplar(id_ejemplar) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_alq_trabajador FOREIGN KEY (id_trabajador)
        REFERENCES trabajador(id_trabajador) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE pertenece (
    id_pelicula  INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_pelicula, id_categoria),
    CONSTRAINT fk_per_pelicula FOREIGN KEY (id_pelicula)
        REFERENCES pelicula(id_pelicula) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_per_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria(id_categoria) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE penalizacion (
    id_penalizacion INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente      INT NOT NULL,
    id_ejemplar     INT NOT NULL,
    dias_retraso    INT NOT NULL,
    fecha_deteccion DATE NOT NULL,
    CONSTRAINT fk_pen_cliente  FOREIGN KEY (id_cliente)  REFERENCES cliente(id_cliente),
    CONSTRAINT fk_pen_ejemplar FOREIGN KEY (id_ejemplar) REFERENCES ejemplar(id_ejemplar)
) ENGINE=InnoDB;

CREATE TABLE ranking_peliculas (
    posicion         INT PRIMARY KEY,
    id_pelicula      INT NOT NULL,
    titulo           VARCHAR(200),
    total_alquileres INT DEFAULT 0,
    ingresos_totales DECIMAL(10,2) DEFAULT 0.00,
    fecha_calculo    DATE NOT NULL,
    CONSTRAINT fk_rank_pelicula FOREIGN KEY (id_pelicula)
        REFERENCES pelicula(id_pelicula) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE dashboard (
    id_metrica    INT AUTO_INCREMENT PRIMARY KEY,
    metrica       VARCHAR(100) NOT NULL,
    valor         VARCHAR(255) NOT NULL,
    fecha_calculo DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

INSERT INTO director (nombre, nacionalidad, fecha_nacimiento) VALUES
('Christopher Nolan',   'Britanico',      '1970-07-30'),
('Steven Spielberg',    'Estadounidense',  '1946-12-18'),
('Quentin Tarantino',   'Estadounidense',  '1963-03-27'),
('Pedro Almodovar',     'Espanol',         '1949-09-25'),
('Martin Scorsese',     'Estadounidense',  '1942-11-17'),
('Ridley Scott',        'Britanico',       '1937-11-30'),
('Alejandro Amenabar',  'Espanol',         '1972-03-31'),
('Denis Villeneuve',    'Canadiense',      '1967-10-03'),
('Greta Gerwig',        'Estadounidense',  '1983-08-04'),
('Bong Joon-ho',        'Surcoreano',      '1969-09-14');

INSERT INTO cliente (nombre, email, ciudad, calle, numero) VALUES
('Maria Garcia Lopez',      'maria.garcia@email.com',    'Madrid',      'Calle Gran Via',           '45'),
('Juan Perez Martinez',     'juan.perez@email.com',      'Barcelona',   'Avenida Diagonal',         '123'),
('Ana Rodriguez Sanchez',   'ana.rodriguez@email.com',   'Valencia',    'Plaza del Ayuntamiento',   '8'),
('Carlos Fernandez Torres', 'carlos.fernandez@email.com','Sevilla',     'Calle Sierpes',            '23'),
('Laura Martinez Ruiz',     'laura.martinez@email.com',  'Bilbao',      'Gran Via',                 '67'),
('Miguel Lopez Gonzalez',   'miguel.lopez@email.com',    'Malaga',      'Calle Larios',             '12'),
('Sofia Hernandez Diaz',    'sofia.hernandez@email.com', 'Zaragoza',    'Paseo Independencia',      '34'),
('David Moreno Jimenez',    'david.moreno@email.com',    'Murcia',      'Plaza de las Flores',      '5'),
('Elena Castro Ruiz',       'elena.castro@email.com',    'Palma',       'Paseo del Borne',          '18'),
('Pablo Sanchez Garcia',    'pablo.sanchez@email.com',   'Las Palmas',  'Calle Triana',             '56');

INSERT INTO telefono_cliente (id_cliente, telefono) VALUES
(1, '612345678'), (1, '912345678'),
(2, '623456789'), (2, '934000111'),
(3, '634567890'),
(4, '645678901'),
(5, '656789012'),
(6, '667890123'),
(7, '678901234'),
(8, '689012345'),
(9, '690123456'),
(10,'601234567');

INSERT INTO categoria (nombre, descripcion) VALUES
('Accion',         'Peliculas con escenas de accion, persecuciones y combates'),
('Comedia',        'Peliculas comicas y de humor'),
('Drama',          'Peliculas dramaticas con contenido emocional'),
('Ciencia Ficcion','Peliculas de ciencia ficcion y futuristas'),
('Terror',         'Peliculas de terror y suspense'),
('Romance',        'Peliculas romanticas y de amor'),
('Aventura',       'Peliculas de aventuras y exploracion'),
('Thriller',       'Peliculas de suspense y thriller psicologico'),
('Animacion',      'Peliculas de animacion para todas las edades'),
('Documental',     'Documentales y peliculas basadas en hechos reales');

INSERT INTO proveedor (nombre, direccion, telefono) VALUES
('Distribuidora Cinematografica S.A.', 'Calle Industria 15, Madrid',      '912345678'),
('Films Internacionales',              'Avenida del Cine 200, Barcelona',  '934567890'),
('Video Distribuciones',               'Plaza del Cine 5, Valencia',       '961234567'),
('Cinema Supply Co.',                  'Calle Pelicula 88, Sevilla',       '954321098'),
('Movie Distributors Ltd.',            'Paseo del Cine 12, Bilbao',        '944567123'),
('Film Express',                       'Avenida Cinema 45, Malaga',        '952345678'),
('Global Films',                       'Calle Distribucion 30, Zaragoza',  '976543210'),
('Cinema World',                       'Plaza Films 7, Murcia',            '968765432'),
('Movie Masters',                      'Paseo Distribucion 22, Palma',     '971234567'),
('Film Network',                       'Avenida Supply 100, Las Palmas',   '928765432');

INSERT INTO trabajador (nombre, fecha_contratacion, salario) VALUES
('Roberto Martinez',   '2020-01-15', 2500.00),
('Carmen Lopez',       '2019-03-20', 2800.00),
('Javier Sanchez',     '2021-06-10', 2300.00),
('Isabel Garcia',      '2018-09-05', 3000.00),
('Manuel Ruiz',        '2022-02-14', 2200.00),
('Patricia Fernandez', '2020-11-30', 2600.00),
('Antonio Moreno',     '2019-07-18', 2700.00),
('Lucia Torres',       '2021-04-22', 2400.00),
('Francisco Jimenez',  '2020-08-12', 2500.00),
('Marta Diaz',         '2022-01-08', 2350.00);

INSERT INTO pelicula (titulo, anno_lanzamiento, genero, clasificacion, precio_dia, id_proveedor, id_director) VALUES
('Inception',    2010, 'Ciencia Ficcion', 'PG-13', 2.00,  1,  1),
('El Padrino',   1972, 'Drama',           'R',     2.50,  2,  2),
('Pulp Fiction', 1994, 'Crimen',          'R',     2.50,  3,  3),
('Volver',       2006, 'Drama',           'R',     2.50,  4,  4),
('Taxi Driver',  1976, 'Drama',           'R',     2.50,  5,  5),
('Blade Runner', 1982, 'Ciencia Ficcion', 'R',     2.50,  6,  6),
('Los Otros',    2001, 'Terror',          'PG-13', 2.00,  7,  7),
('Arrival',      2016, 'Ciencia Ficcion', 'PG-13', 2.00,  8,  8),
('Lady Bird',    2017, 'Drama',           'R',     2.50,  9,  9),
('Parasitos',    2019, 'Thriller',        'R',     2.50,  10, 10);

INSERT INTO ejemplar (estado, stock, id_pelicula) VALUES
('Disponible', 3, 1),
('Disponible', 2, 1),
('Disponible', 4, 2),
('Disponible', 2, 2),
('Disponible', 5, 3),
('Disponible', 3, 3),
('Disponible', 2, 4),
('Disponible', 1, 4),
('Disponible', 4, 5),
('Disponible', 2, 5),
('Disponible', 3, 6),
('Disponible', 2, 6),
('Disponible', 6, 7),
('Disponible', 3, 7),
('Disponible', 4, 8),
('Disponible', 2, 8),
('Disponible', 3, 9),
('Disponible', 1, 9),
('Disponible', 5, 10),
('Disponible', 3, 10);

INSERT INTO dirige (id_director, id_pelicula) VALUES
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10);

INSERT INTO pertenece (id_pelicula, id_categoria) VALUES
(1,4),(1,8),
(2,3),(2,8),
(3,3),(3,8),
(4,3),(4,6),
(5,3),(5,8),
(6,4),(6,8),
(7,5),(7,8),
(8,4),(8,3),
(9,3),(9,2),
(10,8),(10,3);

INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador) VALUES
(1,  1,  '2023-01-05', '2023-01-08', 1),
(2,  3,  '2023-01-10', '2023-01-13', 2),
(3,  5,  '2023-01-15', '2023-01-18', 3),
(1,  7,  '2023-01-20', '2023-01-22', 4),
(10, 9,  '2023-01-25', '2023-01-28', 5),
(2,  11, '2023-02-01', '2023-02-04', 6),
(4,  13, '2023-02-08', '2023-02-11', 7),
(1,  15, '2023-02-14', '2023-02-17', 8),
(10, 17, '2023-02-20', '2023-02-23', 9),
(5,  19, '2023-02-25', '2023-02-28', 10),
(2,  1,  '2023-03-01', '2023-03-05', 1),
(1,  3,  '2023-03-10', '2023-03-14', 2),
(7,  5,  '2023-03-15', '2023-03-18', 3),
(10, 7,  '2023-03-20', '2023-03-24', 4),
(3,  9,  '2023-03-25', '2023-03-28', 5),
(1,  11, '2023-04-02', '2023-04-06', 6),
(2,  13, '2023-04-10', '2023-04-14', 7),
(9,  15, '2023-04-18', '2023-04-21', 8),
(10, 17, '2023-04-22', '2023-04-25', 9),
(6,  19, '2023-04-28', '2023-05-01', 10),
(1,  1,  '2023-05-05', '2023-05-08', 1),
(2,  5,  '2023-05-12', '2023-05-15', 2),
(4,  9,  '2023-06-01', '2023-06-04', 3),
(10, 3,  '2023-06-10', '2023-06-14', 4),
(1,  13, '2023-06-20', '2023-06-23', 5),
(2,  7,  '2023-07-01', '2023-07-05', 6),
(10, 11, '2023-07-15', '2023-07-19', 7),
(1,  15, '2023-08-01', '2023-08-04', 8),
(5,  3,  '2023-08-10', '2023-08-13', 9),
(3,  19, '2023-08-20', '2023-08-24', 10),
(2,  1,  '2023-09-05', '2023-09-09', 1),
(1,  9,  '2023-09-15', '2023-09-18', 2),
(10, 5,  '2023-10-01', '2023-10-05', 3),
(7,  13, '2023-10-10', '2023-10-14', 4),
(9,  17, '2023-10-20', '2023-10-23', 5),
(1,  3,  '2023-11-01', '2023-11-05', 6),
(2,  11, '2023-11-10', '2023-11-14', 7),
(4,  15, '2023-11-20', '2023-11-23', 8),
(10, 19, '2023-12-01', '2023-12-05', 9),
(1,  7,  '2023-12-15', '2023-12-18', 10),
(2,  5,  '2024-01-05', '2024-01-08', 1),
(10, 1,  '2024-01-15', '2024-01-19', 2),
(3,  13, '2024-01-20', '2024-01-23', 3),
(1,  17, '2024-02-01', '2024-02-05', 4),
(5,  9,  '2024-02-10', '2024-02-13', 5),
(2,  3,  '2024-03-01', '2024-03-04', 6),
(10, 11, '2024-03-05', '2024-03-09', 7),
(7,  7,  '2024-03-10', '2024-03-13', 8);

INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador) VALUES
(4,  15, CURRENT_DATE - INTERVAL 2 DAY, NULL, 9),
(6,  19, CURRENT_DATE - INTERVAL 1 DAY, NULL, 10);

INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador) VALUES
(8,  2,  CURRENT_DATE - INTERVAL 8 DAY,  NULL, 1),
(9,  4,  CURRENT_DATE - INTERVAL 6 DAY,  NULL, 2),
(1,  6,  CURRENT_DATE - INTERVAL 10 DAY, NULL, 3);

UPDATE ejemplar SET estado = 'Alquilado', stock = 0 WHERE id_ejemplar IN (2, 4, 6, 15, 19);


DROP TRIGGER IF EXISTS trg_alquiler_nuevo;
CREATE TRIGGER trg_alquiler_nuevo
AFTER INSERT ON alquila
FOR EACH ROW
BEGIN
    UPDATE ejemplar
    SET    estado = 'Alquilado',
           stock  = 0
    WHERE  id_ejemplar = NEW.id_ejemplar;
END;
DROP TRIGGER IF EXISTS trg_devolucion_ejemplar;
CREATE TRIGGER trg_devolucion_ejemplar
AFTER UPDATE ON alquila
FOR EACH ROW
BEGIN
    IF OLD.fecha_entrega IS NULL AND NEW.fecha_entrega IS NOT NULL THEN
        UPDATE ejemplar
        SET    estado = 'Disponible',
               stock  = stock + 1
        WHERE  id_ejemplar = NEW.id_ejemplar;
    END IF;
END;
DROP PROCEDURE IF EXISTS sp_registrar_alquiler;
CREATE PROCEDURE sp_registrar_alquiler(
    IN p_id_cliente    INT,
    IN p_id_ejemplar   INT,
    IN p_id_trabajador INT
)
BEGIN
    DECLARE v_estado VARCHAR(20);
    DECLARE v_stock  INT;

    SELECT estado, stock
    INTO   v_estado, v_stock
    FROM   ejemplar
    WHERE  id_ejemplar = p_id_ejemplar;

    IF v_estado IS NULL THEN
        SELECT 'Error: el ejemplar indicado no existe.' AS mensaje;
    ELSEIF v_estado != 'Disponible' OR v_stock = 0 THEN
        SELECT CONCAT('Error: el ejemplar ', p_id_ejemplar, ' no esta disponible (estado: ', v_estado, ').') AS mensaje;
    ELSE
        START TRANSACTION;
        INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador)
        VALUES (p_id_cliente, p_id_ejemplar, CURRENT_DATE, NULL, p_id_trabajador);
        COMMIT;
        SELECT 'Alquiler registrado correctamente.' AS mensaje;
    END IF;
END;
DROP PROCEDURE IF EXISTS sp_registrar_devolucion;
CREATE PROCEDURE sp_registrar_devolucion(
    IN p_id_cliente  INT,
    IN p_id_ejemplar INT
)
BEGIN
    DECLARE v_count INT DEFAULT 0;

    SELECT COUNT(*)
    INTO   v_count
    FROM   alquila
    WHERE  id_cliente    = p_id_cliente
      AND  id_ejemplar   = p_id_ejemplar
      AND  fecha_entrega IS NULL;

    IF v_count = 0 THEN
        SELECT CONCAT('Error: no hay alquiler activo para el cliente ', p_id_cliente,
                      ' con el ejemplar ', p_id_ejemplar, '.') AS mensaje;
    ELSE
        START TRANSACTION;
        UPDATE alquila
        SET    fecha_entrega = CURRENT_DATE
        WHERE  id_cliente    = p_id_cliente
          AND  id_ejemplar   = p_id_ejemplar
          AND  fecha_entrega IS NULL;
        COMMIT;
        SELECT 'Devolucion registrada correctamente.' AS mensaje;
    END IF;
END;
DROP PROCEDURE IF EXISTS sp_aplicar_penalizacion_retrasos;
CREATE PROCEDURE sp_aplicar_penalizacion_retrasos()
BEGIN
    DECLARE v_id_cliente  INT;
    DECLARE v_id_ejemplar INT;
    DECLARE v_dias        INT;
    DECLARE v_contador    INT DEFAULT 0;
    DECLARE fin           INT DEFAULT 0;

    DECLARE cur_retrasos CURSOR FOR
        SELECT a.id_cliente,
               a.id_ejemplar,
               DATEDIFF(CURRENT_DATE, a.fecha_recogida) AS dias_retraso
        FROM   alquila a
        WHERE  a.fecha_entrega IS NULL
          AND  DATEDIFF(CURRENT_DATE, a.fecha_recogida) > 5;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur_retrasos;

    WHILE fin = 0 DO
        FETCH cur_retrasos INTO v_id_cliente, v_id_ejemplar, v_dias;
        IF fin = 0 THEN
            UPDATE ejemplar
            SET    estado = 'Retraso'
            WHERE  id_ejemplar = v_id_ejemplar;

            IF NOT EXISTS (
                SELECT 1 FROM penalizacion
                WHERE id_cliente  = v_id_cliente
                  AND id_ejemplar = v_id_ejemplar
                  AND fecha_deteccion = CURRENT_DATE
            ) THEN
                INSERT INTO penalizacion (id_cliente, id_ejemplar, dias_retraso, fecha_deteccion)
                VALUES (v_id_cliente, v_id_ejemplar, v_dias, CURRENT_DATE);
            END IF;

            SET v_contador = v_contador + 1;
        END IF;
    END WHILE;

    CLOSE cur_retrasos;

    SELECT CONCAT('Penalizaciones aplicadas: ', v_contador) AS mensaje;
END;
DROP PROCEDURE IF EXISTS sp_generar_ranking_peliculas;
CREATE PROCEDURE sp_generar_ranking_peliculas()
BEGIN
    DECLARE v_id_pelicula   INT;
    DECLARE v_titulo        VARCHAR(200);
    DECLARE v_total_alq     INT;
    DECLARE v_ingresos      DECIMAL(10,2);
    DECLARE v_posicion      INT DEFAULT 0;
    DECLARE fin             INT DEFAULT 0;

    DECLARE cur_ranking CURSOR FOR
        SELECT p.id_pelicula,
               p.titulo,
               COUNT(a.id_cliente) AS total_alquileres,
               IFNULL(SUM(
                   DATEDIFF(IFNULL(a.fecha_entrega, CURRENT_DATE), a.fecha_recogida)
                   * p.precio_dia
               ), 0) AS ingresos_totales
        FROM   pelicula p
        LEFT JOIN ejemplar ej ON ej.id_pelicula = p.id_pelicula
        LEFT JOIN alquila  a  ON a.id_ejemplar  = ej.id_ejemplar
        GROUP BY p.id_pelicula, p.titulo, p.precio_dia
        ORDER BY total_alquileres DESC, ingresos_totales DESC;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    TRUNCATE TABLE ranking_peliculas;

    OPEN cur_ranking;

    WHILE fin = 0 DO
        FETCH cur_ranking INTO v_id_pelicula, v_titulo, v_total_alq, v_ingresos;
        IF fin = 0 THEN
            SET v_posicion = v_posicion + 1;
            INSERT INTO ranking_peliculas (posicion, id_pelicula, titulo, total_alquileres, ingresos_totales, fecha_calculo)
            VALUES (v_posicion, v_id_pelicula, v_titulo, v_total_alq, v_ingresos, CURRENT_DATE);
        END IF;
    END WHILE;

    CLOSE cur_ranking;

    SELECT CONCAT('Ranking generado con ', v_posicion, ' peliculas.') AS mensaje;
END;
DROP FUNCTION IF EXISTS fn_dias_alquiler_activo;
CREATE FUNCTION fn_dias_alquiler_activo(
    p_id_cliente  INT,
    p_id_ejemplar INT
)
RETURNS INT
BEGIN
    DECLARE v_dias INT DEFAULT -1;

    SELECT DATEDIFF(CURRENT_DATE, fecha_recogida)
    INTO   v_dias
    FROM   alquila
    WHERE  id_cliente    = p_id_cliente
      AND  id_ejemplar   = p_id_ejemplar
      AND  fecha_entrega IS NULL
    LIMIT 1;

    RETURN IFNULL(v_dias, -1);
END;
DROP FUNCTION IF EXISTS fn_pelicula_mas_alquilada_categoria;
CREATE FUNCTION fn_pelicula_mas_alquilada_categoria(
    p_id_categoria INT
)
RETURNS VARCHAR(200)
BEGIN
    DECLARE v_titulo VARCHAR(200) DEFAULT 'Sin datos';

    SELECT p.titulo
    INTO   v_titulo
    FROM   pelicula  p
    JOIN   pertenece pe ON pe.id_pelicula  = p.id_pelicula
    JOIN   ejemplar  ej ON ej.id_pelicula  = p.id_pelicula
    JOIN   alquila   a  ON a.id_ejemplar   = ej.id_ejemplar
    WHERE  pe.id_categoria = p_id_categoria
    GROUP BY p.id_pelicula, p.titulo
    ORDER BY COUNT(a.id_cliente) DESC
    LIMIT 1;

    RETURN IFNULL(v_titulo, 'Sin datos');
END;
CALL sp_aplicar_penalizacion_retrasos();
CALL sp_generar_ranking_peliculas();

TRUNCATE TABLE dashboard;

INSERT INTO dashboard (metrica, valor, fecha_calculo) VALUES

('Total de alquileres historicos',
    (SELECT COUNT(*) FROM alquila), NOW()),

('Alquileres activos (sin devolver)',
    (SELECT COUNT(*) FROM alquila WHERE fecha_entrega IS NULL), NOW()),

('Total de clientes registrados',
    (SELECT COUNT(*) FROM cliente), NOW()),

('Total de peliculas en catalogo',
    (SELECT COUNT(*) FROM pelicula), NOW()),

('Ejemplares disponibles',
    (SELECT COUNT(*) FROM ejemplar WHERE estado = 'Disponible'), NOW()),

('Ejemplares en retraso',
    (SELECT COUNT(*) FROM ejemplar WHERE estado = 'Retraso'), NOW()),

('Penalizaciones detectadas hoy',
    (SELECT COUNT(*) FROM penalizacion WHERE fecha_deteccion = CURRENT_DATE), NOW()),

('Ingresos totales estimados (EUR)',
    (SELECT IFNULL(SUM(
        DATEDIFF(IFNULL(a.fecha_entrega, CURRENT_DATE), a.fecha_recogida) * p.precio_dia
     ), 0)
     FROM alquila a
     JOIN ejemplar ej ON ej.id_ejemplar = a.id_ejemplar
     JOIN pelicula  p  ON p.id_pelicula  = ej.id_pelicula), NOW()),

('Ingreso medio por alquiler (EUR)',
    (SELECT ROUND(IFNULL(AVG(
        DATEDIFF(IFNULL(a.fecha_entrega, CURRENT_DATE), a.fecha_recogida) * p.precio_dia
     ), 0), 2)
     FROM alquila a
     JOIN ejemplar ej ON ej.id_ejemplar = a.id_ejemplar
     JOIN pelicula  p  ON p.id_pelicula  = ej.id_pelicula), NOW()),

('Pelicula mas alquilada',
    (SELECT titulo FROM ranking_peliculas ORDER BY posicion LIMIT 1), NOW()),

('Pelicula menos alquilada',
    (SELECT titulo FROM ranking_peliculas ORDER BY posicion DESC LIMIT 1), NOW()),

('Top pelicula en Drama (cat. 3)',
    fn_pelicula_mas_alquilada_categoria(3), NOW()),

('Top pelicula en Ciencia Ficcion (cat. 4)',
    fn_pelicula_mas_alquilada_categoria(4), NOW()),

('Top pelicula en Thriller (cat. 8)',
    fn_pelicula_mas_alquilada_categoria(8), NOW()),

('Cliente con mas alquileres',
    (SELECT CONCAT(c.nombre, ' (', COUNT(*), ' alquileres)')
     FROM   alquila a
     JOIN   cliente c ON c.id_cliente = a.id_cliente
     GROUP BY a.id_cliente, c.nombre
     ORDER BY COUNT(*) DESC
     LIMIT 1), NOW()),

('Alquiler activo mas antiguo',
    (SELECT CONCAT(c.nombre, ' — ejemplar #', a.id_ejemplar,
                   ' — ', fn_dias_alquiler_activo(a.id_cliente, a.id_ejemplar), ' dias')
     FROM   alquila a
     JOIN   cliente c ON c.id_cliente = a.id_cliente
     WHERE  a.fecha_entrega IS NULL
     ORDER BY a.fecha_recogida ASC
     LIMIT 1), NOW()),

('Alquileres en el ano en curso',
    (SELECT COUNT(*) FROM alquila WHERE YEAR(fecha_recogida) = YEAR(CURRENT_DATE)), NOW()),

('Mes con mas alquileres (ano en curso)',
    (SELECT CONCAT('Mes ', MONTH(fecha_recogida), ' — ', COUNT(*), ' alquileres')
     FROM   alquila
     WHERE  YEAR(fecha_recogida) = YEAR(CURRENT_DATE)
     GROUP BY MONTH(fecha_recogida)
     ORDER BY COUNT(*) DESC
     LIMIT 1), NOW()),

('Director mas popular por alquileres',
    (SELECT CONCAT(d.nombre, ' (', COUNT(a.id_cliente), ' alquileres)')
     FROM   director d
     JOIN   pelicula  p  ON p.id_director  = d.id_director
     JOIN   ejemplar  ej ON ej.id_pelicula = p.id_pelicula
     JOIN   alquila   a  ON a.id_ejemplar  = ej.id_ejemplar
     GROUP BY d.id_director, d.nombre
     ORDER BY COUNT(a.id_cliente) DESC
     LIMIT 1), NOW());

SELECT
    id_metrica                                   AS '#',
    metrica                                      AS 'Metrica',
    valor                                        AS 'Valor',
    DATE_FORMAT(fecha_calculo, '%d/%m/%Y %H:%i') AS 'Calculado el'
FROM  dashboard
ORDER BY id_metrica;

CALL sp_registrar_alquiler(5, 20, 3);
SELECT id_ejemplar, estado, stock FROM ejemplar WHERE id_ejemplar = 20;

CALL sp_registrar_devolucion(5, 20);
SELECT id_ejemplar, estado, stock FROM ejemplar WHERE id_ejemplar = 20;

CALL sp_registrar_alquiler(3, 2, 1);

CALL sp_registrar_devolucion(99, 99);

CALL sp_aplicar_penalizacion_retrasos();
SELECT * FROM penalizacion;
SELECT id_ejemplar, estado FROM ejemplar WHERE estado = 'Retraso';

CALL sp_generar_ranking_peliculas();
SELECT * FROM ranking_peliculas;

SELECT fn_dias_alquiler_activo(8, 2) AS dias_sin_devolver;

SELECT
    c.nombre AS categoria,
    (SELECT p.titulo
     FROM   pelicula  p
     JOIN   pertenece pe ON pe.id_pelicula  = p.id_pelicula
     JOIN   ejemplar  ej ON ej.id_pelicula  = p.id_pelicula
     JOIN   alquila   a  ON a.id_ejemplar   = ej.id_ejemplar
     WHERE  pe.id_categoria = c.id_categoria
     GROUP BY p.id_pelicula, p.titulo
     ORDER BY COUNT(a.id_cliente) DESC
     LIMIT 1) AS pelicula_top
FROM categoria c
WHERE c.id_categoria IN (3, 4, 8, 5)
ORDER BY c.id_categoria;

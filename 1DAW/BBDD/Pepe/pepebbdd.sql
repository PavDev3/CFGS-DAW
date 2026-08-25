-- =====================================================
-- BASE DE DATOS: biblioteca_bd
-- Descripción : Sistema de gestión de una biblioteca.
--               Permite registrar libros, ejemplares físicos,
--               usuarios, préstamos, renovaciones e incidencias.
-- Autor        : Pepe
-- =====================================================
-- OBJETOS INCLUIDOS:
--   Tablas      : Autor, Editorial, Categoria, Libros, Ejemplares,
--                 Usuarios, Prestamo, es_escrito, es_editado_por,
--                 pertenece, amigos, dashboard_biblioteca
--   Triggers    : trg_evitar_doble_prestamo, trg_actualizar_estado_prestamo
--   Procedimientos: sp_registrar_prestamo, sp_devolver_ejemplar,
--                   sp_renovar_vencidos, sp_limpiar_mantenimiento,
--                   sp_actualizar_dashboard
--   Funciones   : fn_prestamos_activos, fn_renovaciones_libro
-- =====================================================

DROP DATABASE IF EXISTS biblioteca_bd;
CREATE DATABASE biblioteca_bd;
USE biblioteca_bd;

-- =====================================================
-- TABLAS
-- =====================================================

-- Autores de los libros del catálogo.
-- fecha_registro se rellena automáticamente al insertar.
CREATE TABLE Autor (
    id_autor INT AUTO_INCREMENT,
    nombre_autor VARCHAR(100) NOT NULL,
    pais_autor VARCHAR(50),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_autor)
);

-- Editoriales que publican los libros.
-- nombre_editorial es UNIQUE: no puede haber dos editoriales con el mismo nombre.
CREATE TABLE Editorial (
    id_editorial INT AUTO_INCREMENT,
    nombre_editorial VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id_editorial)
);

-- Categorías o géneros literarios (Ficción, Poesía, etc.).
-- nombre es UNIQUE para evitar duplicados de categoría.
CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT,
    PRIMARY KEY (id_categoria)
);

-- Catálogo de libros. La clave primaria es el ISBN (identificador
-- internacional estándar del libro), que ya es único por naturaleza.
CREATE TABLE Libros (
    isbn VARCHAR(20),
    titulo VARCHAR(200) NOT NULL,
    fecha_publicacion DATE,
    PRIMARY KEY (isbn)
);

-- Copias físicas de un libro. Un mismo ISBN puede tener varios ejemplares
-- (EJ001, EJ002...). estado_ejemplar controla la disponibilidad:
--   'disponible'  → se puede prestar
--   'prestado'    → tiene un préstamo activo (sin fecha_devolucion)
--   'mantenimiento' → retirado temporalmente, no se presta
CREATE TABLE Ejemplares (
    id_ejemplar INT AUTO_INCREMENT,
    isbn VARCHAR(20) NOT NULL,
    copia_ejemplar VARCHAR(50) NOT NULL,
    estado_ejemplar VARCHAR(20) DEFAULT 'disponible',
    PRIMARY KEY (id_ejemplar)
);

-- Usuarios registrados en la biblioteca.
-- email es UNIQUE: un mismo correo no puede tener dos cuentas.
CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario)
);

-- Préstamos de ejemplares a usuarios.
-- Clave primaria compuesta (id_usuario, id_ejemplar, fecha_prestamo):
--   permite que el mismo usuario tome prestado el mismo ejemplar en fechas distintas.
-- fecha_devolucion NULL → préstamo activo (no devuelto aún).
-- Num_Renovaciones lleva la cuenta de cuántas veces se ha renovado el plazo;
--   máximo 3 renovaciones (controlado por sp_renovar_vencidos).
-- ON DELETE/UPDATE CASCADE: si se elimina un usuario o un ejemplar,
--   sus préstamos se eliminan en cascada automáticamente.
CREATE TABLE Prestamo (
    id_usuario       INT  NOT NULL,
    id_ejemplar      INT  NOT NULL,
    fecha_prestamo   DATE NOT NULL,
    fecha_devolucion DATE,
    Num_Renovaciones INT DEFAULT 0,
    PRIMARY KEY (id_usuario, id_ejemplar, fecha_prestamo),
    FOREIGN KEY (id_usuario)  REFERENCES Usuarios(id_usuario)   ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_ejemplar) REFERENCES Ejemplares(id_ejemplar) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Relación N:M entre Autor y Libros.
-- Un libro puede tener varios autores y un autor puede escribir varios libros.
CREATE TABLE es_escrito (
    id_autor INT,
    isbn VARCHAR(20),
    PRIMARY KEY (id_autor, isbn)
);

-- Relación N:M entre Editorial y Libros.
-- Un libro puede tener varias editoriales (distintas ediciones).
CREATE TABLE es_editado_por (
    id_editorial INT,
    isbn VARCHAR(20),
    PRIMARY KEY (id_editorial, isbn)
);

-- Relación N:M entre Libros y Categorias.
-- Un libro puede pertenecer a varias categorías.
CREATE TABLE pertenece (
    isbn VARCHAR(20),
    id_categoria INT,
    PRIMARY KEY (isbn, id_categoria)
);

-- Relación de amistad entre usuarios (red social de la biblioteca).
-- La amistad es direccional en el modelo actual: (1,2) y (2,1) serían
-- registros distintos si se quisiese bidireccionalidad explícita.
CREATE TABLE amigos (
    id_usuario1 INT,
    id_usuario2 INT,
    PRIMARY KEY (id_usuario1, id_usuario2)
);

-- =====================================================
-- CLAVES FORÁNEAS DE LAS TABLAS DE RELACIÓN
-- Se declaran aquí (no en el CREATE TABLE) porque las
-- tablas referenciadas deben existir antes de añadirlas.
-- =====================================================

ALTER TABLE es_escrito      ADD FOREIGN KEY (id_autor)      REFERENCES Autor(id_autor)           ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE es_escrito      ADD FOREIGN KEY (isbn)           REFERENCES Libros(isbn)              ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE es_editado_por  ADD FOREIGN KEY (id_editorial)   REFERENCES Editorial(id_editorial)   ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE es_editado_por  ADD FOREIGN KEY (isbn)           REFERENCES Libros(isbn)              ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE pertenece       ADD FOREIGN KEY (isbn)           REFERENCES Libros(isbn)              ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE pertenece       ADD FOREIGN KEY (id_categoria)   REFERENCES Categoria(id_categoria)   ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE Ejemplares      ADD FOREIGN KEY (isbn)           REFERENCES Libros(isbn)              ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE amigos          ADD FOREIGN KEY (id_usuario1)    REFERENCES Usuarios(id_usuario)      ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE amigos          ADD FOREIGN KEY (id_usuario2)    REFERENCES Usuarios(id_usuario)      ON DELETE CASCADE ON UPDATE CASCADE;

-- =====================================================
-- DATOS INICIALES
-- =====================================================

INSERT INTO Autor (nombre_autor, pais_autor) VALUES
('Gabriel García Márquez', 'Colombia'),
('Isabel Allende', 'Chile'),
('Jorge Luis Borges', 'Argentina'),
('Julio Cortázar', 'Argentina'),
('Mario Vargas Llosa', 'Perú');

INSERT INTO Editorial (nombre_editorial) VALUES
('Editorial Planeta'), ('Random House'), ('Alfaguara'), ('Anagrama');

INSERT INTO Categoria (nombre, descripcion) VALUES
('Ficción', 'Obras literarias de ficción'),
('No Ficción', 'Obras basadas en hechos reales'),
('Ciencia Ficción', 'Literatura de ciencia ficción'),
('Poesía', 'Obras poéticas'),
('Ensayo', 'Obras de ensayo');

INSERT INTO Libros (isbn, titulo, fecha_publicacion) VALUES
('978-0307474728', 'Cien años de soledad',     '1967-05-30'),
('978-0525433446', 'La casa de los espíritus',  '1982-01-01'),
('978-0142437223', 'Ficciones',                 '1944-01-01'),
('978-0307475473', 'Rayuela',                   '1963-06-28'),
('978-8420471839', 'La ciudad y los perros',    '1963-01-01');

-- EJ007 está en mantenimiento: no se puede prestar hasta que se cambie su estado.
INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar) VALUES
('978-0307474728', 'EJ001', 'disponible'),
('978-0307474728', 'EJ002', 'disponible'),
('978-0307474728', 'EJ003', 'prestado'),
('978-0525433446', 'EJ004', 'disponible'),
('978-0142437223', 'EJ005', 'disponible'),
('978-0307475473', 'EJ006', 'disponible'),
('978-8420471839', 'EJ007', 'mantenimiento');

INSERT INTO Usuarios (nombre, email) VALUES
('Juan Pérez',       'juan.perez@email.com'),
('María González',   'maria.gonzalez@email.com'),
('Carlos Rodríguez', 'carlos.rodriguez@email.com'),
('Ana Martínez',     'ana.martinez@email.com'),
('Luis Fernández',   'luis.fernandez@email.com');

INSERT INTO es_escrito (id_autor, isbn) VALUES
(1, '978-0307474728'), (2, '978-0525433446'), (3, '978-0142437223'),
(4, '978-0307475473'), (5, '978-8420471839');

INSERT INTO es_editado_por (id_editorial, isbn) VALUES
(1, '978-0307474728'), (2, '978-0525433446'), (3, '978-0142437223'),
(1, '978-0307475473'), (3, '978-8420471839');

INSERT INTO pertenece (isbn, id_categoria) VALUES
('978-0307474728', 1), ('978-0525433446', 1), ('978-0142437223', 1),
('978-0307475473', 1), ('978-8420471839', 1);

-- Préstamos de muestra:
--   (1,3)  → devuelto el 2024-12-10
--   (2,4)  → devuelto con 1 renovación
--   (3,5)  → activo (fecha_devolucion NULL)
INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones) VALUES
(1, 3, '2024-12-01', '2024-12-10', 0),
(2, 4, '2024-11-20', '2024-12-05', 1),
(3, 5, '2024-12-05', NULL,         0);

INSERT INTO amigos (id_usuario1, id_usuario2) VALUES (1,2), (1,3), (2,4);

-- =====================================================
-- DATOS ADICIONALES (para pruebas de triggers,
-- procedimientos y funciones)
-- Se usa SELECT ... WHERE NOT EXISTS para que el script
-- sea idempotente: ejecutarlo varias veces no genera duplicados.
-- =====================================================

INSERT INTO Autor (nombre_autor, pais_autor)
SELECT 'Laura Esquivel', 'México'
WHERE NOT EXISTS (SELECT 1 FROM Autor WHERE nombre_autor = 'Laura Esquivel');

INSERT INTO Autor (nombre_autor, pais_autor)
SELECT 'Juan Rulfo', 'México'
WHERE NOT EXISTS (SELECT 1 FROM Autor WHERE nombre_autor = 'Juan Rulfo');

INSERT INTO Autor (nombre_autor, pais_autor)
SELECT 'César Vallejo', 'Perú'
WHERE NOT EXISTS (SELECT 1 FROM Autor WHERE nombre_autor = 'César Vallejo');

INSERT INTO Libros (isbn, titulo, fecha_publicacion)
SELECT '978-6071100001', 'Como agua para chocolate', '1989-01-01'
WHERE NOT EXISTS (SELECT 1 FROM Libros WHERE isbn = '978-6071100001');

INSERT INTO Libros (isbn, titulo, fecha_publicacion)
SELECT '978-9707100002', 'Pedro Páramo', '1955-01-01'
WHERE NOT EXISTS (SELECT 1 FROM Libros WHERE isbn = '978-9707100002');

INSERT INTO Libros (isbn, titulo, fecha_publicacion)
SELECT '978-6120000003', 'Los heraldos negros', '1919-01-01'
WHERE NOT EXISTS (SELECT 1 FROM Libros WHERE isbn = '978-6120000003');

INSERT INTO es_escrito (id_autor, isbn)
SELECT a.id_autor, '978-6071100001'
FROM Autor a
WHERE a.nombre_autor = 'Laura Esquivel'
  AND NOT EXISTS (SELECT 1 FROM es_escrito e WHERE e.id_autor = a.id_autor AND e.isbn = '978-6071100001');

INSERT INTO es_escrito (id_autor, isbn)
SELECT a.id_autor, '978-9707100002'
FROM Autor a
WHERE a.nombre_autor = 'Juan Rulfo'
  AND NOT EXISTS (SELECT 1 FROM es_escrito e WHERE e.id_autor = a.id_autor AND e.isbn = '978-9707100002');

INSERT INTO es_escrito (id_autor, isbn)
SELECT a.id_autor, '978-6120000003'
FROM Autor a
WHERE a.nombre_autor = 'César Vallejo'
  AND NOT EXISTS (SELECT 1 FROM es_escrito e WHERE e.id_autor = a.id_autor AND e.isbn = '978-6120000003');

INSERT INTO Editorial (nombre_editorial)
SELECT 'Fondo de Cultura Económica'
WHERE NOT EXISTS (SELECT 1 FROM Editorial WHERE nombre_editorial = 'Fondo de Cultura Económica');

INSERT INTO es_editado_por (id_editorial, isbn)
SELECT ed.id_editorial, '978-6071100001'
FROM Editorial ed
WHERE ed.nombre_editorial = 'Fondo de Cultura Económica'
  AND NOT EXISTS (SELECT 1 FROM es_editado_por ep WHERE ep.id_editorial = ed.id_editorial AND ep.isbn = '978-6071100001');

INSERT INTO es_editado_por (id_editorial, isbn)
SELECT ed.id_editorial, '978-9707100002'
FROM Editorial ed
WHERE ed.nombre_editorial = 'Alfaguara'
  AND NOT EXISTS (SELECT 1 FROM es_editado_por ep WHERE ep.id_editorial = ed.id_editorial AND ep.isbn = '978-9707100002');

INSERT INTO es_editado_por (id_editorial, isbn)
SELECT ed.id_editorial, '978-6120000003'
FROM Editorial ed
WHERE ed.nombre_editorial = 'Anagrama'
  AND NOT EXISTS (SELECT 1 FROM es_editado_por ep WHERE ep.id_editorial = ed.id_editorial AND ep.isbn = '978-6120000003');

INSERT INTO Categoria (nombre, descripcion)
SELECT 'Realismo mágico', 'Narrativa con elementos mágicos'
WHERE NOT EXISTS (SELECT 1 FROM Categoria WHERE nombre = 'Realismo mágico');

INSERT INTO Categoria (nombre, descripcion)
SELECT 'Literatura mexicana', 'Autores de México'
WHERE NOT EXISTS (SELECT 1 FROM Categoria WHERE nombre = 'Literatura mexicana');

INSERT INTO pertenece (isbn, id_categoria)
SELECT '978-6071100001', c.id_categoria
FROM Categoria c
WHERE c.nombre = 'Realismo mágico'
  AND NOT EXISTS (SELECT 1 FROM pertenece p WHERE p.isbn = '978-6071100001' AND p.id_categoria = c.id_categoria);

INSERT INTO pertenece (isbn, id_categoria)
SELECT '978-9707100002', c.id_categoria
FROM Categoria c
WHERE c.nombre = 'Literatura mexicana'
  AND NOT EXISTS (SELECT 1 FROM pertenece p WHERE p.isbn = '978-9707100002' AND p.id_categoria = c.id_categoria);

INSERT INTO pertenece (isbn, id_categoria)
SELECT '978-6120000003', c.id_categoria
FROM Categoria c
WHERE c.nombre = 'Poesía'
  AND NOT EXISTS (SELECT 1 FROM pertenece p WHERE p.isbn = '978-6120000003' AND p.id_categoria = c.id_categoria);

INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar)
SELECT '978-6071100001', 'EJ008', 'disponible'
WHERE NOT EXISTS (SELECT 1 FROM Ejemplares WHERE isbn = '978-6071100001' AND copia_ejemplar = 'EJ008');

-- EJ009 se inserta como 'prestado' directamente para simular un préstamo activo en pruebas
INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar)
SELECT '978-6071100001', 'EJ009', 'prestado'
WHERE NOT EXISTS (SELECT 1 FROM Ejemplares WHERE isbn = '978-6071100001' AND copia_ejemplar = 'EJ009');

INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar)
SELECT '978-9707100002', 'EJ010', 'disponible'
WHERE NOT EXISTS (SELECT 1 FROM Ejemplares WHERE isbn = '978-9707100002' AND copia_ejemplar = 'EJ010');

INSERT INTO Ejemplares (isbn, copia_ejemplar, estado_ejemplar)
SELECT '978-6120000003', 'EJ011', 'disponible'
WHERE NOT EXISTS (SELECT 1 FROM Ejemplares WHERE isbn = '978-6120000003' AND copia_ejemplar = 'EJ011');

INSERT INTO Usuarios (nombre, email)
SELECT 'Sofía Ramírez', 'sofia@email.com'
WHERE NOT EXISTS (SELECT 1 FROM Usuarios WHERE email = 'sofia@email.com');

INSERT INTO Usuarios (nombre, email)
SELECT 'Diego Torres', 'diego@email.com'
WHERE NOT EXISTS (SELECT 1 FROM Usuarios WHERE email = 'diego@email.com');

-- Préstamo devuelto con 1 renovación (para probar fn_renovaciones_libro)
INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones)
SELECT 1, 2, '2025-01-10', '2025-01-20', 1
WHERE NOT EXISTS (SELECT 1 FROM Prestamo WHERE id_usuario = 1 AND id_ejemplar = 2 AND fecha_prestamo = '2025-01-10');

-- Préstamo activo de Ana (id=4) sobre EJ009 → vencido para pruebas de sp_renovar_vencidos
INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones)
SELECT 4, 9, '2025-02-01', NULL, 0
WHERE NOT EXISTS (SELECT 1 FROM Prestamo WHERE id_usuario = 4 AND id_ejemplar = 9 AND fecha_prestamo = '2025-02-01');

-- Préstamo activo de Luis (id=5) con 2 renovaciones ya → solo le queda 1 más
INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones)
SELECT 5, 11, '2025-02-05', NULL, 2
WHERE NOT EXISTS (SELECT 1 FROM Prestamo WHERE id_usuario = 5 AND id_ejemplar = 11 AND fecha_prestamo = '2025-02-05');

-- Se rehace amigos con más relaciones para cubrir más casos de prueba
DELETE FROM amigos;
INSERT INTO amigos (id_usuario1, id_usuario2) VALUES (1,2), (1,3), (2,4), (4,5), (3,5);

-- =====================================================
-- TRIGGERS
-- =====================================================

-- trg_evitar_doble_prestamo
-- Evento  : BEFORE UPDATE en Ejemplares
-- Función : Impide marcar como 'prestado' un ejemplar que ya está 'prestado'.
--           Fuerza un error llamando a una función inexistente
--           (ERROR_LIBRO_YA_PRESTADO) para que MySQL aborte la operación.
-- Nota    : Este mecanismo de error forzado es compatible con XAMPP/MariaDB.
--           En MySQL puro se puede usar SIGNAL SQLSTATE en su lugar.
DROP TRIGGER IF EXISTS trg_evitar_doble_prestamo;
DELIMITER //
CREATE TRIGGER trg_evitar_doble_prestamo
BEFORE UPDATE ON Ejemplares
FOR EACH ROW
BEGIN
    IF NEW.estado_ejemplar = 'prestado' AND OLD.estado_ejemplar = 'prestado' THEN
        SET NEW.estado_ejemplar = ERROR_LIBRO_YA_PRESTADO();
    END IF;
END//
DELIMITER ;

-- trg_actualizar_estado_prestamo
-- Evento  : AFTER INSERT en Prestamo
-- Función : Cuando se registra un nuevo préstamo, actualiza automáticamente
--           el estado del ejemplar a 'prestado'.
--           Trabaja junto con sp_registrar_prestamo: el procedimiento
--           comprueba la disponibilidad antes del INSERT y este trigger
--           ejecuta el cambio de estado sin necesidad de un UPDATE explícito.
DROP TRIGGER IF EXISTS trg_actualizar_estado_prestamo;
DELIMITER //
CREATE TRIGGER trg_actualizar_estado_prestamo
AFTER INSERT ON Prestamo
FOR EACH ROW
BEGIN
    UPDATE Ejemplares SET estado_ejemplar = 'prestado' WHERE id_ejemplar = NEW.id_ejemplar;
END//
DELIMITER ;

-- =====================================================
-- PROCEDIMIENTOS TRANSACCIONALES
-- Usan START TRANSACTION / COMMIT / ROLLBACK para
-- garantizar que las operaciones son atómicas:
-- si algo falla, no queda ningún cambio a medias.
-- =====================================================

-- sp_registrar_prestamo
-- Parámetros: p_usuario  INT  → id del usuario
--             p_ejemplar INT  → id del ejemplar a prestar
--             p_fecha    DATE → fecha del préstamo (NULL = hoy)
-- Funcionamiento:
--   1. Lee el estado actual del ejemplar.
--   2. Si está 'disponible': inserta el préstamo y hace COMMIT.
--      El trigger trg_actualizar_estado_prestamo cambia el estado a 'prestado'.
--   3. Si no está disponible: hace ROLLBACK y devuelve mensaje de error.
-- Uso: CALL sp_registrar_prestamo(1, 6, CURDATE());
DROP PROCEDURE IF EXISTS sp_registrar_prestamo;
DELIMITER //
CREATE PROCEDURE sp_registrar_prestamo(IN p_usuario INT, IN p_ejemplar INT, IN p_fecha DATE)
BEGIN
    DECLARE estado_actual VARCHAR(20);
    START TRANSACTION;
    SELECT estado_ejemplar INTO estado_actual FROM Ejemplares WHERE id_ejemplar = p_ejemplar;
    IF estado_actual = 'disponible' THEN
        INSERT INTO Prestamo (id_usuario, id_ejemplar, fecha_prestamo, fecha_devolucion, Num_Renovaciones)
        VALUES (p_usuario, p_ejemplar, IFNULL(p_fecha, CURDATE()), NULL, 0);
        COMMIT;
        SELECT 'Préstamo registrado correctamente.' AS mensaje;
    ELSE
        ROLLBACK;
        SELECT 'Error: El ejemplar no está disponible.' AS mensaje;
    END IF;
END//
DELIMITER ;

-- sp_devolver_ejemplar
-- Parámetros: p_usuario  INT  → id del usuario que devuelve
--             p_ejemplar INT  → id del ejemplar devuelto
--             p_fecha    DATE → fecha de devolución (NULL = hoy)
-- Funcionamiento:
--   1. Actualiza fecha_devolucion del préstamo activo (fecha_devolucion IS NULL).
--   2. Comprueba ROW_COUNT() para saber si se encontró el préstamo activo.
--   3. Si se encontró: actualiza el estado del ejemplar a 'disponible' y COMMIT.
--   4. Si no se encontró: ROLLBACK (no había préstamo activo para esa combinación).
-- Uso: CALL sp_devolver_ejemplar(3, 5, CURDATE());
DROP PROCEDURE IF EXISTS sp_devolver_ejemplar;
DELIMITER //
CREATE PROCEDURE sp_devolver_ejemplar(IN p_usuario INT, IN p_ejemplar INT, IN p_fecha DATE)
BEGIN
    DECLARE filas_afectadas INT;
    START TRANSACTION;
    UPDATE Prestamo
    SET fecha_devolucion = IFNULL(p_fecha, CURDATE())
    WHERE id_usuario = p_usuario AND id_ejemplar = p_ejemplar AND fecha_devolucion IS NULL;
    SET filas_afectadas = ROW_COUNT();
    IF filas_afectadas > 0 THEN
        UPDATE Ejemplares SET estado_ejemplar = 'disponible' WHERE id_ejemplar = p_ejemplar;
        COMMIT;
        SELECT 'Devolución registrada correctamente.' AS mensaje;
    ELSE
        ROLLBACK;
        SELECT 'Error: No se encontró un préstamo activo para ese usuario y ejemplar.' AS mensaje;
    END IF;
END//
DELIMITER ;

-- =====================================================
-- PROCEDIMIENTOS CON CURSORES
-- Usan CURSOR para recorrer fila a fila un conjunto de
-- resultados y aplicar lógica individual a cada registro.
-- Patrón estándar en MySQL:
--   DECLARE cur CURSOR FOR <SELECT>;
--   DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
--   OPEN cur → LOOP → FETCH → IF fin LEAVE → lógica → END LOOP → CLOSE cur
-- =====================================================

-- sp_renovar_vencidos
-- Función : Renueva automáticamente los préstamos activos vencidos.
--           Un préstamo se considera vencido cuando lleva más de 30 días
--           sin ser devuelto (fecha_devolucion IS NULL y
--           DATEDIFF(CURDATE(), fecha_prestamo) > 30).
-- Límite  : Máximo 3 renovaciones por préstamo (Num_Renovaciones < 3).
--           Si ya tiene 3, el cursor lo excluye directamente.
-- Al renovar:
--   - Incrementa Num_Renovaciones en 1.
--   - Reinicia fecha_prestamo a CURDATE(), dando otros 30 días de plazo.
-- Devuelve: Mensaje con el número de préstamos renovados.
-- Uso: CALL sp_renovar_vencidos();
DROP PROCEDURE IF EXISTS sp_renovar_vencidos;
DELIMITER //
CREATE PROCEDURE sp_renovar_vencidos()
BEGIN
    DECLARE v_usuario     INT;
    DECLARE v_ejemplar    INT;
    DECLARE v_fecha       DATE;
    DECLARE v_renovaciones INT;
    DECLARE v_cont        INT DEFAULT 0;
    DECLARE fin           INT DEFAULT 0;

    -- Cursor: solo préstamos activos, con más de 30 días y menos de 3 renovaciones
    DECLARE cur CURSOR FOR
        SELECT id_usuario, id_ejemplar, fecha_prestamo, Num_Renovaciones
        FROM Prestamo
        WHERE fecha_devolucion IS NULL
          AND DATEDIFF(CURDATE(), fecha_prestamo) > 30
          AND Num_Renovaciones < 3;

    -- Cuando el cursor llega al final (no hay más filas), activa la bandera fin
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    bucle: LOOP
        FETCH cur INTO v_usuario, v_ejemplar, v_fecha, v_renovaciones;
        IF fin THEN LEAVE bucle; END IF;

        -- Renueva el préstamo: incrementa el contador y reinicia la fecha
        UPDATE Prestamo
        SET Num_Renovaciones = Num_Renovaciones + 1,
            fecha_prestamo   = CURDATE()
        WHERE id_usuario     = v_usuario
          AND id_ejemplar    = v_ejemplar
          AND fecha_prestamo = v_fecha;

        SET v_cont = v_cont + 1;
    END LOOP;
    CLOSE cur;

    SELECT CONCAT('Proceso finalizado. Se han renovado ', v_cont, ' préstamos.') AS resultado;
END//
DELIMITER ;

-- sp_limpiar_mantenimiento
-- Función : Elimina los ejemplares en estado 'mantenimiento' que no tienen
--           ningún préstamo asociado (ni activo ni histórico).
--           Si un ejemplar en mantenimiento tiene historial de préstamos,
--           no se elimina para preservar la trazabilidad.
-- Devuelve: Mensaje con el número de ejemplares eliminados.
-- Uso: CALL sp_limpiar_mantenimiento();
DROP PROCEDURE IF EXISTS sp_limpiar_mantenimiento;
DELIMITER //
CREATE PROCEDURE sp_limpiar_mantenimiento()
BEGIN
    DECLARE v_id    INT;
    DECLARE v_copia VARCHAR(50);
    DECLARE v_del   INT DEFAULT 0;
    DECLARE fin     INT DEFAULT 0;

    -- Cursor: todos los ejemplares retirados por mantenimiento
    DECLARE cur CURSOR FOR
        SELECT id_ejemplar, copia_ejemplar FROM Ejemplares WHERE estado_ejemplar = 'mantenimiento';

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    loop_mant: LOOP
        FETCH cur INTO v_id, v_copia;
        IF fin THEN LEAVE loop_mant; END IF;

        -- Solo se elimina si no tiene ningún préstamo (ni activo ni histórico)
        IF NOT EXISTS (SELECT 1 FROM Prestamo WHERE id_ejemplar = v_id) THEN
            DELETE FROM Ejemplares WHERE id_ejemplar = v_id;
            SET v_del = v_del + 1;
        END IF;
    END LOOP;
    CLOSE cur;

    SELECT CONCAT('Limpieza completada. Se eliminaron ', v_del, ' ejemplares sin uso.') AS resultado;
END//
DELIMITER ;

-- =====================================================
-- FUNCIONES
-- Devuelven un único valor escalar.
-- Nota: en MySQL con binary logging activo se necesita
--   SET GLOBAL log_bin_trust_function_creators = 1;
-- o declarar las funciones con READS SQL DATA / DETERMINISTIC.
-- =====================================================

-- fn_prestamos_activos(p_usuario)
-- Devuelve el número de préstamos activos (no devueltos) de un usuario.
-- Un préstamo activo es aquel con fecha_devolucion IS NULL.
-- Uso: SELECT fn_prestamos_activos(3);
DROP FUNCTION IF EXISTS fn_prestamos_activos;
DELIMITER //
CREATE FUNCTION fn_prestamos_activos(p_usuario INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM Prestamo
    WHERE id_usuario = p_usuario AND fecha_devolucion IS NULL;
    RETURN total;
END//
DELIMITER ;

-- fn_renovaciones_libro(p_isbn)
-- Devuelve el total acumulado de renovaciones de todos los préstamos
-- de cualquier ejemplar de un libro dado su ISBN.
-- IFNULL(..., 0) evita devolver NULL cuando el libro no tiene préstamos.
-- Uso: SELECT fn_renovaciones_libro('978-0307474728');
DROP FUNCTION IF EXISTS fn_renovaciones_libro;
DELIMITER //
CREATE FUNCTION fn_renovaciones_libro(p_isbn VARCHAR(20)) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT IFNULL(SUM(p.Num_Renovaciones), 0) INTO total
    FROM Prestamo p
    JOIN Ejemplares e ON p.id_ejemplar = e.id_ejemplar
    WHERE e.isbn = p_isbn;
    RETURN total;
END//
DELIMITER ;

-- =====================================================
-- DASHBOARD
-- Tabla de resumen actualizable bajo demanda mediante
-- sp_actualizar_dashboard. Cada llamada al procedimiento
-- inserta una nueva fila con snapshot del estado actual.
-- =====================================================

-- Tabla de snapshots del estado de la biblioteca.
-- Cada fila es un registro histórico generado por sp_actualizar_dashboard.
CREATE TABLE IF NOT EXISTS dashboard_biblioteca (
    id                       INT AUTO_INCREMENT PRIMARY KEY,
    fecha                    DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_libros             INT,
    total_autores            INT,
    total_usuarios           INT,
    prestamos_activos        INT,
    renovaciones_ultimo_mes  INT,
    libros_mas_prestados     TEXT,        -- top 3 títulos separados por coma
    categoria_top            VARCHAR(50)  -- categoría con más préstamos
);

-- sp_actualizar_dashboard
-- Función : Calcula el estado actual de la biblioteca y lo guarda en
--           dashboard_biblioteca como un nuevo registro.
-- Campos calculados:
--   total_libros            → número de libros distintos en catálogo
--   total_autores           → número de autores registrados
--   total_usuarios          → número de usuarios registrados
--   prestamos_activos       → préstamos sin fecha_devolucion
--   renovaciones_ultimo_mes → suma de Num_Renovaciones de préstamos
--                             iniciados en el mes en curso
--   libros_mas_prestados    → top 3 títulos por número de préstamos (GROUP_CONCAT)
--   categoria_top           → categoría con más préstamos totales
-- Uso: CALL sp_actualizar_dashboard();
--      SELECT * FROM dashboard_biblioteca ORDER BY fecha DESC LIMIT 1;
DROP PROCEDURE IF EXISTS sp_actualizar_dashboard;
DELIMITER //
CREATE PROCEDURE sp_actualizar_dashboard()
BEGIN
    DECLARE v_libros     INT;
    DECLARE v_autores    INT;
    DECLARE v_usuarios   INT;
    DECLARE v_prest_act  INT;
    DECLARE v_renov_mes  INT;
    DECLARE v_top_libros TEXT;
    DECLARE v_categoria  VARCHAR(50);

    SELECT COUNT(*) INTO v_libros   FROM Libros;
    SELECT COUNT(*) INTO v_autores  FROM Autor;
    SELECT COUNT(*) INTO v_usuarios FROM Usuarios;

    -- Préstamos activos a día de hoy
    SELECT COUNT(*) INTO v_prest_act
    FROM Prestamo WHERE fecha_devolucion IS NULL;

    -- Renovaciones de préstamos iniciados en el mes actual
    SELECT IFNULL(SUM(Num_Renovaciones), 0) INTO v_renov_mes
    FROM Prestamo
    WHERE MONTH(fecha_prestamo) = MONTH(CURDATE())
      AND YEAR(fecha_prestamo)  = YEAR(CURDATE());

    -- Top 3 libros más prestados (por número de préstamos de sus ejemplares)
    SELECT GROUP_CONCAT(titulo SEPARATOR ', ') INTO v_top_libros
    FROM (
        SELECT l.titulo, COUNT(*) AS total
        FROM Prestamo p
        JOIN Ejemplares e ON p.id_ejemplar = e.id_ejemplar
        JOIN Libros l     ON e.isbn = l.isbn
        GROUP BY l.isbn
        ORDER BY total DESC
        LIMIT 3
    ) AS top_libros;

    -- Categoría con más préstamos en total
    SELECT nombre INTO v_categoria
    FROM (
        SELECT c.nombre, COUNT(p.id_ejemplar) AS total
        FROM Categoria c
        JOIN pertenece pe ON c.id_categoria = pe.id_categoria
        JOIN Ejemplares e ON pe.isbn = e.isbn
        JOIN Prestamo p   ON e.id_ejemplar = p.id_ejemplar
        GROUP BY c.id_categoria
        ORDER BY total DESC
        LIMIT 1
    ) AS cat_top;

    INSERT INTO dashboard_biblioteca (
        fecha, total_libros, total_autores, total_usuarios,
        prestamos_activos, renovaciones_ultimo_mes,
        libros_mas_prestados, categoria_top
    ) VALUES (
        NOW(), v_libros, v_autores, v_usuarios,
        v_prest_act, v_renov_mes,
        IFNULL(v_top_libros, 'Ninguno'),
        IFNULL(v_categoria,  'Sin categoría')
    );
END//
DELIMITER ;

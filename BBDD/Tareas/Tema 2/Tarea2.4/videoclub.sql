-- ######################################################################
-- CREACIÓN DE LA BASE DE DATOS Y TABLAS - VIDEOCLUB
-- ######################################################################

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS videoclub;
USE videoclub;

-- ######################################################################
-- TABLAS PRINCIPALES (ENTIDADES)
-- ######################################################################

-- 1. Tabla Director
CREATE TABLE director (
    id_director INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE
);

-- 2. Tabla Cliente
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    ciudad VARCHAR(50),
    calle VARCHAR(100),
    numero VARCHAR(20)
);

-- 3. Tabla Telefono_Cliente (para teléfono multivalorado)
CREATE TABLE telefono_cliente (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- 4. Tabla Categoría
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT
);

-- 5. Tabla Proveedor
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20)
);

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
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
        ON DELETE RESTRICT
);

-- 8. Tabla Ejemplar
CREATE TABLE ejemplar (
    id_ejemplar INT AUTO_INCREMENT PRIMARY KEY,
    estado VARCHAR(50),
    stock INT DEFAULT 0,
    id_pelicula INT NOT NULL,
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
        ON DELETE RESTRICT
);

-- ######################################################################
-- TABLAS DE RELACIÓN
-- ######################################################################

-- 9. Tabla dirige (Relación N:M entre Director y Pelicula)
-- Cardinalidad: Director (1,n) - Pelicula (1,1)
CREATE TABLE dirige (
    id_director INT NOT NULL,
    id_pelicula INT NOT NULL,
    PRIMARY KEY (id_director, id_pelicula),
    FOREIGN KEY (id_director) REFERENCES director(id_director),
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
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
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_ejemplar) REFERENCES ejemplar(id_ejemplar)
        ON DELETE RESTRICT,
    FOREIGN KEY (id_trabajador) REFERENCES trabajador(id_trabajador)
        ON DELETE RESTRICT
);

-- 11. Tabla pertenece (Relación N:M entre Pelicula y Categoría)
-- Cardinalidad: Pelicula (1,n) - Categoría (1,n)
CREATE TABLE pertenece (
    id_pelicula INT NOT NULL,
    id_categoria INT NOT NULL,
    PRIMARY KEY (id_pelicula, id_categoria),
    FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

-- 12. Tabla atiende (Relación N:1 entre Cliente y Trabajador)
-- Cardinalidad: Cliente (0,n) - Trabajador (1,1)
-- Esta relación se puede implementar como FK en la tabla alquila
-- ya que cada alquiler es atendido por un trabajador
-- (Ya está incluida en la tabla alquila)

-- ######################################################################
-- MODIFICACIÓN DE TABLAS CON ALTER TABLE
-- Agregar restricciones CASCADE a las claves foráneas
-- ######################################################################
--
-- NOTA: Los nombres de las claves foráneas (ej: telefono_cliente_ibfk_1)
-- son generados automáticamente por MySQL. Si hay algún error al ejecutar
-- los DROP FOREIGN KEY, verifica los nombres reales con:
--   SHOW CREATE TABLE nombre_tabla;
-- o consulta INFORMATION_SCHEMA:
--   SELECT CONSTRAINT_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
--   WHERE TABLE_SCHEMA = 'videoclub' AND TABLE_NAME = 'nombre_tabla';
--

-- Modificar tabla telefono_cliente: agregar CASCADE
ALTER TABLE telefono_cliente
DROP FOREIGN KEY telefono_cliente_ibfk_1;

ALTER TABLE telefono_cliente
ADD CONSTRAINT fk_telefono_cliente_cliente
FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- Modificar tabla pelicula: agregar ON UPDATE CASCADE
ALTER TABLE pelicula
DROP FOREIGN KEY pelicula_ibfk_1;

ALTER TABLE pelicula
ADD CONSTRAINT fk_pelicula_proveedor
FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

-- Modificar tabla ejemplar: agregar ON UPDATE CASCADE
ALTER TABLE ejemplar
DROP FOREIGN KEY ejemplar_ibfk_1;

ALTER TABLE ejemplar
ADD CONSTRAINT fk_ejemplar_pelicula
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
    ON DELETE RESTRICT
    ON UPDATE CASCADE;

-- Modificar tabla dirige: agregar CASCADE a ambas claves foráneas
ALTER TABLE dirige
DROP FOREIGN KEY dirige_ibfk_1,
DROP FOREIGN KEY dirige_ibfk_2;

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
DROP FOREIGN KEY alquila_ibfk_1,
DROP FOREIGN KEY alquila_ibfk_2,
DROP FOREIGN KEY alquila_ibfk_3;

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
DROP FOREIGN KEY pertenece_ibfk_1,
DROP FOREIGN KEY pertenece_ibfk_2;

ALTER TABLE pertenece
ADD CONSTRAINT fk_pertenece_pelicula
FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_pertenece_categoria
FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- ######################################################################
-- COMENTARIOS SOBRE EL DISEÑO
-- ######################################################################

-- NOTAS IMPORTANTES:
-- 1. El atributo "antigüedad" de Trabajador es derivado y se calcula
--    a partir de fecha_contratacion, por lo que no se almacena.
-- 2. El teléfono de Cliente es multivalorado, por lo que se creó
--    la tabla telefono_cliente para almacenar múltiples teléfonos.
-- 3. La dirección de Cliente se descompuso en ciudad, calle y número.
-- 4. La relación "suministra" entre Pelicula y Proveedor se implementa
--    como FK en la tabla pelicula (id_proveedor).
-- 5. La relación "atiende" se implementa en la tabla alquila ya que
--    cada alquiler es atendido por un trabajador específico.
-- 6. Las restricciones CASCADE se agregaron mediante ALTER TABLE para
--    simular el proceso de modificación de tablas existentes.


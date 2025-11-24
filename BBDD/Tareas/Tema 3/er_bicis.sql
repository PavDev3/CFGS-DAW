CREATE DATABASE er2_bicis;

USE er2_bicis;

CREATE TABLE bicicletas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    modelo VARCHAR(255) NOT NULL UNIQUE,
    marca VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio >= 0),
    stock INT DEFAULT 5,
    color VARCHAR(255) NOT NULL,
    fecha_entrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE bicicletas (
    ADD COLUMN descripcion TEXT NOT NULL,
    /* permitir precios hasta 9999,99 */
    UPDATE Bicicletas SET precio = ROUND(precio, 2) WHERE precio > 9999.99,
    ADD CONSTRAINT COLUMN stock CHECK (stock < 0),
    UPDATE COLUMN color VARCHAR(30) NOT NULL,
    ADD COLUMN promocion ENUM('0', '1') NOT NULL DEFAULT '0',
)
CREATE DATABASE Actividad3_02;

CREATE TABLE IF NOT EXISTS Libros (
    id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    fecha_publicacion DATE NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    CONSTRAINT check_categoria CHECK (categoria IN ('Novela', 'Historia', 'Ciencia', 'Filosofía', 'Otros'))
);

CREATE TABLE IF NOT EXISTS Usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    nombre VARCHAR(60)
)

CREATE TABLE IF NOT EXISTS Prestamos ( 
    id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
    id_libro INT NOT NULL,
    id_usuario INT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT check_fecha_prestamo CHECK (fecha_devolucion > fecha_prestamo),
    CONSTRAINT check_estado CHECK (estado = TRUE),
    FOREIGN KEY (id_libro) REFERENCES Libros(id),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id)
);


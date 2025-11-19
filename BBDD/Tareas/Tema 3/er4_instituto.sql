CREATE TABLE 
    IF NOT EXISTS Libro (
        id INT PRIMARY KEY,
        titulo VARCHAR(100) NOT NULL,
        autor VARCHAR(100),
        anio DATE,
        categoria ENUM ('novela', 'ciencia-ficcion', 'historia', 'drama', 'erotica') NOT NULL,
    );

CREATE TABLE
    IF NOT EXISTS Usuario (
        id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,
        nombre VARCHAR(100) NOT NULL,
        apellido VARCHAR(100) NOT NULL,
        email VARCHAR(100) NOT NULL,
    );

CREATE TABLE 
    IF NOT EXISTS Prestamo (
        id INT PRIMARY KEY UNIQUE ,
        FOREING KEY (id_libro) REFERENCES Libro(id),
        FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
        fecha_prestamo DATE NOT NULL,
        fecha_devolucion DATE NOT NULL,
        estado ENUM ('0', '1') NOT NULL,
    )

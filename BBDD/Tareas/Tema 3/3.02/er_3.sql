create database Biblioteca_Universitaria;

CREATE TABLE IF NOT EXISTS Autor (
    claveautor INT PRIMARY KEY ,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS Libro (
    clavelibro INT PRIMARY KEY,
    titulo VARCHAR(60) NOT NULL,
    idioma VARCHAR(15) NOT NULL,
    formato VARCHAR(15) NOT NULL,
    claveeditorial SMALLINT(6)
);

CREATE TABLE IF NOT EXISTS Editorial (
    claveeditorial SMALLINT(6) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    direccion VARCHAR(60) NOT NULL,
    telefono VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS escrito_por (
    clavelibro INT NOT NULL,
    claveautor INT NOT NULL,
    FOREIGN KEY (clavelibro) REFERENCES Libro(clavelibro),
    FOREIGN KEY (claveautor) REFERENCES Autor(claveautor)
);

CREATE TABLE IF NOT EXISTS Tema (
    clavetema SMALLINT(6) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS trata_sobre (
    clavelibro INT NOT NULL,
    clavetema SMALLINT(6) NOT NULL,
    FOREIGN KEY (clavelibro) REFERENCES Libro(clavelibro),
    FOREIGN KEY (clavetema) REFERENCES Tema(clavetema)
)

CREATE TABLE IF NOT EXISTS Ejemplar (
    claveejemplear INT PRIMARY KEY,
    clavelibro INT NOT NULL, 
    numeroorden SMALLINT(6) NOT NULL,
    edicion SMALLINT(6) NOT NULL,
    ubicacion VARCHAR(15) NOT NULL,
    categoria char(1) NOT NULL,
    FOREIGN KEY (clavelibro) REFERENCES Libro(clavelibro)
);

CREATE TABLE IF NOT EXISTS Socio (
    clavesocio INT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    direccion VARCHAR(60) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    categoria CHAR(1)
    
)

CREATE TABLE IF NOT EXISTS Prestamo (
    clavesocio INT NOT NULL,
    claveejemplar INT NOT NULL,
    numeroorden SMALLINT(6) NOT NULL,
    fecha_prestamo DATE NOT NULL,
    fecha_devolucion DATE NOT NULL,
    notas TEXT NOT NULL,
    FOREIGN KEY (clavesocio) REFERENCES Socio(clavesocio),
    FOREIGN KEY (claveejemplar) REFERENCES Ejemplar(claveejemplar)
    CONSTRAINT check_fecha_prestamo CHECK (fecha_devolucion > fecha_prestamo),
    CONSTRAINT check_clave_socio_ejemplar CHECK (clavesocio IS NOT NULL AND claveejemplar IS NOT NULL),
);
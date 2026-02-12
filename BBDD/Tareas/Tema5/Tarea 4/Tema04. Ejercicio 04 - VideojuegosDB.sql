CREATE DATABASE VideojuegosDB;
USE VideojuegosDB;

-- Tabla de Videojuegos
CREATE TABLE Videojuegos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(50),
    plataforma VARCHAR(50),
    fecha_lanzamiento DATE
);

-- Tabla de Jugadores
CREATE TABLE Jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Partidas
CREATE TABLE Partidas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    videojuego_id INT,
    fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
    duracion_minutos INT,
    FOREIGN KEY (videojuego_id) REFERENCES Videojuegos(id) ON DELETE CASCADE
);

-- Tabla intermedia para la relación muchos a muchos entre Jugadores y Partidas
CREATE TABLE Jugadores_Partidas (
    jugador_id INT,
    partida_id INT,
    puntuacion INT,
    PRIMARY KEY (jugador_id, partida_id),
    FOREIGN KEY (jugador_id) REFERENCES Jugadores(id) ON DELETE CASCADE,
    FOREIGN KEY (partida_id) REFERENCES Partidas(id) ON DELETE CASCADE
);

-- Inserciones en Videojuegos
INSERT INTO Videojuegos (nombre, genero, plataforma, fecha_lanzamiento) VALUES
('The Legend of Zelda', 'Aventura', 'Nintendo Switch', '2017-03-03'),
('Super Mario Odyssey', 'Plataformas', 'Nintendo Switch', '2017-10-27'),
('God of War', 'Acción', 'PlayStation 4', '2018-04-20'),
('Halo Infinite', 'FPS', 'Xbox Series X', '2021-12-08'),
('Minecraft', 'Sandbox', 'Multiplataforma', '2011-11-18'),
('Cyberpunk 2077', 'RPG', 'PC', '2020-12-10'),
('FIFA 23', 'Deportes', 'Multiplataforma', '2022-09-30'),
('GTA V', 'Acción', 'Multiplataforma', '2013-09-17'),
('Red Dead Redemption 2', 'Acción', 'Multiplataforma', '2018-10-26'),
('The Witcher 3', 'RPG', 'Multiplataforma', '2015-05-19'),
('Fortnite', 'Battle Royale', 'Multiplataforma', '2017-07-25'),
('Call of Duty: Warzone', 'FPS', 'Multiplataforma', '2020-03-10'),
('Apex Legends', 'Battle Royale', 'Multiplataforma', '2019-02-04'),
('Dark Souls III', 'RPG', 'Multiplataforma', '2016-04-12'),
('Sekiro: Shadows Die Twice', 'Acción', 'Multiplataforma', '2019-03-22'),
('Elden Ring', 'RPG', 'Multiplataforma', '2022-02-25'),
('Street Fighter V', 'Lucha', 'PlayStation 4', '2016-02-16'),
('Mortal Kombat 11', 'Lucha', 'Multiplataforma', '2019-04-23'),
('Animal Crossing: New Horizons', 'Simulación', 'Nintendo Switch', '2020-03-20'),
('Hollow Knight', 'Metroidvania', 'Multiplataforma', '2017-02-24');

-- Inserciones en Jugadores
INSERT INTO Jugadores (nombre, email) VALUES
('Carlos Pérez', 'carlos@example.com'),
('María López', 'maria@example.com'),
('Juan García', 'juan@example.com'),
('Ana Torres', 'ana@example.com'),
('Pedro Sánchez', 'pedro@example.com'),
('Elena Díaz', 'elena@example.com'),
('Miguel Fernández', 'miguel@example.com'),
('Laura Ramírez', 'laura@example.com'),
('David Gómez', 'david@example.com'),
('Paula Jiménez', 'paula@example.com'),
('Sergio Ruiz', 'sergio@example.com'),
('Natalia Ortega', 'natalia@example.com'),
('Javier Castro', 'javier@example.com'),
('Beatriz Herrera', 'beatriz@example.com'),
('Hugo Morales', 'hugo@example.com'),
('Andrea Serrano', 'andrea@example.com'),
('Raúl Navarro', 'raul@example.com'),
('Lucía Cano', 'lucia@example.com'),
('Francisco Reyes', 'francisco@example.com'),
('Carmen Vega', 'carmen@example.com');

-- Inserciones en Partidas
INSERT INTO Partidas (videojuego_id, duracion_minutos) VALUES
(1, 45), (2, 30), (3, 60), (4, 50), (5, 90),
(6, 120), (7, 40), (8, 55), (9, 35), (10, 75),
(11, 25), (12, 50), (13, 45), (14, 80), (15, 30),
(16, 110), (17, 20), (18, 60), (19, 85), (20, 100);

-- Inserciones en Jugadores_Partidas (relación muchos a muchos)
INSERT INTO Jugadores_Partidas (jugador_id, partida_id, puntuacion) VALUES
(1, 1, 1500), (2, 2, 2000), (3, 3, 2500), (4, 4, 3000), (5, 5, 1200),
(6, 6, 1800), (7, 7, 1700), (8, 8, 1900), (9, 9, 1400), (10, 10, 2100),
(11, 11, 1600), (12, 12, 2300), (13, 13, 2200), (14, 14, 2700), (15, 15, 2600),
(16, 16, 2800), (17, 17, 3000), (18, 18, 3200), (19, 19, 3400), (20, 20, 3600);

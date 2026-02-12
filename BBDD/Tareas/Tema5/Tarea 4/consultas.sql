-- 01. Nombre y genero de todos los videojuegos "Multiplataforma"
SELECT nombre, genero
FROM Videojuegos
WHERE plataforma = 'Multiplataforma';

-- 02. Cantidad de videojuegos de "Acción"
SELECT COUNT(*) AS cantidad
FROM Videojuegos
WHERE genero = 'Acción';

-- 03. Nombre de las distintas plataformas que existen para los videojuegos (sin repetir)
SELECT DISTINCT plataforma
FROM Videojuegos;

-- 04. Nombre y fecha_lanzamiento de los videojuegos que salieron a la venta entre el 5 de mayo de 2017 y el 5 de mayo de 2019
SELECT nombre, fecha_lanzamiento
FROM Videojuegos
WHERE fecha_lanzamiento BETWEEN '2017-05-05' AND '2019-05-05';

-- 05. Nombre y email de los jugadores cuyo apellido acabe con la letra "a"
SELECT nombre, email
FROM Jugadores
WHERE nombre LIKE '%a';

-- 06. Duración (en minutos) de la partida más larga y nombre del videojuego en el que se jugó la partida
SELECT p.duracion_minutos, v.nombre
FROM Partidas p
JOIN Videojuegos v ON p.videojuego_id = v.id
WHERE p.duracion_minutos = (SELECT MAX(duracion_minutos) FROM Partidas);

-- 07. Duración promedio de las partidas jugadas
SELECT AVG(duracion_minutos) AS duracion_promedio
FROM Partidas;

-- 08. Nombre del jugador, videojuego jugado y puntuación del jugador con menor puntuación registrado
SELECT j.nombre, v.nombre AS videojuego, jp.puntuacion
FROM Jugadores_Partidas jp
JOIN Jugadores j ON jp.jugador_id = j.id
JOIN Partidas p ON jp.partida_id = p.id
JOIN Videojuegos v ON p.videojuego_id = v.id
WHERE jp.puntuacion = (SELECT MIN(puntuacion) FROM Jugadores_Partidas);

-- 09. Puntuación total obtenida en todos los videojuegos de "PlayStation 4"
SELECT SUM(jp.puntuacion) AS puntuacion_total
FROM Jugadores_Partidas jp
JOIN Partidas p ON jp.partida_id = p.id
JOIN Videojuegos v ON p.videojuego_id = v.id
WHERE v.plataforma = 'PlayStation 4';

-- 10. Nombre de plataforma y número de partidas totales jugadas en cada una de ellas
SELECT v.plataforma, COUNT(p.id) AS num_partidas
FROM Videojuegos v
JOIN Partidas p ON v.id = p.videojuego_id
GROUP BY v.plataforma;

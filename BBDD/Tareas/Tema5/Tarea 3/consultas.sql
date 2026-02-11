
-- 01. Nombre y email de todos los usuarios del departamento de "Recursos Humanos".
SELECT nombre, email
FROM usuario
WHERE departamento = 'Recursos Humanos';

--02. Lista de todos los tickets sin técnico asignado y con prioridad "Alta".
SELECT *
FROM ticket
WHERE email_tecnico IS NULL
  AND prioridad = 'Alta';

-- 03. Lista de tickets que incluyen en su descripción la palabra "servi".
SELECT *
FROM ticket
WHERE descripcion LIKE '%servi%';

-- 04. Número total de tickets resueltos.
SELECT COUNT(*) AS total_resueltos
FROM ticket
WHERE estado = 'Resuelto';

-- 05. Número de usuarios que tiene cada departamento.
SELECT departamento, COUNT(*) AS num_usuarios
FROM usuario
GROUP BY departamento;

-- 06. Nombre y email de todos los técnicos y administradores cuyo apellido acabe en "ez", ordenados ascendentemente.
SELECT nombre, email
FROM tecnico
WHERE nombre LIKE '%ez'
UNION
SELECT nombre, email
FROM administrador
WHERE nombre LIKE '%ez'
ORDER BY nombre ASC;

-- 07. Lista de tickets "Pendiente" ordenados por fecha de creación descendente.
SELECT *
FROM ticket
WHERE estado = 'Pendiente'
ORDER BY fecha_creacion DESC;

-- 08. Número de tickets registrados por cada departamento.
SELECT u.departamento, COUNT(*) AS num_tickets
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
GROUP BY u.departamento;

-- 09. Lista de tickets resueltos en los últimos 5 días (desde el último registro).
SELECT *
FROM historial_ticket
WHERE estado = 'Resuelto'
  AND fecha >= DATE_SUB(
    ('2025-02-12'),
    INTERVAL 4 DAY
  );

-- 10. Mostrar la cantidad de tickets "En progreso", "Resuelto" y "Pendiente".
SELECT estado, COUNT(*) AS cantidad
FROM ticket
GROUP BY estado;

-- 11. Lista de tickets "Pendiente" con el nombre del usuario que lo ha creado.
SELECT t.*, u.nombre AS nombre_usuario
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
WHERE t.estado = 'Pendiente';

-- 12. Lista de tickets "En progreso" con el nombre del usuario y el nombre del técnico asignado.
SELECT t.*, u.nombre AS nombre_usuario, te.nombre AS nombre_tecnico
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
JOIN tecnico te ON t.email_tecnico = te.email
WHERE t.estado = 'En progreso';

-- 13. Lista de tickets "Resuelto" de categorías que empiezan por "S" o por "A".
SELECT *
FROM ticket
WHERE estado = 'Resuelto'
  AND (categoria LIKE 'S%' OR categoria LIKE 'A%');

-- 14. Nombre de los 3 técnicos que más tickets han resuelto.
SELECT te.nombre, COUNT(*) AS tickets_resueltos
FROM ticket t
JOIN tecnico te ON t.email_tecnico = te.email
WHERE t.estado = 'Resuelto'
GROUP BY te.nombre
ORDER BY tickets_resueltos DESC
LIMIT 3;

-- 15. Nombre e email de los técnicos que no han resuelto ningún ticket (para despedir).
SELECT te.nombre, te.email
FROM tecnico te
WHERE te.email NOT IN (
    SELECT email_tecnico
    FROM ticket
    WHERE estado = 'Resuelto'
      AND email_tecnico IS NOT NULL
);

-- 16. Día del mes de febrero que más tickets se crearon.
SELECT DAY(fecha_creacion) AS dia, COUNT(*) AS num_tickets
FROM ticket
WHERE MONTH(fecha_creacion) = 2
GROUP BY dia
ORDER BY num_tickets DESC
LIMIT 1;

-- 17. Nombre, email y número de tickets creados de los 5 usuarios más pesados (más tickets), descendente.
SELECT u.nombre, u.email, COUNT(*) AS num_tickets
FROM ticket t
JOIN usuario u ON t.email_usuario = u.email
GROUP BY u.nombre, u.email
ORDER BY num_tickets DESC
LIMIT 5;

-- 18. Descripción, fecha y estado de todos los tickets del técnico que haya resuelto menos tickets (al menos uno).
SELECT t.descripcion, t.fecha_creacion, t.estado
FROM ticket t
WHERE t.email_tecnico = (
    SELECT email_tecnico
    FROM ticket
    WHERE estado = 'Resuelto'
      AND email_tecnico IS NOT NULL
    GROUP BY email_tecnico
    ORDER BY COUNT(*) ASC
    LIMIT 1
);

-- 19. Muestra el último ticket registrado.
SELECT *
FROM ticket
ORDER BY idTicket DESC
LIMIT 1;

-- 20. Todos los estados por los que ha pasado el ticket con idTicket 5,
--     incluyendo descripción, nombre del técnico, nombre del usuario y fecha.
SELECT h.estado, h.fecha, t.descripcion, te.nombre AS nombre_tecnico, u.nombre AS nombre_usuario
FROM historial_ticket h
JOIN ticket t ON h.idTicket = t.idTicket
JOIN usuario u ON t.email_usuario = u.email
LEFT JOIN tecnico te ON t.email_tecnico = te.email
WHERE h.idTicket = 5
ORDER BY h.fecha ASC;

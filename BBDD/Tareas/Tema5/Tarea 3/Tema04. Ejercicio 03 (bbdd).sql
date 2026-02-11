-- ============================================
-- Base de datos: ayuntamiento
-- Motor: MariaDB
-- ============================================

DROP DATABASE IF EXISTS ayuntamiento;
CREATE DATABASE ayuntamiento CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE ayuntamiento;

-- ============================================
-- Tabla: administrador
-- ============================================

CREATE TABLE administrador (
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO administrador (email, password, nombre) VALUES
  ('admin1@example.com', 'pass1234', 'Juan Pérez'),
  ('admin2@example.com', 'securePass!', 'María Gómez'),
  ('admin3@example.com', 'adminPass2024', 'Carlos López'),
  ('admin4@example.com', 'claveSegura99', 'Ana Martínez'),
  ('admin5@example.com', 'superAdmin!', 'Pedro Sánchez'),
  ('admin6@example.com', 'contraseña123', 'Laura Fernández'),
  ('admin7@example.com', 'gestion2024!', 'David Ruiz'),
  ('admin8@example.com', 'adminSecure45', 'Carmen Rodríguez'),
  ('admin9@example.com', 'passAdmin99', 'José Ramírez'),
  ('admin10@example.com', 'keyAdmin2025', 'Elena Torres');

-- ============================================
-- Tabla: tecnico
-- ============================================

CREATE TABLE tecnico (
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO tecnico (email, password, nombre) VALUES
  ('tecnico1@example.com', 'techPass1', 'Andrés Castillo'),
  ('tecnico2@example.com', 'techPass2', 'Beatriz Herrera'),
  ('tecnico3@example.com', 'techPass3', 'Carlos Medina'),
  ('tecnico4@example.com', 'techPass4', 'Diana Torres'),
  ('tecnico5@example.com', 'techPass5', 'Emilio Vargas'),
  ('tecnico6@example.com', 'techPass6', 'Fernanda Ruiz'),
  ('tecnico7@example.com', 'techPass7', 'Gonzalo Peña'),
  ('tecnico8@example.com', 'techPass8', 'Helena Navarro'),
  ('tecnico9@example.com', 'techPass9', 'Iván Jiménez'),
  ('tecnico10@example.com', 'techPass10', 'Julia Ramírez');

-- ============================================
-- Tabla: usuario
-- ============================================

CREATE TABLE usuario (
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  departamento VARCHAR(255) NOT NULL,
  PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO usuario (email, password, nombre, departamento) VALUES
  ('usuario1@example.com', 'password1', 'Luis Fernández', 'Ventas'),
  ('usuario2@example.com', 'password2', 'Marta López', 'Marketing'),
  ('usuario3@example.com', 'password3', 'Carlos Pérez', 'Recursos Humanos'),
  ('usuario4@example.com', 'password4', 'Ana García', 'Finanzas'),
  ('usuario5@example.com', 'password5', 'Javier Martínez', 'IT'),
  ('usuario6@example.com', 'password6', 'Elena Sánchez', 'Ventas'),
  ('usuario7@example.com', 'password7', 'Pedro Ramírez', 'Marketing'),
  ('usuario8@example.com', 'password8', 'Sofía Torres', 'Recursos Humanos'),
  ('usuario9@example.com', 'password9', 'David Gómez', 'Finanzas'),
  ('usuario10@example.com', 'password10', 'Laura Díaz', 'IT'),
  ('usuario11@example.com', 'password11', 'Alberto Ruiz', 'Ventas'),
  ('usuario12@example.com', 'password12', 'Carmen Moreno', 'Marketing'),
  ('usuario13@example.com', 'password13', 'Hugo Jiménez', 'Recursos Humanos'),
  ('usuario14@example.com', 'password14', 'Natalia Castro', 'Finanzas'),
  ('usuario15@example.com', 'password15', 'Raúl Domínguez', 'IT'),
  ('usuario16@example.com', 'password16', 'Beatriz Vargas', 'Ventas'),
  ('usuario17@example.com', 'password17', 'Tomás Herrera', 'Marketing'),
  ('usuario18@example.com', 'password18', 'Irene Medina', 'Recursos Humanos'),
  ('usuario19@example.com', 'password19', 'Sergio Navarro', 'Finanzas'),
  ('usuario20@example.com', 'password20', 'Patricia Ríos', 'IT'),
  ('usuario21@example.com', 'password21', 'Daniel Ortega', 'Ventas'),
  ('usuario22@example.com', 'password22', 'Eva Álvarez', 'Marketing'),
  ('usuario23@example.com', 'password23', 'Jorge Peña', 'Recursos Humanos'),
  ('usuario24@example.com', 'password24', 'Lucía Herrera', 'Finanzas'),
  ('usuario25@example.com', 'password25', 'Manuel Castro', 'IT'),
  ('usuario26@example.com', 'password26', 'Isabel Vázquez', 'Ventas'),
  ('usuario27@example.com', 'password27', 'Fernando Ramos', 'Marketing'),
  ('usuario28@example.com', 'password28', 'Clara Jiménez', 'Recursos Humanos'),
  ('usuario29@example.com', 'password29', 'Óscar Muñoz', 'Finanzas'),
  ('usuario30@example.com', 'password30', 'Rosa Benítez', 'IT');

-- ============================================
-- Tabla: ticket
-- Depende de: tecnico, usuario
-- ============================================

CREATE TABLE ticket (
  idTicket INT NOT NULL AUTO_INCREMENT,
  descripcion TEXT NOT NULL,
  prioridad ENUM('Baja', 'Media', 'Alta') NOT NULL,
  categoria VARCHAR(255) NOT NULL,
  estado ENUM('En progreso', 'Pendiente', 'Resuelto') NOT NULL,
  fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP(),
  email_tecnico VARCHAR(255) DEFAULT NULL,
  email_usuario VARCHAR(255) NOT NULL,
  PRIMARY KEY (idTicket),
  KEY idx_email_tecnico (email_tecnico),
  KEY idx_email_usuario (email_usuario),
  CONSTRAINT fk_ticket_tecnico FOREIGN KEY (email_tecnico) REFERENCES tecnico (email) ON DELETE SET NULL,
  CONSTRAINT fk_ticket_usuario FOREIGN KEY (email_usuario) REFERENCES usuario (email) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO ticket (idTicket, descripcion, prioridad, categoria, estado, fecha_creacion, email_tecnico, email_usuario) VALUES
  (1, 'Problema con el acceso a la cuenta', 'Alta', 'Autenticación', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario1@example.com'),
  (2, 'Error en la base de datos', 'Alta', 'Base de Datos', 'En progreso', '2025-02-14 16:04:28', 'tecnico1@example.com', 'usuario2@example.com'),
  (3, 'Solicitud de nuevo equipo', 'Media', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario3@example.com'),
  (4, 'Fallo en la conexión a la VPN', 'Alta', 'Red', 'Resuelto', '2025-02-14 16:04:28', 'tecnico2@example.com', 'usuario4@example.com'),
  (5, 'Software no responde', 'Media', 'Software', 'En progreso', '2025-02-14 16:04:28', 'tecnico3@example.com', 'usuario5@example.com'),
  (6, 'Problema con la impresora', 'Baja', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario6@example.com'),
  (7, 'Correo no enviado correctamente', 'Media', 'Correo', 'En progreso', '2025-02-14 16:04:28', 'tecnico4@example.com', 'usuario7@example.com'),
  (8, 'Error en la carga del sistema', 'Alta', 'Sistema Operativo', 'Resuelto', '2025-02-14 16:04:28', 'tecnico5@example.com', 'usuario8@example.com'),
  (9, 'Configuración incorrecta del firewall', 'Alta', 'Seguridad', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario9@example.com'),
  (10, 'Restablecimiento de contraseña', 'Baja', 'Autenticación', 'Resuelto', '2025-02-14 16:04:28', 'tecnico6@example.com', 'usuario10@example.com'),
  (11, 'Pantalla azul de la muerte', 'Alta', 'Sistema Operativo', 'En progreso', '2025-02-14 16:04:28', 'tecnico7@example.com', 'usuario11@example.com'),
  (12, 'Solicitud de actualización de software', 'Media', 'Software', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario12@example.com'),
  (13, 'No se puede conectar a la red WiFi', 'Alta', 'Red', 'Resuelto', '2025-02-14 16:04:28', 'tecnico8@example.com', 'usuario13@example.com'),
  (14, 'Fallo en el sistema de copias de seguridad', 'Alta', 'Base de Datos', 'En progreso', '2025-02-14 16:04:28', 'tecnico9@example.com', 'usuario14@example.com'),
  (15, 'Problema con permisos de usuario', 'Media', 'Seguridad', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario15@example.com'),
  (16, 'Solicitud de instalación de software', 'Baja', 'Software', 'Resuelto', '2025-02-14 16:04:28', 'tecnico10@example.com', 'usuario16@example.com'),
  (17, 'Error al iniciar sesión en el sistema', 'Alta', 'Autenticación', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario17@example.com'),
  (18, 'Disco duro lleno en servidor', 'Alta', 'Almacenamiento', 'En progreso', '2025-02-14 16:04:28', 'tecnico1@example.com', 'usuario18@example.com'),
  (19, 'Reinicio inesperado del equipo', 'Media', 'Hardware', 'Resuelto', '2025-02-14 16:04:28', 'tecnico2@example.com', 'usuario19@example.com'),
  (20, 'Fallo en la sincronización de archivos', 'Alta', 'Almacenamiento', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario20@example.com'),
  (21, 'Problema con la conexión del teclado', 'Baja', 'Hardware', 'Resuelto', '2025-02-14 16:04:28', 'tecnico3@example.com', 'usuario21@example.com'),
  (22, 'Solicitud de permisos administrativos', 'Media', 'Seguridad', 'En progreso', '2025-02-14 16:04:28', 'tecnico4@example.com', 'usuario22@example.com'),
  (23, 'Actualización fallida del sistema', 'Alta', 'Sistema Operativo', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario23@example.com'),
  (24, 'Error en el envío de reportes', 'Media', 'Software', 'Resuelto', '2025-02-14 16:04:28', 'tecnico5@example.com', 'usuario24@example.com'),
  (25, 'Solicitud de cambio de equipo', 'Alta', 'Hardware', 'En progreso', '2025-02-14 16:04:28', 'tecnico6@example.com', 'usuario25@example.com'),
  (26, 'Lentitud extrema en el sistema', 'Alta', 'Sistema Operativo', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario26@example.com'),
  (27, 'Bloqueo de cuenta por intentos fallidos', 'Media', 'Autenticación', 'Resuelto', '2025-02-14 16:04:28', 'tecnico7@example.com', 'usuario27@example.com'),
  (28, 'Problema con el acceso a recursos compartidos', 'Alta', 'Red', 'En progreso', '2025-02-14 16:04:28', 'tecnico8@example.com', 'usuario28@example.com'),
  (29, 'Error en la carga de documentos', 'Media', 'Software', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario29@example.com'),
  (30, 'Sistema operativo no arranca', 'Alta', 'Sistema Operativo', 'Resuelto', '2025-02-14 16:04:28', 'tecnico9@example.com', 'usuario30@example.com'),
  (31, 'Solicitud de reconfiguración de correo', 'Baja', 'Correo', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario1@example.com'),
  (32, 'Fallo en la conexión de escritorio remoto', 'Alta', 'Red', 'En progreso', '2025-02-14 16:04:28', 'tecnico10@example.com', 'usuario2@example.com'),
  (33, 'Problema con la sincronización de calendario', 'Media', 'Correo', 'Resuelto', '2025-02-14 16:04:28', 'tecnico1@example.com', 'usuario3@example.com'),
  (34, 'Cambio de contraseña obligatorio no funciona', 'Alta', 'Autenticación', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario4@example.com'),
  (35, 'Error en la autenticación de doble factor', 'Alta', 'Seguridad', 'Resuelto', '2025-02-14 16:04:28', 'tecnico2@example.com', 'usuario5@example.com'),
  (36, 'Solicitud de restauración de archivos eliminados', 'Media', 'Almacenamiento', 'En progreso', '2025-02-14 16:04:28', 'tecnico3@example.com', 'usuario6@example.com'),
  (37, 'Corte de energía afectó servidores', 'Alta', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario7@example.com'),
  (38, 'Error en la generación de reportes', 'Media', 'Software', 'Resuelto', '2025-02-14 16:04:28', 'tecnico4@example.com', 'usuario8@example.com'),
  (39, 'Red interna con alta latencia', 'Alta', 'Red', 'En progreso', '2025-02-14 16:04:28', 'tecnico5@example.com', 'usuario9@example.com'),
  (40, 'Impresora no responde después de actualización', 'Media', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario10@example.com'),
  (41, 'Aplicación móvil no sincroniza datos', 'Alta', 'Software', 'Resuelto', '2025-02-14 16:04:28', 'tecnico6@example.com', 'usuario11@example.com'),
  (42, 'Problema con el acceso remoto al servidor', 'Alta', 'Red', 'En progreso', '2025-02-14 16:04:28', 'tecnico7@example.com', 'usuario12@example.com'),
  (43, 'Fallo en la conexión del proyector', 'Baja', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario13@example.com'),
  (44, 'Interrupción en el servicio de internet', 'Alta', 'Red', 'Resuelto', '2025-02-14 16:04:28', 'tecnico8@example.com', 'usuario14@example.com'),
  (45, 'Error en la ejecución de scripts automatizados', 'Media', 'Base de Datos', 'En progreso', '2025-02-14 16:04:28', 'tecnico9@example.com', 'usuario15@example.com'),
  (46, 'Actualización de firmware fallida', 'Alta', 'Hardware', 'Pendiente', '2025-02-14 16:04:28', NULL, 'usuario16@example.com'),
  (47, 'Solicitud de acceso a servidor restringido', 'Media', 'Seguridad', 'Resuelto', '2025-02-14 16:04:28', 'tecnico10@example.com', 'usuario17@example.com');

-- ============================================
-- Tabla: historial_ticket
-- Depende de: ticket
-- ============================================

CREATE TABLE historial_ticket (
  idTicket INT NOT NULL,
  estado ENUM('En progreso', 'Pendiente', 'Resuelto') NOT NULL,
  fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (idTicket, estado, fecha),
  CONSTRAINT fk_historial_ticket FOREIGN KEY (idTicket) REFERENCES ticket (idTicket) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO historial_ticket (idTicket, estado, fecha) VALUES
  (1, 'Pendiente', '2025-02-01 08:30:00'),
  (2, 'Pendiente', '2025-02-01 09:00:00'),
  (2, 'En progreso', '2025-02-02 10:15:00'),
  (3, 'Pendiente', '2025-02-01 09:30:00'),
  (4, 'Pendiente', '2025-02-01 10:00:00'),
  (4, 'En progreso', '2025-02-02 11:00:00'),
  (4, 'Resuelto', '2025-02-03 15:45:00'),
  (5, 'Pendiente', '2025-02-01 10:45:00'),
  (5, 'En progreso', '2025-02-02 12:30:00'),
  (6, 'Pendiente', '2025-02-01 11:00:00'),
  (7, 'Pendiente', '2025-02-01 11:15:00'),
  (7, 'En progreso', '2025-02-02 13:45:00'),
  (8, 'Pendiente', '2025-02-01 12:00:00'),
  (8, 'En progreso', '2025-02-02 14:00:00'),
  (8, 'Resuelto', '2025-02-04 09:30:00'),
  (9, 'Pendiente', '2025-02-01 12:30:00'),
  (10, 'Pendiente', '2025-02-01 13:00:00'),
  (10, 'Resuelto', '2025-02-05 10:00:00'),
  (11, 'Pendiente', '2025-02-01 14:00:00'),
  (11, 'En progreso', '2025-02-03 11:00:00'),
  (12, 'Pendiente', '2025-02-01 15:00:00'),
  (13, 'Pendiente', '2025-02-01 16:00:00'),
  (13, 'Resuelto', '2025-02-06 14:00:00'),
  (14, 'Pendiente', '2025-02-01 16:30:00'),
  (14, 'En progreso', '2025-02-02 17:30:00'),
  (15, 'Pendiente', '2025-02-01 17:00:00'),
  (16, 'Pendiente', '2025-02-01 18:00:00'),
  (16, 'Resuelto', '2025-02-07 09:00:00'),
  (17, 'Pendiente', '2025-02-01 18:30:00'),
  (18, 'Pendiente', '2025-02-01 19:00:00'),
  (18, 'En progreso', '2025-02-02 20:00:00'),
  (18, 'Resuelto', '2025-02-08 10:00:00'),
  (19, 'Pendiente', '2025-02-01 20:00:00'),
  (19, 'En progreso', '2025-02-03 08:30:00'),
  (20, 'Pendiente', '2025-02-01 20:30:00'),
  (21, 'Pendiente', '2025-02-01 21:00:00'),
  (21, 'En progreso', '2025-02-02 22:00:00'),
  (21, 'Resuelto', '2025-02-09 14:30:00'),
  (22, 'Pendiente', '2025-02-01 22:00:00'),
  (22, 'Resuelto', '2025-02-10 15:00:00'),
  (23, 'Pendiente', '2025-02-01 22:30:00'),
  (24, 'Pendiente', '2025-02-01 23:00:00'),
  (24, 'En progreso', '2025-02-02 08:00:00'),
  (25, 'Pendiente', '2025-02-01 23:30:00'),
  (25, 'Resuelto', '2025-02-11 10:45:00'),
  (26, 'Pendiente', '2025-02-02 00:00:00'),
  (27, 'Pendiente', '2025-02-02 01:00:00'),
  (27, 'En progreso', '2025-02-02 12:00:00'),
  (28, 'Pendiente', '2025-02-02 02:00:00'),
  (28, 'Resuelto', '2025-02-12 08:15:00');

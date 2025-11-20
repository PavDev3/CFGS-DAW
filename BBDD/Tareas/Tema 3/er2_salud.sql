create database salud;

create table medico (
    id_medico INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    anio_colegiacion INT NOT NULL
);

create table paciente (
    id_paciente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    id_medico INT,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico)
);

create table sala (
    id_sala INT PRIMARY KEY AUTO_INCREMENT,
    ubicacion VARCHAR(255) NOT NULL
);

create table horario (
    id_horario INT PRIMARY KEY AUTO_INCREMENT,
    id_medico INT,
    id_sala INT,
    horario VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES medico(id_medico),
    FOREIGN KEY (id_sala) REFERENCES sala(id_sala)
);

-- Médicos
insert into medico (nombre, anio_colegiacion) values ('Dr. Juan Pérez García', 2010);
insert into medico (nombre, anio_colegiacion) values ('Dra. María López Martín', 2015);
insert into medico (nombre, anio_colegiacion) values ('Dr. Carlos Rodríguez Sánchez', 2008);
insert into medico (nombre, anio_colegiacion) values ('Dra. Ana Martínez Fernández', 2012);
insert into medico (nombre, anio_colegiacion) values ('Dr. Javier Torres Díaz', 2018);

-- Pacientes
insert into paciente (nombre, id_medico) values ('Roberto Jiménez Ruiz', 1);
insert into paciente (nombre, id_medico) values ('Carmen Moreno López', 2);
insert into paciente (nombre, id_medico) values ('Miguel García Pérez', 3);
insert into paciente (nombre, id_medico) values ('Isabel Sánchez Torres', 4);
insert into paciente (nombre, id_medico) values ('Luis Fernández Martínez', 5);

-- Salas
insert into sala (ubicacion) values ('Planta 1 - Consulta 101');
insert into sala (ubicacion) values ('Planta 1 - Consulta 102');
insert into sala (ubicacion) values ('Planta 2 - Consulta 201');
insert into sala (ubicacion) values ('Planta 2 - Consulta 202');
insert into sala (ubicacion) values ('Planta 3 - Consulta 301');

-- Horarios
insert into horario (id_medico, id_sala, horario) values (1, 1, 'Lunes a Viernes: 09:00 - 14:00');
insert into horario (id_medico, id_sala, horario) values (2, 2, 'Lunes a Jueves: 10:00 - 13:00 y 16:00 - 19:00');
insert into horario (id_medico, id_sala, horario) values (3, 3, 'Martes y Jueves: 08:00 - 12:00');
insert into horario (id_medico, id_sala, horario) values (4, 4, 'Lunes, Miércoles y Viernes: 09:30 - 13:30');
insert into horario (id_medico, id_sala, horario) values (5, 5, 'Lunes a Viernes: 11:00 - 15:00');
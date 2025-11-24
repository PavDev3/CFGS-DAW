CREATE TABLE Alumno (
    id_alumno INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    nivel_ingles VARCHAR(255) NOT NULL
);

CREATE TABLE Profesor (
    id_profesor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255) NOT NULL
);

CREATE TABLE Grupo (
    id_grupo INT PRIMARY KEY AUTO_INCREMENT,
    nivel_ingles VARCHAR(255) NOT NULL,
    id_profesor INT NOT NULL,
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor)
);

CREATE TABLE Alumno_Grupo (
    id_alumno INT NOT NULL,
    id_grupo INT NOT NULL,
    FOREIGN KEY (id_alumno) REFERENCES Alumno(id_alumno),
    FOREIGN KEY (id_grupo) REFERENCES Grupo(id_grupo)
);

-- Alumnos
insert into Alumno (nombre, nivel_ingles) values ('Sofía Martínez', 'A2');
insert into Alumno (nombre, nivel_ingles) values ('Diego García', 'B1');
insert into Alumno (nombre, nivel_ingles) values ('Lucía Sánchez', 'A1');
insert into Alumno (nombre, nivel_ingles) values ('Pablo López', 'B2');
insert into Alumno (nombre, nivel_ingles) values ('Elena Torres', 'A2');

-- Profesores
insert into Profesor (nombre, nacionalidad) values ('James Wilson', 'Reino Unido');
insert into Profesor (nombre, nacionalidad) values ('Sarah Johnson', 'Estados Unidos');
insert into Profesor (nombre, nacionalidad) values ('Michael Brown', 'Irlanda');
insert into Profesor (nombre, nacionalidad) values ('Emma Davis', 'Australia');
insert into Profesor (nombre, nacionalidad) values ('David Miller', 'Canadá');

-- Grupos
insert into Grupo (nivel_ingles, id_profesor) values ('A1', 1);
insert into Grupo (nivel_ingles, id_profesor) values ('A2', 2);
insert into Grupo (nivel_ingles, id_profesor) values ('B1', 3);
insert into Grupo (nivel_ingles, id_profesor) values ('B2', 4);
insert into Grupo (nivel_ingles, id_profesor) values ('A2', 5);

-- Alumno-Grupo (relación muchos a muchos)
insert into Alumno_Grupo (id_alumno, id_grupo) values (1, 2);
insert into Alumno_Grupo (id_alumno, id_grupo) values (2, 3);
insert into Alumno_Grupo (id_alumno, id_grupo) values (3, 1);
insert into Alumno_Grupo (id_alumno, id_grupo) values (4, 4);
insert into Alumno_Grupo (id_alumno, id_grupo) values (5, 2);
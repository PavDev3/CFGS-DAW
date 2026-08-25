Create database instituto;

Create table IF NOT EXISTS Profesor (
    dni INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono INT NOT NULL
);

Create table IF NOT EXISTS Modulo (
    codigo INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    profesor_dni INT NOT NULL,
    FOREIGN KEY (profesor_dni) REFERENCES Profesor(dni)
);

Create table IF NOT EXISTS Alumno (
    n_expediente INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

Create table IF NOT EXISTS Grupo (
    nombre VARCHAR(255) PRIMARY KEY,
    nivel_educativo VARCHAR(255) NOT NULL,
    delegado_expediente INT NOT NULL,
    FOREIGN KEY (delegado_expediente) REFERENCES Alumno(n_expediente)
);

-- Profesores
insert into Profesor (dni, nombre, direccion, telefono) values (45678912, 'María González', 'Calle Mayor 45, 3ºB', 912345678);
insert into Profesor (dni, nombre, direccion, telefono) values (78912345, 'Carlos Rodríguez', 'Avenida de la Paz 12', 923456789);
insert into Profesor (dni, nombre, direccion, telefono) values (12345678, 'Ana Martínez', 'Plaza España 8, 2ºA', 934567890);
insert into Profesor (dni, nombre, direccion, telefono) values (34567890, 'Javier López', 'Calle San Juan 23', 945678901);
insert into Profesor (dni, nombre, direccion, telefono) values (56789012, 'Laura Fernández', 'Avenida Libertad 67', 956789012);

-- Módulos
insert into Modulo (codigo, nombre, profesor_dni) values (101, 'Matemáticas', 45678912);
insert into Modulo (codigo, nombre, profesor_dni) values (102, 'Lengua y Literatura', 78912345);
insert into Modulo (codigo, nombre, profesor_dni) values (103, 'Historia', 12345678);
insert into Modulo (codigo, nombre, profesor_dni) values (104, 'Ciencias Naturales', 34567890);
insert into Modulo (codigo, nombre, profesor_dni) values (105, 'Inglés', 56789012);

-- Alumnos
insert into Alumno (n_expediente, nombre, apellidos, fecha_nacimiento) values (2001, 'Sofía', 'Ruiz García', '2008-03-15');
insert into Alumno (n_expediente, nombre, apellidos, fecha_nacimiento) values (2002, 'Diego', 'Sánchez Pérez', '2007-07-22');
insert into Alumno (n_expediente, nombre, apellidos, fecha_nacimiento) values (2003, 'Lucía', 'Torres Martín', '2008-11-08');
insert into Alumno (n_expediente, nombre, apellidos, fecha_nacimiento) values (2004, 'Pablo', 'Jiménez López', '2007-05-30');
insert into Alumno (n_expediente, nombre, apellidos, fecha_nacimiento) values (2005, 'Elena', 'Moreno Díaz', '2008-09-14');

-- Grupos
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('1º ESO A', 'ESO', 2001);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('1º ESO B', 'ESO', 2002);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('2º ESO A', 'ESO', 2003);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('2º ESO B', 'ESO', 2004);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('3º ESO A', 'ESO', 2005);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('3º ESO B', 'ESO', 2001);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('4º ESO A', 'ESO', 2002);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('1º Bachillerato A', 'Bachillerato', 2003);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('1º Bachillerato B', 'Bachillerato', 2004);
insert into Grupo (nombre, nivel_educativo, delegado_expediente) values ('2º Bachillerato A', 'Bachillerato', 2005);
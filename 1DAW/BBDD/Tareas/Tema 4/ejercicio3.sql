CREATE DATABASE Empresa;

CREATE TABLE Empleado (
    dni INT(8) PRIMARY KEY,
    nombre VARCHAR(10) NOT NULL,
    apellido1 VARCHAR(15) NOT NULL,
    apellido2 VARCHAR(15),
    direcc1 VARCHAR(25),
    direcc2 VARCHAR(20),
    ciudad VARCHAR(20),
    provincia VARCHAR(20),
    cod_postal VARCHAR(5),
    sexo ENUM('H', 'M'),
    fecha_nac DATE
);


CREATE TABLE Departamento (
    dpto_cod INT(5) PRIMARY KEY,
    nombre_dpto VARCHAR(20) NOT NULL UNIQUE,
    dpot_padre INT(5),
    presupuesto FLOAT(10, 2) NOT NULL,
    pres_actual FLOAT(10, 2),
    FOREIGN KEY (dpot_padre) REFERENCES Departamento(dpto_cod)
);


CREATE TABLE trabajo (
    trabajo_cod INT(5) PRIMARY KEY,
    nombre_trab VARCHAR(20) NOT NULL UNIQUE,
    salario_minimo FLOAT(10, 2) NOT NULL,
    salario_maximo FLOAT(10, 2) NOT NULL
);


CREATE TABLE Historial_laboral (
    empleado_dni INT(8) NOT NULL,
    trabajo_cod INT(5) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    supervisor_dni INT(8),
    dpto_cod INT(5) NOT NULL,
    PRIMARY KEY (empleado_dni, trabajo_cod, fecha_inicio),
    FOREIGN KEY (empleado_dni) REFERENCES Empleado(dni),
    FOREIGN KEY (trabajo_cod) REFERENCES trabajo(trabajo_cod),
    FOREIGN KEY (supervisor_dni) REFERENCES Empleado(dni),
    FOREIGN KEY (dpto_cod) REFERENCES Departamento(dpto_cod)
);


CREATE TABLE Historial_salaria (
    empleado_dni INT(8) NOT NULL,
    salario_base FLOAT(10, 2) NOT NULL,
    fecha_comienzo DATE NOT NULL,
    fecha_fin DATE,
    PRIMARY KEY (empleado_dni, fecha_comienzo),
    FOREIGN KEY (empleado_dni) REFERENCES Empleado(dni)
);


CREATE TABLE Universidades (
    univ_cod INT(5) PRIMARY KEY,
    nombre_univ VARCHAR(25) NOT NULL,
    ciudad VARCHAR(20),
    municipio VARCHAR(2),
    cod_postal VARCHAR(5)
);


CREATE TABLE Estudios (
    empleado_dni INT(8) NOT NULL,
    universidad INT(5) NOT NULL,
    anio INT(4) NOT NULL,
    grado VARCHAR(3) NOT NULL,
    especialidad VARCHAR(20),
    PRIMARY KEY (empleado_dni, universidad, anio, grado),
    FOREIGN KEY (empleado_dni) REFERENCES Empleado(dni),
    FOREIGN KEY (universidad) REFERENCES Universidades(univ_cod)
);






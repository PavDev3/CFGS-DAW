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
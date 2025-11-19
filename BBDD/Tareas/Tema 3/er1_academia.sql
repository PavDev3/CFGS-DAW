CREATE TABLE Alumno (
    id_alumno INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    nivel_ingles VARCHAR(255) NOT NULL,
)

CREATE TABLE Profesor (
    id_profesor INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    nacionalidad VARCHAR(255) NOT NULL
)

CREATE TABLE Grupo (
    id_grupo INT PRIMARY KEY AUTO_INCREMENT,
    nivel_ingles VARCHAR(255) NOT NULL,
    id_profesor INT NOT NULL,
    FOREIGN KEY (id_profesor) REFERENCES Profesor(id_profesor)
)

CREATE TABLE Alumno_Grupo (
    id_alumno INT NOT NULL,
    id_grupo INT NOT NULL,
    FOREIGN KEY (id_alumno) REFERENCES Alumno(id_alumno),
    FOREIGN KEY (id_grupo) REFERENCES Grupo(id_grupo)
);
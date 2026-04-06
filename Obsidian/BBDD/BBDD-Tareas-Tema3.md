# Tareas Tema 3 — Scripts SQL de Mapeo

← [[BBDD-Tema3]] | [[BBDD]]

---

## Scripts SQL de los 5 ejercicios de mapeo

### er1_academia.sql — Academia de Inglés

```sql
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

-- Datos de ejemplo
INSERT INTO Alumno (nombre, nivel_ingles) VALUES
('Sofía Martínez', 'A2'),
('Diego García', 'B1'),
('Lucía Sánchez', 'A1');

INSERT INTO Profesor (nombre, nacionalidad) VALUES
('James Wilson', 'Reino Unido'),
('Sarah Johnson', 'Estados Unidos');
```

---

## Tarea 3.02 — Ejercicio estructura de tablas

Scripts SQL para crear las estructuras de las tablas de los ejercicios:

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 3/3.02/er_1.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 3/3.02/er_2.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 3/3.02/er_3.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 3/3.02/ACT3.02 Ejercicio estructura de tablas (3).pdf`

## Todos los scripts del Tema 3

| Archivo | Descripción |
|---------|-------------|
| `er1_academia.sql` | Academia de inglés |
| `er2_salud.sql` | Centro de salud |
| `er3_banco.sql` | Sistema bancario |
| `er4_instituto.sql` | Instituto educativo |
| `er5_automoviles.sql` | Empresa de automóviles |
| `er_bicis.sql` | Empresa de bicicletas |

Ubicación: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 3/`

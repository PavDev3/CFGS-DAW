# Tareas Tema 2 — Diagramas ER y Videoclub

← [[BBDD-Tema2]] | [[BBDD]]

---

## Tarea 2.1 — Diagramas ER

Mapeo de tres diagramas ER a modelo relacional.

### Relaciones del ER_1 (Instituto)

```
PERSONA (dni, nombre)
GRUPO (id)
PROFESOR (dni, nombre, nacionalidad)
NIVEL (id)
Profesor_Grupo (id)
Grupo_Persona
Grupo_Nivel
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.1/ER_1.drawio`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.1/ER_2.drawio`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.1/ER_3.drawio`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.1/ER_1.png`

---

## Tarea 2.4 — Videoclub (SQL Completo)

Diseño completo de la base de datos Videoclub, desde el diagrama ER hasta los scripts SQL.

### Entidades principales del Videoclub

| Entidad | Atributos clave |
|---------|-----------------|
| Director | id_director, nombre, nacionalidad, fecha_nacimiento |
| Cliente | id_cliente, nombre, email, ciudad, calle, numero |
| Pelicula | id_pelicula, titulo, año_lanzamiento, genero, clasificacion |
| Ejemplar | id_ejemplar, estado, stock |
| Categoría | id_categoria, nombre, descripcion |
| Proveedor | id_proveedor, nombre, direccion, telefono |
| Trabajador | id_trabajador, nombre, fecha_contratacion, salario |

### Script SQL — Creación de tablas

```sql
-- Crear la base de datos
DROP DATABASE IF EXISTS videoclub_Entrega;
CREATE DATABASE videoclub_Entrega;
USE videoclub_Entrega;

-- Tabla Director
CREATE TABLE director (
    id_director INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50),
    fecha_nacimiento DATE
) ENGINE=InnoDB;

-- Tabla Cliente
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    ciudad VARCHAR(50),
    calle VARCHAR(100),
    numero VARCHAR(20)
) ENGINE=InnoDB;

-- Teléfono multivaluado del cliente
CREATE TABLE telefono_cliente (
    id_telefono INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
) ENGINE=InnoDB;

-- Tabla Pelicula
CREATE TABLE pelicula (
    id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    año_lanzamiento INT,
    genero VARCHAR(50),
    clasificacion VARCHAR(20),
    id_proveedor INT NOT NULL,
    id_director INT NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor),
    FOREIGN KEY (id_director) REFERENCES director(id_director)
) ENGINE=InnoDB;

-- Tabla Alquila (relación N:M entre Cliente y Ejemplar)
CREATE TABLE alquila (
    id_cliente INT NOT NULL,
    id_ejemplar INT NOT NULL,
    fecha_recogida DATE NOT NULL,
    fecha_entrega DATE,
    id_trabajador INT NOT NULL,
    PRIMARY KEY (id_cliente, id_ejemplar, fecha_recogida),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_ejemplar) REFERENCES ejemplar(id_ejemplar),
    FOREIGN KEY (id_trabajador) REFERENCES trabajador(id_trabajador)
);
```

### Datos de ejemplo insertados

```sql
-- Directores
INSERT INTO director (nombre, nacionalidad, fecha_nacimiento) VALUES
('Christopher Nolan', 'Británico', '1970-07-30'),
('Steven Spielberg', 'Estadounidense', '1946-12-18'),
('Quentin Tarantino', 'Estadounidense', '1963-03-27'),
('Pedro Almodóvar', 'Español', '1949-09-25'),
('Martin Scorsese', 'Estadounidense', '1942-11-17');

-- Películas
INSERT INTO pelicula (titulo, año_lanzamiento, genero, clasificacion, id_proveedor, id_director) VALUES
('Inception', 2010, 'Ciencia Ficción', 'PG-13', 1, 1),
('Pulp Fiction', 1994, 'Crimen', 'R', 3, 3),
('Volver', 2006, 'Drama', 'R', 4, 4),
('Parásitos', 2019, 'Thriller', 'R', 10, 10);
```

### Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/Ejercicio2_04.drawio`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/Tarea2.04.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/videoclub.dbml`

Los scripts SQL completos están en:
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/videoclub.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/videoclub_consultas.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/videoclub_datos.sql`

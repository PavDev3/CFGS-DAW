# Tareas Tema 4 — Consultas SQL

← [[BBDD-Tema4]] | [[BBDD]]

---

## Ejercicio 1 — BD PUBS

Creación y consultas sobre una base de datos de pubs.

```sql
CREATE DATABASE PUBS;

CREATE TABLE Localidad (
    cod_localidad INT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL
);

CREATE TABLE Pub (
    cod_pub INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    licencia_fiscal VARCHAR(255) NOT NULL UNIQUE,
    domicilio VARCHAR(255),
    fecha_apertura DATE NOT NULL,
    horario ENUM ('HOR1', 'HOR2', 'HOR3') NOT NULL,
    cod_localidad INT NOT NULL,
    FOREIGN KEY (cod_localidad) REFERENCES Localidad(cod_localidad)
);

CREATE TABLE Titular (
    dni_titular VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    domicilio VARCHAR(120),
    cod_pub INT NOT NULL,
    FOREIGN KEY (cod_pub) REFERENCES Pub(cod_pub)
);

CREATE TABLE Existencias (
    cod_articulo INT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    cod_pub INT NOT NULL,
    FOREIGN KEY (cod_pub) REFERENCES Pub(cod_pub)
);
```

---

## Ejercicios con BD Sales y Ventas

### Relación de consultas avanzadas

Scripts de consultas para bases de datos de ventas y proveedores:

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Relación Consultas SQL Script.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Relación Consultas SQL.pdf`

### TareaVentas — BD VentasDB

```sql
-- Archivo de consultas resueltas
-- Ubicación: /Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/TareaVentas/consultas resueltas.sql
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/TareaVentas/VentasDB.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/TareaVentas/Tema04. Ejercicio 01-Ventas E-R.png`

### Tarea 3 — Consultas avanzadas

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 3/consultas.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 3/Tema04. Ejercicio 03.pdf`

### Tarea 4 — BD Videojuegos

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 4/Tema04. Ejercicio 04 - VideojuegosDB.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 4/consultas.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 4/Tema04. Ejercicio 04.pdf`

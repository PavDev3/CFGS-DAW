# Tema 4 — SQL: Consultas y DML

← [[BBDD-Tema3]] | [[BBDD]] | Siguiente: [[BBDD-Tema5]]

---

## SQL — Structured Query Language

SQL es el lenguaje estándar para gestionar bases de datos relacionales.

### Categorías de SQL

| Categoría | Descripción | Comandos |
|-----------|-------------|----------|
| **DDL** (Data Definition Language) | Define la estructura | `CREATE`, `ALTER`, `DROP` |
| **DML** (Data Manipulation Language) | Manipula los datos | `INSERT`, `UPDATE`, `DELETE` |
| **DQL** (Data Query Language) | Consulta los datos | `SELECT` |
| **DCL** (Data Control Language) | Controla el acceso | `GRANT`, `REVOKE` |

---

## DDL — Creación de Tablas

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

CREATE TABLE Empleado (
    dni_empleado VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    domicilio VARCHAR(120)
);

CREATE TABLE Pub_Empleado (
    cod_pub INT NOT NULL,
    dni_empleado VARCHAR(9) NOT NULL,
    funcion ENUM ('CAMARERO', 'SEGURIDAD', 'LIMPIEZA') NOT NULL,
    PRIMARY KEY (cod_pub, dni_empleado, funcion),
    FOREIGN KEY (dni_empleado) REFERENCES Empleado(dni_empleado)
);
```

---

## DML — SELECT (Consultas)

### Estructura básica

```sql
SELECT columnas
FROM tabla
WHERE condicion
GROUP BY columna
HAVING condicion_grupo
ORDER BY columna [ASC|DESC]
LIMIT n;
```

### JOINs

```sql
-- INNER JOIN: solo registros coincidentes en ambas tablas
SELECT e.nombre, d.NomDep
FROM empleado e
INNER JOIN departamento d ON e.CodDep = d.CodDep;

-- LEFT JOIN: todos los registros de la izquierda + coincidentes de la derecha
SELECT d.NomDep, COUNT(e.CodEmp) AS num_empleados
FROM departamento d
LEFT JOIN empleado e ON d.CodDep = e.CodDep
GROUP BY d.CodDep, d.NomDep;
```

### Funciones de Agregado

| Función | Descripción |
|---------|-------------|
| `COUNT()` | Cuenta registros |
| `SUM()` | Suma valores |
| `AVG()` | Media aritmética |
| `MAX()` / `MIN()` | Valor máximo / mínimo |

---

## Ejemplo: Consultas sobre BD Empresa

```sql
-- Dashboard por departamento
CREATE TABLE dashboard_dpto AS
SELECT
    d.CodDep,
    d.NomDep,
    d.PreAnu,
    COUNT(e.CodEmp) AS num_empleados,
    SUM(e.SalEmp) AS gasto_salarios
FROM
    departamento d
LEFT JOIN
    empleado e ON d.CodDep = e.CodDep
GROUP BY
    d.CodDep, d.NomDep, d.PreAnu;

-- Dashboard por centro
CREATE TABLE dashboard_centro AS
SELECT
    c.CodCen,
    c.NomCen,
    COUNT(d.CodDep) AS num_departamentos,
    SUM(d.PreAnu) AS presupuesto_anual
FROM
    centro c
LEFT JOIN
    departamento d ON c.CodCen = d.CodCen
GROUP BY
    c.CodCen, c.NomCen;
```

---

## Tareas Asociadas

- [[BBDD-Tareas-Tema4]]

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Temas/4/Tema04. Teoria v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema4.1/Tarea 4/Tema04. Ejercicio 04 - VideojuegosDB esquema.jpg`

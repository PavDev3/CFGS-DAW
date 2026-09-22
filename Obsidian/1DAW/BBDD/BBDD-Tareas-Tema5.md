# Tareas Tema 5 — SQL Avanzado

← [[BBDD-Tema5]] | [[BBDD]]

---

## Actividades del Tema 5

### Act 5.6 — Vistas y Tablas Derivadas

```sql
-- Ejercicio 1: Dashboard por departamento
DROP TABLE IF EXISTS dashboard_dpto;
CREATE TABLE dashboard_dpto AS
SELECT
    d.CodDep, d.NomDep, d.PreAnu,
    COUNT(e.CodEmp) AS num_empleados,
    SUM(e.SalEmp) AS gasto_salarios
FROM departamento d
LEFT JOIN empleado e ON d.CodDep = e.CodDep
GROUP BY d.CodDep, d.NomDep, d.PreAnu;

-- Ejercicio 2: Dashboard por centro
DROP TABLE IF EXISTS dashboard_centro;
CREATE TABLE dashboard_centro AS
SELECT
    c.CodCen, c.NomCen,
    COUNT(d.CodDep) AS num_departamentos,
    SUM(d.PreAnu) AS presupuesto_anual
FROM centro c
LEFT JOIN departamento d ON c.CodCen = d.CodCen
GROUP BY c.CodCen, c.NomCen;
```

### Act 5.7 y 5.8 — Procedimientos almacenados

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/ac07-procedimientos.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/ac08-procedimientos.sql`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/06bd-empresa.sql`

### Documentación de actividades

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act1.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act3.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act4.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act5.pdf`

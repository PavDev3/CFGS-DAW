# Tema 5 — SQL Avanzado: Vistas, Procedimientos y Funciones

← [[BBDD-Tema4]] | [[BBDD]]

---

## Vistas (VIEWS)

Una **vista** es una consulta SQL almacenada que se presenta como una tabla virtual.

```sql
-- Crear una vista
CREATE VIEW vista_empleados_activos AS
SELECT nombre, departamento, sueldo
FROM empleados
WHERE activo = TRUE;

-- Usar la vista
SELECT * FROM vista_empleados_activos;
```

### Ventajas de las vistas
- Simplifican consultas complejas
- Proporcionan seguridad (ocultando columnas sensibles)
- Facilitan el mantenimiento

---

## Procedimientos Almacenados

Un **procedimiento almacenado** es un bloque de código SQL reutilizable guardado en la base de datos.

```sql
DELIMITER //
CREATE PROCEDURE obtener_empleados_departamento(IN cod_dep INT)
BEGIN
    SELECT nombre, sueldo
    FROM empleados
    WHERE CodDep = cod_dep;
END //
DELIMITER ;

-- Llamar al procedimiento
CALL obtener_empleados_departamento(10);
```

---

## Funciones (FUNCTIONS)

```sql
DELIMITER //
CREATE FUNCTION calcular_aumento(sueldo DECIMAL(10,2), porcentaje DECIMAL(5,2))
RETURNS DECIMAL(10,2)
BEGIN
    RETURN sueldo * (1 + porcentaje / 100);
END //
DELIMITER ;

-- Usar la función
SELECT nombre, calcular_aumento(sueldo, 10) AS nuevo_sueldo
FROM empleados;
```

---

## Triggers (Disparadores)

Un **trigger** es código que se ejecuta automáticamente ante un evento (INSERT, UPDATE, DELETE).

```sql
DELIMITER //
CREATE TRIGGER before_insert_empleado
BEFORE INSERT ON empleados
FOR EACH ROW
BEGIN
    IF NEW.sueldo < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El sueldo no puede ser negativo';
    END IF;
END //
DELIMITER ;
```

---

## Ejercicio: Dashboard con vistas - BD Empresa

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

---

## Tareas Asociadas

- [[BBDD-Tareas-Tema5]]

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Temas/5/Tema05. Teoria.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema5/Tema5 Act1.pdf`

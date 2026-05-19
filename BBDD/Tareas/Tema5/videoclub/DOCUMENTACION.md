# Videoclub — Documentación Tema 5

## Índice
1. [Estructura de la BD](#1-estructura-de-la-bd)
2. [Triggers](#2-triggers)
3. [Procedimientos con transacción](#3-procedimientos-con-transacción)
4. [Procedimientos con cursores](#4-procedimientos-con-cursores)
5. [Funciones](#5-funciones)
6. [Script dashboard](#6-script-dashboard)
7. [Cómo ejecutarlo](#7-cómo-ejecutarlo)
8. [Preguntas de examen](#8-preguntas-de-examen)

---

## 1. Estructura de la BD

### Tablas originales
| Tabla | Descripción |
|---|---|
| `director` | Directores de cine |
| `cliente` | Clientes del videoclub |
| `telefono_cliente` | Teléfonos (multivalorado) |
| `categoria` | Géneros/categorías de películas |
| `proveedor` | Distribuidoras de películas |
| `trabajador` | Empleados del videoclub |
| `pelicula` | Catálogo de películas (con `precio_dia`) |
| `ejemplar` | Copias físicas de cada película |
| `dirige` | Relación N:M director ↔ película |
| `alquila` | Relación N:M cliente ↔ ejemplar (con fecha_recogida, fecha_entrega, trabajador) |
| `pertenece` | Relación N:M película ↔ categoría |

### Tablas nuevas (Tema 5)
| Tabla | Descripción |
|---|---|
| `penalizacion` | Registros de retrasos detectados por el cursor |
| `ranking_peliculas` | Ranking generado por el cursor de películas |
| `dashboard` | Informe estadístico consolidado |

### Campo `precio_dia` en `pelicula`
- **PG-13** → 2,00 €/día
- **R** → 2,50 €/día

---

## 2. Triggers

### Concepto clave
Un **trigger** es código SQL que se ejecuta automáticamente cuando ocurre un evento (INSERT, UPDATE, DELETE) sobre una tabla. No hay que llamarlo explícitamente.

### TRIGGER 1 — `trg_alquiler_nuevo`
```sql
AFTER INSERT ON alquila
```
**¿Qué hace?** Cuando se inserta un nuevo alquiler, actualiza el ejemplar:
- `estado = 'Alquilado'`
- `stock = 0`

**¿Por qué AFTER?** El INSERT en `alquila` ya se completó, así que cualquier error en el UPDATE del ejemplar no deja un alquiler sin efecto en el stock.

**Palabras clave:** `NEW.id_ejemplar` — accede al valor del campo recién insertado.

### TRIGGER 2 — `trg_devolucion_ejemplar`
```sql
AFTER UPDATE ON alquila
```
**¿Qué hace?** Cuando se registra la `fecha_entrega` (devolución), restaura el ejemplar:
- `estado = 'Disponible'`
- `stock = stock + 1`

**Condición importante:**
```sql
IF OLD.fecha_entrega IS NULL AND NEW.fecha_entrega IS NOT NULL THEN
```
Solo actúa cuando `fecha_entrega` pasa de `NULL` a un valor real (evita dispararse en actualizaciones de otros campos).

**Palabras clave:** `OLD` = valor antes del UPDATE, `NEW` = valor después.

---

## 3. Procedimientos con transacción

### Concepto clave
Una **transacción** agrupa varias operaciones SQL en una unidad atómica: o se ejecutan todas (`COMMIT`) o se deshacen todas (`ROLLBACK`).

```sql
START TRANSACTION;
  -- operaciones...
COMMIT;   -- si todo va bien
ROLLBACK; -- si algo falla
```

### PROCEDIMIENTO 1 — `sp_registrar_alquiler`
```sql
CALL sp_registrar_alquiler(p_id_cliente, p_id_ejemplar, p_id_trabajador);
```
**Pasos internos:**
1. `SELECT ... FOR UPDATE` — bloquea la fila del ejemplar para evitar alquileres simultáneos.
2. Comprueba que el ejemplar existe y está `'Disponible'` con stock > 0.
3. Si no → `SIGNAL SQLSTATE '45000'` (lanza error personalizado).
4. Inserta en `alquila` → el trigger `trg_alquiler_nuevo` actualiza el ejemplar.
5. `COMMIT`.

**Gestión de errores:**
```sql
DECLARE EXIT HANDLER FOR SQLEXCEPTION
BEGIN
    ROLLBACK;
    GET DIAGNOSTICS CONDITION 1 v_err_msg = MESSAGE_TEXT;
    SELECT CONCAT('[ERROR] ...', v_err_msg);
END;
```
El `EXIT HANDLER` intercepta cualquier error SQL, hace ROLLBACK y muestra el mensaje.

### PROCEDIMIENTO 2 — `sp_registrar_devolucion`
```sql
CALL sp_registrar_devolucion(p_id_cliente, p_id_ejemplar);
```
**Pasos internos:**
1. `UPDATE alquila SET fecha_entrega = CURRENT_DATE WHERE fecha_entrega IS NULL`
2. Comprueba `ROW_COUNT()` — si es 0, no había alquiler activo → `SIGNAL`.
3. El trigger `trg_devolucion_ejemplar` restaura el ejemplar.
4. `COMMIT`.

---

## 4. Procedimientos con cursores

### Concepto clave
Un **cursor** permite recorrer fila a fila el resultado de un SELECT dentro de un procedimiento almacenado.

```sql
DECLARE mi_cursor CURSOR FOR SELECT ...;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_fin = 1;

OPEN mi_cursor;
mi_loop: LOOP
    FETCH mi_cursor INTO v_campo1, v_campo2;
    IF v_fin = 1 THEN LEAVE mi_loop; END IF;
    -- procesar fila...
END LOOP;
CLOSE mi_cursor;
```

### PROCEDIMIENTO 3 — `sp_aplicar_penalizacion_retrasos`
```sql
CALL sp_aplicar_penalizacion_retrasos();
```
**¿Qué hace?**
- El cursor selecciona todos los alquileres activos con más de **5 días** sin devolver.
- Por cada fila:
  - Actualiza el ejemplar a `estado = 'Retraso'`.
  - Inserta en `penalizacion` si no existe ya un registro para hoy.
- Al final muestra cuántas penalizaciones aplicó.

**¿Por qué `CONTINUE HANDLER`?** Para que cuando el cursor se quede sin filas (`NOT FOUND`) no lance un error, sino que simplemente active la bandera `v_fin = 1` y salga del loop.

### PROCEDIMIENTO 4 — `sp_generar_ranking_peliculas`
```sql
CALL sp_generar_ranking_peliculas();
```
**¿Qué hace?**
- Limpia `ranking_peliculas` con `TRUNCATE`.
- El cursor recorre todas las películas ordenadas por total de alquileres (DESC).
- Por cada película calcula:
  - `total_alquileres` = COUNT de alquileres.
  - `ingresos_totales` = SUM(días × precio_dia).
- Inserta cada fila en `ranking_peliculas` con su posición.

---

## 5. Funciones

### Concepto clave
Una **función** devuelve un único valor escalar. Se usa dentro de SELECT, WHERE, etc.  
Diferencia con procedimiento: la función devuelve valor, el procedimiento ejecuta acciones.  
La estructura mínima es `CREATE FUNCTION nombre(params) RETURNS tipo BEGIN ... RETURN valor; END`.

### FUNCIÓN 1 — `fn_dias_alquiler_activo`
```sql
SELECT fn_dias_alquiler_activo(id_cliente, id_ejemplar);
```
**¿Qué devuelve?** Los días que lleva activo un alquiler sin devolver. `-1` si no hay alquiler activo para ese par.

**Consulta interna:**
```sql
SELECT DATEDIFF(CURRENT_DATE, fecha_recogida)
FROM alquila
WHERE id_cliente = p_id_cliente
  AND id_ejemplar = p_id_ejemplar
  AND fecha_entrega IS NULL;
```

### FUNCIÓN 2 — `fn_pelicula_mas_alquilada_categoria`
```sql
SELECT fn_pelicula_mas_alquilada_categoria(id_categoria);
```
**¿Qué devuelve?** El título de la película más alquilada dentro de una categoría. `'Sin datos'` si la categoría no tiene alquileres.

**Consulta interna:** JOIN de 4 tablas (pelicula → pertenece → ejemplar → alquila), agrupa por película y ordena por COUNT DESC.

---

## 6. Script dashboard

### ¿Qué hace?
1. Llama a `sp_aplicar_penalizacion_retrasos()` → actualiza penalizaciones.
2. Llama a `sp_generar_ranking_peliculas()` → actualiza ranking.
3. Limpia la tabla `dashboard` con `TRUNCATE`.
4. Inserta ~16 métricas en `dashboard(metrica, valor, fecha_calculo)`.
5. Hace un SELECT final formateado.

### Métricas incluidas
| Tipo | Métrica |
|---|---|
| Global | Total alquileres, activos, clientes, películas, ejemplares disponibles/en retraso |
| Ingresos | Ingresos totales y medios estimados |
| Películas | Más/menos alquilada global, top por categoría (Drama, SciFi, Thriller) |
| Clientes | Cliente con más alquileres, alquiler activo más antiguo |
| Temporal | Alquileres en el año actual, mes con más alquileres, director más popular |

### Tabla `dashboard`
```sql
CREATE TABLE dashboard (
    id_metrica    INT AUTO_INCREMENT PRIMARY KEY,
    metrica       VARCHAR(100) NOT NULL,
    valor         VARCHAR(255) NOT NULL,
    fecha_calculo DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

---

## 7. Cómo ejecutarlo

```bash
# Opción 1: desde terminal
mysql -u root -p < videoclubTrabajo.sql

# Opción 2: desde MySQL Workbench / DBeaver
# Abrir videoclubTrabajo.sql y ejecutar todo (Ctrl+Shift+Enter)
```

**Orden de ejecución dentro del archivo:**
1. DROP/CREATE BD y tablas
2. Inserciones de datos
3. Triggers (DELIMITER $$)
4. Procedimientos con transacción (DELIMITER $$)
5. Procedimientos con cursores (DELIMITER $$)
6. Funciones (DELIMITER $$)
7. Script dashboard (ejecuta CALLs + INSERT + SELECT)
8. Pruebas adicionales

---

## 8. Preguntas de examen

**¿Por qué usamos DELIMITER $$?**
Porque el cuerpo de triggers, procedimientos y funciones contiene `;` internos. Si no cambiamos el delimitador, el cliente SQL interpretaría esos `;` como el final de la sentencia antes de tiempo.

**¿Qué diferencia hay entre EXIT HANDLER y CONTINUE HANDLER?**
- `EXIT HANDLER`: cuando salta el error, sale del bloque BEGIN...END y no continúa.
- `CONTINUE HANDLER`: cuando salta (ej. NOT FOUND), continúa ejecutando la siguiente línea.

**¿Por qué `FOR UPDATE` en sp_registrar_alquiler?**
Bloquea la fila del ejemplar durante la transacción para evitar que dos clientes alquilen el mismo ejemplar simultáneamente (condición de carrera).

**¿Qué es `ROW_COUNT()`?**
Devuelve el número de filas afectadas por el último INSERT/UPDATE/DELETE. Se usa en `sp_registrar_devolucion` para saber si realmente se actualizó alguna fila.

**¿Qué hace `SIGNAL SQLSTATE '45000'`?**
Lanza un error personalizado. `45000` es el código genérico para errores definidos por el usuario en MariaDB/MySQL. Se puede añadir `SET MESSAGE_TEXT = 'mensaje'` para personalizar el texto.

**¿Cuándo se dispara `trg_devolucion_ejemplar`?**
Solo cuando `fecha_entrega` pasa de `NULL` a un valor. Si se actualiza otro campo del alquiler sin tocar `fecha_entrega`, el trigger no hace nada (condición `OLD.fecha_entrega IS NULL AND NEW.fecha_entrega IS NOT NULL`).

**¿Por qué `TRUNCATE` y no `DELETE` en el ranking?**
`TRUNCATE` es más rápido (no genera undo log fila a fila) y resetea el `AUTO_INCREMENT`. Aquí la posición es la PK, así que empezamos siempre desde 1.

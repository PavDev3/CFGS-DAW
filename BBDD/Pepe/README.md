# biblioteca_bd — Documentación

Sistema de gestión de una biblioteca: libros, ejemplares físicos, usuarios, préstamos y renovaciones.

---

## Índice
1. [Modelo de datos](#1-modelo-de-datos)
2. [Tablas](#2-tablas)
3. [Relaciones](#3-relaciones)
4. [Triggers](#4-triggers)
5. [Procedimientos](#5-procedimientos)
6. [Funciones](#6-funciones)
7. [Dashboard](#7-dashboard)
8. [Cómo ejecutarlo](#8-cómo-ejecutarlo)

---

## 1. Modelo de datos

```
Autor ────────── es_escrito ────────── Libros ────────── pertenece ────────── Categoria
                                          │
                                     es_editado_por
                                          │
                                       Editorial
                                          │
                                       Ejemplares
                                          │
                                       Prestamo
                                          │
                                       Usuarios ────── amigos ────── Usuarios
```

Un **libro** (ISBN) puede tener varios **ejemplares** físicos. Cada exemplar puede prestarse a un **usuario**. El préstamo registra fechas y renovaciones.

---

## 2. Tablas

### Autor
| Campo | Tipo | Descripción |
|---|---|---|
| id_autor | INT AUTO_INCREMENT | PK |
| nombre_autor | VARCHAR(100) NOT NULL | Nombre completo |
| pais_autor | VARCHAR(50) | País de origen |
| fecha_registro | TIMESTAMP | Se rellena automáticamente al insertar |

---

### Editorial
| Campo | Tipo | Descripción |
|---|---|---|
| id_editorial | INT AUTO_INCREMENT | PK |
| nombre_editorial | VARCHAR(100) UNIQUE | No puede haber dos editoriales con el mismo nombre |

---

### Categoria
| Campo | Tipo | Descripción |
|---|---|---|
| id_categoria | INT AUTO_INCREMENT | PK |
| nombre | VARCHAR(50) UNIQUE | Ficción, Poesía, Ensayo… No se permiten duplicados |
| descripcion | TEXT | Descripción libre del género |

---

### Libros
| Campo | Tipo | Descripción |
|---|---|---|
| isbn | VARCHAR(20) | PK — identificador internacional único del libro |
| titulo | VARCHAR(200) NOT NULL | Título del libro |
| fecha_publicacion | DATE | Fecha de primera publicación |

---

### Ejemplares
Copias físicas de un libro. Un mismo ISBN puede tener varios ejemplares (EJ001, EJ002…).

| Campo | Tipo | Descripción |
|---|---|---|
| id_ejemplar | INT AUTO_INCREMENT | PK |
| isbn | VARCHAR(20) NOT NULL | FK → Libros |
| copia_ejemplar | VARCHAR(50) | Código identificador de la copia (EJ001…) |
| estado_ejemplar | VARCHAR(20) | `disponible` / `prestado` / `mantenimiento` |

**Estados posibles:**
- `disponible` → se puede prestar
- `prestado` → tiene un préstamo activo (lo cambia automáticamente el trigger `trg_actualizar_estado_prestamo`)
- `mantenimiento` → retirado temporalmente, no se presta ni aparece en búsquedas

---

### Usuarios
| Campo | Tipo | Descripción |
|---|---|---|
| id_usuario | INT AUTO_INCREMENT | PK |
| nombre | VARCHAR(100) NOT NULL | Nombre completo |
| email | VARCHAR(100) UNIQUE | Un correo no puede tener dos cuentas |
| fecha_registro | TIMESTAMP | Se rellena automáticamente al insertar |

---

### Prestamo
Tabla central del sistema. Registra qué usuario tiene qué ejemplar y en qué estado está el préstamo.

| Campo | Tipo | Descripción |
|---|---|---|
| id_usuario | INT | PK + FK → Usuarios |
| id_ejemplar | INT | PK + FK → Ejemplares |
| fecha_prestamo | DATE | PK — permite registrar el mismo ejemplar en fechas distintas |
| fecha_devolucion | DATE | `NULL` = préstamo activo (no devuelto aún) |
| Num_Renovaciones | INT | Contador de renovaciones. Máximo 3 (controlado por `sp_renovar_vencidos`) |

> La clave primaria compuesta `(id_usuario, id_ejemplar, fecha_prestamo)` permite que el mismo usuario tome prestado el mismo ejemplar en momentos distintos sin conflicto.

---

### Tablas de relación N:M

| Tabla | Relación |
|---|---|
| `es_escrito` | Autor ↔ Libros (un libro puede tener varios autores) |
| `es_editado_por` | Editorial ↔ Libros (distintas ediciones del mismo libro) |
| `pertenece` | Libros ↔ Categoria (un libro puede pertenecer a varias categorías) |
| `amigos` | Usuarios ↔ Usuarios (red social de la biblioteca) |

---

## 3. Relaciones

Todas las claves foráneas están definidas con `ON DELETE CASCADE ON UPDATE CASCADE`: si se elimina un usuario o un ejemplar, sus préstamos se eliminan automáticamente en cascada.

```
Libros  (1) ──── (N) Ejemplares
Libros  (N) ──── (N) Autor        [via es_escrito]
Libros  (N) ──── (N) Editorial    [via es_editado_por]
Libros  (N) ──── (N) Categoria    [via pertenece]
Usuarios (1) ─── (N) Prestamo
Ejemplares (1) ── (N) Prestamo
Usuarios (N) ─── (N) Usuarios    [via amigos]
```

---

## 4. Triggers

### trg_evitar_doble_prestamo
- **Evento:** `BEFORE UPDATE` en `Ejemplares`
- **Función:** Impide marcar como `prestado` un ejemplar que ya está `prestado`.
- **Mecanismo:** Llama a una función inexistente (`ERROR_LIBRO_YA_PRESTADO()`) para forzar un error y abortar la operación. Compatible con XAMPP/MariaDB. En MySQL puro se usaría `SIGNAL SQLSTATE`.

```sql
IF NEW.estado_ejemplar = 'prestado' AND OLD.estado_ejemplar = 'prestado' THEN
    SET NEW.estado_ejemplar = ERROR_LIBRO_YA_PRESTADO(); -- fuerza error
END IF;
```

---

### trg_actualizar_estado_prestamo
- **Evento:** `AFTER INSERT` en `Prestamo`
- **Función:** Cuando se inserta un préstamo, cambia automáticamente el estado del ejemplar a `prestado`.
- **Trabaja con:** `sp_registrar_prestamo` — el procedimiento comprueba disponibilidad antes del INSERT y este trigger ejecuta el cambio de estado sin necesitar un UPDATE explícito.

```sql
UPDATE Ejemplares SET estado_ejemplar = 'prestado' WHERE id_ejemplar = NEW.id_ejemplar;
```

---

## 5. Procedimientos

### sp_registrar_prestamo
Registra un nuevo préstamo de forma transaccional.

| Parámetro | Tipo | Descripción |
|---|---|---|
| p_usuario | INT | ID del usuario |
| p_ejemplar | INT | ID del ejemplar a prestar |
| p_fecha | DATE | Fecha del préstamo (`NULL` = hoy) |

**Flujo:**
1. Lee el `estado_ejemplar` del ejemplar dentro de una transacción.
2. Si está `disponible` → inserta el préstamo y hace `COMMIT`. El trigger cambia el estado a `prestado`.
3. Si no está disponible → hace `ROLLBACK` y devuelve mensaje de error.

```sql
CALL sp_registrar_prestamo(1, 6, CURDATE());
```

---

### sp_devolver_ejemplar
Registra la devolución de un ejemplar de forma transaccional.

| Parámetro | Tipo | Descripción |
|---|---|---|
| p_usuario | INT | ID del usuario que devuelve |
| p_ejemplar | INT | ID del ejemplar devuelto |
| p_fecha | DATE | Fecha de devolución (`NULL` = hoy) |

**Flujo:**
1. Actualiza `fecha_devolucion` del préstamo activo (`fecha_devolucion IS NULL`).
2. Comprueba `ROW_COUNT()` para verificar si había un préstamo activo.
3. Si lo había → actualiza el ejemplar a `disponible` y `COMMIT`.
4. Si no lo había → `ROLLBACK` (no existe ese préstamo activo).

```sql
CALL sp_devolver_ejemplar(3, 5, CURDATE());
```

---

### sp_renovar_vencidos ⚠️ usa cursor
Renueva automáticamente los préstamos activos vencidos.

**Criterio de vencido:** `fecha_devolucion IS NULL` y más de 30 días desde `fecha_prestamo`.

**Límite:** máximo 3 renovaciones por préstamo. Los que ya tienen 3 se excluyen directamente en el `SELECT` del cursor.

**Al renovar cada préstamo:**
- Incrementa `Num_Renovaciones` en 1.
- Reinicia `fecha_prestamo` a `CURDATE()` → da otros 30 días de plazo.

**Funcionamiento del cursor:**
```
DECLARE cur CURSOR FOR SELECT ... WHERE vencido AND renovaciones < 3
OPEN cur
LOOP
  FETCH cur → si fin LEAVE
  UPDATE Prestamo SET Num_Renovaciones+1, fecha_prestamo=CURDATE()
END LOOP
CLOSE cur
```

```sql
CALL sp_renovar_vencidos();
-- Resultado: "Proceso finalizado. Se han renovado X préstamos."
```

---

### sp_limpiar_mantenimiento ⚠️ usa cursor
Elimina los ejemplares en estado `mantenimiento` que no tienen ningún préstamo (ni activo ni histórico).

Si un ejemplar en mantenimiento tiene historial de préstamos, **no se elimina** para preservar la trazabilidad.

```sql
CALL sp_limpiar_mantenimiento();
-- Resultado: "Limpieza completada. Se eliminaron X ejemplares sin uso."
```

---

## 6. Funciones

> En MySQL con binary logging activo hay que ejecutar primero:
> ```sql
> SET GLOBAL log_bin_trust_function_creators = 1;
> ```

### fn_prestamos_activos(p_usuario)
Devuelve el número de préstamos activos (no devueltos) de un usuario.

```sql
SELECT fn_prestamos_activos(3);  -- → 1
```

---

### fn_renovaciones_libro(p_isbn)
Devuelve el total acumulado de renovaciones de todos los préstamos de cualquier ejemplar de un libro.
Usa `IFNULL(..., 0)` para devolver 0 en vez de NULL cuando el libro no tiene préstamos.

```sql
SELECT fn_renovaciones_libro('978-0307474728');  -- → suma de todas las renovaciones
```

---

## 7. Dashboard

### Tabla dashboard_biblioteca
Cada llamada a `sp_actualizar_dashboard` inserta una nueva fila con un snapshot del estado actual. Permite ver la evolución histórica de la biblioteca.

| Campo | Descripción |
|---|---|
| id | PK autoincremental |
| fecha | Momento del snapshot |
| total_libros | Libros distintos en catálogo |
| total_autores | Autores registrados |
| total_usuarios | Usuarios registrados |
| prestamos_activos | Préstamos sin devolver a día de hoy |
| renovaciones_ultimo_mes | Suma de renovaciones de préstamos iniciados este mes |
| libros_mas_prestados | Top 3 títulos por número de préstamos (separados por coma) |
| categoria_top | Categoría con más préstamos en total |

```sql
CALL sp_actualizar_dashboard();
SELECT * FROM dashboard_biblioteca ORDER BY fecha DESC LIMIT 1;
```

---

## 8. Cómo ejecutarlo

### Con Docker (recomendado para pruebas)
```bash
# Levantar el contenedor
docker run -d --name biblioteca-mysql \
  -e MYSQL_ROOT_PASSWORD=root \
  -e MYSQL_DATABASE=biblioteca_bd \
  -p 3307:3306 mysql:8.0

# Esperar a que arranque (~10s) y cargar el script
docker exec biblioteca-mysql mysql -uroot -proot \
  -e "SET GLOBAL log_bin_trust_function_creators = 1;"

docker exec -i biblioteca-mysql mysql -uroot -proot < pepebbdd.sql
```

Conexión desde DBeaver / MySQL Workbench: `localhost:3307`, usuario `root`, password `root`.

```bash
# Parar el contenedor al terminar
docker stop biblioteca-mysql

# Eliminar el contenedor (borra los datos)
docker rm biblioteca-mysql
```

### Con XAMPP
Abrir el archivo en phpMyAdmin o ejecutar desde la consola MySQL:
```sql
SOURCE /ruta/a/pepebbdd.sql;
```

### Pruebas rápidas
```sql
-- Registrar un préstamo
CALL sp_registrar_prestamo(1, 6, CURDATE());

-- Devolver un ejemplar
CALL sp_devolver_ejemplar(1, 6, CURDATE());

-- Renovar todos los vencidos
CALL sp_renovar_vencidos();

-- Limpiar ejemplares en mantenimiento sin uso
CALL sp_limpiar_mantenimiento();

-- Cuántos préstamos activos tiene el usuario 3
SELECT fn_prestamos_activos(3);

-- Total de renovaciones del libro Rayuela
SELECT fn_renovaciones_libro('978-0307475473');

-- Actualizar el dashboard y ver el último snapshot
CALL sp_actualizar_dashboard();
SELECT * FROM dashboard_biblioteca ORDER BY fecha DESC LIMIT 1;
```

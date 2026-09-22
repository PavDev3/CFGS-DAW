# Tema 2 — Modelo Entidad-Relación (ER)

← [[BBDD-Tema1]] | [[BBDD]] | Siguiente: [[BBDD-Tema3]]

---

## El Modelo Entidad-Relación

El modelo Entidad-Relación (ER) es una herramienta de diseño conceptual de bases de datos que representa los datos y sus relaciones mediante diagramas gráficos.

### Conceptos básicos

| Concepto | Descripción |
|----------|-------------|
| **Entidad** | Objeto del mundo real con existencia propia (ej: Cliente, Producto) |
| **Atributo** | Propiedad de una entidad (ej: nombre, DNI) |
| **Relación** | Asociación entre entidades |
| **Cardinalidad** | Número de instancias que participan en una relación |

### Tipos de Cardinalidad

- **1:1** — Uno a uno
- **1:N** — Uno a muchos
- **N:M** — Muchos a muchos

---

## Atributos Especiales

- **Atributo clave (PK):** Identifica de forma única a una entidad
- **Atributo multivaluado:** Puede tener varios valores para la misma entidad (ej: teléfonos de un cliente)
- **Atributo derivado:** Se calcula a partir de otros atributos (ej: antigüedad calculada desde fecha_contratacion)

---

## Ejemplo: Diagrama ER — Mapeo Instituto

```
PERSONA:
    - dni (PK)
    - nombre
    - id_grupo (FK) -> GRUPO.id
    (Relación 1:N con GRUPO - "pertenece", FK en PERSONA)

GRUPO:
    - id (PK)
    - id_nivel (FK) -> NIVEL.id
    (Relación 1:1 con NIVEL - "tiene", FK en GRUPO)

PROFESOR:
    - dni (PK)
    - nombre
    - id_grupo (FK) -> GRUPO.id
    (Relación 1:N con GRUPO - "enseña", FK en PROFESOR)

NIVEL:
    - id (PK)

TABLA PARA ATRIBUTO MULTIVALUADO:
PROFESOR_NACIONALIDAD:
    - dni_profesor (FK) -> PROFESOR.dni
    - nacionalidad
    - PRIMARY KEY (dni_profesor, nacionalidad)
```

---

## Ejemplo: Diagrama ER — Videoclub

### Entidades y Atributos

**Película**
- ID (bigint, PK), Título, Director, Año de lanzamiento, Género, Clasificación

**Director**
- ID (bigint, PK), Nombre, Nacionalidad, Fecha de nacimiento

**Cliente**
- ID (bigint, PK), Nombre, Dirección, Teléfono, Email

**Alquiler**
- ID (bigint, PK), Fecha de alquiler, Fecha de devolución, ID de Cliente (FK), ID de Película (FK)

**Categoría**
- ID (bigint, PK), Nombre, Descripción

**Proveedor**
- ID (bigint, PK), Nombre, Dirección, Teléfono

### Relaciones del Videoclub

| Relación | Descripción |
|----------|-------------|
| Película - Director | N:M — Una película puede tener varios directores y viceversa |
| Película - Alquiler | 1:N — Una película puede estar en varios alquileres |
| Cliente - Alquiler | 1:N — Un cliente puede tener varios alquileres |
| Película - Categoría | N:M — Una película puede pertenecer a varias categorías |
| Película - Proveedor | 1:N — Un proveedor puede suministrar varias películas |

---

## Tareas Asociadas

- [[BBDD-Tareas-Tema2]]

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Temas/2/Tema02. Teoria.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.1/ER_1.drawio`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Tareas/Tema 2/Tarea2.4/Ejercicio2_04.drawio`

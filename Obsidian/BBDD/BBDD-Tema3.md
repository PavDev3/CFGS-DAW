# Tema 3 — Modelo Relacional y Mapeo

← [[BBDD-Tema2]] | [[BBDD]] | Siguiente: [[BBDD-Tema4]]

---

## El Modelo Relacional

El modelo relacional organiza los datos en **tablas** (relaciones) compuestas por filas (tuplas) y columnas (atributos).

### Reglas de mapeo ER → Relacional

1. **Entidades fuertes** → Tabla con PK
2. **Entidades débiles** → Tabla con PK compuesta (PK propia + FK de la entidad fuerte)
3. **Relaciones 1:1** → FK en cualquiera de las dos tablas
4. **Relaciones 1:N** → FK en la tabla del lado N
5. **Relaciones N:M** → Tabla intermedia con las dos FKs
6. **Atributos multivaluados** → Tabla aparte con FK

---

## Ejercicios de Mapeo

### Ejercicio 1: Academia de Inglés

```
Alumno (id_alumno, nombre, nivel_ingles)
    PK: id_alumno

Profesor (id_profesor, nombre, nacionalidad)
    PK: id_profesor

Grupo (id_grupo, nivel_ingles, id_profesor)
    PK: id_grupo
    FK: id_profesor → Profesor(id_profesor)

Alumno_Grupo (id_alumno, id_grupo)  ← Relación N:M
    PK: (id_alumno, id_grupo)
    FK: id_alumno → Alumno(id_alumno)
    FK: id_grupo → Grupo(id_grupo)
```

### Ejercicio 2: Centro de Salud

```
Médico (id, datos_personales, año_colegiación)
    PK: id

Paciente (id, datos_personales, medico_id)
    PK: id
    FK: medico_id → Médico(id)

Sala (id, ubicación)
    PK: id

Horario (medico_id, sala_id, horario)  ← Relación N:M
    PK: (medico_id, sala_id)
    FK: medico_id → Médico(id)
    FK: sala_id → Sala(id)
```

### Ejercicio 3: Banco

```
Sucursal (num_sucursal, ciudad, activo)
    PK: num_sucursal

Cliente (codigo, dni, nombre, apellidos, dirección, ciudad)
    PK: codigo

Cuenta (num_cuenta, saldo, sucursal_id)
    PK: num_cuenta
    FK: sucursal_id → Sucursal(num_sucursal)

Transacción (id, cuenta_id, fecha, tipo_operación, cantidad)
    PK: id
    FK: cuenta_id → Cuenta(num_cuenta)

Cliente_Cuenta (cliente_id, cuenta_id)  ← Relación N:M
    PK: (cliente_id, cuenta_id)
    FK: cliente_id → Cliente(codigo)
    FK: cuenta_id → Cuenta(num_cuenta)
```

### Ejercicio 4: Instituto

```
Profesor (dni, nombre, dirección, teléfono)
    PK: dni

Módulo (código, nombre, profesor_dni)
    PK: código
    FK: profesor_dni → Profesor(dni)

Alumno (n_expediente, nombre, apellidos, fecha_nacimiento)
    PK: n_expediente

Grupo (nombre, nivel_educativo, delegado_expediente)
    PK: nombre
    FK: delegado_expediente → Alumno(n_expediente)

Alumno_Módulo (alumno_expediente, módulo_codigo)
    PK: (alumno_expediente, módulo_codigo)
    FK: alumno_expediente → Alumno(n_expediente)
    FK: módulo_codigo → Módulo(código)
```

### Ejercicio 5: Empresa de Venta de Automóviles

```
Coche (matrícula, marca, modelo, color, precio_venta)
    PK: matrícula

Cliente (codigo, nif, nombre, dirección, ciudad, teléfono)
    PK: codigo

Revisión (codigo, coche_matrícula, cambio_filtro, cambio_aceite, cambio_frenos, otros)
    PK: codigo
    FK: coche_matrícula → Coche(matrícula)

Compra (cliente_codigo, coche_matrícula)
    PK: (cliente_codigo, coche_matrícula)
    FK: cliente_codigo → Cliente(codigo)
    FK: coche_matrícula → Coche(matrícula)
```

---

## Normalización

La **normalización** es el proceso de organizar datos en tablas para reducir la redundancia y mejorar la integridad.

| Forma Normal | Regla |
|--------------|-------|
| **1NF** | Cada campo contiene un solo valor atómico; no se permiten listas |
| **2NF** | Cada atributo no clave depende de la clave primaria completa |
| **3NF** | Elimina dependencias transitivas |
| **BCNF** | Versión más estricta de la 3NF para dependencias funcionales complejas |

---

## Tareas Asociadas

- [[BBDD-Tareas-Tema3]]

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Temas/3/Tema03. Teoria.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Temas/3/Resumen teoria - Diseño de bases de datos.pdf`

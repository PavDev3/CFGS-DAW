# Tarea 3.1.1. El Objeto Global Temporal

**Cierre:** jueves 19 de noviembre, 00:00.

## Actividad Práctica 3.1.8. Gestor de Plazos de Matrícula y Convocatorias con Temporal

Código en [`gestor-plazos-temporal.js`](./gestor-plazos-temporal.js).

Requiere un entorno con `Temporal` disponible (navegadores recientes, o Node con `--harmony-temporal`; probado con Node 24).

Notas:

- El enunciado no especifica cuándo arranca exactamente el periodo de alegaciones, así que se asume que comienza el mismo día de la publicación provisional de notas (15 de junio de 2027).
- Tampoco especifica la fecha de apertura de la plataforma de matriculación internacional, así que se usa esa misma fecha (15 de junio de 2027) a las 09:00 en `Europe/Madrid` para el punto 4.

Salida de ejemplo al ejecutar el script:

```
Publicación provisional de notas: 2027-06-15
Fin del periodo de alegaciones: 2027-06-18
Resolución definitiva: 2027-07-02
¿La fecha original sigue intacta? true
Faltan 6 meses y 29 días para el inicio de las alegaciones.
Apertura en Madrid: 2027-06-15T09:00:00+02:00[Europe/Madrid]
Hora equivalente en Tokio: 2027-06-15T16:00:00+09:00[Asia/Tokyo]
```

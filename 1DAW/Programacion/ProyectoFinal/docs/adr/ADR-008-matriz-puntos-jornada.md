# ADR-008 — Matriz 2D para puntos acumulados por jornada y equipo

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

La clasificacion de la liga requiere conocer los puntos de cada equipo en cada jornada, no solo el total acumulado. Esto permite mostrar la evolucion a lo largo del campeonato. El numero de jornadas (5) y el numero maximo de equipos (6) son fijos y conocidos en tiempo de compilacion.

## Decision

Se usa un array bidimensional de enteros en `Liga`:

```java
int[][] matrizPuntos = new int[NUM_JORNADAS][MAX_EQUIPOS]; // [5][6]
```

- **Filas** (indice 0–4): jornadas de la liga.
- **Columnas** (indice 0–5): equipos, en el mismo orden en que estan en `ArrayList<Equipo>`.

Al registrar el resultado de un partido se busca el indice del equipo en la lista y se suma al elemento correspondiente de la matriz (3 pts victoria, 1 pt empate, 0 pts derrota).

## Consecuencias

**Ventajas:**
- Acceso en O(1): `matrizPuntos[jornada][equipoIndex]` sin busquedas.
- Memoria compacta: 30 enteros (5 x 6) frente a objetos con campos adicionales.
- Sencillo de recorrer con dos bucles for para mostrar la tabla completa.

**Desventajas:**
- El indice de equipo en la matriz esta acoplado al orden de insercion en `ArrayList<Equipo>`. Si se elimina un equipo de la lista, los indices de los equipos posteriores cambian y la matriz queda corrupta. Actualmente el sistema no permite eliminar equipos, lo que evita este problema.
- Los tamanos son constantes en tiempo de compilacion (`NUM_JORNADAS = 5`, `MAX_EQUIPOS = 6`). Cambiar las dimensiones de la liga exige modificar el codigo fuente.

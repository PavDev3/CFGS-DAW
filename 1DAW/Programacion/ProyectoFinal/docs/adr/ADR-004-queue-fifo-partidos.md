# ADR-004 — Queue (FIFO) para la cola de partidos pendientes

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

Los partidos deben disputarse en el orden en que fueron registrados: el primero en anadirse al calendario es el primero en jugarse. Se necesita una estructura que imponga ese orden de manera natural y exprese la semantica de "siguiente partido".

## Decision

Se usa `Queue<Partido>` implementada con `LinkedList`:

```java
Queue<Partido> colaPartidos = new LinkedList<>();
```

- Al registrar un partido en el calendario se anade con `offer()`.
- Para ver el proximo sin jugarlo se usa `peek()`.
- Para jugarlo (extraerlo y procesar su resultado) se usa `poll()`.

## Consecuencias

**Ventajas:**
- La interfaz `Queue` comunica la intencion FIFO con mas claridad que un `ArrayList` donde el programador tendria que recordar usar siempre el indice 0.
- `peek()` / `poll()` son operaciones O(1) con `LinkedList`.
- Si se vacia la cola, `poll()` devuelve `null` en lugar de lanzar excepcion, lo que simplifica la comprobacion antes de jugar.

**Desventajas:**
- No es posible acceder a un partido en posicion arbitraria (no hay `get(i)`). Para mostrar toda la cola hay que iterar con un bucle for-each o convertirla a lista.
- `LinkedList` tiene mayor consumo de memoria que `ArrayList` por los punteros de los nodos; para el tamano maximo de la liga (30 partidos en 5 jornadas) esto es irrelevante.

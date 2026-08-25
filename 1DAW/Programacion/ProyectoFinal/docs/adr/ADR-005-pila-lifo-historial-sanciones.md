# ADR-005 — ArrayDeque como pila (LIFO) para historial y deshacer sanciones

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

El sistema necesita dos comportamientos de tipo LIFO:
1. **Historial de acciones:** mostrar las operaciones mas recientes primero.
2. **Deshacer sancion:** revertir la ultima sancion aplicada, no una arbitraria.

Ambos casos requieren recuperar siempre el elemento mas reciente, que es la definicion de una pila (LIFO: Last In, First Out).

## Decision

Se usan dos `ArrayDeque<String>` en `Liga` con semantica de pila:

```java
ArrayDeque<String> pilaAcciones;   // historial de operaciones
ArrayDeque<String> pilaSanciones;  // IDs de jugadores sancionados
```

Cada operacion de estado (anadir persona, crear equipo, aplicar sancion...) hace `push()` de una descripcion en `pilaAcciones`.

Cuando se sanciona a un jugador, se hace `push()` de su ID en `pilaSanciones`. Al deshacer, se hace `pop()` del ultimo ID y se llama a `jugador.setSancionado(false)`.

Se elige `ArrayDeque` sobre `Stack` porque la javadoc oficial de Java recomienda `ArrayDeque` para implementaciones de pila al ser mas rapida y no estar sincronizada innecesariamente.

## Consecuencias

**Ventajas:**
- `push()` y `pop()` son O(1) amortizado con `ArrayDeque`.
- La semantica LIFO hace que deshacer multiples sanciones sea trivial: cada llamada a la opcion 11 revierte la inmediatamente anterior.
- El historial de acciones muestra las operaciones mas recientes sin necesidad de invertir ninguna lista.

**Desventajas:**
- `pilaSanciones` guarda IDs como `String`, lo que exige buscar el jugador por ID (O(n)) para revertir la sancion. Una alternativa seria guardar referencias directas al objeto `Jugador`, pero eso acopla mas la estructura de datos con el modelo.
- Si se elimina un jugador de la liga mientras tiene una sancion pendiente en la pila, el `pop()` posterior devolveria un ID inexistente. Actualmente no hay validacion para este caso.

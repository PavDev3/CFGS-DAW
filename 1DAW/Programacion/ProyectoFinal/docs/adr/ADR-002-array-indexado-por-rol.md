# ADR-002 — Array fijo de titulares indexado por Rol.ordinal()

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

Cada equipo debe tener exactamente 5 jugadores titulares, uno por cada rol del juego (TOP, JUNGLE, MID, ADC, SUPPORT). El enunciado exige que no pueda haber dos titulares con el mismo rol, y que la comprobacion de disponibilidad de un rol sea eficiente.

## Decision

Se usa un array fijo `Jugador[] titulares = new Jugador[5]` donde el indice de cada posicion corresponde al ordinal del enum `Rol`:

```java
titulares[jugador.getRol().ordinal()] = jugador;
// TOP=0, JUNGLE=1, MID=2, ADC=3, SUPPORT=4
```

Si la posicion ya tiene un jugador asignado, se lanza `RolNoDisponibleException` antes de insertar.

Los suplentes, cuyo numero es variable y sin restriccion de rol, se almacenan en un `ArrayList<Jugador>`.

## Consecuencias

**Ventajas:**
- Acceso y comprobacion en O(1): `titulares[rol.ordinal()]` sin bucles.
- Unicidad de rol garantizada por la estructura: no es posible tener dos TOP de forma accidental.
- El array fijo comunica la intencion (exactamente 5 posiciones) mejor que una lista dinamica.
- Las posiciones null indican roles vacantes de forma clara.

**Desventajas:**
- El enum `Rol` y el array estan acoplados por el ordinal. Si se reordenan los valores del enum el sistema se rompe silenciosamente.
- No es posible tener mas de 5 posiciones de titular aunque el deporte cambiase de reglas.

# ADR-003 — HashSet para deteccion de duplicados en O(1)

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

La liga no puede tener dos personas con el mismo ID, dos equipos con el mismo nombre ni dos partidos con el mismo ID. Si los elementos se almacenaran solo en las listas `ArrayList`, detectar un duplicado antes de insertar requeriria recorrer toda la lista (O(n)).

## Decision

Se mantienen tres `HashSet<String>` auxiliares en `Liga`, uno por tipo de entidad:

```java
HashSet<String> idsPersonas;
HashSet<String> nombresEquipos;
HashSet<String> idsPartidos;
```

Antes de cualquier insercion se comprueba `set.contains(clave)`. Si devuelve `true`, se lanza `IllegalArgumentException` y no se inserta. Si devuelve `false`, se anade a la lista y al set a la vez.

## Consecuencias

**Ventajas:**
- `contains()` en O(1) independientemente de cuantos elementos haya, frente a O(n) con `ArrayList.contains()`.
- La logica de insercion queda clara y centralizada en `Liga`.

**Desventajas:**
- Cada insercion actualiza dos estructuras (ArrayList + HashSet), lo que supone redundancia de datos.
- Si en algun momento se elimina un elemento de la lista sin eliminar su clave del HashSet (o viceversa), las dos estructuras quedan desincronizadas. Este riesgo existe actualmente y debe gestionarse con cuidado en los metodos de eliminacion.

# ADR-007 — Liga como orquestador central (unico punto de registro)

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

La aplicacion gestiona multiples entidades relacionadas (equipos, personas, partidos, incidencias) que deben mantenerse consistentes entre si. Por ejemplo, al registrar un partido hay que actualizar estadisticas de equipos y jugadores, la cola de partidos y la matriz de puntos al mismo tiempo.

## Decision

Se centraliza toda la logica de negocio y las estructuras de datos en la clase `Liga`. Es el unico punto desde el que se crean y registran entidades. `Main.java` solo recoge la entrada del usuario y delega en `Liga`.

`Liga` contiene:
- Las cuatro listas principales (equipos, personas, partidos, incidencias).
- Los tres HashSet de deduplicacion.
- La cola FIFO y las dos pilas LIFO.
- La matriz de puntos.
- Todos los metodos de negocio: registrar, buscar, fichar, sancionar, clasificar, calcular estadisticas.

## Consecuencias

**Ventajas:**
- Un solo lugar donde buscar cualquier logica de negocio, lo que facilita la lectura y el mantenimiento.
- Las invariantes (p. ej. HashSet siempre sincronizado con ArrayList) se protegen dentro de `Liga` sin que `Main` deba conocerlas.
- Facil de probar en aislamiento: se puede instanciar `Liga` en un test sin necesidad de la interfaz de usuario.

**Desventajas:**
- `Liga.java` crece (301 lineas) y asume muchas responsabilidades. En un proyecto mas grande se separaria en servicios (PersonaService, PartidoService, etc.).
- Las clases del modelo (`Equipo`, `Jugador`) tambien contienen metodos de negocio (p. ej. `Equipo.ficharTitular()`), lo que crea cierta ambiguedad sobre donde vive cada responsabilidad.

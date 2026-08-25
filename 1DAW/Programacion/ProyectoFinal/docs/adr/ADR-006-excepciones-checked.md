# ADR-006 — Excepciones personalizadas checked para violaciones de reglas de negocio

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

Hay dos situaciones de negocio que deben interrumpir una operacion de fichaje de forma controlada:
1. Intentar fichar un jugador con el rol ya ocupado en los titulares.
2. Intentar fichar un jugador que esta sancionado.

Estas no son errores de programacion (como `NullPointerException`) sino violaciones de las reglas de la liga que el usuario puede provocar intencionadamente desde el menu.

## Decision

Se crean dos excepciones propias que extienden `Exception` (checked, no unchecked):

```java
public class RolNoDisponibleException extends Exception { ... }
public class JugadorSancionadoException extends Exception { ... }
```

Son excepciones comprobadas (checked) porque el compilador obliga a quien llama a tratarlas explicitamente, garantizando que ningun punto de fichaje pueda ignorarlas por descuido.

**Flujo:**
- `Equipo.ficharTitular()` lanza ambas si procede.
- `Jugador.verificarDisponibilidad()` lanza `JugadorSancionadoException`.
- `Main` las captura en bloques `catch` separados y muestra el mensaje apropiado al usuario.

## Consecuencias

**Ventajas:**
- El compilador obliga a gestionar ambas condiciones; no es posible olvidarlas.
- Los bloques `catch` separados permiten mostrar mensajes distintos al usuario segun el motivo del rechazo.
- Los metodos de `Equipo` y `Jugador` quedan limpios: no mezclan logica de negocio con mensajes de interfaz de usuario.

**Desventajas:**
- Al ser checked, todos los metodos en la cadena de llamadas deben declarar `throws` o capturar la excepcion, lo que puede resultar verboso.
- Para violaciones menos criticas (como limites maximos) se usa `IllegalArgumentException` (unchecked), lo que crea cierta inconsistencia en la estrategia de excepciones del proyecto.

# Tarea 3.3. Control de la apariencia y manipulación del entorno de ventanas

**Cierre:** jueves 26 de noviembre, 00:00. Enunciado original en [`Enunciado_3.3.pdf`](./Enunciado_3.3.pdf).

A petición expresa: esta tarea va sin Pico CSS ni ningún framework, solo HTML y JS planos (con el CSS mínimo imprescindible para que funcione cada ejercicio, p.ej. las clases que se alternan con `classList`).

## Actividad 3.3.1 - Caja con dimensiones dinámicas

Código en [`3.3.1-caja-dimensiones.html`](./3.3.1-caja-dimensiones.html) / [`3.3.1-caja-dimensiones.js`](./3.3.1-caja-dimensiones.js).

## Actividad 3.3.2 - Scroll suave al inicio

Código en [`3.3.2-scroll-suave.html`](./3.3.2-scroll-suave.html) / [`3.3.2-scroll-suave.js`](./3.3.2-scroll-suave.js).

## Actividad 3.3.3 - Panel de control ampliado

Código en [`3.3.3-panel-control/`](./3.3.3-panel-control/) (parte del ejemplo base del enunciado, ampliado con):

- **A) Estado de Error:** botón `#btn-error` y clase `.estado-error` — quita `estado-pendiente` o `estado-completado` (la que haya) y aplica `estado-error`, sin importar de cuál venía.
- **B) Texto dinámico del botón de Modo Oscuro:** comprueba con `classList.contains("modo-oscuro")` y cambia el texto del botón entre "Modo Claro" y "Modo Oscuro".

## Actividad 3.3.4 - Ventanas emergentes

Código en [`3.3.4-ventanas-emergentes/`](./3.3.4-ventanas-emergentes/) (`principal.html` + `auxiliar.html`).

`principal.html` abre `auxiliar.html` con `window.open()`, comprueba si el pop-up fue bloqueado (`!ventanaSecundaria || ventanaSecundaria.closed`), y tiene controles para agrandar, bajar, traer al frente y cerrar la ventana secundaria.

**Nota de depuración (probado en Chrome real por Pablo):** `resizeBy`, `moveBy` y `focus()` llamados directamente desde `principal.js` sobre la ventana secundaria no tenían ningún efecto visible, aunque no lanzaban error — Chrome exige "activación de usuario" fresca en la propia ventana que se va a mover/redimensionar, y un clic en la ventana principal no se la transfiere a la secundaria (confirmado: ejecutar `resizeBy(40,40)` a mano en la consola de la ventana secundaria sí funcionaba). Arreglado con `postMessage`: `principal.js` solo avisa a la ventana secundaria, y es ella quien se aplica `resizeBy`/`moveBy`/`focus` a sí misma al recibir el mensaje (ver el `<script>` al final de `auxiliar.html`). `close()` no tiene este problema porque esa restricción es al revés: solo puede cerrarla quien la abrió, así que se queda llamada directamente desde `principal.js`.

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

`principal.html` abre `auxiliar.html` con `window.open()`, comprueba si el pop-up fue bloqueado (`!ventanaSecundaria || ventanaSecundaria.closed`), y tiene controles para `resizeBy(40, 40)`, `moveBy(0, 30)`, `focus()` y `close()` sobre la ventana secundaria.

# Tarea 3.2. Arquitectura y jerarquía del Browser Object Model (BOM)

**Cierre:** martes 24 de noviembre, 00:00.

## Actividad 3.2.1 - Recopilando Información visual

Código en [`3.2.1-auditar-entorno.html`](./3.2.1-auditar-entorno.html) / [`3.2.1-auditar-entorno.js`](./3.2.1-auditar-entorno.js).

## Actividad 3.2.2 - Analizador de URLs

Código en [`3.2.2-analizador-urls.html`](./3.2.2-analizador-urls.html) / [`3.2.2-analizador-urls.js`](./3.2.2-analizador-urls.js).

## Actividad 3.2.3 - Online/Offline

Código en [`3.2.3-monitor-red.html`](./3.2.3-monitor-red.html) / [`3.2.3-monitor-red.js`](./3.2.3-monitor-red.js), con [`3.2.3-soporte.html`](./3.2.3-soporte.html) como página local de soporte.

Requiere un entorno con `Temporal` disponible para el registro de hora de cada corte de red (igual que en la Tarea 3.1.1).

## Actividad 3.2.4 - Autoevaluación

1. ¿Qué relación existe entre `window` y `document`? → **b)** `window` es el objeto padre global y `document` es una propiedad del mismo (`window.document`).
2. ¿Qué diferencia hay entre `location.assign(url)` y `location.replace(url)`? → **b)** `assign()` registra la página anterior en el historial permitiendo retroceder con "Atrás"; `replace()` sobreescribe el registro actual impidiendo el retroceso.
3. ¿Qué propiedad de `screen` indica el ancho útil disponible descontando las barras del sistema operativo? → **c)** `screen.availWidth`.
4. ¿Por qué `history` prohíbe leer las URLs visitadas previamente? → **b)** Para proteger la privacidad del usuario y evitar que sitios maliciosos rastreen su historial de navegación fuera del dominio actual.

## Actividad 3.2.5 - Enrutador SPA de Navegación

Código en [`3.2.5-router-spa/index.html`](./3.2.5-router-spa/index.html) / [`3.2.5-router-spa/rutas.js`](./3.2.5-router-spa/rutas.js).

Navegación entre Inicio/Módulos DAW/Contacto con `history.pushState()`; los dos filtros de curso sobrescriben la misma entrada del historial con `history.replaceState()`, de forma que alternar entre ellos no acumula pasos intermedios en la pila (comprobado: tras filtrar por 1º y luego por 2º, un solo "Atrás" vuelve directo a Inicio).

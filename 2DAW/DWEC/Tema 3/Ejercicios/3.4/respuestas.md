# Tarea 3.4. Generación dinámica de textos y marcado en el documento

**Cierre:** jueves 26 de noviembre, 00:00.

## Actividad 3.4.1 - Generador de lista de módulos

Código en [`3.4.1-generador-lista.html`](./3.4.1-generador-lista.html) / [`3.4.1-generador-lista.js`](./3.4.1-generador-lista.js).

Al pulsar "Generar Lista" se crea una `<ul>` con tres `<li>` ("DWEC", "DWES", "DIW") usando exclusivamente `createElement()`, `createTextNode()` y `appendChild()`.

## Actividad 3.4.2 - Gestor de tareas

Código en [`3.4.2-gestor-tareas.html`](./3.4.2-gestor-tareas.html) / [`3.4.2-gestor-tareas.js`](./3.4.2-gestor-tareas.js).

- "Añadir al final" crea un `<li>` con el texto introducido y lo inserta con `append()`.
- "Añadir al principio" lo inserta con `prepend()`.
- "Reiniciar lista" sustituye todo el contenido por un único `<li>` ("Lista reiniciada") con `replaceChildren()`.
- Si el campo de texto está vacío, los botones de añadir no insertan nada.

## Actividad 3.4.3 - Texto seguro vs HTML

Código en [`3.4.3-texto-vs-html.html`](./3.4.3-texto-vs-html.html) / [`3.4.3-texto-vs-html.js`](./3.4.3-texto-vs-html.js).

"Insertar como Texto Seguro" vuelca el campo con `textContent` (el marcado se muestra literal, sin interpretarse). "Insertar como HTML" lo vuelca con `innerHTML` (el marcado se interpreta: `<strong>Texto en Negrita</strong>` se ve en negrita y `<span style="color:red;">Color Rojo</span>` en rojo).

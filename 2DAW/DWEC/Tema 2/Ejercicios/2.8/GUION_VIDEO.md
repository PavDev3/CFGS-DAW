# Guion para el vídeo (CE 2.h) — máximo 5 min, continuo, con audio

> Tarea de vídeo, 0 si no se graba hablando y mostrando las DevTools en directo.
> Abre `index.html` en Chrome/Edge y ten `depurador_lab.corregido.js` a mano solo como referencia (NO lo subas, el ejercicio se resuelve en directo editando `depurador_lab.js`).

## Preparación (antes de grabar, no sale en el vídeo)
1. Abre la carpeta `2.8/` en VS Code.
2. Abre `index.html` con Live Server (o ábrelo directo en el navegador).
3. Abre las DevTools (F12) y ten listas las pestañas **Sources** y **Elements**.
4. Activa la grabación de pantalla con audio (ej. con el propio grabador de Windows/OBS).

## FASE 1 — Ámbitos y control paso a paso (F10/F11) — 10 pts
1. Clic en "1. Ejecutar y Depurar Algoritmo". El código para en `debugger;`.
2. Abre el panel **Scope** (columna derecha) y señala en voz alta:
   - `unidades` es un **String** (`"5"`, con comillas), no un número.
   - `acumuladoTotal` no está declarada con `let`/`const` dentro de la función, así que se está colando en `window` (ámbito global) — explica que por eso aparece en el `Global` del Scope, no en `Local`.
3. Corrige en directo, en el editor (pausa el debugger, edita, recarga, vuelve a pulsar el botón):
   - `const unidades = Number(document.getElementById("campoUnidades").value);`
   - Añade `"use strict";` como primera línea del archivo.
   - Cambia `acumuladoTotal = 0;` por `let acumuladoTotal = 0;`.
4. Vuelve a lanzar la depuración. Con F10 (Step Over) avanza hasta la línea de `calcularRecargo(precioBase)`. Pulsa F11 (Step Into) para entrar en la función, señala `FACTOR_AJUSTE` en el Scope, y sal con Shift+F11 (Step Out).

## FASE 2 — Watch y breakpoint condicional — 10 pts
1. En el panel **Watch**, añade la expresión `lotes[i]`.
2. Avanza con F10 dentro del bucle `for` de `sumaLotes` hasta que `lotes[i]` muestre `undefined` en la vuelta `i === 5` (por el `<=`). Explica que eso contamina la suma con `NaN`.
3. Corrige en directo: cambia `i <= lotes.length` por `i < lotes.length`.
4. Clic derecho sobre el número de línea de `contadorAuditoria += 1;` → **Add conditional breakpoint...** → escribe `k === 7420`.
5. Pulsa F8 (Resume) y muestra que se detiene justo en `k === 7420` (compruébalo en el Scope o en una Watch `k`).

## FASE 3 — Mutaciones del DOM — 7,5 pts (de las 10 del bloque 3+4)
1. Ve al panel **Elements**, localiza `<div id="zonaMonitorizada">`.
2. Clic derecho sobre esa etiqueta → **Break on** → marca las tres: **Subtree modifications**, **Attribute modifications**, **Node removal**.
3. Vuelve a la página y haz clic en "2. Provocar Mutación DOM".
4. Muestra cómo el navegador se congela exactamente en la línea de `depurador_lab.js` que dispara cada mutación (primero `setAttribute`, luego `appendChild`, luego `remove()` si sigues pulsando Resume).

## FASE 4 — Event Listener Breakpoints — 2,5 pts
1. Quita los breakpoints de mutación del DOM del paso anterior (desmárcalos).
2. En **Sources**, despliega **Event Listener Breakpoints** → abre la categoría **Mouse** → marca **click**.
3. Haz clic en cualquier botón de la página y muestra cómo el depurador para justo en la primera línea del manejador correspondiente, sin que tú supieras de antemano dónde estaba el breakpoint.

## Cierre (últimos segundos)
Resume en una frase qué fallos tenía el script (tipo de dato sin convertir, variable global por fuga de ámbito, bucle con índice fuera de rango) y que ya quedan corregidos en el código final.

---

**Antes de grabar**, usa `depurador_lab.corregido.js` solo para comprobar tú mismo que los 3 fallos y sus arreglos son exactamente esos; la corrección real debe hacerse en vivo sobre `depurador_lab.js` durante la grabación, no copiando y pegando el archivo corregido.

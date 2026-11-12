# Tarea 3.1. Objetos predefinidos del lenguaje

**Cierre:** miércoles 18 de noviembre, 00:00.

## Actividad 3.1.1. Descifra los Rangos de Math.random()

Análisis completo en [`3.1.1-rangos-math-random.md`](./3.1.1-rangos-math-random.md).

## Actividad 3.1.2 (primera parte). Funciones análogas con cryptoRandomFloat

El enunciado duplica el número "3.1.2" para dos ejercicios distintos; esta es la primera: reescribir las 5 funciones de 3.1.1 usando `cryptoRandomFloat()` en vez de `Math.random()`.

Código en [`3.1.2a-funciones-cripto-random.js`](./3.1.2a-funciones-cripto-random.js).

Nota: `cryptoRandomFloat()` del enunciado original hacía `return buffer / 4294967296;` (dividiendo el array `Uint32Array` entero, no su contenido). Funciona por una conversión implícita de JS (un array de un solo elemento se convierte a texto como ese elemento, sin comas, y luego a número), pero es confuso. Aquí se usa `buffer[0]` explícitamente, que hace lo mismo de forma clara.

## Actividad 3.1.2 (segunda parte). Función random genérica

Función `generarAleatorio(min, max, decimales, seguro)` que fusiona todas las anteriores, con control de errores (`throw new Error` si `min > max`) y modo seguro opcional con `cryptoRandomFloat()`.

Código en [`3.1.2b-generarAleatorio.js`](./3.1.2b-generarAleatorio.js).

## Actividad 3.1.3. Validador de Facturación y Conversión Numérica

Formulario que limpia el precio introducido (espacios, coma decimal, € sueltos), valida con `Number.isNaN()`/`Number.isFinite()` que sea una cifra positiva real, y calcula la cuota de IVA y el PVP redondeados a 2 decimales con `toFixed(2)`.

Código en [`3.1.3-validador-facturacion.html`](./3.1.3-validador-facturacion.html) y [`3.1.3-validador-facturacion.js`](./3.1.3-validador-facturacion.js).

## Actividad 3.1.4. Generador de Matrículas y Cripto-Identificadores

**Ojo, esta no está completa.** El Google Doc del enunciado se corta justo después de describir el primer botón ("Generar Matrícula Española (DGT)"): 4 números aleatorios con `padStart(4, "0")` + 3 consonantes mayúsculas aleatorias (sin vocales, Ñ ni Q, según la norma de la DGT). El título de la actividad menciona también "Cripto-Identificadores", pero el documento no llega a describir qué debe generar el segundo botón — puede que el profesor no haya terminado de redactarlo todavía. Revisa el Google Doc más cerca de la fecha de entrega por si lo completa, o pregúntale directamente.

Código de la parte resuelta (matrícula DGT) en [`3.1.4-generador-matriculas.html`](./3.1.4-generador-matriculas.html) y [`3.1.4-generador-matriculas.js`](./3.1.4-generador-matriculas.js).

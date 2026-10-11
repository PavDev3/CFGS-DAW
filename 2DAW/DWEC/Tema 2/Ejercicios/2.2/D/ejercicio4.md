# Ejercicio 4. Análisis de precedencia y operadores unarios combinados

```javascript
let x = 3;
let y = 4;
let resultado = x++ * ++y + (x & 1);
```

Paso a paso:
1. `x++` (post-incremento): se usa el valor actual de `x` (3) en la operación, y **después** `x` pasa a valer 4.
2. `++y` (pre-incremento): `y` pasa a valer 5 **antes** de usarse en la operación, así que se usa el 5.
3. Multiplicación: `3 * 5 = 15`.
4. `(x & 1)`: en este punto `x` ya vale 4. En binario, `4 = 0100` y `1 = 0001`. `0100 & 0001 = 0000 = 0`.
5. Suma final: `15 + 0 = 15`.

**Resultado:** `resultado = 15`, `x = 4`, `y = 5`.

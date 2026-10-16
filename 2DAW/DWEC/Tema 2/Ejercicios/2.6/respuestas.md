# Actividad 1: "El Bug Silencioso"

```javascript
const numeros = [2,4,6,8,9];
for (let i = 0; i < numeros.length; i++) {
  if (numeros[i] % 2 === 0) {
    numeros.splice(i, 1);
  }
}
console.log(numeros);
```

**Traza paso a paso (bucle ascendente):**

| `i` | Array antes | `numeros[i]` | ¿Par? | Acción | Array después |
|---|---|---|---|---|---|
| 0 | `[2,4,6,8,9]` | `2` | Sí | `splice(0,1)` | `[4,6,8,9]` |
| 1 | `[4,6,8,9]` | `6` | Sí | `splice(1,1)` | `[4,8,9]` |
| 2 | `[4,8,9]` | `9` | No | — | `[4,8,9]` |
| 3 | `i < length` (3 < 3) es `false` → fin del bucle | | | | |

**Resultado real: `[4, 8, 9]`** (no `[9]`, que sería lo esperado si se borraran todos los pares).

**Explicación del bug:** `splice()` borra el elemento y **desplaza una posición hacia atrás** todos los que le siguen, pero el contador `i` sigue avanzando normalmente (`i++`). Al borrar el índice `0` (el `2`), el `4` pasa a ocupar el índice `0` y el `6` pasa al índice `1`. Pero como `i` ya avanza a `1` en la siguiente vuelta, el `4` (que ahora está en el índice `0`) nunca se vuelve a comprobar, y el bucle "salta" por encima de él sin darse cuenta. Lo mismo ocurre después con el `8`. Por eso sobreviven pares que deberían haberse borrado: el bug es "silencioso" porque no lanza ningún error, simplemente deja basura sin avisar.

## Con bucle decreciente

```javascript
const numeros = [2,4,6,8,9];
for (let i = numeros.length - 1; i >= 0; i--) {
  if (numeros[i] % 2 === 0) {
    numeros.splice(i, 1);
  }
}
console.log(numeros);
```

**Resultado: `[9]`** — esta vez sí se borran todos los pares correctamente.

**Por qué funciona recorriendo al revés:** al ir de atrás hacia adelante, cuando `splice()` desplaza los elementos posteriores, esas posiciones **ya han sido visitadas** (porque están a la derecha del índice actual, y vamos decreciendo). El desplazamiento nunca afecta a los índices que todavía nos quedan por comprobar, así que no se salta ningún elemento. Por eso la recomendación habitual es: **si vas a borrar elementos de un array mientras lo recorres, recórrelo siempre de atrás hacia adelante.**

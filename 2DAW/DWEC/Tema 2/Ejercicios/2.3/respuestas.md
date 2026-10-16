# Ejercicio 1: El Dilema del Bucle Asíncrono con var frente a let

```
Valor con var: 3
Valor con var: 3
Valor con var: 3
Valor con let: 0
Valor con let: 1
Valor con let: 2
```

**Con `var`**: `var i` tiene ámbito de función (no de bloque), así que las tres llamadas a `setTimeout` comparten la misma variable `i`. Cuando el bucle termina, `i` vale `3`, y como los `setTimeout` se ejecutan después (100 ms más tarde, cuando el bucle ya acabó), las tres funciones leen el mismo valor final: `3`.

**Con `let`**: `let j` tiene ámbito de bloque, y en cada vuelta del bucle se crea una **copia nueva e independiente** de `j`. Cada función que pasamos a `setTimeout` "captura" (closure) su propia copia con el valor que tenía en ese momento, por eso salen `0`, `1` y `2` en orden.

# Ejercicio 2: Identificación de la Zona Muerta Temporal (TDZ)

**Resultado real: no imprime "Carlos", lanza un error.**

```
Uncaught ReferenceError: Cannot access 'usuario' before initialization
```

Parece que debería imprimir `"Carlos"` (el valor de la variable global), pero no es así. Dentro de `configurarPerfil()` hay una declaración `let usuario = "Marta";`. Al tener `let` ámbito de función/bloque con elevación (hoisting), el motor ya "reserva" el nombre `usuario` como variable **local** desde el principio de la función, pero sin inicializarla todavía — esa zona desde el inicio de la función hasta la línea `let usuario = "Marta";` es la **Zona Muerta Temporal (TDZ)**.

Por eso, el `console.log` de la línea anterior ya no se refiere a la variable global `usuario = "Carlos"` (queda **ensombrecida/shadowed** por la local), sino a la local que todavía está en la TDZ → `ReferenceError: Cannot access 'usuario' before initialization`.

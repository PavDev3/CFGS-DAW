# Ejercicio 1: El Enigma de la Igualdad con Booleanos

**Caso A — `if ("0" == false)`:** el operador `==` convierte los dos operandos antes de comparar. `false` se convierte a `0`, y la cadena `"0"` también se convierte a `0` (coerción numérica). Como `0 == 0`, la comparación es `true` y entra en el bloque.

**Caso B — `if ("0")`:** aquí no hay comparación, solo se evalúa si el valor es truthy o falsy. Los únicos valores falsy en JavaScript son `false, 0, -0, 0n, "", null, undefined, NaN`. La cadena `"0"` no está en esa lista porque no está vacía (tiene un carácter dentro), así que se considera truthy y también entra en el bloque.

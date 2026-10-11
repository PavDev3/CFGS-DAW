# Ejercicio 1. Evaluación de cortocircuito y precedencia

```javascript
console.log(0 || "Fallback");       // "Fallback" -> 0 es falsy, así que el OR devuelve el segundo operando
console.log(0 ?? "Fallback");       // 0 -> 0 no es null ni undefined, así que ?? respeta el 0
console.log(5 + "5");               // "55" -> number + string provoca concatenación
let i = 2; console.log(i++ * ++i);  // 8 -> i++ usa el 2 actual (i pasa a 3), luego ++i lo deja en 4 y lo usa: 2 * 4 = 8
```

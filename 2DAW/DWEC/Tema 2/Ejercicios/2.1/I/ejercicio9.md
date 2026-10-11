# Ejercicio 9. Verificación en Consola y Ámbito

```javascript
let totalVentas = 500;
let TotalVentas = 1200;

console.log(totalVentas);
console.log(TotalVentas);
```

Salida: primero `500` y después `1200`.

JavaScript es *case-sensitive*, por lo que `totalVentas` y `TotalVentas` son dos identificadores completamente independientes (dos variables distintas en memoria), aunque solo se diferencien por la mayúscula de la "T". No hay ningún conflicto ni redeclaración entre ellas: cada `console.log` imprime el valor de su propia variable.

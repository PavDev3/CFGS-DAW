# Ejercicio 6. Detección de Errores por Sensibilidad a Mayúsculas

Código original con errores:
```javascript
Function calcularDescuento(PrecioBase) {
  Const porcentaje = 0.15;
  If (PrecioBase > 100) {
    Alert("Descuento aplicado");
    Return PrecioBase * porcentaje;
  }
  return 0;
}

Console.log(calcularDescuento(150));
```

Errores encontrados (palabras reservadas y objetos globales escritos con mayúscula inicial, cuando JavaScript es *case-sensitive*):
1. `Function` → debe ser `function` (palabra reservada en minúscula).
2. `Const` → debe ser `const`.
3. `If` → debe ser `if`.
4. `Alert` → debe ser `alert` (método del objeto global `window`).
5. `Return` → debe ser `return`.
6. `Console.log` → debe ser `console.log` (el objeto global se llama `console`, en minúscula).

Código corregido:
```javascript
function calcularDescuento(PrecioBase) {
  const porcentaje = 0.15;
  if (PrecioBase > 100) {
    alert("Descuento aplicado");
    return PrecioBase * porcentaje;
  }
  return 0;
}

console.log(calcularDescuento(150));
```

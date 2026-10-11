# Ejercicio 5. Comparativa de cortocircuitos con valores falsy críticos

```javascript
function fijarDescuento(descuento) {
  let tasaFinalA = descuento || 15;
  let tasaFinalB = descuento ?? 15;
  console.log(`Tasa A: ${tasaFinalA} | Tasa B: ${tasaFinalB}`);
}
```

Con `fijarDescuento(0)` se imprime: `Tasa A: 15 | Tasa B: 0`.

- `tasaFinalA` usa `||`, y como `0` es un valor **falsy**, el operador ignora el 0% legítimo del cliente VIP y lo sustituye por el valor por defecto (15). Esto es un error para este caso de uso.
- `tasaFinalB` usa `??`, que solo sustituye el valor cuando es `null` o `undefined`. Como `0` no es ninguno de los dos, respeta el descuento real del 0%.

**La asignación correcta para este sistema es `tasaFinalB` (con `??`)**, porque un descuento del 0% es un valor válido y no debe confundirse con "no se ha indicado descuento".

# Ejercicio 1: Creación de Bloque JSDoc Profesional

**Enunciado:** Escribe la documentación formal completa en formato JSDoc para la función `validarDNI` que valida el DNI español, y genera la documentación.

```javascript
/**
 * Valida si la letra de un DNI español se corresponde con el número indicado.
 * @description Calcula el resto de dividir el número del DNI entre 23 y comprueba
 * que coincide con la letra correspondiente de la tabla oficial, sin distinguir mayúsculas/minúsculas.
 *
 * @param {number} numeroDNI - Los 8 dígitos numéricos del DNI.
 * @param {string} letra - La letra del DNI a comprobar.
 *
 * @returns {boolean} true si la letra coincide con el número del DNI, false en caso contrario.
 */
function validarDNI(numeroDNI, letra) {
  const letras = "TRWAGMYFPDXBNJZSQVHLCKE";
  return letras[numeroDNI % 23] === letra.toUpperCase();
}

// Pruebas
console.log(validarDNI(12345678, "Z")); // true
console.log(validarDNI(12345678, "A")); // false
```

## Generar la documentación con JSDoc

Con el código guardado en `validarDNI.js`:

```bash
npx jsdoc validarDNI.js
```

Esto crea una carpeta `out/` con un sitio web navegable (`out/index.html`) que muestra la función documentada: su descripción, el tipo y propósito de cada parámetro (`numeroDNI`, `letra`) y el tipo de dato que devuelve (`boolean`), generado automáticamente a partir del bloque `/** ... */`.

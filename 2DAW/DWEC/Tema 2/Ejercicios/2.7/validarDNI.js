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

console.log(validarDNI(12345678, "Z")); // true
console.log(validarDNI(12345678, "A")); // false

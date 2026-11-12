// Actividad 3.1.2 (segunda parte): función genérica generarAleatorio

/**
 * Genera un número pseudoaleatorio (o criptográficamente seguro) dentro de un rango.
 * @param {number} [min=0] - Valor mínimo del rango (inclusive).
 * @param {number} [max=1] - Valor máximo del rango (inclusive).
 * @param {number} [decimales=0] - Número de cifras decimales del resultado.
 * @param {string} [seguro] - Si vale "segura", usa cryptoRandomFloat() en vez de Math.random().
 * @returns {number} Número aleatorio dentro de [min, max] con los decimales indicados.
 * @throws {Error} Si min es mayor que max.
 */
function generarAleatorio(min = 0, max = 1, decimales = 0, seguro) {
  if (min > max) {
    throw new Error("El valor mínimo no puede ser mayor que el máximo.");
  }

  const base = seguro === "segura" ? cryptoRandomFloat() : Math.random();
  const valor = base * (max - min) + min;

  return Number(valor.toFixed(decimales));
}

function cryptoRandomFloat() {
  const buffer = new Uint32Array(1);
  window.crypto.getRandomValues(buffer);
  return buffer[0] / 4294967296;
}

// Casos de uso de la demostración del enunciado
console.log("Entero [0, 100]:", generarAleatorio(0, 100));
console.log("Coordenada GPS [0.125, 0.875] de 3 cifras:", generarAleatorio(0.125, 0.875, 3));
console.log("Coordenada GPS [0.125, 0.875] de 3 cifras (Segura):", generarAleatorio(0.125, 0.875, 3, "segura"));

// Control de errores
try {
  generarAleatorio(10, 5);
} catch (error) {
  console.log("Error esperado:", error.message);
}

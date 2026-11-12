// Actividad 3.1.3: Validador de Facturación y Conversión Numérica

document.getElementById("formFactura").addEventListener("submit", function (evento) {
  evento.preventDefault();

  const resultado = document.getElementById("resultado");
  const entradaPrecio = document.getElementById("precio").value;
  const entradaIva = document.getElementById("iva").value;

  // 1. Limpiar el precio: quitar espacios, cambiar la coma decimal por punto
  //    y descartar cualquier carácter que no sea dígito o punto (€, letras sueltas...)
  const precioLimpio = entradaPrecio
    .trim()
    .replace(",", ".")
    .replace(/[^0-9.]/g, "");

  const precio = Number(precioLimpio);
  const iva = Number(entradaIva.trim()) || 21;

  // 2. Validar estrictamente que es una cifra positiva real
  if (Number.isNaN(precio) || !Number.isFinite(precio) || precio <= 0) {
    resultado.textContent = "Introduce un precio válido (número positivo).";
    return;
  }

  // 3. Calcular cuota de IVA y PVP
  const cuotaIva = precio * (iva / 100);
  const pvp = precio + cuotaIva;

  // 4. Mostrar el resultado redondeado a 2 decimales
  resultado.textContent =
    `Precio sin IVA: ${precio.toFixed(2)} € · ` +
    `Cuota de IVA (${iva}%): ${cuotaIva.toFixed(2)} € · ` +
    `PVP: ${pvp.toFixed(2)} €`;
});

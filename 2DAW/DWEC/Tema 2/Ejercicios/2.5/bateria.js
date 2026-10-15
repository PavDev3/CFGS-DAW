function calcularMatricula(datos) {
  if (!datos || typeof datos !== "object") {
    return { valido: false, error: "Parámetros de matrícula inválidos." };
  }

  if (typeof datos.precioBase !== "number" || datos.precioBase <= 0) {
    return { valido: false, error: "El precio base debe ser un número positivo." };
  }
  // 
  if (!["general", "desempleado", "socio"].includes(datos.tipoEstudiante)) {
    return { valido: false, error: "Tipo de estudiante no reconocido." };
  }

  let descuentoPorcentaje = 0;
  if (datos.tipoEstudiante === "desempleado") descuentoPorcentaje = 0.30;
  if (datos.tipoEstudiante === "socio") descuentoPorcentaje = 0.15;

  let total = datos.precioBase * (1 - descuentoPorcentaje);

  if (datos.codigoDescuento === "WEB2026") {
    total -= 10;
  }

  if (total < 0) {
    total = 0;
  }

  return { valido: true, total: total };
}
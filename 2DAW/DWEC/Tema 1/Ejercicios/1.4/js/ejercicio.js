const calcularDiferencia = () => {
  const fechaInicio = new Date(document.getElementById("fecha_inicio").value);
  const fechaFin = new Date(document.getElementById("fecha_fin").value);

  const diferenciaEnMilisegundos = fechaFin - fechaInicio;
  const diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);

  document.getElementById("resultadoDiferencia").textContent =
    `Resultado: ${diferenciaEnDias} días`;
};

const calcularDiasMes = () => {
  const anio = parseInt(document.getElementById("anio").value, 10);
  const mes = parseInt(document.getElementById("mes").value, 10);

  // Crear una fecha con el primer día del mes siguiente y restar un día
  const ultimoDia = new Date(anio, mes, 0).getDate();

  document.getElementById("resultadoDiasMes").textContent =
    `Resultado: El mes ${mes}/${anio} tiene ${ultimoDia} días`;
};

const generarFechaRandom = () => {
  const anio = Math.floor(Math.random() * (2025 - 2000 + 1)) + 2000; // Año entre 2000 y 2025
  const mes = Math.floor(Math.random() * 12); // Mes entre 0 y 11
  const dia =
    Math.floor(Math.random() * new Date(anio, mes + 1, 0).getDate()) + 1; // Día válido para el mes
  const hora = Math.floor(Math.random() * 24); // Hora entre 0 y 23
  const minuto = Math.floor(Math.random() * 60);
  const segundos = Math.floor(Math.random() * 60);
  const fechaGenerada = new Date(anio, mes, dia, hora, minuto, segundos);
  document.getElementById("resultadoFechaGenerada").textContent =
    `${fechaGenerada.toString()}`;
  parsearFecha(fechaGenerada);
};

const parsearFecha = (fecha) => {
  const fechaParseada = `${String(fecha.getDate()).padStart(2, "0")}/${String(
    fecha.getMonth() + 1,
  ).padStart(2, "0")}/${fecha.getFullYear()}`;

  document.getElementById("resultadoFechaParseada").textContent =
    `Resultado: ${fechaParseada}`;
};

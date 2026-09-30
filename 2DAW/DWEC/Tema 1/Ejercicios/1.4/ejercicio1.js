// Ejercicio 2: calcularDiasDiferencia(fechaInicio, fechaFin)
const calcularDiasDiferencia = (fechaInicio, fechaFin) => {
  const inicio = new Date(fechaInicio);
  const fin = new Date(fechaFin);
  const msPorDia = 1000 * 60 * 60 * 24;
  return Math.round((fin.getTime() - inicio.getTime()) / msPorDia);
};

const mostrarDiferencia = () => {
  const inicio = document.getElementById("fechaInicio").value;
  const fin = document.getElementById("fechaFin").value;
  const dias = calcularDiasDiferencia(inicio, fin);
  document.getElementById("resultadoDiferencia").innerHTML =
    "Resultado: " + dias + " días";
};

// Ejercicio 3: obtenerUltimoDiaMes(año, mes) - mes en formato humano (1 = enero)
const obtenerUltimoDiaMes = (anio, mes) => {
  // Al pasar "mes" (humano) como índice de mes de JS (base 0), ya apunta
  // al mes siguiente; el día 0 de ese mes es el último día del mes buscado.
  const fecha = new Date(anio, mes, 0);
  return fecha.getDate();
};

const mostrarUltimoDia = () => {
  const anio = Number(document.getElementById("anioMes").value);
  const mes = Number(document.getElementById("mesHumano").value);
  const ultimoDia = obtenerUltimoDiaMes(anio, mes);
  document.getElementById("resultadoUltimoDia").innerHTML =
    "Resultado: el mes " + mes + "/" + anio + " tiene " + ultimoDia + " días";
};

// Ejercicio 4: formatearFechaEspanola(fecha) -> DD/MM/YYYY HH:mm
const formatearFechaEspanola = (fecha) => {
  const dia = String(fecha.getDate()).padStart(2, "0");
  const mes = String(fecha.getMonth() + 1).padStart(2, "0");
  const anio = fecha.getFullYear();
  const horas = String(fecha.getHours()).padStart(2, "0");
  const minutos = String(fecha.getMinutes()).padStart(2, "0");
  return `${dia}/${mes}/${anio} ${horas}:${minutos}`;
};

const mostrarFechaFormateada = () => {
  document.getElementById("resultadoFormateado").innerHTML =
    "Resultado: " + formatearFechaEspanola(new Date());
};

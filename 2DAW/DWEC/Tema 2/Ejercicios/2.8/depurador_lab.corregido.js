"use strict";

function calcularRecargo(base) {
  const FACTOR_AJUSTE = 1.05;
  return base * FACTOR_AJUSTE;
}

function procesarLote() {
  const unidades = Number(document.getElementById("campoUnidades").value);
  const precioBase = Number(document.getElementById("campoPrecio").value);
  const visor = document.getElementById("pantallaSalida");

  let acumuladoTotal = 0;

  debugger;

  const precioConRecargo = calcularRecargo(precioBase);

  const lotes = [10, 20, 30, 40, 50];
  let sumaLotes = 0;

  for (let i = 0; i < lotes.length; i++) {
    sumaLotes += lotes[i];
  }

  let contadorAuditoria = 0;
  for (let k = 0; k < 10000; k++) {
    contadorAuditoria += 1;
  }

  acumuladoTotal = (precioConRecargo * unidades) + sumaLotes;

  visor.innerHTML = `
    Subtotal lotes: ${sumaLotes}<br>
    Precio con recargo (unitario): ${precioConRecargo.toFixed(2)} €<br>
    Total final computado: ${acumuladoTotal} €<br>
    Auditoría masiva completada: ${contadorAuditoria} ciclos<br>
    ¿Se contaminó window?: ${typeof window.acumuladoTotal !== "undefined" ? "SÍ (Fuga)" : "NO"}
  `;
}

function alterarEstructuraDOM() {
  const contenedor = document.getElementById("zonaMonitorizada");
  const nodo = document.getElementById("nodoObjetivo");

  contenedor.setAttribute("data-estado", "actualizado");

  const nuevoElemento = document.createElement("p");
  nuevoElemento.textContent = `Elemento inyectado a las ${new Date().toLocaleTimeString()}`;
  nuevoElemento.style.color = "#38bdf8";
  contenedor.appendChild(nuevoElemento);

  if (nodo) {
    nodo.remove();
  }
}

document.getElementById("btnEjecutar").addEventListener("click", procesarLote);
document.getElementById("btnModificarDOM").addEventListener("click", alterarEstructuraDOM);

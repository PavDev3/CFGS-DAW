const tarjeta = document.querySelector("#tarjeta");
const btnOscuro = document.querySelector("#btn-oscuro");
const btnOcultar = document.querySelector("#btn-ocultar");
const btnExito = document.querySelector("#btn-exito");
const btnError = document.querySelector("#btn-error");
const btnBorde = document.querySelector("#btn-borde");
const btnReset = document.querySelector("#btn-reset");

btnOscuro.addEventListener("click", () => {
  document.body.classList.toggle("modo-oscuro");
  btnOscuro.textContent = document.body.classList.contains("modo-oscuro") ? "Modo Claro" : "Modo Oscuro";
});

btnOcultar.addEventListener("click", () => {
  tarjeta.classList.toggle("oculto");
});

btnExito.addEventListener("click", () => {
  tarjeta.classList.replace("estado-pendiente", "estado-completado");
  tarjeta.textContent = "Estado actual: ¡Tarea completada!";
});

// Sin importar si venía de pendiente o completado, pasa a error
btnError.addEventListener("click", () => {
  tarjeta.classList.remove("estado-pendiente", "estado-completado");
  tarjeta.classList.add("estado-error");
  tarjeta.textContent = "Estado actual: Error detectado.";
});

btnBorde.addEventListener("click", () => {
  if (tarjeta.classList.contains("resaltado")) {
    tarjeta.classList.remove("resaltado");
  } else {
    tarjeta.classList.add("resaltado");
  }
});

btnReset.addEventListener("click", () => {
  tarjeta.className = "caja estado-pendiente";
  tarjeta.textContent = "Estado actual: Tarea pendiente de revisión.";
});

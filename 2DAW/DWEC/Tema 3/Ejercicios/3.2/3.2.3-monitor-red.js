const indicador = document.getElementById("indicador");
const cuerpoHistorial = document.getElementById("cuerpoHistorial");
const btnEmergencia = document.getElementById("btnEmergencia");

function registrarEvento(texto) {
  const fila = document.createElement("tr");
  const ahora = Temporal.Now.plainDateTimeISO();
  fila.innerHTML = `<td>${texto}</td><td>${ahora.toString({ smallestUnit: "second" })}</td>`;
  cuerpoHistorial.prepend(fila);
}

window.addEventListener("online", () => {
  indicador.textContent = "Conexión Estable";
  indicador.className = "conectado";
  registrarEvento("Conexión recuperada");
});

window.addEventListener("offline", () => {
  indicador.textContent = "Sin Conexión";
  indicador.className = "desconectado";
  registrarEvento("Corte de red");
});

btnEmergencia.addEventListener("click", () => {
  location.replace("3.2.3-soporte.html");
});

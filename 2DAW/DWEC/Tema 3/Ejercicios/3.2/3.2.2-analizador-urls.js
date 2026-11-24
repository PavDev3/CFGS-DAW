document.getElementById("valProtocolo").textContent = window.location.protocol;
document.getElementById("valHostname").textContent = window.location.hostname;
document.getElementById("valPuerto").textContent = window.location.port || "(puerto por defecto)";
document.getElementById("valPathname").textContent = window.location.pathname;
document.getElementById("valSearch").textContent = window.location.search || "(sin parámetros)";

document.getElementById("btnRecargar").addEventListener("click", () => {
  location.reload();
});

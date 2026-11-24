const caja = document.getElementById("caja");

document.getElementById("btnExpandir").addEventListener("click", () => {
  caja.classList.toggle("caja-expandida");
});

document.getElementById("btnReset").addEventListener("click", () => {
  caja.classList.remove("caja-expandida");
});

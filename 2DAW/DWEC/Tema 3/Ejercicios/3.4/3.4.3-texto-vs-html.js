const entrada = document.getElementById("entrada");
const resultado = document.getElementById("resultado");

document.getElementById("btnTexto").addEventListener("click", () => {
  resultado.textContent = entrada.value;
});

document.getElementById("btnHtml").addEventListener("click", () => {
  resultado.innerHTML = entrada.value;
});

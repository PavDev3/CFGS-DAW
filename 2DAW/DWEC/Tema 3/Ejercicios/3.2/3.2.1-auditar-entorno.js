const btnAuditar = document.getElementById("btnAuditar");
const resultado = document.getElementById("resultado");
const listaResultado = document.getElementById("listaResultado");

btnAuditar.addEventListener("click", () => {
  const datos = {
    "Idioma del navegador": navigator.language,
    "¿Conectado a internet?": navigator.onLine ? "Sí" : "No",
    "Dimensiones del viewport": `${window.innerWidth} x ${window.innerHeight} px`,
    "Resolución del monitor": `${screen.width} x ${screen.height} px`,
  };

  listaResultado.innerHTML = "";
  for (const [etiqueta, valor] of Object.entries(datos)) {
    const item = document.createElement("li");
    item.innerHTML = `<strong>${etiqueta}:</strong> ${valor}`;
    listaResultado.appendChild(item);
  }

  resultado.hidden = false;
});

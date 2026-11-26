const contenedorLista = document.getElementById("contenedorLista");
const modulos = ["DWEC", "DWES", "DIW"];

document.getElementById("btnGenerar").addEventListener("click", () => {
  const lista = document.createElement("ul");

  modulos.forEach((modulo) => {
    const item = document.createElement("li");
    item.appendChild(document.createTextNode(modulo));
    lista.appendChild(item);
  });

  contenedorLista.appendChild(lista);
});

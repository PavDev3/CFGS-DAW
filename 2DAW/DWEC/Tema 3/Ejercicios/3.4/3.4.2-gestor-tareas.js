const listaTareas = document.getElementById("listaTareas");
const nuevaTarea = document.getElementById("nuevaTarea");

document.getElementById("btnFinal").addEventListener("click", () => {
  if (nuevaTarea.value.trim() === "") return;

  const item = document.createElement("li");
  item.textContent = nuevaTarea.value;
  listaTareas.append(item);
  nuevaTarea.value = "";
});

document.getElementById("btnPrincipio").addEventListener("click", () => {
  if (nuevaTarea.value.trim() === "") return;

  const item = document.createElement("li");
  item.textContent = nuevaTarea.value;
  listaTareas.prepend(item);
  nuevaTarea.value = "";
});

document.getElementById("btnReiniciar").addEventListener("click", () => {
  const item = document.createElement("li");
  item.textContent = "Lista reiniciada";
  listaTareas.replaceChildren(item);
});

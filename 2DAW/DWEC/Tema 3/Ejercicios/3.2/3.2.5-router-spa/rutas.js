const contenedorVista = document.getElementById("contenedor-vista");

const vistas = {
  inicio: "<h2>Inicio</h2><p>Bienvenido/a al portal del ciclo DAW.</p>",
  contacto: "<h2>Contacto</h2><p>Departamento de Informática, I.E.S. Kursaal.</p>",
  modulos: {
    primero: ["Programación", "Bases de Datos", "Lenguajes de Marcas", "Sistemas Informáticos"],
    segundo: ["DWEC", "DWES", "DIW", "Despliegue"],
  },
};

function vistaModulos(filtro) {
  if (filtro === "primero") {
    return `<h2>Módulos DAW (Filtro de 1º Curso activo)</h2><ul>${vistas.modulos.primero.map((m) => `<li>${m}</li>`).join("")}</ul>`;
  }
  if (filtro === "segundo") {
    return `<h2>Módulos DAW (Filtro de 2º Curso activo)</h2><ul>${vistas.modulos.segundo.map((m) => `<li>${m}</li>`).join("")}</ul>`;
  }
  const todos = [...vistas.modulos.primero, ...vistas.modulos.segundo];
  return `<h2>Módulos DAW</h2><ul>${todos.map((m) => `<li>${m}</li>`).join("")}</ul>`;
}

function actualizarVista(estado) {
  if (!estado || !estado.seccion) {
    contenedorVista.innerHTML = vistas.inicio;
    return;
  }

  if (estado.seccion === "modulos") {
    contenedorVista.innerHTML = vistaModulos(estado.filtro);
    return;
  }

  contenedorVista.innerHTML = vistas[estado.seccion] ?? vistas.inicio;
}

document.getElementById("btn-inicio").addEventListener("click", () => {
  const estado = { seccion: "inicio" };
  history.pushState(estado, "", "/inicio");
  actualizarVista(estado);
});

document.getElementById("btn-modulos").addEventListener("click", () => {
  const estado = { seccion: "modulos" };
  history.pushState(estado, "", "/modulos");
  actualizarVista(estado);
});

document.getElementById("btn-contacto").addEventListener("click", () => {
  const estado = { seccion: "contacto" };
  history.pushState(estado, "", "/contacto");
  actualizarVista(estado);
});

document.getElementById("btn-filtro-1").addEventListener("click", () => {
  const estado = { seccion: "modulos", filtro: "primero" };
  history.replaceState(estado, "", "/modulos?curso=1");
  actualizarVista(estado);
});

document.getElementById("btn-filtro-2").addEventListener("click", () => {
  const estado = { seccion: "modulos", filtro: "segundo" };
  history.replaceState(estado, "", "/modulos?curso=2");
  actualizarVista(estado);
});

window.onpopstate = (event) => {
  actualizarVista(event.state);
};

actualizarVista(history.state);

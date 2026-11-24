let ventanaSecundaria = null;

document.getElementById("btnAbrir").addEventListener("click", () => {
  ventanaSecundaria = window.open(
    "auxiliar.html",
    "ventanaAuxiliar",
    "width=400,height=300,popup=yes"
  );

  if (!ventanaSecundaria || ventanaSecundaria.closed) {
    alert("El navegador ha bloqueado la ventana emergente. Permite los pop-ups para este sitio.");
  }
});

document.getElementById("btnAgrandar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.resizeBy(40, 40);
  }
});

document.getElementById("btnBajar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.moveBy(0, 30);
  }
});

document.getElementById("btnFrente").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.focus();
  }
});

document.getElementById("btnCerrar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.close();
  }
});

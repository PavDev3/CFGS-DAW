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

// Chrome exige activacion de usuario "fresca" en la propia ventana para
// resizeBy/moveBy/focus: un clic en principal.html no cuenta para la
// ventana secundaria, asi que le pedimos por postMessage que lo haga ella
// misma sobre si misma (ver auxiliar.html).
document.getElementById("btnAgrandar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.focus();
    ventanaSecundaria.resizeBy(40, 40);
  }
});

document.getElementById("btnBajar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.postMessage("bajar", "*");
  }
});

document.getElementById("btnFrente").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.postMessage("ponte-al-frente", "*");
  }
});

document.getElementById("btnCerrar").addEventListener("click", () => {
  if (ventanaSecundaria && !ventanaSecundaria.closed) {
    ventanaSecundaria.close();
  }
});

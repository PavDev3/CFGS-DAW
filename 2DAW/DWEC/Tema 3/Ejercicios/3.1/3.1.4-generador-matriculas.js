// Actividad 3.1.4: Generador de Matrículas Española (DGT)
// Consonantes permitidas por la norma de la DGT: sin vocales (A, E, I, O, U), sin Ñ ni Q.
const CONSONANTES_DGT = "BCDFGHJKLMNPRSTVWXYZ";

function generarNumeroMatricula() {
  const numero = Math.floor(Math.random() * 10000); // entero en [0, 9999]
  return String(numero).padStart(4, "0");
}

function generarLetrasMatricula() {
  let letras = "";
  for (let i = 0; i < 3; i++) {
    const indice = Math.floor(Math.random() * CONSONANTES_DGT.length);
    letras += CONSONANTES_DGT.charAt(indice);
  }
  return letras;
}

function generarMatricula() {
  return `${generarNumeroMatricula()} ${generarLetrasMatricula()}`;
}

document.getElementById("btnMatricula").addEventListener("click", () => {
  document.getElementById("resultadoMatricula").textContent = generarMatricula();
});

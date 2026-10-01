const evaluarPregunta = (id, esCorrecta) => {
  document.getElementById(id).style.color = esCorrecta ? "green" : "red";
};

const fotogramas = [
  "assets/frame1.svg",
  "assets/frame2.svg",
  "assets/frame3.svg",
  "assets/frame4.svg",
];
let fotogramaActual = 0;

const atrasFotograma = () => {
  fotogramaActual =
    (fotogramaActual - 1 + fotogramas.length) % fotogramas.length;
  document.getElementById("fotograma").src = fotogramas[fotogramaActual];
};
const siguienteFotograma = () => {
  fotogramaActual = (fotogramaActual + 1) % fotogramas.length;
  document.getElementById("fotograma").src = fotogramas[fotogramaActual];
};

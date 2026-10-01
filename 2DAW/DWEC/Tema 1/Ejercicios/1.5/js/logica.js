const cambiarColor = () => {
  const button = document.getElementById("btnCambiar");
  button.style.backgroundColor =
    button.style.backgroundColor === "tomato" ? "steelblue" : "tomato";
};

const button = document.getElementById("btnCambiar");
button.addEventListener("click", cambiarColor);

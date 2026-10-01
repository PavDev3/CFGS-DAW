// Script externo: se descarga una sola vez y el navegador lo guarda en caché.
// Se enlaza desde el <head> con el atributo "defer" para que se ejecute
// después de que el HTML esté completamente leído (así no falla al
// buscar el botón en el DOM, que es justo el problema que explica el documento
// cuando un script va en el <head> sin defer/async).

function cambiarColor() {
    const boton = document.getElementById('btnCambiar');
    boton.style.backgroundColor = boton.style.backgroundColor === 'tomato' ? 'steelblue' : 'tomato';
}

const boton = document.getElementById('btnCambiar');
boton.addEventListener('click', cambiarColor);

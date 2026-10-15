document.getElementById("btnAnalizar").addEventListener("click", analizarCoercion);

function analizarCoercion() {
    const textoEntrada = document.getElementById("txtEntrada").value;
    const pantallaSalida = document.getElementById("pantallaSalida");

    // Normalización de la entrada literal a tipo primitivo o de referencia real
    let valorReal;
    try {
        valorReal = JSON.parse(textoEntrada);
    } catch (error) {
        if (textoEntrada === "undefined") {
            valorReal = undefined;
        } else if (textoEntrada === "NaN") {
            valorReal = NaN;
        } else {
            valorReal = textoEntrada;
        }
    }

    const comoNumero = Number(valorReal);
    const comoTexto = String(valorReal);
    const comoBooleano = Boolean(valorReal);
    const suma = valorReal + 10;
    const resta = valorReal - 10;

    let html = "";
    html += "<strong>Valor original:</strong> <span class=\"valor-resaltado\">" + textoEntrada + "</span> (typeof: " + typeof valorReal + ")<br>";
    html += "<strong>Como Número [Number(x)]:</strong> <span class=\"valor-resaltado\">" + comoNumero + "</span> (typeof: " + typeof comoNumero + ")<br>";
    html += "<strong>Como Texto [String(x)]:</strong> <span class=\"valor-resaltado\">" + comoTexto + "</span> (typeof: " + typeof comoTexto + ")<br>";
    html += "<strong>Como Booleano [Boolean(x)]:</strong> <span class=\"valor-resaltado\">" + comoBooleano + "</span> (typeof: " + typeof comoBooleano + ")<br>";
    html += "<strong>x + 10:</strong> <span class=\"valor-resaltado\">" + suma + "</span> (typeof: " + typeof suma + ")<br>";
    html += "<strong>x - 10:</strong> <span class=\"valor-resaltado\">" + resta + "</span> (typeof: " + typeof resta + ")<br>";

    if (textoEntrada === "0" && comoBooleano === true) {
        html += "<span class=\"alerta\">Aviso: \"0\" es un texto no vacío, por eso es TRUTHY.</span><br>";
    }

    if (textoEntrada === "[]" && comoNumero === 0) {
        html += "<span class=\"alerta\">Aviso: una matriz vacía se coerciona al número 0.</span><br>";
    }

    if (Number.isNaN(comoNumero)) {
        html += "<span class=\"alerta\">Conversión numérica fallida: No es un número válido</span><br>";
    }

    pantallaSalida.innerHTML = html;
}

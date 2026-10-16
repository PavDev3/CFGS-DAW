"use strict";

const visor = document.getElementById("pantallaTerminal");

function registrar(texto, clase = "") {
    const linea = document.createElement("div");
    if (clase) linea.className = clase;
    linea.textContent = texto;
    visor.appendChild(linea);
}

// Modificación 1: Sombreado (Shadowing)
const TASA = 0.21;

function calcularTarifaTemporal() {
    const TASA = 0.10;
    registrar("TASA dentro de la función (sombreada): " + TASA, "resaltado-ok");
}

document.getElementById("btnEjecutar").addEventListener("click", () => {
    visor.innerHTML = "";
    registrar("=== INICIANDO AUDITORIA DE AMBITOS ===");

    // 1. Ámbito de Bloque Aislado
    {
        var testigoVar = "Creada con var en bloque";
        let testigoLet = "Creada con let en bloque";
    }

    // Comprobación de visibilidad de testigoVar
    if (typeof testigoVar !== "undefined") {
        registrar("✓ 'testigoVar' se fugó del bloque: " + testigoVar, "resaltado-ok");
    } else {
        registrar("✗ 'testigoVar' no es accesible.", "resaltado-error");
    }

    // Comprobación de visibilidad de testigoLet
    try {
        let lectura = testigoLet;
        registrar("✓ 'testigoLet' es accesible: " + lectura, "resaltado-ok");
    } catch (e) {
        registrar("✗ 'testigoLet' fue destruida al salir del bloque (ReferenceError).", "resaltado-error");
    }

    // 2. Comprobación de Contaminación del Objeto Window
    var variableGlobalVar = "Dato con var";
    let variableGlobalLet = "Dato con let";

    if (window.variableGlobalVar === "Dato con var") {
        registrar("⚠ 'variableGlobalVar' contaminó el objeto global window.", "resaltado-error");
    }

    if (typeof window.variableGlobalLet === "undefined") {
        registrar("✓ 'variableGlobalLet' NO contamina window (Entorno limpio).", "resaltado-ok");
    }

    // 3. Modificación 1: Sombreado de TASA
    registrar("TASA global: " + TASA, "resaltado-ok");
    calcularTarifaTemporal();

    // 4. Modificación 2: "use strict" con variable no declarada
    try {
        saldoSinDeclarar = 500;
        registrar("saldoSinDeclarar asignada: " + saldoSinDeclarar, "resaltado-ok");
    } catch (e) {
        registrar("✗ Error al asignar sin declarar (use strict): " + e.message, "resaltado-error");
    }

    registrar("=== FIN DE LA AUDITORIA ===");
});

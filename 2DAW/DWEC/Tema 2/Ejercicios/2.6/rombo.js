// Actividad 2: "El Algoritmo del Rombo"

function dibujarRombo(filas) {
    // Parte superior (incluye la fila central más ancha)
    for (let i = 0; i < filas; i++) {
        let espacios = "";
        for (let e = 0; e < filas - i - 1; e++) {
            espacios += " ";
        }

        let asteriscos = "";
        for (let a = 0; a < 2 * i + 1; a++) {
            asteriscos += "*";
        }

        console.log(espacios + asteriscos);
    }

    // Parte inferior (simétrica a la superior, sin repetir la fila central)
    for (let i = filas - 2; i >= 0; i--) {
        let espacios = "";
        for (let e = 0; e < filas - i - 1; e++) {
            espacios += " ";
        }

        let asteriscos = "";
        for (let a = 0; a < 2 * i + 1; a++) {
            asteriscos += "*";
        }

        console.log(espacios + asteriscos);
    }
}

dibujarRombo(5);

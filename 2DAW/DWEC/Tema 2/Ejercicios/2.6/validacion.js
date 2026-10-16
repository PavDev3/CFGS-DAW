// Actividad 3: "Validación de Entrada con do...while"

let codigo;

do {
    codigo = prompt("Introduce un código numérico de 4 dígitos:");
} while (codigo === null || !/^[0-9]{4}$/.test(codigo));

console.log("Código válido introducido:", codigo);

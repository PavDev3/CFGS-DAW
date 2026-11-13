<?php
// Práctica guiada UT3 - Ejercicio 3: control de excepciones (try/catch/finally)
// Conductor: Pablo Núñez | Navegante: Pablo Núñez
// (Trabajo individual: no se asignó pareja, así que he hecho ambos roles yo mismo)

function validarEdadRegistro($edad) {
    if (empty($edad) || !is_numeric($edad)) {
        throw new Exception("Error: el dato introducido no es un valor numérico válido.");
    }

    if ($edad < 18) {
        throw new Exception("Error: debes ser mayor de edad para completar el registro.");
    }

    return true;
}

$casosPrueba = [25, 15, "abc", "", 40];

foreach ($casosPrueba as $edad) {
    try {
        validarEdadRegistro($edad);
        echo "Edad $edad válida: registro permitido.<br>";
    } catch (Exception $e) {
        echo $e->getMessage() . "<br>";
    } finally {
        echo "Comprobación finalizada.<br><br>";
    }
}

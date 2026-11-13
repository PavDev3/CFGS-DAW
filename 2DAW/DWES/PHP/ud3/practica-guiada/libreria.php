<?php
// Práctica guiada UT3 - Ejercicio 2: librería de funciones
// Conductor: Pablo Núñez | Navegante: Pablo Núñez
// (Trabajo individual: no se asignó pareja, así que he hecho ambos roles yo mismo)

// Aplica un descuento (en %) a todos los precios de un array, modificando
// el array original (paso por referencia).
function aplicarDescuento(&$precios, $porcentaje = 10) {
    foreach ($precios as &$precio) {
        $precio = $precio - ($precio * $porcentaje / 100);
    }
}

// Devuelve el importe con el IVA general (21%) aplicado
function calcularIvaGeneral($importe) {
    return $importe * 1.21;
}

// Devuelve el importe con el IVA reducido (10%) aplicado
function calcularIvaReducido($importe) {
    return $importe * 1.10;
}

// Recibe el nombre de una función (callback) y la aplica sobre un importe
function procesarFactura($funcionIva, $importe) {
    return call_user_func($funcionIva, $importe);
}

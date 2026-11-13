<?php
// Práctica guiada UT3 - Ejercicio 2: uso de la librería de funciones
// Conductor: Pablo Núñez | Navegante: Pablo Núñez
// (Trabajo individual: no se asignó pareja, así que he hecho ambos roles yo mismo)

require_once 'libreria.php';

$precios = [100, 250, 50, 80, 120];

// Aplicamos el descuento por defecto (10%)
aplicarDescuento($precios);
echo "Precios tras aplicar el descuento del 10% por defecto:<br>";
echo "<pre>";
print_r($precios);
echo "</pre>";

// Probamos procesarFactura() con los dos callbacks
$importe = 200;
$totalGeneral = procesarFactura("calcularIvaGeneral", $importe);
$totalReducido = procesarFactura("calcularIvaReducido", $importe);

echo "Importe de $importe € con IVA general (21%): $totalGeneral €<br>";
echo "Importe de $importe € con IVA reducido (10%): $totalReducido €<br>";

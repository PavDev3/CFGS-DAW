<?php
// Práctica guiada UT3 - Ejercicio 1: condicionales, bucles y operadores de arrays
// Conductor: Pablo Núñez | Navegante: Pablo Núñez
// (Trabajo individual: no se asignó pareja, así que he hecho ambos roles yo mismo)

// 1. Dos arrays asociativos de inventario (código producto => unidades)
// Comparten el código "P01" con cantidades distintas, y el resto son códigos diferentes.
$almacen1 = ["P01" => 10, "P02" => 0, "P03" => 20];
$almacen2 = ["P01" => 5, "P04" => 3, "P05" => 18];

// 2. Comparación con == (igual) y === (idéntico)
if ($almacen1 == $almacen2) {
    echo "almacen1 y almacen2 son iguales (==).<br>";
} else {
    echo "almacen1 y almacen2 NO son iguales (==).<br>";
}

if ($almacen1 === $almacen2) {
    echo "almacen1 y almacen2 son idénticos (===).<br>";
} else {
    echo "almacen1 y almacen2 NO son idénticos (===).<br>";
}

// 3. Unión de ambos arrays con el operador +
// (si una clave existe en los dos, se queda con el valor del primer array)
$stockTotal = $almacen1 + $almacen2;
echo "<pre>";
print_r($stockTotal);
echo "</pre>";

// 4. Recorrido de $stockTotal con foreach
foreach ($stockTotal as $codigo => $unidades) {
    if ($unidades == 0) {
        // Producto sin stock: lo saltamos
        continue;
    }

    if ($unidades < 5) {
        echo "$codigo ($unidades unidades): Stock crítico (reponer urgente)<br>";
    } elseif ($unidades <= 15) {
        echo "$codigo ($unidades unidades): Stock normal<br>";
    } else {
        echo "$codigo ($unidades unidades): Exceso de stock<br>";
    }
}

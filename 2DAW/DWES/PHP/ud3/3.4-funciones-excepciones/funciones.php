<?php
// Ejercicio 7: todas las funciones de los ejercicios anteriores en un solo archivo.
// PREGUNTA PARA LA PROFESORA: no hemos dado funciones en clase todavía, solo
// estructuras de control y arrays. He mirado un poco por mi cuenta (w3schools
// y php.net que están en los recursos de Moodle) para entender la sintaxis
// "function nombre($param1, $param2) { ... return algo; }".

// Ejercicio 1a: suma 5 números y los muestra por pantalla (no devuelve nada)
function sumarCinco($a, $b, $c, $d, $e) {
    $suma = $a + $b + $c + $d + $e;
    echo "La suma de los 5 números es: " . $suma;
}

// Ejercicio 1b: recibe 5 números y divide dos de ellos, devolviendo el resultado
// PREGUNTA PARA LA PROFESORA: el enunciado dice que hay que usar excepciones
// "por si se diera el caso de que divides por 0", pero no hemos visto nunca
// try/catch/throw en clase. He mirado cómo funciona en php.net y lo he hecho
// así, pero no estoy seguro de si es la forma "oficial" que se espera.
function dividirDosDeCinco($n1, $n2, $n3, $n4, $n5) {
    // Para este ejercicio, divido el primer número entre el segundo
    if ($n2 == 0) {
        throw new Exception("No se puede dividir entre 0");
    }
    return $n1 / $n2;
}

// Ejercicio 1c: volumen de un cilindro
function volumenCilindro($radio, $altura) {
    $numeroPi = 3.1416;
    $volumen = $numeroPi * $radio * $radio * $altura;
    return $volumen;
}

// Ejercicio 2: crea un array de "numero_de_elementos" enteros al azar entre min y max
function inicializar_array($numero_de_elementos, $min, $max) {
    $array = array();
    for ($i = 0; $i < $numero_de_elementos; $i++) {
        $array[$i] = rand($min, $max);
    }
    return $array;
}

// Ejercicio 3: calcula la media de un array
function calcular_media($array) {
    $suma = 0;
    foreach ($array as $valor) {
        $suma = $suma + $valor;
    }
    $media = $suma / count($array);
    return $media;
}

// Ejercicio 4: devuelve el valor máximo del array
function calcular_maximo($array) {
    // PHP tiene una función max() ya hecha, pero lo hago "a mano" porque
    // creo que es lo que se busca en el ejercicio (practicar recorrer arrays)
    $maximo = $array[0];
    foreach ($array as $valor) {
        if ($valor > $maximo) {
            $maximo = $valor;
        }
    }
    return $maximo;
}

// Ejercicio 5: devuelve el valor mínimo del array
function calcular_minimo($array) {
    $minimo = $array[0];
    foreach ($array as $valor) {
        if ($valor < $minimo) {
            $minimo = $valor;
        }
    }
    return $minimo;
}

// Ejercicio 6: muestra el array en una tabla de 2 columnas (posición / valor)
function imprimir_array($array) {
    echo "<table border='1'>";
    echo "<tr><th>Posición</th><th>Valor</th></tr>";
    foreach ($array as $posicion => $valor) {
        echo "<tr><td>" . $posicion . "</td><td>" . $valor . "</td></tr>";
    }
    echo "</table>";
}

// --- Ejercicio 9: funciones con validación y excepciones ---
// PREGUNTA PARA LA PROFESORA: is_numeric() lo he buscado en php.net, no lo
// hemos visto en clase. Tampoco hemos visto "throw new Exception" ni los
// bloques try/catch, los uso aquí porque lo pide el enunciado pero
// agradecería que se explicara en clase cómo funciona por dentro.

function sumar($num1, $num2) {
    if (is_numeric($num1) && is_numeric($num2)) {
        return $num1 + $num2;
    } else {
        throw new Exception("Los valores a sumar tienen que ser numéricos");
    }
}

function dividir($dividendo, $divisor) {
    if (!is_numeric($dividendo) || !is_numeric($divisor)) {
        throw new Exception("Los valores tienen que ser numéricos");
    }
    if ($divisor == 0) {
        throw new Exception("El divisor no puede ser 0");
    }
    return $dividendo / $divisor;
}

function calcularAreaTriangulo($base, $altura) {
    if (!is_numeric($base) || !is_numeric($altura)) {
        throw new Exception("La base y la altura tienen que ser numéricas");
    }
    if ($base <= 0 || $altura <= 0) {
        throw new Exception("La base y la altura tienen que ser positivas");
    }
    return ($base * $altura) / 2;
}

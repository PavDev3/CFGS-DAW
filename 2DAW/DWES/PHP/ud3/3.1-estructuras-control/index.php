<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ejercicios UT3_1 - Estructuras de control</title>
</head>
<body>

    <h1>Ejercicios UT3_1 - Estructuras de control</h1>
    <p>Pablo Núñez - 2º DAW - Desarrollo web en entorno servidor</p>

    <hr>
    <h2>Ejercicio 1 - Lanzar una moneda</h2>
    <?php
        $resultado = rand(0, 1);
    ?>
    <p><?= $resultado == 1 ? 'Cara' : 'Cruz' ?></p>

    <hr>
    <h2>Ejercicio 2 - Adivina tu nota</h2>
    <?php
        $nota = rand(1, 10);
        if ($nota < 5) {
            $calificacion = "Insuficiente";
        } elseif ($nota < 6) {
            $calificacion = "Suficiente";
        } elseif ($nota < 7) {
            $calificacion = "Bien";
        } elseif ($nota < 9) {
            $calificacion = "Notable";
        } else {
            $calificacion = "Sobresaliente";
        }
    ?>
    <p>Número aleatorio generado: <?= $nota ?></p>
    <p>Tu nota del curso será: <?= $calificacion ?></p>

    <hr>
    <h2>Ejercicio 3 - Comprobar números iguales</h2>
    <?php
        $n1 = rand(0, 10);
        $n2 = rand(0, 10);
        $n3 = rand(0, 10);

        if ($n1 == $n2 && $n2 == $n3) {
            $resultado = "hay tres números iguales a $n1";
        } elseif ($n1 == $n2) {
            $resultado = "hay dos números iguales a $n1";
        } elseif ($n1 == $n3) {
            $resultado = "hay dos números iguales a $n1";
        } elseif ($n2 == $n3) {
            $resultado = "hay dos números iguales a $n2";
        } else {
            $resultado = "no hay números iguales";
        }
    ?>
    <p>Números generados: <?= $n1 ?>, <?= $n2 ?>, <?= $n3 ?></p>
    <p>Resultado: <?= $resultado ?></p>

    <hr>
    <h2>Ejercicio 4 - Suma de pares anteriores a un número</h2>
    <?php if (!isset($_GET['numero'])): ?>
        <form method="get">
            Introduce un número: <input type="number" name="numero" required>
            <button type="submit">Calcular</button>
        </form>
    <?php else:
        $numero = (int) $_GET['numero'];
        $suma = 0;
        for ($i = 2; $i < $numero; $i += 2) {
            $suma += $i;
        }
    ?>
        <p>Número introducido: <?= $numero ?></p>
        <p>Suma de los números pares anteriores a él: <?= $suma ?></p>
        <a href="?">Volver al formulario</a>
    <?php endif; ?>

    <hr>
    <h2>Ejercicio 5 - Tabla UNICODE del 0 al 50000</h2>
    <p style="word-break: break-all;">
        <?php
            for ($i = 0; $i <= 50000; $i++) {
                echo "&#" . $i . "; ";
            }
        ?>
    </p>

    <hr>
    <?php
        $numero = rand(1, 10);
    ?>
    <h2>Ejercicio 6 - Tabla de multiplicar del <?= $numero ?></h2>
    <ul>
        <?php for ($i = 1; $i <= 10; $i++): ?>
            <li><?= $numero ?> x <?= $i ?> = <?= $numero * $i ?></li>
        <?php endfor; ?>
    </ul>

    <hr>
    <h2>Ejercicio 7 - Busca la fruta</h2>
    <p>Actualiza la página para mostrar otra búsqueda.</p>
    <?php
        $codigosFrutas = range(127815, 127827); // emojis de frutas en UNICODE
        $numFrutas = rand(7, 20);

        $frutasGeneradas = [];
        for ($i = 0; $i < $numFrutas; $i++) {
            $frutasGeneradas[] = $codigosFrutas[array_rand($codigosFrutas)];
        }

        $frutaBuscada = $frutasGeneradas[array_rand($frutasGeneradas)];
        $veces = 0;
        foreach ($frutasGeneradas as $fruta) {
            if ($fruta == $frutaBuscada) {
                $veces++;
            }
        }
    ?>
    <p><?= $numFrutas ?> frutas</p>
    <p>
        <?php foreach ($frutasGeneradas as $fruta): ?>
            &#<?= $fruta ?>;
        <?php endforeach; ?>
    </p>
    <p>La fruta &#<?= $frutaBuscada ?>; está <?= $veces ?> veces en la lista.</p>

    <hr>
    <h2>Ejercicio 8 - Apartado 1 - Tabla del 1 al 100</h2>
    <table border="1">
        <?php
            $num = 1;
            for ($fila = 0; $fila < 10; $fila++) {
                echo "<tr>";
                for ($col = 0; $col < 10; $col++) {
                    echo "<td>" . $num . "</td>";
                    $num++;
                }
                echo "</tr>";
            }
        ?>
    </table>

    <h2>Ejercicio 8 - Apartado 2 - Tabla de emoticonos aleatorios</h2>
    <?php
        $emojis = [128169, 128168, 127866, 128405, 129313];
    ?>
    <table border="1">
        <?php
            for ($fila = 0; $fila < 10; $fila++) {
                echo "<tr>";
                for ($col = 0; $col < 10; $col++) {
                    $emoji = $emojis[array_rand($emojis)];
                    echo "<td>&#" . $emoji . ";</td>";
                }
                echo "</tr>";
            }
        ?>
    </table>

</body>
</html>

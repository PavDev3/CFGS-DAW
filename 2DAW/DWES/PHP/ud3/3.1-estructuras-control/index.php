<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ejercicios UT3_1 - Estructuras de control</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">

    <header class="mb-4 text-center">
      <h1 class="display-5">Ejercicios UT3_1 - Estructuras de control</h1>
      <p><strong>Nombre:</strong> Pablo Núñez</p>
      <p><strong>Curso:</strong> 2º DAW</p>
      <p><strong>Módulo:</strong> Desarrollo web en entorno servidor</p>
    </header>

    <div class="card shadow-sm">
      <div class="card-body">

        <ul class="nav nav-tabs flex-wrap" id="ejerciciosTab">
          <li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" href="#ejercicio-1">Ejercicio 1</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-2">Ejercicio 2</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-3">Ejercicio 3</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-4">Ejercicio 4</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-5">Ejercicio 5</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-6">Ejercicio 6</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-7">Ejercicio 7</a></li>
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-8">Ejercicio 8</a></li>
        </ul>

        <div class="tab-content">

          <!-- EJERCICIO 1 (OPCIONAL) - Lanzar una moneda -->
          <div class="tab-pane fade show active" id="ejercicio-1">
            <div class="mt-4">
              <h2 class="h4">Lanzar una moneda</h2>
              <?php
                $resultado = rand(0, 1);
              ?>
              <p><?= $resultado == 1 ? 'Cara' : 'Cruz' ?></p>
            </div>
          </div>

          <!-- EJERCICIO 2 (OPCIONAL) - Adivina tu nota -->
          <div class="tab-pane fade" id="ejercicio-2">
            <div class="mt-4">
              <h2 class="h4">Adivina tu nota</h2>
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
            </div>
          </div>

          <!-- EJERCICIO 3 (OPCIONAL) - Comprobar números iguales -->
          <div class="tab-pane fade" id="ejercicio-3">
            <div class="mt-4">
              <h2 class="h4">Comprobar números iguales</h2>
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
            </div>
          </div>

          <!-- EJERCICIO 4 (OPCIONAL) - Suma de pares anteriores a un número -->
          <div class="tab-pane fade" id="ejercicio-4">
            <div class="mt-4">
              <h2 class="h4">Suma de pares anteriores a un número</h2>
              <?php if (!isset($_GET['numero'])): ?>
                <form method="get" class="row g-2 align-items-end">
                    <input type="hidden" name="tab" value="4">
                    <div class="col-auto">
                        <label class="form-label">Introduce un número:</label>
                        <input type="number" name="numero" class="form-control" required>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">Calcular</button>
                    </div>
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
                <a href="?" class="btn btn-secondary btn-sm">Volver al formulario</a>
              <?php endif; ?>
            </div>
          </div>

          <!-- EJERCICIO 5 (OPCIONAL) - Tabla UNICODE del 0 al 50000 -->
          <div class="tab-pane fade" id="ejercicio-5">
            <div class="mt-4">
              <h2 class="h4">Tabla UNICODE del 0 al 50000</h2>
              <p class="text-muted small">Se omiten los códigos de control (0-31 y 127-159), que no son válidos como referencia de carácter en HTML.</p>
              <p style="word-break: break-all;">
                <?php
                    for ($i = 0; $i <= 50000; $i++) {
                        if (($i >= 0 && $i <= 31) || ($i >= 127 && $i <= 159)) {
                            continue;
                        }
                        echo "&#" . $i . "; ";
                    }
                ?>
              </p>
            </div>
          </div>

          <!-- EJERCICIO 6 (OPCIONAL) - Tabla de multiplicar -->
          <div class="tab-pane fade" id="ejercicio-6">
            <div class="mt-4">
              <?php
                $numero = rand(1, 10);
              ?>
              <h2 class="h4">Tabla de multiplicar del <?= $numero ?></h2>
              <ul>
                <?php for ($i = 1; $i <= 10; $i++): ?>
                    <li><?= $numero ?> x <?= $i ?> = <?= $numero * $i ?></li>
                <?php endfor; ?>
              </ul>
            </div>
          </div>

          <!-- EJERCICIO 7 (OBLIGATORIO) - Busca la fruta -->
          <div class="tab-pane fade" id="ejercicio-7">
            <div class="mt-4">
              <h2 class="h4">Busca la fruta</h2>
              <p class="text-muted">Actualiza la página para mostrar otra búsqueda.</p>
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
              <p style="font-size: 1.5rem;">
                <?php foreach ($frutasGeneradas as $fruta): ?>
                    &#<?= $fruta ?>;
                <?php endforeach; ?>
              </p>
              <p>La fruta &#<?= $frutaBuscada ?>; está <?= $veces ?> veces en la lista.</p>
            </div>
          </div>

          <!-- EJERCICIO 8 (OBLIGATORIO) - Tablas -->
          <div class="tab-pane fade" id="ejercicio-8">
            <div class="mt-4">
              <h2 class="h4">Apartado 1 - Tabla del 1 al 100</h2>
              <table class="table table-bordered text-center">
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

              <h2 class="h4">Apartado 2 - Tabla de emoticonos aleatorios</h2>
              <?php
                $emojis = [128169, 128168, 127866, 128405, 129313];
              ?>
              <table class="table table-bordered text-center" style="font-size: 1.5rem;">
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
            </div>
          </div>

        </div>

      </div>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

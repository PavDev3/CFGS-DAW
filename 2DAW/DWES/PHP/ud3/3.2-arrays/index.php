<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ejercicios UT3_2 - Arrays</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">

    <header class="mb-4 text-center">
      <h1 class="display-5">Ejercicios UT3_2 - Arrays</h1>
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

          <!-- EJERCICIO 1 (OPCIONAL) - Array de 10 valores aleatorios -->
          <div class="tab-pane fade show active" id="ejercicio-1">
            <div class="mt-4">
              <h2 class="h4">Array de 10 valores aleatorios (1-30)</h2>
              <?php
                $array = [];
                for ($i = 0; $i < 10; $i++) {
                    $array[] = rand(1, 30);
                }
              ?>
              <p>Valores: <?= implode(", ", $array) ?></p>
            </div>
          </div>

          <!-- EJERCICIO 2 (OPCIONAL) - Operaciones con un array -->
          <div class="tab-pane fade" id="ejercicio-2">
            <div class="mt-4">
              <h2 class="h4">Operaciones con un array</h2>
              <?php
                $array = [];
                for ($i = 0; $i < 10; $i++) {
                    $array[] = rand(1, 30);
                }

                $media = array_sum($array) / count($array);

                $maximos = $array;
                rsort($maximos);
                $maximos = array_slice($maximos, 0, 3);

                $minimos = $array;
                sort($minimos);
                $minimos = array_slice($minimos, 0, 3);

                $arrayCopiado = $array;
                sort($arrayCopiado);

                $arrayCopiadoSinSegundo = $arrayCopiado;
                unset($arrayCopiadoSinSegundo[1]);
                $arrayCopiadoSinSegundo = array_values($arrayCopiadoSinSegundo);
              ?>
              <p>1-2) Array generado: <?= implode(", ", $array) ?></p>
              <p>3-4) Valor medio: <strong><?= round($media, 2) ?></strong></p>
              <p>5) Los 3 valores máximos: <?= implode(", ", $maximos) ?></p>
              <p>6) Los 3 valores mínimos: <?= implode(", ", $minimos) ?></p>
              <p>7) Array copiado y ordenado: <?= implode(", ", $arrayCopiado) ?></p>
              <p>8) Array copiado sin el segundo elemento: <?= implode(", ", $arrayCopiadoSinSegundo) ?></p>
            </div>
          </div>

          <!-- EJERCICIO 3 (OPCIONAL) - Array multidimensional países/ciudades -->
          <div class="tab-pane fade" id="ejercicio-3">
            <div class="mt-4">
              <h2 class="h4">Países y ciudades</h2>
              <?php
                $paises = [
                    "España" => ["Almería", "Talavera de la Reina", "Murcia"],
                    "Francia" => ["Marsella", "París", "Lyon"],
                    "Cuba" => ["La Habana", "Cienfuegos", "Santiago de Cuba"],
                    "Italia" => ["Roma", "Milán", "Nápoles"],
                    "Alemania" => ["Berlín", "Múnich", "Hamburgo"],
                ];
              ?>
              <?php foreach ($paises as $pais => $ciudades): ?>
                <p><strong><?= $pais ?>:</strong> <?= implode(", ", $ciudades) ?></p>
              <?php endforeach; ?>
            </div>
          </div>

          <!-- EJERCICIO 4 (OPCIONAL) - Ordenar array asociativo -->
          <div class="tab-pane fade" id="ejercicio-4">
            <div class="mt-4">
              <h2 class="h4">Ordenar array asociativo</h2>
              <?php
                $edades = array("Antonio" => "31", "María" => "28", "Juan" => "29", "Pepe" => "27");

                $ascValor = $edades;
                asort($ascValor);

                $ascClave = $edades;
                ksort($ascClave);

                $descValor = $edades;
                arsort($descValor);

                $descClave = $edades;
                krsort($descClave);

                function mostrarArrayAsociativo($array) {
                    $partes = [];
                    foreach ($array as $clave => $valor) {
                        $partes[] = "$clave: $valor";
                    }
                    return implode(", ", $partes);
                }
              ?>
              <p>Ascendente por valor: <?= mostrarArrayAsociativo($ascValor) ?></p>
              <p>Ascendente por clave: <?= mostrarArrayAsociativo($ascClave) ?></p>
              <p>Descendente por valor: <?= mostrarArrayAsociativo($descValor) ?></p>
              <p>Descendente por clave: <?= mostrarArrayAsociativo($descClave) ?></p>
            </div>
          </div>

          <!-- EJERCICIO 5 (OPCIONAL) - Capitales de Europa ordenadas por clave -->
          <div class="tab-pane fade" id="ejercicio-5">
            <div class="mt-4">
              <h2 class="h4">Capitales de Europa (ordenado por país)</h2>
              <?php
                $capitales = array("Italy"=>"Rome", "Luxembourg"=>"Luxembourg", "Belgium"=>"Brussels",
                    "Denmark"=>"Copenhagen", "Finland"=>"Helsinki", "France"=>"Paris",
                    "Slovakia"=>"Bratislava", "Slovenia"=>"Ljubljana", "Germany"=>"Berlin", "Greece"=>"Athens",
                    "Ireland"=>"Dublin", "Netherlands"=>"Amsterdam", "Portugal"=>"Lisbon",
                    "Spain"=>"Madrid", "Sweden"=>"Stockholm", "United Kingdom"=>"London",
                    "Cyprus"=>"Nicosia", "Lithuania"=>"Vilnius", "Czech Republic"=>"Prague",
                    "Estonia"=>"Tallin", "Hungary"=>"Budapest", "Latvia"=>"Riga", "Malta"=>"Valetta",
                    "Austria"=>"Vienna", "Poland"=>"Warsaw");

                ksort($capitales);
              ?>
              <?php foreach ($capitales as $pais => $capital): ?>
                <p>La capital de <?= strtoupper($pais) ?> es <?= strtoupper($capital) ?></p>
              <?php endforeach; ?>
            </div>
          </div>

          <!-- EJERCICIO 6 (OPCIONAL) - Array a JSON -->
          <div class="tab-pane fade" id="ejercicio-6">
            <div class="mt-4">
              <h2 class="h4">Array de capitales en formato JSON</h2>
              <?php
                $capitales = array("Italy"=>"Rome", "Luxembourg"=>"Luxembourg", "Belgium"=>"Brussels",
                    "Denmark"=>"Copenhagen", "Finland"=>"Helsinki", "France"=>"Paris",
                    "Slovakia"=>"Bratislava", "Slovenia"=>"Ljubljana", "Germany"=>"Berlin", "Greece"=>"Athens",
                    "Ireland"=>"Dublin", "Netherlands"=>"Amsterdam", "Portugal"=>"Lisbon",
                    "Spain"=>"Madrid", "Sweden"=>"Stockholm", "United Kingdom"=>"London",
                    "Cyprus"=>"Nicosia", "Lithuania"=>"Vilnius", "Czech Republic"=>"Prague",
                    "Estonia"=>"Tallin", "Hungary"=>"Budapest", "Latvia"=>"Riga", "Malta"=>"Valetta",
                    "Austria"=>"Vienna", "Poland"=>"Warsaw");

                $json = json_encode($capitales, JSON_PRETTY_PRINT);
              ?>
              <pre><?= htmlspecialchars($json) ?></pre>
            </div>
          </div>

          <!-- EJERCICIO 7 (OBLIGATORIO) - Sustituir valores menores que 5 -->
          <div class="tab-pane fade" id="ejercicio-7">
            <div class="mt-4">
              <h2 class="h4">Sustituir valores menores que 5 por "suspenso"</h2>
              <?php
                $array = [];
                for ($i = 0; $i < 50; $i++) {
                    $array[] = rand(1, 10);
                }

                $arrayOriginal = $array;

                foreach ($array as $clave => $valor) {
                    if ($valor < 5) {
                        $array[$clave] = "suspenso";
                    }
                }
              ?>
              <p>Array original: <?= implode(", ", $arrayOriginal) ?></p>
              <p>Array modificado: <?= implode(", ", $array) ?></p>
            </div>
          </div>

          <!-- EJERCICIO 8 (OBLIGATORIO) - Suma de pares e impares -->
          <div class="tab-pane fade" id="ejercicio-8">
            <div class="mt-4">
              <h2 class="h4">Suma de números pares e impares</h2>
              <?php
                $numeros = [];
                for ($i = 0; $i < 10; $i++) {
                    $numeros[] = rand(1, 100);
                }

                $sumaPares = 0;
                $sumaImpares = 0;
                foreach ($numeros as $numero) {
                    if ($numero % 2 == 0) {
                        $sumaPares += $numero;
                    } else {
                        $sumaImpares += $numero;
                    }
                }
              ?>
              <p>Números generados: <?= implode(", ", $numeros) ?></p>
              <table class="table table-bordered w-auto">
                <thead>
                  <tr><th>Tipo</th><th>Suma</th></tr>
                </thead>
                <tbody>
                  <tr><td>Pares</td><td><?= $sumaPares ?></td></tr>
                  <tr><td>Impares</td><td><?= $sumaImpares ?></td></tr>
                </tbody>
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

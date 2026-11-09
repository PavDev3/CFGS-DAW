<?php
require_once "funciones.php";
?>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ejercicios UT3_3 - Funciones y excepciones</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">

    <header class="mb-4 text-center">
      <h1 class="display-5">Ejercicios UT3_3 - Funciones y excepciones</h1>
      <p><strong>Nombre:</strong> Pablo Núñez</p>
      <p><strong>Curso:</strong> 2º DAW</p>
      <p><strong>Módulo:</strong> Desarrollo web en entorno servidor</p>
    </header>

    <div class="alert alert-warning">
      <strong>Nota:</strong> todavía no hemos dado funciones ni excepciones en clase
      (solo estructuras de control y arrays). He mirado la sintaxis en los recursos
      de Moodle (php.net, w3schools) para poder hacer el ejercicio, pero dejo
      preguntas comentadas en el código para preguntar en clase.
    </div>

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
          <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#ejercicio-9">Ejercicio 9</a></li>
        </ul>

        <div class="tab-content">

          <!-- EJERCICIO 1 (OPCIONAL) -->
          <div class="tab-pane fade show active" id="ejercicio-1">
            <div class="mt-4">
              <h2 class="h4">Suma, división y volumen</h2>
              <?php
                // 1a) sumar 5 números
                sumarCinco(1, 2, 3, 4, 5);
                echo "<br>";

                // 1b) dividir dos de cinco números aleatorios
                // PREGUNTA: aquí uso try/catch porque la función puede lanzar
                // una excepción si el divisor es 0. No sabemos todavía cómo
                // funciona esto por dentro, lo he copiado de un ejemplo de php.net.
                $num1 = rand(0, 9);
                $num2 = rand(0, 9);
                $num3 = rand(0, 9);
                $num4 = rand(0, 9);
                $num5 = rand(0, 9);
                try {
                    $tmp = dividirDosDeCinco($num1, $num2, $num3, $num4, $num5);
                    echo "Números generados: $num1, $num2, $num3, $num4, $num5<br>";
                    echo "Resultado de dividir el primero entre el segundo: $tmp";
                } catch (Exception $e) {
                    echo "No se ha podido dividir: " . $e->getMessage();
                }
                echo "<br>";

                // 1c) volumen de un cilindro
                $volumen = volumenCilindro(3, 5);
                echo "Volumen del cilindro (radio=3, altura=5): " . $volumen;
              ?>
            </div>
          </div>

          <!-- EJERCICIO 2 (OPCIONAL) -->
          <div class="tab-pane fade" id="ejercicio-2">
            <div class="mt-4">
              <h2 class="h4">Inicializar array</h2>
              <?php
                $miArray = inicializar_array(10, 1, 30);
                // Reutilizo la función del ejercicio 6 para mostrarlo
                imprimir_array($miArray);
              ?>
            </div>
          </div>

          <!-- EJERCICIO 3 (OPCIONAL) -->
          <div class="tab-pane fade" id="ejercicio-3">
            <div class="mt-4">
              <h2 class="h4">Media del array</h2>
              <?php
                $arrayMedia = inicializar_array(10, 1, 30);
                imprimir_array($arrayMedia);
                $media = calcular_media($arrayMedia);
                echo "<p>La media es: " . $media . "</p>";
              ?>
            </div>
          </div>

          <!-- EJERCICIO 4 (OPCIONAL) -->
          <div class="tab-pane fade" id="ejercicio-4">
            <div class="mt-4">
              <h2 class="h4">Máximo del array</h2>
              <?php
                $arrayMax = inicializar_array(10, 1, 30);
                imprimir_array($arrayMax);
                $maximo = calcular_maximo($arrayMax);
                echo "<p>El máximo es: " . $maximo . "</p>";
              ?>
            </div>
          </div>

          <!-- EJERCICIO 5 (OPCIONAL) -->
          <div class="tab-pane fade" id="ejercicio-5">
            <div class="mt-4">
              <h2 class="h4">Mínimo del array</h2>
              <?php
                $arrayMin = inicializar_array(10, 1, 30);
                imprimir_array($arrayMin);
                $minimo = calcular_minimo($arrayMin);
                echo "<p>El mínimo es: " . $minimo . "</p>";
              ?>
            </div>
          </div>

          <!-- EJERCICIO 6 (OPCIONAL) -->
          <div class="tab-pane fade" id="ejercicio-6">
            <div class="mt-4">
              <h2 class="h4">Imprimir array en tabla</h2>
              <?php
                $arrayTabla = inicializar_array(10, 1, 30);
                imprimir_array($arrayTabla);
              ?>
            </div>
          </div>

          <!-- EJERCICIO 7 (OBLIGATORIO) -->
          <div class="tab-pane fade" id="ejercicio-7">
            <div class="mt-4">
              <h2 class="h4">Ejercicio de UT3_2 reutilizando funciones.php</h2>
              <?php
                // 1) Inicializar un array de 10 elementos entre 1 y 30
                $array = inicializar_array(10, 1, 30);

                // 2) Imprimir todos los valores
                echo "<p>Array generado:</p>";
                imprimir_array($array);

                // 3 y 4) Calcular y mostrar el valor medio
                $media = calcular_media($array);
                echo "<p>Valor medio: " . $media . "</p>";

                // 5) Mostrar los 3 valores máximos
                // Hago una copia del array para no estropear el original al ordenarlo
                $copiaParaMaximos = $array;
                rsort($copiaParaMaximos);
                echo "<p>3 valores máximos: " . $copiaParaMaximos[0] . ", "
                     . $copiaParaMaximos[1] . ", " . $copiaParaMaximos[2] . "</p>";

                // 6) Mostrar los 3 valores mínimos
                $copiaParaMinimos = $array;
                sort($copiaParaMinimos);
                echo "<p>3 valores mínimos: " . $copiaParaMinimos[0] . ", "
                     . $copiaParaMinimos[1] . ", " . $copiaParaMinimos[2] . "</p>";
              ?>
            </div>
          </div>

          <!-- EJERCICIO 9 (OBLIGATORIO) -->
          <div class="tab-pane fade" id="ejercicio-9">
            <div class="mt-4">
              <h2 class="h4">Funciones con validación y excepciones</h2>

              <h3 class="h6 mt-3">Apartado A: sumar</h3>
              <?php
                try {
                    $resultadoSuma = sumar(15, 5);
                    echo "<p>Suma de 15 y 5: " . $resultadoSuma . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error: " . $e->getMessage() . "</p>";
                }

                // Probamos también con un valor incorrecto para ver la excepción
                try {
                    $resultadoSuma2 = sumar(15, "hola");
                    echo "<p>Suma: " . $resultadoSuma2 . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error al sumar 15 y 'hola': " . $e->getMessage() . "</p>";
                }
              ?>

              <h3 class="h6 mt-3">Apartado B: dividir y área del triángulo</h3>
              <?php
                try {
                    $resultadoDivision = dividir(20, 4);
                    echo "<p>División de 20 entre 4: " . $resultadoDivision . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error: " . $e->getMessage() . "</p>";
                }

                try {
                    $resultadoDivisionCero = dividir(20, 0);
                    echo "<p>División: " . $resultadoDivisionCero . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error al dividir entre 0: " . $e->getMessage() . "</p>";
                }

                try {
                    $resultadoArea = calcularAreaTriangulo(10, 5);
                    echo "<p>Área del triángulo (base=10, altura=5): " . $resultadoArea . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error: " . $e->getMessage() . "</p>";
                }

                try {
                    $resultadoAreaMal = calcularAreaTriangulo(-10, 5);
                    echo "<p>Área: " . $resultadoAreaMal . "</p>";
                } catch (Exception $e) {
                    echo "<p>Error con base negativa: " . $e->getMessage() . "</p>";
                }
              ?>
            </div>
          </div>

        </div>

      </div>
    </div>

  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

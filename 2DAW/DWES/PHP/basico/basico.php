<!DOCTYPE html>
<html lang="es">
<head>
  <!-- Metadatos básicos requeridos para la validación W3C -->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Ejercicios de PHP - Entorno Servidor</title>

  <!-- CSS de Bootstrap 5 -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <!-- Contenido principal de la página -->
  <main class="container my-5">
    
    <header class="mb-4 text-center">
      <h1 class="display-5">Desarrollo Web en Entorno Servidor</h1>
      <p class="text-muted">Estructura básica HTML5 con PHP y Bootstrap</p>
    </header>

    <div class="card shadow-sm">
      <div class="card-body">

        <ul class="nav nav-tabs" id="ejerciciosTab">
          <li class="nav-item">
            <a class="nav-link active" id="ejercicio-1-tab" data-bs-toggle="tab" href="#ejercicio-1">
              Ejercicio 1
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="ejercicio-2-tab" data-bs-toggle="tab" href="#ejercicio-2">
              Ejercicio 2
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="ejercicio-3-tab" data-bs-toggle="tab" href="#ejercicio-3">
              Ejercicio 3
            </a>
          </li>
        </ul>

      <div class="tab-content">
      <div class="tab-pane fade show active" id="ejercicio-1">
        <div class="random-number-container mt-4">
          <h2 class="h4">Número aleatorio generado:</h2>
          <p class="fs-3 text-primary">
            <button class="btn btn-primary mb-3" onclick="location.reload()">Generar Numero</button>
            <br>
            <?php
              // Generar un número aleatorio entre 1 y 100
              $numeroAleatorio = rand(1, 100);
              $tamanoAleatorio = rand(200, 800); // Generar un tamaño aleatorio entre 200 y 800 píxeles
              echo '<span class="d-inline-block border border-primary  p-3" style="font-size: ' . $tamanoAleatorio . '%;">' . $numeroAleatorio . '</span>';
              echo "<br>";
              echo "Tamaño aleatorio: " . $tamanoAleatorio . "%";
            ?>
          </p>
          

        </div>
       </div>

          <div class="tab-pane fade" id="ejercicio-2"></div>

          <div class="tab-pane fade" id="ejercicio-3"></div>
        </div>

      </div>
    </div>

  </main>

  <!-- JavaScript de Bootstrap (necesario para la interactividad) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

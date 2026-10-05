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
        <?php
          // Código PHP del ejercicio
          $mensaje = "¡Hola Mundo! Tu servidor PHP está funcionando correctamente.";
          $fechaActual = date("d/m/Y H:i");

          echo "<h2 class='h4 text-primary'>Mensaje desde el servidor:</h2>";
          echo "<p class='lead'>$mensaje</p>";
          echo "<hr>";
          echo "<p class='text-secondary small'>Fecha y hora de generación: $fechaActual</p>";
        ?>
      </div>
    </div>

  </main>

  <!-- JavaScript de Bootstrap (necesario para la interactividad) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

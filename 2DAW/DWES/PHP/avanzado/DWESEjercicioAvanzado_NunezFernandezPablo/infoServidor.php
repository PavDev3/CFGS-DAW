<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Información del Servidor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h1 class="display-5 text-center mb-4">Información del Servidor</h1>

        <?php
          // $_SERVER es un array que PHP rellena automáticamente con datos del servidor
          $nombreServidor = $_SERVER["SERVER_NAME"];
          $softwareServidor = $_SERVER["SERVER_SOFTWARE"];

          // phpversion() es una función propia de PHP que devuelve la versión instalada
          $versionPHP = phpversion();
        ?>

        <table class="table table-bordered">
          <tbody>
            <tr><th>Nombre del servidor:</th><td><?= $nombreServidor ?></td></tr>
            <tr><th>Software del servidor:</th><td><?= $softwareServidor ?></td></tr>
            <tr><th>Versión de PHP:</th><td><?= $versionPHP ?></td></tr>
          </tbody>
        </table>

        <div class="text-center">
          <a class="btn btn-primary" href="index.php">Volver</a>
        </div>
      </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Operaciones con Cadenas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h1 class="display-5 text-center mb-4">Operaciones con Cadenas</h1>

        <?php
          // Recogemos las 2 cadenas que llegan por GET desde index.php
          $cadena1 = $_GET["cadena1"];
          $cadena2 = $_GET["cadena2"];

          // Concatenar: unir las dos cadenas con el operador punto "."
          $concatenacion = $cadena1 . " " . $cadena2;

          // strlen() es una función propia de PHP que cuenta los caracteres de una cadena
          $longitud1 = strlen($cadena1);
          $longitud2 = strlen($cadena2);

          // substr() extrae una parte de la cadena. Con un número negativo
          // empieza a contar desde el final, así sacamos los últimos 10 caracteres
          $ultimos10 = substr($cadena2, -10);

          // str_replace() busca "pepe" dentro de la cadena y lo cambia por "Juan"
          $cadena2Reemplazada = str_replace("pepe", "Juan", $cadena2);
        ?>

        <table class="table table-bordered">
          <tbody>
            <tr><th>Cadena 1:</th><td><?= $cadena1 ?></td></tr>
            <tr><th>Cadena 2:</th><td><?= $cadena2 ?></td></tr>
            <tr><th>Concatenación de cadenas:</th><td><?= $concatenacion ?></td></tr>
            <tr><th>Longitud de la cadena 1:</th><td><?= $longitud1 ?> caracteres</td></tr>
            <tr><th>Longitud de la cadena 2:</th><td><?= $longitud2 ?> caracteres</td></tr>
            <tr><th>Últimos 10 caracteres de la cadena 2:</th><td><?= $ultimos10 ?></td></tr>
            <tr><th>Reemplazo de 'pepe' por 'Juan':</th><td><?= $cadena2Reemplazada ?></td></tr>
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

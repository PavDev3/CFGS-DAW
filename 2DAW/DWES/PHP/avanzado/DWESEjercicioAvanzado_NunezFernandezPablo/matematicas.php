<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Operaciones Matemáticas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  <main class="container my-5">
    <div class="card shadow-sm">
      <div class="card-body">
        <h1 class="display-5 text-center mb-4">Operaciones Matemáticas</h1>

        <?php
          // Recogemos los 3 números que llegan por GET desde index.php
          $num1 = $_GET["num1"];
          $num2 = $_GET["num2"];
          $num3 = $_GET["num3"];

          // Suma: sumamos los 3 números
          $suma = $num1 + $num2 + $num3;

          // Producto: multiplicamos los 3 números
          $producto = $num1 * $num2 * $num3;

          // Media: la suma dividida entre cuántos números son
          $media = $suma / 3;

          // max() y min() son funciones propias de PHP que devuelven
          // el valor más grande o más pequeño de los que le pasemos
          $maximo = max($num1, $num2, $num3);
          $minimo = min($num1, $num2, $num3);
        ?>

        <table class="table table-bordered">
          <tbody>
            <tr><th>Número 1:</th><td><?= $num1 ?></td></tr>
            <tr><th>Número 2:</th><td><?= $num2 ?></td></tr>
            <tr><th>Número 3:</th><td><?= $num3 ?></td></tr>
            <tr><th>Suma:</th><td><?= $suma ?></td></tr>
            <tr><th>Producto:</th><td><?= $producto ?></td></tr>
            <tr><th>Media:</th><td><?= $media ?></td></tr>
            <tr><th>Máximo:</th><td><?= $maximo ?></td></tr>
            <tr><th>Mínimo:</th><td><?= $minimo ?></td></tr>
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

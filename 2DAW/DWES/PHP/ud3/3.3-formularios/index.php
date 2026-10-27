<?php
// Ejercicio: calculadora de precio del servicio de newsletter
//
// Tabla de precios:
//   0        a 2000      -> 0 €
//   2001     a 10000     -> 0.7 € por unidad
//   10001    a infinito  -> 0.2 € por unidad
//
// Opcional: seguro por cada mensaje -> +0.1 € por email

$error = '';
$total = null;
$emails = '';
$seguro = false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $emails = trim($_POST['emails'] ?? '');
    $seguro = isset($_POST['seguro']);

    // Comprobar que el campo es un número válido
    if ($emails === '' || !is_numeric($emails)) {
        $error = 'Debes indicar un número válido de emails.';
    } else {
        $n = (float)$emails;

        if ($n < 0) {
            $error = 'El número de emails no puede ser negativo.';
        } else {
            // Precio por unidad según la tabla
            if ($n <= 2000) {
                $precioUnitario = 0;
            } elseif ($n <= 10000) {
                $precioUnitario = 0.7;
            } else {
                $precioUnitario = 0.2;
            }

            // Subtotal: emails * precio unitario
            $subtotal = $n * $precioUnitario;

            // Recargo por seguro: 0.1 € por mensaje
            if ($seguro) {
                $subtotal += $n * 0.1;
            }

            $total = $subtotal;
        }
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calculadora de precio - Newsletter</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        .error { color: #c00; margin-bottom: 15px; }
        .total { font-size: 1.2em; font-weight: bold; margin-top: 15px; }
        label { display: block; margin-top: 12px; }
        input[type="number"] { margin-left: 8px; padding: 4px; width: 150px; }
        button { margin-top: 18px; padding: 8px 16px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Calculadora de precio del servicio de newsletter</h1>

    <?php if ($error): ?>
        <p class="error"><?= htmlspecialchars($error) ?></p>
    <?php endif; ?>

    <?php if ($total !== null): ?>
        <p class="total">Precio total: <?= number_format($total, 2, ',', '.') ?> €</p>
    <?php endif; ?>

    <form method="post" action="">
        <label for="emails">Número de emails a enviar:</label>
        <input type="number" name="emails" id="emails" min="0" value="<?= htmlspecialchars($emails) ?>" required>

        <label for="seguro">
            <input type="checkbox" name="seguro" id="seguro" value="1" <?= $seguro ? 'checked' : '' ?>>
            Añadir seguro por cada mensaje (+0.1 € por email)
        </label>

        <button type="submit">Calcular precio total</button>
    </form>
</body>
</html>

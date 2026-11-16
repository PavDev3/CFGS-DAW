<?php
$nombre = trim($_POST['nombre'] ?? '');
$apellidos = trim($_POST['apellidos'] ?? '');
$salario = $_POST['salario'] ?? '';
$edad = $_POST['edad'] ?? '';

if ($nombre === '' || $apellidos === '' || $salario === '' || $edad === '') {
    header('Location: index.php?error=1');
    exit;
}

$salario = (float) $salario;
$edad = (int) $edad;

if ($salario > 2000) {
    $nuevoSalario = $salario;
} elseif ($salario >= 1000) {
    $nuevoSalario = $edad > 45 ? $salario * 1.03 : $salario * 1.10;
} else {
    if ($edad < 30) {
        $nuevoSalario = 1100;
    } elseif ($edad <= 45) {
        $nuevoSalario = $salario * 1.03;
    } else {
        $nuevoSalario = $salario * 1.15;
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo salario</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        .resultado { font-size: 1.2em; font-weight: bold; margin-top: 15px; }
    </style>
</head>
<body>
    <h1>Nuevo salario</h1>
    <p>Persona: <?= htmlspecialchars($nombre) ?> <?= htmlspecialchars($apellidos) ?></p>
    <p class="resultado">Nuevo salario: <?= number_format($nuevoSalario, 2, ',', '.') ?> €</p>
    <p><a href="index.php">Volver al formulario</a></p>
</body>
</html>

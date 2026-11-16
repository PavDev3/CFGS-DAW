<?php
$precio = trim($_POST['precio'] ?? '');
$tipo = trim($_POST['tipo'] ?? '');

$faltan = [];
if ($precio === '') {
    $faltan[] = 'precio';
}
if ($tipo === '') {
    $faltan[] = 'tipo de vivienda';
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resumen de la vivienda</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        .error { color: #c00; }
        ul { padding-left: 20px; }
    </style>
</head>
<body>
    <h1>Resumen de la vivienda</h1>

    <?php if (!empty($faltan)): ?>
        <p class="error">FALTAN DATOS: <?= htmlspecialchars(implode(', ', $faltan)) ?></p>
        <p><a href="index.php">Volver al formulario</a></p>
    <?php else: ?>
        <?php
        $zona = $_POST['zona'] ?? '';
        $direccion = $_POST['direccion'] ?? '';
        $habitaciones = $_POST['habitaciones'] ?? '';
        $extras = $_POST['extras'] ?? [];
        $observaciones = $_POST['observaciones'] ?? '';
        ?>
        <p>Estos son los datos que se guardarían:</p>
        <ul>
            <li>Tipo de vivienda: <?= htmlspecialchars($tipo) ?></li>
            <li>Zona: <?= htmlspecialchars($zona) ?></li>
            <li>Dirección: <?= htmlspecialchars($direccion) ?></li>
            <li>Habitaciones: <?= htmlspecialchars($habitaciones) ?></li>
            <li>Precio: <?= htmlspecialchars($precio) ?> €</li>
            <li>Extras: <?= $extras ? htmlspecialchars(implode(', ', $extras)) : 'Ninguno' ?></li>
            <li>Observaciones: <?= $observaciones !== '' ? nl2br(htmlspecialchars($observaciones)) : '—' ?></li>
        </ul>
        <p><a href="index.php">Añadir otra vivienda</a></p>
    <?php endif; ?>
</body>
</html>

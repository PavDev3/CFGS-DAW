<?php
$nombre = $_POST['nombre'] ?? '';
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Introducir nombre</title>
</head>
<body>
    <form method="POST">
        <label for="nombre">Introduce tu nombre:</label>
        <input type="text" id="nombre" name="nombre" required>
        <button type="submit">Enviar</button>
    </form>

    <?php if ($nombre !== ''): ?>
        <p>Hola, <?php echo htmlspecialchars($nombre); ?>!</p>
    <?php endif; ?>
</body>
</html>

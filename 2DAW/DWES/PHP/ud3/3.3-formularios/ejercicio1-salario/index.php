<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cálculo del salario</title>
    <style>
        body { font-family: Arial, sans-serif; max-width: 500px; margin: 40px auto; padding: 0 20px; }
        .error { color: #c00; margin-bottom: 15px; }
        label { display: block; margin-top: 12px; }
        input { margin-left: 8px; padding: 4px; width: 200px; }
        button { margin-top: 18px; padding: 8px 16px; cursor: pointer; }
    </style>
</head>
<body>
    <h1>Cálculo del salario</h1>

    <?php if (isset($_GET['error'])): ?>
        <p class="error">FALTAN DATOS</p>
        <p><a href="index.php">Volver al formulario</a></p>
    <?php endif; ?>

    <form method="post" action="salario.php">
        <label for="nombre">Nombre:
            <input type="text" name="nombre" id="nombre" required>
        </label>
        <label for="apellidos">Apellidos:
            <input type="text" name="apellidos" id="apellidos" required>
        </label>
        <label for="salario">Salario (€):
            <input type="number" name="salario" id="salario" step="0.01" min="0" required>
        </label>
        <label for="edad">Edad:
            <input type="number" name="edad" id="edad" min="0" required>
        </label>

        <button type="submit">Calcular salario</button>
    </form>
</body>
</html>

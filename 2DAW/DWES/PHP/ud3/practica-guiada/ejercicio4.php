<?php
// Práctica guiada UT3 - Ejercicio 4: formulario y procesamiento seguro
// Conductor: Pablo Núñez | Navegante: Pablo Núñez
// (Trabajo individual: no se asignó pareja, así que he hecho ambos roles yo mismo)

$nombre = "";
$nota1 = "";
$nota2 = "";
$error = "";
$resultado = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Recuperamos los campos sin tocar $_POST directamente
    $nombre = filter_input(INPUT_POST, "nombre");
    $nota1 = filter_input(INPUT_POST, "nota1");
    $nota2 = filter_input(INPUT_POST, "nota2");

    if (empty($nombre) || $nota1 === "" || $nota2 === "") {
        $error = "Todos los campos son obligatorios.";
    } elseif (!is_numeric($nota1) || !is_numeric($nota2)) {
        $error = "Las notas deben ser numéricas.";
    } elseif ($nota1 < 0 || $nota1 > 10 || $nota2 < 0 || $nota2 > 10) {
        $error = "Las notas deben estar comprendidas entre 0 y 10.";
    } else {
        $media = ($nota1 + $nota2) / 2;

        if ($media < 5) {
            $calificacion = "suspenso";
        } elseif ($media < 7) {
            $calificacion = "aprobado";
        } elseif ($media < 9) {
            $calificacion = "notable";
        } else {
            $calificacion = "sobresaliente";
        }

        $resultado = "$nombre, tu nota media es " . round($media, 2) . " ($calificacion).";
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cálculo de nota media</title>
</head>
<body>
    <h1>Cálculo de nota media</h1>

    <?php if ($error !== "") { ?>
        <p style="color: red;"><?php echo $error; ?></p>
    <?php } ?>

    <?php if ($resultado !== "") { ?>
        <p><?php echo $resultado; ?></p>
    <?php } ?>

    <form method="POST" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>">
        <label>Nombre: <input type="text" name="nombre" value="<?php echo htmlspecialchars($nombre); ?>"></label><br>
        <label>Nota 1: <input type="text" name="nota1" value="<?php echo htmlspecialchars($nota1); ?>"></label><br>
        <label>Nota 2: <input type="text" name="nota2" value="<?php echo htmlspecialchars($nota2); ?>"></label><br>
        <button type="submit">Calcular</button>
    </form>
</body>
</html>

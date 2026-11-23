<?php
// 1. Si el usuario ha enviado el formulario, guardamos su elección en la cookie
if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['idioma'])) {
    $idioma = $_POST['idioma'];
    
    setcookie('idioma', $idioma, time() + (3600 * 24 * 30), '/');
    
    header('Location: ' . $_SERVER['PHP_SELF']);
    exit;
}

// 2. Comprobamos si existe la cookie
$tiene_cookie = isset($_COOKIE['idioma']);
$idioma_actual = $tiene_cookie ? $_COOKIE['idioma'] : 'es';

// 3. Definimos los textos según el idioma y si la cookie ya existe o no
if ($idioma_actual === 'en') {
    $titulo = "Welcome to our website";
    $descripcion = "Your language preference has been saved in a cookie.";
    $etiqueta_select = "Choose your language:";
    $opcion_es = "Spanish";
    $opcion_en = "English";
    $boton = "Save preference";
} else {
    $titulo = "Bienvenido a nuestra página web";
    // Si tiene cookie mostramos que está guardada; si no, avisamos de que se muestra en español por defecto
    $descripcion = $tiene_cookie 
        ? "Tu preferencia de idioma se ha guardado en una cookie." 
        : "Mostrando el contenido en español por defecto. Elige un idioma si deseas guardar tu preferencia.";
    $etiqueta_select = "Elige tu idioma:";
    $opcion_es = "Español";
    $opcion_en = "Inglés";
    $boton = "Guardar preferencia";
}
?>
<!DOCTYPE html>
<html lang="<?php echo $idioma_actual; ?>">
<head>
    <meta charset="UTF-8">
    <title><?php echo $titulo; ?></title>
</head>
<body>
    <h1><?php echo $titulo; ?></h1>
    <p><?php echo $descripcion; ?></p>

    <form method="POST" action="<?php echo $_SERVER['PHP_SELF']; ?>">
        <label for="idioma"><?php echo $etiqueta_select; ?></label>
        <select name="idioma" id="idioma">
            <option value="es" <?php if ($idioma_actual === 'es') echo 'selected'; ?>>
                <?php echo $opcion_es; ?>
            </option>
            <option value="en" <?php if ($idioma_actual === 'en') echo 'selected'; ?>>
                <?php echo $opcion_en; ?>
            </option>
        </select>
        <input type="submit" value="<?php echo $boton; ?>">
    </form>
</body>
</html>
Explain
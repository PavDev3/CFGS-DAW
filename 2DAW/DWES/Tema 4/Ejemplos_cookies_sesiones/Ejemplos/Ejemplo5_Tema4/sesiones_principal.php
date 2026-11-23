<?php
    session_start();
    if(!isset($_SESSION["usuario"])){
        header("Location:sesiones_login.php?redirigido=true");
    }
?>

<!DOCTYPE html>
<html>
    <head>
        <title>Página principal</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <?php echo "Bienvenido ".$_SESSION["usuario"];?>
        <br><a href="sesiones_logout.php">Salir</a>
    </body>
</html>


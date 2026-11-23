<?php
/* Formulario de login habitual.
 * Si va bien --> abre sesión, guarda el nombre de usuario y redirige a sesiones_principal.php
 * Si va mal --> mensaje de error
 */

//Función que comprueba el usuario
function comprobar_usuario($nombre, $clave) {
//si el usuario es "usuario" ---> rol 0
    if ($nombre === "usuario" && $clave === "1234") {
        $usu["nombre"] = "usuario";
        $usu["rol"] = 0;
        return $usu;
        //si el usuario es "admin" ---> rol 1
    } else if ($nombre === "admin" && $clave === "1234") {
        $usu["nombre"] = "admin";
        $usu["rol"] = 1;
        return $usu;
    } 
    else{
        return FALSE;
    }
}
    //compruebo si he recibido una petición POST
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $usu = comprobar_usuario($_POST["usuario"], $_POST["clave"]);

  
        //Si hay error en el usuario
        if ($usu == FALSE) {
            $err = TRUE;
            $usuario = $_POST["usuario"];
        } else {
            session_start();
            $_SESSION["usuario"] = $_POST["usuario"];
            header("Location:sesiones_principal.php");
        }
    }

?>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Formulario de login</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <?php
        if (isset($_GET["redirigido"])) {
            echo "<p>Haga login para continuar</p>";
        }
        ?>

        <?php
        if (isset($err) and $err == true) {
            echo "<p>Revise usuario y contraseña</p>";
        }
        ?>

        <form method ="POST" action ="<?php echo $_SERVER["PHP_SELF"]; ?>"> 
            Usuario
            <!-- INPUT USUARIO -->
            <input value="<?php if (isset($usuario)) echo $usuario; ?>"
                   id ="usuario" name="usuario" type="text">   
            <!-- INPUT CLAVE -->
            <input id="clave" name="clave" type="password">
            <input type="submit">
        </form>    
    </body>
</html>

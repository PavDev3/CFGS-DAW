<?php
$nombre = (isset($_COOKIE['nombre'])) ? $_COOKIE['nombre'] : null;
?>
<!DOCTYPE html> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title> Página de bienvenida </title> 
    </head> 
    <body> 
        <div id="bloque" align='center'>    
            <?php if ($nombre) { ?> 
                <h1> PROBANDO COOKIE</h1> 
                <h2> Bienvenido/a <?php echo $nombre; ?> </h2> 
                <p> ¿Qué quieres hacer? 
                    <a href="pages/guardar_nombre.php"> Cambiar mi nombre </a> | 
                    <a href="pages/eliminar_nombre.php"> Eliminar mi nombre </a> 
                </p> 
            <?php } else { ?> 
                <h1> Bienvenido/a usuario desconocido </h1> 
                <p> <a href="pages/guardar_nombre.php"> 
                        Seleccionar un nombre para identificarme </a>     
                </p> 
            <?php } ?> 
        </div>
    </body> 
</html>  

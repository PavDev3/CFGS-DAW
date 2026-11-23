<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ejemplo 1 Tema 4</title>
    </head>
    <body>
        <?php
            setcookie('nueva',"valor", time()+3600*24,"/");
            echo $_COOKIES["nueva"];
        ?>
    </body>
</html>

<?php

session_start();
if(!isset($_SESSION["count"])){
    $_SESSION["count"] = 0;
}
else{
    $_SESSION["count"]++;
}

echo "Hola ".$_SESSION["count"];
echo "<br><a href='sesiones_uso_basico2.php'>Siguiente</a>";


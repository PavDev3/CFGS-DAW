<?php
  session_start();

  if(isset( $_POST['token'] ) ){
   
    $token = $_POST['token'];
   
    if ($_SESSION['token'] == $token){
        $nombre = $_POST['nombre'];
        echo "<br>Hola " . $nombre. ", tienes permiso de entrada.";
    }
    else 
        echo "<br>Has intentado acceder sin cumplir con el token";
    
} else
     echo "<br>Has intentado acceder sin cumplir con el token";

echo "<br>";
echo '<p><a href="formulario.php">Volver</a></p>';




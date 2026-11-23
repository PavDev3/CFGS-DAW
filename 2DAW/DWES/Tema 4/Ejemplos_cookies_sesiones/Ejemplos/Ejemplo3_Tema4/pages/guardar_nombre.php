<?php
  $nombre = (isset($_COOKIE['nombre'])) ? $_COOKIE['nombre'] : null;
  if ($_SERVER['REQUEST_METHOD'] == 'POST') {
      $nombre = $_POST['nombre'];
      setcookie('nombre', $nombre, time() + 86400, '/');
      header('Location: ../index.php');
      exit;
 }
?> 
<!DOCTYPE> 
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title> Guardar nombre </title> 
    </head> 
    <body> 
      <div id="bloque" align='center'>    
       <h1> Guardo la cookie</h1> 
       <form method="post" action="guardar_nombre.php"> 
          <label> Teclea nombre a guardar: </label> 
          <input type="text" name="nombre" value="<?php echo $nombre;?>" 
               required="required" /> 
          <input type="submit" value="Guardar" /> 
          <a href="../index.php"> Volver </a> 
          </form> 
      </div>
    </body> 
</html>



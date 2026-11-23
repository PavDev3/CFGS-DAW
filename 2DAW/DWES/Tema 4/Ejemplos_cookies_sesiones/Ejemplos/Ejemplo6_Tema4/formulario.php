<?php
session_start();
?>

<div id="bloque" align='center'> 
    <h1>Probando tokens</h1>     

    <h2>Formulario con permiso</h2>     
    <form method="POST" action="envio.php">
        <label>Nombre: </label><input type="text" name="nombre">
        <input type="hidden" name="token" 
              value="<?php echo $_SESSION['token']; ?>">
        <input type="submit" value="enviar">
    </form>
    
    <h2>Formulario sin permiso</h2>     
    <form method="POST" action="envio.php">
        <label>Nombre: </label><input type="text" name="nombre">
        <input type="submit" value="enviar">
    </form>

</div>



<?php
// Ejercicio 8: cambiar entre tema claro y tema oscuro con un enlace.
// Por defecto se carga el tema claro.
// PREGUNTA PARA LA PROFESORA: uso $_GET para leer "?estilo=..." de la URL,
// lo he visto en los apuntes de UT2 pero no lo hemos usado mucho, no sé si
// esta es la forma correcta de guardar "qué tema está activo" o si se
// debería usar sesiones en vez de la URL.

$estilo = "temaClaro"; // valor por defecto

if (isset($_GET["estilo"]) && $_GET["estilo"] == "temaOscuro") {
    $estilo = "temaOscuro";
}

if ($estilo == "temaOscuro") {
    include "../resources/templates/cabeceraTemaOscuro.php";
} else {
    include "../resources/templates/cabeceraTemaClaro.php";
}
?>

  <p>Contenido de la página de ejemplo.</p>

<?php
include "../resources/templates/footer.php";
?>

</body>
</html>

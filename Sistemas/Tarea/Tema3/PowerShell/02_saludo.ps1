$nombre = Read-Host "Introduce tu nombre"
if ($nombre -eq "") {
    $nombre = "desconocido"
}
Write-Output "Hola, $nombre!"
$File = Read-Host "Introduce el fichero"
$lineas = Get-Content -Path $File
$numeros = @()
foreach ($linea in $lineas) {
    $lineaTrim = $linea.Trim()
    if ($lineaTrim -eq "") {
        continue
    }
    try {
        $numero = [double]$lineaTrim
        $numeros += $numero
    } catch {
        Write-Output "Error: La linea '$linea' no es un numero valido."
        exit 1
    }
}
if ($numeros.Count -eq 0) {
    Write-Output "Error: No se encontraron numeros validos en el fichero '$File'."
    exit 1
}
$cantidad = $numeros.Count
$minimo = ($numeros | Measure-Object -Minimum).Minimum
$maximo = ($numeros | Measure-Object -Maximum).Maximum
$suma = ($numeros | Measure-Object -Sum).Sum
$media = [math]::Round($suma / $cantidad, 2)
Write-Output "Cantidad de numeros: $cantidad"
Write-Output "Minimo: $minimo"
Write-Output "Maximo: $maximo"
Write-Output "Media: $media"


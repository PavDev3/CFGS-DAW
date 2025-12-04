param(
    [Parameter(Mandatory=$true)]
    [string]$File
)
if (-not (Test-Path -Path $File -PathType Leaf)) {
    Write-Output "Error: El fichero '$File' no existe."
    exit 1
}
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


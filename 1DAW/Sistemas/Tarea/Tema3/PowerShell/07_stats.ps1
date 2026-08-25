$File = Read-Host "Introduce el fichero"
if (-not (Test-Path -Path $File -PathType Leaf)) {
    Write-Output "Error: El fichero '$File' no existe."
    exit 1
}
Get-Item -Path $File
$size = (Get-Item -Path $File).Length
Write-Output "El fichero '$File' tiene un tamaño de $size bytes."

#Contamos lienas e ignoramos vacios y espacios
$lines = Get-Content -Path $File | Where-Object { $_ -ne "" -and $_ -ne " " }
$linesCount = $lines.Count
Write-Output "El fichero '$File' tiene $linesCount lineas."

# Guardamos los numeros en una array
$numbers = @()
Get-Content -Path $File | ForEach-Object {
    # Buscar todos los números en la línea (enteros y decimales)
    $numberMatches = [regex]::Matches($_, "-?\d+(\.\d+)?")
    foreach ($match in $numberMatches) {
        $number = [double]$match.Value
        if ($number -ne 0) {
            $numbers += $number
        }
    }
}
$numbersCount = $numbers.Count
Write-Output "El fichero '$File' tiene $numbersCount numeros."

# Calculamos el minimo, maximo y media
if ($numbersCount -gt 0) {
    $min = ($numbers | Measure-Object -Minimum).Minimum
    $max = ($numbers | Measure-Object -Maximum).Maximum
    $media = [math]::Round(($numbers | Measure-Object -Sum).Sum / $numbersCount, 2)
    
    Write-Output "El minimo es $min"
    Write-Output "El maximo es $max"
    Write-Output "La media es $media"
} else {
    Write-Output "No se encontraron numeros en el fichero."
}


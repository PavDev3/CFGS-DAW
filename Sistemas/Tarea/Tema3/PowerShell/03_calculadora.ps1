[int]$a = Read-Host "Introduce el primer numero"
[int]$b = Read-Host "Introduce el segundo numero"
if ($b -eq 0) {
    Write-Output "Error: El segundo numero no puede ser 0"
    exit 1
}
$suma = $a + $b
$resta = $a - $b
$multiplicacion = $a * $b
$division = $a / $b
Write-Output "Suma: $suma"
Write-Output "Resta: $resta"
Write-Output "Multiplicacion: $multiplicacion"
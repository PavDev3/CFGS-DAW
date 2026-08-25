$n = Read-Host "Introduce un numero"
for ($i = 1; $i -le 10; $i++) {
    Write-Output "$n x $i = $($n * $i)"
}
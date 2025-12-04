param(
    [Parameter(Mandatory=$true)]
    [string]$Dir,
    
    [Parameter(Mandatory=$true)]
    [string]$Extension
)
if (-not (Test-Path -Path $Dir -PathType Container)) {
    Write-Output "Error: El directorio '$Dir' no existe."
    exit 1
}
if (-not $Extension.StartsWith(".")) {
    $Extension = "." + $Extension
}
$archivos = Get-ChildItem -Path $Dir -File | Where-Object { $_.Extension -eq $Extension }

$cantidad = $archivos.Count

Write-Output "Archivos $Extension en $Dir : $cantidad"
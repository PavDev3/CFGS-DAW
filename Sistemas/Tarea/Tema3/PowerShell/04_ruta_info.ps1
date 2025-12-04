param(
    [Parameter(Mandatory=$false)]
    [string]$Path
)
if (-not $Path -or $Path -eq "") {
    Write-Output "Uso: .\04_ruta_info.ps1 -Path <ruta>"
    exit 1
}
if (-not (Test-Path -Path $Path)) {
    Write-Output "La ruta no existe"
    exit 1
}
$item = Get-Item -Path $Path
$tipo = ""
if ($item.LinkType) {
    if ($item.LinkType -eq "SymbolicLink") {
        $tipo = "enlace (symlink)"
    } elseif ($item.LinkType -eq "Junction") {
        $tipo = "enlace (junction)"
    } else {
        $tipo = "enlace"
    }
} elseif ($item.PSIsContainer) {
    $tipo = "directorio"
} else {
    $tipo = "fichero"
}
Write-Output "La ruta $Path es un $tipo"

if ($tipo -eq "fichero") {
    $tamano = $item.Length
    Write-Output "Tamaño: $tamano bytes"
}

if ($tipo -eq "directorio") {
    $elementos = (Get-ChildItem -Path $Path).Count
    Write-Output "Elementos directos: $elementos"
}
$acl = Get-Acl -Path $Path
$propietario = $acl.Owner
Write-Output "Propietario: $propietario"

Write-Output "Permisos:"
foreach ($regla in $acl.Access) {
    $identidad = $regla.IdentityReference
    $permisos = $regla.FileSystemRights
    $tipoAcceso = $regla.AccessControlType
    Write-Output "  $identidad : $permisos ($tipoAcceso)"
}

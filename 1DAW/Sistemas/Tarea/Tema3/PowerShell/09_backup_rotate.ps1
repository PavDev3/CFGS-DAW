$Source = Read-Host "Introduce el directorio a respaldar"
$Destination = Read-Host "Introduce el directorio donde guardar backups"
$Keep = Read-Host "Introduce el número de copias a conservar"

# Validar Source
if (-not (Test-Path -Path $Source -PathType Container)) {
    Write-Output "Error: El directorio origen '$Source' no existe."
    exit 1
}

# Validar Destination
if (-not (Test-Path -Path $Destination -PathType Container)) {
    Write-Output "Error: El directorio destino '$Destination' no existe."
    exit 1
}

# Validar Keep
$Keep = [int]$Keep
if ($Keep -le 0) {
    Write-Output "Error: El número de copias a conservar debe ser mayor que 0."
    exit 1
}

# Crear timestamp YYYYmmdd_HHMMSS
$timestamp = Get-Date -Format "yyyyMMdd_HHmmss"
$backupName = "backup_$timestamp.zip"
$backupPath = Join-Path -Path $Destination -ChildPath $backupName

# Crear el backup
Write-Output "Creando backup: $backupName"
Compress-Archive -Path $Source -DestinationPath $backupPath -Force
Write-Output "Backup creado: $backupPath"

# Obtener todos los backups ordenados por fecha (más recientes primero)
$allBackups = Get-ChildItem -Path $Destination -Filter "backup_*.zip" | Sort-Object -Property LastWriteTime -Descending

# Eliminar backups antiguos si hay más de Keep
if ($allBackups.Count -gt $Keep) {
    $backupsToDelete = $allBackups | Select-Object -Skip $Keep
    foreach ($backup in $backupsToDelete) {
        Write-Output "Eliminando backup antiguo: $($backup.Name)"
        Remove-Item -Path $backup.FullName -Force
    }
}

Write-Output "Rotación completada. Se mantienen $Keep copias más recientes."


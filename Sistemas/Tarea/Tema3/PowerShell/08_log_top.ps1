$Log = Read-Host "Introduce la ruta del log"
if (-not (Test-Path -Path $Log -PathType Leaf)) {
    Write-Output "Error: El archivo de log '$Log' no existe."
    exit 1
}

# Contadores
$ipCount = @{}
$resourceCount = @{}
$statusCount = @{}
$totalRequests = 0

# Leer el log
Get-Content -Path $Log | ForEach-Object {
    $line = $_
    # Formato: IP - - [timestamp] "METHOD /recurso HTTP/version" status ...
    if ($line -match '^(\S+)\s+.*?"\w+\s+(\S+).*?"\s+(\d{3})') {
        $totalRequests++
        
        # Contar IP
        $ip = $matches[1]
        if ($ipCount.ContainsKey($ip)) {
            $ipCount[$ip]++
        } else {
            $ipCount[$ip] = 1
        }
        
        # Contar recurso
        $resource = $matches[2]
        if ($resourceCount.ContainsKey($resource)) {
            $resourceCount[$resource]++
        } else {
            $resourceCount[$resource] = 1
        }
        
        # Contar código HTTP
        $status = $matches[3]
        if ($statusCount.ContainsKey($status)) {
            $statusCount[$status]++
        } else {
            $statusCount[$status] = 1
        }
    }
}

# Top 5 IPs
Write-Output "Top 5 IPs por número de peticiones:"
$topIPs = $ipCount.GetEnumerator() | Sort-Object -Property Value -Descending | Select-Object -First 5
$i = 1
foreach ($ipEntry in $topIPs) {
    Write-Output "$i. $($ipEntry.Key): $($ipEntry.Value) peticiones"
    $i++
}

Write-Output ""

# Top 5 recursos
Write-Output "Top 5 recursos solicitados:"
$topResources = $resourceCount.GetEnumerator() | Sort-Object -Property Value -Descending | Select-Object -First 5
$i = 1
foreach ($resourceEntry in $topResources) {
    Write-Output "$i. $($resourceEntry.Key): $($resourceEntry.Value) peticiones"
    $i++
}

Write-Output ""

# Total y códigos HTTP
Write-Output "Total de peticiones: $totalRequests"
Write-Output ""
Write-Output "Resumen por código HTTP:"
$sortedStatus = $statusCount.GetEnumerator() | Sort-Object -Property Name
foreach ($statusEntry in $sortedStatus) {
    Write-Output "$($statusEntry.Key): $($statusEntry.Value) peticiones"
}

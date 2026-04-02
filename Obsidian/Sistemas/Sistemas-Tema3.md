# Tema 3 — PowerShell y Scripting Windows

← [[Sistemas-Tema2]] | [[Sistemas]] | Siguiente: [[Sistemas-Tema4]]

---

## PowerShell

PowerShell es un intérprete de comandos y lenguaje de scripting de Microsoft.

### Configuración inicial

```powershell
# Permitir ejecución de scripts (solo sesión actual)
Set-ExecutionPolicy -Scope Process Bypass
```

---

## Ejercicios de PowerShell

### 01_hola.ps1 — Variables y salida

```powershell
$texto = "Hola, DAW"
Write-Host $texto
Write-Host (Get-Date -Format "yyyy-MM-dd HH:mm:ss")
```

### 02_saludo.ps1 — Lectura por teclado

```powershell
param([string]$nombre = "desconocido")
if (-not $nombre) { $nombre = "desconocido" }
Write-Host "Hola, $nombre!"
```

### 03_calculadora.ps1 — Aritmética

```powershell
$a = [int](Read-Host "Introduce a")
$b = [int](Read-Host "Introduce b")
Write-Host "Suma: $($a + $b)"
Write-Host "Resta: $($a - $b)"
Write-Host "Multiplicación: $($a * $b)"
if ($b -ne 0) {
    Write-Host "División: $([math]::Floor($a / $b))"
} else {
    Write-Host "No se puede dividir entre 0"
}
```

### 05_tabla.ps1 — Bucles

```powershell
$n = [int](Read-Host "Introduce un número")
for ($i = 1; $i -le 10; $i++) {
    Write-Host "$n x $i = $($n * $i)"
}
```

---

## Ejercicios Windows (Tarea 3)

Los ejercicios del Tema 3 cubren configuración del sistema operativo Windows:

| Ejercicio | Descripción |
|-----------|-------------|
| 1 | Teclado táctil, barra de tareas, pantalla de bloqueo |
| 2 | Símbolo del sistema: `cls`, `ver`, `vol`, `time /t` |
| 3 | Instalar PowerShell última versión |
| 5 | Variables de entorno: `COMPUTERNAME`, `TEMP`, `PATH` |
| 7 | Crear carpetas con Explorer y comandos `mkdir`, `rmdir` |
| 8 | Crear usuarios `alumno1`, `alumno2`, `profesor` y grupo `Clase` |
| 12 | Programar tarea de apagado a las 23:30 |
| 16 | Script `apagar.bat` con aviso de apagado en 60 segundos |
| 17 | Script `datos.ps1`: limpiar pantalla, mostrar equipo, usuario y fecha |
| 19 | Clonar VM y crear volumen RAID 1 |
| 21 | Instalar WSL (Windows Subsystem for Linux) con Debian |

---

## Ejercicios de PowerShell (enunciados completos)

La tanda completa de 10 ejercicios de scripting:

| # | Script | Descripción |
|---|--------|-------------|
| 1 | `01_hola.ps1` | Variables y salida básica |
| 2 | `02_saludo.ps1` | Lectura de teclado |
| 3 | `03_calculadora.ps1` | Aritmética y validación |
| 4 | `04_ruta_info.ps1` | Análisis de rutas (ficheros, ACL) |
| 5 | `05_tabla.ps1` | Tabla de multiplicar (bucles) |
| 6 | `06_contar_ext.ps1` | Contador de ficheros por extensión |
| 7 | `07_stats.ps1` | Estadísticas de fichero numérico |
| 8 | `08_log_top.ps1` | Analizador de access.log |
| 9 | `09_backup_rotate.ps1` | Copia de seguridad con rotación |
| 10 | `10_todo.ps1` | Gestor de tareas CLI persistente |

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema3/PowerShell/Ejercicios.md`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tema/Tema 3/PresentacionGuiaPowershell.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tema/Tema 3/Resumen apuntes unidad 3.pdf`

Los scripts PowerShell completados están en:
`/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema3/PowerShell/`

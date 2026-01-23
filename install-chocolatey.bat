@echo off
setlocal enabledelayedexpansion

REM Script para instalar Chocolatey en Windows (como Administrador)
REM Uso: install-chocolatey.bat

echo 🍫 Instalador de Chocolatey para Windows
echo =====================================

REM Verificar si se está ejecutando como administrador
echo 📋 Paso 1: Verificando permisos de administrador...
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERROR: Este script requiere ejecutarse como Administrador
    echo.
    echo 💡 Cómo ejecutar como Administrador:
    echo 1. Haz clic derecho en este archivo .bat
    echo 2. Selecciona "Ejecutar como administrador"
    echo 3. Haz clic en "Sí" en el control de cuentas de usuario
    echo.
    echo ⏹️  Presiona cualquier tecla para salir...
    pause >nul
    exit /b 1
)
echo ✅ Ejecutando como Administrador

REM Verificar si Chocolatey ya está instalado
echo 📋 Paso 2: Verificando si Chocolatey ya está instalado...
where >nul 2>nul choco
if %errorlevel% equ 0 (
    echo ✅ Chocolatey ya está instalado
    for /f "tokens=*" %%i in ('choco --version') do set CHOCO_VERSION=%%i
    echo 📦 Versión: !CHOCO_VERSION!
    echo.
    echo 🔄 ¿Deseas actualizar Chocolatey a la última versión? (s/n)
    set /p UPDATE_CHOICE=
    if /i "!UPDATE_CHOICE!"=="s" (
        echo 📥 Actualizando Chocolatey...
        choco upgrade chocolatey -y
        if !errorlevel! equ 0 (
            echo ✅ Chocolatey actualizado exitosamente
        ) else (
            echo ⚠️  Error al actualizar Chocolatey
        )
    )
    echo ✅ Instalación completada
    pause
    exit /b 0
)

REM Instalar Chocolatey
echo 📋 Paso 3: Instalando Chocolatey...
echo 📥 Descargando e instalando Chocolatey (requiere conexión a internet)...

REM Usar PowerShell para instalar Chocolatey
powershell -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command ^
    "try { ^
        Write-Host '🔍 Configurando protocolos de seguridad...'; ^
        [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; ^
        Write-Host '📥 Descargando script de instalación...'; ^
        iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1')); ^
        Write-Host '✅ Chocolatey instalado exitosamente'; ^
        exit 0; ^
    } catch { ^
        Write-Host '❌ Error durante la instalación:'; ^
        Write-Host $_.Exception.Message; ^
        Write-Host '🔍 Verificando conexión a internet...'; ^
        try { ^
            Test-Connection -ComputerName google.com -Count 1 -Quiet; ^
            Write-Host '✅ Conexión a internet disponible'; ^
        } catch { ^
            Write-Host '❌ Sin conexión a internet'; ^
        } ^
        exit 1; ^
    }"

if %errorlevel% neq 0 (
    echo ❌ Error al instalar Chocolatey automáticamente
    echo.
    echo 🔍 Solución de problemas:
    echo 1. Verifica tu conexión a internet
    echo 2. Desactiva temporalmente el antivirus/firewall
    echo 3. Asegúrate de tener PowerShell 5.0 o superior
    echo.
    echo 📋 Instalación manual alternativa:
    echo 1. Abre PowerShell como Administrador
    echo 2. Ejecuta estos comandos uno por uno:
    echo    Set-ExecutionPolicy Bypass -Scope Process -Force
    echo    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    echo    iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
    echo.
    echo 🌐 Descarga directa: https://chocolatey.org/install
    echo.
    pause
    exit /b 1
)

echo ✅ Chocolatey instalado exitosamente

REM Refrescar variables de entorno
echo 📋 Paso 4: Configurando variables de entorno...
call "%ALLUSERSPROFILE%\chocolatey\bin\RefreshEnv.cmd" 2>nul
set "PATH=%ALLUSERSPROFILE%\chocolatey\bin;%PATH%"

REM Verificar instalación
echo 📋 Paso 5: Verificando instalación...
timeout /t 3 /nobreak >nul
where >nul 2>nul choco
if %errorlevel% neq 0 (
    echo ⚠️  Chocolatey está instalado pero no disponible en esta sesión
    echo 💡 Reinicia la terminal o abre una nueva ventana
    echo.
    echo 🔄 ¿Deseas reiniciar el explorador de Windows para aplicar los cambios? (s/n)
    set /p RESTART_EXPLORER=
    if /i "!RESTART_EXPLORER!"=="s" (
        echo 🔄 Reiniciando explorador de Windows...
        taskkill /f /im explorer.exe
        start explorer.exe
        timeout /t 5 /nobreak >nul
    )
) else (
    echo ✅ Chocolatey disponible en esta sesión
    for /f "tokens=*" %%i in ('choco --version') do set CHOCO_VERSION=%%i
    echo 📦 Versión instalada: !CHOCO_VERSION!
)

REM Configurar Chocolatey
echo 📋 Paso 6: Configurando Chocolatey...
echo 🔄 Ejecutando choco upgrade all -y para actualizar paquetes del sistema...
choco upgrade all -y

echo.
echo 🎉 ¡Chocolatey instalado y configurado exitosamente!
echo.
echo 📋 Comandos útiles:
echo    choco --version           ^| Ver versión
echo    choco search nombre       ^| Buscar paquetes
echo    choco install paquete     ^| Instalar paquete
echo    choco upgrade paquete     ^| Actualizar paquete
echo    choco list --local-only   ^| Ver paquetes instalados
echo.
echo 💡 Ahora puedes ejecutar setup-claude-ollama.bat
echo.
pause
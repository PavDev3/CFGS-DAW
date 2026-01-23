@echo off
setlocal enabledelayedexpansion

REM Script alternativo para instalar y configurar Claude Code con Ollama (Windows)
REM Usa instalación manual si Chocolatey falla

echo 🚀 Instalando y configurando Claude Code con Ollama local (Windows)...

REM Paso 1: Verificar Windows
echo 📋 Paso 1: Verificando sistema operativo...
if not "%OS%"=="Windows_NT" (
    echo ❌ Este script está diseñado para Windows
    pause
    exit /b 1
)
echo ✅ Windows detectado

REM Paso 2: Verificar/Instalar Ollama manualmente
echo 📋 Paso 2: Verificando Ollama...
where >nul 2>nul ollama
if %errorlevel% neq 0 (
    echo ⚠️  Ollama no encontrado. Descargando e instalando Ollama...
    echo 📥 Descargando Ollama para Windows...
    
    REM Crear directorio temporal
    if not exist "%TEMP%\ollama_install" mkdir "%TEMP%\ollama_install"
    
    REM Descargar Ollama usando PowerShell
    powershell -NoProfile -ExecutionPolicy Bypass -Command ^
        "Invoke-WebRequest -Uri 'https://ollama.com/download/OllamaSetup.exe' -OutFile '%TEMP%\ollama_install\OllamaSetup.exe'"
    
    if %errorlevel% neq 0 (
        echo ❌ Error al descargar Ollama
        pause
        exit /b 1
    )
    
    echo 🔄 Instalando Ollama (puede requerir administrador)...
    start "" /wait "%TEMP%\ollama_install\OllamaSetup.exe" /S
    
    REM Esperar y verificar instalación
    timeout /t 5 /nobreak >nul
    where >nul 2>nul ollama
    if %errorlevel% neq 0 (
        echo ⚠️  Ollama podría necesitar reinicio del sistema o estar en otra ruta
        echo 🔍 Buscando Ollama en ubicaciones comunes...
        
        REM Buscar en Program Files
        if exist "C:\Program Files\Ollama\ollama.exe" (
            set "PATH=%PATH%;C:\Program Files\Ollama"
            echo ✅ Ollama encontrado en C:\Program Files\Ollama
        ) else if exist "C:\Program Files (x86)\Ollama\ollama.exe" (
            set "PATH=%PATH%;C:\Program Files (x86)\Ollama"
            echo ✅ Ollama encontrado en C:\Program Files (x86)\Ollama
        ) else if exist "%LOCALAPPDATA%\Programs\Ollama\ollama.exe" (
            set "PATH=%PATH%;%LOCALAPPDATA%\Programs\Ollama"
            echo ✅ Ollama encontrado en %LOCALAPPDATA%\Programs\Ollama
        ) else (
            echo ❌ No se pudo encontrar Ollama después de la instalación
            echo 💡 Por favor, instala Ollama manualmente desde: https://ollama.com/download
            pause
            exit /b 1
        )
    )
    echo ✅ Ollama instalado
) else (
    echo ✅ Ollama ya está instalado
    for /f "tokens=*" %%i in ('ollama --version') do set OLLAMA_VERSION=%%i
    echo ✅ Versión: !OLLAMA_VERSION!
)

REM Paso 3: Iniciar Ollama
echo 📋 Paso 3: Verificando que Ollama esté corriendo...
tasklist | findstr /i "ollama.exe" >nul
if %errorlevel% neq 0 (
    echo ⚠️  Ollama no está corriendo. Iniciando Ollama...
    start "" ollama serve
    echo 🔄 Esperando a que Ollama inicie...
    timeout /t 10 /nobreak >nul
    
    tasklist | findstr /i "ollama.exe" >nul
    if %errorlevel% neq 0 (
        echo ❌ Error al iniciar Ollama
        pause
        exit /b 1
    )
    echo ✅ Ollama iniciado
) else (
    echo ✅ Ollama ya está corriendo
)

REM Paso 4: Verificar/Instalar Claude Code
echo 📋 Paso 4: Verificando Claude Code...
where >nul 2>nul claude
if %errorlevel% neq 0 (
    echo ⚠️  Claude Code no encontrado. Instalando Claude Code...
    
    REM Descargar e instalar Claude Code
    powershell -NoProfile -ExecutionPolicy Bypass -Command ^
        "Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://claude.ai/install.sh'))"
    
    if %errorlevel% neq 0 (
        echo ❌ Error al instalar Claude Code
        pause
        exit /b 1
    )
    
    REM Añadir al PATH
    set "PATH=%USERPROFILE%\.local\bin;%PATH%"
    echo ✅ Claude Code instalado
) else (
    echo ✅ Claude Code ya está instalado
    for /f "tokens=*" %%i in ('claude --version') do set CLAUDE_VERSION=%%i
    echo ✅ Versión: !CLAUDE_VERSION!
)

REM Paso 5: Configurar variables de entorno
echo 📋 Paso 5: Configurando entorno para modelo local...
set ANTHROPIC_BASE_URL=http://localhost:11434
set ANTHROPIC_AUTH_TOKEN=ollama
set CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC=1
echo ✅ Variables de entorno configuradas

REM Paso 6: Descargar modelo
echo 📋 Paso 6: Verificando modelo qwen2.5-coder:7b...
ollama list | findstr /i "qwen2.5-coder:7b" >nul
if %errorlevel% neq 0 (
    echo ⚠️  Modelo qwen2.5-coder:7b no encontrado. Descargando...
    echo 📥 Descargando modelo (esto puede tomar varios minutos)...
    ollama pull qwen2.5-coder:7b
    if %errorlevel% neq 0 (
        echo ❌ Error al descargar el modelo
        pause
        exit /b 1
    )
    echo ✅ Modelo descargado
) else (
    echo ✅ Modelo qwen2.5-coder:7b encontrado
)

REM Paso 7: Verificación final
echo 📋 Paso 7: Verificación final...
echo ✅ Ollama: $(ollama --version 2>nul || echo "Instalado")
echo ✅ Claude Code: $(claude --version 2>nul || echo "Instalado")
echo ✅ Modelo: qwen2.5-coder:7b
echo ✅ URL Ollama: %ANTHROPIC_BASE_URL%

REM Paso 8: Iniciar Claude Code
echo.
echo 🎯 ¡Todo listo! Claude Code se iniciará con el modelo qwen2.5-coder:7b
echo 📁 Navega a tu proyecto y escribe comandos como:
echo    - 'crea un programa Java que...'
echo    - 'ayuda con este error...'
echo    - 'optimiza este código...'
echo.
echo ⏹️  Para salir: Ctrl+C
echo 🔄 Para reiniciar Ollama: ollama serve
echo.
echo 🚀 Iniciando Claude Code...
echo.

claude --model qwen2.5-coder:7b

pause
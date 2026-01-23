@echo off
setlocal enabledelayedexpansion

REM Script para instalar y configurar Claude Code con modelo local Ollama (Windows)
REM Descarga Ollama y Claude Code si no están instalados

echo 🚀 Instalando y configurando Claude Code con Ollama local...

REM Función para verificar si un comando existe
where >nul 2>nul %1
if %errorlevel% equ 0 (
    set %1_exists=1
) else (
    set %1_exists=0
)

REM Paso 1: Verificar que estamos en Windows
echo 📋 Paso 1: Verificando sistema operativo...
if not "%OS%"=="Windows_NT" (
    echo ❌ Este script está diseñado para Windows
    pause
    exit /b 1
)
echo ✅ Windows detectado

REM Paso 2: Verificar e instalar Chocolatey si es necesario
echo 📋 Paso 2: Verificando Chocolatey...
where >nul 2>nul choco
if %errorlevel% neq 0 (
    echo ⚠️  Chocolatey no encontrado. Instalando Chocolatey...
    powershell -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "[System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))"
    if %errorlevel% neq 0 (
        echo ❌ Error al instalar Chocolatey
        pause
        exit /b 1
    )
    echo ✅ Chocolatey instalado
    echo 🔄 Actualizando variables de entorno...
    call refreshenv
) else (
    echo ✅ Chocolatey ya está instalado
)

REM Paso 3: Verificar e instalar Ollama
echo 📋 Paso 3: Verificando Ollama...
where >nul 2>nul ollama
if %errorlevel% neq 0 (
    echo ⚠️  Ollama no encontrado. Instalando Ollama...
    choco install ollama -y
    if %errorlevel% neq 0 (
        echo ❌ Error al instalar Ollama
        pause
        exit /b 1
    )
    echo ✅ Ollama instalado
    echo 🔄 Actualizando variables de entorno...
    call refreshenv
) else (
    echo ✅ Ollama ya está instalado
    for /f "tokens=*" %%i in ('ollama --version') do set OLLAMA_VERSION=%%i
    echo ✅ Versión: !OLLAMA_VERSION!
)

REM Paso 4: Iniciar Ollama si no está corriendo
echo 📋 Paso 4: Verificando que Ollama esté corriendo...
tasklist | findstr /i "ollama.exe" >nul
if %errorlevel% neq 0 (
    echo ⚠️  Ollama no está corriendo. Iniciando Ollama...
    start "" ollama serve
    echo 🔄 Esperando a que Ollama inicie...
    timeout /t 10 /nobreak >nul
    
    REM Verificar que Ollama esté corriendo
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

REM Paso 5: Verificar e instalar Claude Code
echo 📋 Paso 5: Verificando Claude Code...
where >nul 2>nul claude
if %errorlevel% neq 0 (
    echo ⚠️  Claude Code no encontrado. Instalando Claude Code...
    powershell -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "curl -fsSL https://claude.ai/install.sh | bash"
    if %errorlevel% neq 0 (
        echo ❌ Error al instalar Claude Code
        pause
        exit /b 1
    )
    
    REM Añadir Claude al PATH
    set PATH=%USERPROFILE%\.local\bin;%PATH%
    echo ✅ Claude Code instalado
) else (
    echo ✅ Claude Code ya está instalado
    for /f "tokens=*" %%i in ('claude --version') do set CLAUDE_VERSION=%%i
    echo ✅ Versión: !CLAUDE_VERSION!
)

REM Paso 6: Configurar variables de entorno para Ollama local
echo 📋 Paso 6: Configurando entorno para modelo local...

REM Configurar URL de Ollama local
set ANTHROPIC_BASE_URL=http://localhost:11434
echo ✅ ANTHROPIC_BASE_URL configurado a: %ANTHROPIC_BASE_URL%

REM Configurar token dummy
set ANTHROPIC_AUTH_TOKEN=ollama
echo ✅ ANTHROPIC_AUTH_TOKEN configurado a: %ANTHROPIC_AUTH_TOKEN%

REM Optimizar tráfico
set CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC=1
echo ✅ CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC configurado

REM Paso 7: Descargar modelo si no está disponible
echo 📋 Paso 7: Verificando modelo qwen2.5-coder:7b...
ollama list | findstr /i "qwen2.5-coder:7b" >nul
if %errorlevel% neq 0 (
    echo ⚠️  Modelo qwen2.5-coder:7b no encontrado. Descargando...
    echo 📥 Descargando modelo qwen2.5-coder:7b (esto puede tomar varios minutos)...
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

REM Paso 8: Verificación final
echo 📋 Paso 8: Verificación final...
for /f "tokens=*" %%i in ('ollama --version') do set OLLAMA_VER=%%i
for /f "tokens=*" %%i in ('claude --version') do set CLAUDE_VER=%%i
echo ✅ Ollama: !OLLAMA_VER!
echo ✅ Claude Code: !CLAUDE_VER!
echo ✅ Modelo: qwen2.5-coder:7b
echo ✅ URL Ollama: %ANTHROPIC_BASE_URL%

REM Paso 9: Iniciar Claude Code con el modelo local
echo.
echo 🎯 ¡Todo listo! Claude Code se iniciará con el modelo qwen2.5-coder:7b
echo 📁 Navega a tu proyecto y escribe comandos como:
echo    - 'crea un programa Java que...'
echo    - 'ayuda con este error...'
echo    - 'optimiza este código...'
echo.
echo ⏹️  Para salir: Ctrl+C
echo 🔄 Para reiniciar Ollama en el futuro: ollama serve
echo.
echo 🚀 Iniciando Claude Code...
echo.

REM Iniciar Claude Code en el directorio actual
claude --model qwen2.5-coder:7b

pause
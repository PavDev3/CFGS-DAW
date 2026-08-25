@echo off
REM ==== CONFIGURACIÓN ====
set HOSTNAME=mariadb.pablonunez.dev
set LOCAL_PORT=3307
set HEIDI_PATH="C:\Program Files\HeidiSQL\heidisql.exe"
REM =======================

echo Iniciando tunel Cloudflare hacia %HOSTNAME% en puerto local %LOCAL_PORT%...
start "cloudflared" cmd /k "cloudflared access tcp --hostname %HOSTNAME% --url 127.0.0.1:%LOCAL_PORT%"

REM Espera 5 segundos para que el tunel levante
timeout /t 5 >nul

echo Abriendo HeidiSQL...
start "" %HEIDI_PATH%

echo Listo. Configura HeidiSQL para conectar a 127.0.0.1:%LOCAL_PORT%

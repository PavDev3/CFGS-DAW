#!/bin/bash

# ==== CONFIGURACIÓN ====
HOSTNAME="mariadb.pablonunez.dev"
LOCAL_PORT="3307"
# =======================

# Verificar si cloudflared está instalado
if ! command -v cloudflared &> /dev/null; then
    echo "Instalando cloudflared..."
    if [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS
        if command -v brew &> /dev/null; then
            brew install cloudflared
        else
            echo "Por favor instala Homebrew primero: https://brew.sh"
            exit 1
        fi
    else
        echo "Sistema operativo no soportado para instalación automática"
        exit 1
    fi
fi

# Verificar si mysql client está instalado
if ! command -v mysql &> /dev/null; then
    echo "Instalando mysql client..."
    if [[ "$OSTYPE" == "darwin"* ]]; then
        if command -v brew &> /dev/null; then
            brew install mysql-client
        else
            echo "Por favor instala Homebrew primero: https://brew.sh"
            exit 1
        fi
    fi
fi

echo "Iniciando túnel Cloudflare hacia $HOSTNAME en puerto local $LOCAL_PORT..."
cloudflared access tcp --hostname $HOSTNAME --url 127.0.0.1:$LOCAL_PORT &
TUNNEL_PID=$!

# Esperar 5 segundos para que el túnel se levante
echo "Esperando 5 segundos para que el túnel se establezca..."
sleep 5

echo "Conectando a MySQL via túnel..."
echo "Usa estas credenciales cuando te las pregunten:"
echo "- Host: 127.0.0.1"
echo "- Port: $LOCAL_PORT"
echo ""

# Conectar al servidor MySQL (necesitarás usuario y contraseña)
mysql -h 127.0.0.1 -P $LOCAL_PORT -u root -p

# Limpiar: matar el proceso del túnel
echo "Cerrando túnel..."
kill $TUNNEL_PID
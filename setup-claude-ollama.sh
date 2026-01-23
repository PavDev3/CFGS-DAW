#!/bin/bash

# Script para instalar y configurar Claude Code con modelo local Ollama (Mac)
# Descarga Ollama y Claude Code si no están instalados

echo "🚀 Instalando y configurando Claude Code con Ollama local..."

# Función para verificar si un comando existe
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Función para verificar si estamos en Mac
check_mac() {
    if [[ "$OSTYPE" != "darwin"* ]]; then
        echo "❌ Este script está diseñado para macOS"
        exit 1
    fi
}

# Paso 1: Verificar que estamos en Mac
echo "📋 Paso 1: Verificando sistema operativo..."
check_mac
echo "✅ macOS detectado"

# Paso 2: Verificar e instalar Homebrew si es necesario
echo "📋 Paso 2: Verificando Homebrew..."
if ! command_exists brew; then
    echo "⚠️  Homebrew no encontrado. Instalando Homebrew..."
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
    if [[ $? -ne 0 ]]; then
        echo "❌ Error al instalar Homebrew"
        exit 1
    fi
    echo "✅ Homebrew instalado"
    
    # Añadir Homebrew al PATH para la sesión actual
    eval "$(/opt/homebrew/bin/brew shellenv)"
else
    echo "✅ Homebrew ya está instalado: $(brew --version | head -1)"
fi

# Paso 3: Verificar e instalar Ollama
echo "📋 Paso 3: Verificando Ollama..."
if ! command_exists ollama; then
    echo "⚠️  Ollama no encontrado. Instalando Ollama..."
    brew install ollama
    if [[ $? -ne 0 ]]; then
        echo "❌ Error al instalar Ollama"
        exit 1
    fi
    echo "✅ Ollama instalado"
else
    echo "✅ Ollama ya está instalado: $(ollama --version)"
fi

# Paso 4: Iniciar Ollama si no está corriendo
echo "📋 Paso 4: Verificando que Ollama esté corriendo..."
if ! pgrep -f "ollama" > /dev/null; then
    echo "⚠️  Ollama no está corriendo. Iniciando Ollama..."
    # Iniciar Ollama en segundo plano
    nohup ollama serve > /dev/null 2>&1 &
    OLLAMA_PID=$!
    echo "🔄 Esperando a que Ollama inicie..."
    sleep 5
    
    # Verificar que Ollama esté corriendo
    if ! pgrep -f "ollama" > /dev/null; then
        echo "❌ Error al iniciar Ollama"
        exit 1
    fi
    echo "✅ Ollama iniciado (PID: $OLLAMA_PID)"
else
    echo "✅ Ollama ya está corriendo"
fi

# Paso 5: Verificar e instalar Claude Code
echo "📋 Paso 5: Verificando Claude Code..."
if ! command_exists claude; then
    echo "⚠️  Claude Code no encontrado. Instalando Claude Code..."
    curl -fsSL https://claude.ai/install.sh | bash
    if [[ $? -ne 0 ]]; then
        echo "❌ Error al instalar Claude Code"
        exit 1
    fi
    
    # Añadir Claude al PATH para la sesión actual
    export PATH="$HOME/.local/bin:$PATH"
    echo "✅ Claude Code instalado"
else
    echo "✅ Claude Code ya está instalado: $(claude --version)"
fi

# Paso 6: Configurar variables de entorno para Ollama local
echo "📋 Paso 6: Configurando entorno para modelo local..."

# Configurar URL de Ollama local
export ANTHROPIC_BASE_URL="http://localhost:11434"
echo "✅ ANTHROPIC_BASE_URL configurado a: $ANTHROPIC_BASE_URL"

# Configurar token dummy
export ANTHROPIC_AUTH_TOKEN="ollama"
echo "✅ ANTHROPIC_AUTH_TOKEN configurado a: $ANTHROPIC_AUTH_TOKEN"

# Optimizar tráfico
export CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC=1
echo "✅ CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC configurado"

# Paso 7: Descargar modelo si no está disponible
echo "📋 Paso 7: Verificando modelo qwen2.5-coder:7b..."
if ollama list | grep -q "qwen2.5-coder:7b"; then
    echo "✅ Modelo qwen2.5-coder:7b encontrado"
else
    echo "⚠️  Modelo qwen2.5-coder:7b no encontrado. Descargando..."
    echo "📥 Descargando modelo qwen2.5-coder:7b (esto puede tomar varios minutos)..."
    ollama pull qwen2.5-coder:7b
    if [[ $? -ne 0 ]]; then
        echo "❌ Error al descargar el modelo"
        exit 1
    fi
    echo "✅ Modelo descargado"
fi

# Paso 8: Verificar configuración final
echo "📋 Paso 8: Verificación final..."
echo "✅ Ollama: $(ollama --version)"
echo "✅ Claude Code: $(claude --version)"
echo "✅ Modelo: qwen2.5-coder:7b"
echo "✅ URL Ollama: $ANTHROPIC_BASE_URL"

# Paso 9: Iniciar Claude Code con el modelo local
echo ""
echo "🎯 ¡Todo listo! Claude Code se iniciará con el modelo qwen2.5-coder:7b"
echo "📁 Navega a tu proyecto y escribe comandos como:"
echo "   - 'crea un programa Java que...'"
echo "   - 'ayuda con este error...'"
echo "   - 'optimiza este código...'"
echo ""
echo "⏹️  Para salir: Ctrl+C o escribe 'exit'"
echo "🔄 Para reiniciar Ollama en el futuro: ollama serve"
echo ""
echo "🚀 Iniciando Claude Code..."
echo ""

# Iniciar Claude Code en el directorio actual
claude --model qwen2.5-coder:7b
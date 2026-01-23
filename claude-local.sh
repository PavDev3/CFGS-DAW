#!/bin/bash

# Script para configurar Claude Code con modelo local Ollama (Mac)
# SIN instalar Claude Code (ya instalado)

echo "🚀 Configurando Claude Code con Ollama local..."

# Paso 1: Verificar que Ollama está corriendo
echo "📋 Paso 1: Verificando Ollama..."
if ! pgrep -f "ollama" > /dev/null; then
    echo "❌ Ollama no está corriendo. Por favor, inicia Ollama primero:"
    echo "   Abre una terminal y ejecuta: ollama serve"
    exit 1
fi
echo "✅ Ollama está corriendo"

# Paso 2: Verificar que Claude Code está instalado
echo "📋 Paso 2: Verificando Claude Code..."
if ! command -v claude &> /dev/null; then
    echo "❌ Claude Code no está instalado. Por favor, instálalo primero:"
    echo "   curl -fsSL https://claude.ai/install.sh | bash"
    exit 1
fi
echo "✅ Claude Code está instalado: $(claude --version)"

# Paso 3: Configurar variables de entorno para Ollama local
echo "📋 Paso 3: Configurando entorno para modelo local..."

# Configurar URL de Ollama local
export ANTHROPIC_BASE_URL="http://localhost:11434"
echo "✅ ANTHROPIC_BASE_URL configurado a: $ANTHROPIC_BASE_URL"

# Configurar token dummy
export ANTHROPIC_AUTH_TOKEN="ollama"
echo "✅ ANTHROPIC_AUTH_TOKEN configurado a: $ANTHROPIC_AUTH_TOKEN"

# Optimizar tráfico
export CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC=1
echo "✅ CLAUDE_CODE_DISABLE_NONESSENTIAL_TRAFFIC configurado"

# Paso 4: Verificar que el modelo esté disponible
echo "📋 Paso 4: Verificando modelo qwen2.5-coder:7b..."
if ollama list | grep -q "qwen2.5-coder:7b"; then
    echo "✅ Modelo qwen2.5-coder:7b encontrado"
else
    echo "⚠️  Modelo qwen2.5-coder:7b no encontrado. ¿Deseas descargarlo? (s/n)"
    read -r response
    if [[ "$response" =~ ^[Ss]$ ]]; then
        echo "📥 Descargando modelo qwen2.5-coder:7b..."
        ollama pull qwen2.5-coder:7b
        echo "✅ Modelo descargado"
    else
        echo "❌ Se requiere el modelo para continuar. Abortando."
        exit 1
    fi
fi

# Paso 5: Iniciar Claude Code con el modelo local
echo "📋 Paso 5: Iniciando Claude Code con modelo local..."
echo ""
echo "🎯 Claude Code se iniciará con el modelo qwen2.5-coder:7b"
echo "📁 Navega a tu proyecto y escribe comandos como:"
echo "   - 'crea un programa Java que...'"
echo "   - 'ayuda con este error...'"
echo "   - 'optimiza este código...'"
echo ""
echo "⏹️  Para salir: Ctrl+C o escribe 'exit'"
echo ""
echo "🚀 Iniciando Claude Code..."
echo ""

# Iniciar Claude Code en el directorio actual
claude --model qwen2.5-coder:7b
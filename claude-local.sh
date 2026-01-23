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

# Paso 4: Seleccionar modelo
echo "📋 Paso 4: Seleccionando modelo..."
echo "Modelos disponibles:"
echo "1) qwen2.5-coder:7b (4.7GB - ligero y rápido)"
echo "2) qwen3-coder:latest (18GB - más potente)"
echo "3) Otro modelo (especificar nombre)"

echo -n "Elige una opción [1-3]: "
read -r choice

case $choice in
    1)
        MODEL="qwen2.5-coder:7b"
        ;;
    2)
        MODEL="qwen3-coder:latest"
        ;;
    3)
        echo -n "Ingresa el nombre del modelo: "
        read -r MODEL
        ;;
    *)
        echo "Opción no válida, usando qwen2.5-coder:7b por defecto"
        MODEL="qwen2.5-coder:7b"
        ;;
esac

# Paso 5: Verificar que el modelo esté disponible
echo "📋 Paso 5: Verificando modelo $MODEL..."
if ollama list | grep -q "$MODEL"; then
    echo "✅ Modelo $MODEL encontrado"
else
    echo "⚠️  Modelo $MODEL no encontrado. ¿Deseas descargarlo? (s/n)"
    read -r response
    if [[ "$response" =~ ^[Ss]$ ]]; then
        echo "📥 Descargando modelo $MODEL..."
        ollama pull "$MODEL"
        echo "✅ Modelo descargado"
    else
        echo "❌ Se requiere el modelo para continuar. Abortando."
        exit 1
    fi
fi

# Paso 6: Iniciar Claude Code con el modelo seleccionado
echo "📋 Paso 6: Iniciando Claude Code con modelo local..."
echo ""
echo "🎯 Claude Code se iniciará con el modelo $MODEL"
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
claude --model "$MODEL"
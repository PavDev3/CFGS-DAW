#!/usr/bin/env bash

if [[ "$EUID" -eq 0 ]]; then
    echo "Por favor no ejecutes como root"
    exit 1
fi

echo "Todo correcto, no eres root."

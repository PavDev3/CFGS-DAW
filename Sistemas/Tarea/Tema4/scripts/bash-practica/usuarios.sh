#!/usr/bin/env bash

read -p "Introduce tu nombre de usuario: " usuario

if [[ "$usuario" == "usuario_admin" ]]; then
    echo "¡Eres el usuario administrador!"
elif [[ "$usuario" != "usuario_admin" && "$EUID" -ne 0 ]]; then
    echo "No eres admin ni root, ¡ten cuidado!"
else
    echo "Eres admin o root."
fi

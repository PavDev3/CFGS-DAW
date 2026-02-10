#!/usr/bin/env bash

#Ejercicio introducir numero

read -p "Introduce un numero: " numero

if [ "$numero" -lt 0 ]; then
    echo "El numero es negativo"
elif [ "$numero" -gt 0 ]; then
    echo "El numero es positivo"
else
    echo "El numero es 0"
fi


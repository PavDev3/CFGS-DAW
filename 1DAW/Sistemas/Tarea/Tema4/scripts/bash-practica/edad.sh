#!/usr/bin/env bash

# Ejercicio - Clasificador de edad con if/elif/else

read -p "Introduce tu edad: " edad

if [ "$edad" -lt 0 ]; then
    echo "Edad no válida"
elif [ "$edad" -lt 18 ]; then
    echo "Menor de edad"
elif [ "$edad" -lt 65 ]; then
    echo "Adulto"
else
    echo "Mayor (65+)"
fi

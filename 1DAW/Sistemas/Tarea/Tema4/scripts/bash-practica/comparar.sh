#!/usr/bin/env bash

# Ejercicio 9 - Comparador de dos números

read -p "Introduce el primer número (A): " a
read -p "Introduce el segundo número (B): " b

if [ "$a" -gt "$b" ]; then
    echo "A es mayor que B"
elif [ "$a" -lt "$b" ]; then
    echo "A es menor que B"
else
    echo "A es igual a B"
fi

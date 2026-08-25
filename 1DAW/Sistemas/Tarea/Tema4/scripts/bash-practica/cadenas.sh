#!/usr/bin/env bash

# Ejercicio - Slicing de cadenas

texto="¡Hola, Mundo!"

# "Mundo" -> 5 caracteres a partir del índice 7
echo "Mundo: ${texto:7:5}"

# Los dos primeros caracteres
echo "Dos primeros: ${texto:0:2}"

# Desde el índice 7 hasta el final
echo "Desde índice 7: ${texto:7}"

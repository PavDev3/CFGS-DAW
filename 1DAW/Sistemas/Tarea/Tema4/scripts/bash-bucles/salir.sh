#!/bin/bash

#Until para salir del bucle cuando se introduzca la palabra "salir"
while true; do
    read -p "Introduce una palabra: " palabra
    if [ "$palabra" == "salir" ]; then
        break
    fi
    echo "La palabra es: $palabra"
done
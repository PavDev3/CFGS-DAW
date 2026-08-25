#!/bin/bash

#Bucle numeros pares del 2 al 20 
for i in {2..20}; do
    if [ $((i % 2)) -eq 0 ]; then
        echo "Numero par: $i"
    fi
done
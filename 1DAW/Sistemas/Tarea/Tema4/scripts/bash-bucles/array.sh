#!/bin/bash

#Array 4 frutas
frutas=("Manzana" "Banana" "Naranja" "Uva")

#Bucle for para mostrar cada fruta
for fruta in "${frutas[@]}"; do
    echo "Fruta: $fruta"
done

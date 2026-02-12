#!/bin/bash

#Variable 3 nombres y bucle for para mostrar cada nombre
nombres=("Juan" "Pedro" "Maria")
for nombre in "${nombres[@]}"; do
    echo "Nombre: $nombre"
done
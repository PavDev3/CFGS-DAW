#!/bin/bash

#Pedir nombre y mostrarlo, si es vacio volver a pedir
nombre=""
echo "Introduce tu nombre: "
read nombre
while [ "$nombre" == "" ]; do
    echo "Nombre no valido"
    echo "Introduce tu nombre: "
    read nombre
done
echo "Hola, $nombre"
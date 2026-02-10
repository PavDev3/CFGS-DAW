#!/usr/bin/env bash

#ejercicio 1 hara un echo para imprimir hola mundo!
#echo "Hola, mundo"

#ejercicio 2 crear una variable llamado nombre y otra saludo
#nombre="MiApp"
#saludo="Hola,"
#echo "$saludo $nombre"

#ejercicio 4 Muestre el primer y segundo argumento ( $1 y $2 ).
#Modifica mi_script.sh para que:
# si no se pasa el primer argumento, pregunte al usuario su nombre con read -p y luego salude.
# si sí hay primer argumento, lo use como nombre y salude sin pedir entrada.
nombre=""
if [ -z "$1" ]; then
    read -p "Introduce tu nombre: " nombre
    saludo="Hola, $nombre"
else
    saludo="Hola, $1"
fi
echo $saludo



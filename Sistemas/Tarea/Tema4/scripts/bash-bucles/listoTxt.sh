#!/bin/bash

#Escribe un script que use un bucle until para que el script se until para imprimir el contador mientras until y una expresión de archivo para esperar hasta que un archivo llamado listo.txt exista en el directorio actual. (Puedes probarlo creando el archivo en otra terminal).
i=0
until [ -f listo.txt ]; do
    i=$((i+1))
    echo "Intento $i: Esperando a que exista el archivo listo.txt"
    sleep 1
done
echo "El archivo listo.txt existe"
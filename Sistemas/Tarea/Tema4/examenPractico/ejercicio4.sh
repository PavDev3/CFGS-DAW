#!bin/bash

echo "Cual es tu nombre? "
read -r nombre

if [ -z "$nombre" ]; then
  echo "Por favor, ingresa tu nombre!"
else
  echo "Hola $nombre"
fi


#!/usr/bin/env bash

# Ejercicio 5 - Arrays basicos

mi_array=("valor 1" "valor 2" "valor 3" "valor 4")

# Segundo elemento (indice 1)
echo "Segundo elemento: ${mi_array[1]}"

# Ultimo elemento
echo "Ultimo elemento: ${mi_array[-1]}"

# Todos los elementos
echo "Todos los elementos: ${mi_array[@]}"

# Numero total de elementos
echo "Numero total de elementos: ${#mi_array[@]}"

# Imprimir elemento del indice 1 tres posiciones
echo "Elemento del indice 1 tres posiciones: ${mi_array[1+3]}"

# los elementos desde el indice 2 hasta el final
echo "Elementos desde el indice 2 hasta el final: ${mi_array[@]:2}"

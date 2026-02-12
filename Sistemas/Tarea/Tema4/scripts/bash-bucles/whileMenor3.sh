#!/bin/bash

#Bucle while que imprima el valor de un contador mientras este sea menor que 3, e incrementa el contador en 1 en cada iteración.
i=0
while [ $i -lt 3 ]; do
    echo "$i"
    i=$((i+1))
done
echo "Cuenta: " $i
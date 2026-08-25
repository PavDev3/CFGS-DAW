#!/bin/bash

#Bucle while para contar del 1 al 10
i=1
while [ $i -lt 10 ]; do
    i=$((i+1))
done
echo "Cuenta: " $i
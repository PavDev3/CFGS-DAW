#!/bin/bash

#Bucle while para contar del 10 al 1
i=5
while [ $i -ge 1 ]; do
    echo "$i"
    i=$((i-1))
done
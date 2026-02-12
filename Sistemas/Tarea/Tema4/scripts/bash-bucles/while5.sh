#!/bin/bash

#Bucle while para contar del 1 al 10
i=1
while [ $i -le 5 ]; do
    echo "$i"
    i=$((i+1))
done
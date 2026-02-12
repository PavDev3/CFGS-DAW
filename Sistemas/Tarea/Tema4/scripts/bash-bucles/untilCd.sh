#!/bin/bash

#Bucle until para contar del 5 al 1
i=5
until [ $i -lt 1 ]; do
    echo "$i"
    i=$((i-1))
done
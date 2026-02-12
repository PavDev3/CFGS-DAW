#!/bin/bash

#Bucle until para contar del 1 al 5
i=1
until [ $i -gt 5 ]; do
    echo "$i"
    i=$((i+1))
done

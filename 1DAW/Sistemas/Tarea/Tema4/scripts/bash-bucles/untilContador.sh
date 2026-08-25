#!/bin/bash

#Until cuenta atras hasta el 10
i=10
until [ $i -lt 0 ]; do
    echo "$i"
    i=$((i-1))
done
echo "BOOOM!"
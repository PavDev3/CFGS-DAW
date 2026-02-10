#!/usr/bin/env bash

# Ejercicio - Comprobación de archivos

read -p "Introduce una ruta: " ruta

# Comprobar si existe
if [ ! -e "$ruta" ]; then
    echo "\"$ruta\" no existe."
    exit 1
fi

echo "\"$ruta\" existe."

# Tipo: enlace simbólico, directorio o archivo regular
if [ -L "$ruta" ]; then
    echo "Es un enlace simbólico."
elif [ -d "$ruta" ]; then
    echo "Es un directorio."
elif [ -f "$ruta" ]; then
    echo "Es un archivo regular."
else
    echo "Es otro tipo de archivo."
fi

# Permisos
[ -r "$ruta" ] && echo "Es legible (r)." || echo "No es legible."
[ -w "$ruta" ] && echo "Es escribible (w)." || echo "No es escribible."
[ -x "$ruta" ] && echo "Es ejecutable (x)." || echo "No es ejecutable."

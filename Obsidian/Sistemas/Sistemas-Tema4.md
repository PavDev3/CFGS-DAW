# Tema 4 — Linux: Comandos y Bash Scripting

← [[Sistemas-Tema3]] | [[Sistemas]] | Siguiente: [[Sistemas-Tema5]]

---

## Comandos Linux Básicos

### Gestión de archivos y directorios

| Comando | Descripción | Ejemplo |
|---------|-------------|---------|
| `ls` | Listar archivos | `ls -la /bin` |
| `cd` | Cambiar directorio | `cd /etc` |
| `pwd` | Directorio actual | `pwd` |
| `mkdir` | Crear directorio | `mkdir -p dir1/dir2` |
| `rm` | Borrar | `rm -rf directorio/` |
| `cp` | Copiar | `cp archivo.txt destino/` |
| `mv` | Mover/renombrar | `mv origen destino` |
| `find` | Buscar archivos | `find . -type f -name "*.txt"` |
| `touch` | Crear fichero vacío | `touch archivo.txt` |
| `ln -s` | Crear enlace simbólico | `ln -s ../dir1 enlacedir1` |

### Visualización de archivos

| Comando | Descripción |
|---------|-------------|
| `cat` | Mostrar contenido |
| `less` | Paginar contenido |
| `head -n` | Primeras N líneas |
| `tail -n` | Últimas N líneas |
| `grep` | Buscar texto en archivos |

### Permisos

```bash
# Notación simbólica
chmod a-w directorio     # Quitar escritura a todos
chmod u+x script.sh      # Dar ejecución al propietario

# Notación octal
chmod 755 script.sh      # rwxr-xr-x
chmod 644 archivo.txt    # rw-r--r--
```

---

## Ejercicios de Comandos Linux

```bash
# 1. Listar archivos del directorio bin
ls /bin

# 2. Archivos de /etc que empiecen por 't'
ls /etc/t*

# 3. Archivos de /dev que empiecen por 'tty' y tengan 5 caracteres
ls /dev/tty??

# 4. Archivos de /dev: empiezan por 'tty' y acaban en 1,2,3 ó 4
ls /dev/tty[1-4]

# 5. Archivos de /dev: empiezan por 't' y acaban en 'S1'
ls /dev/t*S1

# 6. Listar todos los ficheros incluidos ocultos del directorio raíz
ls -la /

# 7. Mostrar día y hora actual
date

# 9. Crear estructura de directorios
mkdir -p PRUEBA/dir1/dir11 PRUEBA/dir2 PRUEBA/dir3/dir31/dir311 PRUEBA/dir3/dir31/dir312

# 10. Verificar estructura
find PRUEBA -type d | sort

# 11. Copiar archivos con patrón
cp /bin/?a?? PRUEBA/dir3/dir31/dir311/

# 13. Mover directorio
mv PRUEBA/dir3/dir31 PRUEBA/dir2/

# 14. Borrar directorio y su contenido
rm -rf PRUEBA/dir1

# 18. Crear enlace simbólico
ln -s ../dir1 PRUEBA/dir3/enlacedir1

# 22. Permisos del directorio dir2
mkdir -p PRUEBA/dir2 PRUEBA/dir3 && ls -ld PRUEBA/dir2

# 23. Quitar todos los permisos de escritura (notación simbólica)
chmod a-w PRUEBA/dir2

# 24. Quitar lectura a otros (notación octal)
chmod o-r PRUEBA/dir2

# 28. Contar usuarios del sistema
cut -d: -f1 /etc/passwd | wc -l
```

---

## Bash Scripting

### Estructura básica de un script

```bash
#!/bin/bash

# Variables
nombre="Juan"
saludo="Hola"

# Salida
echo "$saludo $nombre"
```

### Scripts del examen práctico

#### ejercicio1.sh — Variables y echo

```bash
#!/bin/bash
nombre="Juan"
saludo="Hola"
echo "$saludo $nombre"
```

#### ejercicio2.sh — Lectura de entrada

```bash
#!/bin/bash
echo "Enter your name: "
read -r name
echo "Hello, $name!"
```

### Scripts de bucles

| Script | Descripción |
|--------|-------------|
| `for.sh` | Bucle for básico |
| `while5.sh` | Bucle while con contador |
| `pares.sh` | Mostrar números pares |
| `tabla3.sh` | Tabla de multiplicar del 3 |
| `array.sh` | Manejo de arrays en bash |
| `nombres.sh` | Recorrer lista de nombres |

Ubicación: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema4/scripts/bash-bucles/`

### Scripts de práctica avanzada

| Script | Descripción |
|--------|-------------|
| `archivos.sh` | Operaciones con archivos |
| `argumentos.sh` | Manejo de argumentos |
| `arrays.sh` | Arrays avanzados |
| `cadenas.sh` | Manipulación de cadenas |
| `comparar.sh` | Comparaciones |
| `edad.sh` | Verificar edad |
| `usuarios.sh` | Gestión de usuarios |

Ubicación: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema4/scripts/bash-practica/`

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema4/ejercicios_linux.md`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema4/Ejercicios Comandos Linux.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tarea/Tema4/Ejercicios combinados.pdf`

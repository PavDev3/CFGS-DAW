# Tanda de ejercicios de Scripting (PowerShell) — DAW (Sistemas Informáticos)

## Requisitos generales

* **Intérprete: ****PowerShell 5.1+** (Windows) o **PowerShell 7+** (Windows/Linux/macOS).
* **Para ejecutar scripts localmente, quizá necesites ajustar la política de ejecución (según el entorno):**
  * **Ejemplo (solo sesión actual): **`Set-ExecutionPolicy -Scope Process Bypass`
* **Todos los scripts deben:**
  * **Declarar parámetros con **`param(...)` cuando aplique
  * **Validar entradas y mostrar errores claros**
  * **Finalizar con código distinto de 0 en caso de error (**`exit 1`, `exit 2`, etc.)

## Ejercicios (1–7 fáciles, 8–10 nivel intermedio)

### 1) Hola, DAW (variables y salida)

**Crea **`01_hola.ps1` que:

* **Guarde en una variable el texto **`Hola, DAW`.
* **Imprima el texto y, en la línea siguiente, la fecha/hora actual en formato **`yyyy-MM-dd HH:mm:ss`.

### 2) Saludo personalizado (lectura por teclado)

**Crea **`02_saludo.ps1` que:

* **Pida el nombre por teclado.**
* **Si el usuario pulsa Enter sin escribir nada, use **`desconocido`.
* **Imprima: **`Hola, <nombre>!`.

### 3) Mini-calculadora (aritmética + validación)

**Crea **`03_calculadora.ps1` que:

* **Pida dos números enteros **`a` y `b`.
* **Imprima suma, resta, multiplicación y (si **`b != 0`) división entera.
* **Si **`b == 0`, muestre un aviso y no haga la división.

### 4) Analizador de rutas (ficheros, enlaces, ACL)

**Crea **`04_ruta_info.ps1` que reciba **una ruta** como parámetro:

* **Si no se pasa, muestre uso: **`._ruta_info.ps1 -Path <ruta>`.
* **Informe si la ruta ** **no existe** **, o si es ** **fichero** **, ****directorio** o **enlace** (symlink/junction).
* **Si existe, muestre:**
  * **Tamaño (bytes) si es fichero**
  * **Si es directorio: número de elementos directos (no recursivo)**
  * **Propietario (Owner) y un resumen de permisos (ACL) en texto**

### 5) Tabla de multiplicar (bucles)

**Crea **`05_tabla.ps1` que:

* **Pida un número **`n`.
* **Imprima la tabla de multiplicar de **`n` del 1 al 10 con formato: `n x i = resultado`.

### 6) Contador por extensión (recorrer directorios)

**Crea **`06_contar_ext.ps1` que reciba:

* `-Dir` (directorio)
* `-Extension` (por ejemplo `txt` o `.txt`)

**Debe:**

* **Validar que el directorio existe.**
* **Contar cuántos ficheros ****regulares** en ese directorio (solo nivel 1) tienen esa extensión.
* **Mostrar: **`Archivos .txt en C:\ruta: <n>`

### 7) Estadísticas de un fichero numérico (lectura + cálculos)

**Crea **`07_stats.ps1` que reciba `-File` con un fichero de texto con  **un número por línea** **.**Debe:

* **Ignorar líneas vacías o con espacios.**
* **Calcular: cantidad de números, mínimo, máximo y media (con 2 decimales).**
* **Si el fichero no existe o no hay números válidos, avisar.**

### 8) (Intermedio) Analizador de access.log (regex, hashtables, sorting)

**Crea **`08_log_top.ps1` que reciba `-Log` con la ruta a un `access.log` en formato “Common/Combined”.**Debe mostrar:**

* **Top 5 IPs por número de peticiones.**
* **Top 5 recursos solicitados (la URL del request).**
* **Total de peticiones y un resumen por código HTTP (200, 404, etc.) si aparece.**

### 9) (Intermedio) Copia de seguridad con rotación (Compress-Archive + timestamps)

**Crea **`09_backup_rotate.ps1` que reciba:

* `-Source` (directorio a respaldar)
* `-Destination` (directorio donde guardar backups)
* `-Keep` (número de copias a conservar)

**Debe:**

* **Crear un ****.zip** del origen con timestamp (YYYYmmdd_HHMMSS) en el nombre.
* **Guardarlo en destino.**
* **Mantener solo las ****Keep** copias más recientes (borrar las antiguas).

### 10) (Intermedio) Gestor de tareas CLI (switch, funciones, persistencia)

**Crea **`10_todo.ps1` con subcomandos:

* `add "texto"`: añade una tarea pendiente
* `list`: lista tareas con ID, estado y texto
* `done <id>`: marca como completada
* `rm <id>`: elimina
* `help`: muestra ayuda

**Persistencia:**

* **Guarda las tareas en un fichero (por ejemplo **`$HOME\.todo_daw_ps.db`).
* **Formato recomendado: **`id|estado|texto` (estado: 0 pendiente, 1 hecha).

**Requisitos:**

* **IDs consecutivos.**
* **Manejo de errores: ID inexistente, comandos mal usados, etc.**

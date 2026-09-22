---
theme: default
title: Cómo funciona el proceso de la memoria
info: |
  Resumen de arquitectura de computadores y sistemas operativos.
  Basado en resumen_introduccion.pdf
class: text-center
transition: slide-left
mdc: true
---

# Cómo funciona el proceso de la memoria

Jerarquía, caché, memoria virtual y gestión por el sistema operativo

<div class="pt-12 text-sm opacity-60">
Basado en resumen_introduccion.pdf — arquitectura de computadores y sistemas operativos
</div>

---
transition: fade
---

# 1. Jerarquía de memoria

<v-click>

La memoria rápida es cara y de poco tamaño, así que el ordenador organiza la memoria en **niveles**, de más rápido/caro/pequeño a más lento/barato/grande.

</v-click>

<v-clicks>

- Registros
- Caché
- Memoria principal (RAM)
- Disco SSD
- Disco magnético

</v-clicks>

---

# Jerarquía de memoria — en cifras

<table class="w-full text-sm">
<thead>
<tr><th>Nivel</th><th>Capacidad típica</th><th>Tiempo de acceso</th></tr>
</thead>
<tbody>
<tr v-click><td>Registros</td><td>64 a 1024 bytes</td><td>0,25–0,5 ns</td></tr>
<tr v-click><td>Caché</td><td>8 KiB a 8 MiB</td><td>0,5–20 ns</td></tr>
<tr v-click><td>Memoria principal (RAM)</td><td>128 MiB a 64 GiB</td><td>60–200 ns</td></tr>
<tr v-click><td>Disco SSD</td><td>128 GiB a 1 TiB</td><td>~50 µs</td></tr>
<tr v-click><td>Disco magnético</td><td>256 GiB a 4 TiB</td><td>5–30 ms</td></tr>
</tbody>
</table>

<v-click>

<div class="pt-8">
La información se va <b>moviendo</b> entre niveles según se necesita — de los lentos a los rápidos cuando se usa, y de vuelta a los lentos para guardarla de forma permanente.
</div>

</v-click>

---
layout: two-cols
---

# Movimiento entre niveles

<v-click>

### Explícito

El programa lo pide directamente

*(ej: abrir un fichero)*

</v-click>

::right::

<div class="pt-10" />

<v-click>

### Automático

Lo hace el hardware/SO sin que el programa se entere

*(caché y memoria virtual)*

</v-click>

---
transition: fade
---

# 2. Memoria caché

<v-clicks>

- Memoria pequeña y muy rápida entre el procesador y la memoria principal
- Guarda la información usada recientemente, apostando a que se vuelva a necesitar pronto
- Así se evita ir a la RAM, mucho más lenta
- El bloque que se mueve entre RAM y caché se llama **línea** (normalmente 32–128 bytes)
- Si la caché no tiene el dato pedido: **fallo de caché** → hay que ir a buscarlo a memoria principal

</v-clicks>

---

# 3. Memoria virtual vs memoria real

<div class="grid grid-cols-2 gap-8 pt-4">

<v-click>
<div class="border rounded-lg p-4">

### Memoria real

El ordenador solo usa la RAM como memoria direccionable.

</div>
</v-click>

<v-click>
<div class="border rounded-lg p-4">

### Memoria virtual

Además de la RAM usa una parte del disco como memoria de respaldo (**swap**), ampliando el espacio disponible de forma transparente para los programas.

</div>
</v-click>

</div>

---

# Memoria virtual — piezas del puzle

<v-clicks>

- **Páginas virtuales**: bloques del espacio de direcciones que ve el programa
- **Marcos de página**: los huecos correspondientes en la RAM física
- **Páginas de intercambio**: los bloques que están en el disco (swap) en vez de en RAM
- **Tabla de páginas**: guarda en qué marco de RAM (o en qué página de swap) está cada página virtual

</v-clicks>

---
layout: center
class: text-center
---

# MMU y TLB

<v-click>

La **MMU** (*Memory Management Unit*) traduce direcciones virtuales a físicas

</v-click>

<v-click>

Si la página pedida no está en RAM → **fallo de página**, y el SO la trae del disco

</v-click>

<v-click>

La **TLB** (*Translation Look-aside Buffer*) es una caché de traducciones página→marco, para no consultar la tabla de páginas en cada acceso

</v-click>

---

# 4. Gestión de memoria por el SO

El **gestor de memoria** del sistema operativo se encarga de:

<v-clicks>

- Asignar memoria a los procesos para crear su imagen de memoria
- Dar memoria cuando un proceso la pide, y liberarla cuando ya no la necesita
- Evitar que un proceso acceda o interfiera en la memoria de otro (**protección**)
- Permitir que varios procesos compartan memoria entre sí (para comunicarse)
- Gestionar la jerarquía de memoria y resolver los fallos de página

</v-clicks>

---

# Sus tres servicios principales

<div class="grid grid-cols-3 gap-4 pt-8">

<v-click>
<div class="border rounded-lg p-4 h-full">

### 1. Solicitar memoria

El proceso pide más espacio; el SO lo concede si hay recursos y devuelve un puntero a la nueva zona.

</div>
</v-click>

<v-click>
<div class="border rounded-lg p-4 h-full">

### 2. Liberar memoria

El proceso devuelve espacio que ya no usa, y el SO lo recicla para futuras peticiones.

</div>
</v-click>

<v-click>
<div class="border rounded-lg p-4 h-full">

### 3. Compartir memoria

Crear/liberar regiones compartidas entre procesos, usadas como mecanismo de comunicación.

</div>
</v-click>

</div>

---

# 5. Relación con los procesos

<v-clicks>

- Cada proceso tiene su propia **imagen de memoria** (código + datos) dentro del mapa de memoria del sistema
- El SO guarda información sobre cada proceso en su **BCP** (Bloque de Control del Proceso)
- A diferencia del ejecutable (permanente en disco), la imagen de memoria de un proceso es **temporal**
- Existe mientras el proceso se ejecuta y desaparece cuando termina

</v-clicks>

---
layout: center
class: text-center
---

# Resumen

<v-clicks>

- La memoria se organiza en **niveles** (registros → caché → RAM → disco)
- La **caché** acelera accesos repetidos; la **memoria virtual** amplía la RAM con disco
- La **MMU** y la **TLB** traducen y aceleran el acceso a memoria
- El **SO** asigna, libera, protege y comparte memoria entre procesos

</v-clicks>

# Resumen: cómo funciona el proceso de la memoria

Basado en `resumen_introduccion.pdf` (secciones de arquitectura de computadores y sistemas operativos).

## 1. Jerarquía de memoria

La memoria rápida es cara y de poco tamaño, así que el ordenador organiza la memoria en niveles, de más rápido/caro/pequeño a más lento/barato/grande:

| Nivel | Capacidad típica | Tiempo de acceso |
|---|---|---|
| Registros | 64 a 1024 bytes | 0,25–0,5 ns |
| Caché | 8 KiB a 8 MiB | 0,5–20 ns |
| Memoria principal (RAM) | 128 MiB a 64 GiB | 60–200 ns |
| Disco SSD | 128 GiB a 1 TiB | ~50 µs |
| Disco magnético | 256 GiB a 4 TiB | 5–30 ms |

La idea clave: la información se va "moviendo" entre niveles según se necesita — de los lentos a los rápidos cuando se usa, y de vuelta a los lentos cuando hay que guardarla de forma permanente. Ese movimiento puede ser explícito (el programa lo pide, como al abrir un fichero) o automático (lo hace el propio hardware/SO sin que el programa se entere) — este segundo caso es el de la caché y la memoria virtual.

## 2. Memoria caché

Es una memoria pequeña y muy rápida que se coloca entre el procesador y la memoria principal. Guarda la información usada recientemente, apostando a que se vuelva a necesitar pronto (así se evita ir a la RAM, mucho más lenta). El bloque que se mueve entre RAM y caché se llama **línea** (normalmente 32–128 bytes). Cuando la caché no tiene el dato pedido se produce un **fallo de caché**, y hay que ir a buscarlo a memoria principal, mucho más lento.

## 3. Memoria virtual vs memoria real

- **Memoria real**: el ordenador solo usa la RAM como memoria direccionable.
- **Memoria virtual**: además de la RAM usa una parte del disco como "memoria de respaldo" (zona de *swap*), ampliando el espacio disponible de forma transparente para los programas.

Para gestionar esto, tanto la RAM como el espacio virtual se dividen en bloques del mismo tamaño:
- **Páginas virtuales**: bloques del espacio de direcciones que ve el programa.
- **Marcos de página**: los huecos correspondientes en la RAM física.
- **Páginas de intercambio**: los bloques que están en el disco (swap) en vez de en RAM.

Una **tabla de páginas** guarda en qué marco de RAM (o en qué página de swap) está cada página virtual. El hardware encargado de traducir direcciones virtuales a físicas es la **MMU** (*Memory Management Unit*). Si el programa pide una dirección cuya página no está en RAM, la MMU genera un **fallo de página**, y el sistema operativo se encarga de traerla del disco. Para no tener que consultar la tabla de páginas en cada acceso (sería muy lento), la MMU usa una caché específica llamada **TLB** (*Translation Look-aside Buffer*), que guarda las traducciones página→marco más recientes.

## 4. Gestión de memoria por el sistema operativo

El **gestor de memoria** del SO se encarga de:
- Asignar memoria a los procesos para crear su imagen de memoria.
- Dar memoria cuando un proceso la pide, y liberarla cuando ya no la necesita.
- Evitar que un proceso acceda o interfiera en la memoria de otro (protección).
- Permitir que varios procesos compartan memoria entre sí (para poder comunicarse).
- Gestionar la jerarquía de memoria y resolver los fallos de página en sistemas con memoria virtual.

Sus servicios principales son tres:
1. **Solicitar memoria**: el proceso pide más espacio; el SO lo concede si hay recursos y devuelve un puntero a la nueva zona.
2. **Liberar memoria**: el proceso devuelve espacio que ya no usa, y el SO lo recicla para futuras peticiones.
3. **Compartir memoria**: crear/liberar regiones de memoria compartidas entre procesos, usadas como mecanismo de comunicación entre ellos.

## 5. Relación con los procesos

Cada proceso tiene su propia **imagen de memoria** (código + datos) dentro del mapa de memoria del sistema, y el sistema operativo guarda información sobre cada proceso en su **BCP** (Bloque de Control del Proceso). A diferencia del ejecutable (permanente en disco), la imagen de memoria de un proceso es temporal: existe mientras el proceso se ejecuta y desaparece cuando termina.

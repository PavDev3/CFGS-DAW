
## Cuestión 1: Auditoría de Tiempos con `console.time`

| Registros | Generación (ms) | Ordenación (ms) |
|-----------|------------------|------------------|
| 150.000   | 46,12            | 89,94            |
| 500.000   | 162,45           | 396,92           |

Datos ×3,33, ordenación ×4,41. No crece lineal, crece más rápido que los datos

## Cuestión 2: Ahorro de Procesamiento en el Servidor

Tiempo total ≈ 10.000 × tiempo por usuario (ej. 150 ms -> 25 min de CPU en total),
pero repartido en paralelo entre 10.000 dispositivos: cada usuario nota solo sus
~150 ms.

Beneficio para la empresa: cero carga de CPU/BD en el servidor, sin cuellos de
botella

## Cuestión 4: Límite Arquitectónico y Necesidad de Paginación

No es viable con 8 millones de registros: consumiría varios GB de RAM del cliente,
la descarga/parseo inicial sería muy lenta, y ordenar ese volumen bloquearía el
hilo principal y congelaría la interfaz.

Solución: paginación en servidor (`LIMIT`/`OFFSET` o cursor), con el filtrado y
ordenación delegados a la base de datos (usando índices), y scroll infinito (lazy load) o
"cargar más" en el cliente en lugar de traer todo el catálogo de golpe.
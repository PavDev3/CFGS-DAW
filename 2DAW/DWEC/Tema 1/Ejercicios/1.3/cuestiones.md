## Actividad Propuesta 1.1: ¿Qué es la programación reactiva?

La **programación reactiva** es un paradigma en el que se declaran flujos de datos y sus dependencias, y el propio sistema se encarga de **propagar automáticamente los cambios** por toda esa cadena de dependencias cuando el dato de origen cambia — sin que el programador tenga que actualizar manualmente cada elemento afectado.

**Cómo se comporta una hoja de cálculo (ejemplo):**

Si en una hoja tenemos `A1 = 5`, `B1 = 10` y `C1 = A1 + B1` (resultado 15), y cambiamos `A1` a `20`, `C1` se recalcula solo a `30` de forma inmediata, sin que el usuario tenga que pulsar nada ni volver a escribir la fórmula. La hoja de cálculo detecta que `C1` **depende** de `A1`, y reacciona al cambio propagando el nuevo valor por toda la cadena de celdas que dependan de ella (incluidas las que a su vez dependan de `C1`).

**Relación con los frameworks modernos:** en React, Angular o Vue ocurre lo mismo con el **estado** de la aplicación. El estado hace de `A1`: cuando cambia, todos los elementos de la interfaz que "dependen" de él (el DOM, variables derivadas, otros componentes) se actualizan solos — igual que `C1` se recalcula solo — sin que el programador tenga que escribir manualmente el código que sincroniza cada parte de la pantalla con el dato nuevo.

---

## Actividad de Análisis Comparativo: elección de framework

| Caso | Framework recomendado | Justificación |
|---|---|---|
| **1. Pequeña tienda de barrio**, presupuesto reducido, despliegue rápido | **Vue.js** | Curva de aprendizaje progresiva y ligera — no requiere un equipo grande ni mucha formación previa. Es gratuito y permite tener una interfaz funcional en poco tiempo, sin la complejidad de configuración de Angular. |
| **2. Portal bancario**, cientos de programadores, tipado robusto | **Angular** | Usa **TypeScript** de forma nativa (tipado estático obligatorio), lo que reduce errores en proyectos grandes. Su arquitectura rígida y estructurada facilita que equipos numerosos trabajen de forma estandarizada — justo lo que exige un entorno regulado como la banca. |
| **3. Aplicación con renderizado ultra rápido** de miles de productos y cambios constantes | **ReactJS** | Su **DOM Virtual** con reconciliación calcula solo las diferencias mínimas entre estado anterior y nuevo, actualizando únicamente los nodos necesarios. Tiene además un ecosistema maduro de librerías para virtualización de listas grandes, pensado justo para interfaces con muchos elementos que cambian constantemente. |

---

*Fuente: PDF de la unidad "1.3. Identificación y caracterización de los principales lenguajes relacionados con la programación de clientes Web" (Criterio 1.c), apartado G. Actividades Prácticas y de Consolidación.*

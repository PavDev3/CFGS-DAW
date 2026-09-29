## Ejercicio 1: Interpretación de constructores y desbordamientos

```js
const fechaA = new Date(2026, 0, 10);
const fechaB = new Date(2026, 12, 1);
const fechaC = new Date(2026);
const fechaD = new Date("2026-02-28");
const fechaE = new Date("2026/02/28");
```

| Variable | Fecha resultante | Por qué |
|---|---|---|
| `fechaA` | **10 de enero de 2026**, 00:00 hora local | Mes en índice 0 = enero, día 10. Sin desbordamiento. |
| `fechaB` | **1 de enero de 2027**, 00:00 hora local | Mes 12 desborda: 12 meses = 1 año completo, así que `12 % 12 = 0` (enero) y se suma 1 año → enero de 2027. |
| `fechaC` | **1 de enero de 1970, 00:00:02.026** (UTC, ~2 segundos después de la época Unix) | Al pasar un único número al constructor, **nunca** se interpreta como año: son milisegundos desde el 1/1/1970. `2026` ms ≈ 2 segundos. |
| `fechaD` | **28 de febrero de 2026, 00:00:00 UTC** (01:00 hora local en España en febrero, UTC+1) | Formato ISO 8601 con guiones (`YYYY-MM-DD`): el navegador lo interpreta como **medianoche en UTC**. |
| `fechaE` | **28 de febrero de 2026, 00:00:00 hora local** | Formato con barras (`YYYY/MM/DD`) **no es ISO 8601 estricto**: el navegador lo interpreta como medianoche en **hora local**, no en UTC. |

**Punto clave:** `fechaD` y `fechaE` representan el mismo día, pero con guiones el motor asume UTC y con barras asume hora local — es la trampa clásica del constructor `Date` con cadenas de texto, y por eso el documento recomienda siempre el formato ISO 8601 con guiones para evitar ambigüedad.

---

## Ejercicio 5 (Ejercicio 9 del libro): ¿En qué se diferencia técnicamente JavaScript de Java?

A pesar de compartir parte del nombre (por motivos comerciales de Netscape en 1995, para aprovechar la popularidad de Java), son lenguajes con filosofías opuestas:

| | Java | JavaScript |
|---|---|---|
| Tipo de lenguaje | Tradicional | Script |
| Tipado | Fuerte (estático) | Débil (dinámico) |
| Ejecución | Se compila a bytecode y corre sobre una máquina virtual (JVM) | Se interpreta directamente en el navegador (o en Node.js), sin máquina virtual dedicada |
| Paradigma | Orientado a objetos de forma rígida (todo vive dentro de clases) | Orientado a eventos, con tipado y estructura mucho más flexibles |
| Detección de errores | En tiempo de compilación (antes de ejecutar) | En tiempo de ejecución (runtime), al llegar a la línea con el fallo |

---

## Ejercicio 6 (Ejercicio 10 del libro): Ventajas más importantes de usar JavaScript en el desarrollo web moderno

- **Sencillez y curva de aprendizaje rápida**: sintaxis pensada para ser accesible, sin la complejidad formal de los lenguajes tradicionales.
- **Agilidad en el desarrollo**: no hace falta compilar ni enlazar binarios — cualquier cambio se comprueba al instante recargando el navegador.
- **Integración natural con HTML**: se incrusta directamente dentro de la página web.
- **Portabilidad total**: funciona en cualquier dispositivo (ordenador, tablet, móvil) que tenga un navegador compatible con los estándares, sin recompilar nada.
- **Ecosistema enorme**: frameworks (React, Angular, Vue) y gestores de paquetes (npm) que resuelven de fábrica la mayoría de problemas comunes.
- **Un único lenguaje para todo el stack**: gracias a Node.js, el mismo JavaScript sirve para cliente y para servidor.

---

## Ejercicio 7: Actividad de laboratorio (`calculo.js`)

**Código usado** (`calculo.js`):

```js
console.log("Inicio del script: esta línea se ejecuta con normalidad.");
console.log("Segunda línea: también se ejecuta sin problema.");

const resultado = variableNoDeclarada + 10;

console.log("Esta línea NUNCA se ejecuta: el intérprete ya se ha detenido en la anterior.");
```

**Comportamiento observado al cargarlo desde `ejercicio7_laboratorio.html` con la consola abierta:**

1. Se imprimen con normalidad los dos primeros `console.log` — confirma que el intérprete ejecuta **línea a línea**, no valida todo el archivo de antemano como haría un compilador.
2. Al llegar a la línea `const resultado = variableNoDeclarada + 10;`, el motor lanza un **`ReferenceError: variableNoDeclarada is not defined`**, porque esa variable nunca se declaró.
3. El tercer `console.log` **no llega a ejecutarse**: el intérprete se detiene en el punto exacto del fallo.

**Conclusión (enlaza con el apartado B.4 del documento):** esto demuestra en la práctica que los lenguajes de script detectan los errores en **tiempo de ejecución**, no en una fase previa de compilación — el programa avanza con normalidad hasta topar con la instrucción defectuosa, momento en el que se detiene. Por eso el documento insiste en la necesidad de planes de prueba exhaustivos: un error en una rama de código poco usada puede pasar desapercibido durante mucho tiempo.

---

*Fuente: documento de la unidad "1.4. Particularidades de la programación de guiones (scripts) y sus ventajas y desventajas sobre la programación tradicional" (Criterio 1.d), apartado de Ejercicios Prácticos.*

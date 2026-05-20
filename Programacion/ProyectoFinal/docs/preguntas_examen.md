# Guía de estudio — Proyecto Final Programación
## Liga de E-Sports · Pablo Nunez Fernandez · Mayo 2026

---

## Índice
1. [Herencia y clases abstractas](#1-herencia-y-clases-abstractas)
2. [Interfaz Entrenable](#2-interfaz-entrenable)
3. [Enums](#3-enums)
4. [Estructuras de datos](#4-estructuras-de-datos)
   - [Array de titulares](#41-array-fijo-de-titulares)
   - [ArrayList](#42-arraylist)
   - [HashSet](#43-hashset--duplicados)
   - [Queue FIFO](#44-queue--cola-fifo)
   - [ArrayDeque LIFO](#45-arraydeque--pila-lifo)
   - [Matriz 2D](#46-matriz-2d-de-puntos)
5. [Excepciones personalizadas](#5-excepciones-personalizadas)
6. [Polimorfismo e instanceof](#6-polimorfismo-e-instanceof)
7. [Liga como orquestador](#7-liga-como-orquestador-central)

---

## 1. Herencia y clases abstractas

### ¿Por qué `PersonaLiga` es abstracta?
Porque define atributos y comportamiento común (ID, nombre, nickname, edad, salario) pero el método `calcularCosteMensual()` no tiene una fórmula válida para "una persona genérica": cada subclase lo calcula diferente. Al declararlo abstracto, el compilador obliga a que toda subclase lo implemente.

```
PersonaLiga  (abstracta)
├── Jugador  (implementa Entrenable)
└── Entrenador
```

### ¿Puedes instanciar directamente un `PersonaLiga`?
No. `new PersonaLiga(...)` da error de compilación. Solo se puede instanciar a través de sus subclases concretas (`new Jugador(...)` o `new Entrenador(...)`).

### ¿Qué obliga `calcularCosteMensual()` abstracto a hacer en las subclases?
Que lo implementen con `@Override` o el código no compila. Cada una usa su fórmula:

| Clase | Fórmula |
|---|---|
| `Jugador` | `salarioBase + (nivelMecanico + nivelEstrategico) * 100` |
| `Entrenador` | `salarioBase + (experiencia * 200)` |

```java
// Jugador.java
@Override
public double calcularCosteMensual() {
    return getSalarioBase() + (nivelMecanico + nivelEstrategico) * 100.0;
}

// Entrenador.java
@Override
public double calcularCosteMensual() {
    return getSalarioBase() + (experiencia * 200.0);
}
```

Si `PersonaLiga` no fuera abstracta y tuviera una implementación por defecto, podrías olvidarte de sobreescribirla y calcularías mal los costes sin que el compilador avisara. Al ser abstracto, el error es en compilación, no en ejecución.

### ¿Qué ventaja da tener `ArrayList<PersonaLiga>` en `Liga`?
Almacena jugadores y entrenadores en la misma lista de forma polimórfica. Para buscar una persona por ID no hace falta una lista separada para cada tipo: se recorre una sola lista llamando a `getIdentificador()`, que está en `PersonaLiga`.

### ¿Qué desventaja tiene?
Para operaciones exclusivas de jugadores (fichar, sancionar) hay que hacer `instanceof` + cast en `Main.java`, lo que añade verbosidad.

---

## 2. Interfaz Entrenable

### ¿Qué métodos declara?
```java
public interface Entrenable {
    void entrenar();
    double calcularRendimiento();
}
```

### ¿Por qué es interfaz y no clase abstracta?
Porque `Entrenable` define un **contrato de capacidad** ("este objeto sabe entrenarse y medir su rendimiento"), no una familia de objetos. Una interfaz permite que cualquier clase la implemente sin estar forzada a pertenecer a una jerarquía concreta.

### ¿Por qué solo `Jugador` la implementa y no `Entrenador`?
Porque en la lógica del proyecto los entrenadores dirigen, no se entrenan. Solo los jugadores tienen `nivelMecanico`, `nivelEstrategico` y `mvpTotales`, que son los datos que usa `calcularRendimiento()`.

### ¿Qué pasa si `Jugador` no implementa alguno de los métodos?
Error de compilación: `Jugador is not abstract and does not override abstract method entrenar() in Entrenable`.

### Fórmula de `calcularRendimiento()`

| Componente | Peso | Cálculo |
|---|---|---|
| Nivel mecánico | 40% | `nivelMecanico * 0.4` |
| Nivel estratégico | 40% | `nivelEstrategico * 0.4` |
| Ratio MVP | 20% | `(mvpTotales / partidasJugadas) * 10 * 0.2` |

Si `partidasJugadas == 0`, el componente MVP es 0 para evitar división por cero.

```java
@Override
public double calcularRendimiento() {
    double componenteMvp = 0.0;
    if (partidasJugadas > 0) {
        componenteMvp = ((double) mvpTotales / partidasJugadas) * 10 * 0.2;
    }
    return (nivelMecanico * 0.4) + (nivelEstrategico * 0.4) + componenteMvp;
}
```

---

## 3. Enums

### ¿Qué enums hay y para qué sirven?
- **`Rol`** — 5 valores: `TOP, JUNGLE, MID, ADC, SUPPORT`. Su ordinal (0–4) se usa directamente como índice en `titulares[]`.
- **`TipoIncidencia`** — 5 valores: `SANCION, EXPULSION, ERROR_TECNICO, PARTIDO_APLAZADO, OTRO`.

### ¿Qué ventaja tiene `Rol` frente a usar Strings?
- El compilador rechaza valores inválidos: `Rol.PORTERO` no compila, `"PORTERO"` sí.
- El `switch` y el `ordinal()` funcionan de forma segura y eficiente.
- Autocompletado en el IDE: no hay errores de typo.

### ¿Qué valor devuelve `MID.ordinal()`?
`2`, porque es el tercer valor declarado en el enum (empieza en 0).

### ¿Qué riesgo tiene usar `ordinal()` como índice?
Si se reordena el enum (p. ej. se pone `MID` antes de `JUNGLE`), los ordinales cambian y el array se corrompe silenciosamente. Es el precio de la eficiencia O(1).

---

## 4. Estructuras de datos

### 4.1 Array fijo de titulares

**¿Por qué `Jugador[] titulares` y no `ArrayList<Jugador>`?**

Porque los titulares tienen una restricción que el ArrayList no garantiza solo: **un único jugador por rol**. El array aprovecha `Rol.ordinal()` como índice directo:

```java
// Equipo.java
int indice = jugador.getRol().ordinal(); // TOP=0, JUNGLE=1, MID=2, ADC=3, SUPPORT=4
if (titulares[indice] != null) {
    throw new RolNoDisponibleException("El rol " + jugador.getRol() + " ya está ocupado");
}
titulares[indice] = jugador;
```

- Comprobación en **O(1)**, sin bucles.
- La posición `null` indica que el rol está vacante de forma explícita.
- El tamaño fijo (5) comunica la intención: exactamente 5 roles, ni más ni menos.

Con ArrayList habría que recorrerlo entero buscando si ya existe un jugador con ese rol: O(n).

**¿Por qué los suplentes sí usan ArrayList?**
Porque su número es variable y no tienen restricción de roles duplicados entre sí.

---

### 4.2 ArrayList

Se usan cuatro en `Liga`:

| Lista | Contenido | Motivo |
|---|---|---|
| `ArrayList<Equipo>` | Equipos de la liga | Índice necesario para la matriz de puntos |
| `ArrayList<PersonaLiga>` | Jugadores y entrenadores | Polimorfismo: ambos tipos en una sola lista |
| `ArrayList<Partido>` | Historial completo | Acceso aleatorio para buscar por jornada |
| `ArrayList<Incidencia>` | Incidencias | Número desconocido a priori |

Y una en `Equipo`: `ArrayList<Jugador> suplentes` — número variable, sin restricción de rol.

---

### 4.3 HashSet — Duplicados

**¿Por qué HashSet y no recorrer el ArrayList?**

`ArrayList.contains()` es **O(n)**: recorre elemento a elemento.
`HashSet.contains()` es **O(1)**: calcula el hash y accede directamente.

```java
// Liga.java
if (idsPersonas.contains(persona.getIdentificador())) {
    throw new IllegalArgumentException("Ya existe una persona con el ID: " + ...);
}
personas.add(persona);
idsPersonas.add(persona.getIdentificador()); // se guarda en ambas estructuras
```

Hay tres HashSet en `Liga`:
```java
HashSet<String> idsPersonas;
HashSet<String> nombresEquipos;
HashSet<String> idsPartidos;
```

**¿Qué riesgo tiene mantener dos estructuras sincronizadas?**
Si se elimina un elemento del ArrayList sin eliminarlo del HashSet (o viceversa), las dos quedan desincronizadas. Por eso toda eliminación debe actualizar ambas. En `eliminarPersona()`:
```java
personas.remove(persona);
idsPersonas.remove(id); // imprescindible para mantener consistencia
```

---

### 4.4 Queue — Cola FIFO

**FIFO = First In, First Out** (el primero en entrar es el primero en salir).

```java
Queue<Partido> colaPartidos = new LinkedList<>();
```

| Operación | Método | Comportamiento si vacía |
|---|---|---|
| Añadir al final | `offer()` / `add()` | — |
| Ver el primero sin sacarlo | `peek()` | Devuelve `null` |
| Sacar el primero | `poll()` | Devuelve `null` |

**¿Por qué no usar `ArrayList` y leer siempre el índice 0?**
Porque `Queue` comunica la intención FIFO explícitamente. Con ArrayList un programador podría accidentalmente usar `get(3)` y romper el orden. Además, `poll()` en `LinkedList` es O(1); con ArrayList borrar el índice 0 es O(n) porque desplaza todos los demás elementos.

**¿Qué devuelve `poll()` si la cola está vacía?**
`null`. Si usaras `remove()` en cambio, lanzaría `NoSuchElementException`.

**Flujo de uso:**
```java
// Al programar un partido: se añade al historial y a la cola
partidos.add(partido);
colaPartidos.offer(partido);

// Ver el próximo sin jugarlo
Partido proximo = colaPartidos.peek();

// Jugarlo (sacarlo de la cola)
Partido aJugar = colaPartidos.poll();
```

---

### 4.5 ArrayDeque — Pila LIFO

**LIFO = Last In, First Out** (el último en entrar es el primero en salir).

Se usan dos pilas en `Liga`:
```java
ArrayDeque<String> pilaAcciones;   // historial de operaciones del usuario
ArrayDeque<String> pilaSanciones;  // IDs de jugadores sancionados (para undo)
```

| Operación | Método |
|---|---|
| Meter en la cima | `push()` |
| Ver la cima sin sacar | `peek()` |
| Sacar de la cima | `pop()` |

**¿Por qué `ArrayDeque` y no `Stack`?**
La propia Javadoc de Java recomienda `ArrayDeque` como implementación de pila porque es más rápida (no está sincronizada innecesariamente) y no tiene el overhead de `Vector` del que hereda `Stack`.

**Flujo de sanciones (undo):**
```java
// Aplicar sanción
public void aplicarSancion(Jugador jugador) {
    jugador.setSancionado(true);
    pilaSanciones.push(jugador.getIdentificador()); // guarda el ID en la cima
}

// Deshacer la última sanción
public String deshacerUltimaSancion() {
    String idJugador = pilaSanciones.pop(); // extrae el último ID
    Jugador jugador = (Jugador) buscarPersonaPorId(idJugador);
    jugador.setSancionado(false);
}
```

**¿Qué problema tiene guardar IDs y no referencias directas?**
Al deshacer, hay que buscar el jugador por ID en O(n). Además, si el jugador se elimina de la liga antes del undo, `pop()` devuelve un ID que ya no existe y la operación falla sin validación.

---

### 4.6 Matriz 2D de puntos

```java
int[][] matrizPuntos = new int[NUM_JORNADAS][MAX_EQUIPOS]; // [5][6]
```

- **Filas** (índice 0–4): jornadas.
- **Columnas** (índice 0–5): equipos, en el mismo orden que `ArrayList<Equipo>`.

Puntuación: victoria = 3 pts, empate = 1 pt, derrota = 0 pts.

```java
// Liga.java — actualizar tras registrar resultado
int jornada = partido.getJornada() - 1; // 0-based
int idxLocal = getIndiceEquipo(partido.getEquipoLocal());
Equipo ganador = partido.calcularGanador();
if (ganador == null) {
    matrizPuntos[jornada][idxLocal] += 1;       // empate
    matrizPuntos[jornada][idxVisitante] += 1;
} else if (ganador == partido.getEquipoLocal()) {
    matrizPuntos[jornada][idxLocal] += 3;        // victoria local
} else {
    matrizPuntos[jornada][idxVisitante] += 3;    // victoria visitante
}
```

**¿Qué riesgo tiene el índice acoplado al orden del ArrayList?**
Si se eliminara un equipo de la lista, todos los índices posteriores cambiarían y la matriz quedaría corrupta. Para evitarlo, el sistema no permite eliminar equipos una vez registrados.

---

## 5. Excepciones personalizadas

### ¿Qué dos excepciones propias hay y cuándo se lanzan?

| Excepción | Se lanza cuando | La lanza | La captura |
|---|---|---|---|
| `RolNoDisponibleException` | El rol del jugador ya está ocupado en titulares, o titular y suplente tienen roles distintos al intercambiar | `Equipo.ficharTitular()`, `Equipo.intercambiarTitularSuplente()` | `Main` |
| `JugadorSancionadoException` | Se intenta fichar a un jugador sancionado | `Jugador.verificarDisponibilidad()` | `Main` |

### ¿Por qué extienden `Exception` y no `RuntimeException`?

Porque son **checked exceptions**: el compilador obliga a quien llama a tratarlas con `try-catch` o declararlas con `throws`. No se pueden ignorar por descuido.

```java
// Equipo.java — quien llama DEBE gestionar estas excepciones
public void ficharTitular(Jugador jugador)
        throws RolNoDisponibleException, JugadorSancionadoException {
    jugador.verificarDisponibilidad();   // lanza JugadorSancionadoException
    int indice = jugador.getRol().ordinal();
    if (titulares[indice] != null) {
        throw new RolNoDisponibleException("El rol " + jugador.getRol() + " ya está ocupado");
    }
    titulares[indice] = jugador;
}
```

Un rol ocupado o un jugador sancionado son **violaciones de las reglas de negocio** que el usuario puede provocar intencionadamente desde el menú, no errores de programación. Tiene sentido forzar su tratamiento en compilación.

### ¿Qué diferencia hay con `IllegalArgumentException`?
`IllegalArgumentException` es unchecked (extiende `RuntimeException`): el compilador no obliga a capturarla. Se usa en este proyecto para límites de capacidad (`"La liga ya tiene 6 equipos"`), que son errores más simples y menos críticos.

### ¿Por qué los `catch` están separados en `Main`?
Para mostrar un mensaje distinto al usuario según el motivo del rechazo: "rol ocupado" es diferente de "jugador sancionado". Si hubiera un solo `catch (Exception e)` se perdería esa distinción.

---

## 6. Polimorfismo e instanceof

### ¿Cómo distingues `Jugador` de `Entrenador` en el `ArrayList<PersonaLiga>`?

Con `instanceof` para comprobar el tipo real en tiempo de ejecución, y luego un cast para acceder a los métodos específicos:

```java
// Liga.java
for (PersonaLiga p : personas) {
    if (p instanceof Jugador) {
        Jugador j = (Jugador) p; // cast seguro: ya verificamos con instanceof
        System.out.printf("%s | Rol: %s | Rendimiento: %.2f%n",
                j.getNickname(), j.getRol(), j.calcularRendimiento());
    }
}
```

Sin el `instanceof` y el cast, sobre una referencia `PersonaLiga` solo se pueden llamar métodos declarados en `PersonaLiga`. `getRol()` y `calcularRendimiento()` no existen en `PersonaLiga`, solo en `Jugador`.

### Si llamas a `calcularCosteMensual()` sobre una referencia `PersonaLiga` que en realidad es un `Jugador`, ¿qué versión se ejecuta?

La de `Jugador`. Esto es el **polimorfismo en tiempo de ejecución**: Java mira el tipo real del objeto (no el tipo de la variable) y ejecuta el método correspondiente. Se llama **dynamic dispatch**.

```java
PersonaLiga p = new Jugador(...); // referencia de tipo PersonaLiga, objeto Jugador
p.calcularCosteMensual();         // ejecuta Jugador.calcularCosteMensual()
```

---

## 7. Liga como orquestador central

### ¿Por qué toda la lógica de negocio está en `Liga` y no repartida?

Para mantener consistencia. Cada operación puede afectar a varias estructuras a la vez. Por ejemplo, al registrar un partido:

```java
partidos.add(partido);          // historial
idsPartidos.add(partido.getId()); // HashSet de deduplicación
colaPartidos.offer(partido);    // cola FIFO
registrarAccion("Partido registrado: ..."); // pila de historial
```

Si `Main` hiciera esto directamente, podría olvidar actualizar el HashSet o la cola. Al centralizar en `Liga`, las invariantes (ArrayList siempre sincronizado con HashSet, cola siempre actualizada) se protegen en un único lugar.

### ¿Qué hace `Main.java` entonces?

Solo recoge la entrada del usuario (Scanner) y delega en `Liga`. No contiene lógica de negocio.

### ¿Qué desventaja tiene que `Liga` tenga tantas responsabilidades?

Que `Liga.java` crece mucho (301 líneas). En un proyecto más grande se separaría en servicios: `PersonaService`, `PartidoService`, `SancionService`, etc. Es una decisión aceptable para el tamaño de este proyecto.

---

## Resumen de estructuras y su justificación

| Estructura | Dónde | Por qué |
|---|---|---|
| `Jugador[] titulares` | `Equipo` | Unicidad de rol en O(1) usando `Rol.ordinal()` como índice |
| `ArrayList<Jugador> suplentes` | `Equipo` | Número variable, sin restricción de rol |
| `ArrayList<Equipo/PersonaLiga/Partido/Incidencia>` | `Liga` | Colecciones dinámicas con acceso por índice |
| `HashSet<String>` ×3 | `Liga` | Detección de duplicados en O(1) |
| `Queue<Partido>` | `Liga` | Cola FIFO: el primer partido registrado es el primero en jugarse |
| `ArrayDeque<String>` ×2 | `Liga` | Pila LIFO: historial más reciente primero + undo de sanciones |
| `int[][]` | `Liga` | Puntos por jornada/equipo en O(1), memoria compacta |

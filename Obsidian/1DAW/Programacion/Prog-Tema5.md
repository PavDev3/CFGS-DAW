# Tema 5 — Arrays y POO Avanzada

← [[Prog-Tema4]] | [[Programacion]]

---

## Parte 1: Arrays

> Fuente: `Tema5.1-Arrays-v2.pdf`

### Índice

1. Arrays unidimensionales (Vectores)
   1. Declaración, creación e inicialización
   2. Operaciones básicas
   3. Ordenación de arrays
   4. Otros métodos
2. Arrays bidimensionales (Matrices)
   1. Declaración, creación e inicialización
   2. Operaciones básicas

---

### 1. Arrays unidimensionales (vectores)

Son estructuras de datos para agrupar elementos de un mismo tipo dentro de un programa.

**Ejemplo:** Si queremos almacenar las notas de un examen de los 30 alumnos de la clase, en lugar de usar 30 variables (nota1, nota2, … nota30), crearemos un array o vector de notas donde se almacenan de forma organizada las 30 notas.

Un array (vector) es una colección ordenada de elementos del mismo tipo, donde cada elemento está asociado a un índice o posición que ocupa. Los elementos de un array se almacenan en posiciones contiguas de memoria.

Un vector queda determinado por:
- Tipo de sus elementos
- Número de elementos

Para utilizar un array o vector hay que realizar tres operaciones: declarar una referencia al vector, crear el vector, e inicializarlo.

---

#### Declaración

Como otras variables, antes de poder utilizar un vector, primero se debe declarar. Se pueden declarar de formas distintas:

```java
Tipo[] nombre;
Tipo nombre[];
```

`Tipo` indica el tipo de los elementos del vector (int, double, String, un objeto…); `nombre` es un identificador que nombra al array. De esta forma, `nombre` es una referencia a un vector, pero el vector todavía no está creado.

---

#### Creación

Tras declarar el vector, el siguiente paso es crearlo o construirlo. Hay que indicar el número de elementos para reservar la cantidad de memoria necesaria.

```java
nombre = new Tipo[numeroElementos];
```

**Ejemplos:**

```java
// Vector de 30 elementos de tipo double
double[] notas = new double[30];

// Vector de 10 objetos Cuenta (los objetos están a null hasta inicializarlos)
Cuenta[] cuentas = new Cuenta[10];
```

> **IMPORTANTE:** cuando se crea un vector de objetos se ha hecho el `new` del vector, no de los objetos. Se reserva memoria para 10 referencias, pero todos están a `null`.

Es muy común declarar y crear el vector en una sola línea:

```java
int[] numeros = new int[5];
String[] nombres = new String[3];
```

---

#### Inicialización

Si no se especifica ningún valor, los elementos de un vector se inicializan automáticamente:
- Variables numéricas → `0`
- Objetos → `null`
- Booleanos → `false`

Es posible inicializarlo con valores a la vez que se declara (no hace falta `new`; el tamaño se deduce del número de elementos):

```java
int[] primos = {2, 3, 5, 7, 11};
String[] dias = {"Lunes", "Martes", "Miércoles"};
```

---

#### Acceso

Para acceder al valor de un elemento se usa el nombre del vector seguido de un subíndice entre corchetes `[x]`. Los índices van desde `0` hasta `tamaño - 1`.

```java
vectorNotas[3]; // nota del alumno 4 (índice 3)
```

Si se intenta acceder con un subíndice fuera de rango, Java lanza:

```
ArrayIndexOutOfBoundsException
```

Para evitarlo, usar el atributo `length`:

```java
for (int i = 0; i < vector.length; i++) {
    // acceso seguro a vector[i]
}
```

---

#### Recorrido

```java
// Recorrido con for clásico
for (int i = 0; i < primos.length; i++) {
    System.out.println(primos[i]);
}

// Recorrido con for-each
for (String dia : dias) {
    System.out.println(dia);
}
```

---

#### Ordenación

`Arrays.sort` ordena por defecto de forma ascendente (tipos básicos y String):

```java
import java.util.Arrays;
int[] nums = {5, 2, 8, 1};
Arrays.sort(nums); // {1, 2, 5, 8}
```

Para ordenar un array de **objetos**, la clase debe implementar la interfaz `Comparable` y definir el método `compareTo`:

```java
// Prototipo de compareTo
public int compareTo(Object otro)
```

Funciona así:
- Devuelve `0` si `this` es igual a `otro`
- Devuelve un valor **< 0** si `this` es menor que `otro`
- Devuelve un valor **> 0** si `this` es mayor que `otro`

---

#### Equals y Clone

**EQUALS:** Devuelve `true` si los dos arrays comparados son el mismo (compara referencias). Se puede sobreescribir para comparar por contenido.

**CLONE:** Devuelve un nuevo array con los mismos datos (copia superficial).

```java
int[] a = {1, 2, 3};
int[] b = a.clone(); // b es una copia independiente
```

---

#### Uso como parámetro

Un vector puede pasarse como parámetro a un método. El paso siempre es **por referencia**: cualquier cambio sobre los elementos del vector dentro del método se conservará al salir.

```java
public static void rellenarArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        arr[i] = i * 2;
    }
}
```

---

### 2. Arrays bidimensionales (matrices)

Una matriz es un array de dos dimensiones: un conjunto de elementos del mismo tipo distribuidos en filas y columnas. Se usan para relacionar 2 magnitudes.

**Ejemplo:** Notas de los 30 alumnos en los tres trimestres.

#### Declaración y Creación

```java
Tipo[][] nombre = new Tipo[numFilas][numColumnas];

// Ejemplo: matriz 5x4 de enteros
int[][] matriz = new int[5][4];
```

#### Inicialización

```java
// Declaración, creación e inicialización de una matriz 2x3
int[][] tabla = {{1, 2, 3}, {4, 5, 6}};
```

#### Acceso

```java
tabla[0][1]; // fila 0, columna 1 → valor 2
```

#### Recorrido por filas

```java
for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println();
}
```

#### Recorrido por columnas

```java
for (int j = 0; j < matriz[0].length; j++) {
    for (int i = 0; i < matriz.length; i++) {
        System.out.print(matriz[i][j] + " ");
    }
    System.out.println();
}
```

---

## Parte 2: POO Avanzada

> Fuente: `Tema5.2-POOavanzada-v2.pdf`

### Índice

1. Sobrecarga de métodos
2. Herencia. Superclase y subclases
3. Sobreescritura de métodos
4. Clases y métodos abstractos
5. Polimorfismo
6. Paquetes
7. Enumerados
8. Interfaces

---

### 1. Sobrecarga de métodos

Un método sobrecargado es un método que tiene el mismo nombre que otro pero con diferentes argumentos.

**Reglas:**
- Un método puede ser sobrecargado en la misma clase o en una subclase.
- La lista de argumentos debe ser diferente (en número y/o en tipos).
- Puede cambiar el tipo de retorno.
- Puede cambiar el modificador de acceso.
- Los constructores también se pueden sobrecargar.

---

### 2. Herencia

La herencia es un mecanismo que permite crear clases a partir de otras existentes:
- Heredando y posiblemente añadiendo atributos.
- Heredando y posiblemente añadiendo y/o modificando métodos.

- La clase de la que se hereda → **superclase** o clase padre.
- La clase que hereda → **subclase** o clase hija.

En Java, una clase sólo puede extender una superclase (**herencia simple**):

```java
public class Alumno extends Persona {
    // Alumno hereda atributos y métodos de Persona
}
```

---

#### Visibilidad

| Modificador | Clase | Paquete | Subclase | Todos |
|-------------|-------|---------|----------|-------|
| `public`    | SI    | SI      | SI       | SI    |
| `protected` | SI    | SI      | SI       | NO    |
| friendly    | SI    | SI      | NO       | NO    |
| `private`   | SI    | NO      | NO       | NO    |

---

#### `this` y `super`

- **`this`** referencia al objeto actual.
  - `this.atributo` → atributo del objeto actual.
  - `this()` → constructor del objeto actual.
- **`super`** referencia al objeto de la superclase.
  - `super.atributo` → atributo de la superclase.
  - `super()` → constructor de la superclase.

```java
public class Directivo extends Empleado {
    private double bonus;

    public Directivo(String nombre, double sueldo, double bonus) {
        super(nombre, sueldo); // llama al constructor de Empleado
        this.bonus = bonus;
    }
}
```

> **IMPORTANTE:** La llamada a `super()` debe ser la **primera instrucción** del constructor de la subclase.

---

### 3. Sobreescritura de métodos

Cuando una subclase hereda un método de una superclase, puede sobreescribirlo para definir un comportamiento específico.

**Reglas:**
- La lista de argumentos debe ser la misma.
- El tipo de retorno debe ser el mismo o un subtipo.
- El nivel de acceso no puede ser más restrictivo (pero sí menos).
- No se puede sobreescribir un método marcado como `final` ni como `static`.

```java
@Override
public String toString() {
    return super.toString() + ", bonus: " + bonus;
}
```

---

#### Clases y métodos `final`

- Una **clase final** no puede tener subclases (ej: `String`).
- Un **método final** no puede ser sobreescrito por ninguna subclase.

---

### 4. Clases abstractas

- Una **clase abstracta** no puede instanciarse directamente; sirve para ser heredada.
- Un **método abstracto** está declarado pero sin implementación.
- Una clase abstracta puede tener métodos abstractos y métodos concretos.
- Una subclase debe implementar todos los métodos abstractos (o volver a declararlos como abstractos, convirtiéndose en abstracta también).

```java
public abstract class Figura {
    protected String color;

    public abstract double calcularArea(); // sin implementación

    public String getColor() { // método concreto
        return color;
    }
}

public class Rectangulo extends Figura {
    private double base, altura;

    @Override
    public double calcularArea() {
        return base * altura;
    }
}
```

> Se pueden crear **referencias** a clases abstractas, pero no instanciarlas directamente. Se usa **up-casting**:
> ```java
> Figura f = new Rectangulo("rojo", 3, 4); // OK
> ```

---

### 5. Polimorfismo

Un objeto sólo es de una clase (la que se le asigna con `new`). Pero la **referencia** es polimórfica: puede referirse a objetos de diferentes clases relacionadas por herencia.

La combinación de herencia + enlace dinámico = **polimorfismo**.

#### Operador `instanceof`

Sirve para saber el tipo real de un objeto en tiempo de ejecución:

```java
if (objeto instanceof ClaseB) {
    // es una instancia de ClaseB
}
```

#### Conversión entre objetos (casting)

```java
Figura f = new Rectangulo(...); // up-casting (automático)
Rectangulo r = (Rectangulo) f; // down-casting (explícito)
```

---

### 6. Paquetes

Las clases se agrupan en paquetes según su funcionalidad. La declaración de paquete debe ser la primera instrucción válida del archivo:

```java
package com.empresa.proyecto;
```

Para usar clases de otro paquete:

```java
import java.util.Arrays;
import java.util.*;
```

Convención de nombres: usar el dominio invertido → `com.sun.eng`

---

### 7. Enumerados

Un tipo enumerado restringe los valores posibles a un conjunto definido:

```java
public enum DiaSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}
```

```java
DiaSemana dia = DiaSemana.LUNES;
System.out.println(dia); // LUNES
```

También se pueden añadir atributos a un enumerado:

```java
public enum Planeta {
    MERCURIO(3.303e+23, 2.4397e6),
    TIERRA(5.976e+24, 6.37814e6);

    private final double masa;
    private final double radio;

    Planeta(double masa, double radio) {
        this.masa = masa;
        this.radio = radio;
    }
}
```

---

### 8. Interfaces

Una interfaz define un conjunto de constantes y métodos públicos sin cuerpo (contrato que deben cumplir las clases que la implementen).

- Los atributos son siempre `public`, `static` y `final`.
- Los métodos son siempre `public` y `abstract` (salvo métodos `default` desde Java 8).
- Una clase puede implementar **varias interfaces** (herencia múltiple de tipo).
- Una clase sólo puede extender una superclase.

```java
public interface Arrancable {
    void arrancar();
    void parar();
}

public class Coche implements Arrancable {
    @Override
    public void arrancar() { System.out.println("Coche arrancando..."); }

    @Override
    public void parar() { System.out.println("Coche parando..."); }
}
```

```java
// Una clase puede implementar varias interfaces
public class Coche extends Vehiculo implements Arrancable, MedibleConsumo {
    // ...
}
```

#### Interfaces vs Clases Abstractas

| | Interfaz | Clase Abstracta |
|---|---|---|
| Instanciable | No | No |
| Herencia múltiple | Sí (implements) | No (una sola) |
| Métodos con código | Solo `default` (Java 8+) | Sí |
| Atributos | Solo constantes | Cualquier tipo |

---

## Archivos de referencia

- `Programacion/Tema5/Tema5.1-Arrays-v2.pdf`
- `Programacion/Tema5/Tema5.2-POOavanzada-v2.pdf`
- `Programacion/Tema5/Relacin9-v2.pdf`
- `Programacion/Tema5/Relacin10-v2.pdf`

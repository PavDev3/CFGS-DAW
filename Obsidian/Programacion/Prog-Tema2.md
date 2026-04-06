# Tema 2 — Tipos de Datos y Conversiones en Java

← [[Prog-Tema1]] | [[Programacion]] | Siguiente: [[Prog-Tema3]]

---

## Tipos de Datos Primitivos en Java

| Tipo | Tamaño | Rango | Ejemplo |
|------|--------|-------|---------|
| `byte` | 8 bits | -128 a 127 | `byte b = 100;` |
| `short` | 16 bits | -32768 a 32767 | `short s = 1000;` |
| `int` | 32 bits | -2^31 a 2^31-1 | `int i = 300;` |
| `long` | 64 bits | -2^63 a 2^63-1 | `long l = 1000L;` |
| `float` | 32 bits | ~6-7 dígitos decimales | `float f = 3.14f;` |
| `double` | 64 bits | ~15 dígitos decimales | `double d = 3.14;` |
| `char` | 16 bits | '\u0000' a '\uffff' | `char c = 'A';` |
| `boolean` | 1 bit | true / false | `boolean ok = true;` |

---

## Conversiones de Tipos (Casting)

### Conversión implícita (Widening)
Java convierte automáticamente de un tipo menor a uno mayor sin pérdida de datos.

```java
byte  → short → int → long → float → double
```

```java
int i = 300;
long l = i;  // Conversión implícita, sin pérdida
```

### Conversión explícita (Narrowing / Casting)
Se indica explícitamente. Puede haber pérdida de datos.

```java
long l = 1000L;
int i = (int) l;       // → 1000, sin pérdida

float f = 123.456f;
long n = (long) f;     // → 123, pierde .456

int i = 300;
byte b = (byte) i;     // → 44, 300 está fuera del rango de byte
```

---

## Ejercicios de Conversiones (Tarea)

### Respuestas a los ejercicios

```
1. Ejercicio 8:  long → int:   1000  (sin pérdida)
2. Ejercicio 9:  int → char:   'C'   (sin pérdida)
3. Ejercicio 10: int → byte:    44   (pérdida: 300 está fuera de rango)
4. Ejercicio 11: float → long: 123   (pérdida: pierde .456)

Conversiones implícitas posibles:
- short  → int: SÍ
- long   → int: NO (long es mayor que int)
- int    → int: NO (mismo tipo)
- byte   → int: SÍ
```

---

## Variables y Operadores

### Declaración de variables

```java
// Declaración y asignación
int edad = 25;
String nombre = "Pablo";
double precio = 19.99;

// Declaración múltiple
int a = 1, b = 2, c = 3;
```

### Operadores Aritméticos

| Operador | Descripción | Ejemplo |
|----------|-------------|---------|
| `+` | Suma | `5 + 3 = 8` |
| `-` | Resta | `5 - 3 = 2` |
| `*` | Multiplicación | `5 * 3 = 15` |
| `/` | División | `10 / 3 = 3` (enteros) |
| `%` | Módulo (resto) | `10 % 3 = 1` |

### Operadores de Comparación

```java
== != > < >= <=
```

### Operadores Lógicos

```java
&&   // AND
||   // OR
!    // NOT
```

---

## Estructuras de Control en Java

### if-else

```java
if (edad >= 18) {
    System.out.println("Mayor de edad");
} else {
    System.out.println("Menor de edad");
}
```

### switch

```java
switch (dia) {
    case 1: System.out.println("Lunes"); break;
    case 2: System.out.println("Martes"); break;
    default: System.out.println("Otro día");
}
```

### Bucles

```java
// for
for (int i = 0; i < 10; i++) {
    System.out.println(i);
}

// while
while (condicion) {
    // instrucciones
}

// do-while
do {
    // instrucciones
} while (condicion);
```

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema2/Tema2-v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema2/Tareas/Exercises of Conversion.pdf`

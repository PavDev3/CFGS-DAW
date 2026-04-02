# Tema 3 — Programación Modular

← [[Prog-Tema2]] | [[Programacion]] | Siguiente: [[Prog-Tema4]]

---

## Programación Modular

La **programación modular** consiste en dividir el programa en unidades más pequeñas y manejables llamadas **módulos** o **métodos**.

### Ventajas
- Reutilización de código
- Facilita el mantenimiento
- Mejora la legibilidad
- Facilita las pruebas (testing)

---

## Métodos en Java

### Estructura de un método

```java
modificador tipoRetorno nombreMetodo(parámetros) {
    // cuerpo del método
    return valor; // si no es void
}
```

### Ejemplos de métodos

```java
// Método que devuelve un valor
public static int sumar(int a, int b) {
    return a + b;
}

// Método sin retorno (void)
public static void mostrarMensaje(String mensaje) {
    System.out.println(mensaje);
}

// Método con múltiples parámetros
public static boolean esPrimo(int numero) {
    if (numero < 2) return false;
    for (int i = 2; i <= Math.sqrt(numero); i++) {
        if (numero % i == 0) return false;
    }
    return true;
}
```

---

## Paso de Parámetros

### Por valor (tipos primitivos)
En Java, los tipos primitivos se pasan **por valor**: el método recibe una copia.

```java
public static void duplicar(int n) {
    n = n * 2;  // Solo modifica la copia local
}

int x = 5;
duplicar(x);
System.out.println(x); // Sigue siendo 5
```

### Por referencia (objetos)
Los objetos se pasan por referencia: el método puede modificarlos.

```java
public static void modificar(int[] array) {
    array[0] = 99;  // Modifica el array original
}
```

---

## Sobrecarga de Métodos

Java permite definir varios métodos con el mismo nombre pero distintos parámetros:

```java
public static int sumar(int a, int b) {
    return a + b;
}

public static double sumar(double a, double b) {
    return a + b;
}

public static int sumar(int a, int b, int c) {
    return a + b + c;
}
```

---

## Recursividad

Un método que se llama a sí mismo.

```java
public static int factorial(int n) {
    if (n == 0 || n == 1) return 1;
    return n * factorial(n - 1);
}

public static int fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}
```

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema3/Tema 03. Programación modular.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema3/ExamenTema3Java.pdf`

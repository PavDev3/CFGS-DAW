# Tema 5 — POO Avanzada y Arrays

← [[Prog-Tema4]] | [[Programacion]]

---

## Arrays en Java

Un **array** es una estructura de datos que almacena elementos del mismo tipo de forma contigua.

### Declaración y uso

```java
// Declaración
int[] numeros = new int[5];           // Array de 5 enteros
String[] nombres = new String[3];      // Array de 3 Strings

// Inicialización directa
int[] primos = {2, 3, 5, 7, 11};
String[] dias = {"Lunes", "Martes", "Miércoles"};

// Acceso a elementos
numeros[0] = 10;
System.out.println(numeros[0]); // 10
System.out.println(primos.length); // 5

// Recorrido
for (int i = 0; i < primos.length; i++) {
    System.out.println(primos[i]);
}

// For-each
for (String dia : dias) {
    System.out.println(dia);
}
```

### Arrays bidimensionales (matrices)

```java
int[][] matriz = new int[3][3];
int[][] tablero = {{1,2,3}, {4,5,6}, {7,8,9}};

for (int i = 0; i < tablero.length; i++) {
    for (int j = 0; j < tablero[i].length; j++) {
        System.out.print(tablero[i][j] + " ");
    }
    System.out.println();
}
```

---

## POO Avanzada — Herencia

La **herencia** permite crear nuevas clases a partir de clases existentes.

```java
// Clase padre (superclase)
public class Empleado {
    private String nombre;
    protected double sueldo;
    protected double sueldoMaximo;

    public Empleado(String nombre, double sueldo, double sueldoMaximo) {
        this.nombre = nombre;
        this.sueldoMaximo = sueldoMaximo;
        setSueldo(sueldo);
    }

    public void setSueldo(double sueldo) {
        if (sueldo <= sueldoMaximo) {
            this.sueldo = sueldo;
        }
    }

    @Override
    public String toString() {
        return "Empleado: " + nombre + ", sueldo: " + sueldo;
    }
}

// Clase hijo (subclase)
public class Directivo extends Empleado {
    private double bonus;

    public Directivo(String nombre, double sueldo, double sueldoMaximo, double bonus) {
        super(nombre, sueldo, sueldoMaximo); // Llamada al constructor padre
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.toString() + ", bonus: " + bonus;
    }
}
```

---

## Interfaces

Una **interfaz** define un contrato que las clases deben cumplir.

```java
public interface CreableEstadisticas {
    void crearEstadisticas();
    String obtenerResumen();
}

public class Personaje implements CreableEstadisticas {
    @Override
    public void crearEstadisticas() {
        // implementación
    }

    @Override
    public String obtenerResumen() {
        return "Resumen del personaje";
    }
}
```

---

## Clases Abstractas

```java
public abstract class Figura {
    protected String color;

    public Figura(String color) {
        this.color = color;
    }

    // Método abstracto: debe ser implementado por subclases
    public abstract double calcularArea();

    // Método concreto: heredado tal cual
    public String getColor() {
        return color;
    }
}

public class Rectangulo extends Figura {
    private double base, altura;

    public Rectangulo(String color, double base, double altura) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}
```

---

## Excepciones

```java
// Crear excepción personalizada
public class PersonajeException extends Exception {
    public PersonajeException(String mensaje) {
        super(mensaje);
    }
}

// Lanzar y capturar excepciones
try {
    if (fuerza < 0 || fuerza > 20) {
        throw new PersonajeException("Fuerza inválida: debe estar entre 0 y 20");
    }
} catch (PersonajeException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("Bloque finally siempre se ejecuta");
}
```

---

## Código de Ejemplo: Clase Personaje

```java
public class Personaje {
    private String nombre;
    private String raza; // humano, elfo, enano, orco
    private int fuerza;       // 0-20
    private int inteligencia; // 0-20
    private int vidaMaxima;   // 0-100
    private int vidaActual;   // 0-vidaMaxima

    public Personaje(String nombre, String raza, int fuerza,
                     int inteligencia, int vidaMaxima) throws PersonajeException {
        this.nombre = nombre;
        this.raza = raza;
        setFuerza(fuerza);
        setInteligencia(inteligencia);
        setVidaMaxima(vidaMaxima);
        this.vidaActual = vidaMaxima;
    }

    public void setFuerza(int fuerza) throws PersonajeException {
        if (fuerza < 0 || fuerza > 20) {
            throw new PersonajeException("Fuerza invalida: debe estar entre 0 y 20");
        }
        this.fuerza = fuerza;
    }

    @Override
    public String toString() {
        return nombre + " [" + raza + "] fuerza=" + fuerza
               + " int=" + inteligencia
               + " vida=" + vidaActual + "/" + vidaMaxima;
    }
}
```

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema5/Tema5.1-Arrays-v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema5/Tema5.2-POOavanzada-v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema4.1/Tema4.3-Arrays-v2.pdf`

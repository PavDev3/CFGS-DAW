# Java Tema 4 — POO, Excepciones y Arrays

← [[Programacion]]

---

## Proyectos del Tema 4

Ubicación: `/Users/imac/CFGS-DAW/Java/Tema4/src/`

---

## Proyecto: Empresa (Herencia)

```java
// Clase base: Empleado
public class Empleado {
    private String nombre;
    private double sueldo;
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
```

### Jerarquía de la empresa

```
Empleado (base)
├── Directivo (extends Empleado)
├── Informatico (extends Empleado)
└── Operario (extends Empleado)
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/empresa/Empleado.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/empresa/Directivo.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/empresa/Informatico.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/empresa/Operario.java`

---

## Proyecto: Personajes (POO + Excepciones + Arrays)

### Clase Personaje con excepciones

```java
public class Personaje {
    private String nombre;
    private String raza;     // humano, elfo, enano, orco
    private int fuerza;      // 0-20
    private int inteligencia; // 0-20
    private int vidaMaxima;  // 0-100
    private int vidaActual;

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

Archivos relacionados:
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/personajes/Personaje.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/personajes/PersonajeException.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/personajes/Clerigo.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/personajes/Mago.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/personajes/mainMenuPersonajes.java`

---

## Proyecto: RentingCar (Herencia + Polimorfismo)

### Jerarquía de vehículos

```
Vehiculo (base)
├── Coche (extends Vehiculo)
├── Furgoneta (extends Vehiculo)
└── Microbus (extends Vehiculo)
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/rentingCar/Vehiculo.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/rentingCar/Coche.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/rentingCar/mainAlquiler.java`

---

## Proyecto: Saldo (Excepciones personalizadas)

```java
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/saldo/Cuenta.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/saldo/SaldoInsuficienteException.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/saldo/Menu.java`

---

## Proyecto: ArrayAvanzada (Arrays de objetos)

Gestión de alumnos y asignaturas mediante arrays.

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/ArrayAvanzada/Alumno.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/ArrayAvanzada/Asignatura.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/ArrayAvanzada/mainArrayAvanzada.java`

---

## Proyecto: Edificios (Clases abstractas + Interfaces)

```
InstalacionDeportiva (abstracta)
└── Polideportivo (extends InstalacionDeportiva)
```

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/edificios/InstalacionDeportiva.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/edificios/Polideportivo.java`

---

## Examen de Marzo — Tienda

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/examenMar/ExamenTema4.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/examenMar/MainTienda.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/examenMar/Producto.java`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/Tema4/src/examenMar/ProductoInvalidoException.java`

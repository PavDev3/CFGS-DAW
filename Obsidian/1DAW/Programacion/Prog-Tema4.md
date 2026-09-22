# Tema 4 — Cadenas de Caracteres y POO Básica

← [[Prog-Tema3]] | [[Programacion]] | Siguiente: [[Prog-Tema5]]

---

## La clase String en Java

En Java, las cadenas de texto son objetos de la clase `String`.

### Métodos más importantes de String

| Método | Descripción | Ejemplo |
|--------|-------------|---------|
| `length()` | Longitud de la cadena | `"Hola".length()` → 4 |
| `charAt(i)` | Carácter en posición i | `"Hola".charAt(0)` → 'H' |
| `substring(i,j)` | Subcadena de i a j | `"Hola".substring(0,2)` → "Ho" |
| `indexOf(s)` | Posición de subcadena | `"Hola".indexOf("la")` → 2 |
| `contains(s)` | ¿Contiene subcadena? | `"Hola".contains("ol")` → true |
| `toUpperCase()` | Convierte a mayúsculas | `"hola".toUpperCase()` → "HOLA" |
| `toLowerCase()` | Convierte a minúsculas | `"HOLA".toLowerCase()` → "hola" |
| `trim()` | Elimina espacios inicio/fin | `"  hola  ".trim()` → "hola" |
| `replace(a,b)` | Reemplaza caracteres | `"Hola".replace('l','r')` → "Hora" |
| `split(s)` | Divide la cadena | `"a,b,c".split(",")` → ["a","b","c"] |
| `equals(s)` | Comparación de igualdad | `"hola".equals("hola")` → true |
| `compareTo(s)` | Comparación lexicográfica | devuelve int |
| `startsWith(s)` | ¿Empieza por s? | `"Hola".startsWith("Ho")` → true |
| `endsWith(s)` | ¿Termina en s? | `"Hola".endsWith("la")` → true |
| `isEmpty()` | ¿Está vacía? | `"".isEmpty()` → true |

---

## POO Básica — Clases y Objetos

### Conceptos fundamentales

| Concepto | Descripción |
|----------|-------------|
| **Clase** | Plantilla que define atributos y comportamientos |
| **Objeto** | Instancia de una clase |
| **Atributo** | Variable que pertenece a la clase |
| **Método** | Función que pertenece a la clase |
| **Constructor** | Método especial para crear objetos |

### Estructura de una clase

```java
public class Persona {
    // Atributos (variables de instancia)
    private String nombre;
    private int edad;

    // Constructor
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    // toString
    @Override
    public String toString() {
        return "Persona: " + nombre + ", edad: " + edad;
    }
}
```

### Uso de la clase

```java
Persona p = new Persona("Pablo", 20);
System.out.println(p.getNombre()); // Pablo
System.out.println(p);             // Persona: Pablo, edad: 20
```

---

## Encapsulamiento

El encapsulamiento protege los datos mediante modificadores de acceso:

| Modificador | Acceso |
|-------------|--------|
| `private` | Solo dentro de la clase |
| `protected` | La clase, subclases y mismo paquete |
| `public` | Todos |
| (sin modificador) | Solo el mismo paquete |

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema4/Tema3.2-Cadenas-v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema4.1/Tema4.1-POO-v2.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Programacion/Tema4.1/Tema4.2-Excepciones-v2.pdf`

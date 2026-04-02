# Java — Ejercicios 1er Trimestre

← [[Programacion]]

---

## Ejercicios básicos

Ubicación: `/Users/imac/CFGS-DAW/Java/1Trimestre/`

### Ejercicios principales

| Archivo | Descripción |
|---------|-------------|
| `amstrong.java` | Números de Armstrong |
| `fibonacci.java` | Sucesión de Fibonacci |
| `fibonacciFunc.java` | Fibonacci con funciones |
| `billetes.java` | Cambio de billetes |
| `cajafuerte.java` | Simulación caja fuerte |
| `calculaPotencia.java` | Cálculo de potencias |
| `cifras.java` | Manejo de cifras de un número |
| `contador.java` | Contador básico |
| `piramide.java` | Dibujar pirámide con asteriscos |
| `rombo.java` | Dibujar rombo con asteriscos |
| `triangulo.java` | Dibujar triángulo |
| `rectangulo.java` | Dibujar rectángulo |
| `secretNumber.java` | Juego del número secreto |
| `secretNumberFunc.java` | Número secreto con funciones |
| `sumaNumeros.java` | Suma de números |
| `ordenMenorMayor.java` | Ordenar de menor a mayor |
| `mayorMenorFunc.java` | Mayor y menor con funciones |
| `primosFunc.java` | Números primos con funciones |

---

## Relación 4 — Ejercicios de Cadenas (LaC)

Ubicación: `/Users/imac/CFGS-DAW/Java/1Trimestre/Relacion_4/LaC/`

### ejercicio1_contarCaracter.java

```java
package Relacion_4.LaC;

import java.util.Scanner;

public class ejercicio1_contarCaracter {
    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Introduce una cadena de texto: ");
        String cadena = teclado.nextLine();
        System.out.println("Introduce un carácter: ");
        String entrada = teclado.nextLine();
        char caracter = entrada.charAt(0);

        String cadenaMin = convertirAMinuscula(cadena);
        char caracterMin = aMinuscula(caracter);

        int contador = contarCaracter(cadenaMin, caracterMin);
        System.out.println("El carácter '" + caracter + "' aparece "
            + contador + " veces en \"" + cadena + "\"");
    }

    public static int contarCaracter(String cadena, char caracter) {
        int contador = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                contador++;
            }
        }
        return contador;
    }

    public static char aMinuscula(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            if (c >= 'A' && c <= 'Z') {
                int diferencia = 'a' - 'A';
                return (char)(c + diferencia);
            }
        }
        return c;
    }

    public static String convertirAMinuscula(String s) {
        String resultado = "";
        for (int i = 0; i < s.length(); i++) {
            resultado += aMinuscula(s.charAt(i));
        }
        return resultado;
    }
}
```

### Lista completa de ejercicios de cadenas

| Archivo | Descripción |
|---------|-------------|
| `ejercicio1_contarCaracter.java` | Contar ocurrencias de un carácter |
| `ejercicio2_contarMinusMayusNum.java` | Contar minúsculas, mayúsculas y números |
| `ejercicio3_palindromo.java` | Verificar si es palíndromo |
| `ejercicio4_palabraEscondida.java` | Buscar palabra escondida |
| `ejercicio5_reemplazarPalabra.java` | Reemplazar palabras |
| `ejercicio6_contarVocalesDiferentes.java` | Contar vocales distintas |
| `ejercicio7_ordenarConsYVocales.java` | Ordenar consonantes y vocales |
| `ejercicio8_validarLogin.java` | Validar login de usuario |
| `ejercicio9_sumarNumeros.java` | Sumar números de una cadena |
| `ejercicio10_validarWebs.java` | Validar URLs |
| `ejercicio11_ahorcado.java` | Juego del ahorcado |
| `ejercicio12_validarCorreo.java` | Validar dirección de email |

---

## Archivos comprimidos (exámenes y simulacros)

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/1Trimestre/Nunez_Fernandez_Pablo_Examen_Tema_2.zip`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/1Trimestre/Relacion_4/Nunez_Fernandez_Pablo_Examen_Tema3.zip`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Java/1Trimestre/Relacion_4/Nunez_Fernandez_Pablo_Simulacro.zip`

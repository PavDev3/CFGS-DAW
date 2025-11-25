package Relacion_4;

import java.util.Scanner;
public class sumarNum {
    public static void main(String[] args) {
        String frase = pedirFrase();
        sumarNum(frase);
    }

    private static String pedirFrase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una frase: ");
        String frase = scanner.nextLine();
        scanner.close();
        return frase;
    }

    private static void sumarNum(String frase) {
        // Variable para almacenar la suma total
        int suma = 0;
        // Variable para construir el número actual mientras leemos dígitos consecutivos
        String numeroActual = "";
        
        // Recorrer cada carácter de la frase
        for (int i = 0; i < frase.length(); i++) {
            char caracter = frase.charAt(i);
            
            // Si el carácter es un dígito, añadirlo al número actual
            if (Character.isDigit(caracter)) {
                // Añadir el dígito al número actual
                numeroActual += caracter;
            } else {
                // Si no es un dígito y tenemos un número construido, sumarlo
                if (!numeroActual.isEmpty()) {
                    // Convertir el número actual a entero y sumarlo a la suma total
                    suma += Integer.parseInt(numeroActual);
                    // Reiniciar el número actual para el siguiente número
                    numeroActual = ""; 
                }
            }
        }
        
        // Si al final de la frase queda un número sin procesar, sumarlo también
        if (!numeroActual.isEmpty()) {
            suma += Integer.parseInt(numeroActual);
        }
        
        // Mostrar el resultado
        System.out.println("La suma de los números es: " + suma);
    }
}

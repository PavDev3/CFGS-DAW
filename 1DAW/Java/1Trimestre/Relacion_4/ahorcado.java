package Relacion_4;

import java.util.Scanner;

public class ahorcado {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String palabra = pedirPalabra(scanner);        
        String palabraOculta = ocultarPalabra(palabra);
        System.out.println(palabraOculta);
        jugar(scanner, palabra, palabraOculta);
        scanner.close();
    }

    private static String pedirPalabra(Scanner scanner) {
        System.out.println("Introduce una palabra: ");
        String palabra = scanner.nextLine();
        // Convertir a minúsculas para facilitar las comparaciones
        palabra = palabra.toLowerCase();
        return palabra;
    }

    private static String ocultarPalabra(String palabra) {
        String palabraOculta = "";
        // Recorrer cada posición de la palabra y añadir un asterisco
        for (int i = 0; i < palabra.length(); i++) {
            palabraOculta += "*";
        } for (int i = 30 ; i > 0 ; i--) {
            System.out.println("");
        }
        return palabraOculta;
    }
    
    private static void jugar(Scanner scanner, String palabra, String palabraOculta) {
        // Inicializar el contador de intentos a 7
        int intentos = 7;
        
        // El juego continúa mientras haya intentos y queden letras por descubrir (asteriscos)
        while (intentos > 0 && palabraOculta.contains("*")) {
            // Pedir al jugador que introduzca una letra
            System.out.println("Introduce una letra: ");
            String letra = scanner.nextLine().toLowerCase();
            // Variable para saber si la letra introducida está en la palabra
            boolean letraEncontrada = false;
            // Variable para construir la nueva palabra oculta con las letras descubiertas
            String nuevaPalabraOculta = "";
            // Recorrer cada posición de la palabra original
            for (int i = 0; i < palabra.length(); i++) {
                // Si la letra en esta posición coincide con la letra introducida
                if (palabra.charAt(i) == letra.charAt(0)) {
                    // Revelar la letra en esta posición
                    nuevaPalabraOculta += letra.charAt(0);
                    // Marcar que se encontró la letra
                    letraEncontrada = true;
                } else {
                    // Si no coincide, mantener lo que había antes (letra revelada o asterisco)
                    nuevaPalabraOculta += palabraOculta.charAt(i);
                }
            }
            
            // Si se encontró la letra en la palabra
            if (letraEncontrada) {
                // Actualizar la palabra oculta con las nuevas letras reveladas
                palabraOculta = nuevaPalabraOculta;
                System.out.println("Letra encontrada!");
            } else {
                // Si no se encontró, restar un intento
                intentos--;
                System.out.println("Letra no encontrada");
            }
            
            // Mostrar el estado actual del juego
            System.out.println("Palabra: " + palabraOculta);
            System.out.println("Intentos restantes: " + intentos);
            System.out.println(); // Línea en blanco para separar turnos
        }
        
        // Al salir del bucle, verificar si el jugador ganó o perdió
        // Si no quedan asteriscos, significa que se adivinó toda la palabra
        if (!palabraOculta.contains("*")) {
            System.out.println("¡Has ganado! La palabra era: " + palabra);
        } else {
            // Si aún quedan asteriscos pero se acabaron los intentos, perdió
            System.out.println("Has perdido. La palabra era: " + palabra);
        }
    }

}

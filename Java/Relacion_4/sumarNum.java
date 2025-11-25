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
        int suma = 0;
        for (int i = 0; i < frase.length(); i++) {
            // Comprobar si el caracter es un número
            if (Character.isDigit(frase.charAt(i))) {
                // Creo variable num que guarda el valor del caracter en entero el - '0' es para convertir el caracter a entero
                int num = frase.charAt(i) - '0';
                suma += num;
            }
        }
        System.out.println("La suma de los números es: " + suma);
    }
}

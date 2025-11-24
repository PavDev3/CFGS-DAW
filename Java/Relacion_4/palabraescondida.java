package Relacion_4;

import java.util.Scanner;

public class palabraescondida {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String frase = pedirFrase(scanner);
        String palabra = pedirPalabra(scanner);
        scanner.close();
        encontrada(frase, palabra);
    }

    private static String pedirFrase(Scanner scanner) {
        System.out.println("Introduce una frase: ");
        String frase = scanner.nextLine().toLowerCase();
        return frase;
    }

    private static String pedirPalabra(Scanner scanner) {
        System.out.println("Introduce una palabra: ");
        String palabra = scanner.nextLine().toLowerCase();
        return palabra;
    }

    private static void encontrada(String frase, String palabra) {
        int contador = 0;
        boolean encontrada = false;
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == palabra.charAt(0)) {
                contador++;
                encontrada = true;
            }
        }
        if (encontrada) {
            System.out.println("Encontrada");
        } else {
            System.out.println("No se encuentra");
        }
    }
}



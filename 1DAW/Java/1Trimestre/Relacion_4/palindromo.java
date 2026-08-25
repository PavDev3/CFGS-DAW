package Relacion_4;

import java.util.Scanner;

public class palindromo {
    public static void main(String[] args) {
        String palabra = pedirPalabra();
        if (esPalindromo(palabra)) {
            System.out.println("La palabra es un palindromo");
        } else {
            System.out.println("La palabra no es un palindromo");
        }
    }

    public static String pedirPalabra() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una palabra: ");
        String palabra = scanner.nextLine().toLowerCase();
        scanner.close();
        return palabra;
    }

    public static boolean esPalindromo(String palabra) {
        if (palabra.equals(new StringBuilder(palabra).reverse().toString())) {
            return true;
        } 
        return false;
    }
}

package Relacion_4;

import java.util.Scanner;

public class string4 {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Introduce una cadena de texto: ");
        String str1 = scanner.nextLine().toLowerCase();
        System.out.println("Introduce un caracter: ");
        char caracter = scanner.next().toLowerCase().charAt(0);
        
        
        contarCaracter(str1, caracter);
        scanner.close();
    }

    public static void contarCaracter(String str1, char caracter) {
        int contador = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == caracter) {
                contador++;
            }
        }
        System.out.println("El carácter '" + caracter + "' aparece " + contador + " veces en \"" + str1 + "\"");
    }
}

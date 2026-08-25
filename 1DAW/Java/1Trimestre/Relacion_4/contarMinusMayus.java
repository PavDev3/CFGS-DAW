package Relacion_4;
import java.util.Scanner;

public class contarMinusMayus {
    public static void main(String[] args) {
        String str1 = pedirCadena();
        contarMinus(str1);
        contarMayus(str1);
        contarNum(str1);
    }

    private static String pedirCadena() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cadena de texto: ");
        String str1 = scanner.nextLine();
        scanner.close();
        return str1;
    }

    private static void contarMinus (String str1) {
        int minusculas = 0;
        for ( int i = 0; i < str1.length(); i++) {
            if (Character.isLowerCase(str1.charAt(i))) {
                minusculas++;
            }
        }
        System.out.println("La cadena de texto tiene " + minusculas + " minusculas");
    }

    private static void contarMayus (String str1) {
        int mayusculas = 0;
        for ( int i = 0; i < str1.length(); i++) {
            if (Character.isUpperCase(str1.charAt(i))) {
                mayusculas++;
            }
        }
        System.out.println("La cadena de texto tiene " + mayusculas + " mayusculas");
    }

    private static void contarNum (String str1) {
        int numeros = 0;
        for ( int i = 0; i < str1.length(); i++) {
            if (Character.isDigit(str1.charAt(i))) {
                numeros++;
            }
        }
        System.out.println("La cadena de texto tiene " + numeros + " numeros");
    }

}
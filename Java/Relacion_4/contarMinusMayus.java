package Relacion_4;
import java.util.Scanner;

public class contarMinusMayus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce una cadena de texto: ");
        String str1 = scanner.nextLine();
        contador(str1);
        scanner.close();
    }

    public static void contador(String str1) {
        int minusculas = 0;
        int mayusculas = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') {
                minusculas++;
            } else if (str1.charAt(i) >= 'A' && str1.charAt(i) <= 'Z') {
                mayusculas++;
            }
        }
        System.out.println("La cadena de texto tiene " + minusculas + " minusculas y " + mayusculas + " mayusculas");
    }
}
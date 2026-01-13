
import java.util.Scanner;

public class calculaPotencia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce la base: ");
        int base = scanner.nextInt();
        System.out.println("Introduce el exponente: ");
        int exponente = scanner.nextInt();
        int resultado = 1;
        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }
        
    System.out.println("El resultado es: " + resultado);
    scanner.close();
}
}


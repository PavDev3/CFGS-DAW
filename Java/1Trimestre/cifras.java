
import java.util.Scanner;

public class cifras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        int numero = scanner.nextInt();
        if (numero < 0) {
            numero = -numero;
        }
        int cifras = 0;
        while (numero > 0) {
            numero = numero / 10;
            cifras++;
        }
        System.out.println("El número tiene " + cifras + " cifras");
        scanner.close();
    }
}

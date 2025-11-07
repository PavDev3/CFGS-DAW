
import java.util.Scanner;

public class cifrasFunc {
    public static void main(String[] args) {
        int numero = introdudir();
        int cifras = cifras(numero);
        System.out.println("El número tiene " + cifras + " cifras");

    }

    public static int introdudir() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        int numero = scanner.nextInt();
        return numero ;

    }

    private static int cifras(int numero){
        if (numero < 0) {
            numero = -numero;
        }
        int cifras = 0;
        while (numero > 0) {
            numero = numero / 10;
            cifras++;
        }
        return cifras;
    }
}
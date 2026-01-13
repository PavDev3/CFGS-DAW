import java.util.Scanner;

public class contador {
    public static void main(String[] args) {
        int numero;
        int cifras = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un número: ");
        numero = scanner.nextInt();
        //contar cifras de un numero;
        while (numero > 0) {
            numero = numero / 10;
            cifras++;
        }
        System.out.println("El número tiene " + cifras + " cifras");
        scanner.close();
    }
}

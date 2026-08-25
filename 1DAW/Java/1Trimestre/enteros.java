
import java.util.Scanner;

public class enteros {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numero1,numero2;
        System.out.print("Ingrese un número entero: ");
        numero1 = sc.nextInt();
        if (numero1 <= 0) {
            System.out.println("El número debe ser positivo o mayor de 0.");
        }

        System.out.print("Ingrese otro número entero: ");
        numero2 = sc.nextInt();
        if (numero2 <= 0) {
            System.out.println("El número debe ser positivo o mayor de 0."); 
        }

        // Euclides algoritmo
        while (numero2 != 0) {
            int temp = numero2;
            numero2 = numero1 % numero2;
            numero1 = temp;
        }
        System.out.println("El máximo común divisor es: " + numero1);


        sc.close(); 
    }
    
}

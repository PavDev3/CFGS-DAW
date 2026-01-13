import java.util.*;

public class exam_esPrimo {
    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in); {
            int numero;
            // Solicitar número entero positivo
            System.out.println("Introduce un número entero positivo: ");
            numero = scanner.nextInt();
            
            // Comprobamos si el numero es positivo
            while (numero < 0) {
                System.out.print("El número debe ser positivo. Introduce un número entero positivo: ");
                numero = scanner.nextInt();
            }
            // Si introduce 0, hasta luego masquinaaasss
            if (numero == 0) {
                System.out.println("Venga máquina, hasta luego");
                return;
            }
            // Imprimir todos los numeros primos desde del numero introducido hasta el 2
            System.out.println("Números primos desde " + numero + " :");
            // Bucle que recorre desde el número hasta 2
            for (int i = numero; i >= 2; i--) {
                boolean esPrimo = true;
                // Verificar si es primo con verdadero o farso
                if (i < 2) {
                    esPrimo = false;
                } else if (i == 2) {
                    esPrimo = true;
                } else if (i % 2 == 0) {
                    esPrimo = false;
                } 
                if (esPrimo) {
                    System.out.print(i + " ");
                }
            }
         }
         scanner.close();
    }
}

import java.util.*;

public class primosFunc {
    public static void main(String[] args) {
        int num = pedirNumero();

        // Mostrar números primos hasta num
        System.out.println("Números primos hasta " + num + ":");
        // Este bucle llama a esPrimo cuantas veces sea necesario
        for (int i = 2; i <= num; i++) {
            if (esPrimo(i)) {
                System.out.println(i + " es primo");
            }
        }
    }

    
    // Pide un número positivo al usuario
    public static int pedirNumero() {
        Scanner scanner = new Scanner(System.in);
        int numero;
        System.out.print("Introduce un número: ");
        numero = scanner.nextInt();
        // Validar que el número sea positivo
        while (numero <= 0) {
            System.out.println("El número debe ser positivo y mayor que 0");
            System.out.print("Introduce un número: ");
            numero = scanner.nextInt();
        }

        return numero;
    }
	// Función que determina si un número es primo que devuelve true o false
    private static boolean esPrimo(int numero) {
        if (numero < 2) {
            return false;
        }

        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false; 
            }
        }

        return true; // si no entró en el if, es primo
    }
}

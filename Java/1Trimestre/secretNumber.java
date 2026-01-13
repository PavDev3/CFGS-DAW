

import java.util.*;

public class secretNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;// Numero random del 1 al 100
        int intentos = 0;
        int maxIntentos = 5;
        
        System.out.println("Tienes " + maxIntentos + " intentos para adivinar el número (entre 1 y 100)");
        
        while (intentos < maxIntentos) {
            System.out.print("Introduce un número: ");
            int numero = scanner.nextInt();
            intentos++;
            
            // Validar que el numero este en rango 
            if (numero < 1 || numero > 100) {
                System.out.println("El número debe estar entre 1 y 100");
                System.out.println("Intentos restantes: " + (maxIntentos - intentos));
                continue;
            }
            
            // Verificar numero secreto
            if (numero == secretNumber) {
                System.out.println("Has adivinado el número en " + intentos + " intentos");
                scanner.close();
                return;
            }
            
            //Mayor o menor
            if (numero > secretNumber) {
                System.out.println("El número secreto es menor");
            } else {
                System.out.println("El número secreto es mayor");
            }
            
            System.out.println("Intentos restantes: " + (maxIntentos - intentos));
        }
        
        System.out.println("El numero secreto era: " + secretNumber);
        scanner.close();
    }
}
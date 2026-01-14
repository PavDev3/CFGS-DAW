package Relacion_4;
import java.util.Scanner;
public class rango4 {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Prueba 1: Rango normal
        int limiteInferior = solicitarRango(1, 100);
        int limiteSuperior = solicitarRango(limiteInferior, 100);
        System.out.println("Numero inicial: " + limiteInferior);
        System.out.println("Numero final: " + limiteSuperior);

        int numero = soliticarNumeroEnRango(limiteInferior, limiteSuperior);
        System.out.println("Numero: " + numero);
    }
    
    public static int solicitarRango(int limiteInferior, int limiteSuperior) {
        // Si el limite inferior es mayor al superior, los intercambia
        if (limiteInferior > limiteSuperior) {
            int temp = limiteInferior;
            limiteInferior = limiteSuperior;
            limiteSuperior = temp;
        }
        int numero;
        boolean numeroValido = false;
        // Bucle para solicitar un numero entre el limite inferior y el superior
        do {
            System.out.print("Introduce un numero entre " + limiteInferior + " y " + limiteSuperior + ": ");
            numero = scanner.nextInt();
            // Si el numero está entre el limite inferior y el superior, es valido
            if (numero > limiteInferior && numero < limiteSuperior) {
                numeroValido = true;
            } else {
                System.out.println("El numero " + numero + " no esta en el rango [" + limiteInferior + ", " + limiteSuperior + "]. Intentalo de nuevo.");
            }
        } while (!numeroValido);
        // Devuelve el numero
        return numero;    
    }
    
    public static int soliticarNumeroEnRango(int limiteInferior, int limiteSuperior) {
        int numero;
        boolean numeroValido = false;
        do {
            System.out.print("Introduce un numero entre " + limiteInferior + " y " + limiteSuperior + ": ");
            numero = scanner.nextInt();
            if (numero > limiteInferior && numero < limiteSuperior) {
                numeroValido = true;
            } else {
                System.out.println("El numero " + numero + " no esta en el rango [" + limiteInferior + ", " + limiteSuperior + "]. Intentalo de nuevo.");
            }
        } while (!numeroValido);
        return numero;
    }
}

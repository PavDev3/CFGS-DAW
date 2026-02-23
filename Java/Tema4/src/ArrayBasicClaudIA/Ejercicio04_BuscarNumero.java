package ArrayBasicClaudIA;
import java.util.Scanner;

/**
 * EJERCICIO 4 - NIVEL 1: Buscar un número
 * Dado un array de enteros, pide un número por teclado y:
 * - Indica si está en el array
 * - Indica en qué posición aparece (si aparece)
 */
public class Ejercicio04_BuscarNumero {
    public static void main(String[] args) {
        int[] numeros = {12, 45, 7, 23, 56, 89, 34};
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Array: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.print("Introduce un número a buscar: ");
        int buscar = sc.nextInt();
        
        boolean encontrado = false;
        int posicion = -1;
        
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscar) {
                encontrado = true;
                posicion = i;
                break;
            }
        }
        
        if (encontrado) {
            System.out.println("El número " + buscar + " está en el array en la posición " + posicion);
        } else {
            System.out.println("El número " + buscar + " NO está en el array");
        }
        
        sc.close();
    }
}
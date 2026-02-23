package ArrayBasicClaudIA;
import java.util.Arrays;

/**
 * EJERCICIO 9 - NIVEL 3: Comparar arrays
 * Crea dos arrays de enteros y:
 * - Comprueba si son iguales usando equals
 * - Comprueba si son iguales usando Arrays.equals
 * - Explica la diferencia
 */
public class Ejercicio09_CompararArrays {
    public static void main(String[] args) {
        int[] array1 = {1, 2, 3, 4, 5};
        int[] array2 = {1, 2, 3, 4, 5};
        
        System.out.println("Array 1: " + Arrays.toString(array1));
        System.out.println("Array 2: " + Arrays.toString(array2));
        
        // Comparar con equals (método de Object)
        boolean igualesEquals = array1.equals(array2);
        System.out.println("\nUsando equals(): " + igualesEquals);
        
        // Comparar con Arrays.equals
        boolean igualesArraysEquals = Arrays.equals(array1, array2);
        System.out.println("Usando Arrays.equals(): " + igualesArraysEquals);
        
        /*
         * EXPLICACIÓN DE LA DIFERENCIA:
         * 
         * - equals(): Compara las REFERENCIAS de los objetos (direcciones de memoria).
         *   Como array1 y array2 son objetos diferentes en memoria, devuelve false.
         * 
         * - Arrays.equals(): Compara el CONTENIDO de los arrays elemento a elemento.
         *   Como ambos arrays tienen los mismos valores en el mismo orden, devuelve true.
         */
    }
}
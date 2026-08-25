package ArrayBasicClaudIA;
import java.util.Arrays;

/**
 * EJERCICIO 11 - NIVEL 4: Método que modifica el array
 * Crea un método:
 * public static void multiplicarPorDos(int[] array)
 * Que multiplique todos los valores por 2.
 * Comprueba que el array original cambia.
 */
public class Ejercicio11_ModificarArray {
    
    public static void multiplicarPorDos(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= 2;
        }
    }
    
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        
        System.out.println("Antes de llamar al método:");
        System.out.println("Array: " + Arrays.toString(numeros));
        
        multiplicarPorDos(numeros);
        
        System.out.println("\nDespués de llamar al método:");
        System.out.println("Array: " + Arrays.toString(numeros));
        
        /*
         * EXPLICACIÓN:
         * 
         * El array original SÍ cambia porque en Java los arrays se pasan
         * por referencia. El método recibe la dirección de memoria del array,
         * por lo que cualquier modificación afecta al array original.
         */
    }
}
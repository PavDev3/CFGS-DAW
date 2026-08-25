package ArrayBasicClaudIA;
import java.util.Arrays;

/**
 * EJERCICIO 12 - NIVEL 4: Método que NO modifica
 * Crea un método que recorra el array con foreach y multiplique cada elemento por 2.
 * ¿Cambia el array?
 * Explica por qué.
 */
public class Ejercicio12_NoModifica {
    
    public static void multiplicarPorDosForeach(int[] array) {
        System.out.println("Dentro del método (intentando modificar con foreach):");
        for (int num : array) {
            num *= 2; // Esto NO modifica el array
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        
        System.out.println("Antes de llamar al método:");
        System.out.println("Array: " + Arrays.toString(numeros));
        
        multiplicarPorDosForeach(numeros);
        
        System.out.println("\nDespués de llamar al método:");
        System.out.println("Array: " + Arrays.toString(numeros));
        
        /*
         * ¿Cambia el array? NO
         * 
         * EXPLICACIÓN:
         * 
         * En el foreach, la variable 'num' es una COPIA del valor de cada elemento,
         * no una referencia al elemento original. Cuando hacemos 'num *= 2',
         * estamos modificando la variable local 'num', no el elemento del array.
         * 
         * El foreach es de "solo lectura" respecto a la modificación de elementos.
         * Para modificar un array, necesitamos usar un for tradicional con índices.
         */
    }
}
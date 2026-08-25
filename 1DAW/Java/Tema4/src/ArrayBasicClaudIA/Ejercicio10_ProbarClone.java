package ArrayBasicClaudIA;
import java.util.Arrays;

/**
 * EJERCICIO 10 - NIVEL 3: Probar clone()
 * Crea un array, clónalo y modifica el original.
 * Muestra ambos arrays y explica qué ocurre.
 */
public class Ejercicio10_ProbarClone {
    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};
        
        // Clonar el array
        int[] clonado = original.clone();
        
        System.out.println("Antes de modificar:");
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Clonado:  " + Arrays.toString(clonado));
        
        // Modificar el original
        original[0] = 999;
        original[2] = 777;
        
        System.out.println("\nDespués de modificar original[0]=999 y original[2]=777:");
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Clonado:  " + Arrays.toString(clonado));
        
        /*
         * EXPLICACIÓN:
         * 
         * El método clone() crea una COPIA INDEPENDIENTE del array.
         * Cuando modificamos el array original, el clonado NO se ve afectado.
         * Esto ocurre porque los arrays de tipos primitivos se copian por valor.
         * 
         * Cada array tiene su propia zona de memoria independiente.
         */
    }
}
package ArrayBasicClaudIA;
/**
 * EJERCICIO 8 - NIVEL 2: Copiar un array manualmente
 * Sin usar clone() ni Arrays.copyOf(), copia un array en otro usando un for.
 */
public class Ejercicio08_CopiarArray {
    public static void main(String[] args) {
        int[] original = {10, 20, 30, 40, 50};
        int[] copia = new int[original.length];
        
        // Copiar manualmente con for
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        
        System.out.println("Array original:");
        for (int num : original) {
            System.out.print(num + " ");
        }
        
        System.out.println("\nArray copia:");
        for (int num : copia) {
            System.out.print(num + " ");
        }
        
        // Verificar que son copias independientes
        original[0] = 999;
        System.out.println("\n\nTras modificar original[0] = 999:");
        System.out.println("Original: " + original[0]);
        System.out.println("Copia: " + copia[0]);
    }
}
package ArrayBasicClaudIA;
/**
 * EJERCICIO 7 - NIVEL 2: Invertir el array
 * Dado:
 * int[] numeros = {1,2,3,4,5};
 * Crea otro array con el orden invertido.
 * Resultado esperado: 5 4 3 2 1
 */
public class Ejercicio07_InvertirArray {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        int[] invertido = new int[numeros.length];
        
        // Invertir el array
        for (int i = 0; i < numeros.length; i++) {
            invertido[i] = numeros[numeros.length - 1 - i];
        }
        
        System.out.println("Array original:");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        
        System.out.println("\nArray invertido:");
        for (int num : invertido) {
            System.out.print(num + " ");
        }
    }
}
package ArrayBasicClaudIA;
/**
 * EJERCICIO 3 - NIVEL 1: Contar números positivos
 * Dado el array:
 * int[] numeros = {4, -3, 7, 0, -2, 8};
 * Cuenta cuántos números son positivos.
 */
public class Ejercicio03_ContarPositivos {
    public static void main(String[] args) {
        int[] numeros = {4, -3, 7, 0, -2, 8};
        
        int positivos = 0;
        for (int num : numeros) {
            if (num > 0) {
                positivos++;
            }
        }
        
        System.out.println("Array: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Cantidad de números positivos: " + positivos);
    }
}
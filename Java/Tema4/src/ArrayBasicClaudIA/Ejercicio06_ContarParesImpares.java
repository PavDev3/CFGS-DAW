package ArrayBasicClaudIA;
/**
 * EJERCICIO 6 - NIVEL 2: Contar pares e impares
 * Dado un array de enteros, cuenta cuántos son pares y cuántos impares.
 */
public class Ejercicio06_ContarParesImpares {
    public static void main(String[] args) {
        int[] numeros = {12, 7, 23, 44, 56, 89, 34, 67, 78, 91};
        
        int pares = 0;
        int impares = 0;
        
        for (int num : numeros) {
            if (num % 2 == 0) {
                pares++;
            } else {
                impares++;
            }
        }
        
        System.out.println("Array: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Cantidad de pares: " + pares);
        System.out.println("Cantidad de impares: " + impares);
    }
}
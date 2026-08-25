package ArrayBasicClaudIA;
/**
 * EJERCICIO 5 - NIVEL 2: Encontrar el mayor
 * Crea un array de 10 enteros y muestra cuál es el número mayor.
 */
public class Ejercicio05_EncontrarMayor {
    public static void main(String[] args) {
        int[] numeros = {23, 56, 12, 89, 34, 67, 45, 78, 90, 11};
        
        int mayor = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
        }
        
        System.out.println("Array: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("El número mayor es: " + mayor);
    }
}
package ArrayBasicClaudIA;
/**
 * EJERCICIO 1 - NIVEL 1: Crear y mostrar un array
 * Crea un array de 5 números enteros.
 * Asigna valores manualmente y muéstralos por pantalla usando:
 * a) for tradicional
 * b) foreach
 */
public class Ejercicio01_CrearMostrarArray {
    public static void main(String[] args) {
        // Crear array de 5 números enteros
        int[] numeros = new int[5];
        
        // Asignar valores manualmente
        numeros[0] = 10;
        numeros[1] = 25;
        numeros[2] = 33;
        numeros[3] = 47;
        numeros[4] = 52;
        
        // Mostrar con for tradicional
        System.out.println("a) Con for tradicional:");
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("numeros[" + i + "] = " + numeros[i]);
        }
        
        // Mostrar con foreach
        System.out.println("\nb) Con foreach:");
        for (int num : numeros) {
            System.out.println("Valor: " + num);
        }
    }
}
package ArrayBasicClaudIA;
/**
 * EJERCICIO 2 - NIVEL 1: Suma de elementos
 * Crea un array de 6 números reales.
 * Calcula y muestra:
 * - La suma total
 * - La media
 */
public class Ejercicio02_SumaElementos {
    public static void main(String[] args) {
        // Crear array de 6 números reales
        double[] numeros = {12.5, 23.8, 7.2, 45.0, 3.5, 18.9};
        
        // Calcular suma
        double suma = 0;
        for (double num : numeros) {
            suma += num;
        }
        
        // Calcular media
        double media = suma / numeros.length;
        
        // Mostrar resultados
        System.out.println("Array: ");
        for (double num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Suma total: " + suma);
        System.out.println("Media: " + media);
    }
}
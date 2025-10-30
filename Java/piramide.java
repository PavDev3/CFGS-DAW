
import java.util.Scanner;

public class piramide {
    // Método principal del programa
    public static void main(String[] args) {
        int niveles; // Declarar variable para almacenar el número de niveles de la pirámide
        int espacios;        // Declarar variable para controlar los espacios en blanco de cada fila
        int i, j, k;        // Declarar variables de control para los bucles (i=nivel actual, j=espacios, k=números)
        int contador = 2;        // Inicializar contador en 2 (primer múltiplo de 2)
        

        Scanner scanner = new Scanner(System.in);        // Crear objeto Scanner para leer entrada del teclado
        System.out.println("Introduce el número de niveles de la pirámide: ");        // Pedimos el nivel
        niveles = scanner.nextInt();        // Guardamos los niveles
        

        if (niveles > 0) { // Verificar que el número de niveles sea positivo
            espacios = niveles - 1;// Calcular espacios iniciales: el primer nivel necesita (niveles-1) espacios
            for (i = 1; i <= niveles; i++) {// Bucle principal: recorre cada nivel de la pirámide (desde 1 hasta niveles)
                for (j = 1; j <= espacios; j++) {// Bucle para imprimir espacios en blanco antes de los números
                    System.out.print("  "); // Imprimir dos espacios en blanco para centrar la fila
                }
              
                // Bucle para imprimir los múltiplos de 2 en la fila actual
                for (k = 1; k <= (2 * i - 1); k++) { // Cada nivel tiene (2*i - 1) números: nivel 1=1, nivel 2=3, nivel 3=5, etc.
                    System.out.print(contador + " ");// Imprimir el múltiplo de 2 actual seguido de un espacio
                    contador += 2;// Incrementar contador en 2 para obtener el siguiente múltiplo de 2
                }
                System.out.println();// Salto de línea al final de cada fila                
                espacios--;// Reducir espacios para el siguiente nivel (pirámide se va estrechando hacia arriba)
            }
        } else {
            System.out.println("El número de niveles debe ser positivo.");// Mostrar mensaje si no es numero positivo
        }
    }
}
package Relacion_4;
import java.util.Scanner;

public class Reemplazo5Javi {
	private static final Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) {

        
        System.out.print("Introduce texto original: ");
        String original = teclado.nextLine();
        String limpia   = original.toLowerCase(); // Limpio la cadena para que no haya distinciones entre mayus y minus
        
        System.out.print("Texto a buscar: ");
        String buscar = teclado.nextLine();
        
        System.out.print("Texto a reemplazar: ");
        String reemplazo = teclado.nextLine();

        // Llamada al método
        String resultado = reemplazarManual(limpia, buscar, reemplazo);
        
        System.out.print("El texto modificado es: ");
        System.out.print(resultado);
    }

  
    // Creo el meetodo que se encarga de encontrar, comprobar el tamaño y reemplazar la sub cadena de caracteres en la cadena original
    public static String reemplazarManual(String texto, String buscar, String reemplazo) {
    	// Creo una variable encargada de sumar con "+" las cadenas de texto que se van a reemplazar
        String resultado = "";
        int i = 0;
        // Recorro la cadena original y levanto una bandera cuando se encuentre la coincidencia
        while (i < texto.length()) {
            boolean coincidencia = false;

            // Compruebo si la palabra cabe desde la posición actual y se comprueba dentro de ese length
            if (i + buscar.length() <= texto.length()) {
                boolean iguales = true;
                // Se compara de caracter en caracter
                for (int j = 0; j < buscar.length(); j++) {
                    if (texto.charAt(i + j) != buscar.charAt(j)) {
                        iguales = false; // Si no coinciden seguimos buscando otra sub cadena de caracteres del mismo tamaño a ver si coinciden
                        break;
                    }
                }
                // Si se encuentra
                if (iguales) {
                    coincidencia = true;
                }
            }

            // Se crea la nueva cadena reemplazando los caracteres originales por los nuevos
            if (coincidencia) {
                resultado = resultado + reemplazo; // Concatenamos el reemplazo
                i = i + buscar.length(); // Saltamos la palabra encontrada
            } else {
                resultado = resultado + texto.charAt(i); // Copiamos el original
                i++; // Avanzamos uno
            }
        }
        
        return resultado;
    }
}
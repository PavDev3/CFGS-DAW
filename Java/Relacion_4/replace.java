package Relacion_4;
import java.util.Scanner;

public class replace {
    public static void main(String[] args) {
        // Mostrar texto original
        String texto = "El lenguaje Java es un lenguaje de alto nivel";
        mostrarTexto("Texto original", texto);
        // Realizar el reemplazo
        String textoModificado = realizarReemplazo(texto);
        // Mostrar resultado
        mostrarTexto("Texto modificado", textoModificado);
    }
    
    private static String realizarReemplazo(String texto) {
        Scanner scanner = new Scanner(System.in);
        // Pedir palabra a reemplazar
        System.out.print("Introduce la palabra a reemplazar: ");
        String palabraBuscar = scanner.nextLine();
        // Pedir palabra de reemplazo
        System.out.print("Introduce la palabra de reemplazo: ");
        String palabraReemplazo = scanner.nextLine();        
        // Realizar el reemplazo
        String textoModificado = texto.replace(palabraBuscar, palabraReemplazo);
        scanner.close();
        return textoModificado;
    }
    
    private static void mostrarTexto(String etiqueta, String texto) {
        System.out.println(etiqueta + ": " + texto);
    }
}

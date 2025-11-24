package Relacion_4;

import java.util.Scanner;

public class ordenarConsYVocales {
    public static void main(String[] args) {
        String texto = pedirTexto();
        texto = eliminarEspacios(texto);
        String vocales = guardarVocales(texto);
        String consonantes = guardarConsonantes(texto);
        construirTexto(vocales, consonantes);
    }

    private static String pedirTexto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un texto: ");
        String texto = scanner.nextLine().toLowerCase();
        scanner.close();
        return texto;
    }

    private static String eliminarEspacios(String texto) {
        String textoSinEspacios = texto.replace(" ", "");
        return textoSinEspacios.toLowerCase();
    }

    private static String guardarVocales(String texto) {
        //Guardame las vocales en una string
        //Recorro el texto y guardo las vocales en la string
        String vocales = "";
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == 'a' || texto.charAt(i) == 'e' || texto.charAt(i) == 'i' || texto.charAt(i) == 'o' || texto.charAt(i) == 'u') {
                vocales += texto.charAt(i);
            }
        }
        return vocales;
    }

    private static String guardarConsonantes(String texto) {
        String consonantes = "";
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != 'a' && texto.charAt(i) != 'e' && texto.charAt(i) != 'i' && texto.charAt(i) != 'o' && texto.charAt(i) != 'u') {
                consonantes += texto.charAt(i);
            }
        }
        return consonantes;
    }
    private static void construirTexto (String vocales, String consonantes) {
      // el texto debe de mostrarse primero las consonantes y luego las vocales
      System.out.println("El texto ordenado es: " + consonantes + vocales);
    }

    
    
}

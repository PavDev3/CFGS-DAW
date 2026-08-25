package Relacion_4;

import java.util.Scanner;

public class cuentaVocales {
    public static void main(String[] args) {
        String texto = pedirTexto();
        // contarVocalesRepetidas(texto);
        contarVocales(texto);
    }

    private static String pedirTexto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce un texto: ");
        String texto = scanner.nextLine().toLowerCase();
        scanner.close();
        return texto;
    }

    private static void contarVocales(String texto) {
        // Contar vocales diferentes (sin repetir)
        boolean tieneA = false;
        boolean tieneE = false;
        boolean tieneI = false;
        boolean tieneO = false;
        boolean tieneU = false;
        // Recorrer el texto y contar las vocales diferentes
        for (int contador = 0; contador < texto.length(); contador++) {
            // Obtener el caracter actual
            char caracter = texto.charAt(contador);
            // Comprobar si el caracter es una vocal
            switch (caracter) {
                case 'a':
                    tieneA = true;
                    break;
                case 'e':
                    tieneE = true;
                    break;
                case 'i':
                    tieneI = true;
                    break;
                case 'o':
                    tieneO = true;
                    break;
                case 'u':
                    tieneU = true;
                    break;
            }
        }
        // Contar cuántas vocales diferentes hay
        int contadorVocales = (tieneA ? 1 : 0) + (tieneE ? 1 : 0) + (tieneI ? 1 : 0) + (tieneO ? 1 : 0) + (tieneU ? 1 : 0);
        System.out.println("El texto tiene " + contadorVocales + " vocales diferentes");
    }

  /*  private static void contarVocalesRepetidas(String texto) {
        // Contar vocales diferentes (con repetir)
        int contadorA = 0;
        int contadorE = 0;
        int contadorI = 0;
        int contadorO = 0;
        int contadorU = 0;
        // Recorrer el texto y contar las vocales con repetir
        for (int contador = 0; contador < texto.length(); contador++) {
            // Obtener el caracter actual
            char caracter = texto.charAt(contador);
            // Comprobar si el caracter es una vocal y contarla
            switch (caracter) {
                case 'a':
                    contadorA++;
                    break;
                case 'e':
                    contadorE++;
                    break;
                case 'i':
                    contadorI++;
                    break;
                case 'o':
                    contadorO++;
                    break;
                case 'u':
                    contadorU++;
                    break;
            }
        }
        // Contar cuántas vocales diferentes hay
        System.out.println("El texto tiene " + contadorA + " a");
        System.out.println("El texto tiene " + contadorE + " e");
        System.out.println("El texto tiene " + contadorI + " i");
        System.out.println("El texto tiene " + contadorO + " o");
        System.out.println("El texto tiene " + contadorU + " u");
    }
    */
}
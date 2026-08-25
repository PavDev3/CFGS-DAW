package Relacion_4;

import java.util.Scanner;

public class ejercico2_examen {
    public static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("LENGUAJE CANI");
        System.out.println("Introduce una frase:");
        String frase = teclado.nextLine();
        String resultado = convertirACani(frase);
        System.out.println("Resultado: " + resultado);
    }
    
    // Convierte una frase al lenguaje cani (alterna mayuculas y minusculas)
    public static String convertirACani(String frase) {
        String resultado = "";
        
        for (int i = 0; i < frase.length(); i++) {
            char caracter = frase.charAt(i);
            
            // Si la posicion es par convertir a minuscula
            // Si la posicion es impar convertir a mayuscula
            if (i % 2 == 0) {
                resultado += minuscula(caracter);
            } else {
                resultado += mayuscula(caracter);
            }
        }
        
        return resultado;
    }
    
    // Convierte un caracter a minuscula sin usar funciones predeterminadas
    public static char minuscula(char c) {
        // Si es una letra mayúscula (A-Z), convertir a minuscula
        if (c >= 'A' && c <= 'Z') {
        	// es la diferencia en codigo ASCII entre 'a' y 'A'
            int diferencia = 'a' - 'A';
            return (char)(c + diferencia);
        }
        // Si ya es minuscula o no es una letra, devolverlo tal cual
        return c;
    }
    
    // Convierte un caracter a mayuscula sin usar funciones predeterminadas
    public static char mayuscula(char c) {
        // Si es una letra minuscula (a-z), convertir a mayuscula
        if (c >= 'a' && c <= 'z') {
        	// diferencia en codigo ASCII entre 'A' y 'a'
            int diferencia = 'A' - 'a';
            return (char)(c + diferencia);
        }
        // Si ya es mayuscula o no es una letra, devolverlo tal cual
        return c;
    }
}

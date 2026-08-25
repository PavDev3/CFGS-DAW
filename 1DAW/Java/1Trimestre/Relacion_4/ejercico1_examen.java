package Relacion_4;

import java.util.Scanner;

public class ejercico1_examen {
    public static Scanner teclado = new Scanner(System.in);
    
    public static void main(String[] args) {
    	System.out.println("LENGUAJE Pii");
        System.out.println("Se pediran 5 frases para convertir");
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("Frase " + i + ":");
            String frase = teclado.nextLine();
            String resultado = convertirAPii(frase);
            System.out.println("Resultado: " + resultado);
            System.out.println();
        }
        
        System.out.println("Fin!");
    }
    
   
    // Convierte una frase al lenguaje Pii.
    public static String convertirAPii(String frase) {
        String resultado = "";
        // Bucle para comprobar caracter
        for (int i = 0; i < frase.length(); i++) {
            char caracter = frase.charAt(i);
            resultado += caracter;
            
            // Si es una vocal que no sea 'e' (ni 'E'), insertar "Pi"
            if (esVocalNoE(caracter)) {
                resultado += "Pi";
            }
        }
        return resultado;
    }
    
    
    // Verifica si un caracter es una vocal (a, i, o, u) pero NO es 'e'.
    public static boolean esVocalNoE(char c) {
        // Verificar si es 'a' o 'A'
        if (c == 'a' || c == 'A') {
            return true;
        }
        // Verificar si es 'i' o 'I'
        if (c == 'i' || c == 'I') {
            return true;
        }
        // Verificar si es 'o' o 'O'
        if (c == 'o' || c == 'O') {
            return true;
        }
        // Verificar si es 'u' o 'U'
        if (c == 'u' || c == 'U') {
            return true;
        }
        // Si es 'e' o 'E', o cualquier otro caracter, devuelve falso
        return false;
    }
}

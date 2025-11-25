package Relacion_4;

import java.util.Scanner;

public class simulacro {
    static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        String frase = pedirFrase();
        boolean continuar = true;

        while (continuar) {
            int option = menu();

            switch (option) {
                case 1:
                    System.out.println("Numero de espacios: " + contarEspacios(frase));
                    break;
                case 2:
                    System.out.println("Numero de palabras: " + contarPalabras(frase));
                    break;
                case 3:
                    System.out.println("Mayusculas en ultima letra: " + contarMayusculas(frase));
                    break;
                case 4:
                    System.out.println("Frase invertida: " + invertir(frase));
                    break;
                case 5:
                    System.out.println("Nos vemos");
                    continuar = false;
                    break;
            }
            // Si el usuario quiere hacer algo mas, pedirle la opcion
            if (continuar) {
                System.out.print("¿Desea hacer algo mas? (s/n): ");
                String answer = scanner.nextLine().toLowerCase();
                if (!answer.equals("si") && !answer.equals("s")) {
                    System.out.println("Nos vemos");
                    continuar = false;
                }
            }
        }
    }

    public static String pedirFrase() {
        // limpiar terminal
        for (int i = 30 ; i > 0 ; i--) {
            System.out.println("");
        }
        System.out.print("Introduce una frase: ");
        return scanner.nextLine();
    }

    public static int menu() {
        System.out.println("Menu:");
        System.out.println("1 --> Contar espacios");
        System.out.println("2 --> Contar palabras");
        System.out.println("3 --> Mayusculas en ultima palabra");
        System.out.println("4 --> Invertir frase");
        System.out.println("5 --> Salir");
        
        // Inicializar la opcion a 0
        int option = 0;
        boolean valido = false;
        
        while (!valido) {
            // Pedir la opcion al usuario
            System.out.print("Seleccione una opcion (1-5): ");
            
            String input = scanner.nextLine().trim();
            
            // Verificar que solo contenga digitos
            boolean esNumero = true;
            for (int i = 0; i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    esNumero = false;
                    break;
                }
            }
            // Verificar que la opcion sea un numero y no este vacio
            if (esNumero && !input.isEmpty()) {
                option = Integer.parseInt(input);
                // Verificar que este entre 1 y 5
                if (option >= 1 && option <= 5) {
                    valido = true;
                } else {
                    System.out.println("Error: Debe introducir un numero entre 1 y 5");
                }
            } else {
                System.out.println("Error: Debe introducir solo digitos y no esta vacio");
            }
        }
        return option;
    }

    public static int contarEspacios(String frase) {
        // Inicializar el contador a 0
        int contador = 0;
        // Recorrer la frase y contar los espacios
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                contador++;
            }
        }
        return contador;
    }

    public static int contarPalabras(String frase) {
        if (frase.isEmpty()) return 0;
        int contador = 0;
        boolean esPalabra = false;
        // metemos un espacio al final para contar la ultima palabra si no termina en espacio
        String fraseAux = frase + " "; 
        for (int i = 0; i < fraseAux.length(); i++) {
            // Si el caracter actual no es un espacio
            if (fraseAux.charAt(i) != ' ') {
                // Si el caracter actual no es un espacio, es una palabra
                esPalabra = true;
            // Si el caracter actual es un espacio y es una palabra
            } else if (esPalabra) {
                contador++;
                esPalabra = false;
            }
        }
        return contador;
    }

    public static int contarMayusculas(String frase) {
        int contador = 0;
        String fraseTrans = frase + " "; // metemos un espacio al final para procesar la ultima palabra
        
        for (int i = 0; i < fraseTrans.length() - 1; i++) {
            // Si el caracter actual no es espacio y el siguiente SI es espacio (fin de palabra)
            if (fraseTrans.charAt(i) != ' ' && fraseTrans.charAt(i + 1) == ' ') {
                char ultimaLetra = fraseTrans.charAt(i);
                // Comprobamos si es mayuscula
                if (Character.isUpperCase(ultimaLetra)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public static String invertir(String frase) {
        String resultado = "";
        String palabraActual = "";
         // metemos un espacio al final para procesar la ultima palabra
        String fraseTrans = frase + " ";
        // Recorrer la frase y invertir las palabras
        for (int i = 0; i < fraseTrans.length(); i++) {
            // Obtener el caracter actual
            char caracter = fraseTrans.charAt(i);
            // Si el caracter actual no es un espacio, es una palabra
            if (caracter != ' ') {
                // Si el caracter actual no es un espacio, es una palabra
                palabraActual += caracter;
            // Si el caracter actual es un espacio y tenemos una palabra
            } else if (!palabraActual.isEmpty()) {
                // Añadir la palabra al resultado y un espacio
                resultado = palabraActual + " " + resultado;
                // Reiniciar la palabra actual
                palabraActual = "";
            }
        }
        return resultado;
    }
}
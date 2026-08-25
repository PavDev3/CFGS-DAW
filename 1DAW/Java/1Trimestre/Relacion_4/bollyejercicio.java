package Relacion_4;

import java.util.Scanner;

public class bollyejercicio {
    public static void main(String[] args) {    
        String frase = pedirFrase();
        contarPalabras(frase);
        eliminarEspacios(frase);
        reverseFrase(frase);
    }

    private static String pedirFrase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una frase: ");
        String frase = scanner.nextLine();
        scanner.close();
        return frase;
    }

  
    private static void contarPalabras(String frase) {
        int contador = 0;
        boolean enPalabra = false;
        
        // Recorre cada carácter de la frase para identificar palabras
        // Incrementa el contador cuando encuentra el inicio de una nueva palabra
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) != ' ') {
            	// Si no estamos ya en una palabra, incrementa el contador
                if (!enPalabra) {
                    contador++;
                    enPalabra = true;
                }
            } else {
                enPalabra = false;
            }
        }
        System.out.println("La frase tiene " + contador + " palabras");
        
    }


    private static void eliminarEspacios(String frase) {
      String resultado = "";
      boolean espacioAnterior = false;
      
      // Recorre la frase carácter por carácter usando substring
      // Añade todos los caracteres no-espacio y solo el primer espacio de cada secuencia
      for (int i = 0; i < frase.length(); i++) {
    	  // Usa substring para obtener el caracter actual
          if (frase.charAt(i) != ' ') {
        	  // Sumamos el caracter actual al resultado
              resultado += frase.substring(i, i + 1);
              // Reseteamos la bandera
              espacioAnterior = false;
          } else {
              // Solo anade el espacio si no habia uno anterior
              if (!espacioAnterior) {
            	  // Sumamos el espacio al resultado
                  resultado += frase.substring(i, i + 1);
                  espacioAnterior = true;
              }
          }
      }
      System.out.println("La frase sin espacios sobrantes es: " + resultado);
    }

    // Invierte el orden de los caracteres de la frase
    // Recorre la frase desde el final hacia el principio
    private static void reverseFrase(String frase) {
        String reversedFrase = "";
        // Recorre la frase desde el final hacia el principio
        for (int i = frase.length() - 1; i >= 0; i--) {
            reversedFrase += frase.charAt(i);
        }
        System.out.println("La frase invertida es: " + reversedFrase);
    }
}

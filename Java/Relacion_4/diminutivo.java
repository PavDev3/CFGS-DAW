package Relacion_4;

import java.util.*;

public class diminutivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un nombre: ");
        String palabra = sc.nextLine();
        System.out.println("La palabra en diminutivo es: " + diminutivo(palabra));
    }
    public static String diminutivo(String palabra) {
        String resultado = "";
        //si palabra termina en o se sustituye por ito
        if (palabra.endsWith("o")) {
            resultado = palabra.substring(0, palabra.length() - 1) + "ITO";
        } else {
            resultado = palabra;
        }
        // si la palabra termina en a se sustituto por ita
        if (palabra.endsWith("a")) {
            resultado = palabra.substring(0, palabra.length() - 1) + "ITA";
        }
        // si la palabra contiene e/i/u llamar al metodo genero para saber que substring
        if (palabra.endsWith("e") || palabra.endsWith("i") || palabra.endsWith("u")){
            boolean genero = genero();
            if (genero) {
                resultado = palabra.substring(0, palabra.length() - 1) + "ITA";
            } else {
                resultado = palabra.substring(0, palabra.length() - 1) + "ITO";
            }

        }
        // Si la palabra no contiene a/e/i/o/u
        if (!palabra.endsWith("a")|| !palabra.endsWith("e") || !palabra.endsWith("i") || !palabra.endsWith("o") || !palabra.endsWith("u")){
			boolean genero = genero();
			if (genero) {
				resultado = palabra + "ITA";
			} else {
				resultado = palabra + "ITO";
			}
		}
        return resultado;
    }

    public static boolean genero() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("¿La palabra es femenina o masculina? (f/m): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            if (respuesta.equals("f")) {
                return true;
            } else if (respuesta.equals("m")) {
                return false;
            } else {
                System.out.println("Respuesta no válida. Por favor, introduce 'f' para femenino o 'm' para masculino.");
            }
        }
    }

}

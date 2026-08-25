package colecciones;

import java.util.ArrayList;

public class Colecciones {

    // metodo generico que devuelve un ArrayList en orden inverso
    private static <T> ArrayList<T> reverse(ArrayList<T> arrayOriginal) {
        ArrayList<T> resultado = new ArrayList<>();
        for (int i = arrayOriginal.size() - 1; i >= 0; i--) {
            resultado.add(arrayOriginal.get(i));
        }
        return resultado;
    }

    public static void main(String[] args) {
        // prueba con String
        ArrayList<String> palabras = new ArrayList<>();
        palabras.add("primero");
        palabras.add("segundo");
        palabras.add("tercero");
        palabras.add("cuarto");
        System.out.println("Original: " + palabras);
        System.out.println("Invertido: " + reverse(palabras));

        // prueba con Integer
        ArrayList<Integer> numeros = new ArrayList<>();
        for (int i = 1; i <= 5; i++) numeros.add(i);
        System.out.println("\nOriginal: " + numeros);
        System.out.println("Invertido: " + reverse(numeros));
    }
}

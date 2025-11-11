import java.util.*;

public class Practica03 {
    public static void main(String[] args) {
        Random random = new Random();
        
        // Generar clave aleatoria de 4 letras minúsculas
        String claveGenerada = generarClaveAleatoria(random);
        System.out.println("=== Programa de Práctica 03 ===");
        System.out.println("Clave generada (oculta): " + claveGenerada);
        System.out.println();
        
        // Llamar a los 3 métodos para encontrar la clave
        System.out.println("Buscando la clave con diferentes métodos...\n");
        
        // Método 1
        long inicio1 = System.nanoTime();
        String resultado1 = metodo1(claveGenerada);
        long fin1 = System.nanoTime();
        long tiempo1 = fin1 - inicio1;
        System.out.println("Método 1 - Clave encontrada: " + resultado1);
        System.out.println("Tiempo de ejecución: " + tiempo1 + " nanosegundos (" + 
                          (tiempo1 / 1_000_000.0) + " milisegundos)");
        System.out.println();
        
        // Método 2
        long inicio2 = System.nanoTime();
        String resultado2 = metodo2(claveGenerada);
        long fin2 = System.nanoTime();
        long tiempo2 = fin2 - inicio2;
        System.out.println("Método 2 - Clave encontrada: " + resultado2);
        System.out.println("Tiempo de ejecución: " + tiempo2 + " nanosegundos (" + 
                          (tiempo2 / 1_000_000.0) + " milisegundos)");
        System.out.println();
        
        // Método 3
        long inicio3 = System.nanoTime();
        String resultado3 = metodo3(claveGenerada);
        long fin3 = System.nanoTime();
        long tiempo3 = fin3 - inicio3;
        System.out.println("Método 3 - Clave encontrada: " + resultado3);
        System.out.println("Tiempo de ejecución: " + tiempo3 + " nanosegundos (" + 
                          (tiempo3 / 1_000_000.0) + " milisegundos)");
        System.out.println();
        
        // Resumen
        System.out.println("=== RESUMEN ===");
        System.out.println("Clave original: " + claveGenerada);
        System.out.println("Método más rápido: " + 
            ((tiempo1 < tiempo2 && tiempo1 < tiempo3) ? "Método 1" :
             (tiempo2 < tiempo3) ? "Método 2" : "Método 3"));
    }
    
    /**
     * Genera una clave aleatoria de 4 letras minúsculas
     * En ASCII: 'a' = 97, 'z' = 122
     */
    public static String generarClaveAleatoria(Random random) {
        StringBuilder clave = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            // Generar letra minúscula aleatoria (97-122)
            char letra = (char) (random.nextInt(26) + 97);
            clave.append(letra);
        }
        return clave.toString();
    }
    
    /**
     * Método 1: Búsqueda secuencial carácter por carácter
     */
    public static String metodo1(String claveObjetivo) {
        StringBuilder claveEncontrada = new StringBuilder();
        
        for (int i = 0; i < 4; i++) {
            char objetivo = claveObjetivo.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == objetivo) {
                    claveEncontrada.append(c);
                    break;
                }
            }
        }
        
        return claveEncontrada.toString();
    }
    
    /**
     * Método 2: Búsqueda usando códigos ASCII directamente
     */
    public static String metodo2(String claveObjetivo) {
        StringBuilder claveEncontrada = new StringBuilder();
        
        for (int i = 0; i < 4; i++) {
            int codigoObjetivo = claveObjetivo.charAt(i);
            for (int codigo = 97; codigo <= 122; codigo++) {
                if (codigo == codigoObjetivo) {
                    claveEncontrada.append((char) codigo);
                    break;
                }
            }
        }
        
        return claveEncontrada.toString();
    }
    
    /**
     * Método 3: Búsqueda de toda la clave completa
     */
    public static String metodo3(String claveObjetivo) {
        String claveEncontrada = "";
        
        // Generar todas las combinaciones posibles
        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = 'a'; c2 <= 'z'; c2++) {
                for (char c3 = 'a'; c3 <= 'z'; c3++) {
                    for (char c4 = 'a'; c4 <= 'z'; c4++) {
                        String intento = "" + c1 + c2 + c3 + c4;
                        if (intento.equals(claveObjetivo)) {
                            claveEncontrada = intento;
                            return claveEncontrada;
                        }
                    }
                }
            }
        }
        
        return claveEncontrada;
    }
}

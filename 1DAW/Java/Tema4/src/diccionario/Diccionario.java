package diccionario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Diccionario {

    // HashMap para busqueda rapida (opcion 2 la mas usada)
    private HashMap<String, ArrayList<String>> palabras;

    public Diccionario() {
        palabras = new HashMap<>();
    }

    // anadir palabra — si ya existe, agrega el significado a los anteriores
    public void annadirPalabra(String palabra, String significado) {
        if (palabras.containsKey(palabra)) {
            palabras.get(palabra).add(significado);
        } else {
            ArrayList<String> significados = new ArrayList<>();
            significados.add(significado);
            palabras.put(palabra, significados);
        }
        System.out.println("Palabra '" + palabra + "' anadida.");
    }

    // buscar palabra — muestra todos sus significados
    public void buscarPalabra(String palabra) throws Exception {
        if (!palabras.containsKey(palabra)) {
            throw new Exception("La palabra '" + palabra + "' no esta en el diccionario.");
        }
        System.out.println("Significados de '" + palabra + "':");
        for (String sig : palabras.get(palabra)) {
            System.out.println("  - " + sig);
        }
    }

    // borrar palabra con todos sus significados
    public void borrarPalabra(String palabra) throws Exception {
        if (!palabras.containsKey(palabra)) {
            throw new Exception("La palabra '" + palabra + "' no esta en el diccionario.");
        }
        palabras.remove(palabra);
        System.out.println("Palabra '" + palabra + "' borrada.");
    }

    // listar palabras que empiezan por un prefijo, ordenadas alfabeticamente
    public void listarPorPrefijo(String prefijo) {
        TreeMap<String, ArrayList<String>> ordenado = new TreeMap<>(palabras);
        System.out.println("Palabras que empiezan por '" + prefijo + "':");
        boolean encontrado = false;
        for (Map.Entry<String, ArrayList<String>> entry : ordenado.entrySet()) {
            if (entry.getKey().startsWith(prefijo)) {
                System.out.println("  " + entry.getKey()
                        + " (" + entry.getValue().size() + " significado(s))");
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("  Ninguna.");
    }
}

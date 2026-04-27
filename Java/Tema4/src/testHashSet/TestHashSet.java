package testHashSet;

import java.util.HashSet;
import java.util.Iterator;

public class TestHashSet {

    public static void main(String[] args) {
        HashSet<String> ciudades = new HashSet<String>();
        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Sevilla");
        ciudades.add("Madrid"); // repetido

        // Iteramos sobre el conjunto
        Iterator<String> iterador = ciudades.iterator();
        while (iterador.hasNext())
            System.out.println("Ciudad: " + iterador.next());

        // Equivalente a lo anterior
        for (String c : ciudades) {
            System.out.println("Ciudad " + c);
        }
    }
}

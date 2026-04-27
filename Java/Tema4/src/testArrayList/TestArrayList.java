package testArrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class TestArrayList {

    public static void main(String args[]) {
        ArrayList<String> ciudades = new ArrayList<String>();

        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Sevilla");
        ciudades.add("Madrid"); // aunque este repetido se inserta

        // Iteramos sobre el conjunto y nos recorremos la coleccion
        Iterator<String> iterador = ciudades.iterator();
        while (iterador.hasNext())
            System.out.println(iterador.next());
    }
}

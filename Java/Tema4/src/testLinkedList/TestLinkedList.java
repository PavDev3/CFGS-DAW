package testLinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class TestLinkedList {

    public static void main(String args[]) {
        LinkedList<String> ciudades = new LinkedList<String>();

        ciudades.add("Madrid");
        ciudades.add("Barcelona");
        ciudades.add("Sevilla");
        ciudades.add("Madrid"); // repetido

        // Iteramos sobre el conjunto
        Iterator<String> itr = ciudades.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());
    }
}

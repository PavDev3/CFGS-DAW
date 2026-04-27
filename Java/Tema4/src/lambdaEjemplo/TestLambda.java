package lambdaEjemplo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class TestLambda {

    public static void main(String[] args) {
        LinkedList<Persona> listadoPersonas = new LinkedList<Persona>();
        listadoPersonas.add(new Persona("22B", "PEPE", "SOL", 25));
        listadoPersonas.add(new Persona("21X", "PEPE", "ADSFFL", 25));
        listadoPersonas.add(new Persona("45X", "LOLA", "ADSFFL", 55));

        // 1. Usando una clase que implementa Comparator
        ComparadorPorDni compClase = new ComparadorPorDni();
        Collections.sort(listadoPersonas, compClase);
        System.out.println("Ordenado por DNI (clase Comparator):");
        for (Persona p : listadoPersonas)
            System.out.println(p);

        // 2. Usando una clase anonima
        Comparator<Persona> comparador = new Comparator<Persona>() {
            @Override
            public int compare(Persona persona1, Persona persona2) {
                return persona1.getDni().compareTo(persona2.getDni());
            }
        };
        Collections.sort(listadoPersonas, comparador);
        System.out.println("\nOrdenado por DNI (clase anonima):");
        Iterator<Persona> itr = listadoPersonas.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());

        // 3. Usando expresion lambda
        Collections.sort(listadoPersonas, (persona1, persona2) -> {
            return persona1.getDni().compareTo(persona2.getDni());
        });
        System.out.println("\nOrdenado por DNI (lambda):");
        for (Persona p : listadoPersonas)
            System.out.println(p);
    }
}

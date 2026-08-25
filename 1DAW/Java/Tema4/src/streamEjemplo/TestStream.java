package streamEjemplo;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TestStream {

    public static void main(String[] args) {
        ArrayList<Persona> arrayListPersona = new ArrayList<Persona>();
        arrayListPersona.add(new Persona("22B", "JUANA", "SOL", 25));
        arrayListPersona.add(new Persona("21X", "JUAN", "ADSFFL", 24));
        arrayListPersona.add(new Persona("45X", "LOLA", "ADSFFL", 55));
        arrayListPersona.add(new Persona("10A", "PEDRO", "GARCIA", 30));

        // Stream a partir de una coleccion
        Stream<Persona> streamDeArrayList = arrayListPersona.stream();

        // forEach: ejecutar una accion sobre todos los elementos
        System.out.println("=== forEach ===");
        arrayListPersona.stream().forEach(p -> System.out.println(p));

        // sorted: ordenar por edad usando lambda
        System.out.println("\n=== sorted por edad ===");
        arrayListPersona.stream()
            .sorted((p1, p2) -> p1.getEdad() - p2.getEdad())
            .forEach(p -> System.out.println(p));

        // distinct: sin repetidos (usa equals, aqui todos distintos)
        System.out.println("\n=== distinct ===");
        Stream<String> streamCadenas = Stream.of("Sevilla", "Cordoba", "Madrid", "Sevilla");
        streamCadenas.distinct().forEach(c -> System.out.println(c));

        // filter: personas mayores de 25
        System.out.println("\n=== filter edad > 25 ===");
        arrayListPersona.stream()
            .filter(p -> p.getEdad() > 25)
            .forEach(p -> System.out.println(p));

        // Stream a partir de un array
        System.out.println("\n=== Stream desde array ===");
        Persona[] arrayPersonas = {
            new Persona("22B", "JUANA", "SOL", 25),
            new Persona("21X", "JUAN", "ADSFFL", 24)
        };
        Stream<Persona> streamDeArray = Stream.of(arrayPersonas);
        streamDeArray.forEach(p -> System.out.println(p));
    }
}

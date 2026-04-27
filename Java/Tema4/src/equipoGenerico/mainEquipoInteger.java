package equipoGenerico;

public class mainEquipoInteger {

    public static void main(String[] args) {
        try {
            Equipo<Integer> e1 = new Equipo<>("Numeros A");
            e1.annadir(1);
            e1.annadir(2);
            e1.annadir(3);

            Equipo<Integer> e2 = new Equipo<>("Numeros B");
            e2.annadir(2);
            e2.annadir(3);
            e2.annadir(4);
            e2.annadir(5);

            System.out.println("=== Equipos ===");
            e1.mostrar();
            e2.mostrar();

            System.out.println("\n=== Union ===");
            e1.union(e2).mostrar();

            System.out.println("\n=== Interseccion ===");
            e1.interseccion(e2).mostrar();

            System.out.println("\n=== Borrar 1 de e1 ===");
            e1.borrar(1);
            e1.mostrar();

            System.out.println("\n=== Test excepcion: borrar elemento inexistente ===");
            e1.borrar(99);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

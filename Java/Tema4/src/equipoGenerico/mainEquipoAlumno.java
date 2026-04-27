package equipoGenerico;

public class mainEquipoAlumno {

    public static void main(String[] args) {
        try {
            Alumno a1 = new Alumno("Pablo",  "12345678A");
            Alumno a2 = new Alumno("Maria",  "87654321B");
            Alumno a3 = new Alumno("Carlos", "11111111C");

            Equipo<Alumno> e1 = new Equipo<>("Equipo A");
            e1.annadir(a1);
            e1.annadir(a2);

            Equipo<Alumno> e2 = new Equipo<>("Equipo B");
            e2.annadir(a2);
            e2.annadir(a3);

            System.out.println("=== Equipos ===");
            e1.mostrar();
            e2.mostrar();

            System.out.println("\n=== Union ===");
            e1.union(e2).mostrar();

            System.out.println("\n=== Interseccion ===");
            e1.interseccion(e2).mostrar();

            System.out.println("\n=== Buscar a1 en e1 ===");
            System.out.println(e1.buscar(a1));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

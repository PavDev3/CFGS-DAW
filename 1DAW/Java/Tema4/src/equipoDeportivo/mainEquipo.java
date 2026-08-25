package equipoDeportivo;

public class mainEquipo {

    public static void main(String[] args) {
        try {
            Alumno a1 = new Alumno("Pablo",  "12345678A");
            Alumno a2 = new Alumno("Maria",  "87654321B");
            Alumno a3 = new Alumno("Carlos", "11111111C");
            Alumno a4 = new Alumno("Ana",    "22222222D");

            Equipo e1 = new Equipo("Equipo A");
            e1.annadirAlumno(a1);
            e1.annadirAlumno(a2);
            e1.annadirAlumno(a3);

            Equipo e2 = new Equipo("Equipo B");
            e2.annadirAlumno(a2);
            e2.annadirAlumno(a3);
            e2.annadirAlumno(a4);

            System.out.println("=== Equipos ===");
            e1.mostrar();
            e2.mostrar();

            System.out.println("\n=== Buscar a2 en e1 ===");
            Alumno encontrado = e1.buscar(a2);
            System.out.println(encontrado != null ? "Encontrado: " + encontrado : "No encontrado");

            System.out.println("\n=== Union ===");
            e1.union(e2).mostrar();

            System.out.println("\n=== Interseccion ===");
            e1.interseccion(e2).mostrar();

            System.out.println("\n=== Borrar a1 de e1 ===");
            e1.borrarAlumno(a1);
            e1.mostrar();

            System.out.println("\n=== Test excepcion: anadir duplicado ===");
            e1.annadirAlumno(a2);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

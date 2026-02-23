package ArrayBasicClaudIA;
/**
 * EJERCICIO 15 - NIVEL 5: Subir nota a todos
 * Recorre el array de alumnos y suma 1 punto a todos.
 */
public class Ejercicio15_SubirNotas {
    
    static class Alumno {
        String nombre;
        double nota;
        
        public Alumno(String nombre, double nota) {
            this.nombre = nombre;
            this.nota = nota;
        }
        
        @Override
        public String toString() {
            return "Alumno{nombre='" + nombre + "', nota=" + nota + "}";
        }
    }
    
    public static void main(String[] args) {
        Alumno[] alumnos = {
            new Alumno("Ana García", 8.5),
            new Alumno("Pedro López", 7.2),
            new Alumno("María Fernández", 9.0),
            new Alumno("Carlos Ruiz", 6.8),
            new Alumno("Laura Martínez", 5.5)
        };
        
        System.out.println("Notas originales:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
        
        // Subir 1 punto a todos (con límite de 10)
        for (Alumno a : alumnos) {
            a.nota = Math.min(a.nota + 1, 10);
        }
        
        System.out.println("\nNotas después de subir 1 punto:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}
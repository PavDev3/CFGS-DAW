package ArrayBasicClaudIA;
/**
 * EJERCICIO 14 - NIVEL 5: Buscar el alumno con mejor nota
 * Dado un array de Alumno, muestra el alumno con mayor nota.
 */
public class Ejercicio14_MejorAlumno {
    
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
            new Alumno("Laura Martínez", 9.5)
        };
        
        System.out.println("Todos los alumnos:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
        
        // Buscar el de mejor nota
        Alumno mejor = alumnos[0];
        for (int i = 1; i < alumnos.length; i++) {
            if (alumnos[i].nota > mejor.nota) {
                mejor = alumnos[i];
            }
        }
        
        System.out.println("\nAlumno con mejor nota:");
        System.out.println(mejor);
    }
}
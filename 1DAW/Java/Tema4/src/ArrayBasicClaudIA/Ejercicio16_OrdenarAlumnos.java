package ArrayBasicClaudIA;
import java.util.Arrays;

/**
 * EJERCICIO 16 - NIVEL 5: Ordenar alumnos por nota
 * Haz que Alumno implemente Comparable<Alumno> y ordena el array por nota usando:
 * Arrays.sort(alumnos);
 */
public class Ejercicio16_OrdenarAlumnos {
    
    static class Alumno implements Comparable<Alumno> {
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
        
        @Override
        public int compareTo(Alumno otro) {
            // Ordenar por nota ascendente
            return Double.compare(this.nota, otro.nota);
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
        
        System.out.println("Alumnos sin ordenar:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
        
        // Ordenar usando Arrays.sort
        Arrays.sort(alumnos);
        
        System.out.println("\nAlumnos ordenados por nota (ascendente):");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}
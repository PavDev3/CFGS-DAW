package ArrayBasicClaudIA;
/**
 * EJERCICIO 13 - NIVEL 5: Crear array de objetos
 * Crea una clase Alumno con:
 * - nombre
 * - nota
 * Crea un array de 3 alumnos.
 * Inicialízalos correctamente (evita null).
 * Muestra sus datos.
 */
public class Ejercicio13_ArrayObjetos {
    
    // Clase Alumno
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
        // Crear array de 3 alumnos e inicializar
        Alumno[] alumnos = new Alumno[3];
        
        // Inicialización correcta (evitando null)
        alumnos[0] = new Alumno("Ana García", 8.5);
        alumnos[1] = new Alumno("Pedro López", 7.2);
        alumnos[2] = new Alumno("María Fernández", 9.0);
        
        // Mostrar datos
        System.out.println("Lista de alumnos:");
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }
}
package ordenarListas;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class TestListaAlumno {

    public static void main(String args[]) {
        LinkedList<Alumno> listaAlumnos = new LinkedList<Alumno>();

        listaAlumnos.add(new Alumno("Pepe", 5));
        listaAlumnos.add(new Alumno("Andres", 10));
        listaAlumnos.add(new Alumno("Rosa", 8));
        listaAlumnos.add(new Alumno("Juan", 7));

        // Ordena por el compareTo de Alumnos, es decir por nota
        Collections.sort(listaAlumnos);

        System.out.println("Ordenados por nota (Comparable):");
        Iterator<Alumno> itr = listaAlumnos.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());

        // Ordena por nombre usando Comparator
        ComparadorPorNombre comp = new ComparadorPorNombre();
        Collections.sort(listaAlumnos, comp);

        System.out.println("\nOrdenados por nombre (Comparator):");
        itr = listaAlumnos.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());
    }
}

package ordenarListas;

import java.util.Comparator;

public class ComparadorPorNombre implements Comparator<Alumno> {

    @Override
    public int compare(Alumno al1, Alumno al2) {
        return al1.getNombre().compareTo(al2.getNombre());
    }
}

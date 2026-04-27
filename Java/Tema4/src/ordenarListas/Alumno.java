package ordenarListas;

public class Alumno implements Comparable<Alumno> {

    private String nombre;
    private int nota;

    public Alumno(String nombre, int nota) {
        this.nombre = nombre;
        this.nota   = nota;
    }

    public String getNombre() { return nombre; }
    public int getNota()      { return nota; }

    @Override
    public int compareTo(Alumno otro) {
        int resul;
        if (nota == otro.getNota())
            resul = 0;
        else {
            if (nota > otro.getNota())
                resul = 1;
            else
                resul = -1;
        }
        return resul;
    }

    @Override
    public String toString() {
        return nombre + " (" + nota + ")";
    }
}

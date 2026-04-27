package equipoDeportivo;

import java.util.HashSet;

public class Equipo {

    private String nombre;
    private HashSet<Alumno> alumnos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.alumnos = new HashSet<>();
    }

    // annadir alumno — excepcion si ya existe
    public void annadirAlumno(Alumno a) throws Exception {
        if (alumnos.contains(a)) {
            throw new Exception("El alumno " + a.getNombre() + " ya esta en el equipo.");
        }
        alumnos.add(a);
    }

    // borrar alumno — excepcion si no existe
    public void borrarAlumno(Alumno a) throws Exception {
        if (!alumnos.contains(a)) {
            throw new Exception("El alumno " + a.getNombre() + " no esta en el equipo.");
        }
        alumnos.remove(a);
    }

    // buscar — null si no existe, objeto si existe
    public Alumno buscar(Alumno a) {
        for (Alumno alumno : alumnos) {
            if (alumno.equals(a)) return alumno;
        }
        return null;
    }

    public void mostrar() {
        System.out.println("Equipo: " + nombre + " (" + alumnos.size() + " alumnos)");
        for (Alumno a : alumnos) {
            System.out.println("  " + a);
        }
    }

    // union — nuevo equipo con alumnos de ambos
    public Equipo union(Equipo otro) {
        Equipo resultado = new Equipo(nombre + "_union_" + otro.nombre);
        resultado.alumnos.addAll(this.alumnos);
        resultado.alumnos.addAll(otro.alumnos);
        return resultado;
    }

    // interseccion — nuevo equipo solo con alumnos comunes
    public Equipo interseccion(Equipo otro) {
        Equipo resultado = new Equipo(nombre + "_interseccion_" + otro.nombre);
        for (Alumno a : this.alumnos) {
            if (otro.alumnos.contains(a)) {
                resultado.alumnos.add(a);
            }
        }
        return resultado;
    }

    public String getNombre() { return nombre; }
}

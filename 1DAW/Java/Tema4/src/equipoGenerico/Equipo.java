package equipoGenerico;

import java.util.HashSet;

public class Equipo<T> {

    private String nombre;
    private HashSet<T> elementos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.elementos = new HashSet<>();
    }

    public void annadir(T elemento) throws Exception {
        if (elementos.contains(elemento)) {
            throw new Exception("El elemento ya existe en el equipo.");
        }
        elementos.add(elemento);
    }

    public void borrar(T elemento) throws Exception {
        if (!elementos.contains(elemento)) {
            throw new Exception("El elemento no existe en el equipo.");
        }
        elementos.remove(elemento);
    }

    public T buscar(T elemento) {
        for (T e : elementos) {
            if (e.equals(elemento)) return e;
        }
        return null;
    }

    public void mostrar() {
        System.out.println("Equipo: " + nombre + " (" + elementos.size() + " elementos)");
        for (T e : elementos) {
            System.out.println("  " + e);
        }
    }

    public Equipo<T> union(Equipo<T> otro) {
        Equipo<T> resultado = new Equipo<>(nombre + "_union_" + otro.nombre);
        resultado.elementos.addAll(this.elementos);
        resultado.elementos.addAll(otro.elementos);
        return resultado;
    }

    public Equipo<T> interseccion(Equipo<T> otro) {
        Equipo<T> resultado = new Equipo<>(nombre + "_interseccion_" + otro.nombre);
        for (T e : this.elementos) {
            if (otro.elementos.contains(e)) {
                resultado.elementos.add(e);
            }
        }
        return resultado;
    }

    public String getNombre() { return nombre; }
}

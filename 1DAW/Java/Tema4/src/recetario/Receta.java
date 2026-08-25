package recetario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Receta {

    private String nombre;
    private int tiempoPreparacion;
    private HashSet<Ingrediente> ingredientes;
    private LinkedList<String> pasos;

    public Receta(String nombre, int tiempoPreparacion) {
        this.nombre            = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.ingredientes      = new HashSet<>();
        this.pasos             = new LinkedList<>();
    }

    public String getNombre()          { return nombre; }
    public int getTiempoPreparacion()  { return tiempoPreparacion; }

    // saber si necesita un ingrediente por nombre
    public boolean necesitaIngrediente(String nombreIngrediente) {
        for (Ingrediente ing : ingredientes) {
            if (ing.getNombre().equals(nombreIngrediente)) return true;
        }
        return false;
    }

    // anadir ingrediente — si ya existe suma la cantidad
    public void annadirIngrediente(Ingrediente ingredienteNuevo) {
        for (Ingrediente ing : ingredientes) {
            if (ing.equals(ingredienteNuevo)) {
                ing.setCantidad(ing.getCantidad() + ingredienteNuevo.getCantidad());
                return;
            }
        }
        ingredientes.add(ingredienteNuevo);
    }

    // borrar ingrediente — tambien borra pasos que lo mencionen
    public void borrarIngrediente(Ingrediente ingredienteABorrar) throws RecetaException {
        if (!ingredientes.contains(ingredienteABorrar)) {
            throw new RecetaException("El ingrediente '" + ingredienteABorrar.getNombre()
                    + "' no esta en la receta.");
        }
        ingredientes.remove(ingredienteABorrar);
        Iterator<String> it = pasos.iterator();
        while (it.hasNext()) {
            if (it.next().contains(ingredienteABorrar.getNombre())) {
                it.remove();
            }
        }
    }

    // anadir paso al final (para crear la receta)
    public void annadirPaso(String paso) {
        pasos.add(paso);
    }

    // anadir paso detras de otro paso existente
    public void annadirPasoDetrasDe(String pasoNuevo, String pasoExistente) throws RecetaException {
        int idx = pasos.indexOf(pasoExistente);
        if (idx == -1) {
            throw new RecetaException("El paso '" + pasoExistente + "' no existe en la receta.");
        }
        pasos.add(idx + 1, pasoNuevo);
    }

    @Override
    public String toString() {
        return "Receta: " + nombre + " | Tiempo: " + tiempoPreparacion + " min"
                + "\n  Ingredientes: " + ingredientes
                + "\n  Pasos: " + pasos;
    }
}

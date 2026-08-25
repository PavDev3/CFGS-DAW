package recetario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Recetario {

    private HashMap<String, Receta> recetas;

    public Recetario() {
        recetas = new HashMap<>();
    }

    // anadir receta — excepcion si ya existe ese nombre
    public void annadirReceta(Receta nuevaReceta) throws RecetaException {
        if (recetas.containsKey(nuevaReceta.getNombre())) {
            throw new RecetaException("Ya existe una receta con el nombre: " + nuevaReceta.getNombre());
        }
        recetas.put(nuevaReceta.getNombre(), nuevaReceta);
    }

    // listado ordenado alfabeticamente por nombre
    public String listadoRecetasOrdenadasAlfabeticamente() throws RecetaException {
        if (recetas.isEmpty()) {
            throw new RecetaException("No hay recetas en el recetario.");
        }
        ArrayList<Receta> lista = new ArrayList<>(recetas.values());
        lista.sort(Comparator.comparing(Receta::getNombre));
        StringBuilder sb = new StringBuilder("=== Recetas (orden alfabetico) ===\n");
        for (Receta r : lista) {
            sb.append("  ").append(r.getNombre())
              .append(" | Tiempo: ").append(r.getTiempoPreparacion()).append(" min\n");
        }
        return sb.toString();
    }

    // listado de recetas con un ingrediente, ordenadas por tiempo de preparacion
    public String listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion(String ingrediente)
            throws RecetaException {
        ArrayList<Receta> lista = new ArrayList<>();
        for (Receta r : recetas.values()) {
            if (r.necesitaIngrediente(ingrediente)) lista.add(r);
        }
        if (lista.isEmpty()) {
            throw new RecetaException("No hay recetas con el ingrediente: " + ingrediente);
        }
        lista.sort(Comparator.comparingInt(Receta::getTiempoPreparacion));
        StringBuilder sb = new StringBuilder("=== Recetas con '" + ingrediente + "' (por tiempo) ===\n");
        for (Receta r : lista) {
            sb.append("  ").append(r.getNombre())
              .append(" | Tiempo: ").append(r.getTiempoPreparacion()).append(" min\n");
        }
        return sb.toString();
    }
}

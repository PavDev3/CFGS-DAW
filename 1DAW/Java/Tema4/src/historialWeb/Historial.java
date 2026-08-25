package historialWeb;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Historial {

    private LinkedList<PaginaWeb> paginas;

    public Historial() {
        paginas = new LinkedList<>();
    }

    // nueva pagina — fecha actual, debe ser posterior a la ultima
    public void nuevaPagina(String url) throws Exception {
        LocalDateTime ahora = LocalDateTime.now();
        if (!paginas.isEmpty()) {
            if (!ahora.isAfter(paginas.getLast().getFechaHora())) {
                throw new Exception("La fecha y hora debe ser posterior a la ultima entrada almacenada.");
            }
        }
        paginas.add(new PaginaWeb(url, ahora));
        System.out.println("Pagina anadida: " + url);
    }

    public void consultarHistorial() {
        if (paginas.isEmpty()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        System.out.println("=== Historial completo (" + paginas.size() + " entradas) ===");
        for (PaginaWeb p : paginas) {
            System.out.println("  " + p);
        }
    }

    public void consultarDia(LocalDate fecha) {
        System.out.println("=== Historial del dia " + fecha + " ===");
        boolean encontrado = false;
        for (PaginaWeb p : paginas) {
            if (p.getFechaHora().toLocalDate().equals(fecha)) {
                System.out.println("  " + p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("  No hay entradas para ese dia.");
        }
    }

    public void borrarHistorial() {
        paginas.clear();
        System.out.println("Historial borrado.");
    }
}

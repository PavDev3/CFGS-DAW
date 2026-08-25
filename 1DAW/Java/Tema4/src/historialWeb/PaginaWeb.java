package historialWeb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaginaWeb {

    private String url;
    private LocalDateTime fechaHora;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public PaginaWeb(String url, LocalDateTime fechaHora) {
        this.url = url;
        this.fechaHora = fechaHora;
    }

    public String getUrl()              { return url; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return url + "  [" + fechaHora.format(FMT) + "]";
    }
}

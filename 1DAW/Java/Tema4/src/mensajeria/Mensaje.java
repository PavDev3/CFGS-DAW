package mensajeria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje {

    private Persona remitente;
    private String texto;
    private LocalDateTime fechaHora;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Mensaje(Persona remitente, String texto) {
        this.remitente = remitente;
        this.texto = texto;
        this.fechaHora = LocalDateTime.now();
    }

    public Persona getRemitente() { return remitente; }
    public String getTexto()      { return texto; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return "De: " + remitente.getNombre() + " Texto: " + texto
                + "\nFecha y hora: " + fechaHora.format(FMT);
    }
}

package mensajeria;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Persona {

    protected String nombre;
    protected int edad;
    protected ArrayList<Mensaje> buzon;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad   = edad;
        this.buzon  = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public int getEdad()      { return edad; }

    // enviar mensaje — cada subclase tiene sus propias restricciones
    public abstract void enviar(Persona destinatario, String texto) throws Exception;

    // metodo para recibir (acceso protegido para subclases)
    void recibirMensaje(Mensaje m) {
        buzon.add(m);
    }

    // leer buzon numerado
    public String leerMensajes() throws Exception {
        if (buzon.isEmpty()) {
            throw new Exception("No hay mensajes en el buzon.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buzon.size(); i++) {
            sb.append("Mensaje ").append(i + 1).append(": ")
              .append(buzon.get(i)).append("\n");
        }
        return sb.toString();
    }

    // leer buzon ordenado alfabeticamente por nombre del remitente
    public String leerMensajesOrdenados() throws Exception {
        if (buzon.isEmpty()) {
            throw new Exception("No hay mensajes en el buzon.");
        }
        ArrayList<Mensaje> ordenado = new ArrayList<>(buzon);
        ordenado.sort(Comparator.comparing(m -> m.getRemitente().getNombre()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ordenado.size(); i++) {
            sb.append("Mensaje ").append(i + 1).append(": ")
              .append(ordenado.get(i)).append("\n");
        }
        return sb.toString();
    }

    // borrar mensaje por numero (1-based)
    public void borrarMensaje(int numero) throws Exception {
        if (numero < 1 || numero > buzon.size()) {
            throw new Exception("Numero de mensaje invalido: " + numero);
        }
        buzon.remove(numero - 1);
        System.out.println("Mensaje " + numero + " borrado.");
    }

    // buscar mensajes que contengan una frase
    public String buscarMensajes(String frase) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buzon.size(); i++) {
            if (buzon.get(i).getTexto().contains(frase)) {
                sb.append("Mensaje ").append(i + 1).append(": ")
                  .append(buzon.get(i)).append("\n");
            }
        }
        if (sb.length() == 0) {
            throw new Exception("No se encontraron mensajes con la frase: " + frase);
        }
        return sb.toString();
    }
}

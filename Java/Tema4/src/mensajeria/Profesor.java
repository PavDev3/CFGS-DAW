package mensajeria;

public class Profesor extends Persona {

    public Profesor(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public void enviar(Persona destinatario, String texto) throws Exception {
        Mensaje m = new Mensaje(this, texto);
        destinatario.recibirMensaje(m);
    }

    @Override
    public String toString() {
        return "Profesor [nombre=" + nombre + ", edad=" + edad + "]";
    }
}

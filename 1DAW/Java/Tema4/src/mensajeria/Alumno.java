package mensajeria;

public class Alumno extends Persona {

    public Alumno(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public void enviar(Persona destinatario, String texto) throws Exception {
        // alumno menor de edad solo puede enviar a profesores
        if (edad < 18 && !(destinatario instanceof Profesor)) {
            throw new Exception("Un alumno menor de edad solo puede enviar mensajes a profesores.");
        }
        Mensaje m = new Mensaje(this, texto);
        destinatario.recibirMensaje(m);
    }

    @Override
    public String toString() {
        return "Alumno [nombre=" + nombre + ", edad=" + edad + "]";
    }
}

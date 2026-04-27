package mensajeria;

public class mainMensajeria {

    public static void main(String[] args) {
        try {
            Profesor prof = new Profesor("Filippo", 40);
            Alumno mayor  = new Alumno("Pablo", 20);
            Alumno menor  = new Alumno("Ana",   16);

            // alumno mayor envia a otro alumno — OK
            mayor.enviar(menor, "Hola Ana, como estas?");

            // alumno menor envia a profesor — OK
            menor.enviar(prof, "Buenos dias profesor");

            // alumno menor intenta enviar a otro alumno — excepcion
            System.out.println("=== Test: menor envia a alumno ===");
            menor.enviar(mayor, "Hola Pablo");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();

        try {
            Profesor prof = new Profesor("Carlos", 35);
            Alumno alu    = new Alumno("Pedro", 21);

            alu.enviar(prof, "Tengo una pregunta sobre el examen");
            alu.enviar(prof, "Cuando entregamos la practica?");
            prof.enviar(alu, "El examen es el viernes");

            System.out.println("=== Buzon del profesor ===");
            System.out.println(prof.leerMensajes());

            System.out.println("=== Buzon del alumno ===");
            System.out.println(alu.leerMensajes());

            System.out.println("=== Buscar 'examen' en buzon profesor ===");
            System.out.println(prof.buscarMensajes("examen"));

            System.out.println("=== Borrar mensaje 1 del profesor ===");
            prof.borrarMensaje(1);
            System.out.println(prof.leerMensajes());

            System.out.println("=== Mensajes ordenados por remitente ===");
            System.out.println(prof.leerMensajesOrdenados());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

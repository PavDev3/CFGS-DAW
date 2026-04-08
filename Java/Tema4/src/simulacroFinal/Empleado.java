package simulacroFinal;

public abstract class Empleado implements Reconocible {

    protected String id;
    protected String nombre;
    protected Departamento departamento;
    protected double[][] horasTrabajadas; // 5 dias x 2 turnos

    public Empleado(String id, String nombre, Departamento departamento, double[][] horasTrabajadas) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.horasTrabajadas = horasTrabajadas;
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public Departamento getDepartamento() { return departamento; }
    public double[][] getHorasTrabajadas() { return horasTrabajadas; }

    // Calcula el total de horas trabajadas en la semana
    public double calcularHorasTotalesSemana() {
        double total = 0;
        for (int dia = 0; dia < horasTrabajadas.length; dia++) {
            for (int turno = 0; turno < horasTrabajadas[dia].length; turno++) {
                total += horasTrabajadas[dia][turno];
            }
        }
        return total;
    }

    // Calcula las horas trabajadas en un dia concreto (suma mañana + tarde)
    public double calcularHorasDia(int dia) {
        return horasTrabajadas[dia][0] + horasTrabajadas[dia][1];
    }

    // Metodo abstracto: cada subclase define su formula de productividad
    public abstract double calcularProductividad();

    // Muestra la matriz de horas trabajadas
    public void mostrarHorario() {
        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        System.out.println("Horario de " + nombre + ":");
        System.out.printf("%-12s %-10s %-10s%n", "Dia", "Mañana", "Tarde");
        for (int i = 0; i < horasTrabajadas.length; i++) {
            System.out.printf("%-12s %-10.1f %-10.1f%n",
                    dias[i], horasTrabajadas[i][0], horasTrabajadas[i][1]);
        }
    }

    @Override
    public String toString() {
        return "Empleado{id='" + id + "', nombre='" + nombre +
               "', departamento=" + departamento + "}";
    }
}

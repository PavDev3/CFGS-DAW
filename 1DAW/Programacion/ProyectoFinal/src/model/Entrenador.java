package model;

// Entrenador de un equipo de la liga
// Su coste mensual depende de los anios de experiencia acumulados
public class Entrenador extends PersonaLiga {

    private int experiencia;       // anios como entrenador profesional
    private String especialidad;   // p.ej: "Macro", "Teamfight", "Draft"
    private int victoriasTotales;

    public Entrenador(String identificador, String nombre, String nickname,
                      int edad, double salarioBase,
                      int experiencia, String especialidad) {
        super(identificador, nombre, nickname, edad, salarioBase);
        this.experiencia = experiencia;
        this.especialidad = especialidad;
        this.victoriasTotales = 0;
    }

    // Getters
    public int getExperiencia() { return experiencia; }
    public String getEspecialidad() { return especialidad; }
    public int getVictoriasTotales() { return victoriasTotales; }

    // Setters
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setVictoriasTotales(int victoriasTotales) { this.victoriasTotales = victoriasTotales; }

    // Se llama desde Equipo.registrarVictoria() cuando el equipo gana
    public void incrementarVictorias() { this.victoriasTotales++; }

    // Coste mensual = salario base + bonus por experiencia
    @Override
    public double calcularCosteMensual() {
        return getSalarioBase() + (experiencia * 200.0);
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("  Experiencia: " + experiencia + " años"
                + " | Especialidad: " + especialidad
                + " | Victorias totales: " + victoriasTotales
                + " | Coste mensual: " + calcularCosteMensual() + "€");
    }

    @Override
    public String toString() {
        return "Entrenador{id='" + getIdentificador() + "', nickname='" + getNickname()
                + "', experiencia=" + experiencia + " años, especialidad='" + especialidad + "'}";
    }
}

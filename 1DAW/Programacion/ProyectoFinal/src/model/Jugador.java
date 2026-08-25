package model;

import exceptions.JugadorSancionadoException;

// Jugador de la liga. Extiende PersonaLiga e implementa Entrenable
// Tiene un rol fijo que determina su posicion en el array de titulares del equipo
public class Jugador extends PersonaLiga implements Entrenable {

    private Rol rol;
    private int nivelMecanico;    // habilidad tecnica individual, del 1 al 10
    private int nivelEstrategico; // toma de decisiones en juego, del 1 al 10
    private int partidasJugadas;
    private int mvpTotales;
    private boolean sancionado;   // si esta sancionado no puede ser convocado

    public Jugador(String identificador, String nombre, String nickname,
                   int edad, double salarioBase, Rol rol,
                   int nivelMecanico, int nivelEstrategico) {
        super(identificador, nombre, nickname, edad, salarioBase);
        this.rol = rol;
        this.nivelMecanico = nivelMecanico;
        this.nivelEstrategico = nivelEstrategico;
        this.partidasJugadas = 0;
        this.mvpTotales = 0;
        this.sancionado = false;
    }

    // Getters
    public Rol getRol() { return rol; }
    public int getNivelMecanico() { return nivelMecanico; }
    public int getNivelEstrategico() { return nivelEstrategico; }
    public int getPartidasJugadas() { return partidasJugadas; }
    public int getMvpTotales() { return mvpTotales; }
    public boolean isSancionado() { return sancionado; }

    // Setters
    public void setRol(Rol rol) { this.rol = rol; }
    public void setNivelMecanico(int nivelMecanico) { this.nivelMecanico = nivelMecanico; }
    public void setNivelEstrategico(int nivelEstrategico) { this.nivelEstrategico = nivelEstrategico; }
    public void setPartidasJugadas(int partidasJugadas) { this.partidasJugadas = partidasJugadas; }
    public void setMvpTotales(int mvpTotales) { this.mvpTotales = mvpTotales; }
    public void setSancionado(boolean sancionado) { this.sancionado = sancionado; }

    // Se llama al registrar el resultado de un partido
    public void incrementarPartidas() { this.partidasJugadas++; }
    public void incrementarMvp() { this.mvpTotales++; }

    // Coste mensual = salario base + bonus por nivel tecnico
    @Override
    public double calcularCosteMensual() {
        return getSalarioBase() + (nivelMecanico + nivelEstrategico) * 100.0;
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("  Rol: " + rol
                + " | Mecanico: " + nivelMecanico + "/10"
                + " | Estrategico: " + nivelEstrategico + "/10"
                + " | Partidas: " + partidasJugadas
                + " | MVPs: " + mvpTotales
                + " | Sancionado: " + (sancionado ? "SI" : "NO")
                + " | Coste mensual: " + calcularCosteMensual() + "€");
    }

    // Implementacion de Entrenable
    @Override
    public void entrenar() {
        System.out.println("El jugador " + getNickname() + " (" + rol + ") esta entrenando.");
    }

    // Rendimiento: 40% mecanica + 40% estrategia + 20% ratio MVP
    // Si no ha jugado partidas aun, el componente MVP es 0 (evita division por cero)
    @Override
    public double calcularRendimiento() {
        double componenteMvp = 0.0;
        if (partidasJugadas > 0) {
            componenteMvp = ((double) mvpTotales / partidasJugadas) * 10 * 0.2;
        }
        return (nivelMecanico * 0.4) + (nivelEstrategico * 0.4) + componenteMvp;
    }

    // Lanza excepcion si el jugador no puede ser convocado por sancion
    public void verificarDisponibilidad() throws JugadorSancionadoException {
        if (sancionado) {
            throw new JugadorSancionadoException(
                    "El jugador " + getNickname() + " (ID: " + getIdentificador()
                            + ") esta sancionado y no puede ser convocado.");
        }
    }

    @Override
    public String toString() {
        return "Jugador{id='" + getIdentificador() + "', nickname='" + getNickname()
                + "', rol=" + rol + ", sancionado=" + sancionado + "}";
    }
}

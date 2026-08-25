package model;

import exceptions.JugadorSancionadoException;
import exceptions.RolNoDisponibleException;
import java.util.ArrayList;

// Equipo competidor de la liga de e-sports
// Estructura de jugadores: array fijo de 5 titulares + lista dinamica de suplentes
public class Equipo {

    private String nombre;   // debe ser unico en la liga
    private String ciudad;
    private Entrenador entrenador;
    private double presupuesto;
    private int victorias;
    private int derrotas;
    private int puntosFavor;
    private int puntosContra;

    // Array fijo de 5 titulares - cada indice corresponde al ordinal del enum Rol
    // indice 0=TOP, 1=JUNGLE, 2=MID, 3=ADC, 4=SUPPORT
    // null significa que ese rol esta vacante
    private Jugador[] titulares;

    // Lista dinamica de suplentes, sin restriccion de roles duplicados entre si
    private ArrayList<Jugador> suplentes;

    public Equipo(String nombre, String ciudad, double presupuesto) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presupuesto = presupuesto;
        this.victorias = 0;
        this.derrotas = 0;
        this.puntosFavor = 0;
        this.puntosContra = 0;
        this.titulares = new Jugador[5];
        this.suplentes = new ArrayList<>();
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public Entrenador getEntrenador() { return entrenador; }
    public double getPresupuesto() { return presupuesto; }
    public int getVictorias() { return victorias; }
    public int getDerrotas() { return derrotas; }
    public int getPuntosFavor() { return puntosFavor; }
    public int getPuntosContra() { return puntosContra; }
    public Jugador[] getTitulares() { return titulares; }
    public ArrayList<Jugador> getSuplentes() { return suplentes; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    // Diferencia de puntos para el desempate en la clasificacion
    public int getDiferenciaPuntos() {
        return puntosFavor - puntosContra;
    }

    // Actualiza estadisticas del equipo y del entrenador al ganar
    public void registrarVictoria(int puntosF, int puntosC) {
        this.victorias++;
        this.puntosFavor += puntosF;
        this.puntosContra += puntosC;
        if (entrenador != null) entrenador.incrementarVictorias();
    }

    public void registrarDerrota(int puntosF, int puntosC) {
        this.derrotas++;
        this.puntosFavor += puntosF;
        this.puntosContra += puntosC;
    }

    // Registra un empate: actualiza puntos pero NO incrementa victorias ni derrotas
    public void registrarEmpate(int puntosF, int puntosC) {
        this.puntosFavor += puntosF;
        this.puntosContra += puntosC;
    }

    // Ficha a un jugador como titular en la posicion de su rol
    // Lanza excepcion si el rol ya esta ocupado o el jugador esta sancionado
    public void ficharTitular(Jugador jugador)
            throws RolNoDisponibleException, JugadorSancionadoException {
        jugador.verificarDisponibilidad();
        int indice = jugador.getRol().ordinal();
        if (titulares[indice] != null) {
            throw new RolNoDisponibleException(
                    "El rol " + jugador.getRol() + " ya esta ocupado en " + nombre
                            + " por " + titulares[indice].getNickname());
        }
        titulares[indice] = jugador;
    }

    // Anade un jugador a la lista de suplentes
    public void ficharSuplente(Jugador jugador) throws JugadorSancionadoException {
        jugador.verificarDisponibilidad();
        suplentes.add(jugador);
    }

    // Intercambia un titular por un suplente - deben tener el mismo rol
    public void intercambiarTitularSuplente(String nicknameTitular, String nicknameSuplente)
            throws RolNoDisponibleException, JugadorSancionadoException {

        // Buscar el titular
        int indiceTitular = -1;
        Jugador jugadorTitular = null;
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null && titulares[i].getNickname().equals(nicknameTitular)) {
                indiceTitular = i;
                jugadorTitular = titulares[i];
                break;
            }
        }
        if (jugadorTitular == null) {
            throw new IllegalArgumentException("No se encontro el titular: " + nicknameTitular);
        }

        // Buscar el suplente
        Jugador jugadorSuplente = null;
        for (Jugador s : suplentes) {
            if (s.getNickname().equals(nicknameSuplente)) {
                jugadorSuplente = s;
                break;
            }
        }
        if (jugadorSuplente == null) {
            throw new IllegalArgumentException("No se encontro el suplente: " + nicknameSuplente);
        }

        jugadorSuplente.verificarDisponibilidad();

        // Verificar compatibilidad de roles
        if (jugadorSuplente.getRol() != jugadorTitular.getRol()) {
            throw new RolNoDisponibleException(
                    "El suplente " + nicknameSuplente + " tiene rol " + jugadorSuplente.getRol()
                            + " pero el titular tiene rol " + jugadorTitular.getRol());
        }

        // Realizar el intercambio
        titulares[indiceTitular] = jugadorSuplente;
        suplentes.remove(jugadorSuplente);
        suplentes.add(jugadorTitular);
    }

    // Elimina un jugador de titulares o suplentes
    public boolean eliminarJugador(String nickname) {
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null && titulares[i].getNickname().equals(nickname)) {
                titulares[i] = null;
                return true;
            }
        }
        return suplentes.removeIf(j -> j.getNickname().equals(nickname));
    }

    // Suma el coste de todos los jugadores y el entrenador
    public double calcularCosteEquipo() {
        double total = 0.0;
        for (Jugador j : titulares) {
            if (j != null) total += j.calcularCosteMensual();
        }
        for (Jugador j : suplentes) {
            total += j.calcularCosteMensual();
        }
        if (entrenador != null) total += entrenador.calcularCosteMensual();
        return total;
    }

    public void mostrarPlantillaCompleta() {
        System.out.println("=== EQUIPO: " + nombre + " (" + ciudad + ") ===");
        System.out.println("Presupuesto: " + presupuesto + "€");
        System.out.println("Entrenador: " + (entrenador != null ? entrenador.getNickname() : "Sin asignar"));
        System.out.println("--- Titulares ---");
        String[] nombresRol = {"TOP", "JUNGLE", "MID", "ADC", "SUPPORT"};
        for (int i = 0; i < titulares.length; i++) {
            if (titulares[i] != null) {
                System.out.println("  " + nombresRol[i] + ": " + titulares[i].getNickname()
                        + (titulares[i].isSancionado() ? " [SANCIONADO]" : ""));
            } else {
                System.out.println("  " + nombresRol[i] + ": VACANTE");
            }
        }
        System.out.println("--- Suplentes (" + suplentes.size() + ") ---");
        for (Jugador s : suplentes) {
            System.out.println("  " + s.getNickname() + " - " + s.getRol()
                    + (s.isSancionado() ? " [SANCIONADO]" : ""));
        }
        System.out.println("Coste mensual total: " + calcularCosteEquipo() + "€");
    }

    @Override
    public String toString() {
        return "Equipo{nombre='" + nombre + "', victorias=" + victorias
                + ", derrotas=" + derrotas + "}";
    }
}

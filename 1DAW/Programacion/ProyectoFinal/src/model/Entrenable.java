package model;

// Interfaz que deben implementar los jugadores de la liga
// Obliga a definir como entrenan y como se calcula su rendimiento
public interface Entrenable {

    // Simula una sesion de entrenamiento e informa al usuario por consola
    void entrenar();

    // Calcula el rendimiento numerico del jugador en base a sus estadisticas
    // Formula ponderada: 40% nivel mecanico + 40% nivel estrategico + 20% ratio MVP
    //   componente mecanico:    nivelMecanico * 0.4
    //   componente estrategico: nivelEstrategico * 0.4
    //   componente MVP:         (mvpTotales / partidasJugadas) * 10 * 0.2
    // Si el jugador no ha jugado ninguna partida todavia, el componente MVP es 0.0
    // para evitar una division por cero
    // Resultado aproximado entre 0.0 y 10.0
    double calcularRendimiento();
}

# ADR-001 — Jerarquia de herencia y contrato de entrenamiento

**Estado:** Aceptado  
**Fecha:** Mayo 2026  
**Autor:** Pablo Nunez Fernandez

---

## Contexto

La liga gestiona dos tipos de personas: jugadores y entrenadores. Ambos comparten atributos comunes (ID, nombre, nickname, edad, salario base) pero tienen datos y comportamientos propios. Ademas, solo los jugadores pueden ser entrenados y tienen rendimiento calculable.

## Decision

Se define una clase abstracta `PersonaLiga` con los atributos comunes y el metodo abstracto `calcularCosteMensual()`. De ella heredan `Jugador` y `Entrenador`.

Adicionalmente se declara la interfaz `Entrenable` con los metodos `entrenar()` y `calcularRendimiento()`. Solo `Jugador` la implementa.

```
PersonaLiga  (abstracta)
├── Jugador  (implementa Entrenable)
└── Entrenador
```

El metodo `calcularCosteMensual()` se deja abstracto porque cada subclase usa una formula distinta:
- **Jugador:** `salarioBase + (nivelMecanico + nivelEstrategico) * 100`
- **Entrenador:** `salarioBase + (experiencia * 200)`

## Consecuencias

**Ventajas:**
- `ArrayList<PersonaLiga>` en `Liga` almacena jugadores y entrenadores de forma polimorfca, simplificando busquedas y listados.
- Anadir un tercer tipo de persona (p. ej. arbitro) solo requiere una nueva subclase; el resto del codigo no cambia.
- La interfaz `Entrenable` permite invocar `calcularRendimiento()` sin conocer la clase concreta, util en estadisticas.

**Desventajas:**
- Para operaciones exclusivas de jugadores (fichar, sancionar) hay que hacer `instanceof` + casting en `Main.java`, lo que reduce la elegancia polimorfca.
- Si un futuro tipo de persona necesitara entrenamiento, habria que refactorizar la jerarquia (actualmente `Entrenable` solo la usa `Jugador`).

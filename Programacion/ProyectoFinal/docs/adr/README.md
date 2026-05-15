# Architecture Decision Records — Liga de E-Sports

Registro de las decisiones arquitectonicas del Proyecto Final de Programacion (1 CFGS DAW).

| ADR | Titulo | Estado |
|-----|--------|--------|
| [ADR-001](ADR-001-jerarquia-herencia.md) | Jerarquia de herencia y contrato de entrenamiento | Aceptado |
| [ADR-002](ADR-002-array-indexado-por-rol.md) | Array fijo de titulares indexado por Rol.ordinal() | Aceptado |
| [ADR-003](ADR-003-hashset-deduplicacion.md) | HashSet para deteccion de duplicados en O(1) | Aceptado |
| [ADR-004](ADR-004-queue-fifo-partidos.md) | Queue (FIFO) para la cola de partidos pendientes | Aceptado |
| [ADR-005](ADR-005-pila-lifo-historial-sanciones.md) | ArrayDeque como pila (LIFO) para historial y deshacer sanciones | Aceptado |
| [ADR-006](ADR-006-excepciones-checked.md) | Excepciones personalizadas checked para reglas de negocio | Aceptado |
| [ADR-007](ADR-007-liga-orquestador-central.md) | Liga como orquestador central | Aceptado |
| [ADR-008](ADR-008-matriz-puntos-jornada.md) | Matriz 2D para puntos acumulados por jornada y equipo | Aceptado |

## Formato de cada ADR

Cada fichero sigue la estructura:
- **Contexto** — por que habia que tomar una decision
- **Decision** — que se decidio y como funciona
- **Consecuencias** — ventajas e inconvenientes de la eleccion

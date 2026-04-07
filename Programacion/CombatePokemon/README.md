# Combate Pokemon: IA vs Humano

Proyecto de clase DAW — Torneo interno + enfrentamiento final contra una IA que ha completado el juego Pokemon Anil desde cero.

---

## Concepto

1. Los alumnos del grado superior de DAW compiten en un torneo de Pokemon Anil
2. El ganador humano se enfrenta a una IA que tambien ha jugado desde el inicio
3. Objetivo: determinar si la IA o el humano es mas fuerte

---

## Arquitectura del sistema

```
Pantalla del juego
       |
  [Captura de pantalla - mss/pyautogui]
       |
  [Capa de Vision - Qwen2.5-VL 32B via Ollama]
  Extrae el estado: HP, Pokemon en juego, movimientos disponibles
       |
  [Capa de Conocimiento - PokeAPI / Base de datos local]
  Tipos, debilidades, stats, movimientos
       |
  [Capa de Decision - LLM estrategico]
  Razona que accion tomar
       |
  [Capa de Control - pyautogui / pynput]
  Simula pulsaciones de teclado
       |
  Juego recibe la accion
```

---

## Niveles de automatizacion

### Nivel 1 — Automatico (sin consultar la IA)
- Movimiento por el mapa → pathfinding A*
- Avanzar dialogos → pulsar A automaticamente
- Recoger objetos del suelo → automatico
- Guardar la partida periodicamente

### Nivel 2 — Decision de la IA (puntos clave)
- A que ciudad/gimnasio ir ahora
- Cada turno de combate: que movimiento usar
- Que Pokemon capturar y entrenar
- Cuando usar objetos curativos
- Cuando cambiar de Pokemon en combate

---

## Stack tecnologico

| Componente | Tecnologia |
|---|---|
| Modelo de vision | Qwen2.5-VL 14B (Ollama) |
| Hardware | NVIDIA RTX 4090 (24GB VRAM) |
| Captura de pantalla | `mss` + `Pillow` |
| Control del juego | `pyautogui` / `pynput` |
| Conocimiento Pokemon | PokeAPI o JSON local |
| Pathfinding | `python-pathfinding` (A*) |
| Orquestacion | Python 3.11+ |
| Juego | Pokemon Anil (RPG Maker, PC) |

---

## Estructura del proyecto

```
CombatePokemon/
├── README.md                  # Este archivo
├── docs/
│   ├── arquitectura.md        # Detalle tecnico completo
│   ├── plan_desarrollo.md     # Fases y tareas
│   └── pokemon_knowledge.md   # Logica de tipos y combate
├── src/
│   ├── main.py                # Punto de entrada
│   ├── vision/
│   │   ├── screen_capture.py  # Captura de pantalla
│   │   └── game_state.py      # Interpreta el estado del juego
│   ├── knowledge/
│   │   ├── pokedex.py         # Consulta tipos, stats, movimientos
│   │   └── data/              # JSONs con datos Pokemon locales
│   ├── decision/
│   │   ├── battle_agent.py    # Logica de combate
│   │   └── exploration_agent.py # Logica de exploracion
│   └── control/
│       ├── input_handler.py   # Simula teclas
│       └── pathfinder.py      # Navegacion por mapas
├── tests/
│   └── test_battle_logic.py
└── requirements.txt
```

---

## Plan de desarrollo por fases

### Fase 1 — Fundamentos (1-2 dias)
- [ ] Configurar Ollama con Qwen2.5-VL 32B
- [ ] Script de captura de pantalla del juego
- [ ] Enviar screenshot al modelo y recibir descripcion del estado
- [ ] Simular pulsaciones de teclas basicas

### Fase 2 — Vision del juego (2-3 dias)
- [ ] Parsear el estado de combate desde la imagen (HP, nombres, movimientos)
- [ ] Detectar cuando hay combate vs cuando se esta explorando
- [ ] Distinguir dialogos, menus, y pantallas de combate

### Fase 3 — Conocimiento Pokemon (2-3 dias)
- [ ] Base de datos local con los 151+ Pokemon (tipos, stats, movimientos)
- [ ] Calculadora de efectividad de tipos
- [ ] Sistema de puntuacion de movimientos segun contexto

### Fase 4 — Agente de combate (3-4 dias)
- [ ] Logica de decision por turno
- [ ] Gestion de PP (puntos de poder) de los movimientos
- [ ] Decidir cuando cambiar de Pokemon
- [ ] Usar objetos curativos

### Fase 5 — Agente de exploracion (3-4 dias)
- [ ] Navegar por ciudades y caminos
- [ ] Ir al siguiente gimnasio segun el progreso
- [ ] Capturar y entrenar Pokemon

### Fase 6 — Integracion y pruebas (2-3 dias)
- [ ] Bucle principal de juego
- [ ] Sistema de guardado y recuperacion de estado
- [ ] Logs de decisiones para analisis posterior

---

## Estimacion de tiempo de ejecucion

| Tarea | Tiempo estimado |
|---|---|
| Cada decision de combate | 2-5 segundos |
| Combate de 5 turnos | ~25 segundos |
| Un gimnasio completo | 15-45 minutos |
| 8 gimnasios + historia | 4-12 horas |

> Nota: con la velocidad del juego al maximo (Shift en RPG Maker) el tiempo de desplazamiento se reduce drasticamente.

---

## Consideraciones para el torneo

- La IA parte exactamente desde el mismo punto que los humanos (inicio del juego)
- Se documentan todas las decisiones de la IA (logs) para transparencia
- El equipo final de la IA se revelara el dia del combate
- El combate final sera transmitido en directo para la clase

---

## Requisitos previos

- Python 3.11+
- Ollama instalado con modelo `qwen2.5vl:32b`
- Pokemon Anil instalado en PC
- RTX 4090 con drivers actualizados

```bash
# Instalar dependencias
pip install -r requirements.txt

# Descargar modelo
ollama pull qwen2.5-vl:32b

# Ejecutar la IA
python src/main.py
```

---

## Creditos

- Juego original: Pokemon Anil por Eric Lostie (lostiefangames.blogspot.com)
- Proyecto academico: IES Kursaal — Grado Superior DAW

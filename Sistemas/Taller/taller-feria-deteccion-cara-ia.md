# Taller: "Hackea tu cara con IA" 🎭
## Feria de orientación — CFGS Desarrollo de Aplicaciones Web

**Público objetivo:** Alumnos de 16 años (4º ESO / 1º Bachillerato)
**Duración:** 10–15 minutos por turno
**Dificultad para el visitante:** Ninguna — solo interactúan con la cámara

---

## Objetivo del taller

Demostrar en tiempo real cómo una aplicación web puede detectar caras, gestos y expresiones usando la cámara del ordenador, **sin instalar nada**, directamente en el navegador.

El visitante ve su propia cara siendo analizada por IA en tiempo real, puede jugar con filtros AR y competir en minijuegos. El mensaje de fondo: *"Esto lo aprenderás a hacer tú en 1º de DAW."*

---

## Qué hace la aplicación

### Detección facial base
- **468 puntos faciales** en tiempo real (MediaPipe FaceMesh)
- Hasta 4 caras simultáneas
- Detección de pestañeos (algoritmo EAR — Eye Aspect Ratio)
- Detección de sonrisa (ratio de elevación de comisuras)
- Detección de boca abierta (MAR — Mouth Aspect Ratio)
- Detección de guiños (ojo izquierdo / derecho independientes)
- Badge de emoción automático: 😂 Carcajada / 😮 Sorprendido / 😄 Sonriendo

### Filtros AR (activables individualmente)

| Filtro | Descripción |
|---|---|
| 🕸️ Malla | 468 puntos de la malla facial con colores por zona |
| 🕶️ Gafas | Gafas de sol AR ajustadas a los iris (posición dinámica) |
| 🎩 Sombrero | Sombrero de copa proporcional al tamaño de la cara |
| 😄 Sonrisa | Detector con indicador visual |
| 😮 Boca | Detector de boca abierta con indicador |
| 🌈 Fondo | Fondo cambia de color según el número de caras detectadas |

### Minijuegos (panel lateral)

#### 🎯 Simón Dice
Te pide un gesto aleatorio. Tienes 3.5 segundos para hacerlo. La barra de tiempo cambia de verde a rojo. Puntuación acumulada por gestos correctos.

Gestos posibles:
- 😄 Sonríe
- 😮 Abre la boca
- 😉 Guiña el ojo izquierdo
- 😉 Guiña el ojo derecho
- 😑 Cierra los dos ojos

#### 🧊 No te muevas
Fija tu posición al pulsar Iniciar. Tienes 100 puntos que van bajando según cuánto te muevas. Barra visual verde → rojo. Game Over al llegar a 0.

#### 👁️ Sin pestañear
Cronómetro que mide cuántos segundos aguantas sin pestañear. Se reinicia automáticamente al detectar un pestañeo. Guarda el récord de la sesión.

#### ⚔️ Duelo 2P
Dos personas frente a la cámara. 30 segundos de cuenta atrás. Contador independiente de pestañeos por cara. Quien más pestañee... **pierde**. Declara ganador y perdedor al terminar.

---

## Layout de la interfaz

```
┌─────────────────────────┬──────────────────────┐
│                         │  ⚡ JUEGOS            │
│     CÁMARA EN VIVO      │  ─────────────────── │
│     (560×420 px)        │  ⏹ Sin juego         │
│                         │  🎯 Simón Dice  ◀    │
│  [filtros AR activos]   │  🧊 No te muevas     │
│                         │  👁️ Sin pestañear    │
├─────────────────────────│  ⚔️ Duelo 2P         │
│ 🕸️ 🕶️ 🎩 😄 😮 🌈    │  ─────────────────── │
├─────────────────────────│  [contenido del      │
│ CARAS PESTAÑEOS 😄 😮   │   juego activo]      │
└─────────────────────────┴──────────────────────┘
```

---

## Concepto técnico

La demo usa **MediaPipe FaceMesh** (Google), una biblioteca de IA que funciona 100% en el navegador con JavaScript. No requiere backend ni sube datos a ningún servidor.

### Tecnologías usadas

| Tecnología | Rol |
|---|---|
| HTML5 + CSS | Estructura y diseño de la página |
| JavaScript (vanilla) | Lógica AR, algoritmos EAR/MAR, juegos |
| MediaPipe FaceMesh | Modelo de IA para detección facial (CDN) |
| Canvas API | Dibujo de puntos y filtros AR sobre el vídeo |
| getUserMedia | Captura de vídeo en tiempo real |

### Algoritmos clave

**EAR (Eye Aspect Ratio)** — Detecta pestañeos y guiños:
```
EAR = (dist_vertical_1 + dist_vertical_2) / (2 × dist_horizontal)
Si EAR < 0.22 durante ≥ 2 frames → ojo cerrado
```

**MAR (Mouth Aspect Ratio)** — Detecta boca abierta:
```
MAR = dist(labio_superior, labio_inferior) / dist(comisura_izq, comisura_dcha)
Si MAR > 0.5 → boca abierta
```

**Smile Ratio** — Detecta sonrisa:
```
Mide la elevación de las comisuras de la boca relativa al centro de la cara
Si ratio > 0.04 → sonrisa detectada
```

---

## Montaje del stand

### Material necesario

| Elemento | Observaciones |
|---|---|
| Portátil o iMac con webcam | Chrome o Edge actualizado |
| Monitor externo grande (opcional) | Para efecto llamada desde lejos |
| Conexión a Internet | Solo para cargar MediaPipe desde CDN la 1ª vez |

> **Sin Internet:** Descargar archivos de MediaPipe localmente y servirlos con `python3 -m http.server 8080`.

### Preparación previa

1. Abrir `index.html` en Chrome/Edge
2. Aceptar permisos de cámara
3. Verificar que los puntos faciales se dibujan correctamente
4. Dejar activos todos los filtros AR (estado por defecto)

---

## Guion del monitor

**[Al acercarse alguien]**
> "¿Has visto los filtros de Instagram o Snapchat? Esto es exactamente lo mismo, pero hecho por nosotros con JavaScript. Mira — ponte delante."

**[Mientras ven la demo]**
> "Está detectando 468 puntos de tu cara en tiempo real. Las gafas y el sombrero se ajustan solos a donde están tus ojos y tu cabeza. También cuenta los pestañeos — mira, pestañea."

**[Para engancharlo más]**
> "¿Quieres jugar? Tenemos un juego de Simón Dice que te pide gestos, uno de aguantar sin pestañear, y un duelo con otro visitante."

**[Para cerrar]**
> "Si te gusta crear cosas así — webs, apps, juegos — eso es exactamente lo que hacemos en Desarrollo de Aplicaciones Web. En el primer año ya trabajas con esto."

---

## Estructura de archivos

```
Sistemas/Taller/
├── index.html                        ← Aplicación completa (abrir en Chrome)
└── taller-feria-deteccion-cara-ia.md ← Esta documentación
```

---

## Variantes avanzadas (futuras ampliaciones)

| Idea | Dificultad | Descripción |
|---|---|---|
| Esquivar objetos con la cabeza | Media | La posición de la nariz controla un personaje |
| Detección de manos | Baja | MediaPipe Hands, mismo stack |
| Emociones reales (CNN) | Alta | Modelo de clasificación sobre los puntos |
| Multijugador en red | Alta | Socket.io + dos navegadores |

---

*Taller documentado para la Feria de Orientación del IES Kursaal — CFGS DAW 2025-2026*

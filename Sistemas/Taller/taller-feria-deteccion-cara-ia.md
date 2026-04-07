# Taller: "Hackea tu cara con IA" 🎭
## Feria de orientación — CFGS Desarrollo de Aplicaciones Web

**Público objetivo:** Alumnos de 16 años (4º ESO / 1º Bachillerato)
**Duración:** 10–15 minutos por turno
**Dificultad para el visitante:** Ninguna — solo observan e interactúan

---

## Objetivo del taller

Demostrar en tiempo real cómo una aplicación web puede detectar caras, gestos y expresiones usando la cámara del ordenador, **sin instalar nada**, directamente en el navegador.

El visitante ve su propia cara siendo analizada por IA en tiempo real. El impacto es inmediato y genera preguntas. El mensaje de fondo: *"Esto lo aprenderás a hacer tú en 1º de DAW."*

---

## Concepto técnico

La demo usa **MediaPipe Face Mesh** (Google), una biblioteca de IA que funciona en el navegador con JavaScript. Detecta hasta 468 puntos del rostro en tiempo real a través de la webcam.

No requiere backend, no sube datos a ningún servidor. Todo ocurre en local, en el navegador.

### Tecnologías usadas

| Tecnología | Rol |
|---|---|
| HTML5 + CSS | Estructura y diseño de la página |
| JavaScript (vanilla) | Lógica de la aplicación |
| MediaPipe FaceMesh | Modelo de IA para detección facial |
| Canvas API | Dibujo de los puntos sobre el vídeo |
| Webcam (getUserMedia) | Captura de vídeo en tiempo real |

---

## Demo: funcionamiento

1. La página abre la cámara del ordenador
2. MediaPipe analiza cada fotograma en tiempo real
3. Se dibujan los 468 puntos del mapa facial sobre la imagen
4. Efectos adicionales opcionales:
   - Cambio de color de fondo según la emoción detectada
   - Contador de parpadeos
   - Dibujo de máscara o filtro encima de la cara

### Código base (HTML + JS)

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Hackea tu cara con IA</title>
  <style>
    body { margin: 0; background: #111; display: flex; justify-content: center; align-items: center; height: 100vh; flex-direction: column; }
    h1 { color: #00ff88; font-family: monospace; font-size: 1.5rem; margin-bottom: 1rem; }
    .container { position: relative; }
    video { display: block; transform: scaleX(-1); border-radius: 12px; }
    canvas { position: absolute; top: 0; left: 0; transform: scaleX(-1); }
    p { color: #aaa; font-family: monospace; margin-top: 0.5rem; text-align: center; font-size: 0.9rem; }
  </style>
</head>
<body>
  <h1>🎭 Hackea tu cara con IA</h1>
  <div class="container">
    <video id="video" width="640" height="480" autoplay playsinline></video>
    <canvas id="canvas" width="640" height="480"></canvas>
  </div>
  <p>468 puntos · tiempo real · solo con JavaScript · hecho en DAW</p>

  <!-- MediaPipe desde CDN, sin instalación -->
  <script src="https://cdn.jsdelivr.net/npm/@mediapipe/camera_utils/camera_utils.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@mediapipe/drawing_utils/drawing_utils.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@mediapipe/face_mesh/face_mesh.js"></script>

  <script>
    const video = document.getElementById('video');
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');

    const faceMesh = new FaceMesh({
      locateFile: (file) => `https://cdn.jsdelivr.net/npm/@mediapipe/face_mesh/${file}`
    });

    faceMesh.setOptions({
      maxNumFaces: 2,
      refineLandmarks: true,
      minDetectionConfidence: 0.5,
      minTrackingConfidence: 0.5
    });

    faceMesh.onResults((results) => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      if (results.multiFaceLandmarks) {
        for (const landmarks of results.multiFaceLandmarks) {
          // Dibuja los puntos del mapa facial
          drawConnectors(ctx, landmarks, FACEMESH_TESSELATION,
            { color: '#00FF8855', lineWidth: 1 });
          drawConnectors(ctx, landmarks, FACEMESH_RIGHT_EYE,
            { color: '#FF3030', lineWidth: 2 });
          drawConnectors(ctx, landmarks, FACEMESH_LEFT_EYE,
            { color: '#30FF30', lineWidth: 2 });
          drawConnectors(ctx, landmarks, FACEMESH_LIPS,
            { color: '#E0E0FF', lineWidth: 2 });
        }
      }
    });

    const camera = new Camera(video, {
      onFrame: async () => {
        await faceMesh.send({ image: video });
      },
      width: 640,
      height: 480
    });

    camera.start();
  </script>
</body>
</html>
```

---

## Montaje del stand

### Material necesario

| Elemento | Observaciones |
|---|---|
| Portátil o iMac con webcam | Con Chrome o Edge actualizado |
| Monitor externo grande (opcional) | Para que se vea desde lejos — efecto llamada |
| Cartel impreso o pantalla secundaria | Con el QR y el mensaje del taller |
| Conexión a Internet | Solo para cargar las librerías de CDN la primera vez |

> **Sin Internet:** Descargar los archivos de MediaPipe y servirlos en local con `python3 -m http.server 8080`.

### Preparación previa

1. Abrir el archivo `index.html` en Chrome/Edge
2. Aceptar permisos de cámara
3. Verificar que los puntos se dibujan correctamente
4. (Opcional) Conectar monitor externo en modo espejo para pantalla grande

---

## Guion del monitor (cómo explicarlo)

**[Al acercarse alguien]**
> "¿Has visto alguna vez cómo funciona el filtro de Snapchat o Instagram? Esto es exactamente lo mismo, pero hecho por nosotros con JavaScript. Mira — ponte delante."

**[Mientras ven la demo]**
> "Está detectando 468 puntos de tu cara en tiempo real. Todo esto corre en el navegador, sin ninguna app instalada. Nosotros aprendemos a hacer esto en el primer año del grado."

**[Para cerrar]**
> "Si te gusta crear cosas que la gente pueda usar desde el móvil, la web o cualquier pantalla, eso es exactamente lo que hacemos en Desarrollo de Aplicaciones Web."

---

## Variantes y ampliaciones

| Variante | Dificultad de implementación | Impacto visual |
|---|---|---|
| Contar parpadeos en tiempo real | Media | Alto |
| Aplicar filtro/máscara (gafas, bigote) | Media-Alta | Muy alto |
| Detección de manos (MediaPipe Hands) | Baja (mismo stack) | Alto |
| Cambio de color de fondo por emoción | Alta (requiere modelo adicional) | Alto |
| Múltiples caras a la vez | Ya incluido (maxNumFaces: 2) | Medio |

---

## Mensaje para el visitante

> **"En DAW aprendes a construir esto."**
>
> No solo a usarlo. A programarlo, modificarlo, desplegarlo y publicarlo en Internet.
> El grado dura 2 años. En el primero ya trabajas con JavaScript, Java, bases de datos y redes.
> En el segundo haces prácticas en empresa.

---

## Archivos del proyecto

```
taller-feria-ia/
├── index.html          ← Demo principal (código en este documento)
├── README.md           ← Este archivo
└── offline/            ← (Opcional) librerías MediaPipe descargadas para usar sin Internet
```

---

*Taller documentado para la Feria de Orientación del IES Kursaal — CFGS DAW 2025-2026*

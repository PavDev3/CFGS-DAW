# Apuntes LMSGI — Febrero 2026

← [[LenguajeDeMarca]]

> Recopilación de apuntes basada en el material del profesor Francisco Jesús Torres Villarroya
> Extraído de Classroom y vídeos de YouTube — 5 de marzo de 2026

---

## 1. Historia de Internet

### Línea temporal

| Año | Evento |
|-----|--------|
| **1969** | ARPANET — Primer prototipo de red (Departamento de Defensa EE.UU.) |
| **1971** | Primer email enviado por Ray Tomlinson |
| **1983** | TCP/IP se convierte en protocolo estándar |
| **1989** | Tim Berners-Lee propone la World Wide Web |
| **1991** | Primera página web pública |
| **1993** | NCSA Mosaic — Primer navegador gráfico popular |
| **1998** | Google fundado |
| **2004** | Web 2.0 — Redes sociales y contenido generado por usuarios |

### Conceptos clave
- **ARPANET:** Red militar que conectaba universidades, precursora de Internet
- **TCP/IP:** Lenguaje común que permite que los ordenadores se comuniquen
- **Paquetes de datos:** Información dividida en pequeñas partes para su transmisión

---

## 2. Origen de la WWW

La World Wide Web fue inventada por **Tim Berners-Lee** en 1989 en el CERN (Suiza).

### Los 3 pilares de la WWW

1. **HTML** — Lenguaje para crear páginas web, estructura el contenido
2. **URL** — Dirección única de cada recurso (protocolo + dominio + ruta)
3. **HTTP** — Protocolo de comunicación (ahora HTTPS, versión segura)

### Evolución de la Web

| Web 1.0 | Web 2.0 | Web 3.0 |
|---------|---------|---------|
| Solo lectura | Lectura/escritura | Descentralizada |
| Páginas estáticas | Contenido dinámico | Blockchain, IA |
| Unidireccional | Redes sociales | Semántica |
| 1990-2004 | 2004-presente | En desarrollo |

---

## 3. Transiciones y Animaciones CSS

### Transiciones CSS

Las transiciones cambian suavemente de un estado a otro.

```css
.elemento {
  /* Propiedad | Duración | Curva | Retardo */
  transition: all 0.3s ease-in-out 0.1s;
}

.elemento:hover {
  background-color: blue;
  transform: scale(1.1);
}
```

#### Propiedades de transición

| Propiedad | Descripción | Ejemplo |
|-----------|-------------|---------|
| `transition-property` | Qué propiedad animar | `all`, `opacity`, `transform` |
| `transition-duration` | Cuánto dura | `0.3s`, `300ms` |
| `transition-timing-function` | Curva de velocidad | `ease`, `linear`, `ease-in-out` |
| `transition-delay` | Espera antes de empezar | `0.1s` |

#### Timing functions

```css
ease        /* Empieza lento, acelera, termina lento (por defecto) */
linear      /* Velocidad constante */
ease-in     /* Empieza lento */
ease-out    /* Termina lento */
ease-in-out /* Empieza y termina lento */
```

### Animaciones CSS (@keyframes)

```css
@keyframes rebote {
  0%   { transform: translateY(0); }
  50%  { transform: translateY(-20px); }
  100% { transform: translateY(0); }
}

.elemento {
  animation: rebote 1s ease-in-out infinite;
}
```

#### Propiedades de animación

| Propiedad | Descripción |
|-----------|-------------|
| `animation-name` | Nombre del @keyframes |
| `animation-duration` | Duración de un ciclo |
| `animation-iteration-count` | Repeticiones (`infinite`, número) |
| `animation-direction` | `normal`, `reverse`, `alternate` |
| `animation-fill-mode` | `forwards`, `backwards`, `both` |
| `animation-play-state` | `running`, `paused` |

---

## 4. Carruseles CSS Modernos (Chrome 135+)

A partir de Chrome 135, es posible crear carruseles **solo con CSS**, sin JavaScript.

### Ventajas
- Accesibilidad incluida (navegación por teclado, screen readers)
- Mejor rendimiento (nativo del navegador)
- Funciona sin JavaScript

### Estructura básica

```html
<ul class="carrusel">
  <li><img src="imagen1.jpg" alt="Imagen 1"></li>
  <li><img src="imagen2.jpg" alt="Imagen 2"></li>
</ul>
```

```css
.carrusel {
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  display: flex;
  gap: 10px;
}

.carrusel > li {
  scroll-snap-align: center;
  flex: 0 0 auto;
  width: 300px;
}
```

### Botones de scroll (::scroll-button)

```css
.carrusel {
  &::scroll-button(left)  { content: "⬅" / "Scroll Left"; }
  &::scroll-button(right) { content: "➡" / "Scroll Right"; }
}
```

### Marcadores (::scroll-marker)

```css
.carrusel {
  scroll-marker-group: after;
  > li::scroll-marker {
    content: ' ';
    width: 10px; height: 10px;
    border-radius: 50%;
    background: #ccc;
  }
  > li::scroll-marker:target-current {
    background: #3498db;
  }
}
```

---

## 5. Ejercicio Práctico: Carrusel con Botonera

Implementación de carrusel con botones de inicio, izquierda, derecha y final.

```html
<ul class="carrusel" id="carrusel">
  <li><img src="https://picsum.photos/200/150?random=1" alt="Imagen 1"></li>
  <!-- ... 10 imágenes en total -->
</ul>
<div class="botonera">
  <button onclick="irInicio()">⏮ Inicio</button>
  <button onclick="izquierda()">⬅ Izquierda</button>
  <button onclick="derecha()">➡ Derecha</button>
  <button onclick="irFinal()">⏭ Final</button>
</div>
```

```javascript
const carrusel = document.getElementById('carrusel');
const scrollAmount = 220;

function izquierda() { carrusel.scrollBy({ left: -scrollAmount, behavior: 'smooth' }); }
function derecha()   { carrusel.scrollBy({ left: scrollAmount, behavior: 'smooth' }); }
function irInicio()  { carrusel.scrollTo({ left: 0, behavior: 'smooth' }); }
function irFinal()   { carrusel.scrollTo({ left: carrusel.scrollWidth, behavior: 'smooth' }); }
```

---

## Enlaces de interés

| Recurso | Enlace |
|---------|--------|
| Historia de Internet | https://youtu.be/IwpHqDa0XEM |
| Origen WWW | https://youtu.be/o9y4sgV4oAE |
| Transiciones CSS | https://www.youtube.com/watch?v=sTh7iZyxHrw |
| Novedades CSS | https://www.youtube.com/watch?v=2dYocOPhVgY |
| Carruseles CSS | https://developer.chrome.com/blog/carousels-with-css |

# Apuntes LMSGI - Febrero 2026

> Recopilación de apuntes basada en el material del profesor Francisco Jesús Torres Villarroya
> Extraído de Classroom y vídeos de YouTube - 5 de marzo de 2026

---

## 📚 Índice

1. [Historia de Internet](#1-historia-de-internet)
2. [Origen de la WWW](#2-origen-de-la-www)
3. [Transiciones y Animaciones CSS](#3-transiciones-y-animaciones-css)
4. [Carruseles CSS Modernos](#4-carruseles-css-modernos)
5. [Ejercicio Práctico: Carrusel de Imágenes](#5-ejercicio-práctico-carrusel-de-imágenes)
6. [Pseudo-clases CSS](#6-pseudo-clases-css-)
7. [Pseudo-elementos CSS](#7-pseudo-elementos-css-)

---

## 1. Historia de Internet

### 🎬 Fuente: Vídeo "Historia de Internet - Su Origen y Evolución"
- **Canal:** Historias Para Un Cafe
- **Duración:** 10:21 min
- **Enlace:** https://youtu.be/IwpHqDa0XEM

### 📝 Resumen

Internet es una de las invenciones más importantes de la historia, herramienta que dio origen a las redes sociales, intercambio de información y evolución tecnológica.

#### Línea temporal:

| Año | Evento |
|-----|--------|
| **1969** | ARPANET - Primer prototipo de red (Departamento de Defensa EE.UU.) |
| **1971** | Primer email enviado por Ray Tomlinson |
| **1983** | TCP/IP se convierte en protocolo estándar |
| **1989** | Tim Berners-Lee propone la World Wide Web |
| **1991** | Primera página web pública |
| **1993** | NCSA Mosaic - Primer navegador gráfico popular |
| **1998** | Google fundado |
| **2004** | Web 2.0 - Redes sociales y contenido generado por usuarios |

#### Conceptos clave:

- **ARPANET:** Red militar que conectaba universidades, precursora de Internet
- **Protocolo TCP/IP:** Lenguaje común que permite que los ordenadores se comuniquen
- **Paquetes de datos:** Información dividida en pequeñas partes para su transmisión
- **Servidores:** Ordenadores que almacenan y sirven información
- **Clientes:** Dispositivos que acceden a esa información (navegadores)

---

## 2. Origen de la WWW

### 🎬 Fuente: Vídeo "El día que NACIÓ INTERNET - Origen WWW"
- **Canal:** Documental
- **Enlace:** https://youtu.be/o9y4sgV4oAE

### 📝 Resumen

La World Wide Web (WWW) fue inventada por **Tim Berners-Lee** en 1989 mientras trabajaba en el CERN (Suiza).

#### Los 3 pilares de la WWW:

1. **HTML (HyperText Markup Language)**
   - Lenguaje para crear páginas web
   - Estructura el contenido
   - Define elementos: títulos, párrafos, enlaces, imágenes

2. **URL (Uniform Resource Locator)**
   - Dirección única de cada recurso en la web
   - Ejemplo: `https://www.ejemplo.com/pagina.html`
   - Partes: protocolo + dominio + ruta

3. **HTTP (HyperText Transfer Protocol)**
   - Protocolo de comunicación
   - Define cómo se transfieren los datos
   - Ahora se usa HTTPS (versión segura)

#### Evolución de la Web:

| Web 1.0 | Web 2.0 | Web 3.0 |
|---------|---------|---------|
| Solo lectura | Lectura/escritura | Descentralizada |
| Páginas estáticas | Contenido dinámico | Blockchain, IA |
| Unidireccional | Redes sociales | Semántica |
| 1990-2004 | 2004-presente | En desarrollo |

---

## 3. Transiciones y Animaciones CSS

### 🎬 Fuente: Vídeo "CSS 12 - Transiciones & Animaciones"
- **Enlace:** https://www.youtube.com/watch?v=sTh7iZyxHrw

### 📝 Resumen

Las transiciones y animaciones permiten crear efectos visuales fluidos sin JavaScript.

### Transiciones CSS

Las transiciones cambian suavemente de un estado a otro.

#### Sintaxis básica:

```css
.elemento {
  /* Propiedad a animar | Duración | Curva de velocidad | Retardo */
  transition: all 0.3s ease-in-out 0.1s;
}

.elemento:hover {
  background-color: blue;
  transform: scale(1.1);
}
```

#### Propiedades de transición:

| Propiedad | Descripción | Ejemplo |
|-----------|-------------|---------|
| `transition-property` | Qué propiedad animar | `all`, `opacity`, `transform` |
| `transition-duration` | Cuánto dura | `0.3s`, `300ms` |
| `transition-timing-function` | Curva de velocidad | `ease`, `linear`, `ease-in-out` |
| `transition-delay` | Espera antes de empezar | `0.1s` |

#### Timing functions:

```css
ease        /* Empieza lento, acelera, termina lento (por defecto) */
linear      /* Velocidad constante */
ease-in     /* Empieza lento */
ease-out    /* Termina lento */
ease-in-out /* Empieza y termina lento */
cubic-bezier() /* Curva personalizada */
```

### Animaciones CSS (@keyframes)

Las animaciones permiten secuencias más complejas con múltiples estados.

```css
@keyframes rebote {
  0% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-20px);
  }
  100% {
    transform: translateY(0);
  }
}

.elemento {
  animation: rebote 1s ease-in-out infinite;
}
```

#### Propiedades de animación:

| Propiedad | Descripción |
|-----------|-------------|
| `animation-name` | Nombre del @keyframes |
| `animation-duration` | Duración de un ciclo |
| `animation-timing-function` | Curva de velocidad |
| `animation-delay` | Retardo inicial |
| `animation-iteration-count` | Repeticiones (`infinite`, número) |
| `animation-direction` | `normal`, `reverse`, `alternate` |
| `animation-fill-mode` | `forwards`, `backwards`, `both` |
| `animation-play-state` | `running`, `paused` |

#### Ejemplo práctico - Botón animado:

```css
.boton {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.boton:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
}

.boton:active {
  transform: translateY(0);
}
```

---

## 4. Carruseles CSS Modernos

### 🎬 Fuente: Vídeo "Estas Novedades de CSS son INCREÍBLES"
- **Enlace:** https://www.youtube.com/watch?v=2dYocOPhVgY

### 📄 Fuente técnica: Chrome Developers Blog
- **Artículo:** "Carousels with CSS"
- **Enlace:** https://developer.chrome.com/blog/carousels-with-css

### 📝 Resumen

A partir de Chrome 135, es posible crear carruseles **solo con CSS**, sin JavaScript.

#### Ventajas:
- ✅ Accesibilidad incluida (navegación por teclado, screen readers)
- ✅ Mejor rendimiento (nativo del navegador)
- ✅ Funciona sin JavaScript
- ✅ Menos código, menos errores

### Estructura básica:

```html
<ul class="carrusel">
  <li><img src="imagen1.jpg" alt="Imagen 1"></li>
  <li><img src="imagen2.jpg" alt="Imagen 2"></li>
  <li><img src="imagen3.jpg" alt="Imagen 3"></li>
  <!-- ... más imágenes -->
</ul>
```

### CSS para el contenedor:

```css
.carrusel {
  overflow-x: auto;           /* Permite scroll horizontal */
  scroll-snap-type: x mandatory; /* Snap obligatorio */
  display: flex;
  gap: 10px;
  padding: 20px;
}

.carrusel > li {
  scroll-snap-align: center;  /* Cada imagen se centra */
  flex: 0 0 auto;             /* No se estiran */
  width: 300px;               /* Ancho fijo */
}
```

### Añadir botones de scroll (::scroll-button)

```css
.carrusel {
  /* ... estilos anteriores ... */
  
  &::scroll-button(left) {
    content: "⬅" / "Scroll Left";
    position: absolute;
    left: 10px;
  }
  
  &::scroll-button(right) {
    content: "➡" / "Scroll Right";
    position: absolute;
    right: 10px;
  }
  
  &::scroll-button(*):focus-visible {
    outline-offset: 5px;
  }
}
```

### Añadir marcadores (::scroll-marker)

Los marcadores son puntos indicadores de posición.

```css
.carrusel {
  scroll-marker-group: after;  /* Marcadores después del carrusel */
  
  > li::scroll-marker {
    content: ' ';  /* Punto vacío */
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background: #ccc;
  }
  
  > li::scroll-marker:target-current {
    background: #3498db;  /* Punto activo */
  }
}
```

### Características importantes:

| Característica | Descripción |
|----------------|-------------|
| `::scroll-button()` | Crea botones de navegación |
| `::scroll-marker` | Crea indicadores de posición |
| `:target-current` | Estado del marcador activo |
| `scroll-snap-type` | Control del comportamiento de snap |
| Accesibilidad | Navegación por teclado incluida |

### Ventajas sobre JavaScript:

- **Menos código:** No necesitas librerías externas
- **Mejor CLS:** Sin cambios de layout al cargar
- **Accesibilidad:** Roles ARIA automáticos
- **Rendimiento:** Animaciones nativas del navegador
- **Touch-friendly:** Gestos táctiles incluidos

---

## 5. Ejercicio Práctico: Carrusel de Imágenes

### 📋 Enunciado del profesor:

> "Implementar un carrusel de imágenes, con los requisitos explicados en clase: botonera externa con botones de izquierda, derecha, inicio y final. Mínimo diez imágenes."

### 🔗 Recursos:
- Documentación: https://developer.chrome.com/blog/carousels-with-css

### 💡 Solución propuesta:

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Carrusel de Imágenes</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    
    body {
      font-family: Arial, sans-serif;
      padding: 20px;
      background: #f5f5f5;
    }
    
    h1 {
      text-align: center;
      margin-bottom: 20px;
    }
    
    .contenedor-carrusel {
      position: relative;
      max-width: 800px;
      margin: 0 auto;
    }
    
    .carrusel {
      display: flex;
      overflow-x: auto;
      scroll-snap-type: x mandatory;
      scroll-behavior: smooth;
      gap: 10px;
      padding: 10px;
      background: white;
      border-radius: 10px;
      box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    }
    
    .carrusel::-webkit-scrollbar {
      height: 8px;
    }
    
    .carrusel::-webkit-scrollbar-thumb {
      background: #3498db;
      border-radius: 4px;
    }
    
    .carrusel li {
      list-style: none;
      flex: 0 0 auto;
      scroll-snap-align: center;
    }
    
    .carrusel img {
      width: 200px;
      height: 150px;
      object-fit: cover;
      border-radius: 5px;
      transition: transform 0.3s ease;
    }
    
    .carrusel img:hover {
      transform: scale(1.05);
    }
    
    /* Botonera externa */
    .botonera {
      display: flex;
      justify-content: center;
      gap: 10px;
      margin-top: 15px;
    }
    
    .botonera button {
      padding: 10px 15px;
      font-size: 16px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      background: #3498db;
      color: white;
      transition: background 0.3s ease;
    }
    
    .botonera button:hover {
      background: #2980b9;
    }
    
    .botonera button:active {
      transform: scale(0.95);
    }
  </style>
</head>
<body>
  <h1>Carrusel de Imágenes</h1>
  
  <div class="contenedor-carrusel">
    <ul class="carrusel" id="carrusel">
      <li><img src="https://picsum.photos/200/150?random=1" alt="Imagen 1"></li>
      <li><img src="https://picsum.photos/200/150?random=2" alt="Imagen 2"></li>
      <li><img src="https://picsum.photos/200/150?random=3" alt="Imagen 3"></li>
      <li><img src="https://picsum.photos/200/150?random=4" alt="Imagen 4"></li>
      <li><img src="https://picsum.photos/200/150?random=5" alt="Imagen 5"></li>
      <li><img src="https://picsum.photos/200/150?random=6" alt="Imagen 6"></li>
      <li><img src="https://picsum.photos/200/150?random=7" alt="Imagen 7"></li>
      <li><img src="https://picsum.photos/200/150?random=8" alt="Imagen 8"></li>
      <li><img src="https://picsum.photos/200/150?random=9" alt="Imagen 9"></li>
      <li><img src="https://picsum.photos/200/150?random=10" alt="Imagen 10"></li>
    </ul>
    
    <div class="botonera">
      <button onclick="irInicio()">⏮ Inicio</button>
      <button onclick="izquierda()">⬅ Izquierda</button>
      <button onclick="derecha()">➡ Derecha</button>
      <button onclick="irFinal()">⏭ Final</button>
    </div>
  </div>
  
  <script>
    const carrusel = document.getElementById('carrusel');
    const scrollAmount = 220; // Ancho de imagen + gap
    
    function izquierda() {
      carrusel.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
    }
    
    function derecha() {
      carrusel.scrollBy({ left: scrollAmount, behavior: 'smooth' });
    }
    
    function irInicio() {
      carrusel.scrollTo({ left: 0, behavior: 'smooth' });
    }
    
    function irFinal() {
      carrusel.scrollTo({ left: carrusel.scrollWidth, behavior: 'smooth' });
    }
  </script>
</body>
</html>
```

---

## 6. Pseudo-clases CSS (`:`)

Las pseudo-clases seleccionan elementos según su **estado** o **posición** en el DOM.

### Pseudo-clases de estado

| Pseudo-clase | Qué hace | Ejemplo |
|--------------|----------|---------|
| `:hover` | Al pasar el ratón por encima | `button:hover { background: red; }` |
| `:focus` | Cuando el elemento tiene el foco | `input:focus { border: 2px solid blue; }` |
| `:active` | Mientras se hace clic | `a:active { color: green; }` |
| `:visited` | Enlace ya visitado | `a:visited { color: purple; }` |
| `:checked` | Checkbox/radio marcado | `input:checked { accent-color: green; }` |
| `:disabled` | Elemento deshabilitado | `button:disabled { opacity: 0.5; }` |
| `:enabled` | Elemento habilitado | `input:enabled { background: white; }` |
| `:focus-visible` | Foco visible (por teclado) | `button:focus-visible { outline: 2px solid blue; }` |

### Pseudo-clases de posición (estructurales)

| Pseudo-clase | Qué hace | Ejemplo |
|--------------|----------|---------|
| `:first-child` | Primer hijo de su padre | `li:first-child { font-weight: bold; }` |
| `:last-child` | Último hijo de su padre | `li:last-child { border: none; }` |
| `:nth-child(n)` | Enésimo hijo | `tr:nth-child(odd) { background: #f0f0f0; }` |
| `:nth-child(even)` | Hijos pares | `li:nth-child(even) { background: #eee; }` |
| `:nth-child(3n)` | Cada 3 elementos | `li:nth-child(3n) { color: red; }` |
| `:only-child` | Único hijo (sin hermanos) | `p:only-child { margin: 0; }` |
| `:first-of-type` | Primero de su tipo | `p:first-of-type { font-size: 1.2em; }` |
| `:last-of-type` | Último de su tipo | `p:last-of-type { margin-bottom: 0; }` |

### Pseudo-clases funcionales

#### `:not(selector)` - Excluye elementos

Selecciona todo **excepto** lo que coincida con el selector.

```css
/* Todos los párrafos excepto los con clase 'especial' */
p:not(.especial) {
  color: gray;
}

/* Todos los inputs excepto checkbox y radio */
input:not([type="checkbox"]):not([type="radio"]) {
  width: 100%;
}
```

#### `:is(selector1, selector2, ...)` - Agrupa selectores

Agrupa múltiples selectores en uno. Útil para no repetir código.

```css
/* Sin :is() */
h1, h2, h3, h4, h5, h6 {
  color: blue;
}

/* Con :is() - más limpio */
:is(h1, h2, h3, h4, h5, h6) {
  color: blue;
}

/* Combina con otros selectores */
article :is(h1, h2, h3) {
  margin-top: 20px;
}
```

#### `:has(selector)` - Tiene descendiente

Selecciona un elemento si **contiene** otro elemento. Muy potente.

```css
/* Sección que tiene una imagen */
section:has(img) {
  padding: 20px;
  background: #f5f5f5;
}

/* Card que tiene un badge */
.card:has(.badge) {
  position: relative;
}

/* Párrafo que contiene un enlace */
p:has(a) {
  cursor: pointer;
}

/* Formulario con campos inválidos */
form:has(:invalid) button[type="submit"] {
  opacity: 0.5;
  cursor: not-allowed;
}
```

#### `:where(selector)` - Igual que `:is()` pero sin especificidad

```css
/* :where() tiene especificidad 0, útil para estilos base */
:where(h1, h2, h3) {
  margin-bottom: 0.5em;
}
```

---

## 7. Pseudo-elementos CSS (`::`)

Los pseudo-elementos **crean elementos virtuales** que no existen en el HTML.

### Pseudo-elementos de contenido

#### `::before` y `::after`

Insertan contenido antes o después del elemento. **Requieren `content`**.

```css
/* Añadir icono antes */
.aviso::before {
  content: "⚠️ ";
  color: orange;
}

/* Añadir texto después */
.obligatorio::after {
  content: " *";
  color: red;
}

/* Añadir comillas tipográficas */
blockquote::before {
  content: "«";
}
blockquote::after {
  content: "»";
}

/* Precio con símbolo */
.precio::before {
  content: "$";
}
```

#### Ejemplo práctico: botón con flecha

```css
.boton-flecha {
  padding: 10px 20px;
  background: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.boton-flecha::after {
  content: " →";
  transition: transform 0.2s;
}

.boton-flecha:hover::after {
  transform: translateX(5px);
}
```

### Pseudo-elementos de texto

#### `::first-line` - Primera línea

Aplica estilos solo a la primera línea de texto.

```css
p::first-line {
  font-weight: bold;
  color: #333;
}
```

#### `::first-letter` - Primera letra

Útil para capitulares (letras grandes al inicio).

```css
p::first-letter {
  font-size: 3em;
  float: left;
  line-height: 1;
  margin-right: 5px;
  color: #3498db;
}
```

### Pseudo-elementos de formulario

#### `::placeholder` - Texto placeholder

```css
input::placeholder {
  color: #999;
  font-style: italic;
}

input:focus::placeholder {
  color: transparent;
}
```

### Pseudo-elementos de selección

#### `::selection` - Texto seleccionado

```css
::selection {
  background: #3498db;
  color: white;
}

/* Solo en párrafos específicos */
.especial::selection {
  background: orange;
}
```

---

## 💡 Diferencia clave: Pseudo-clase vs Pseudo-elemento

```
:Pseudo-clase     → Selecciona elementos EXISTENTES (por estado/posición)
::Pseudo-elemento → CREA elementos virtuales NUEVOS
```

### Resumen visual

| Sintaxis | Nombre | ¿Qué hace? | Ejemplos |
|----------|--------|------------|----------|
| `:` | Pseudo-clase | Filtra elementos existentes | `:hover`, `:first-child`, `:not()` |
| `::` | Pseudo-elemento | Crea elementos virtuales | `::before`, `::after`, `::placeholder` |

### Ejemplo combinado

```css
/* Tarjeta con pseudo-clases y pseudo-elementos */
.tarjeta {
  position: relative;
  padding: 20px;
  background: white;
  border-radius: 8px;
  transition: box-shadow 0.3s ease;
}

/* Pseudo-elemento: barra decorativa lateral */
.tarjeta::before {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 4px;
  height: 100%;
  background: #3498db;
  border-radius: 8px 0 0 8px;
}

/* Pseudo-clase: efecto al pasar el ratón */
.tarjeta:hover {
  box-shadow: 0 8px 24px rgba(0,0,0,0.15);
}

/* Pseudo-elemento: indicador en títulos dentro de tarjeta */
.tarjeta h2::after {
  content: "";
  display: block;
  width: 50px;
  height: 3px;
  background: #3498db;
  margin-top: 10px;
}

/* Pseudo-clase funcional: tarjetas que tienen imagen */
.tarjeta:has(img) {
  padding-top: 0;
}

.tarjeta:has(img) img {
  border-radius: 8px 8px 0 0;
}
```

---

## 📎 Enlaces de interés

| Recurso | Enlace |
|---------|--------|
| Historia de Internet (vídeo) | https://youtu.be/IwpHqDa0XEM |
| Origen WWW (vídeo) | https://youtu.be/o9y4sgV4oAE |
| Transiciones CSS (vídeo) | https://www.youtube.com/watch?v=sTh7iZyxHrw |
| Novedades CSS (vídeo) | https://www.youtube.com/watch?v=2dYocOPhVgY |
| Carruseles CSS (artículo) | https://developer.chrome.com/blog/carousels-with-css |
| Carousel Configurator | https://chrome.dev/carousel-configurator/ |
| Carousel Gallery | https://chrome.dev/carousel/ |

---

## 📝 Notas adicionales

- Los nuevos carruseles CSS nativos requieren Chrome 135+
- Para navegadores antiguos, usar JavaScript como fallback
- Siempre incluir atributos `alt` en las imágenes para accesibilidad
- Las animaciones deben ser sutiles para no distraer al usuario

---

*Apuntes generados automáticamente desde Classroom de LMSGI - 5 de marzo de 2026*

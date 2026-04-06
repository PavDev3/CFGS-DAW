# Pseudo-clases y Pseudo-elementos CSS

← [[LenguajeDeMarca]] | Ver también: [[LM-Apuntes-Feb2026]]

---

## Diferencia clave

```
:Pseudo-clase     → Selecciona elementos EXISTENTES (por estado/posición)
::Pseudo-elemento → CREA elementos virtuales NUEVOS
```

| Sintaxis | Nombre | ¿Qué hace? | Ejemplos |
|----------|--------|------------|----------|
| `:` | Pseudo-clase | Filtra elementos existentes | `:hover`, `:first-child`, `:not()` |
| `::` | Pseudo-elemento | Crea elementos virtuales | `::before`, `::after`, `::placeholder` |

---

## Pseudo-clases (`:`)

### De estado

| Pseudo-clase | Descripción | Ejemplo |
|--------------|-------------|---------|
| `:hover` | Al pasar el ratón | `button:hover { background: red; }` |
| `:focus` | Elemento con foco | `input:focus { border: 2px solid blue; }` |
| `:active` | Mientras se hace clic | `a:active { color: green; }` |
| `:visited` | Enlace visitado | `a:visited { color: purple; }` |
| `:checked` | Checkbox marcado | `input:checked { accent-color: green; }` |
| `:disabled` | Elemento deshabilitado | `button:disabled { opacity: 0.5; }` |
| `:focus-visible` | Foco visible (teclado) | `button:focus-visible { outline: 2px solid blue; }` |

### De posición (estructurales)

| Pseudo-clase | Descripción |
|--------------|-------------|
| `:first-child` | Primer hijo de su padre |
| `:last-child` | Último hijo de su padre |
| `:nth-child(n)` | Enésimo hijo |
| `:nth-child(even)` | Hijos pares |
| `:nth-child(3n)` | Cada 3 elementos |
| `:only-child` | Único hijo (sin hermanos) |
| `:first-of-type` | Primero de su tipo |
| `:last-of-type` | Último de su tipo |

### Funcionales

#### `:not()` — Excluye elementos

```css
/* Todos los párrafos excepto los con clase 'especial' */
p:not(.especial) { color: gray; }

/* Todos los inputs excepto checkbox y radio */
input:not([type="checkbox"]):not([type="radio"]) { width: 100%; }
```

#### `:is()` — Agrupa selectores

```css
/* Sin :is() */
h1, h2, h3, h4, h5, h6 { color: blue; }

/* Con :is() — más limpio */
:is(h1, h2, h3, h4, h5, h6) { color: blue; }
```

#### `:has()` — Tiene descendiente

```css
/* Sección que tiene una imagen */
section:has(img) { padding: 20px; }

/* Formulario con campos inválidos */
form:has(:invalid) button[type="submit"] {
  opacity: 0.5;
  cursor: not-allowed;
}
```

#### `:where()` — Como `:is()` pero sin especificidad

```css
/* Especificidad 0 — útil para estilos base */
:where(h1, h2, h3) { margin-bottom: 0.5em; }
```

---

## Pseudo-elementos (`::`)

### `::before` y `::after`

Insertan contenido antes o después del elemento. **Requieren `content`**.

```css
.aviso::before {
  content: "⚠️ ";
  color: orange;
}

.obligatorio::after {
  content: " *";
  color: red;
}

/* Añadir comillas tipográficas */
blockquote::before { content: "«"; }
blockquote::after  { content: "»"; }
```

### `::first-line` — Primera línea

```css
p::first-line {
  font-weight: bold;
  color: #333;
}
```

### `::first-letter` — Primera letra (capitular)

```css
p::first-letter {
  font-size: 3em;
  float: left;
  line-height: 1;
  margin-right: 5px;
  color: #3498db;
}
```

### `::placeholder` — Texto placeholder

```css
input::placeholder {
  color: #999;
  font-style: italic;
}

input:focus::placeholder {
  color: transparent;
}
```

### `::selection` — Texto seleccionado

```css
::selection {
  background: #3498db;
  color: white;
}
```

---

## Ejemplo combinado: Tarjeta con pseudo-clases y pseudo-elementos

```css
.tarjeta {
  position: relative;
  padding: 20px;
  background: white;
  border-radius: 8px;
  transition: box-shadow 0.3s ease;
}

/* Barra decorativa lateral */
.tarjeta::before {
  content: "";
  position: absolute;
  left: 0; top: 0;
  width: 4px; height: 100%;
  background: #3498db;
  border-radius: 8px 0 0 8px;
}

/* Efecto hover */
.tarjeta:hover {
  box-shadow: 0 8px 24px rgba(0,0,0,0.15);
}

/* Tarjetas que tienen imagen */
.tarjeta:has(img) { padding-top: 0; }
.tarjeta:has(img) img { border-radius: 8px 8px 0 0; }
```

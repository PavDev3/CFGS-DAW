# Apuntes rápidos LM

Ver archivo principal: **Apuntes_LMSGI_Feb2026.md**

---

## Resumen: Pseudo-clases vs Pseudo-elementos

| Sintaxis | Tipo | Qué hace |
|----------|------|----------|
| `:hover` | Pseudo-clase | Selecciona por **estado** |
| `::before` | Pseudo-elemento | **Crea** elemento virtual |

```
:Pseudo-clase     → Filtra elementos existentes
::Pseudo-elemento → Crea elementos virtuales nuevos
```

---

## Pseudo-clases comunes (`:`)

**Estado:** `:hover`, `:focus`, `:active`, `:checked`, `:disabled`

**Posición:** `:first-child`, `:last-child`, `:nth-child(n)`

**Funcionales:** `:not()`, `:is()`, `:has()`

---

## Pseudo-elementos comunes (`::`)

**Contenido:** `::before`, `::after` (requieren `content`)

**Texto:** `::first-line`, `::first-letter`

**Formulario:** `::placeholder`

**Selección:** `::selection`

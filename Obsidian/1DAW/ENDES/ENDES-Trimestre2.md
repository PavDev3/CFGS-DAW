# ENDES Trimestre 2 — Conceptos Clave

← [[ENDES]] | Ver también: [[ENDES-Scrum]]

---

## Herramientas de Diseño de BD

### DBML (Database Markup Language)
Lenguaje de marcado de código abierto para definir esquemas de bases de datos. Filosofía: **"Documentación como código"**.
- Facilita el diseño visual (diagramas ER) antes de programar en SQL
- Fácil de compartir y versionar en Git

### Dbdiagram.io
Herramienta web que interpreta código DBML y lo dibuja en tiempo real.
- Convierte archivos `.sql` a diagramas
- Genera código SQL para PostgreSQL/MySQL/SQL Server
- Genera enlaces públicos para trabajo en equipo

### RunSQL.com
Entorno web para ejecución y testeo SQL **sin servidores locales**.
- Izquierda: esquema y tablas
- Derecha: consultas y resultados
- Flujo correcto: introducir datos dummy primero para testear relaciones

---

## Calidad del Software

### QA (Quality Assurance)
Proceso **PROACTIVO** orientado al **PROCESO**.
- Previene defectos estableciendo estándares y metodologías
- Se hace ANTES de crear el software
- Mejora el proceso de desarrollo desde el inicio

### QC (Quality Control)
Proceso **REACTIVO** orientado al **PRODUCTO**.
- Identifica y corrige defectos del producto ya desarrollado
- Testeo, inspección de código, seguimiento de bugs
- Valida que el software cumple los requisitos antes de la entrega

### QE (Quality Engineering)
Disciplina integral que **combina QA y QC**.
- Incorpora la calidad en todo el ciclo de vida del desarrollo
- Se apoya en automatización de pruebas e integración continua
- La calidad es una **responsabilidad compartida**

#### Resumen

```
QA → Previene errores mejorando el PROCESO
QC → Detecta errores probando el PRODUCTO
QE → Automatiza e integra la calidad en todo el CICLO
```

---

## UI vs UX

### UI (User Interface)
- Se centra en el **CÓMO y la APARIENCIA**
- La parte visual y táctil con la que el usuario interactúa
- Incluye botones, iconos, tipografías, colores y diseño de pantalla
- Es una **PARTE** de la experiencia de usuario

### UX (User Experience)
- Se centra en el **PORQUÉ y el SENTIMIENTO** del usuario
- Proceso de crear productos con experiencias significativas
- El usuario encuentra solución a su problema fácil, lógica y satisfactoriamente
- Es el **TODO**: el conjunto de factores de la interacción

| | UI | UX |
|--|----|----|
| Foco | Producto/dispositivo | Usuario/persona |
| Objetivo | Look & feel | Feeling/sentimiento |
| Herramientas | Botones, colores | Usabilidad, análisis |
| Meta | Estética y guía visual | Efectividad y facilidad |

> Nota: **buena UI + mal UX = producto inútil**, y viceversa

---

## Roles de Gestión de Producto

### Product Manager (PM)
- "Dueño del producto" ante el mercado
- Visión a **LARGO PLAZO** orientada al **EXTERIOR** (mercado y clientes)
- Define estrategia del producto, asegura viabilidad económica

### Product Owner (PO)
- Visión a **CORTO/MEDIO PLAZO** orientada al **INTERIOR** (equipo de desarrollo)
- Rol específico dentro de Scrum
- Traduce la visión del PM en Historias de Usuario
- Gestiona y prioriza el Product Backlog

---

## Tipos de Pruebas de Software

### Pruebas Regresivas
- **Objetivo:** ESTABILIDAD
- Se realizan tras un cambio para asegurar que nada anterior se rompió

### Pruebas de Caja Blanca
- Se centran en el **CÓDIGO FUENTE** ("cómo se hace")
- Requieren conocer la programación
- Tipos:
  - **De cubrimiento:** Cada línea se ejecuta al menos una vez
  - **De condiciones:** Se prueban todos los resultados de cada condicional
  - **De bucles:** Se prueban los límites (máximo, máximo+1, máximo-1)

### Pruebas de Caja Negra
- Se centran en la **APLICACIÓN** ("qué hace"), ignorando el código
- Se prueban casos de uso y datos incorrectos desde la interfaz
- Tipos:
  - **Clases de equivalencia:** Clasifica datos en válidos e inválidos
  - **Valores límite:** Datos extremos (ej: edad 150 vs 25)
  - **Interfaces:** Evalúa la GUI y su usabilidad

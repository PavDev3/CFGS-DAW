# ADR-001 — Framework de Frontend

**Estado:** Aceptado  
**Fecha:** 2026-05-14  
**Autor:** Pablo Núñez Fernández

---

## Contexto

El proyecto requiere una aplicación web de página única (SPA) con las siguientes características:
- Múltiples vistas con navegación (dashboard, historial, alertas, admin).
- Actualización en tiempo real de datos vía WebSocket.
- Tablas con paginación, filtros y formularios complejos.
- Autenticación con JWT y control de acceso por rol.
- Componentes reutilizables (tablas, gráficas, formularios).

Se evaluaron tres opciones: Angular, React y Vue.

---

## Decisión

Se elige **Angular 17** como framework de frontend.

---

## Razones

1. **Conocimiento existente**: El desarrollador usa Angular profesionalmente, lo que reduce el tiempo de aprendizaje a cero y permite un nivel de implementación más alto.

2. **Todo incluido**: Angular provee de serie lo necesario para este proyecto — router, formularios reactivos, HTTP client con interceptores, inyección de dependencias, guards — sin necesidad de elegir y configurar librerías de terceros para cada función.

3. **Tipado fuerte con TypeScript**: Reduce errores en tiempo de desarrollo y facilita el mantenimiento. Especialmente útil cuando se manejan modelos de datos complejos (sensores, dispositivos, alertas).

4. **Estructura clara**: La arquitectura de módulos, componentes y servicios de Angular facilita la organización del código en un proyecto de esta envergadura y mejora la mantenibilidad.

5. **Angular Material**: Librería de componentes UI oficial que permite construir una interfaz profesional y consistente sin diseño desde cero.

---

## Consecuencias

**Positivas:**
- Menor tiempo de desarrollo al dominar el framework.
- Código más mantenible y estructurado.
- Intercepción de peticiones HTTP para JWT sin código repetitivo.

**Negativas:**
- Mayor tamaño del bundle inicial comparado con Vue o React (mitigable con lazy loading).
- Curva de aprendizaje alta para otros colaboradores no familiarizados con Angular.

---

## Alternativas descartadas

| Framework | Motivo de descarte |
|---|---|
| React | Requiere configurar librería de routing, formularios y estado por separado. Mayor complejidad de setup para este perfil. |
| Vue 3 | Menos usado en el mercado laboral del entorno del desarrollador. Sin ventaja técnica que justifique el cambio. |

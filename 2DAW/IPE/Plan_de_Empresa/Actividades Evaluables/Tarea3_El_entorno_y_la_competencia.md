# Tarea 3: El entorno y la competencia

**EL PLAN DE EMPRESA** — aplicado al proyecto **Oposia**

> Notas/esquema de referencia (24 nov 2026), no el texto final a entregar — redactar el párrafo con subepígrafes (macroentorno, microentorno, competencia, DAFO/CAME) con estas palabras, en propia voz.

## 1. Macroentorno (análisis PEST)

| Factor | Situación de Oposia |
|---|---|
| **Político** | Sector formación/oposiciones sin regulación específica fuerte, pero depende de que la administración siga convocando plazas públicas (estabilidad relativa, aunque las convocatorias fluctúan por legislatura). Pendiente de revisar si existe alguna subvención de digitalización o emprendimiento joven aplicable. |
| **Económico** | No hay crisis específica del sector — las oposiciones son contracíclicas: cuando sube el desempleo en el sector privado, aumenta la demanda de plazas públicas. El desempleo juvenil alto en España refuerza esa tendencia hacia la función pública. |
| **Social** | Cambio de hábito real: los opositores jóvenes ya usan apps (Anki, Quizlet) en vez de solo estudiar en papel. Se normaliza estudiar apoyándose en el móvil y en herramientas con IA. |
| **Tecnológico** | La IA generativa (LLMs) es el cambio clave que hace viable ahora mismo un producto que antes no era posible: generar preguntas y resúmenes personalizados sobre el temario concreto que sube cada usuario. |

## 2. Microentorno

- **Competencia actual:** pocas plataformas realmente adaptativas en español para oposiciones; los bancos de test estáticos sí son numerosos. Sector en crecimiento impulsado por la IA.
- **Nuevos competidores:** barrera de entrada media — hace falta un pipeline de IA bien verificado y contenido legal curado; no es trivial copiarlo rápido y bien.
- **Productos sustitutivos:** apuntes en papel, academias presenciales tradicionales, Anki en su versión genérica (sin contenido ni adaptación propia).
- **Proveedores:** dependencia de proveedores de modelos de IA (varios candidatos evaluados — Anthropic, Google, Cloudflare — sin atarse a uno solo).
- **Clientes:** el usuario individual tiene poco poder de negociación (precio fijo de suscripción); las academias (cliente B2B) sí pueden negociar por volumen de alumnos.

## 3. Entorno: ¿sencillo o cambiante?

**Cambiante.** El ritmo de avance de la IA generativa obliga a reevaluar proveedores y modelos cada pocos meses (varias decisiones de arquitectura del proyecto tratan justo de qué modelo de IA usar en cada momento). Mercado único por ahora (España, oposiciones en español), con posible expansión futura a certificaciones técnicas.

## 4. Competencia directa

| Competidor | Qué ofrece | Qué le falta |
|---|---|---|
| Anki | Repetición espaciada genérica | No genera contenido, no es adaptativo por tema, curva de aprendizaje alta |
| Bancos de test de oposiciones | Preguntas oficiales por convocatoria | Estáticos, sin personalización real, sin generación de contenido propio |
| Duolingo / apps de estudio genéricas | Buena UX adaptativa | No cubren temario legal/técnico español ni contenido subido por el propio usuario |

## 5. Análisis DAFO

| | Favorable | Desfavorable |
|---|---|---|
| **Interno** | **Fortalezas:** conocimiento técnico del fundador; diferenciación clara (contenido propio + IA adaptativa + generación de preguntas verificada con cita de origen) | **Debilidades:** equipo muy pequeño (un desarrollador), sin capital inicial, producto aún sin validar en mercado real |
| **Externo** | **Oportunidades:** mercado grande y recurrente (cada convocatoria genera una nueva oleada de opositores), oferta adaptativa real escasa en español, posible expansión B2B a academias | **Amenazas:** dependencia de proveedores de IA externos (coste y cambios de API), posible entrada de un competidor con más capital que copie la idea |

## 6. Estrategia CAME

- **Corregir:** validar con usuarios reales cuanto antes (MVP); buscar financiación o ayudas para compensar el poco capital inicial.
- **Afrontar:** diseñar el sistema para poder cambiar de proveedor de IA sin reescribir nada (ya es la política seguida en las decisiones de arquitectura del verificador del proyecto), para no depender de uno solo.
- **Mantener:** seguir invirtiendo en la verificación de calidad del contenido generado — es lo que diferencia a Oposia de un simple chatbot genérico.
- **Explotar:** ofrecer primero a academias pequeñas de oposiciones como piloto B2B, antes de escalar al mercado general.

---

Notas de referencia generadas a partir de `docs/01-planteamiento.md` del proyecto Oposia. No es el texto final a entregar.

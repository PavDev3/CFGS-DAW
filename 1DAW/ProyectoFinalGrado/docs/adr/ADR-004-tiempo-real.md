# ADR-004 — Comunicación en Tiempo Real

**Estado:** Aceptado  
**Fecha:** 2026-05-14  
**Autor:** Pablo Núñez Fernández

---

## Contexto

El dashboard necesita mostrar datos de nuevos dispositivos detectados y alertas en tiempo real, sin que el usuario tenga que recargar la página. Se evaluaron tres opciones:

- **Polling HTTP**: El frontend consulta la API periódicamente (ej. cada 2 segundos).
- **Server-Sent Events (SSE)**: El servidor envía eventos unidireccionales al cliente.
- **WebSocket (Socket.io)**: Canal bidireccional persistente entre cliente y servidor.

---

## Decisión

Se elige **Socket.io** (sobre WebSocket) para la comunicación en tiempo real.

---

## Razones

1. **Push real**: El servidor empuja los datos al cliente en el instante en que llegan del sensor ESP32, sin esperar a que el cliente pregunte. Esto garantiza latencia mínima.

2. **Bidireccionalidad**: Aunque el caso principal es servidor → cliente, la bidireccionalidad de WebSocket permite también enviar comandos desde el dashboard al backend en el futuro (ej. solicitar un escaneo inmediato).

3. **Integración con Node.js/Express**: Socket.io integra de forma nativa con Express y es la solución WebSocket más documentada y usada en el ecosistema Node.js.

4. **Fallback automático**: Socket.io hace fallback a long-polling si WebSocket no está disponible, mejorando la compatibilidad.

5. **Gestión de salas**: Socket.io permite enviar eventos solo a clientes suscritos a ciertos "rooms" (ej. solo al admin), útil para las alertas.

---

## Consecuencias

**Positivas:**
- Latencia mínima en la actualización del dashboard.
- Implementación sencilla tanto en el backend (Node.js) como en el frontend (ngx-socket-io para Angular).
- Escalable si en el futuro se añaden más tipos de eventos.

**Negativas:**
- Las conexiones WebSocket persistentes consumen más recursos del servidor que el polling simple. Para el volumen esperado (decenas de clientes simultáneos), no es un problema.
- Requiere que el frontend gestione la reconexión automática (Socket.io lo maneja por defecto).

---

## Alternativas descartadas

| Opción | Motivo de descarte |
|---|---|
| Polling HTTP | Introduce latencia artificial (hasta 2-5 segundos) y genera tráfico innecesario. No adecuado para un monitor de seguridad. |
| Server-Sent Events (SSE) | Unidireccional únicamente. No permite enviar comandos desde el cliente. Menor ecosistema de librerías para Angular. |

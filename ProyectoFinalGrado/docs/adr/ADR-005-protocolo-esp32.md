# ADR-005 — Protocolo de Comunicación ESP32 → Backend

**Estado:** Aceptado  
**Fecha:** 2026-05-14  
**Autor:** Pablo Núñez Fernández

---

## Contexto

Los sensores ESP32 necesitan enviar periódicamente los dispositivos detectados al backend. Se evaluaron tres protocolos:

- **HTTP REST**: El ESP32 hace peticiones POST al backend directamente.
- **MQTT**: El ESP32 publica mensajes en un broker; el backend se suscribe.
- **WebSocket directo**: El ESP32 abre una conexión WebSocket al backend.

---

## Decisión

Se elige **MQTT con broker Eclipse Mosquitto**.

---

## Razones

1. **Diseñado para IoT**: MQTT es el protocolo estándar en dispositivos IoT. Está optimizado para conexiones inestables, bajo ancho de banda y dispositivos con recursos limitados (exactamente el perfil del ESP32).

2. **Desacoplamiento**: El ESP32 publica en el broker sin necesitar saber la dirección del backend. El backend se suscribe al broker. Esto permite añadir o quitar sensores sin modificar el backend.

3. **QoS configurable**: MQTT permite niveles de calidad de servicio (QoS 0/1/2) para garantizar entrega de mensajes incluso con reconexiones.

4. **Librería madura para ESP32**: `PubSubClient` es la librería MQTT más usada en el ecosistema Arduino/ESP32, bien documentada y estable.

5. **Reconexión automática**: El protocolo MQTT gestiona reconexiones y last-will messages (permite saber cuándo un sensor se desconecta inesperadamente).

6. **Escalabilidad**: El broker MQTT puede gestionar cientos de sensores simultáneos sin sobrecargar el backend directamente.

---

## Consecuencias

**Positivas:**
- Arquitectura desacoplada y escalable.
- Gestión robusta de reconexiones y fallos de red.
- Estándar IoT ampliamente conocido, útil para el portfolio profesional.
- Last-will message permite detectar sensores caídos automáticamente.

**Negativas:**
- Introduce un componente adicional (broker Mosquitto) en la infraestructura. Gestionado con Docker Compose, el impacto es mínimo.
- Requiere configurar autenticación en el broker para evitar que cualquier dispositivo publique datos falsos.

---

## Alternativas descartadas

| Protocolo | Motivo de descarte |
|---|---|
| HTTP REST | Más pesado para el ESP32 (TCP overhead). Requiere que el ESP32 conozca la IP del backend. Sin gestión nativa de reconexión. |
| WebSocket directo | El ESP32 no tiene soporte WebSocket tan maduro como MQTT. Más complejo de implementar y depurar en hardware embebido. |

# ADR-002 — Framework de Backend

**Estado:** Aceptado  
**Fecha:** 2026-05-14  
**Autor:** Pablo Núñez Fernández

---

## Contexto

El backend necesita:
- Exponer una API REST con autenticación JWT.
- Integrarse con un broker MQTT para recibir datos de los sensores ESP32.
- Gestionar conexiones WebSocket en tiempo real (Socket.io).
- Acceder a una base de datos MariaDB.
- Generar archivos PDF bajo demanda.

Se evaluaron Node.js + Express, Python + FastAPI y Java + Spring Boot.

---

## Decisión

Se elige **Node.js 20 LTS + Express 4** como stack de backend.

---

## Razones

1. **JavaScript full-stack**: Permite compartir modelos, tipos y utilidades entre frontend (Angular/TypeScript) y backend, reduciendo la duplicación.

2. **Ecosistema MQTT**: La librería `mqtt.js` es la más madura y documentada para Node.js. La integración con `socket.io` es también nativa y ampliamente usada.

3. **Socket.io**: La librería socket.io fue diseñada para Node.js y ofrece la integración más sencilla con Express de todas las opciones evaluadas.

4. **Rendimiento suficiente**: Para el volumen de datos esperado (decenas de sensores, cientos de eventos por minuto), Node.js con su modelo de I/O no bloqueante es más que suficiente.

5. **Simplicidad de Express**: Permite construir la API sin la rigidez de un framework más opinionado, lo que facilita aprender y entender cada capa.

---

## Consecuencias

**Positivas:**
- Un único lenguaje (JavaScript/TypeScript) en todo el stack.
- Excelente soporte para MQTT y WebSocket.
- Despliegue sencillo (PM2 o Docker).

**Negativas:**
- Sin tipado estricto en Express por defecto (mitigable con TypeScript en el backend).
- Para operaciones muy intensivas en CPU (procesamiento de datos pesado), Node.js no es la opción óptima — aunque no es el caso de este proyecto.

---

## Alternativas descartadas

| Stack | Motivo de descarte |
|---|---|
| Python + FastAPI | Buena opción técnica, pero rompe el full-stack JS y requiere aprender un nuevo framework. |
| Java + Spring Boot | Excesivamente pesado para este proyecto. Mayor tiempo de setup y despliegue más complejo. |

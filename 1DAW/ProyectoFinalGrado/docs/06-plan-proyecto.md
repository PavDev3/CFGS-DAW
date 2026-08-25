# 06 — Plan de Proyecto

## Metodología

Se seguirá una metodología ágil simplificada con sprints de 1 semana. Al final de cada sprint habrá un entregable funcional demostrable.

## Fases y sprints

### Fase 0 — Configuración inicial (1 semana)
- [ ] Configurar repositorio Git con estructura de carpetas.
- [ ] Configurar entorno de desarrollo (Node.js, Angular CLI, Docker).
- [ ] Levantar MariaDB y Mosquitto con Docker Compose.
- [ ] Crear script SQL con el esquema de BBDD.
- [ ] Skeleton del proyecto Angular (módulos, routing, Angular Material).
- [ ] Skeleton del backend Node.js (estructura en capas, Express básico).

**Entregable:** Entornos configurados, app Angular arranca, backend responde en `/health`.

---

### Fase 1 — Autenticación (1 semana)
- [ ] Backend: endpoint POST /auth/login con JWT.
- [ ] Backend: middleware de autenticación JWT.
- [ ] Backend: middleware de autorización por rol.
- [ ] Frontend: pantalla de login.
- [ ] Frontend: guard de rutas (AuthGuard, RoleGuard).
- [ ] Frontend: interceptor HTTP para adjuntar JWT.
- [ ] Frontend: gestión de sesión (logout, expiración).

**Entregable:** Login funcional, rutas protegidas.

---

### Fase 2 — Firmware ESP32 + MQTT (1 semana)
- [ ] Firmware: conexión WiFi y cliente MQTT.
- [ ] Firmware: escaneo BLE con BLEScan.
- [ ] Firmware: escaneo redes WiFi con WiFiScan.
- [ ] Firmware: publicación de resultados en JSON al broker.
- [ ] Firmware: heartbeat periódico al topic de status.
- [ ] Backend: cliente MQTT suscrito a los topics.
- [ ] Backend: procesamiento y almacenamiento en BBDD.
- [ ] Backend: actualización de estado de sensor (online/offline).

**Entregable:** ESP32 detecta dispositivos y los datos llegan a la BBDD.

---

### Fase 3 — Dashboard en tiempo real (1 semana)
- [ ] Backend: endpoint GET /devices/active.
- [ ] Backend: emisión de eventos Socket.io al recibir datos del MQTT.
- [ ] Frontend: módulo dashboard con lista de dispositivos activos.
- [ ] Frontend: integración Socket.io para actualización en tiempo real.
- [ ] Frontend: indicadores visuales (whitelist vs desconocido, BLE vs WiFi).
- [ ] Frontend: estado de sensores en tiempo real.

**Entregable:** Dashboard muestra dispositivos en tiempo real desde la ESP32.

---

### Fase 4 — Whitelist y alertas (1 semana)
- [ ] Backend: CRUD completo de whitelist.
- [ ] Backend: lógica de generación de alertas (dispositivo no en whitelist → nueva alerta).
- [ ] Backend: endpoints GET/PUT para alertas.
- [ ] Backend: emisión de evento Socket.io `alert:new`.
- [ ] Frontend: módulo de gestión de whitelist (añadir/eliminar MACs).
- [ ] Frontend: módulo de alertas con lista, filtros y cambio de estado.
- [ ] Frontend: notificación visual en tiempo real al llegar alerta nueva.

**Entregable:** Sistema de alertas funcional, whitelist gestionable desde la web.

---

### Fase 5 — Historial, estadísticas e informes (1 semana)
- [ ] Backend: endpoint GET /devices con filtros y paginación.
- [ ] Backend: endpoint GET /stats/summary y /stats/timeline.
- [ ] Backend: generación de PDF con pdfkit o puppeteer.
- [ ] Frontend: módulo de historial con filtros, paginación y tabla.
- [ ] Frontend: módulo de estadísticas con gráficas (Chart.js o ngx-charts).
- [ ] Frontend: botón de generación y descarga de informe PDF.

**Entregable:** Historial completo, gráficas, descarga de informe PDF.

---

### Fase 6 — Gestión de sensores y usuarios (1 semana)
- [ ] Backend: CRUD completo de sensores y configuración por sensor.
- [ ] Backend: CRUD de usuarios (solo admin).
- [ ] Frontend: módulo admin — gestión de sensores.
- [ ] Frontend: módulo admin — gestión de usuarios.
- [ ] Frontend: perfil de usuario (cambio de contraseña).

**Entregable:** Panel de administración completo.

---

### Fase 7 — Pulido, tests y despliegue (1-2 semanas)
- [ ] Tests unitarios backend (Jest) — cobertura ≥ 60%.
- [ ] Revisión de seguridad: rate limiting, CORS, validación de inputs.
- [ ] Docker Compose completo (backend + BBDD + MQTT).
- [ ] README de despliegue con instrucciones paso a paso.
- [ ] Revisión UI/UX: responsividad, mensajes de error, estados de carga.
- [ ] Preparación de la demo para la defensa.

**Entregable:** Sistema completo, dockerizado y documentado.

---

## Estimación total

| Fase | Duración estimada |
|---|---|
| Fase 0 — Configuración | 1 semana |
| Fase 1 — Autenticación | 1 semana |
| Fase 2 — Firmware + MQTT | 1 semana |
| Fase 3 — Dashboard RT | 1 semana |
| Fase 4 — Whitelist + Alertas | 1 semana |
| Fase 5 — Historial + Informes | 1 semana |
| Fase 6 — Admin | 1 semana |
| Fase 7 — Pulido + Tests | 1-2 semanas |
| **Total** | **8-9 semanas** |

## Herramientas

| Herramienta | Uso |
|---|---|
| Git + GitHub | Control de versiones |
| VS Code | Desarrollo |
| Arduino IDE 2.x | Firmware ESP32 |
| Docker Desktop | Entorno local |
| Postman | Testing de la API |
| DBeaver / HeidiSQL | Gestión BBDD |
| Figma (opcional) | Diseño de mockups UI |

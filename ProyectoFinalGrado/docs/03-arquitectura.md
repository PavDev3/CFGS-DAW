# 03 — Arquitectura del Sistema

## Visión general

El sistema sigue una arquitectura de tres capas con un componente IoT adicional:

```
┌─────────────────────────────────────────────────────────────────┐
│                        CAPA DE PRESENTACIÓN                      │
│                    Angular (última versión) (SPA)                              │
│         Dashboard │ Alertas │ Historial │ Informes               │
└───────────────────────────┬─────────────────────────────────────┘
                            │ HTTP (REST) + WebSocket
┌───────────────────────────▼─────────────────────────────────────┐
│                         CAPA DE NEGOCIO                          │
│                    Node.js + Express                             │
│    Auth (JWT) │ API REST │ Socket.io │ MQTT Client               │
└──────────┬─────────────────────────────────────┬────────────────┘
           │ SQL (mysql2)                         │ MQTT sub/pub
┌──────────▼──────────────┐          ┌────────────▼───────────────┐
│       CAPA DE DATOS     │          │     BROKER MQTT             │
│       MariaDB           │          │     Mosquitto               │
└─────────────────────────┘          └────────────┬───────────────┘
                                                   │ MQTT pub
                                      ┌────────────▼───────────────┐
                                      │     CAPA IoT               │
                                      │     ESP32 (firmware)        │
                                      │   Escaneo BLE + WiFi        │
                                      └────────────────────────────┘
```

## Componentes

### 1. Frontend — Angular (última versión)

**Responsabilidades:**
- Interfaz de usuario SPA (Single Page Application).
- Comunicación con el backend via HTTP (API REST) y WebSocket (Socket.io).
- Gestión del estado local con servicios Angular.
- Autenticación: guarda el JWT en localStorage, lo adjunta a cada petición via interceptor HTTP.

**Estructura de módulos:**
```
src/
├── app/
│   ├── core/               ← Servicios singleton, guards, interceptores
│   │   ├── auth/
│   │   ├── guards/
│   │   └── interceptors/
│   ├── shared/             ← Componentes, pipes y directivas reutilizables
│   ├── features/
│   │   ├── dashboard/      ← Vista principal tiempo real
│   │   ├── devices/        ← Historial y whitelist
│   │   ├── alerts/         ← Gestión de alertas
│   │   ├── sensors/        ← Gestión de sensores ESP32
│   │   ├── reports/        ← Generación de informes
│   │   └── admin/          ← Gestión de usuarios
│   └── layout/             ← Shell, navbar, sidebar
```

### 2. Backend — Node.js + Express

**Responsabilidades:**
- Exponer la API REST para el frontend.
- Gestionar sesiones WebSocket (Socket.io) para actualizaciones en tiempo real.
- Suscribirse al broker MQTT y procesar los mensajes de los sensores.
- Autenticación y autorización con JWT.
- Acceso a la base de datos.

**Estructura en capas:**
```
src/
├── routes/         ← Definición de endpoints
├── controllers/    ← Lógica de cada endpoint
├── services/       ← Lógica de negocio
├── models/         ← Acceso a BBDD (queries)
├── middleware/     ← Auth JWT, rate limiting, validación
├── mqtt/           ← Cliente MQTT y procesador de mensajes
└── socket/         ← Gestión Socket.io
```

### 3. Base de datos — MariaDB

**Responsabilidades:**
- Persistencia de todos los datos del sistema.
- Ver modelo detallado en [`04-modelo-datos.md`](04-modelo-datos.md).

### 4. Broker MQTT — Mosquitto

**Responsabilidades:**
- Recibir los mensajes publicados por los sensores ESP32.
- Distribuirlos al backend suscrito.
- Autenticación de clientes MQTT.

**Topics:**
```
iot/sensor/{sensor_id}/ble       ← Dispositivos BLE detectados
iot/sensor/{sensor_id}/wifi      ← Redes/dispositivos WiFi detectados
iot/sensor/{sensor_id}/status    ← Heartbeat del sensor (online/offline)
```

**Formato de mensaje (JSON):**
```json
{
  "sensor_id": "ESP32_A1B2",
  "timestamp": "2026-05-14T10:30:00Z",
  "devices": [
    {
      "mac": "AA:BB:CC:DD:EE:FF",
      "name": "iPhone de Ana",
      "rssi": -65,
      "type": "BLE"
    }
  ]
}
```

### 5. Firmware ESP32

**Responsabilidades:**
- Escanear periódicamente dispositivos BLE y redes WiFi.
- Publicar los resultados al broker MQTT.
- Enviar heartbeat periódico para indicar que está activo.

**Librerías Arduino utilizadas:**
- `WiFi.h` — Conexión a la red local.
- `PubSubClient.h` — Cliente MQTT.
- `BLEDevice.h` / `BLEScan.h` — Escaneo BLE.
- `ArduinoJson.h` — Serialización de mensajes.

## Flujo de datos

```
ESP32 escanea BLE/WiFi
       │
       ▼
Publica JSON en topic MQTT
       │
       ▼
Mosquitto broker recibe y distribuye
       │
       ▼
Backend Node.js (cliente MQTT) recibe el mensaje
       │
       ├──► Almacena en MariaDB
       │
       └──► Emite evento Socket.io a los clientes conectados
                   │
                   ▼
           Angular actualiza el dashboard en tiempo real
```

## Comunicación Frontend ↔ Backend

| Canal | Uso |
|---|---|
| HTTP GET/POST/PUT/DELETE | CRUD de recursos (sensores, usuarios, whitelist, informes) |
| HTTP POST /auth/login | Autenticación, obtención de JWT |
| WebSocket (Socket.io) | Actualizaciones en tiempo real: nuevos dispositivos, alertas |

## Seguridad

- **HTTPS**: En producción, el backend servirá sobre HTTPS (certificado autofirmado o Let's Encrypt).
- **JWT**: Todos los endpoints protegidos requieren `Authorization: Bearer <token>`.
- **MQTT Auth**: El broker Mosquitto requiere usuario/contraseña para cada sensor.
- **Rate limiting**: El endpoint de login tiene límite de intentos (express-rate-limit).
- **CORS**: El backend solo acepta peticiones desde el origen del frontend.

## Despliegue

El sistema se desplegará con **Docker Compose**:

```yaml
services:
  backend:    # Node.js + Express
  database:   # MariaDB
  mqtt:       # Eclipse Mosquitto
  # El frontend se despliega como build estático (nginx o similar)
```

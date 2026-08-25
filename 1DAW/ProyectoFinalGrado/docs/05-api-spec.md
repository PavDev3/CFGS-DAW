# 05 — Especificación de la API REST

Base URL: `http://localhost:3000/api`

Todos los endpoints excepto `/auth/login` requieren header:
```
Authorization: Bearer <jwt_token>
```

---

## Autenticación

### POST /auth/login
Autentica un usuario y devuelve un JWT.

**Body:**
```json
{ "username": "admin", "password": "1234" }
```
**Response 200:**
```json
{ "token": "eyJ...", "user": { "id": 1, "username": "admin", "role": "admin" } }
```
**Response 401:** Credenciales incorrectas.

---

## Sensores

### GET /sensors
Lista todos los sensores. Solo admin.

**Response 200:**
```json
[
  { "id": 1, "name": "Entrada", "location": "Puerta principal", "mac_address": "AA:BB:CC:DD:EE:FF", "status": "online", "last_seen": "2026-05-14T10:00:00Z" }
]
```

### POST /sensors
Registra un nuevo sensor. Solo admin.

**Body:**
```json
{ "name": "Sala reuniones", "location": "Planta 2", "mac_address": "11:22:33:44:55:66" }
```

### PUT /sensors/:id
Edita un sensor. Solo admin.

### DELETE /sensors/:id
Elimina un sensor. Solo admin.

---

## Dispositivos detectados

### GET /devices
Lista dispositivos con filtros opcionales.

**Query params:**
- `from` — fecha inicio (ISO 8601)
- `to` — fecha fin (ISO 8601)
- `sensor_id` — filtrar por sensor
- `type` — `BLE` o `WIFI`
- `whitelist` — `true` / `false`
- `page` — número de página (default 1)
- `limit` — resultados por página (default 50)

**Response 200:**
```json
{
  "total": 1240,
  "page": 1,
  "limit": 50,
  "data": [
    {
      "id": 1001,
      "mac_address": "AA:BB:CC:DD:EE:FF",
      "device_name": "iPhone de Ana",
      "device_type": "BLE",
      "rssi": -65,
      "sensor_id": 1,
      "sensor_name": "Entrada",
      "detected_at": "2026-05-14T10:30:00Z",
      "in_whitelist": true
    }
  ]
}
```

### GET /devices/active
Dispositivos detectados en los últimos N minutos (default 5).

**Query params:** `minutes` (default 5)

---

## Whitelist

### GET /whitelist
Lista todos los dispositivos autorizados.

### POST /whitelist
Añade un dispositivo a la whitelist. Solo admin.

**Body:**
```json
{ "mac_address": "AA:BB:CC:DD:EE:FF", "description": "iPhone de Ana", "owner": "Ana García" }
```

### DELETE /whitelist/:id
Elimina un dispositivo de la whitelist. Solo admin.

---

## Alertas

### GET /alerts
Lista alertas con filtros opcionales.

**Query params:** `status` (`new`/`reviewed`/`dismissed`), `from`, `to`, `page`, `limit`

### PUT /alerts/:id
Actualiza el estado de una alerta. Solo admin.

**Body:**
```json
{ "status": "reviewed", "notes": "Dispositivo identificado, sin riesgo." }
```

---

## Estadísticas

### GET /stats/summary
Resumen general para el dashboard.

**Response 200:**
```json
{
  "active_devices": 12,
  "unknown_devices": 3,
  "active_sensors": 2,
  "open_alerts": 5,
  "total_detections_today": 340
}
```

### GET /stats/timeline
Dispositivos detectados agrupados por hora.

**Query params:** `from`, `to`, `sensor_id`

**Response 200:**
```json
[
  { "hour": "2026-05-14T09:00:00Z", "count": 45 },
  { "hour": "2026-05-14T10:00:00Z", "count": 62 }
]
```

---

## Informes

### POST /reports/generate
Genera un informe PDF y lo devuelve como descarga.

**Body:**
```json
{ "from": "2026-05-01T00:00:00Z", "to": "2026-05-14T23:59:59Z", "sensor_ids": [1, 2] }
```
**Response 200:** `application/pdf`

---

## Usuarios (solo admin)

### GET /users
Lista todos los usuarios.

### POST /users
Crea un usuario.

**Body:**
```json
{ "username": "juan", "email": "juan@empresa.com", "password": "segura123", "role": "viewer" }
```

### PUT /users/:id
Edita un usuario.

### DELETE /users/:id
Desactiva un usuario (no se elimina de la BBDD).

---

## WebSocket — Eventos Socket.io

### Evento: `device:new`
Emitido cuando un sensor detecta un dispositivo por primera vez (no visto en los últimos 5 minutos).

```json
{
  "mac_address": "AA:BB:CC:DD:EE:FF",
  "device_name": "Unknown",
  "device_type": "BLE",
  "rssi": -72,
  "sensor_id": 1,
  "sensor_name": "Entrada",
  "detected_at": "2026-05-14T10:35:00Z",
  "in_whitelist": false
}
```

### Evento: `alert:new`
Emitido cuando se genera una nueva alerta.

```json
{
  "id": 42,
  "mac_address": "AA:BB:CC:DD:EE:FF",
  "sensor_name": "Sala reuniones",
  "triggered_at": "2026-05-14T10:35:00Z"
}
```

### Evento: `sensor:status`
Emitido cuando cambia el estado de un sensor.

```json
{ "sensor_id": 1, "status": "offline", "last_seen": "2026-05-14T10:34:00Z" }
```

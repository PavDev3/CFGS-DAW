# 04 — Modelo de Datos

## Diagrama Entidad-Relación (descripción textual)

```
Users ──────────── (crea) ──────────── Alerts
  │                                      │
  │                               (genera)
  │                                      │
Sensors ─── (detecta) ─── DetectedDevices
                                  │
                           (puede estar en)
                                  │
                              Whitelist
```

## Esquema de tablas

### Tabla: `users`
Usuarios del sistema.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| id | INT AUTO_INCREMENT | PK | Identificador |
| username | VARCHAR(50) | NOT NULL, UNIQUE | Nombre de usuario |
| email | VARCHAR(100) | NOT NULL, UNIQUE | Email |
| password_hash | VARCHAR(255) | NOT NULL | Hash bcrypt |
| role | ENUM('admin','viewer') | NOT NULL, DEFAULT 'viewer' | Rol |
| active | BOOLEAN | NOT NULL, DEFAULT TRUE | Estado de la cuenta |
| created_at | DATETIME | DEFAULT CURRENT_TIMESTAMP | Fecha de creación |
| last_login | DATETIME | NULL | Último acceso |

---

### Tabla: `sensors`
Dispositivos ESP32 registrados en el sistema.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| id | INT AUTO_INCREMENT | PK | Identificador |
| name | VARCHAR(100) | NOT NULL | Nombre descriptivo |
| location | VARCHAR(150) | NULL | Ubicación física |
| mac_address | VARCHAR(17) | NOT NULL, UNIQUE | MAC del ESP32 |
| status | ENUM('online','offline') | NOT NULL, DEFAULT 'offline' | Estado de conexión |
| last_seen | DATETIME | NULL | Último heartbeat recibido |
| registered_by | INT | FK → users.id | Usuario que lo registró |
| created_at | DATETIME | DEFAULT CURRENT_TIMESTAMP | Fecha de registro |

---

### Tabla: `detected_devices`
Registro histórico de todos los dispositivos detectados.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| id | BIGINT AUTO_INCREMENT | PK | Identificador |
| mac_address | VARCHAR(17) | NOT NULL | MAC del dispositivo |
| device_name | VARCHAR(150) | NULL | Nombre anunciado (si disponible) |
| device_type | ENUM('BLE','WIFI') | NOT NULL | Tipo de tecnología |
| rssi | INT | NULL | Intensidad de señal (dBm) |
| sensor_id | INT | FK → sensors.id | Sensor que lo detectó |
| detected_at | DATETIME | NOT NULL | Momento de detección |
| in_whitelist | BOOLEAN | NOT NULL, DEFAULT FALSE | Si está en whitelist en ese momento |

Índices: `mac_address`, `detected_at`, `sensor_id`

---

### Tabla: `whitelist`
Dispositivos autorizados.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| id | INT AUTO_INCREMENT | PK | Identificador |
| mac_address | VARCHAR(17) | NOT NULL, UNIQUE | MAC del dispositivo |
| description | VARCHAR(200) | NULL | Descripción del dispositivo |
| owner | VARCHAR(100) | NULL | Propietario o responsable |
| added_by | INT | FK → users.id | Admin que lo autorizó |
| created_at | DATETIME | DEFAULT CURRENT_TIMESTAMP | Fecha de autorización |

---

### Tabla: `alerts`
Alertas generadas al detectar dispositivos no autorizados.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| id | INT AUTO_INCREMENT | PK | Identificador |
| mac_address | VARCHAR(17) | NOT NULL | MAC que generó la alerta |
| device_name | VARCHAR(150) | NULL | Nombre del dispositivo |
| sensor_id | INT | FK → sensors.id | Sensor que lo detectó |
| status | ENUM('new','reviewed','dismissed') | NOT NULL, DEFAULT 'new' | Estado de la alerta |
| triggered_at | DATETIME | NOT NULL | Momento en que se generó |
| reviewed_by | INT | FK → users.id, NULL | Usuario que la revisó |
| reviewed_at | DATETIME | NULL | Momento de revisión |
| notes | TEXT | NULL | Notas del revisor |

---

### Tabla: `sensor_config`
Configuración individual por sensor.

| Campo | Tipo | Restricciones | Descripción |
|---|---|---|---|
| sensor_id | INT | PK, FK → sensors.id | Sensor |
| scan_interval_ms | INT | NOT NULL, DEFAULT 5000 | Intervalo de escaneo (ms) |
| ble_enabled | BOOLEAN | NOT NULL, DEFAULT TRUE | Escaneo BLE activo |
| wifi_enabled | BOOLEAN | NOT NULL, DEFAULT TRUE | Escaneo WiFi activo |
| updated_at | DATETIME | DEFAULT CURRENT_TIMESTAMP ON UPDATE | Última modificación |

---

## Script SQL de creación

```sql
CREATE DATABASE IF NOT EXISTS iot_security_monitor;
USE iot_security_monitor;

CREATE TABLE users (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role          ENUM('admin','viewer') NOT NULL DEFAULT 'viewer',
    active        BOOLEAN NOT NULL DEFAULT TRUE,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login    DATETIME NULL
);

CREATE TABLE sensors (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    location      VARCHAR(150) NULL,
    mac_address   VARCHAR(17)  NOT NULL UNIQUE,
    status        ENUM('online','offline') NOT NULL DEFAULT 'offline',
    last_seen     DATETIME NULL,
    registered_by INT NULL,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (registered_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE detected_devices (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    mac_address  VARCHAR(17)  NOT NULL,
    device_name  VARCHAR(150) NULL,
    device_type  ENUM('BLE','WIFI') NOT NULL,
    rssi         INT NULL,
    sensor_id    INT NOT NULL,
    detected_at  DATETIME NOT NULL,
    in_whitelist BOOLEAN NOT NULL DEFAULT FALSE,
    INDEX idx_mac (mac_address),
    INDEX idx_detected_at (detected_at),
    FOREIGN KEY (sensor_id) REFERENCES sensors(id) ON DELETE CASCADE
);

CREATE TABLE whitelist (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    mac_address VARCHAR(17)  NOT NULL UNIQUE,
    description VARCHAR(200) NULL,
    owner       VARCHAR(100) NULL,
    added_by    INT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (added_by) REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE alerts (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    mac_address  VARCHAR(17)  NOT NULL,
    device_name  VARCHAR(150) NULL,
    sensor_id    INT NOT NULL,
    status       ENUM('new','reviewed','dismissed') NOT NULL DEFAULT 'new',
    triggered_at DATETIME NOT NULL,
    reviewed_by  INT NULL,
    reviewed_at  DATETIME NULL,
    notes        TEXT NULL,
    FOREIGN KEY (sensor_id)    REFERENCES sensors(id) ON DELETE CASCADE,
    FOREIGN KEY (reviewed_by)  REFERENCES users(id)   ON DELETE SET NULL
);

CREATE TABLE sensor_config (
    sensor_id        INT PRIMARY KEY,
    scan_interval_ms INT     NOT NULL DEFAULT 5000,
    ble_enabled      BOOLEAN NOT NULL DEFAULT TRUE,
    wifi_enabled     BOOLEAN NOT NULL DEFAULT TRUE,
    updated_at       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sensor_id) REFERENCES sensors(id) ON DELETE CASCADE
);
```

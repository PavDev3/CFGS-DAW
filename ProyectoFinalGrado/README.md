# IoT Security Monitor — Proyecto de Fin de Grado

**Alumno:** Pablo Núñez Fernández  
**Centro:** IES Kursaal  
**Ciclo:** Desarrollo de Aplicaciones Web (DAW)  
**Curso:** 2025-2026

---

## Descripción

Sistema de monitorización de seguridad IoT en tiempo real. Dispositivos ESP32 distribuidos en un entorno escanean continuamente los dispositivos BLE y WiFi presentes, enviando los datos a un backend centralizado. Una aplicación web Angular permite visualizar en tiempo real qué dispositivos hay en la red, detectar intrusos, gestionar alertas y generar informes de auditoría.

## Estructura del repositorio

```
ProyectoFinalGrado/
├── README.md                        ← Este archivo
├── docs/
│   ├── 01-descripcion-proyecto.md   ← Descripción completa y objetivos
│   ├── 02-requisitos.md             ← Requisitos funcionales y no funcionales
│   ├── 03-arquitectura.md           ← Arquitectura del sistema
│   ├── 04-modelo-datos.md           ← Modelo entidad-relación y esquema BBDD
│   ├── 05-api-spec.md               ← Especificación de la API REST
│   ├── 06-plan-proyecto.md          ← Planificación y milestones
│   └── adr/
│       ├── ADR-001-frontend.md
│       ├── ADR-002-backend.md
│       ├── ADR-003-base-de-datos.md
│       ├── ADR-004-tiempo-real.md
│       └── ADR-005-protocolo-esp32.md
├── frontend/                        ← Aplicación Angular (por implementar)
├── backend/                         ← API Node.js + Express (por implementar)
├── firmware/                        ← Sketch Arduino para ESP32 (por implementar)
└── database/                        ← Scripts SQL (por implementar)
```

## Stack tecnológico

| Capa | Tecnología |
|---|---|
| Frontend | Angular 17 + Angular Material |
| Backend | Node.js + Express |
| Base de datos | MariaDB |
| Tiempo real | Socket.io (WebSocket) |
| Protocolo IoT | MQTT (broker Mosquitto) |
| Firmware | Arduino (ESP32) |
| Autenticación | JWT |

## Documentación

Ver carpeta [`docs/`](docs/) para la documentación completa del proyecto.

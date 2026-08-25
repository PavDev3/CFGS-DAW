# 01 — Descripción del Proyecto

## Nombre del proyecto

**IoT Security Monitor**

## Descripción general

IoT Security Monitor es una plataforma web de monitorización de seguridad en entornos físicos basada en dispositivos ESP32. El sistema permite detectar, identificar y registrar todos los dispositivos Bluetooth Low Energy (BLE) y WiFi presentes en un espacio, proporcionando visibilidad en tiempo real sobre posibles intrusiones o dispositivos no autorizados.

El proyecto nace de la convergencia entre dos disciplinas del ciclo DAW: el desarrollo web moderno (Angular, Node.js, BBDD) y la ciberseguridad aplicada a entornos IoT.

## Motivación

En entornos empresariales y domésticos existe una creciente necesidad de controlar qué dispositivos se conectan o están presentes en el espacio físico. Herramientas comerciales para este fin son costosas y cerradas. Este proyecto propone una alternativa de bajo coste, basada en hardware accesible (ESP32 ~5€/unidad) y software open-source, con una interfaz web profesional.

## Objetivos

### Objetivo principal
Desarrollar una aplicación web completa que permita monitorizar en tiempo real los dispositivos BLE y WiFi detectados por una red de sensores ESP32, con gestión de alertas, historial y generación de informes.

### Objetivos específicos

1. Implementar el firmware ESP32 que escanea dispositivos BLE y WiFi y publica los datos vía MQTT.
2. Desarrollar un backend Node.js que recibe los datos MQTT, los almacena en MariaDB y los expone via API REST y WebSocket.
3. Desarrollar el frontend Angular con:
   - Dashboard en tiempo real con lista de dispositivos activos.
   - Sistema de alertas para dispositivos desconocidos.
   - Historial y estadísticas de presencia.
   - Gestión de dispositivos autorizados (whitelist).
   - Generación de informes PDF de auditoría.
4. Implementar autenticación y control de acceso (JWT).
5. Documentar el proyecto completo (arquitectura, ADR, manual de despliegue).

## Alcance

### Incluido en el proyecto
- Firmware ESP32 para escaneo BLE y WiFi.
- Backend completo: API REST + WebSocket + integración MQTT.
- Frontend Angular completo con todas las vistas.
- Base de datos relacional con historial completo.
- Sistema de alertas en tiempo real (web + notificación Telegram opcional).
- Generación de informes PDF.
- Autenticación con roles (admin / usuario).
- Documentación técnica y manual de usuario.

### No incluido
- Aplicación móvil nativa.
- Análisis profundo de paquetes (packet sniffing a nivel de payload).
- Infraestructura en la nube (el sistema corre en red local).

## Usuarios objetivo

| Rol | Descripción |
|---|---|
| Administrador | Gestiona sensores, usuarios, whitelist y configuración |
| Usuario | Visualiza el dashboard y consulta el historial |

## Valor diferencial

- Hardware de bajo coste (ESP32 ~5€) frente a soluciones comerciales (>500€).
- Sistema completamente local: los datos no salen de la red interna.
- Stack moderno y profesional (Angular + Node.js), alineado con el mercado laboral.
- Combina IoT + ciberseguridad + desarrollo web en un único proyecto.

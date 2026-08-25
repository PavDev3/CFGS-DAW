# 02 — Requisitos del Sistema

## Requisitos Funcionales

### RF-01 — Autenticación y autorización
- El sistema dispondrá de login con usuario y contraseña.
- Las contraseñas se almacenarán hasheadas (bcrypt).
- Se usarán tokens JWT con tiempo de expiración.
- Existirán dos roles: `admin` y `viewer`.
- Solo el admin puede gestionar sensores, usuarios y whitelist.

### RF-02 — Gestión de sensores ESP32
- El admin puede registrar, editar y dar de baja sensores.
- Cada sensor tiene: nombre, ubicación, MAC, estado (activo/inactivo).
- El sistema muestra el estado de conexión de cada sensor en tiempo real.

### RF-03 — Detección de dispositivos
- Los sensores ESP32 escanean BLE y WiFi periódicamente (intervalo configurable).
- Por cada dispositivo detectado se registra: MAC, nombre (si disponible), tipo (BLE/WiFi), RSSI, sensor que lo detectó, timestamp.
- Los registros se almacenan en la base de datos.

### RF-04 — Dashboard en tiempo real
- Vista principal con lista de dispositivos activos en este momento.
- Indicador visual de dispositivos nuevos (no vistos antes) vs conocidos.
- Indicador visual de dispositivos autorizados (whitelist) vs desconocidos.
- Actualización automática vía WebSocket sin recargar la página.
- Contador de dispositivos por tipo (BLE / WiFi).

### RF-05 — Whitelist de dispositivos autorizados
- El admin puede añadir/eliminar dispositivos a la whitelist por MAC.
- Cada entrada en la whitelist tiene: MAC, descripción, propietario, fecha de alta.
- Los dispositivos en whitelist se muestran diferenciados en el dashboard.

### RF-06 — Sistema de alertas
- Cuando aparece un dispositivo no presente en la whitelist, se genera una alerta.
- Las alertas se muestran en tiempo real en la interfaz.
- Las alertas tienen estado: nueva / revisada / descartada.
- El admin puede configurar notificación por Telegram al recibir una alerta.

### RF-07 — Historial y búsqueda
- Vista de historial con todos los registros de detección.
- Filtros por: rango de fechas, sensor, tipo de dispositivo, MAC, estado (whitelist/desconocido).
- Paginación de resultados.

### RF-08 — Estadísticas
- Gráfica de dispositivos detectados por hora/día.
- Top dispositivos más frecuentes.
- Estadísticas por sensor.
- Historial de alertas.

### RF-09 — Informes de auditoría
- Generación de informe PDF con: período, sensores activos, dispositivos detectados, alertas generadas, dispositivos no autorizados.
- Descarga directa desde la interfaz web.

### RF-10 — Gestión de usuarios
- El admin puede crear, editar y desactivar usuarios.
- Cada usuario tiene: nombre, email, rol, estado activo/inactivo.

---

## Requisitos No Funcionales

### RNF-01 — Rendimiento
- El dashboard debe actualizar los datos con una latencia máxima de 2 segundos.
- La API REST debe responder en menos de 500ms para el 95% de las peticiones.
- El sistema debe soportar al menos 10 sensores ESP32 simultáneos.

### RNF-02 — Seguridad
- Todas las comunicaciones HTTP serán sobre HTTPS en producción.
- La API REST estará protegida con JWT en todos los endpoints autenticados.
- Las contraseñas se almacenarán con bcrypt (coste mínimo 10).
- El broker MQTT usará autenticación usuario/contraseña.
- Se implementará protección contra fuerza bruta en el login (rate limiting).

### RNF-03 — Usabilidad
- La interfaz será responsiva y funcional en escritorio y tablet.
- Los tiempos de carga inicial de la aplicación serán menores a 3 segundos.
- La aplicación seguirá las guías de Angular Material para consistencia visual.

### RNF-04 — Mantenibilidad
- El código seguirá las guías de estilo de Angular (ESLint).
- El backend seguirá una arquitectura en capas (rutas / controladores / servicios / modelos).
- Cobertura mínima de tests unitarios: 60% en el backend.

### RNF-05 — Portabilidad
- El sistema se desplegará mediante Docker Compose (backend + BBDD + broker MQTT).
- El frontend se compilará como build estático servible desde cualquier servidor web.

### RNF-06 — Disponibilidad
- El sistema estará diseñado para funcionar 24/7 en red local.
- El backend se ejecutará con PM2 para reinicio automático en caso de fallo.

---

## Requisitos de hardware

| Componente | Especificación mínima |
|---|---|
| Servidor (backend + BBDD) | Raspberry Pi 4 (2GB) o equivalente |
| Sensores | ESP32 (cualquier modelo con BT + WiFi integrado) |
| Red | Router WiFi con acceso a los sensores |

# ADR-003 — Sistema de Base de Datos

**Estado:** Aceptado  
**Fecha:** 2026-05-14  
**Autor:** Pablo Núñez Fernández

---

## Contexto

El sistema necesita persistir:
- Usuarios y roles.
- Sensores y su configuración.
- Historial de detecciones (volumen potencialmente alto: miles de registros diarios).
- Whitelist de dispositivos autorizados.
- Alertas con su ciclo de vida.

Se evaluaron MariaDB, PostgreSQL y MongoDB.

---

## Decisión

Se elige **MariaDB 10.11** como sistema de gestión de base de datos.

---

## Razones

1. **Datos relacionales**: Los datos del sistema son claramente relacionales (sensors → detected_devices, users → alerts, etc.). Un modelo relacional con claves foráneas garantiza integridad referencial.

2. **Familiaridad**: MariaDB es el SGBD usado durante el ciclo formativo. El equipo conoce su sintaxis, herramientas (HeidiSQL, DBeaver) y modelo de permisos.

3. **Compatibilidad MySQL**: Compatible con el ecosistema MySQL, ampliamente documentado y con gran soporte en la librería `mysql2` para Node.js.

4. **Rendimiento adecuado**: Para el volumen esperado (miles de registros diarios, no millones), MariaDB con índices correctos es más que suficiente.

5. **Open source y ligero**: Sin costes de licencia y bajo consumo de recursos, ideal para despliegue en Raspberry Pi o servidor local.

---

## Consecuencias

**Positivas:**
- Integridad de datos garantizada por constraints y foreign keys.
- Consultas complejas (JOINs para estadísticas) bien optimizadas.
- Herramientas conocidas para gestión y depuración.

**Negativas:**
- Para volúmenes masivos de datos de series temporales (millones de eventos), una base de datos de series temporales (InfluxDB) sería más eficiente. Sin embargo, el volumen esperado no justifica esa complejidad.
- Esquema fijo: añadir nuevos campos a detected_devices requiere migraciones.

---

## Alternativas descartadas

| SGBD | Motivo de descarte |
|---|---|
| PostgreSQL | Técnicamente superior en algunos aspectos, pero sin ventaja práctica para este proyecto y sin uso previo en el ciclo formativo. |
| MongoDB | Los datos son relacionales; usar un modelo de documentos introduciría complejidad innecesaria y perdería integridad referencial. |
| InfluxDB | Especializado en series temporales, adecuado para sensores IoT a gran escala, pero excesivo para el volumen de este proyecto y más difícil de combinar con datos relacionales (usuarios, alertas). |

# Presentaciones Grupales — BBDD

← [[BBDD]]

---

## Resúmenes de las presentaciones

Las presentaciones grupales cubrieron los siguientes temas avanzados de bases de datos:

---

## Grupo 1 — Big Data

**Big Data** se define como el manejo de grandes volúmenes de datos que no pueden ser procesados de forma convencional. Se caracteriza por las **5V**:

| V | Descripción |
|---|-------------|
| **Volumen** | Gran cantidad de datos |
| **Velocidad** | Procesamiento rápido |
| **Variedad** | Diferentes tipos de datos |
| **Veracidad** | Posiblemente poco fiables |
| **Valor** | Extracción de información útil |

**Diferencias BD Tradicional vs Big Data:**
- BD Tradicional (SQL): datos en tablas, escalabilidad vertical (MySQL, Oracle)
- Big Data: datos no estructurados, procesamiento masivo en paralelo, **escalabilidad horizontal** (MongoDB, Hadoop, Cassandra)

**Herramientas clave:**
- NoSQL: MongoDB, Cassandra
- Almacenamiento distribuido: HDFS
- Procesamiento: MapReduce, Apache Spark
- Data Lakes: Amazon S3

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 1 - Big Data.pdf`

---

## Grupo 2 — Fragmentación y Sistemas Distribuidos

Un **sistema distribuido** está compuesto por múltiples nodos autónomos que trabajan coordinadamente.

**Características:**
- Tolerancia a fallos (uno o varios nodos pueden fallar)
- Concurrencia

**Escalabilidad:**
- **Vertical (Scale Up):** Más CPU/RAM en un solo servidor. Simple pero con límite físico.
- **Horizontal (Scale Out):** Añadir más servidores. Mejor para alta demanda.

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 2 - Fragmentacion y Sistemas Distribuidos.pdf`

---

## Grupo 3 — Normalización

La **normalización** organiza datos para reducir redundancia y mejorar la integridad.

| Forma Normal | Regla |
|--------------|-------|
| **1NF** | Valores atómicos, sin listas en celdas |
| **2NF** | Dependencia total de la PK completa |
| **3NF** | Sin dependencias transitivas |
| **BCNF** | Versión estricta de 3NF |

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 3 - Normalización.pdf`

---

## Grupo 4 — Alta Disponibilidad (HA)

La **Alta Disponibilidad** mantiene el servicio continuamente ante fallos.

**Métricas clave:**
- **RTO** (Recovery Time Objective): Tiempo máximo tolerable de restauración
- **RPO** (Recovery Point Objective): Máximo tiempo de datos que se puede perder

**Patrones de arquitectura:**
- Activo-Pasivo, Multi-Master, Shared-Nothing

**Tipos de replicación:**
- **Síncrona:** RPO = 0, pero mayor latencia
- **Asíncrona:** Mejor rendimiento, riesgo de pérdida de datos

**Prevención de split-brain:** Quorum, Heartbeat, Fencing (STONITH)

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 4 - Alta disponibilidad.pdf`

---

## Grupo 5 — Protección de Datos y GDPR

**GDPR** (Reglamento General de Protección de Datos) reemplazó a la LOPD española en 2018.

**Diferencias LOPD vs GDPR:**
- GDPR afecta a toda Europa y a cualquier empresa que trate datos de ciudadanos europeos
- Exige **consentimiento explícito**
- Incluye el **derecho al olvido**
- Multas hasta el **4% de los ingresos anuales globales**
- Obligatorio notificar brechas de seguridad en **72 horas**

**Métodos de seguridad:**
- Cifrado de datos
- Copias de seguridad periódicas (completas, incrementales, diferenciales)
- Control de accesos
- Auditorías y monitorización

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 5 - Proteccion_de_Datos.pdf`

---

## Grupo 6 — Escalabilidad

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/BBDD/Presentaciones/Grupo 6 - Escalabilidad.pdf`

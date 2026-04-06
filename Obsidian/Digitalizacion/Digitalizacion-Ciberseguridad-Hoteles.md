# Reto Profesional Tema 5 — Ciberseguridad en el Sector Hotelero

← [[Digitalizacion]] | Ver también: [[Ciberseguridad]]

> Caso: "+hotel" → Consultora "+tech"

---

## Ficha 1: Ataque a MGM Resorts (2023)

**Titular:** MGM Resorts sufre ciberataque de $100 millones mediante ingeniería social

### Resumen

En septiembre de 2023, el grupo **Scattered Spider** atacó MGM Resorts usando **vishing** (voice phishing): llamaron al soporte técnico haciéndose pasar por un empleado para obtener credenciales de superadministrador. Con ello accedieron a sistemas internos, robaron datos y cifraron servidores con **ransomware**.

**Consecuencias:**
- $100 millones en pérdidas
- Robo de datos personales (nombres, DNI, pasaportes, Seguro Social)
- Caída de sistemas de reservas, llaves digitales y pagos
- Ocupación cayó del 93% al 88%

### Riesgos y propuestas

| Riesgo | Propuesta |
|--------|-----------|
| Ingeniería social/vishing | Formación obligatoria en ciberseguridad |
| Acceso privilegiado comprometido | MFA obligatorio para cuentas administrativas |
| Dependencia de sistemas conectados | Principio de mínimo privilegio |
| | Segmentación de red |

---

## Ficha 2: Estafa masiva suplantando a Booking.com

**Titular:** Ciberdelincuentes roban datos de reservas para estafar a clientes de Booking.com

### Resumen

Atacantes accedieron a sistemas de reservas de hoteles mediante **phishing** al personal. Extrajeron datos reales de reservas y contactaron a los clientes haciéndose pasar por Booking.com, solicitando prepago. Los clientes confiaron porque los datos eran reales.

### Riesgos y propuestas

| Riesgo | Propuesta |
|--------|-----------|
| Phishing al personal | Sistema de alertas para accesos inusuales |
| Fuga de datos de clientes | Cifrado extremo a extremo |
| Reputación dañada | Comunicación clara a clientes sobre pagos |
| | Verificación en dos pasos |

---

## Ficha 3: Ransomware en cadena hotelera de Baleares (2023)

**Titular:** Ransomware cifra base de datos el mismo día de la alerta de seguridad

### Resumen

Una cadena hotelera balear recibió alerta de vulnerabilidad crítica en su base de datos. **El mismo día**, antes de poder aplicar el parche, los ciberdelincuentes descubrieron el servidor vulnerable y lo cifraron con ransomware. Recuperaron los datos gracias a **copias de seguridad offline**.

### Riesgos y propuestas

| Riesgo | Propuesta |
|--------|-----------|
| Vulnerabilidades sin parchear | Parches automáticos |
| Ventana de vulnerabilidad | Copias de seguridad offline |
| Pérdida de datos | Monitorización 24/7 de CVEs |
| | Plan de respuesta a incidentes |

---

## Tres Riesgos Principales en el Sector Hotelero

### 1. Vulnerabilidades en IoT

Los hoteles usan cientos de dispositivos conectados (termostatos, cerraduras electrónicas, sensores de ocupación, minibares). **Muchos no se actualizan nunca** y tienen contraseñas por defecto.

### 2. Robo de Datos Personales y Financieros

Los hoteles almacenan datos de pasaporte/DNI, tarjetas de crédito, información de huéspedes VIP e historial de estancias. Esta información es muy valiosa en el mercado negro.

### 3. Dependencia de Terceros

Hoteles, agencias de viajes y plataformas de reservas están interconectados. El **60% de los ataques en 2024** involucraron a terceros.

---

## Estrategias y Soluciones Generales

| Medida | Descripción |
|--------|-------------|
| **Formación continua** | Todo el personal debe reconocer phishing y vishing |
| **MFA obligatorio** | Autenticación multifactor en TODAS las cuentas |
| **Backups offline** | No conectadas a la red — imposibles de cifrar |
| **Segmentación de red** | Separar sistemas críticos de los demás |
| **Actualizaciones automáticas** | Reducir la ventana de vulnerabilidad |
| **Monitorización 24/7** | Detectar intrusiones antes de que causen daño |
| **Plan de incidentes** | Saber qué hacer ANTES de que ocurra el ataque |
| **Ciberseguro** | Transferir parte del riesgo económico |

---

## Cuestiones Finales

### ¿Cuáles han sido las mayores dificultades para identificar riesgos?
- Falta de transparencia en la industria (miedo a dañar reputación)
- Evolución constante de las técnicas de ataque
- Complejidad de sistemas heredados + nuevos
- Factor humano: el más difícil de controlar

### ¿Qué medidas adicionales son necesarias a largo plazo?
- Certificaciones obligatorias de ciberseguridad para establecimientos turísticos
- Auditorías periódicas externas de seguridad
- Simulacros de ataque (pentesting ético)
- Colaboración sectorial: compartir información sobre amenazas
- **La ciberseguridad no es un gasto, es una inversión**

---

## Fuentes

1. Hosteltur — "Cinco casos reales de ciberataques a empresas turísticas" (2026)
2. Asimily — "3 Cyberattacks That Devastated Hospitality in 2023 and 2024"
3. Verizon Data Breach Investigations Report (2025)
4. Kaspersky — "Hacked hotel accounts on Booking.com"

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Digitalizacion/Tema5_Reto_Ciberseguridad_Hoteles.md`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Digitalizacion/Dig. Tema5.Act1.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Digitalizacion/Dig. Tema5.Cuestionario.pdf`

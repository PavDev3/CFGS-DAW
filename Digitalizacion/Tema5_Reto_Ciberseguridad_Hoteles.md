# RETO PROFESIONAL: Ciberseguridad en el Sector Hotelero

## Caso: "+hotel" → Consultora "+tech"

---

## 📋 FICHA 1: Ataque a MGM Resorts (2023)

### Titular
**MGM Resorts sufre ciberataque de $100 millones mediante ingeniería social**

### Enlace
https://www.csoonline.com/article/2081598/cyberattack-forces-omni-hotels-to-shut-down-its-it-systems.html

### Resumen de la noticia
En septiembre de 2023, MGM Resorts International sufrió un ataque masivo llevado a cabo por el grupo Scattered Spider. El ataque comenzó con una llamada de **vishing** (voice phishing) al servicio de soporte, donde el atacante se hizo pasar por un empleado y convenció al personal para obtener acceso a una cuenta de superadministrador. Esto permitió a los hackers acceder a los sistemas internos, robar datos de clientes y encriptar servidores con ransomware.

**Consecuencias:**
- $100 millones en pérdidas
- Robo de datos personales (nombres, DNI, pasaportes, números de Seguro Social)
- Caída de sistemas de reservas, llaves digitales y pagos con tarjeta
- Ocupación hotelera cayó del 93% al 88%

### Riesgos asociados
- **Ingeniería social y vishing**: Manipulación psicológica del personal para obtener credenciales
- **Acceso privilegiado comprometido**: Un solo empleado con demasiados permisos puede ser el punto de entrada
- **Dependencia de sistemas conectados**: Llaves digitales, pagos y reservas todo en la nube = punto único de fallo

### Propuestas
- **Formación obligatoria en ciberseguridad** para todo el personal (reconocer vishing, phishing)
- **Autenticación multifactor (MFA)** obligatoria para todas las cuentas administrativas
- **Principio de mínimo privilegio**: cada empleado solo tiene acceso a lo que necesita
- **Segmentación de red**: si un sistema cae, los demás siguen funcionando

---

## 📋 FICHA 2: Estafa masiva suplantando a Booking.com

### Titular
**Ciberdelincuentes roban datos de reservas para estafar a clientes de Booking.com**

### Enlace
https://www.hosteltur.com/171181_la-ciberestafa-del-verano-roban-datos-de-hoteles-para-suplantar-a-booking.html

### Resumen de la noticia
Los atacantes accedieron a los sistemas de gestión de reservas de varios hoteles mediante **phishing** dirigido al personal hotelero. Una vez dentro, extrajeron datos reales de reservas (nombres, fechas, habitaciones) y contactaron directamente a los clientes haciéndose pasar por Booking.com, solicitando prepago para "confirmar" la estancia.

Los clientes confiaron porque los datos de la reserva eran correctos y reales.

### Riesgos asociados
- **Phishing al personal**: El eslabón más débil suele ser el humano, no la tecnología
- **Fuga de datos de clientes**: Información sensible expuesta (datos personales y de pago)
- **Reputación dañada**: Los clientes pierden confianza en el hotel/plataforma

### Propuestas
- **Sistema de alertas** para detectar accesos inusuales a datos de reservas
- **Cifrado extremo a extremo** de datos de clientes
- **Comunicación clara a clientes**: Informar que nunca se pedirán pagos adicionales por email/WhatsApp
- **Verificación en dos pasos** para acceder a sistemas de gestión

---

## 📋 FICHA 3: Ransomware en cadena hotelera de Baleares (2023)

### Titular
**Ransomware encripta base de datos de cadena hotelera balear el mismo día de la alerta de seguridad**

### Enlace
https://www.hosteltur.com/174641_cinco-casos-reales-de-ciberataques-a-empresas-turisticas-ante-la-temporada-2026.html

### Resumen de la noticia
En octubre de 2023, una de las cinco principales cadenas hoteleras de Baleares recibió una alerta de vulnerabilidad crítica en su base de datos. **El mismo día**, antes de poder aplicar el parche, los ciberdelincuentes descubrieron el servidor vulnerable, accedieron a él y encriptaron toda la base de datos con ransomware, exigiendo un rescate.

La cadena logró recuperar los datos gracias a **copias de seguridad** y desplegó un nuevo servidor actualizado, evitando pagar el rescate.

### Riesgos asociados
- **Vulnerabilidades sin parchear**: Las actualizaciones de seguridad son críticas
- **Tiempo de respuesta**: La ventana entre "detectar vulnerabilidad" y "aplicar parche" es aprovechada por los atacantes
- **Riesgo de pérdida de datos** si no hay backups

### Propuestas
- **Parches automáticos** o ventanas de mantenimiento muy reducidas
- **Copias de seguridad offline** (no conectadas a la red, imposibles de encriptar)
- **Monitorización 24/7** de vulnerabilidades conocidas (CVEs)
- **Plan de respuesta a incidentes** documentado y ensayado

---

## 🔐 TRES RIESGOS PRINCIPALES EN EL SECTOR HOTELERO

### 1. Vulnerabilidades en IoT (Internet de las Cosas)
Los hoteles modernos usan cientos de dispositivos conectados:
- Termostatos inteligentes
- Cerraduras electrónicas
- Sensores de ocupación
- Minibares conectados
- Sistemas de climatización

**Problema**: Muchos de estos dispositivos **no se actualizan nunca** y tienen contraseñas por defecto.

### 2. Robo de datos personales y financieros
Los hoteles almacenan:
- Datos de pasaporte/DNI
- Tarjetas de crédito
- Información de huéspedes VIP
- Historial de estancias

**Problema**: Esta información es muy valiosa en el mercado negro y para extorsiones.

### 3. Dependencia de terceros (channel managers, OTAs)
Hoteles, agencias de viajes y plataformas de reservas están interconectados.

**Problema**: Si un tercero es hackeado (como la empresa de diseño web del hotel), el hotel también se ve afectado. El 60% de los ataques en 2024 involucraron a terceros.

---

## 💡 ESTRATEGIAS Y SOLUCIONES GENERALES

| Medida | Descripción |
|--------|-------------|
| **Formación continua** | Todo el personal debe reconocer phishing, vishing y prácticas seguras |
| **MFA obligatorio** | Autenticación multifactor en TODAS las cuentas, especialmente administrativas |
| **Copias de seguridad offline** | Backups que no se pueden encriptar aunque la red esté comprometida |
| **Segmentación de red** | Separar sistemas críticos (pagos, reservas) de los demás |
| **Actualizaciones automáticas** | Reducir la ventana de vulnerabilidad |
| **Monitorización 24/7** | Detectar intrusiones antes de que causen daño |
| **Plan de respuesta a incidentes** | Saber qué hacer ANTES de que ocurra el ataque |
| **Ciberseguro** | Transferir parte del riesgo económico |

---

## ❓ CUESTIONES FINALES

### 1. ¿Cuáles han sido las mayores dificultades para identificar y analizar los riesgos?

- **Falta de transparencia**: Muchos hoteles no reportan ataques por miedo a dañar su reputación
- **Evolución constante**: Las técnicas de ataque cambian rápidamente
- **Complejidad de sistemas**: Hoteles con tecnología heredada + nueva conviven con vulnerabilidades
- **Personal no técnico**: El factor humano sigue siendo el más difícil de controlar

### 2. ¿Qué medidas adicionales serían necesarias para garantizar la ciberseguridad a largo plazo?

- **Certificaciones obligatorias** de ciberseguridad para establecimientos turísticos
- **Auditorías periódicas** externas de seguridad
- **Tabla de clasificación de datos** según su sensibilidad
- **Simulacros de ataque** regulares (pentesting ético)
- **Colaboración sectorial**: Compartir información sobre amenazas entre hoteles
- **Inversión continua**: La ciberseguridad no es un gasto, es una inversión

---

## 📚 FUENTES

1. Hosteltur - "Cinco casos reales de ciberataques a empresas turísticas" (2026)
2. Asimily - "3 Cyberattacks That Devastated Hospitality in 2023 and 2024"
3. Verizon Data Breach Investigations Report (2025)
4. Kaspersky - "Hacked hotel accounts on Booking.com"
5. CSO Online - MGM Resorts cyberattack coverage

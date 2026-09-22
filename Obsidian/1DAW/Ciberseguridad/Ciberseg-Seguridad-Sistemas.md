# Seguridad en Administración de Sistemas (UD5)

← [[Ciberseguridad]]

---

## Conceptos clave de Seguridad en Sistemas

### Principios de Seguridad

| Principio | Descripción |
|-----------|-------------|
| **Confidencialidad** | Solo los autorizados acceden a la información |
| **Integridad** | La información no es alterada sin autorización |
| **Disponibilidad** | La información es accesible cuando se necesita |

### Autenticación y Acceso

- **Autenticación:** Verificar la identidad del usuario
  - Factor 1: Algo que sabe (contraseña)
  - Factor 2: Algo que tiene (token, móvil)
  - Factor 3: Algo que es (biometría)
- **MFA (Multi-Factor Authentication):** Usa 2 o más factores

### Principio de Mínimo Privilegio
Cada usuario/proceso solo debe tener los permisos estrictamente necesarios para su función.

---

## Tipos de Amenazas

| Amenaza | Descripción |
|---------|-------------|
| **Malware** | Software malicioso (virus, troyanos, ransomware) |
| **Phishing** | Suplantación de identidad por email |
| **Vishing** | Suplantación de identidad por teléfono |
| **Ransomware** | Cifra archivos y exige rescate |
| **DDoS** | Saturación del servicio con tráfico falso |
| **Ingeniería social** | Manipulación psicológica para obtener información |

---

## Medidas de Protección

### Control de acceso
```bash
# Linux: gestión de permisos
chmod 750 /datos/sensibles/
chown root:admins /datos/sensibles/
```

### Copias de seguridad
- **Completa:** Copia de todos los datos
- **Incremental:** Solo los cambios desde la última copia
- **Diferencial:** Los cambios desde la última copia completa

### Actualización y parcheo
- Aplicar parches de seguridad de forma regular
- La ventana entre detectar una vulnerabilidad y parchearla es crítica (CVEs)

---

## Manual de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Ciberseguridad/[Esp1 - ASC] [UD5] Manual «Seguridad en administración de sistemas».pdf`

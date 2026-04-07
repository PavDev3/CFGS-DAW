## Tema 5 — Configuración de Red y Acceso WAN

← [[Sistemas-Tema4]] | [[Sistemas]]

---

## Modos de Red en VirtualBox

### Tabla comparativa

| Modo | Internet | Visible en LAN | Comunicación entre VMs |
|------|----------|----------------|------------------------|
| **NAT** | Sí | No | Limitada |
| **Adaptador Puente** | Sí | Sí | Sí |
| **Red Interna** | No | No | Sí |

### NAT (Network Address Translation)
- La VM recibe IP privada de VirtualBox (típico: `10.0.2.x`)
- La VM accede a Internet a través del host
- La VM **no es visible** en la red local sin port forwarding

### Adaptador Puente (Bridged)
- La VM se conecta directamente a la red física
- El router le asigna IP propia (ej: `192.168.50.x`)
- Visible en la LAN para todos los equipos

### Red Interna
- Las VMs solo se comunican entre ellas
- Sin acceso a Internet ni al host
- Se usa para redes de laboratorio aisladas

---

## Configuración IP en Ubuntu con Netplan

### Localizar el fichero de configuración

```bash
ls /etc/netplan/
# Típicamente: 01-netcfg.yaml o 50-cloud-init.yaml

# Hacer copia de seguridad antes de editar
sudo cp /etc/netplan/XX.yaml /etc/netplan/XX.yaml.bak
```

### Editar configuración YAML

```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      dhcp4: true         # Adaptador 1 por DHCP
    enp0s8:
      dhcp4: false        # Adaptador 2 con IP estática
      addresses:
        - 10.10.10.10/24
```

**Importante:** YAML es sensible a la sangría. Usar espacios, no tabs.

### Aplicar cambios

```bash
# Modo seguro (permite volver atrás)
sudo netplan try

# Aplicar definitivamente
sudo netplan apply

# Verificar
ip a
ip r
```

### Verificar conectividad

```bash
# Ver interfaces y IPs
ip a
ip link

# Ver rutas
ip r

# Ping a otra máquina
ping -c 4 10.10.10.20

# Si ifconfig no está disponible
sudo apt install -y net-tools
ifconfig
```

---

## Configuración IP Estática en Windows (Red Interna)

1. Panel de control → Redes e Internet → Cambiar configuración del adaptador
2. Identificar el adaptador de Red interna
3. Click derecho → Propiedades → TCP/IPv4 → Propiedades
4. Configurar:
   - IP: `10.10.10.20`
   - Máscara: `255.255.255.0`
   - Puerta de enlace: (vacía)
5. Verificar con `ipconfig /all`

---

## Tecnologías de Acceso WAN

| Tecnología | Medio | Velocidad | Uso típico |
|-----------|-------|-----------|------------|
| **DSL** | Par de cobre telefónico | Variable, limitada por distancia | Zonas sin fibra |
| **FTTH** | Fibra óptica hasta el hogar | Muy alta, estable | Ciudades con fibra |
| **WiMAX** | Radio (inalámbrico) | Variable, afectado por interferencias | Rural, despliegue rápido |

### DSL
- Equipo: router DSL
- Limitaciones: velocidad y calidad dependen de la distancia del cobre

### FTTH (Fiber To The Home)
- Equipo: ONT + router
- Ventajas: gran ancho de banda, baja atenuación, estabilidad

### WiMAX
- Equipo: CPE exterior/interior + antena
- Compromisos: interferencias, línea de vista

---

## Laboratorio DAW-LAB

Configuración del router para el laboratorio:
- **LAN:** `192.168.50.0/24`
- **Router/gateway:** `192.168.50.1`
- **DHCP:** `192.168.50.100` a `192.168.50.200`
- **Red interna VMs:** `10.10.10.0/24`
  - Ubuntu: `10.10.10.10/24`
  - Windows: `10.10.10.20/24`

---

## Archivos de referencia

- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tema/Tema 5/practica-bloque4-config-red-virtualbox-netplan-wan.md`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tema/Tema 5/Configuración Virtualbox.pdf`
- 📎 Ver archivo: `/Users/imac/CFGS-DAW/Sistemas/Tema/Tema 5/PropuestaContenidosUnidad5_Bloques.pdf`

---

## Práctica Bloque 4 — VirtualBox + Ubuntu/Windows + WAN

**Duración:** 2–3 horas | **Entrega:** PDF/Markdown en Moodle

### Objetivos

- Configurar adaptadores de red en VirtualBox (NAT, Puente, Red interna)
- Configurar IP estática en Ubuntu con Netplan (YAML)
- Configurar IP estática en Windows 10/11
- Entender tecnologías WAN: DSL, FTTH, WiMAX

### Escenario de red (DAW-LAB)

| Elemento | Valor |
|---|---|
| LAN (router WiFi clase) | `192.168.50.0/24` |
| Gateway | `192.168.50.1` |
| DHCP | `192.168.50.100–200` |
| Red interna VMs | `10.10.10.0/24` |
| Ubuntu (intnet) | `10.10.10.10/24` |
| Windows (intnet) | `10.10.10.20/24` |

### Evidencias mínimas requeridas

- **VirtualBox:** captura configuración de red (NAT / Puente / Red interna)
- **Ubuntu:** `ip a`, `ip r`, fichero Netplan, `netplan try/apply`
- **Windows:** configuración IPv4, `ipconfig /all`, ping
- **Pruebas:** pings entre VMs (`10.10.10.10 ↔ 10.10.10.20`)

### Estructura del entregable

1. Datos del alumno/grupo
2. Topología y tabla de IPs
3. Capturas VirtualBox: NAT, Puente, Red interna
4. Ubuntu Netplan: YAML + `ip a/r` + pings
5. Windows IP estática: capturas IPv4 + `ipconfig /all` + ping
6. WAN: comparativa DSL/FTTH/WiMAX
7. Incidencias y cómo las resolviste

### Rúbrica

| Bloque | Peso |
|---|---|
| VirtualBox (NAT/Puente/Interna) | 30% |
| Ubuntu Netplan + verificación | 40% |
| Windows IP estática + pruebas | 20% |
| WAN comparativa | 10% |

### Checklist final

- [ ] Router DAW-LAB configurado y host con `192.168.50.x`
- [ ] Captura NAT + `ip a` (IP `10.0.2.x`)
- [ ] Captura Puente + `ip a` (IP `192.168.50.x`) o incidencia documentada
- [ ] Captura Red interna en ambas VMs
- [ ] Ubuntu: Netplan con IP estática `10.10.10.10/24`
- [ ] Windows: IP estática `10.10.10.20/24`
- [ ] Ping `10.10.10.10 ↔ 10.10.10.20`
- [ ] WAN: DSL vs FTTH vs WiMAX

### Troubleshooting rápido

| Problema | Solución |
|---|---|
| Netplan no aplica | Revisar sangría YAML; ejecutar `sudo netplan generate` |
| Sin ping entre VMs (Red interna) | Confirmar mismo nombre intnet (`intnet-daw`), revisar IPs y máscara |
| Modo puente inestable en WiFi | Depende del driver, documentar y continuar |
| Firewall Windows bloquea ICMP | Desactivar temporalmente o probar con servicio TCP |

---

## Preguntas de examen (Tema 5)

### Test

1. ¿Qué modo VirtualBox asigna IP 10.0.2.x sin ser visible en LAN?
   - a) Adaptador Puente · b) Red Interna · **c) NAT ✓** · d) Host-Only

2. ¿Qué comando aplica Netplan definitivamente?
   - a) netplan restart · b) netplan try · **c) netplan apply ✓** · d) systemctl reload network

3. ¿Dónde está el fichero de configuración de Netplan?
   - a) /etc/network/interfaces · **b) /etc/netplan/ ✓** · c) /etc/network/netplan/ · d) /usr/share/netplan/

4. ¿Qué tecnología WAN usa fibra hasta el domicilio?
   - a) DSL · b) WiMAX · **c) FTTH ✓** · d) ADSL

5. ¿Qué comando muestra las rutas en Linux?
   - a) route -a · b) netstat -r · **c) ip r ✓** · d) ifconfig -r

### Preguntas de desarrollo

1. Explica las diferencias entre NAT, Adaptador Puente y Red Interna en VirtualBox. ¿Cuándo usarías cada uno?
2. ¿Qué es Netplan? ¿En qué directorio se encuentra su configuración y cómo se aplican los cambios de forma segura?
3. Configura en YAML Netplan dos interfaces: `enp0s3` por DHCP y `enp0s8` con IP estática `10.10.10.10/24`. Explica cada línea.
4. Compara DSL, FTTH y WiMAX: medio físico, equipamiento necesario, ventajas e inconvenientes.
5. En un laboratorio con VMs en Red Interna, Ubuntu tiene `10.10.10.10/24` y Windows `10.10.10.20/24`. El ping falla. ¿Cuáles son las posibles causas y cómo las diagnosticas?

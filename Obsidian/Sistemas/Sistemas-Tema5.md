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

# Bloque 4 (DAW) - Practica: Configuracion de red y acceso WAN (VirtualBox + Ubuntu/Windows)

Objetivo
- Aprender a configurar adaptadores de red en VirtualBox (NAT, Adaptador puente, Red interna).
- Configurar red en Ubuntu con Netplan (YAML) y verificar con `ip` (y `ifconfig` si se instala).
- Configurar IP estatica en Ubuntu y Windows 10/11 y documentar con capturas.
- Entender (a nivel funcional) tecnologias de acceso WAN: DSL, FTTH, WiMAX.

Escenario (seguridad)
- La clase usa un router propio (de casa) para crear una red WiFi privada e independiente de la red del centro.
- Toda la practica ocurre dentro de esa LAN privada.

Duracion sugerida
- 2 a 3 horas.

---

## 0) Material y requisitos

Material
- Router WiFi (casa) con alimentacion.
- 1 portatil por grupo (ideal) o por alumno.
- VirtualBox instalado.
- ISOs:
  - Ubuntu (recomendado: 22.04 LTS o 24.04 LTS)
  - Windows 10/11 (VM o equipo fisico).

Requisitos de la red del router (recomendado)
- LAN: `192.168.50.0/24`
- Router/gateway: `192.168.50.1`
- DHCP: `192.168.50.100` a `192.168.50.200`
- DNS (en el router): el propio router (`192.168.50.1`) o el que configureis.

Direccionamiento para la red interna de VirtualBox (aislada entre VMs)
- Red interna (sin gateway): `10.10.10.0/24`
  - Ubuntu (intnet): `10.10.10.10/24`
  - Windows (intnet): `10.10.10.20/24`

Evidencias (capturas) minimas
- VirtualBox: pantalla de configuracion de red (NAT / Puente / Red interna).
- Ubuntu:
  - `ip a` y `ip r`
  - fichero de Netplan
  - `netplan try` / `netplan apply`
- Windows:
  - configuracion IPv4 en el adaptador
  - `ipconfig /all`
- Pruebas: pings y/o `curl`.

---

## 1) Introduccion: modos de red en VirtualBox

Cuando trabajamos con maquinas virtuales en VirtualBox, es necesario definir como se conectaran a la red. VirtualBox ofrece varios modos, pero los mas utilizados son:

- **NAT** - la VM accede a internet a traves del host, pero no es visible en la LAN.
- **Adaptador Puente (Bridged Adapter)** - la VM se comporta como un equipo mas dentro de la red local.
- **Red Interna (Internal Network)** - las VMs se comunican solo entre ellas, sin acceso al exterior.

Cada modo define como la maquina virtual se comunica con internet, con el ordenador anfitrion (host) y con otros dispositivos de la red.

### Modo NAT (Network Address Translation)

Es el modo por defecto de VirtualBox.

Funcionamiento:
- VirtualBox actua como un router.
- La VM recibe una IP privada creada por VirtualBox (tipicamente `10.0.2.x`).
- Todo el trafico pasa primero por el host y despues sale a internet.

Caracteristicas principales:
- Permite acceso a internet facilmente.
- No requiere configuracion adicional.
- La VM **no es visible** en la red local (sin reenvio de puertos).

Uso tipico:
- Navegacion por internet dentro de la VM.
- Pruebas basicas o laboratorios sin necesidad de comunicacion entre equipos.

### Adaptador Puente (Bridged Adapter)

Conecta la VM directamente a la red fisica del ordenador.

Funcionamiento:
- La tarjeta de red virtual se conecta a la tarjeta de red real del host.
- El router le asigna una IP propia (igual que a cualquier equipo de la LAN).

Ejemplo: si el host tiene `192.168.1.20`, la VM podria recibir `192.168.1.35` (o en nuestro lab, `192.168.50.x`).

Caracteristicas:
- Tiene acceso a internet.
- Puede comunicarse con otros dispositivos de la red.
- Otros dispositivos tambien pueden conectarse a ella.

Uso tipico:
- Pruebas de servidores.
- Simulacion de redes reales.
- Practicas de administracion de sistemas.

### Red Interna (Internal Network)

Permite que las VMs solo se comuniquen entre ellas.

Caracteristicas:
- No hay acceso a internet.
- No hay conexion con la red del host.
- Solo comunicacion entre VMs que comparten el mismo nombre de red interna.

VirtualBox crea una red privada virtual aislada.

Uso tipico:
- Simulacion de redes empresariales.
- Practicas de seguridad.
- Laboratorios de red aislados.

### Tabla comparativa de modos

| Modo            | Internet | Visible en red local | Comunicacion entre VMs |
|-----------------|----------|----------------------|------------------------|
| NAT             | Si       | No                   | Limitada               |
| Adaptador Puente| Si       | Si                   | Si                     |
| Red Interna     | No       | No                   | Si                     |

---

## 2) Preparacion del router (LAN privada por WiFi)

1. Entra al router y cambia credenciales de admin (si no estan cambiadas).
2. Crea SSID: `DAW-LAB` (o similar).
3. Seguridad WiFi: WPA2-PSK (AES) o WPA3 si todos los equipos lo soportan.
4. Clave fuerte (min 12-16 caracteres).
5. LAN del router: configura `192.168.50.1/24`.
6. DHCP activado: rango `192.168.50.100-200`.
7. (Opcional) Desconecta el puerto WAN: sin Internet, para que sea una red cerrada.

Checklist rapido del router
- [ ] SSID y clave propios
- [ ] Password admin cambiado
- [ ] LAN 192.168.50.1/24
- [ ] DHCP en 192.168.50.100-200

---

## 3) Preparacion del host (tu portatil)

1. Conectate al WiFi `DAW-LAB`.
2. Verifica que recibes IP del router (debe ser `192.168.50.x`).
   - Windows: `ipconfig`
   - Linux/macOS: `ip a` o `ifconfig`
3. Comprueba conectividad al router: ping a `192.168.50.1`.

Si el host NO recibe IP
- Revisa clave WiFi.
- Revisa DHCP activado.
- Reinicia WiFi del host o el router.

---

## 4) Crear las VMs (Ubuntu + Windows)

Recomendacion
- Ubuntu: 2 CPU, 2-4 GB RAM, 20 GB disco.
- Windows: 2 CPU, 4-8 GB RAM, 40 GB disco (si es VM).

Consejo para capturas
- Usa nombres claros en VirtualBox:
  - `DAW-Ubuntu-B4`
  - `DAW-Windows-B4`

### Como configurar el modo de red en VirtualBox (pasos)

1. Abrir VirtualBox.
2. Seleccionar la maquina virtual.
3. Ir a **Configuracion**.
4. Entrar en la seccion **Red**.
5. En **Adaptador 1**, seleccionar el modo de red deseado (NAT / Adaptador Puente / Red Interna).
6. Pulsar **Aceptar** y arrancar la VM.

Una vez iniciada la VM, verificar la configuracion de red con:
```bash
ip a
# o si esta instalado:
ifconfig
```

---

## 5) Parte A: VirtualBox - Modos NAT, Puente y Red interna

Vas a repetir pruebas para observar diferencias.
Guarda capturas de cada modo.

### A1) Modo NAT (salida facil, VM no visible en la LAN)

En la VM Ubuntu
1. Apaga la VM si esta encendida.
2. VirtualBox -> Settings -> Network
3. Adapter 1: Enable
4. Attached to: `NAT`
5. Inicia Ubuntu.

Comprobaciones en Ubuntu
1. Ver IP:
   - `ip a`
   - Deberias ver algo como `10.0.2.x` en la interfaz del Adapter 1.
2. Ver rutas:
   - `ip r`
   - Deberia aparecer una ruta por defecto via `10.0.2.2` (tipico de VirtualBox NAT).
3. Ping al router del lab:
   - `ping -c 4 192.168.50.1`
   - Puede fallar o no ser significativo: NAT crea otra red.

> **Resultado esperado:** la maquina virtual tiene salida a internet (a traves del host) pero no aparece como equipo en la red local `192.168.50.0/24`.

Pregunta para explicar (1-3 lineas)
- Por que una VM en NAT no suele ser accesible directamente desde otra maquina de la LAN (sin port forwarding).

### A2) Adaptador Puente (la VM es un equipo mas en la LAN privada)

En la VM Ubuntu
1. Apaga la VM.
2. Adapter 1 -> Attached to: `Bridged Adapter`.
3. Name: selecciona la tarjeta WiFi por la que estas conectado a `DAW-LAB`.
4. Inicia Ubuntu.

Comprobaciones en Ubuntu
1. `ip a` -> la VM debe recibir `192.168.50.x`.
2. `ip r` -> la ruta por defecto debe salir via `192.168.50.1`.
3. `ping -c 4 192.168.50.1`.

Comprobaciones desde Windows (o desde otro equipo en la WiFi)
1. `ping 192.168.50.X` (la IP puente de Ubuntu).

> **Resultado esperado:** la VM recibe una IP del router y aparece como un equipo mas en la red local. Otros equipos pueden hacerle ping.

Si el modo puente NO funciona sobre WiFi
- Confirma que has elegido la interfaz WiFi correcta.
- En VirtualBox (segun version): prueba Promiscuous Mode = `Allow VMs`.
- Si aun falla, documenta el problema (captura + 2 lineas) y continua con NAT + Red interna; el aprendizaje sigue siendo valido.

> [!TIP] **OPCIONAL — Modo practico/visual**
> Si el modo puente funciona correctamente, puedes demostrar de forma muy visual que la VM es un servidor real en la red:
>
> En Ubuntu, levanta un servidor HTTP con un solo comando:
> ```bash
> python3 -m http.server 8080
> ```
> Luego abre el navegador en el **host o en otro portatil de la clase** y entra a:
> ```
> http://192.168.50.X:8080
> ```
> (sustituye `X` por la IP puente de tu VM)
>
> Veras el listado de ficheros de la VM desde otro equipo. Esto demuestra exactamente por que existe el modo puente: la VM actua como un servidor real accesible en la LAN, igual que cualquier otro equipo conectado al router.
>
> Para cerrar el servidor: `Ctrl+C` en la terminal de Ubuntu.

### A3) Red Interna (VMs aisladas entre si, sin router)

Objetivo
- Tener un segundo adaptador en Ubuntu y Windows que solo se vean entre ellos.

En la VM Ubuntu
1. Apaga la VM.
2. Adapter 2: Enable
3. Attached to: `Internal Network`
4. Name: `intnet-daw`

En la VM Windows
1. Apaga la VM.
2. Adapter 2: Enable
3. Attached to: `Internal Network`
4. Name: `intnet-daw` (exactamente igual)

Inicia ambas VMs.

Comprobacion inicial
- Ubuntu: `ip a` debe mostrar una segunda interfaz (sin IP o con link up).
- Windows: debe aparecer un segundo adaptador de red.

> **Resultado esperado:** las maquinas virtuales se comunican entre ellas (tras asignar IPs estaticas en los pasos siguientes) pero no tienen acceso a internet ni a la red del host.

> [!TIP] **OPCIONAL — Modo practico/visual** (hacer despues de completar B4 y C)
> Una vez que Ubuntu tiene `10.10.10.10` y Windows tiene `10.10.10.20` y se hacen ping, puedes montar un **chat en tiempo real** entre las dos VMs usando solo `netcat`, sin instalar nada extra.
>
> En Ubuntu (modo escucha):
> ```bash
> nc -l -p 1234
> ```
> En Windows CMD (modo conexion):
> ```bash
> nc 10.10.10.10 1234
> ```
> A partir de ese momento, todo lo que escribas en una terminal aparece en la otra. Es una conexion TCP real, sin internet, sin router, solo a traves de la red interna que acabas de configurar.
>
> Para cerrar: `Ctrl+C` en cualquiera de los dos lados.
>
> Nota: en Windows puede que `nc` no este disponible por defecto. Alternativa: instalar `ncat` (incluido en Nmap para Windows) o usar PowerShell con `Test-NetConnection` para verificar el puerto.

---

## 6) Parte B: Ubuntu - IP estatica con Netplan (YAML)

Objetivo
- Mantener Adapter 1 (NAT o Puente) por DHCP.
- Configurar Adapter 2 (Red interna) con IP estatica `10.10.10.10/24`.

### B1) Identificar interfaces

En Ubuntu
1. Lista interfaces:
   - `ip link`
2. Mira cual tiene IP del NAT/Puente y cual es la interna:
   - `ip a`

Ejemplo (nombres varian)
- `enp0s3` -> Adapter 1 (NAT/Puente)
- `enp0s8` -> Adapter 2 (Red interna)

Apunta los nombres reales que te salen.

### B2) Localizar el fichero Netplan

1. Lista ficheros:
   - `ls /etc/netplan/`
2. Normalmente sera algo tipo `01-netcfg.yaml` o `50-cloud-init.yaml`.

Antes de tocar nada (recomendado)
- Haz copia:
  - `sudo cp /etc/netplan/XX.yaml /etc/netplan/XX.yaml.bak`

### B3) Editar YAML (ejemplo)

Abre el fichero con el editor que uses (nano/vi).

Plantilla orientativa (AJUSTA interfaces)
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      dhcp4: true
    enp0s8:
      dhcp4: false
      addresses:
        - 10.10.10.10/24
```

Notas importantes (errores tipicos)
- YAML es sensible a la sangria: usa espacios (no tabs).
- No pongas `gateway4` en la interfaz de Red interna.
- Si cambias `renderer`, documenta por que (en Desktop puede ser `NetworkManager`).

### B4) Aplicar y verificar

1. Prueba con modo seguro (te permite volver atras si pierdes red):
   - `sudo netplan try`
2. Si todo ok:
   - `sudo netplan apply`
3. Verifica:
   - `ip a`
   - `ip r`

Prueba de comunicacion por la red interna
- Desde Ubuntu:
  - `ping -c 4 10.10.10.20`

Opcional: ifconfig
- `ifconfig` no suele venir por defecto. Para usarlo:
  - `sudo apt update && sudo apt install -y net-tools`

---

## 7) Parte C: Windows 10/11 - IP estatica (Red interna)

Objetivo
- Configurar el adaptador de `intnet-daw` con `10.10.10.20/24`.

Pasos (GUI)
1. Panel de control -> Redes e Internet -> Centro de redes -> Cambiar configuracion del adaptador.
2. Identifica el adaptador de Red interna (suele ser el segundo, sin Internet).
3. Click derecho -> Propiedades.
4. Selecciona `Protocolo de Internet version 4 (TCP/IPv4)` -> Propiedades.
5. Marca `Usar la siguiente direccion IP`:
   - IP: `10.10.10.20`
   - Mascara: `255.255.255.0`
   - Puerta de enlace: (vacia)
6. DNS: (vacio) para esta red interna.

Verificacion
1. En CMD:
   - `ipconfig /all`
2. Ping a Ubuntu:
   - `ping 10.10.10.10`

Si el ping falla
- Comprueba que ambas VMs estan en `intnet-daw`.
- Revisa IPs y mascara /24.
- Firewall de Windows puede bloquear ICMP. Para evidenciarlo:
  - desactiva temporalmente solo para la prueba (documenta), o
  - usa otra prueba (por ejemplo, habilitar un servicio en Ubuntu y probar TCP).

---

## 8) Comparativa rapida: NAT vs Puente vs Red interna (para el informe)

Rellena con datos reales de tu caso.

| Modo         | IP tipica             | Gateway          | Visible en `192.168.50.0/24` | Comunicacion entre VMs |
|--------------|-----------------------|------------------|------------------------------|------------------------|
| NAT          | `10.0.2.x`            | `10.0.2.2`       | No (sin redireccion)         | Limitada               |
| Puente       | `192.168.50.x`        | `192.168.50.1`   | Si                           | Si                     |
| Red interna  | `10.10.10.10/.20`     | No aplica        | No                           | Si (solo entre VMs)    |

---

## 9) WAN (teoria aplicada): DSL vs FTTH vs WiMAX

Entregable (10-12 lineas o una tabla)

DSL
- Medio: par de cobre telefonico.
- Equipo en casa: router DSL.
- Limitaciones: velocidad y calidad dependen de distancia y estado del cobre; mas ruido/atenuacion.
- Uso tipico: zonas donde no llega fibra.

FTTH
- Medio: fibra optica hasta el hogar.
- Equipo en casa: ONT (o integrada) + router.
- Ventajas: gran ancho de banda, baja atenuacion, estabilidad; mejor escalabilidad.
- Uso tipico: ciudades/zonas con despliegue de fibra.

WiMAX (u otras inalambricas de largo alcance)
- Medio: radio (enlace inalambrico).
- Equipo: CPE exterior/interior + antena (segun despliegue).
- Compromisos: interferencias, linea de vista, comparticion del espectro; latencia/estabilidad variables.
- Uso tipico: rural, despliegue rapido o donde cablear es caro.

Conexion con la practica
- En clase has montado una LAN (router WiFi + VMs). La WAN seria el enlace del router hacia el ISP (DSL/FTTH/WiMAX).

---

## 10) Estructura del entregable (Moodle)

Entrega recomendada: 1 PDF o 1 Markdown exportado a PDF

Secciones minimas
1. Datos del alumno/grupo.
2. Topologia (dibujito simple) y tabla de IPs.
3. Capturas VirtualBox: NAT, Puente, Red interna.
4. Ubuntu Netplan:
   - fichero YAML
   - `ip a` y `ip r`
   - pruebas de ping
5. Windows IP estatica:
   - capturas IPv4
   - `ipconfig /all`
   - ping
6. WAN: comparativa DSL/FTTH/WiMAX.
7. Incidencias y como las resolviste (2-6 lineas).

Rubrica sugerida
- 30% VirtualBox: evidencia y explicacion breve de NAT/Puente/Interna.
- 40% Ubuntu: Netplan correcto + verificacion con comandos.
- 20% Windows: IP estatica correcta + pruebas.
- 10% WAN: comparativa clara.

---

## 11) Troubleshooting rapido

No tengo Internet (si el router NO tiene WAN conectada)
- Esto es normal si decidisteis una LAN cerrada. La practica no necesita Internet.

Netplan no aplica
- Revisa sangria del YAML.
- Ejecuta: `sudo netplan generate` (para ver si hay errores) y captura el mensaje.

No hay ping entre VMs en Red interna
- Confirma que ambas usan el mismo nombre de Red interna (`intnet-daw`).
- Revisa IPs y mascara /24.
- Firewall de Windows puede bloquear ICMP.

Modo puente inestable en WiFi
- Depende de driver/tarjeta. Documenta y continua con el resto.

---

## 12) Checklist final (antes de entregar)

- [ ] Router `DAW-LAB` configurado y host con `192.168.50.x`
- [ ] Captura NAT + `ip a` (IP `10.0.2.x`)
- [ ] Captura Puente + `ip a` (IP `192.168.50.x`) o incidencia documentada
- [ ] Captura Red interna en ambas VMs
- [ ] Ubuntu: Netplan con IP estatica `10.10.10.10/24`
- [ ] Windows: IP estatica `10.10.10.20/24`
- [ ] Ping `10.10.10.10` <-> `10.10.10.20`
- [ ] WAN: DSL vs FTTH vs WiMAX


## Preguntas para examen

TEST:
1. ¿Qué modo VirtualBox asigna IP 10.0.2.x sin ser visible en LAN?
a) Adaptador Puente  c) NAT ✓
b) Red Interna       d) Host-Only

2. ¿Qué comando aplica Netplan definitivamente?
a) netplan restart   c) netplan apply ✓
b) netplan try       d) systemctl reload network

3. ¿Dónde está el fichero de configuración de Netplan?
a) /etc/network/interfaces   c) /etc/network/netplan/
b) /etc/netplan/ ✓           d) /usr/share/netplan/

4. ¿Qué tecnología WAN usa fibra hasta el domicilio?
a) DSL      c) FTTH ✓
b) WiMAX    d) ADSL

5. ¿Qué comando muestra las rutas en Linux?
a) route -a       c) ip r ✓ 
b) netstat -r     d) ifconfig -r
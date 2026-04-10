# Bloque 4 - Tutorial completo: Configuracion de red y acceso WAN
## VirtualBox + Ubuntu (Netplan) + Windows

> **Asignatura:** Sistemas Informaticos - DAW  
> **Tema:** 5  
> **Duracion:** 2-3 horas  
> **Entrega:** PDF en Moodle (capturas incluidas)

---

## Antes de empezar

### Que vamos a hacer

En esta practica vamos a:

1. Conectar el portatil a una red WiFi privada de clase (`DAW-LAB`)
2. Crear dos maquinas virtuales en VirtualBox (Ubuntu y Windows)
3. Probar los tres modos de red de VirtualBox: **NAT**, **Adaptador Puente** y **Red Interna**
4. Configurar una IP estatica en Ubuntu usando **Netplan** (fichero YAML)
5. Configurar una IP estatica en Windows desde la GUI
6. Comprobar que Ubuntu y Windows se comunican entre si por la red interna
7. Entender brevemente las tecnologias WAN: DSL, FTTH y WiMAX

### Tabla de IPs del laboratorio

| Dispositivo / interfaz          | Red                    | IP                  |
|---------------------------------|------------------------|---------------------|
| Router WiFi clase               | `192.168.50.0/24`      | `192.168.50.1`      |
| Host (tu portatil)              | `192.168.50.0/24`      | `192.168.50.x` (DHCP) |
| Ubuntu - Adapter 1 (NAT)        | VirtualBox NAT         | `10.0.2.x`          |
| Ubuntu - Adapter 1 (Puente)     | `192.168.50.0/24`      | `192.168.50.x` (DHCP) |
| Ubuntu - Adapter 2 (Red interna)| `10.10.10.0/24`        | `10.10.10.10`       |
| Windows - Adapter 2 (Red interna)| `10.10.10.0/24`       | `10.10.10.20`       |

---

## Paso 0: Conectar el host al router de clase

1. El profesor encendera el router WiFi con SSID `DAW-LAB`.
2. Conecta tu portatil a esa red WiFi (pregunta la clave al profesor).
3. Verifica que recibes IP del router:
   - En **Windows** abre CMD y escribe:
     ```
     ipconfig
     ```
   - Deberias ver una IP del tipo `192.168.50.x` en el adaptador WiFi.
4. Haz ping al gateway para confirmar conectividad:
   ```
   ping 192.168.50.1
   ```
   Si recibes respuesta, el host esta correctamente en la red de clase.

> **CAPTURA 0:** pantalla del `ipconfig` del host mostrando la IP `192.168.50.x`

---

## Paso 1: Preparar las maquinas virtuales en VirtualBox

### 1.1 Crear (o importar) la VM Ubuntu

Si ya tienes una VM Ubuntu creada, salta al paso 1.3.

1. Abre **VirtualBox**.
2. Haz clic en **Nueva**.
3. Nombre: `DAW-Ubuntu-B4`
4. Tipo: `Linux` / Version: `Ubuntu (64-bit)`
5. RAM: `2048 MB` (minimo); `4096 MB` si el portatil lo permite.
6. Disco duro: crear disco nuevo -> `VDI` -> asignacion dinamica -> `20 GB`.
7. Haz clic en **Crear**.
8. Antes de arrancar, asigna la ISO: `Configuracion -> Almacenamiento -> Controlador IDE -> icono de disco -> elegir ISO de Ubuntu`.
9. Arranca y completa la instalacion de Ubuntu (elige instalacion minima si el disco es pequeno).

### 1.2 Crear (o importar) la VM Windows

1. En VirtualBox, haz clic en **Nueva**.
2. Nombre: `DAW-Windows-B4`
3. Tipo: `Microsoft Windows` / Version: `Windows 10 (64-bit)` o `Windows 11`.
4. RAM: `4096 MB` minimo.
5. Disco: `40 GB` asignacion dinamica.
6. Asigna la ISO de Windows y completa la instalacion.

> Si el centro proporciona VMs ya instaladas, importalas desde `Archivo -> Importar servicio virtualizado`.

### 1.3 Como acceder a la configuracion de red de una VM

Para cualquiera de las dos VMs:

1. **Apaga la VM** si esta encendida.
2. Selecciona la VM en la lista de VirtualBox.
3. Haz clic en **Configuracion** (icono de engranaje o `Ctrl+S`).
4. Ve a la seccion **Red**.
5. Ahi tienes **Adaptador 1**, **Adaptador 2**, etc.
6. Cada adaptador se puede habilitar/deshabilitar y elegir el modo.

---

## Paso 2: Modo NAT - la VM accede a internet sin ser visible en la LAN

### Que es NAT

VirtualBox hace de router. La VM recibe una IP privada del propio VirtualBox (`10.0.2.x`) y todo el trafico sale a internet a traves del host. La VM **no es visible** desde otros equipos de la red.

```
[Internet] <-> [Host 192.168.50.x] <-> [VirtualBox NAT 10.0.2.1] <-> [VM 10.0.2.15]
```

### Configurar NAT en Ubuntu

1. Apaga `DAW-Ubuntu-B4`.
2. `Configuracion -> Red -> Adaptador 1`
3. Marca **Habilitar adaptador de red**.
4. Conectado a: **NAT**
5. Haz clic en **Aceptar**.
6. Arranca Ubuntu.

### Verificar NAT en Ubuntu

Abre una terminal en Ubuntu (`Ctrl+Alt+T`) y ejecuta:

```bash
ip a
```

Busca la interfaz de red (normalmente `enp0s3`). Deberias ver una IP del tipo `10.0.2.x`.

```bash
ip r
```

Deberias ver la ruta por defecto apuntando a `10.0.2.2` (el gateway virtual de VirtualBox):
```
default via 10.0.2.2 dev enp0s3
```

Prueba de acceso a internet (si el router de clase tiene WAN conectada):
```bash
ping -c 4 8.8.8.8
```

Intenta hacer ping al router de clase:
```bash
ping -c 4 192.168.50.1
```
> Este ping **fallara o no recibira respuesta**: la VM en NAT no pertenece a la red `192.168.50.0/24`. Esto es el comportamiento esperado.

> **CAPTURA 1a:** configuracion VirtualBox con Adaptador 1 en NAT  
> **CAPTURA 1b:** `ip a` de Ubuntu mostrando IP `10.0.2.x`  
> **CAPTURA 1c:** `ip r` de Ubuntu  

**Pregunta para el informe:** Por que una VM en modo NAT no es accesible directamente desde otro equipo de la LAN? (1-3 lineas)

---

## Paso 3: Adaptador Puente - la VM como equipo mas de la red

### Que es Adaptador Puente

La VM se conecta directamente a la tarjeta de red fisica del host. El router le asigna una IP igual que a cualquier otro equipo. La VM **es visible** en la red local.

```
[Router 192.168.50.1] <-> [Host 192.168.50.x] 
                      <-> [VM Puente 192.168.50.y]  <- misma red fisica
```

### Configurar Adaptador Puente en Ubuntu

1. Apaga `DAW-Ubuntu-B4`.
2. `Configuracion -> Red -> Adaptador 1`
3. Conectado a: **Adaptador puente**
4. Nombre: selecciona la tarjeta WiFi del host (la que esta conectada a `DAW-LAB`).
5. Haz clic en **Aceptar**.
6. Arranca Ubuntu.

### Verificar Adaptador Puente en Ubuntu

```bash
ip a
```

La interfaz `enp0s3` deberia tener ahora una IP `192.168.50.x` (asignada por el router).

```bash
ip r
```

La ruta por defecto debe apuntar a `192.168.50.1`:
```
default via 192.168.50.1 dev enp0s3
```

Ping al router:
```bash
ping -c 4 192.168.50.1
```
Debe funcionar correctamente.

Ping desde otro equipo de la clase a la VM (pide la IP al compañero para que te haga ping, o lo hace el profesor):
```bash
ping 192.168.50.Y   # IP de la VM Ubuntu del compañero
```

> **CAPTURA 2a:** configuracion VirtualBox con Adaptador Puente y la tarjeta WiFi seleccionada  
> **CAPTURA 2b:** `ip a` mostrando IP `192.168.50.x`  
> **CAPTURA 2c:** `ip r` con gateway `192.168.50.1`  
> **CAPTURA 2d:** ping desde otro equipo a la VM (o desde la VM al router)

> **Si el modo puente no funciona sobre WiFi:** algunos drivers no lo soportan bien. Documenta el problema con una captura y 2 lineas de explicacion. La practica sigue siendo valida con NAT + Red interna.

### Opcional: demostrar que la VM es un servidor real en la LAN

Si el modo puente funciona, puedes hacer esto para ver claramente para que sirve:

En Ubuntu, levanta un servidor web en un comando:
```bash
python3 -m http.server 8080
```

Desde el **host o desde otro portatil de la clase**, abre el navegador y entra a:
```
http://192.168.50.X:8080
```
(sustituye `X` por la IP puente de tu VM)

Veras el listado de ficheros de la VM. La VM actua como un servidor real accesible en la LAN. Para cerrar el servidor: `Ctrl+C`.

---

## Paso 4: Red Interna - VMs aisladas entre si

### Que es Red Interna

Las VMs comparten una red virtual privada aislada. No tienen acceso a internet ni a la red del host. Solo se comunican entre ellas si estan en la misma red interna (mismo nombre).

```
[Ubuntu 10.10.10.10] <---intnet-daw---> [Windows 10.10.10.20]
         |                                       |
     sin internet                           sin internet
```

### Configurar Red Interna en Ubuntu (Adapter 2)

1. Apaga `DAW-Ubuntu-B4`.
2. `Configuracion -> Red -> Adaptador 2`
3. Marca **Habilitar adaptador de red**.
4. Conectado a: **Red interna**
5. Nombre: `intnet-daw`
6. Haz clic en **Aceptar**.

> Deja el Adaptador 1 en NAT o Puente segun el paso anterior.

### Configurar Red Interna en Windows (Adapter 2)

1. Apaga `DAW-Windows-B4`.
2. `Configuracion -> Red -> Adaptador 2`
3. Marca **Habilitar adaptador de red**.
4. Conectado a: **Red interna**
5. Nombre: `intnet-daw` (exactamente igual que en Ubuntu)
6. Haz clic en **Aceptar**.

> **IMPORTANTE:** el nombre debe ser identico en ambas VMs para que esten en la misma red.

Arranca ambas VMs.

Comprobacion inicial en Ubuntu:
```bash
ip link
```
Deberia aparecer una segunda interfaz (normalmente `enp0s8`) sin IP asignada todavia. Le asignaremos IP en el siguiente paso.

> **CAPTURA 3a:** configuracion VirtualBox - Adaptador 2 en Red interna en Ubuntu  
> **CAPTURA 3b:** configuracion VirtualBox - Adaptador 2 en Red interna en Windows  
> **CAPTURA 3c:** `ip link` en Ubuntu mostrando la segunda interfaz

---

## Paso 5: Ubuntu - IP estatica con Netplan

### Que es Netplan

Netplan es la herramienta de configuracion de red en Ubuntu (desde la version 17.10). La configuracion se escribe en un fichero YAML en `/etc/netplan/`. Netplan genera la configuracion para el sistema de red subyacente (`networkd` o `NetworkManager`).

### 5.1 Identificar las interfaces de red

En Ubuntu, abre una terminal:

```bash
ip link
```

Ejemplo de salida tipica:
```
1: lo: <LOOPBACK,UP> ...
2: enp0s3: <BROADCAST,UP> ...    <- Adapter 1 (NAT o Puente)
3: enp0s8: <BROADCAST> ...       <- Adapter 2 (Red interna, sin IP aun)
```

Apunta los nombres reales de tus interfaces (pueden ser diferentes).

```bash
ip a
```

Confirma cual tiene IP (el Adapter 1) y cual no (el Adapter 2).

### 5.2 Localizar el fichero Netplan

```bash
ls /etc/netplan/
```

Normalmente apareceran ficheros como:
- `01-netcfg.yaml`
- `50-cloud-init.yaml`
- `00-installer-config.yaml`

El nombre puede variar segun la version de Ubuntu. Trabaja con el que aparezca.

**Antes de modificar nada, haz una copia de seguridad:**

```bash
sudo cp /etc/netplan/01-netcfg.yaml /etc/netplan/01-netcfg.yaml.bak
```

(cambia `01-netcfg.yaml` por el nombre real de tu fichero)

### 5.3 Ver el contenido actual del fichero

```bash
cat /etc/netplan/01-netcfg.yaml
```

Apunta lo que hay actualmente antes de modificarlo.

### 5.4 Editar el fichero Netplan

```bash
sudo nano /etc/netplan/01-netcfg.yaml
```

Sustituye el contenido por esta plantilla, ajustando los nombres de interfaz a los tuyos:

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

> Sustituye `enp0s3` y `enp0s8` por los nombres reales de tus interfaces.

**Reglas criticas del YAML (errores tipicos):**
- Usa **espacios**, nunca tabulaciones.
- La sangria debe ser exacta: 2 espacios por nivel.
- No pongas `gateway4` en la interfaz de Red interna (no tiene gateway).
- Los dos puntos `:` despues de cada clave son obligatorios.

Para guardar en nano: `Ctrl+O` -> `Enter` -> `Ctrl+X`

> **CAPTURA 4a:** contenido del fichero YAML en el editor

### 5.5 Aplicar la configuracion

Primero valida la sintaxis sin aplicar cambios:

```bash
sudo netplan generate
```

Si no hay errores (no muestra nada), aplica con modo prueba (tienes 120 segundos para confirmar antes de que revierta):

```bash
sudo netplan try
```

Si todo esta bien, escribe `yes` y pulsa Enter. Si has perdido conectividad, espera 120 segundos y revertera solo.

Aplicacion definitiva:

```bash
sudo netplan apply
```

### 5.6 Verificar la configuracion

```bash
ip a
```

La interfaz `enp0s8` debe mostrar ahora la IP `10.10.10.10/24`.

```bash
ip r
```

Debe aparecer la ruta a la red `10.10.10.0/24` por la interfaz `enp0s8`.

> **CAPTURA 4b:** `ip a` mostrando `10.10.10.10/24` en enp0s8  
> **CAPTURA 4c:** `ip r`  

**Opcional - instalar ifconfig (herramienta clasica):**

```bash
sudo apt update && sudo apt install -y net-tools
ifconfig
```

> **CAPTURA 4d (opcional):** salida de `ifconfig`

---

## Paso 6: Windows - IP estatica en la Red interna

### 6.1 Abrir la configuracion de adaptadores de red

1. Pulsa `Win + R`, escribe `ncpa.cpl` y pulsa Enter.
   - (Alternativa: Panel de control -> Redes e Internet -> Centro de redes -> Cambiar configuracion del adaptador)
2. Apareceran los adaptadores de red. Identifica el **segundo adaptador** (el que corresponde a `intnet-daw`). Suele aparecer como "Ethernet 2" o similar, sin conexion a internet.

### 6.2 Configurar IP estatica

1. Haz clic derecho sobre el adaptador de Red interna -> **Propiedades**.
2. Selecciona `Protocolo de Internet version 4 (TCP/IPv4)`.
3. Haz clic en **Propiedades**.
4. Marca **Usar la siguiente direccion IP**:
   - Direccion IP: `10.10.10.20`
   - Mascara de subred: `255.255.255.0`
   - Puerta de enlace predeterminada: (dejar en blanco)
5. DNS: dejar en blanco.
6. Haz clic en **Aceptar** -> **Cerrar**.

> **CAPTURA 5a:** ventana de propiedades IPv4 con los datos rellenados

### 6.3 Verificar en CMD

Abre CMD (`Win + R` -> `cmd`):

```
ipconfig /all
```

Busca el adaptador de Red interna y confirma que muestra `10.10.10.20` con mascara `255.255.255.0`.

> **CAPTURA 5b:** `ipconfig /all` mostrando `10.10.10.20`

---

## Paso 7: Prueba de comunicacion entre VMs (Red interna)

Ahora Ubuntu tiene `10.10.10.10` y Windows tiene `10.10.10.20`. Comprueba que se ven.

### Desde Ubuntu -> ping a Windows

```bash
ping -c 4 10.10.10.20
```

Deben llegar las 4 respuestas.

### Desde Windows -> ping a Ubuntu

En CMD de Windows:

```
ping 10.10.10.10
```

Deben llegar respuestas.

> Si el ping desde Windows falla pero desde Ubuntu funciona, el firewall de Windows puede estar bloqueando ICMP. Para verificarlo temporalmente: desactiva el firewall de Windows Defender solo para la red privada, haz la prueba, captura y vuelve a activarlo.

> **CAPTURA 6a:** ping desde Ubuntu a `10.10.10.20` con respuestas  
> **CAPTURA 6b:** ping desde Windows a `10.10.10.10` con respuestas

### Opcional: chat en tiempo real entre VMs con netcat

Una vez que el ping funciona, puedes probar una conexion TCP real entre las dos VMs sin instalar nada extra:

En Ubuntu (modo escucha):
```bash
nc -l -p 1234
```

En Windows CMD (modo conexion, requiere `ncat` de Nmap o similar):
```
nc 10.10.10.10 1234
```

Todo lo que escribas en una terminal aparece en la otra. Es una conexion TCP directa por la red interna, sin internet, sin router. Para cerrar: `Ctrl+C`.

---

## Paso 8: Tabla comparativa de modos (para el informe)

Rellena con los datos reales obtenidos en la practica:

| Modo            | IP tipica          | Gateway          | Visible en `192.168.50.0/24` | Comunicacion entre VMs |
|-----------------|--------------------|------------------|------------------------------|------------------------|
| NAT             | `10.0.2.x`         | `10.0.2.2`       | No                           | Limitada               |
| Adaptador Puente| `192.168.50.x`     | `192.168.50.1`   | Si                           | Si                     |
| Red Interna     | `10.10.10.10/.20`  | No aplica        | No                           | Si (solo entre VMs)    |

---

## Paso 9: WAN - DSL, FTTH y WiMAX (teoria aplicada)

En la practica has montado una **LAN** (router WiFi + VMs). La conexion del router hacia el ISP seria la **WAN**. Estas son las tecnologias mas comunes:

### DSL (Digital Subscriber Line)

- **Medio fisico:** par de cobre telefonico (la linea de telefono convencional).
- **Equipo en casa:** router DSL/ADSL.
- **Velocidad tipica:** hasta ~20 Mbps bajada (ADSL2+); limitada por distancia a la central.
- **Limitaciones:** a mas distancia de la central, peor senal y velocidad. El cobre sufre atenuacion e interferencias.
- **Uso tipico:** zonas donde no llega la fibra optica.

### FTTH (Fiber To The Home)

- **Medio fisico:** fibra optica hasta el domicilio.
- **Equipo en casa:** ONT (Optical Network Terminal) + router del operador.
- **Velocidad tipica:** 300 Mbps, 600 Mbps, 1 Gbps o mas (simetrico en muchos casos).
- **Ventajas:** gran ancho de banda, baja atenuacion, muy estable, escalable.
- **Uso tipico:** ciudades y zonas con despliegue de fibra del operador.

### WiMAX (Wireless Interoperability for Microwave Access)

- **Medio fisico:** enlace de radio (inalambrico).
- **Equipo en casa:** CPE (Customer Premises Equipment) exterior/interior con antena.
- **Velocidad tipica:** hasta ~70 Mbps teoricos; en la practica menor y compartido.
- **Compromisos:** interferencias de radio, necesita linea de vista (o casi), el espectro se comparte entre usuarios, latencia y estabilidad variables.
- **Uso tipico:** zonas rurales o de dificil cableado donde DSL y fibra no llegan.

### Tabla comparativa WAN

| Tecnologia | Medio       | Velocidad tipica     | Ventaja principal       | Inconveniente principal       |
|------------|-------------|----------------------|-------------------------|-------------------------------|
| DSL/ADSL   | Cobre        | Hasta ~20 Mbps       | Infraestructura existente| Degrada con la distancia      |
| FTTH       | Fibra optica | 300 Mbps - 1 Gbps+   | Gran velocidad y estabilidad | Coste de despliegue        |
| WiMAX      | Radio        | Hasta ~70 Mbps       | Sin cableado necesario  | Interferencias, latencia      |

---

## Paso 10: Estructura del informe a entregar en Moodle

Entrega: **1 PDF** con las siguientes secciones:

1. **Datos del alumno/grupo**
   - Nombre, curso, fecha.

2. **Topologia y tabla de IPs**
   - Dibujito simple (puede ser a mano y fotografiado) mostrando router, host, Ubuntu y Windows.
   - Tabla con todas las IPs usadas.

3. **Capturas VirtualBox - Modos de red**
   - NAT: captura configuracion + `ip a`
   - Adaptador Puente: captura configuracion + `ip a`
   - Red interna: captura configuracion en ambas VMs

4. **Ubuntu - Netplan**
   - Contenido del fichero YAML
   - `ip a` y `ip r` tras aplicar
   - Ping a `10.10.10.20`

5. **Windows - IP estatica**
   - Captura de propiedades IPv4 rellenadas
   - `ipconfig /all`
   - Ping a `10.10.10.10`

6. **Tabla comparativa modos VirtualBox**

7. **WAN: comparativa DSL / FTTH / WiMAX** (tabla o 10-12 lineas)

8. **Incidencias** (2-6 lineas): problemas que tuviste y como los resolviste.

### Rubrica

| Apartado                                 | Peso |
|------------------------------------------|------|
| VirtualBox: evidencia y explicacion modos | 30%  |
| Ubuntu: Netplan correcto + verificacion   | 40%  |
| Windows: IP estatica + pruebas           | 20%  |
| WAN: comparativa clara                   | 10%  |

---

## Troubleshooting

### No tengo internet en la VM

Si el router de clase NO tiene WAN conectada (red cerrada), es normal. La practica no necesita internet.

### Netplan no aplica / error al ejecutar

```bash
sudo netplan generate
```

Este comando muestra los errores de sintaxis. Los mas frecuentes:
- Tabulaciones en lugar de espacios.
- Sangria incorrecta.
- Nombre de interfaz mal escrito (distingue mayusculas).

Comprueba con `cat -A /etc/netplan/tu-fichero.yaml` que no hay caracteres `^I` (tabulaciones).

### No hay ping entre VMs en Red interna

1. Confirma que ambas VMs usan **exactamente el mismo nombre** de red interna (`intnet-daw`).
2. Confirma las IPs: Ubuntu `10.10.10.10/24`, Windows `10.10.10.20/24`.
3. El firewall de Windows puede bloquear ICMP. Desactivalo temporalmente para la prueba.
4. Comprueba con `ip a` que la interfaz de Ubuntu tiene la IP correcta tras `netplan apply`.

### Modo puente inestable en WiFi

Algunos drivers WiFi no soportan el modo promiscuo necesario para el adaptador puente. Documenta el problema (captura + 2 lineas) y continua con NAT + Red interna. El aprendizaje sigue siendo valido.

### La segunda interfaz de Ubuntu no aparece en `ip a`

Verifica en VirtualBox que el Adaptador 2 esta **habilitado** y que la VM se ha reiniciado despues de habilitarlo.

---

## Checklist final (antes de entregar)

- [ ] Host conectado a `DAW-LAB` con IP `192.168.50.x`
- [ ] Captura NAT + `ip a` (IP `10.0.2.x`)
- [ ] Captura Puente + `ip a` (IP `192.168.50.x`) o incidencia documentada
- [ ] Captura Red interna en Ubuntu y Windows (mismo nombre `intnet-daw`)
- [ ] Ubuntu: fichero Netplan YAML correcto
- [ ] Ubuntu: `ip a` con `10.10.10.10/24` en enp0s8
- [ ] Windows: IP estatica `10.10.10.20/24` configurada
- [ ] Ping `10.10.10.10` <-> `10.10.10.20` funcionando (capturas)
- [ ] Tabla comparativa modos VirtualBox rellenada
- [ ] Apartado WAN: DSL / FTTH / WiMAX

---

## Preguntas de examen

**TEST:**

1. Que modo VirtualBox asigna IP `10.0.2.x` sin ser visible en la LAN?  
   a) Adaptador Puente  
   b) Red Interna  
   c) **NAT**  
   d) Host-Only  

2. Que comando aplica la configuracion de Netplan definitivamente?  
   a) `netplan restart`  
   b) `netplan try`  
   c) **`netplan apply`**  
   d) `systemctl reload network`  

3. Donde esta el fichero de configuracion de Netplan?  
   a) `/etc/network/interfaces`  
   b) **`/etc/netplan/`**  
   c) `/etc/network/netplan/`  
   d) `/usr/share/netplan/`  

4. Que tecnologia WAN usa fibra optica hasta el domicilio?  
   a) DSL  
   b) WiMAX  
   c) **FTTH**  
   d) ADSL  

5. Que comando muestra las rutas de red en Linux?  
   a) `route -a`  
   b) `netstat -r`  
   c) **`ip r`**  
   d) `ifconfig -r`  

6. En Red interna de VirtualBox, que ocurre si las dos VMs tienen nombres de red diferentes?  
   a) Se comunican con latencia alta  
   b) Solo se comunican por IPv6  
   c) **No se ven entre ellas**  
   d) El router les asigna IPs distintas  

7. En el fichero Netplan YAML, que caracter NO se debe usar para la sangria?  
   a) Espacio simple  
   b) Espacio doble  
   c) Espacio cuadruple  
   d) **Tabulacion**  

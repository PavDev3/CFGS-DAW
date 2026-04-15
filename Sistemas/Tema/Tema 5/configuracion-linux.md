# 5.5.2. Configuración de Red en Linux (Ubuntu)

La configuración de red en sistemas operativos basados en Linux, específicamente en Ubuntu, ha evolucionado significativamente a lo largo de los años. Actualmente, los administradores de sistemas disponen de múltiples herramientas que van desde interfaces gráficas intuitivas hasta potentes herramientas de línea de comandos e infraestructuras de configuración declarativa.

A continuación, se detallan los métodos y herramientas principales para la gestión de redes en Ubuntu.

---

## 1. Modo Gráfico (NetworkManager)

En las versiones de escritorio de Ubuntu (Ubuntu Desktop), la configuración de red se gestiona de manera predeterminada a través de **NetworkManager** mediante su interfaz gráfica (GUI).

* **Acceso:** Se puede acceder desde la esquina superior derecha de la pantalla (el menú del sistema) y seleccionando la configuración de red (Wired o Wi-Fi) o yendo directamente a **Configuración > Red / Wi-Fi**.
* **Funcionalidades:** Permite configurar de manera sencilla conexiones IPv4 e IPv6, establecer direcciones IP estáticas o dinámicas (DHCP), configurar servidores DNS, gestionar conexiones VPN y establecer perfiles de red.
* **Ventaja:** Ideal para usuarios de escritorio, ya que abstrae la complejidad de los archivos de configuración subyacentes.

---

## 2. Comandos de Red: `ifconfig` vs `ip`

Para la administración desde la terminal o en entornos de servidor (Ubuntu Server), se utilizan comandos específicos para inspeccionar y modificar las interfaces de red.

### El comando tradicional: `ifconfig`
Históricamente, `ifconfig` (parte del paquete `net-tools`) era el estándar para la configuración de red en Linux.
* **Uso común:** Visualizar las interfaces activas, sus direcciones IP y la dirección MAC.
* **Estado actual:** Está **obsoleto (deprecated)** en las distribuciones modernas de Linux y no viene instalado por defecto en las versiones recientes de Ubuntu.
* **Ejemplo de uso:**
    ```bash
    # Ver interfaces de red
    ifconfig

    # Levantar o apagar una interfaz
    sudo ifconfig eth0 up
    sudo ifconfig eth0 down
    ```

### El estándar moderno: `ip`
El comando `ip` (parte del paquete `iproute2`) es el reemplazo directo y moderno de `ifconfig`. Es mucho más potente y versátil.
* **Uso común:** Gestionar enrutamiento, dispositivos de red, políticas de ruteo y túneles.
* **Ejemplo de uso:**
    ```bash
    # Ver direcciones IP de todas las interfaces (equivalente a ifconfig)
    ip a  # o ip address

    # Ver el estado de los enlaces (interfaces físicas)
    ip link

    # Mostrar la tabla de enrutamiento (Gateway)
    ip r  # o ip route
    ```

---

## 3. Gestión de Nombres: `hostnamectl`

El *hostname* es el nombre de red que identifica a la máquina dentro de una red local. En distribuciones basadas en systemd (como Ubuntu 16.04 en adelante), la herramienta principal para gestionar esto es `hostnamectl`.

* **Ver el nombre actual y detalles del sistema:**
    ```bash
    hostnamectl status
    ```
    *Este comando muestra no solo el hostname estático, sino también información sobre el kernel, la arquitectura y el sistema operativo.*

* **Cambiar el nombre del equipo:**
    Para cambiar el nombre del equipo de forma persistente (sin necesidad de reiniciar inmediatamente o editar archivos manualmente), se utiliza:
    ```bash
    sudo hostnamectl set-hostname nuevo-nombre-equipo
    ```
    *(Nota: También es recomendable actualizar el archivo `/etc/hosts` para que refleje este nuevo nombre apuntando a `127.0.1.1`).*

---

## 4. Netplan (Archivos `.yaml`)

Desde Ubuntu 17.10, Canonical introdujo **Netplan** como la utilidad predeterminada de gestión de red por línea de comandos para configurar el sistema subyacente (NetworkManager o systemd-networkd).

Netplan utiliza archivos de configuración en formato **YAML**, los cuales son fáciles de leer y escribir.

* **Ubicación de los archivos:** Los archivos de configuración se encuentran en `/etc/netplan/`. Generalmente tienen nombres como `01-netcfg.yaml` o `50-cloud-init.yaml`.
* **Ejemplo de configuración estática (IP Fija):**
    ```yaml
    network:
      version: 2
      renderer: networkd
      ethernets:
        enp3s0:
          dhcp4: no
          addresses:
            - 192.168.1.100/24
          routes:
            - to: default
              via: 192.168.1.1
          nameservers:
            addresses: [8.8.8.8, 8.8.4.4]
    ```

### Comandos clave de Netplan:
Una vez editado el archivo `.yaml`, los cambios no se aplican automáticamente. Se deben usar los siguientes comandos:

1.  **Probar la configuración:**
    ```bash
    sudo netplan try
    ```
    *(Aplica la configuración temporalmente y espera confirmación. Si pierdes conexión, revierte los cambios automáticamente tras unos segundos).*

2.  **Aplicar la configuración:**
    ```bash
    sudo netplan apply
    ```
    *(Aplica los cambios de manera definitiva).*
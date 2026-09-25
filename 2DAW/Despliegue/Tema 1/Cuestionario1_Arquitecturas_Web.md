# Actividad de Investigación Inicial: Modelos y Servicios Web

**Módulo: Despliegue de Aplicaciones Web (2º DAW)**
**Unidad 1: Implantación de arquitecturas web y servidores**

---

## Bloque I: Fundamentos de arquitecturas y pilas de software

**1. Servidor Web frente a Servidor de Aplicaciones**

Un servidor web como **Apache HTTPD** atiende peticiones HTTP y su función nativa es servir contenido, principalmente ficheros estáticos (HTML, CSS, JS, imágenes) directamente desde disco, de forma muy eficiente y con poco consumo de recursos por petición. Un servidor/contenedor de aplicaciones como **Apache Tomcat** es un contenedor de servlets: gestiona el ciclo de vida de componentes Java (Servlets, JSP compiladas por el motor Jasper), procesa de forma nativa peticiones que requieren lógica de negocio, acceso a bases de datos o mantenimiento de estado de sesión (`HttpSession`).

No es recomendable delegar la entrega de ficheros estáticos de alto tráfico en el motor de aplicaciones porque cada petición a Tomcat consume un hilo de su pool de conexiones y recursos de la JVM (memoria, presión sobre el recolector de basura) que deberían reservarse para procesar lógica de negocio real. Apache, en cambio, está optimizado a bajo nivel para esa tarea (uso de `sendfile`, cachés de contenido con `mod_cache`, compresión con `mod_deflate`). Por eso el patrón habitual es colocar Apache como frontal: sirve él mismo lo estático y reenvía solo las peticiones dinámicas a Tomcat.

**2. Renderizado y ejecución en ausencia de servidor HTTP**

Sí es viable visualizar una página abriendo un fichero con el esquema `file://` en el navegador: este simplemente lee el archivo del disco local y construye el DOM/CSSOM, sin que medie ninguna petición HTTP. El problema aparece con la parte dinámica: bajo `file://` cada recurso se trata como un origen opaco (`origin: null`), por lo que las políticas **CORS** bloquean cualquier `fetch`/`XMLHttpRequest` hacia otro fichero o servidor remoto, al no poder validarse cabeceras `Access-Control-Allow-Origin` sobre un origen local. Los **módulos ECMAScript** (`<script type="module">`) tampoco funcionan de forma fiable bajo `file://` en navegadores como Chrome, porque la carga de módulos usa internamente `fetch`, que queda bloqueado por el mismo motivo. Por eso cualquier aplicación que use peticiones asíncronas o módulos ES necesita un servidor HTTP real (aunque sea local, tipo `python -m http.server`) para funcionar correctamente.

**3. Transición y obsolescencia tecnológica**

- **CGI (Perl/C)**: cada petición lanza un proceso nuevo del sistema operativo (`fork`+`exec`) para ejecutar el script, lo que supone un coste de recursos altísimo por petición y escala muy mal bajo carga; además, si la entrada del usuario no se sanea, es un vector clásico de inyección de comandos.
- **Applets de Java**: requerían un plugin de JVM embebido en el navegador (arquitectura NPAPI). Todos los navegadores principales eliminaron el soporte de plugins NPAPI hacia 2015-2017 por motivos de seguridad (numerosas vulnerabilidades de escape de sandbox), y Oracle los declaró obsoletos en JDK 9, eliminándolos en JDK 11.
- **ActiveX/VBScript**: tecnología propietaria de Microsoft, exclusiva de Internet Explorer, basada en componentes COM con acceso prácticamente sin restricciones a las APIs de Windows — lo que la convirtió en un vector habitual de malware. Quedó definitivamente abandonada al no tener soporte multiplataforma ni continuidad en Edge (basado en Chromium).

Hoy esas funciones las cubren estándares abiertos: en el servidor, intérpretes embebidos o pasarelas más eficientes que CGI (FastCGI, `mod_php`, runtimes como Node.js) que evitan el coste de lanzar un proceso por petición; en el cliente, JavaScript/ECMAScript asume toda la interactividad (peticiones asíncronas con `fetch`, gráficos con Canvas/WebGL, rendimiento casi nativo con WebAssembly), sin necesidad de ningún plugin.

**4. Comparativa de arquitecturas base (LAMP vs. WISA) y su virtualización**

**LAMP** (Linux, Apache, MySQL/MariaDB, PHP/Perl/Python) es una pila enteramente de software libre, sin coste de licencia en ninguna de sus capas. **WISA** (Windows, IIS, SQL Server, ASP.NET) está basada en tecnología propietaria de Microsoft, con licenciamiento por servidor y/o por CAL (Client Access License), integrada en el ecosistema .NET.

Aprovisionar estas pilas manualmente sobre el sistema operativo anfitrión genera problemas de mantenimiento conocidos: deriva de versiones entre entornos de desarrollo y producción (el clásico "en mi máquina funciona"), conflictos cuando varias aplicaciones del mismo servidor necesitan versiones distintas de PHP/Python, y procesos de reinstalación lentos y poco reproducibles ante un cambio de hardware o un desastre.

**Docker** resuelve esto empaquetando cada pila como una imagen inmutable con todas sus dependencias fijadas en un `Dockerfile`: el entorno es reproducible byte a byte, varias pilas (incluso con versiones distintas) conviven en el mismo host sin pisarse porque cada contenedor está aislado, y el despliegue/escalado se vuelve prácticamente inmediato con `docker-compose` o un orquestador como Kubernetes. La diferencia LAMP/WISA se traslada entonces a la elección de imagen base, aunque WISA en contenedores requiere host con contenedores Windows, mientras que LAMP se apoya en el ecosistema de contenedores Linux, mucho más extendido.

**5. Alojamientos múltiples y Virtual Hosts**

El fichero por defecto `/etc/apache2/sites-enabled/000-default` define el `VirtualHost` que Apache usa si ninguna otra regla coincide. Las directivas clave son:

- **`Listen`** (definida en `/etc/apache2/ports.conf`): fija el puerto (y opcionalmente la IP) en el que Apache escucha, típicamente `Listen 80`.
- **`DocumentRoot`**: indica en qué carpeta del disco están los ficheros de ese sitio concreto (por defecto `/var/www`).
- **`ServerName`**: establece el nombre de dominio que Apache debe asociar a ese bloque `VirtualHost`.

El mecanismo que permite servir varios sitios independientes desde una única IP pública y un único puerto (80/443) se llama **alojamiento virtual basado en nombre**: toda petición HTTP/1.1 incluye obligatoriamente la cabecera `Host`, con el dominio exacto que el navegador solicitó. Apache compara esa cabecera contra el `ServerName`/`ServerAlias` de cada `VirtualHost` configurado y enruta internamente la petición al bloque que coincide, sirviendo el `DocumentRoot` correspondiente — sin necesitar una IP dedicada por sitio, a diferencia del antiguo alojamiento virtual basado en IP.

---

## Bloque II: Escalabilidad, balanceo e integración de servicios

**6. Dimensionamiento: Escalabilidad Vertical vs. Horizontal**

| | **Escalado vertical (`scale-up`)** | **Escalado horizontal (`scale-out`)** |
|---|---|---|
| **Qué hace** | Añade más CPU/RAM/disco a la misma máquina | Añade más nodos iguales a una granja de servidores |
| **Ventajas** | Sencillo: no exige rediseñar la aplicación ni gestionar estado distribuido | Escalabilidad prácticamente ilimitada; tolerancia real a fallos; permite ampliar/reducir en caliente |
| **Limitaciones técnicas** | Límite físico del hardware de un único servidor; el upgrade suele exigir parada del servicio | Añade complejidad: hay que resolver balanceo de carga y persistencia/sincronización de sesión entre nodos |
| **Costes** | Crece de forma no lineal — el hardware de gama muy alta es desproporcionadamente caro | Escala de forma más lineal con hardware estándar, sumando el coste del balanceador |
| **Punto único de fallo (SPOF)** | Sí: sigue siendo un único nodo; si cae, cae todo el servicio | No en los nodos de aplicación (son redundantes), pero el propio balanceador puede convertirse en un nuevo SPOF si no está también redundado |

**7. Problemática de la persistencia de sesiones en entornos distribuidos**

El enfoque tradicional de **sticky sessions** hace que el balanceador dirija siempre al mismo usuario al mismo servidor donde se creó su sesión, identificándolo por IP de origen o por una cookie de afinidad. Es sencillo de implementar, pero si ese nodo cae se pierden todas las sesiones que dependían de él, y puede repartir la carga de forma desigual. La **replicación en clúster** mejora la tolerancia a fallos: cada nodo replica el objeto `HttpSession` al resto, de modo que cualquier nodo puede atender cualquier petición; a cambio, introduce coste de red y CPU por la sincronización constante, que se agrava con sesiones grandes o clústeres muy numerosos.

El enfoque moderno de aplicaciones **sin estado ("stateless")** elimina el problema de raíz: el servidor no guarda nada de la sesión en su propia memoria. En su lugar, o bien se apoya en un almacenamiento externo rápido y compartido (**Redis/Memcached**) accesible por igual desde cualquier nodo, o bien usa **tokens firmados (JWT)** que viajan con cada petición y contienen ya la información de sesión, de forma que el servidor solo necesita verificar la firma, sin guardar estado. Esto permite escalar horizontalmente sin afinidad ni replicación entre nodos.

**8. Algoritmos de reparto de carga**

- **Round Robin**: reparte las peticiones de forma cíclica y secuencial entre todos los servidores del pool (1→2→3→1...), sin tener en cuenta su carga real. Es simple, pero puede ser injusto si los nodos no son homogéneos o si algunas peticiones son mucho más costosas que otras.
- **LRU (Least Recently Used)**: envía la siguiente petición al servidor que lleva más tiempo sin recibir ninguna, es decir, al "usado menos recientemente" — un criterio basado en el historial real de uso en lugar de un orden fijo predeterminado.
- **Least Connections**: dirige la petición al servidor que tiene abiertas menos conexiones activas en ese momento. Es más inteligente que Round Robin porque tiene en cuenta la carga real instantánea, y resulta especialmente útil cuando las peticiones tienen duraciones muy dispares.
- **Balanceo ponderado (Weighted Round Robin/Connections)**: a cada nodo se le asigna un peso según su capacidad real (CPU, RAM), de modo que el balanceador le envía más peticiones proporcionalmente cuanto mayor sea su peso — imprescindible en granjas con hardware heterogéneo, donde un reparto puramente igualitario infrautilizaría los nodos más potentes o saturaría los más débiles.

**9. Patrón Proxy Inverso y protocolo AJP**

Colocar Apache como proxy inverso delante de Tomcat, en lugar de exponer directamente su puerto 8080, aporta tres ventajas:

- **Seguridad**: Tomcat no necesita estar accesible desde redes públicas, solo desde el propio servidor o la red interna donde vive Apache. Así se reduce la superficie de ataque: cualquier vulnerabilidad del conector HTTP nativo de Tomcat deja de ser explotable directamente desde Internet.
- **Descarga de SSL/TLS**: el cifrado se gestiona en Apache (`mod_ssl`), liberando a la JVM de ese coste de CPU; además, al no cifrarse la comunicación interna Apache↔Tomcat (que ocurre en localhost o red de confianza), la gestión de certificados se centraliza en un único punto.
- **Administración de puertos**: solo hace falta abrir al exterior 80/443 en Apache; los puertos 8009 (AJP) y 8080 (HTTP de Tomcat) permanecen cerrados a Internet. El protocolo **AJP** es, además, un protocolo binario diseñado específicamente para esta comunicación interna, que usa conexiones TCP persistentes — mucho más eficiente que abrir una conexión HTTP nueva por cada petición reenviada.

La configuración típica en `/etc/apache2/sites-enabled/000-default` define el clúster y el reenvío:

```
<Proxy balancer://tomcat_cluster>
    BalancerMember ajp://localhost:8009
</Proxy>
ProxyPass / balancer://tomcat_cluster/
ProxyPassReverse / balancer://tomcat_cluster/
```

**10. Evolución en la administración de servicios en entornos GNU/Linux**

El material de referencia (basado en Debian 6 Squeeze) usa scripts **SysV** en `/etc/init.d/apache2`, invocados directamente o a través de `apachectl` (`start`/`restart`/`stop`/`configtest`). Las distribuciones Linux actuales (Debian ≥ 8 "Jessie", Ubuntu ≥ 15.04, RHEL/CentOS ≥ 7) sustituyeron SysV init (y Upstart) por **systemd** como sistema de inicio y gestor de servicios.

La sintaxis exacta con `systemctl` para el servicio Apache (unidad `apache2.service` en Debian/Ubuntu; `httpd.service` en RHEL/CentOS) es:

```bash
sudo systemctl start apache2      # iniciar
sudo systemctl restart apache2    # reiniciar
sudo systemctl reload apache2     # recargar configuración sin cortar conexiones activas
sudo systemctl stop apache2       # detener
sudo systemctl status apache2     # comprobar estado detallado (PID, activo/inactivo, últimas líneas de log)
sudo systemctl enable apache2     # habilitar arranque automático en el boot
```

---

## Fuentes consultadas

- Material de la unidad: *Implantación de arquitecturas web* (IES Kursaal, módulo Despliegue de Aplicaciones Web).
- [Apache HTTP Server Documentation](https://httpd.apache.org/docs/) — `mod_proxy`, `mod_proxy_ajp`, Virtual Hosts.
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/) — conector AJP, arquitectura de contenedores de servlets.
- [MDN Web Docs](https://developer.mozilla.org/) — CORS, esquema `file://`, módulos ECMAScript.
- [systemd — freedesktop.org](https://www.freedesktop.org/wiki/Software/systemd/) — `systemctl` y unidades de servicio.
- [Docker Documentation](https://docs.docker.com/) — contenedores y reproducibilidad de entornos.

# Prueba de Evaluación / Trabajo Individual: Arquitecturas y Servidores Web

**Módulo: Implantación de Aplicaciones Web (2º DAW)**
**Unidad: Arquitecturas y Servidores Web**

---

## Bloque I · Conceptos fundamentales de arquitectura web

**1. Tres elementos fundamentales del esquema de funcionamiento de los servicios web**

- **Proveedor del servicio web**: quien lo diseña, desarrolla e implementa, poniéndolo disponible para su uso (dentro de la propia organización o en público).
- **Consumidor del servicio**: quien accede al componente para utilizar los servicios que presta.
- **Agente del servicio**: actúa como enlace entre proveedor y consumidor para la publicación, búsqueda y localización del servicio (por ejemplo, mediante un registro UDDI).

**2. Las tres capas de un modelo de arquitectura web y software representativo**

- **Capa de base de datos**: almacena toda la información que gestiona el servicio web. Software asociado: **MySQL**, PostgreSQL.
- **Capa de servidores de aplicaciones web**: ejecuta la lógica de la aplicación. Software asociado: **Apache**, Tomcat, Resin.
- **Capa de clientes del servicio web**: es por donde el usuario accede al servicio. Software asociado: un navegador como **Firefox**, Internet Explorer u Opera.

**3. Diferencia técnica entre PHP/ASP y lenguajes de script como JavaScript**

PHP y ASP son tecnologías de **ejecución en servidor**: el código corre en la máquina servidora, genera el HTML de respuesta y su ciclo de vida termina en el momento en que esa respuesta se envía al navegador — el cliente nunca ve el código fuente, solo el resultado ya procesado. JavaScript, en cambio, se interpreta y ejecuta **en el cliente** (el navegador): llega como código fuente dentro de la página y permanece "vivo" después de la carga, pudiendo seguir reaccionando a eventos del usuario (mover imágenes, validar formularios, modificar el DOM) sin necesidad de volver a contactar con el servidor para cada interacción.

**4. Diferencias entre el «Modelo 1» y el «Modelo 2» de aplicaciones web**

- **Modelo 1**: las responsabilidades de presentación, negocio y acceso a datos se confunden en un mismo script (el caso extremo es el modelo CGI, donde un único proceso en Perl/C genera directamente el HTML de salida). No existe separación real entre lo que se muestra y la lógica que lo calcula.
- **Modelo 2**: incorpora el patrón **MVC** (Modelo-Vista-Controlador). Aparece un elemento controlador (un Servlet) que gestiona la navegación de la aplicación; el modelo de negocio queda encapsulado en JavaBeans, que se incrustan en páginas JSP cuya única responsabilidad pasa a ser la presentación. Existe también un paso intermedio, el **Modelo 1.5** (JSP + beans sin controlador central), que ya separa negocio/acceso a datos de la presentación pero todavía no tiene el elemento controlador que caracteriza al Modelo 2.

**5. Componentes de las pilas LAMP y WISA**

| Componente | LAMP | WISA |
|---|---|---|
| Sistema operativo | **L**inux | **W**indows |
| Servidor web | **A**pache | **I**nternet Information Services (IIS) |
| Gestor de base de datos | **M**ySQL | **S**QL Server |
| Lenguaje de backend | **P**HP (a veces Perl o Python) | **A**SP / ASP.NET |

**6. Balanceador de carga hardware tradicional frente a balanceador hardware HTTP**

El **balanceador hardware tradicional** responde únicamente a algoritmos de reparto de carga (Round Robin, LRU, etc.) y redirecciona la petición basándose en conmutación de circuitos, **sin examinar ni interpretar el paquete HTTP**. Es muy rápido, pero al no "mirar dentro" del paquete no puede garantizar que las peticiones de una misma sesión de usuario vayan siempre a la misma máquina — esa información hay que gestionarla aparte (cookies, base de datos). El **balanceador hardware HTTP** sí examina el paquete HTTP y mantiene la relación usuario-máquina servidora, de modo que una misma sesión se sirve siempre desde el mismo nodo; es algo más lento que el tradicional por esa inspección adicional, pero sigue siendo mucho más rápido que un balanceador software, lo que lo convierte en una de las soluciones más aceptadas actualmente.

---

## Bloque I (continuación) · Configuración y servidores de aplicaciones

**7. Las tres categorías de módulos del servidor web Apache**

- **Módulos base**: se encargan de las funciones básicas del servidor.
- **Módulos multiproceso (MPM)**: gestionan la unión de los puertos de la máquina, aceptando y atendiendo las peticiones entrantes.
- **Módulos adicionales**: añaden funcionalidad extra al servidor (compresión, caché, proxy, etc.), activable o desactivable al arrancar.

**8. Por qué el proceso inicial de Apache requiere permisos de root**

En sistemas Unix/Linux, abrir un socket de escucha en un puerto por debajo del 1024 — como el **puerto 80** del HTTP estándar — es una operación privilegiada, reservada al superusuario. Por eso el proceso maestro de `httpd` arranca como **root**: es el único que puede hacer el `bind` sobre ese puerto. Una vez completadas las tareas preliminares (como abrir los ficheros de log), ese proceso lanza **procesos hijo**, que son los que realmente escuchan y atienden las peticiones de los clientes, y lo hacen con **privilegios de usuario mucho menores** (un usuario del sistema dedicado, sin permisos administrativos). Así, si un proceso hijo se ve comprometido por una vulnerabilidad, el atacante no hereda privilegios de root.

**9. Los tres modos de despliegue de un contenedor de Servlets**

- **Stand-alone (independiente)**: el contenedor de servlets es parte integral del propio servidor web, que está basado en Java (p. ej. JavaWebServer, sustituido actualmente por iPlanet). Es el modo en que trabaja Tomcat por defecto, aunque la mayoría de servidores web no están basados en Java.
- **Dentro-de-proceso**: el plugin del servidor web abre una JVM dentro de su propio espacio de direcciones y le pasa el control de la petición usando **JNI**. Da buen rendimiento en servidores multi-thread de un solo proceso, pero está limitado en escalabilidad.
- **Fuera-de-proceso**: el plugin del servidor web y la JVM del contenedor Java se comunican mediante un mecanismo IPC (normalmente sockets TCP/IP), ejecutándose en procesos separados. El tiempo de respuesta es algo peor que en el modo anterior, pero mejora la escalabilidad y la estabilidad.

**10. Protocolo de comunicación interna entre Apache y Tomcat**

El protocolo empleado es **AJP** (Apache JServ Protocol), sobre el puerto habitual **8009** (frente al 8080, que es el puerto HTTP nativo de Tomcat). Frente a reenviar las peticiones por HTTP simple, AJP es un **protocolo binario diseñado específicamente para esta comunicación interna**, que usa **conexiones TCP persistentes** en lugar de abrir una conexión nueva por cada petición reenviada — lo que lo hace notablemente más eficiente para el tráfico constante entre el proxy (Apache) y el contenedor de aplicaciones (Tomcat).

---

## Bloque II · Cuestiones teóricas de profundización e investigación

**11. Flujo de control de un Servlet Controlador en una arquitectura MVC web**

1. El navegador envía una petición HTTP, que llega siempre en primer lugar al **Servlet Controlador** (único punto de entrada de la aplicación).
2. El controlador interpreta la petición (parámetros, ruta solicitada) y decide qué operación de negocio hay que ejecutar.
3. Delega esa lógica en el **Modelo** (un JavaBean), que accede a los datos necesarios (base de datos u otro origen) y devuelve el resultado.
4. El controlador recoge ese resultado, lo coloca como atributo accesible (por ejemplo, en el `request` o la `session`), y decide a qué **Vista (JSP)** reenviar la petición según la lógica de navegación de la aplicación.
5. La JSP seleccionada se limita a **renderizar** esos datos como HTML, sin contener lógica de negocio.
6. El contenedor de servlets envía esa respuesta HTML final de vuelta al navegador.

**12. Round Robin frente a LRU en servidores balanceados**

**Round Robin** reparte las peticiones de forma cíclica y fija entre todos los servidores del pool (1→2→3→1...), sin tener en cuenta su estado real en cada momento. **LRU (Least Recently Used)** en cambio envía la siguiente petición al servidor que lleva **más tiempo sin recibir ninguna**, es decir, se basa en el historial real de uso en lugar de en un orden predeterminado.

**Problemática de LRU con peticiones concurrentes**: si llegan varias peticiones casi al mismo tiempo, el balanceador puede identificar a **el mismo servidor** como "el que lleva más tiempo sin atender nada" para varias de ellas a la vez, antes de que su estado se actualice tras la primera asignación — lo que provoca que ese nodo reciba una ráfaga de peticiones de golpe mientras otros quedan ociosos. Es, en esencia, una condición de carrera sobre el dato de "última vez usado" si no está correctamente sincronizado entre asignaciones.

**13. El papel de Java Native Interface (JNI)**

JNI es el mecanismo que permite que código Java y código nativo (C/C++) convivan e invocarse mutuamente **dentro del mismo espacio de direcciones de un mismo proceso**. Es justo lo que emplea el modo de despliegue "dentro-de-proceso" de un contenedor de servlets: el plugin nativo del servidor web abre una JVM embebida en su propio proceso y usa JNI para pasarle el control cuando una petición debe ejecutar un servlet.

**Impacto en la estabilidad**: al compartir proceso y espacio de direcciones, un fallo grave en el lado nativo o en la JVM (un *crash*, una fuga de memoria, un puntero corrupto) puede tirar abajo **todo el proceso del servidor web**, no solo el componente Java afectado. Es la contrapartida del buen rendimiento que ofrece este modo frente al modo "fuera-de-proceso", que al aislar la JVM en un proceso independiente resulta más estable (aunque algo más lento, al depender de IPC).

**14. Replicación de sesión en un clúster y su efecto en la escalabilidad**

Cada nodo del clúster **replica el objeto de sesión** (`HttpSession` en Java) al resto de nodos, de modo que, independientemente de qué máquina atienda finalmente una petición del usuario, siempre tenga acceso a los datos de su sesión — eliminando la necesidad de *sticky sessions* (afinidad fija a un único servidor).

**Por qué es un cuello de botella**: cada cambio en la sesión de cualquier usuario debe propagarse por red a todos los demás nodos del clúster, generando tráfico y consumo de CPU proporcional tanto al **número de nodos** como al **tamaño y frecuencia** de esos cambios. Cuantos más nodos y más sesiones concurrentes gestione el clúster, mayor es este coste de sincronización — hasta el punto de que puede llegar a anular la ganancia de rendimiento que se buscaba al escalar horizontalmente. Es precisamente el motivo por el que las arquitecturas modernas tienden hacia servidores **sin estado ("stateless")**, apoyados en almacenamiento externo compartido (Redis/Memcached) o en tokens firmados (JWT), evitando así tener que replicar nada entre nodos.

---

## Fuentes consultadas

- Material de la unidad: *Implantación de arquitecturas web* (módulo Implantación de Aplicaciones Web), mismo documento base usado en el Cuestionario 1.
- [Apache HTTP Server Documentation](https://httpd.apache.org/docs/) — arquitectura de módulos, `mod_proxy_ajp`.
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/) — modos de contenedor de servlets, conector AJP.
- [Oracle — Java Native Interface Specification](https://docs.oracle.com/javase/8/docs/technotes/guides/jni/) — JNI.

I.E.S. Kursaal
Optativa - Wordpress
C.F.G.S. DESARROLLO DE APLICACIONES WEB
Daniel Ocaña Aguilera

I.E.S. Kursaal
Unidad 2:
El panel de administración

2.1.-¿Qué vamos a aprender?
• Al finalizar esta unidad, el alumno será capaz de:
• Navegación y configuración:
• Identificar y utilizar todas las secciones del escritorio
• Configurar ajustes generales, escritura y lectura
• Personalizar el espacio de trabajo según necesidades
• Gestión de usuarios:
• Crear y gestionar usuarios con diferentes roles
• Comprender el sistema de permisos por roles
• Implementar políticas de seguridad mediante usuarios
• Flujos de trabajo:
• Establecer procesos de publicación colaborativa
• Configurar el sitio para diferentes tipos de contenido
3

2.2.-Estructura del Panel de
Administración
Acceso al panel:
• URL: tudominio.com/wp-admin
• Pantalla de login con usuario y contraseña
• Recordatorio de contraseña disponible
Secciones principales del menú lateral:
|  ESCRITORIO |  MEDIOS          |  APARIENCIA      |  AJUSTES      |
| ----------- | ---------------- | ---------------- | ------------- |
|   - Inicio  |   - Biblioteca   |   - Temas        |   - Generales |
|             |   - Añadir nuevo |   - Personalizar |   - Escritura |
  - Actualizaciones
|  ENTRADAS              |  PÁGINAS              |   - Widgets    |   - Lectura     |
| ---------------------- | --------------------- | -------------- | --------------- |
|   - Todas las entradas |   - Todas las páginas |   - Menús      |   - Comentarios |
|                        |   - Añadir nueva      |        PLUGINS |   - Medios      |
  - Añadir nueva
  - Categorías  COMENTARIOS  USUARIOS   - Enlaces permanentes
  - Etiquetas
Video extra: https://www.youtube.com/watch?v=15Cz-V0dbr0 4

2.3.-El Escritorio - Centro de Control Principal
Widgets del escritorio por defecto:
1. Bienvenida 3. Borrador rápido
- Accesos rápidos a - Creación rápida de
funciones comunes borradores sin salir del
- Enlaces para escritorio
personalizar el sitio - Ideal para capturar
- Estado de la actividad ideas rápidamente
reciente
4. Noticias y eventos
2. Actividad reciente de WordPress
- Últimas entradas - Novedades de la
publicadas comunidad
- Comentarios recientes - Eventos y meetups
pendientes de cercanos
moderación
- Enlaces rápidos de
moderación
5

2.4.-Personalización del Escritorio
Opciones de pantalla (esquina  Ajustes de usuario (Tu perfil →  Accesos directos de teclado:
| superior derecha): | Opciones de pantalla): |     |
| ------------------ | ---------------------- | --- |
• Shift + Alt + C: Añadir nuevo
• Mostrar/ocultar widgets  • Diseño en 1 o 2 columnas comentario
específicos
|                                  | • Color del esquema (Azul,  | • Shift + Alt + M: Añadir nuevo  |
| -------------------------------- | --------------------------- | -------------------------------- |
| • Organizar widgets por arrastre | Claro, Oscuro, etc.)        | medio                            |
• Configurar número de  • Habilitar/deshabilitar barra de  • Shift + Alt + P: Añadir nueva
| columnas | herramientas | página |
| -------- | ------------ | ------ |
6

2.5.-Ajustes Generales - Configuración
Esencial
Ajustes → Generales - Campos críticos:
1. Información básica del sitio:
• Título del sitio: Aparece en pestañas del navegador y SEO
• Descripción breve: Meta description por defecto
2. Configuración de URLs:
• Dirección de WordPress: Ruta de instalación
• Dirección del sitio: URL pública (pueden ser diferentes)
3. Configuración regional:
• Zona horaria: Crítica para programación de contenidos
• Formato de fecha/hora: Coherencia con audiencia local
4. Opciones de membresía:
• Cualquiera puede registrarse: Habilitar/deshabilitar
• Rol de usuario por defecto: Suscriptor o Colaborador
7
Video extra: https://www.youtube.com/watch?v=YuBX9yMVtWY

2.6.-Ajustes de Escritura - Configuración
de Contenido
Ajustes → Escritura - Opciones de publicación:
1. Configuración de la página principal:
• Página de entradas recientes (blog clásico)
• Página estática frontal (sitio corporativo)
2. Opciones de correo electrónico:
• Publicar mediante correo electrónico (raro uso actual)
• Servidor y puerto para posting remoto
3. Servicios de actualización:
• Pingbacks a servicios de notificación
• Lista de servicios a notificar al publicar
• http://rpc.pingomatic.com/ (servicio por defecto)
Video extra: https://www.youtube.com/watch?v=4A_OxmARbrs
8

2.7.-Ajustes de Lectura –
Comportamiento Frontend
Ajustes → Lectura - Configuración de visualización:
1. Configuración de la página frontal:
• Tu última entradas: Comportamiento blog tradicional
• Una página estática:
• Página frontal: Homepage estática
• Página de entradas: Blog/Noticias
2. Configuración del feed:
• Número de entradas en feed RSS
• Mostrar: Texto completo o resumen
3. Visibilidad en motores de búsqueda:
• ✓ Discourage search engines from indexing this site
• IMPORTANTE: Desmarcar en sitios en producción
Video extra: https://www.youtube.com/watch?v=jXZcXELKRWM 9

2.8.-Sistema de Usuarios en WordPress
Conceptos fundamentales:
¿Qué es un usuario?
• Persona registrada con capacidad de acceso al sitio
• Puede tener diferentes niveles de permisos
• Identificado por nombre de usuario y email único
Campos de perfil de usuario:
• Información personal (nombre, apellidos, web)
• Información de cuenta (usuario, email, contraseña)
• Preferencias de visualización
• Información de contacto

2.9.- Roles de Usuario - Jerarquía de Permisos
❖ Los 6 roles por defecto en WordPress:
1. Super Administrador (Solo en Multisite)
• Control total sobre toda la red de sitios
2. Administrador
• Acceso completo a todas las funciones administrativas
• Puede gestionar usuarios, plugins, temas y
configuraciones
3. Editor
• Gestionar todo el contenido (entradas, páginas,
comentarios)
• Sin acceso a configuraciones del sitio o usuarios
4. Autor
• Publicar y gestionar sus propias entradas
• Subir archivos multimedia
5. Colaborador
• Escribir y gestionar sus entradas, pero no publicar
• Necesita aprobación de Editor/Administrador
6. Suscriptor
• Solo gestionar su perfil y comentar (si está permitido)
11

2.10.-Permisos Detallados por Rol
12

2.11.-Gestión de Usuarios en el Panel
Usuarios → Todos los usuarios: Proceso de creación de usuario: Opciones adicionales:
• Lista completa de usuarios registrados 1.Usuarios → Añadir nuevo • Enviar notificación al usuario
• Filtros por rol y búsqueda 2.Información obligatoria: • Establecer sitio web y biografía
• Edición rápida y eliminación • Nombre de usuario (único, no • Configurar nombre para mostrar
cambiable)
• Email (único, para notificaciones)
• Controleña (generar segura)
• Rol (seleccionar apropiado)
13

2.12.-Perfil de Usuario - Personalización
Individual
• Usuarios → Tu perfil - Configuraciones personales:
• 1. Información personal:
• Nombre, apellidos, nombre público
• Información de contacto y sitio web
• Biografía (aparece en autoría)
• 2. Preferencias de cuenta:
• Generar nueva contraseña
• Configuración de visualización del escritorio
• 3. Preferencias de contenido:
• Editor visual o HTML
• Sintaxis de resaltado para código
• Esquema de color del administrador
14

2.13.-Seguridad en la Gestión de
Usuarios
• Buenas prácticas esenciales:
1. Política de nombres de usuario:
• Nunca usar "admin" como usuario
• Usar combinación nombre.apellido
• Evitar información personal obvia
2. Gestión de contraseñas:
• Mínimo 12 caracteres
• Combinación de mayúsculas, minúsculas, números y símbolos
• Cambio periódico cada 3-6 meses
3. Principio del menor privilegio:
• Asignar solo los permisos necesarios
• Revisar periódicamente los roles asignados
• Eliminar usuarios inactivos
15

2.14-Casos de Uso de Roles
Caso 1: Blog personal
• 1 Administrador (dueño del blog)
• Varios Colaboradores (invitados esporádicos)
• Suscriptores (lectores que comentan)
Caso 2: Sitio corporativo pequeño
• 1 Administrador (responsable TI)
• 1 Editor (responsable de comunicación)
• Varios Autores (departamentos)
Caso 3: Medio de comunicación online
• 1-2 Administradores
• Varios Editores (por secciones)
• Múltiples Autores (redactores)
• Colaboradores (freelancers)
16

2.15.-Resumen Gestión de Usuarios
Video extra: https://www.youtube.com/watch?v=VDvi9hDJLEA 17

2.16.-Resolución de Problemas Comunes
Problema 1: Usuario no puede acceder
• Verificar que el email de activación fue recibido
• Comprobar que la cuenta está activa (no eliminada)
• Revisar posibles bloqueos por seguridad
Problema 2: Permisos incorrectos
• Verificar que el rol asignado es el correcto
• Comprobar plugins de gestión de usuarios
• Revisar posibles conflictos con temas
Problema 3: Usuario ve opciones que no debería
• Limpiar caché del navegador
• Verificar que no tiene múltiples roles
• Comprobar configuraciones de plugins de seguridad
18

2.17.-Mejores Prácticas para Gestión de Usuarios
Políticas recomendadas para proyectos reales:
1. Documentación de roles:
| •   | Mantener matriz de permisos actualizada |     |
| --- | --------------------------------------- | --- |
| •   | Documentar responsabilidades por rol    |     |
| •   | Establecer procedimientos de escalado   |     |
2. Revisiones periódicas:
| •   | Auditoría trimestral de usuarios activos |     |
| --- | ---------------------------------------- | --- |
•
Revisión de permisos asignados
| •   | Eliminación de cuentas inactivas |     |
| --- | -------------------------------- | --- |
3. Seguridad proactiva:
| •   | Implementar autenticación de dos factores  |     |
| --- | ------------------------------------------ | --- |
| •   | Monitorizar intentos de acceso fallidos    |     |
| •   | Mantener registro de actividad de usuarios | 19  |

2.18.-Resumen de la unidad
Hemos aprendido a:
- Navegar eficientemente por el panel de administración
- Configurar ajustes esenciales para el comportamiento del sitio
- Gestionar usuarios con diferentes roles y permisos
- Implementar flujos de trabajo colaborativos realistas
- Aplicar mejores prácticas de seguridad en gestión de usuarios
Conceptos clave dominados:
- Jerarquía de roles de WordPress
- Configuración de lectura/escritura
- Personalización del escritorio
- Políticas de seguridad de usuarios
20

Práctica UD2: Configuración del Panel de Administración y Gestión de Usuarios en
WordPress
Contexto
Partimos de la instalación de WordPress realizada en la UD1 sobre el hosting gratuito CDMON.
En esta práctica vas a configurar el sitio, personalizar el escritorio y diseñar un flujo de trabajo
colaborativo con distintos roles de usuario, aplicando las buenas prácticas de seguridad vistas
en la unidad.
Importante: en esta unidad no se desarrolla el contenido real de la web. Solo crearás el
contenido mínimo imprescindible (una entrada y unas páginas genéricas) para poder probar
los usuarios y el flujo de trabajo. Ese contenido será sustituido o ampliado en las siguientes
unidades, donde desarrollarás tu proyecto web completo.
Objetivos
• Navegar y personalizar el panel de administración.
• Configurar los ajustes generales, de escritura y de lectura del sitio.
• Crear y gestionar usuarios con distintos roles, aplicando el principio de menor
privilegio.
• Diseñar un flujo de trabajo editorial realista.
• Documentar las decisiones tomadas mediante capturas de pantalla y una breve
explicación.
Enunciado
Cada alumno/a elegirá libremente la temática de su sitio web (blog personal, revista,
portfolio, sitio corporativo, etc.). A partir de esa temática, deberás dejar el sitio configurado y
preparado para un equipo de trabajo ficticio.
Realiza las siguientes tareas sobre tu instalación de WordPress en CDMON:
Tarea 1. Configuración general del sitio (Ajustes → Generales)
1. Establece el título del sitio y una descripción corta coherentes con la temática elegida
(máx. 150 caracteres, actúa como meta description).
2. Configura la zona horaria de Madrid y el formato de fecha y hora habituales en
España.
3. Define el idioma del sitio en español.
4. Deshabilita el registro libre de usuarios ("Cualquiera puede registrarse") y explica
brevemente por qué lo haces.
5. Indica brevemente qué diferencia hay entre Dirección de WordPress (URL) y Dirección
del sitio (URL) y cuándo podrían ser distintas.

Tarea 2. Configuración de escritura y lectura
Ajustes → Escritura:
1. Establece una categoría predeterminada para las entradas coherente con tu temática
(por ejemplo, General, Noticias, Artículos...).
2. Deja el resto de opciones por defecto y explica brevemente para qué sirve el servicio
de actualización rpc.pingomatic.com.
Ajustes → Lectura:
1. Configura una página estática como portada y otra página como página de entradas
(blog). Crea previamente las páginas necesarias (con contenido genérico que
modificarás en unidades posteriores):
o Página "Inicio" (con un texto de bienvenida breve).
o Página "Blog" (vacía, solo para alojar las entradas).
o Página "Quiénes somos" (o equivalente según tu temática).
o Página "Contacto".
2. Limita el número de entradas mostradas en el blog a 5 y en el feed RSS a 10,
mostrando solo el resumen.
3. Asegúrate de que la opción "Disuadir a los motores de búsqueda de indexar este
sitio" está desmarcada (sitio en producción). Explica brevemente por qué es
importante en un entorno real.
Tarea 3. Personalización del escritorio
1. Desde Opciones de pantalla (esquina superior derecha del Escritorio), oculta al menos
un widget (por ejemplo, "Eventos y noticias de WordPress" o "Borrador rápido").
2. Cambia el esquema de color del administrador a uno distinto del predeterminado
(desde Usuarios → Tu perfil).
3. Organiza los widgets restantes en 2 columnas.
4. Anota los atajos de teclado del escritorio vistos en clase (Shift + Alt + C, M y P) y
explica qué hace cada uno.
Tarea 4. Gestión de usuarios y roles
Crea al menos 5 usuarios con roles distintos, siguiendo un patrón de nombres de usuario
coherente (por ejemplo, rol.nombre o nombre.apellido). Puedes usar emails ficticios del tipo
nombre@tudominio.local; si CDMON no lo permite, usa direcciones válidas de prueba.

|                | Nombre de usuario  | Nombre a   |                        |
| -------------- | ------------------ | ---------- | ---------------------- |
| Rol            |                    |            | Justificación del rol  |
|                | (patrón)           | mostrar    |                        |
|                | admin.tunombre (o  |            | Responsable TI /       |
| Administrador  |                    | Tu nombre  |                        |
|                | similar)           |            | dirección              |
Responsable de
| Editor  | editor.nombre  | ...  |     |
| ------- | -------------- | ---- | --- |
contenidos
| Autor  | autor.nombre  | ...  | Redactor de sección  |
| ------ | ------------- | ---- | -------------------- |
Colaborador  colaborador.nombre  ...  Colaborador esporádico
Lector registrado para
| Suscriptor  | suscriptor.nombre  | ...  |     |
| ----------- | ------------------ | ---- | --- |
comentar
Requisitos:
•  Aplica una política de nombres de usuario coherente (no uses "admin" a secas).
•  Genera contraseñas seguras (mínimo 12 caracteres, mayúsculas, minúsculas, números
y símbolos).
•  Configura el nombre público de cada usuario de forma adecuada (no el login).
•  Rellena la biografía al menos del Editor y del Autor.
Tarea 5. Verificación de permisos (experimento práctico)
1.  Inicia sesión como el usuario Colaborador y comprueba qué puede hacer: crea una
entrada de prueba y verifica que no puede publicarla (solo enviarla a revisión).
Captura de pantalla.
2.  Inicia sesión como el usuario Autor y comprueba que puede publicar sus propias
entradas. Captura de pantalla.
3.  Inicia sesión como Editor y comprueba que puede moderar comentarios y editar
entradas de otros, pero no acceder a Ajustes ni Usuarios. Captura de pantalla.
4.  Recoge una tabla resumen con lo observado y compárala con la matriz de permisos de
la página 12 del PDF.
Tarea 6. Simulación de flujo de trabajo editorial
1.  Con el usuario Colaborador, crea una entrada genérica (título acorde a tu temática) y
envíala a revisión.
2.  Con el usuario Editor, revisa la entrada, añade una categoría y una etiqueta, y
publícala.

3. Con el usuario Suscriptor, accede al sitio y comenta la entrada. (Activa previamente
los comentarios si es necesario.)
4. Con el usuario Editor, modera el comentario (apruébalo).
5. Documenta el flujo con capturas de pantalla de cada paso.
Cómo entregar la práctica:
Deberás entregar un único archivo PDF (o un ZIP si el tamaño de las capturas es elevado) que
contenga:
1. Portada con tu nombre, apellidos, curso y fecha, e indicación de la temática elegida.
Incluye tu nombre en la portada y en el nombre del archivo (formato recomendado:
UD2_Practica_ApellidoNombre.pdf)
2. Capturas de pantalla de cada tarea, organizadas por apartados (Tarea 1, Tarea 2, etc.).
3. Breve explicación (2-3 líneas por tarea) de lo realizado y de las decisiones tomadas. No
es necesario un informe técnico extenso: basta con que se entienda qué has hecho y
por qué.
4. Tabla resumen de roles y permisos observada en la Tarea 5.
5. Conclusiones personales: qué has aprendido, qué dificultades has encontrado y cómo
las has resuelto.
Las capturas deben ser legibles y mostrar claramente la información relevante (evita capturas
de pantalla completa sin recortar).

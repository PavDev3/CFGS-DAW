# Práctica 2: Configuración, Usuarios y Administración — UD2 Gestores de Contenido

**Sitio:** pablonunez.dev.mialias.net (CDMON, instalación de UD1)
**Temática elegida:** blog personal técnico sobre desarrollo web e IA (misma que en UD1: entradas reales tipo "REA: un MCP para hacer ingeniería inversa con agentes de IA", tema Astra + Elementor).

Documento en construcción, se va ampliando tarea a tarea. Capturas en `capturas/`.

## Tarea 1. Configuración general del sitio

1. **Título y descripción**: cambiados a "Pablo Núñez — Blog de desarrollo e IA" / "Blog personal sobre desarrollo web, IA y herramientas para programadores." (coherente con la temática y dentro de 150 caracteres).
2. **Zona horaria**: ya estaba en Madrid.
3. **Idioma**: ya estaba en Español.
4. **Registro libre de usuarios**: la casilla "Cualquiera puede registrarse" ya estaba desmarcada. Se deja así: en un sitio con roles definidos a mano (Tarea 4), permitir alta libre rompería el control de quién tiene cada permiso — cualquier visitante podría crearse una cuenta sin pasar por el criterio del administrador.
5. **Dirección de WordPress (URL) vs Dirección del sitio (URL)**: la primera es dónde viven los archivos núcleo de WordPress; la segunda es la URL que ve el visitante para acceder al sitio. Suelen coincidir, pero son distintas cuando WordPress está instalado en una subcarpeta (p. ej. `/wordpress/`) y se quiere que la web se vea en la raíz del dominio, o en instalaciones multisitio.

![Ajustes generales guardados](capturas/tarea1_ajustes_generales_guardado.png)

## Tarea 2. Configuración de escritura y lectura

**Ajustes → Escritura**
- Creé la categoría "Artículos" (no existía ninguna aparte de "Sin categoría") y la dejé como categoría por defecto para las entradas, coherente con un blog de desarrollo/IA.
- `rpc.pingomatic.com` es un servicio de notificación: cuando publicas una entrada nueva, WordPress le hace un "ping" automático para avisar a buscadores/agregadores de blogs de que hay contenido nuevo, para que lo indexen antes. Se deja por defecto.

![Categoría por defecto guardada](capturas/tarea2_escritura_categoria.png)

**Páginas creadas** (contenido genérico, se ampliará en unidades posteriores): Inicio, Blog (vacía), Quiénes somos, Contacto.

![Las 4 páginas creadas](capturas/tarea2_paginas_creadas.png)

**Ajustes → Lectura**
- Página de inicio: estática → "Inicio" como portada, "Blog" como página de entradas.
- Límite de entradas en archivo: 5. Límite en RSS: 10, mostrando solo el extracto (no el texto completo).
- "Pedir a los motores de búsqueda que no indexen este sitio" se dejó **desmarcada**: es importante en un entorno real porque, si estuviera marcada, Google y el resto de buscadores no indexarían el sitio y nadie lo encontraría por búsqueda orgánica — solo tiene sentido activarla en sitios de pruebas/desarrollo, nunca en producción.

![Ajustes de lectura guardados](capturas/tarea2_lectura_guardado.png)

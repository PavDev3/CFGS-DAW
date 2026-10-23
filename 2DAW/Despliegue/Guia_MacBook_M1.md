# Guía adaptada a MacBook Pro M1 (Apple Silicon) — Despliegue de aplicaciones web

Esta guía adapta los enunciados oficiales de **Actividad 1** (Apache) y **Actividad 2** (Tomcat) a comandos reales para macOS con chip Apple Silicon, usando Homebrew. Los pasos del PDF del profesor están pensados para Windows/Linux — aquí tienes el equivalente exacto para tu equipo.

> **Antes de empezar**: necesitas tener [Homebrew](https://brew.sh) instalado. Si no lo tienes:
> ```bash
> /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
> ```

---

## Actividad 1 — Instalación y verificación de Apache

**Entregable pedido:** documento explicando cómo instalaste Apache, con capturas de los pasos más importantes.

### Paso 1: Instalar Apache con Homebrew
```bash
brew install httpd
```
Homebrew compila/instala la versión nativa ARM64, no hace falta Rosetta.

### Paso 2: Arrancar el servicio
```bash
brew services start httpd
```
Esto deja Apache arrancado en segundo plano de forma permanente (se reinicia solo si reinicias el Mac). Para pararlo: `brew services stop httpd`.

> ⚠️ **Puerto por defecto**: el Apache de Homebrew escucha en el puerto **8080**, no en el 80 (para no necesitar permisos de administrador). Accederás en `http://localhost:8080`, no en `http://localhost`. Es normal en instalaciones vía Homebrew en Mac — coméntalo así en tu documento.

### Paso 3: Verificar que funciona
```bash
apachectl -v
curl -I http://localhost:8080
```
El primer comando muestra la versión instalada (equivalente al `java -version` del otro enunciado). El segundo confirma que el servidor responde.

Después, abre el navegador y entra en `http://localhost:8080` — deberías ver la página "It works!" de Apache.

📸 **Captura 1:** terminal mostrando la salida de `apachectl -v`.
📸 **Captura 2:** navegador mostrando "It works!" en `http://localhost:8080`.

### Paso 4 (opcional pero recomendable): página propia
La carpeta donde Apache sirve los archivos (DocumentRoot) en Homebrew suele ser:
```
/opt/homebrew/var/www
```
Crea ahí un `index.html` con tu nombre, recarga `http://localhost:8080` y haz una tercera captura — demuestras que entiendes qué carpeta sirve el contenido, igual que se pide en la Actividad 2 con Tomcat.
```bash
echo "<h1>Apache funcionando - Pablo Núñez</h1>" > /opt/homebrew/var/www/index.html
```

---

## Actividad 2 — Instalación y comprobación de Tomcat

**Entregable pedido:** PDF `Apellido_nombre_tarea2.pdf` con 3 capturas (ver enunciado oficial en `2DAW/Despliegue/Enunciado_Practica_Tomcat.pdf`).

> ⚠️ **Ojo con el puerto**: Tomcat también usa el 8080 por defecto, igual que el Apache de Homebrew. Si tienes `brew services start httpd` activo, detenlo antes de arrancar Tomcat para evitar el choque:
> ```bash
> brew services stop httpd
> ```

### Paso 1: Instalar y verificar Java
```bash
brew install openjdk
```
Al terminar, Homebrew muestra en terminal un comando para enlazarlo al sistema (tipo `sudo ln -sfn ...`) — cópialo y ejecútalo tal cual te lo da.

```bash
java -version
```

📸 **Captura 1:** terminal con el resultado de `java -version`.

### Paso 2: Descargar y arrancar Tomcat
Descarga el `.tar.gz` (Core) desde [tomcat.apache.org](https://tomcat.apache.org/) — es el mismo paquete para cualquier sistema operativo, Tomcat es Java puro y no depende de la arquitectura del chip.
```bash
cd ~/Downloads
tar -xzf apache-tomcat-*.tar.gz
cd apache-tomcat-*/bin
chmod +x *.sh
```
El `chmod +x` es importante: en Mac/Linux los scripts descargados no suelen tener permiso de ejecución por defecto.
```bash
./startup.sh
```
Abre el navegador en `http://localhost:8080` — deberías ver la página de bienvenida de Tomcat con el gato.

📸 **Captura 2:** navegador mostrando la página de bienvenida de Tomcat (el gato) en `http://localhost:8080`.

### Paso 3: Página de bienvenida personalizada
Dentro de la carpeta donde descomprimiste Tomcat, busca:
```
webapps/ROOT/
```
Crea ahí `inicio.html`:
```html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Práctica Tomcat</title>
</head>
<body>
    <h1>Servidor Tomcat funcionando correctamente</h1>
    <h2>Alumno: Pablo Núñez Fernández</h2>
    <p>IES Kursaal - 2º DAW</p>
</body>
</html>
```
Guarda y entra en `http://localhost:8080/inicio.html`.

📸 **Captura 3:** navegador mostrando tu página personalizada en `http://localhost:8080/inicio.html`.

### Paso 4: Parar Tomcat al terminar
```bash
./shutdown.sh
```

---

## Chuleta rápida de comandos (Mac M1)

| Acción | Comando |
|---|---|
| Instalar Apache | `brew install httpd` |
| Arrancar Apache | `brew services start httpd` |
| Parar Apache | `brew services stop httpd` |
| Versión de Apache | `apachectl -v` |
| Instalar Java | `brew install openjdk` |
| Versión de Java | `java -version` |
| Arrancar Tomcat | `./startup.sh` (dentro de `bin/`) |
| Parar Tomcat | `./shutdown.sh` |

*Guía de apoyo para adaptar el enunciado del profesor a macOS/Apple Silicon. Las capturas de pantalla las tienes que hacer tú en tu propio equipo — esta guía no sustituye la entrega, solo te dice qué comandos ejecutar.*

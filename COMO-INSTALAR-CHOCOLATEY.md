# 🍫 Cómo Instalar Chocolatey en Windows

## 🚀 Método 1: Automático (Recomendado)

### Paso 1: Descargar el script
Guarda este archivo como `install-chocolatey.bat`

### Paso 2: Ejecutar como Administrador
1. **Haz clic derecho** en `install-chocolatey.bat`
2. **Selecciona "Ejecutar como administrador"**
3. **Haz clic en "Sí"** en el control de cuentas

### Paso 3: Seguir las instrucciones
El script instalará Chocolatey automáticamente

---

## 🛠️ Método 2: Manual (PowerShell)

### Paso 1: Abrir PowerShell como Administrador
1. **Presiona `Win + X`**
2. **Selecciona "Windows PowerShell (Administrador)"** o "Terminal (Administrador)"

### Paso 2: Ejecutar el comando de instalación
Copia y pega este comando en PowerShell:

```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

### Paso 3: Esperar la instalación
- Verás mensajes de progreso
- Al finalizar, cierra y vuelve a abrir PowerShell

---

## 🌐 Método 3: Descarga Directa

### Paso 1: Visita la web oficial
Abre: https://chocolatey.org/install

### Paso 2: Copia el comando
Copia el comando PowerShell que aparece en la web

### Paso 3: Pega en PowerShell Administrador
Ejecuta el comando como en el Método 2

---

## ✅ Verificación

### Para verificar que Chocolatey está instalado:
Abre una nueva terminal (PowerShell o CMD) y ejecuta:

```cmd
choco --version
```

Deberías ver algo como: `1.4.0`

---

## 🔧 Configuración Adicional

### Actualizar Chocolatey:
```cmd
choco upgrade chocolatey -y
```

### Ver paquetes instalados:
```cmd
choco list --local-only
```

---

## ⚠️ Notas Importantes

1. **Requiere Administrador** - Debes ejecutar como administrador
2. **Internet** - Necesitas conexión para descargar
3. **Firewall/Antivirus** - Puede bloquear - permite la ejecución
4. **Reinicio** - Puede requerir reiniciar la terminal

---

## 🐛 Solución de Problemas

### Si dice "comando no reconocido":
1. Cierra y abre una nueva terminal
2. O reinicia el explorador de Windows:
   ```cmd
   taskkill /f /im explorer.exe && start explorer.exe
   ```

### Si hay error de permisos:
1. Asegúrate de ejecutar como Administrador
2. Revisa el Control de Cuentas de Usuario (UAC)

### Si el antivirus bloquea:
1. Desactiva temporalmente el antivirus
2. O añade excepción para Chocolatey

---

## 📋 Siguiente Paso

Una vez instalado Chocolatey, puedes ejecutar:

```cmd
setup-claude-ollama.bat
```

Para instalar Ollama y Claude Code automáticamente.
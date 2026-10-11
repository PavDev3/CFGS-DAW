# Ejercicio 8. Análisis del Comportamiento del ASI

```javascript
function crearConfiguracion() {
  return 
  {
    modo: "oscuro",
    puerto: 8080
  };
}

const config = crearConfiguracion();
console.log(config);
```

**a)** Se imprime `undefined`.

**b)** Al encontrar un salto de línea justo después de `return`, el mecanismo ASI (Automatic Semicolon Insertion) del motor de JavaScript inserta automáticamente un punto y coma, interpretando la línea como `return;`. El bloque `{ modo: "oscuro", puerto: 8080 }` que viene justo después queda huérfano y ya no se devuelve como objeto, así que la función termina devolviendo `undefined`.

**c)** Código corregido (la llave de apertura debe ir en la misma línea que `return`, sin salto de línea entre medias):
```javascript
function crearConfiguracion() {
  return {
    modo: "oscuro",
    puerto: 8080
  };
}

const config = crearConfiguracion();
console.log(config);
```

# Relación 4 — Ejercicios de Cadenas (Java)

← [[Programacion]] | Ver también: [[Java-1Trimestre]]

---

## Descripción

La Relación 4 es una colección de ejercicios que trabajan con la manipulación de cadenas de caracteres (String) en Java, usando únicamente métodos básicos como `charAt()`, `length()`, sin usar `toUpperCase()` ni `toLowerCase()` de la clase String.

---

## Lista de ejercicios

| # | Archivo | Descripción |
|---|---------|-------------|
| 1 | `ejercicio1_contarCaracter.java` | Contar cuántas veces aparece un carácter |
| 2 | `ejercicio2_contarMinusMayusNum.java` | Contar minúsculas, mayúsculas y dígitos |
| 3 | `ejercicio3_palindromo.java` | Verificar si una cadena es palíndromo |
| 4 | `ejercicio4_palabraEscondida.java` | Buscar una palabra dentro de otra |
| 5 | `ejercicio5_reemplazarPalabra.java` | Reemplazar una palabra por otra |
| 6 | `ejercicio6_contarVocalesDiferentes.java` | Contar cuántas vocales distintas hay |
| 7 | `ejercicio7_ordenarConsYVocales.java` | Ordenar consonantes y vocales por separado |
| 8 | `ejercicio8_validarLogin.java` | Validar contraseña con reglas |
| 9 | `ejercicio9_sumarNumeros.java` | Extraer y sumar números de una cadena |
| 10 | `ejercicio10_validarWebs.java` | Validar formato de URLs |
| 11 | `ejercicio11_ahorcado.java` | Juego del ahorcado completo |
| 12 | `ejercicio12_validarCorreo.java` | Validar formato de email |

---

## Técnicas usadas

### Recorrer una cadena carácter a carácter

```java
for (int i = 0; i < cadena.length(); i++) {
    char c = cadena.charAt(i);
    // procesar c
}
```

### Convertir manualmente a minúscula

```java
public static char aMinuscula(char c) {
    if (c >= 'A' && c <= 'Z') {
        return (char)(c + ('a' - 'A'));
    }
    return c;
}
```

### Verificar si un carácter es vocal

```java
public static boolean esVocal(char c) {
    c = Character.toLowerCase(c);
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
}
```

### Verificar si es un dígito

```java
if (c >= '0' && c <= '9') {
    // es un dígito
}
```

### Comparación de caracteres (ASCII)

```java
// Mayúsculas: 'A' (65) a 'Z' (90)
// Minúsculas: 'a' (97) a 'z' (122)
// Dígitos:    '0' (48) a '9' (57)
// Diferencia mayúscula-minúscula: 32
```

---

## Archivos

Ubicación: `/Users/imac/CFGS-DAW/Java/1Trimestre/Relacion_4/LaC/`

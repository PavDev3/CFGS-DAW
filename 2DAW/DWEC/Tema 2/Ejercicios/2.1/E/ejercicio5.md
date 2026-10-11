# Ejercicio 5. Validación Sintáctica de Identificadores

| Identificador | ¿Válido? | Motivo si es inválido |
|---|---|---|
| `1erUsuario` | ❌ Inválido | Empieza por un número |
| `_totalFactura` | ✅ Válido | Empieza por guion bajo |
| `$elementoDOM` | ✅ Válido | Empieza por el símbolo `$` |
| `precio-final` | ❌ Inválido | El guion `-` no está permitido (se interpretaría como una resta) |
| `class` | ❌ Inválido | Es una palabra reservada del lenguaje |
| `montoTotal2` | ✅ Válido | Los números solo están prohibidos al principio del identificador |
| `nombre usuario` | ❌ Inválido | Contiene un espacio, no permitido en un identificador |
| `function` | ❌ Inválido | Es una palabra reservada del lenguaje |

# Subnetting FLSM — Ejercicios Bloque 3 (Resuelto)

---

## Ejercicio 1

**IP:** `172.18.71.2` | **Máscara:** `255.255.248.0`

### Paso 1 — Convertir la máscara a notación CIDR

```
255.255.248.0
11111111.11111111.11111000.00000000
         ← 16 bits →  ← 5 → ← 3 →
```
→ Prefijo **/21**

### Paso 2 — Calcular el tamaño del bloque

El último octeto de la máscara con valor distinto de 0 o 255 es el 3er octeto: **248**

```
Tamaño de bloque = 256 − 248 = 8
```
Las subredes saltan de 8 en 8 en el tercer octeto: 0, 8, 16, 24, 32, 40, 48, 56, **64**, 72, 80...

### Paso 3 — Localizar la subred del host

El host tiene **71** en el tercer octeto. Buscamos el múltiplo de 8 más cercano por debajo:

```
64 ≤ 71 < 72  →  la subred empieza en 64
```

### Paso 4 — Calcular broadcast

```
Siguiente subred: 172.18.72.0
Broadcast:        172.18.72.0 − 1 = 172.18.71.255
```

### Resultado

| Campo | Valor |
|-------|-------|
| Dirección de red | 172.18.64.0 |
| Rango hosts | 172.18.64.1 – 172.18.71.254 |
| Broadcast | 172.18.71.255 |

**Respuesta correcta: d**
> network ID = 172.18.64.0 | broadcast = 172.18.71.255

---

## Ejercicio 2

**Red Clase B** | Subredes requeridas: 20 + 30 = **50** | Hosts por subred: **800**

### Paso 1 — Identificar la clase y sus bits

Clase B → los primeros **16 bits** son de red, los **16 bits** restantes son para subredes + hosts.

```
  16 bits red fija  |  n bits subred  |  m bits host
```

### Paso 2 — Calcular bits de subred (n)

Necesitamos al menos 50 subredes:

```
2¹ = 2    →  no llega
2² = 4    →  no llega
2³ = 8    →  no llega
2⁴ = 16   →  no llega
2⁵ = 32   →  no llega
2⁶ = 64   →  64 ≥ 50 ✓  →  n = 6
```

### Paso 3 — Calcular bits de host (m)

Los 16 bits disponibles menos los 6 de subred → m = 16 − 6 = **10 bits**

Verificación: 2¹⁰ − 2 = **1022 hosts** ≥ 800 ✓

### Paso 4 — Construir la máscara

```
Prefijo = 16 (clase B) + 6 (subred) = /22

11111111.11111111.11111100.00000000
   255  .   255  .   252  .    0
```

**Respuesta correcta: b**
> 255.255.252.0

---

## Ejercicio 3

**Red Clase B** | Subredes requeridas: 20 + 4 = **24** | Hosts por subred: **2000**

### Paso 1 — Calcular bits de subred (n)

```
2⁴ = 16   →  16 < 24, no llega
2⁵ = 32   →  32 ≥ 24 ✓  →  n = 5
```

### Paso 2 — Calcular bits de host (m)

Bits disponibles: 16 − 5 = **11 bits**

Verificación: 2¹¹ − 2 = **2046 hosts** ≥ 2000 ✓

### Paso 3 — Construir la máscara

```
Prefijo = 16 (clase B) + 5 (subred) = /21

11111111.11111111.11111000.00000000
   255  .   255  .   248  .    0
```

### Paso 4 — Comparar con las opciones

| Opción | Prefijo | Subredes | Hosts/subred |
|--------|---------|----------|--------------|
| /19 | 3 bits subred | 8 subredes — **insuficiente** | 8190 |
| **/21** | **5 bits subred** | **32 subredes ✓** | **2046 ✓** |
| /22 | 6 bits subred | 64 subredes | 1022 — **insuficiente** |
| /24 | 8 bits subred | 256 subredes | 254 — **insuficiente** |

**Respuesta correcta: b**
> /21

---

## Ejercicio 4

**Red concedida:** `200.35.1.0/24` (Clase C) | Objetivo: **≥ 20 hosts por subred**

### a) Máscara de subred

**Paso 1 — Calcular bits de host (m):**

```
2¹ − 2 = 0    →  no llega
2² − 2 = 2    →  no llega
2³ − 2 = 6    →  no llega
2⁴ − 2 = 14   →  14 < 20, no llega
2⁵ − 2 = 30   →  30 ≥ 20 ✓  →  m = 5
```

**Paso 2 — Bits de subred:**

Clase C tiene 8 bits de host. Con m = 5:
```
n = 8 − 5 = 3 bits de subred
```

**Paso 3 — Construir la máscara:**

```
/24 + 3 = /27

11111111.11111111.11111111.11100000
   255  .   255  .   255  .   224
```

**Máscara: 255.255.255.224 (/27)**

---

### b) Número máximo de subredes

```
2ⁿ = 2³ = 8 subredes
```

---

### c) Tabla completa de subredes

Tamaño de bloque: 2⁵ = **32**

| # | Dirección de red | Rango asignable a hosts | Broadcast |
|---|-----------------|------------------------|-----------|
| 1 | 200.35.1.**0**/27   | 200.35.1.1 – 200.35.1.30   | 200.35.1.**31**  |
| 2 | 200.35.1.**32**/27  | 200.35.1.33 – 200.35.1.62  | 200.35.1.**63**  |
| 3 | 200.35.1.**64**/27  | 200.35.1.65 – 200.35.1.94  | 200.35.1.**95**  |
| 4 | 200.35.1.**96**/27  | 200.35.1.97 – 200.35.1.126 | 200.35.1.**127** |
| 5 | 200.35.1.**128**/27 | 200.35.1.129 – 200.35.1.158 | 200.35.1.**159** |
| **6** | **200.35.1.160/27** | **200.35.1.161 – 200.35.1.190** | **200.35.1.191** |
| 7 | 200.35.1.**192**/27 | 200.35.1.193 – 200.35.1.222 | 200.35.1.**223** |
| 8 | 200.35.1.**224**/27 | 200.35.1.225 – 200.35.1.254 | 200.35.1.**255** |

> Patrón: cada subred empieza en un múltiplo de 32 → 0, 32, 64, 96, 128, 160, 192, 224

---

### d) Hosts asignables en la subred 6

La subred 6 es `200.35.1.160/27`:

```
Dirección de red:  200.35.1.160  →  no asignable
Primer host:       200.35.1.161
Último host:       200.35.1.190
Broadcast:         200.35.1.191  →  no asignable
```

**Rango asignable: 200.35.1.161 – 200.35.1.190 (30 hosts disponibles)**

---

### e) Broadcast de la subred 6

```
Inicio subred 6:     200.35.1.160
Siguiente subred:    200.35.1.192
Broadcast:           200.35.1.192 − 1 = 200.35.1.191
```

**Broadcast subred 6: `200.35.1.191`**

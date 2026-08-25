# Subnetting FLSM — Relación 2 (Resuelto)

---

## Ejercicio 1

**IP:** `10.46.173.85` | **Máscara:** `255.255.240.0`

**¿Qué pide?** Dado un host concreto, encontrar a qué subred pertenece (dirección de red) y cuál es su broadcast.

---

### Paso 1 — Convertir la máscara a binario para saber el prefijo

La máscara en binario nos dice cuántos bits están "fijos" (los unos) y cuántos son libres para hosts (los ceros):

```
255  .  255  .  240  .   0
11111111.11111111.11110000.00000000
```

Contamos los unos: 8 + 8 + 4 = **20 bits** → prefijo **/20**

> Por qué importa: el prefijo nos dice en qué octeto "actúa" la máscara.
> Con /20, los primeros 16 bits identifican la red clase A (10.x) y los siguientes 4 bits
> identifican la subred — ese "punto de corte" cae en el 3er octeto.

---

### Paso 2 — Calcular el tamaño del bloque (salto entre subredes)

El único octeto que no es ni 255 ni 0 es el **3er octeto: 240**.
Ese es el octeto "interesante" donde las subredes cambian.

```
Tamaño de bloque = 256 − 240 = 16
```

> Por qué 256 − máscara: la máscara 240 deja 4 bits libres en ese octeto (2⁴ = 16).
> Cada subred ocupa exactamente 16 direcciones, así que la siguiente empieza 16 más arriba.
> Dicho de otro modo: los posibles valores del 3er octeto para inicios de subred son
> múltiplos de 16: 0, 16, 32, 48, ..., 144, **160**, 176, 192, ...

---

### Paso 3 — Localizar en qué subred cae el host

El 3er octeto del host es **173**. Buscamos entre qué dos múltiplos de 16 está:

```
160 ≤ 173 < 176   →   la subred empieza en 160
```

> La dirección de red es siempre el múltiplo de bloque inmediatamente inferior al valor del host.
> Nunca se redondea hacia arriba: si el host es 173, la subred es 160, no 176.

Por tanto la **dirección de red = 10.46.160.0** (los octetos a la derecha del punto de corte se ponen a 0).

---

### Paso 4 — Calcular el broadcast

El broadcast es la última dirección de la subred, es decir, la dirección justo antes de que empiece la siguiente:

```
Siguiente subred:  10.46.176.0
Broadcast:         10.46.176.0 − 1  =  10.46.175.255
```

> Los octetos a la derecha del punto de corte se ponen todos a 1 (255).
> El 3er octeto pasa de 176 a 175, y el 4º octeto que era 0 se convierte en 255.

---

### Resultado

| Campo | Valor |
|-------|-------|
| Dirección de red | 10.46.160.0 |
| Primer host asignable | 10.46.160.1 |
| Último host asignable | 10.46.175.254 |
| Broadcast | 10.46.175.255 |
| Hosts útiles | 2¹² − 2 = **4094** |

**Respuesta correcta: a**
> network ID = 10.46.160.0 | broadcast = 10.46.175.255

---

## Ejercicio 2

**Red Clase B** | Subredes requeridas: 35 + 20 = **55** | Hosts por subred: **1000**

**¿Qué pide?** Encontrar la máscara que permita *a la vez* tener ≥ 55 subredes Y ≥ 1000 hosts por subred.

---

### Paso 1 — ¿De cuántos bits disponemos?

Una red **Clase B** tiene por defecto 16 bits para la parte de red y 16 bits para hosts.
Al hacer subnetting, tomamos una parte de esos 16 bits libres para identificar subredes:

```
| 16 bits red (fijos) | n bits subred | m bits host |
|      clase B        |   "robados"   |  disponibles |
                      ←      16 bits en total      →
```

La clave es que **n + m = 16** siempre, y hay que elegir n y m de forma que se cumplan los dos requisitos.

---

### Paso 2 — Calcular bits de subred (n)

Necesitamos representar al menos 55 subredes. Cada bit extra duplica las posibles combinaciones:

```
2⁵ = 32   →   32 < 55, no llega
2⁶ = 64   →   64 ≥ 55 ✓   →   n = 6 bits de subred
```

> Siempre se toma la potencia de 2 más pequeña que sea ≥ al número requerido.
> Con 5 bits solo tendríamos 32 subredes y nos quedaríamos cortos.

---

### Paso 3 — Calcular bits de host (m) y verificar

Con n = 6, los bits restantes para hosts son:

```
m = 16 − 6 = 10 bits de host
```

Hosts útiles por subred (se restan 2: dirección de red y broadcast):

```
2¹⁰ − 2 = 1024 − 2 = 1022 hosts ≥ 1000 ✓
```

> Si m hubiera dado menos de 1000 hosts útiles, habría que volver al paso 2 y probar con n − 1.
> En este caso cuadra perfectamente.

---

### Paso 4 — Construir la máscara

Sumamos los bits de red de clase B más los bits de subred:

```
Prefijo = 16 (clase B) + 6 (subred) = /22
```

Escribimos 22 unos seguidos de 10 ceros:

```
11111111.11111111.11111100.00000000
   255  .   255  .   252  .    0
```

> El tercer octeto tiene 6 unos (los bits de subred) y 2 ceros (primeros bits de host):
> 11111100 en binario = 128+64+32+16+8+4 = 252

---

### Paso 5 — Verificar descartando las otras opciones

| Opción | Prefijo | Bits subred (n) | Subredes posibles | Bits host (m) | Hosts útiles |
|--------|---------|-----------------|-------------------|---------------|--------------|
| a. 255.255.248.0 | /21 | 5 | 32 — **insuficiente** | 11 | 2046 |
| **b. 255.255.252.0** | **/22** | **6** | **64 ✓** | **10** | **1022 ✓** |
| c. 255.255.254.0 | /23 | 7 | 128 ✓ | 9 | 510 — **insuficiente** |
| d. 255.255.240.0 | /20 | 4 | 16 — **insuficiente** | 12 | 4094 |

> La opción c tiene subredes de sobra pero no llega a 1000 hosts por subred.
> La opción a tiene hosts de sobra pero no llega a 55 subredes.
> Solo la b cumple los dos requisitos simultáneamente.

**Respuesta correcta: b**
> 255.255.252.0

---

## Ejercicio 3

**Red concedida:** `192.168.50.0/24` (Clase C) | Objetivo: **≥ 30 hosts por subred**

**¿Qué pide?** A partir de una red /24 (Clase C), hacer subnetting para que cada subred tenga al menos 30 hosts útiles. Luego describir todas las subredes y detallar la subred 6.

---

### a) Máscara de subred

**Punto de partida:** Una red /24 tiene 8 bits libres (el 4º octeto entero).
Al hacer subnetting, de esos 8 bits, tomamos n para subredes y dejamos m para hosts.

```
| 24 bits red (fijos) | n bits subred | m bits host |
|     clase C /24     |   "robados"   |  disponibles |
                      ←    8 bits en total          →
```

**Paso 1 — Calcular los bits de host necesarios (m):**

La fórmula de hosts útiles es **2ᵐ − 2** (se restan la dirección de red y el broadcast):

```
2³ − 2 =  6   →   6 < 30, no llega
2⁴ − 2 = 14   →   14 < 30, no llega
2⁵ − 2 = 30   →   30 = 30 ✓   →   m = 5 bits para hosts
```

> ¿Por qué restar 2? Porque en cada subred la primera dirección (todos ceros en la parte de host)
> es la dirección de red y no se puede asignar a ningún equipo. La última (todos unos) es el
> broadcast y tampoco se asigna. Por eso de 32 direcciones totales solo 30 son útiles.

**Paso 2 — Calcular bits de subred (n):**

```
n = 8 − m = 8 − 5 = 3 bits de subred
```

**Paso 3 — Construir la máscara:**

Al prefijo base /24 le sumamos los 3 bits de subred:

```
/24 + 3 = /27
```

En binario, el 4º octeto tiene 3 unos (subred) seguidos de 5 ceros (host):

```
11100000  →  128 + 64 + 32 = 224
```

```
11111111.11111111.11111111.11100000
   255  .   255  .   255  .   224
```

**Máscara: 255.255.255.224 (/27)**

---

### b) Número máximo de subredes

Con n = 3 bits de subred:

```
2³ = 8 subredes
```

> Esos 3 bits pueden tomar los valores: 000, 001, 010, 011, 100, 101, 110, 111 → 8 combinaciones.

---

### c) Tabla completa de subredes

El **tamaño de bloque** es 2ᵐ = 2⁵ = **32** (cada subred agrupa 32 direcciones).

> El bloque de 32 viene de los 5 bits de host: 2⁵ = 32 direcciones por subred.
> Por eso el 4º octeto avanza de 32 en 32: 0, 32, 64, 96, 128, 160, 192, 224.

| # | Dirección de red | Rango asignable a hosts | Broadcast |
|---|-----------------|------------------------|-----------|
| 1 | 192.168.50.**0**/27   | 192.168.50.1 – 192.168.50.30   | 192.168.50.**31**  |
| 2 | 192.168.50.**32**/27  | 192.168.50.33 – 192.168.50.62  | 192.168.50.**63**  |
| 3 | 192.168.50.**64**/27  | 192.168.50.65 – 192.168.50.94  | 192.168.50.**95**  |
| 4 | 192.168.50.**96**/27  | 192.168.50.97 – 192.168.50.126 | 192.168.50.**127** |
| 5 | 192.168.50.**128**/27 | 192.168.50.129 – 192.168.50.158 | 192.168.50.**159** |
| **6** | **192.168.50.160/27** | **192.168.50.161 – 192.168.50.190** | **192.168.50.191** |
| 7 | 192.168.50.**192**/27 | 192.168.50.193 – 192.168.50.222 | 192.168.50.**223** |
| 8 | 192.168.50.**224**/27 | 192.168.50.225 – 192.168.50.254 | 192.168.50.**255** |

> Cómo leer la tabla: la dirección de red de cada fila es la de la anterior + 32.
> El primer host es siempre dirección_de_red + 1.
> El broadcast es siempre la siguiente_dirección_de_red − 1 (o equivalentemente, dirección_de_red + 31).

---

### d) Hosts asignables en la subred 6

La subred 6 empieza en **192.168.50.160** (quinta subred = 5×32 = 160):

```
Dirección de red:  192.168.50.160   →  reservada, no asignable a hosts
Primer host:       192.168.50.161   →  dirección_de_red + 1
Último host:       192.168.50.190   →  broadcast − 1
Broadcast:         192.168.50.191   →  reservada, no asignable a hosts
```

**Rango asignable: 192.168.50.161 – 192.168.50.190 (30 hosts disponibles)**

> 30 hosts = 2⁵ − 2 = 32 − 2, que coincide exactamente con el requisito del enunciado.

---

### e) Broadcast de la subred 6

```
Inicio subred 6:     192.168.50.160
Siguiente subred:    192.168.50.192   (160 + 32 = 192)
Broadcast:           192.168.50.192 − 1 = 192.168.50.191
```

> El broadcast siempre es el número inmediatamente anterior al inicio de la siguiente subred.
> Otra forma de verlo: en la parte de host, el broadcast tiene todos los bits a 1 → 11111 en binario = 31 → 160 + 31 = 191.

**Broadcast subred 6: `192.168.50.191`**

# BATALLA DE PINGS

> **Duracion:** 20-30 min  
> **Modalidad:** Cada alumno por su cuenta (o por parejas)  
> **Objetivo:** Dominar IPs estaticas mientras sobrevives en una guerra de pings  
> **Unico material:** terminal, papel, y 5 pings por persona

---

## PREGUNTAS FRECUENTES

**"Y si rompo algo?"**  
No. Ping solo envia paquetes ICMP, no configura nada. Lo peor que pasa es que alguien descubra tu IP.

**"Y si me pongo una IP fuera de la red?"**  
Entonces nadie puede matarte... pero tampoco puedes matar a nadie. Aburrido.

**"Puedo hacer ping a un rango entero?"**  
Nope. Solo a IPs concretas. Toca pensar.

---

## FASE 1 - PREPARACION (10 min)

### Paso 1: Elegid vuestra arma secreta

1. Mirad que IP teneis actualmente (DHCP): anotadla por si hay que volver.

2. **Elegid una IP estatica** dentro de la red `192.168.50.0/24`.

**Reglas de eleccion:**
- Tiene que estar entre `192.168.50.10` y `192.168.50.99`.
- No podeis decirl a nadie cual habeis elegido.
- Si alguien mas elige la misma, **ambos moris** al instante (conflicto de IPs = muerte).

**Estrategia:** podeis elegir una IP que nadie va a intentar (como `192.168.50.99`) y esperar, u elegir una que alguien va a buscar y rezar.

### Paso 2: Configurad la IP estatica

#### Ubuntu

```bash
# Averiguad vuestra interfaz
ip link

# Editad netplan (ajustad el nombre del fichero si es diferente)
sudo nano /etc/netplan/00-installer-config.yaml
```

```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:                    # <-- ajustad a vuestra interfaz
      addresses:
        - VUESTRA_IP/24
      gateway4: 192.168.50.1
      nameservers:
        addresses:
          - 8.8.8.8
```

```bash
sudo netplan apply
```

#### Windows

1. `Win + R` -> `ncpa.cpl`
2. Adaptador de red -> Propiedades
3. IPv4 -> Usar siguiente IP:
   - IP: `VUESTRA_IP`
   - Mascara: `255.255.255.0`
   - Gateway: `192.168.50.1`
   - DNS: `8.8.8.8`

#### Mac

1. Preferencias del Sistema -> Red
2. WiFi -> Avanzado -> TCP/IP
3. IPv4: Manualmente
4. IP: `VUESTRA_IP`, Mascara: `255.255.255.0`, Router: `192.168.50.1`

### Paso 3: Verificad que habeis sobrevivido

Haced ping a vosotros mismos:
- Ubuntu/Mac: `ping -c 4 VUESTRA_IP`
- Windows: `ping VUESTRA_IP`

**Si responde:** estais vivos. Anotad la IP en un papel y **NO SE LO MOSTREIS A NADIE**.

**Si NO responde:** hay conflicto. Cambiad a otra IP inmediatamente.

### Paso 4: Verificad que podeis matar

Haced ping al gateway para confirmar que podeis atacar:
```
ping -c 4 192.168.50.1
```

**Podeis disparar. Teneis 5 balas (pings). Cuidado con desperdiciar balas.**

---

## FASE 2 - LA GUERRA (15-20 min)

### Reglas

1. Cada jugador tiene **5 pings** (balas).
2. Podeis hacer ping a **cualquier IP** de la red `192.168.50.0/24`.
3. Si haceis ping a una IP y **responde**, el objetivo **MUERE**.
4. Podeis consultar la lista de IPs "muertas" al profesor.
5. Podeis **revivir** si conseguis hacer ping a alguien que no os ha matado todavia? NO. Muerto es muerto.
6. **El ultimo en pie gana.**

### Estrategia

| Estilo | Ventaja | Riesgo |
|--------|---------|--------|
| Esperar (IP unpopular) | Nadie te dispara | Nadie pasa cerca |
| Disparar a IPs comunes (20-50) | Mas probabilidades de acertar | Gastas balas rapido |
| Disparar a IPs muy cercanas a la tuya | Menos IPs que cubrir | others may think the same |
| Dejar que otros se maten entre si | Ahorras balas | Puede que no quede nadie |

### Como jugar

Cada vez que haceis un ping:

```
ping -c 4 192.168.50.XX   # Sustituid XX por la IP que elegisteis atacar
```

**Resultado A (reply received):**
```
64 bytes from 192.168.50.XX: icmp_seq=1 ttl=64 time=0.5ms
```
**HABEIS MATADO A ESE JUGADOR.** Anotadle como muerto y rested 1 bala.

**Resultado B (no reply):**
```
Request timeout for icmp_seq 1
```
**No habeis dado.** Restead 1 bala.

**Resultado C (unreachable):**
```
From 192.168.50.1: Destination Host Unreachable
```
**Esa IP no existe o esta apagada.** Bala perdida.

---

## FASE 3 - ANALISIS POST-GUERRA (5 min)

Cuando la guerra termina (1 superviviente o se acaban las balas):

1. **Contad** balas restantes.
2. **Contad** victimas.
3. **Responded** en vuestra hoja:
   - A que IPs habeis disparado?
   - Quienes os han disparado a vosotros?
   - Cual era la IP del que os ha matado?
   - habis descubierto alguna IP de alguien sin mati?

---

## HOJA DE GUERRA

### Tu perfil

| Campo | Valor |
|-------|-------|
| Mi IP secreta | `192.168.50.____` |
| Mi SO | Windows / Mac / Ubuntu |
| Balas iniciales | 5 |
| Balas restantes | |

### Registro de disparos

| # | IP objetivo | Resultado | Bala gastada? |
|---|-------------|-----------|--------------|
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| 5 | | | |

### Registro de bajas

| Quien me ha matado | IP del asesino | Bala que le quedaba |
|--------------------|---------------|--------------------|
| | | |

### Preguntas post-guerra

1. Tu IP era facil o dificil de encontrar? Por que?
2. Que zona de IPs ha sido mas peligroso? Por que?
3. Si volvieras a jugar, que IP elegirias? Por que?
4. Podeis identificar a la persona que os mato?

---

## TABLA DE MORTOS (el profesor va anotando)

| # | IP | Matado por | Balas restantes del matador |
|---|----|-----------|---------------------------|
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| 5 | | | |
| 6 | | | |
| 7 | | | |
| 8 | | | |
| 9 | | | |
| 10 | | | |

---

## VARIANTES

### Variante A: Por parejas
Jugais en parejas. Entre los dos teneis 10 balas. Podeis hablar y deciros IPs por telegram/whatsapp, pero **nadie puede ver la pantalla del otro**.

### Variante B: Escudo (1 uso)
Cada jugador puede declarar **un escudo** en una IP (por ejemplo la vuestra). Si alguien os dispara y teneis escudo, el atacante **pierde 2 balas** y vosotros seguis vivos. Solo podeis usar 1 escudo en toda la partida.

### Variante C: Cluedo de redes
Al final, todos revelan sus IPs. Quien ha matado a quien? Drawn el mapa de la masacre en forma de red (las IPs como nodos, flechas de matador a victima).

---

## LECCIONES QUE SE VEN

- IP estatica: concepto y configuracion en cada SO.
- Rango valido de IPs en una subred (`192.168.50.10` a `192.168.50.99`).
- ICMP/ping: quien responde, quien no.
- Broadcast (`192.168.50.255`) y redes invalidas.
- Por que el DHCP evita conflictos de IPs automaticamente.
- Estrategia: cuanto mas obvio, mas peligro.

---

---

---

# IP BATTLE ROYALE - Cuaderno de preguntas

> **Duracion:** 45-60 min  
> **Modalidad:** Competicion por equipos (3-5 personas)  
> **Objetivo:** Dominar IPs, mascaras, redes y gateways a base de adrenalina  
> **Unico material:** papel, boli, y vuestra red de clase (para verificar)

---

## Preparacion

1. Formad equipos de 3-5 personas.
2. Cada equipo tiene un nombre de equipo de red (ej: "Los Routers", "Paquetes S.A.", "DHCP Warriors").
3. Ponedle un nombre gracioso al equipo.
4. El profesor tiene las respuestas. Cada respuesta correcta = 1 punto.
5. **Premio:** gloria eterna y chocolate (o lo que decida el profesor).

---

## REGLAS

- El profesor lanza una pregunta. Tenéis **30 segundos** para deliberar en equipo.
- Un representante de cada equipo levanta la mano para responder.
- El primero en levantar tiene prioridad. Si falla, -1 punto y se abre al resto.
- Respuesta correcta = +1 punto. Fallo = -1 punto.
- Respuesta correcta al segundo intento = +0.5 puntos.
- **Round bonus:** al final de cada ronda, el equipo que mas fallos haya tenido puede intentar un "desafio de reparacion" (+2 puntos si acierta).

---

## RONDA 0 - Calentamiento (5 min, 5 preguntas)

Preguntas suaves para abrir boca.

---

**P0-1.** El router de clase tiene IP `192.168.50.1`. Esta IP, es valida para una red local?

- a) Si
- b) No
- c) Depende

<details>
<answer>Respuesta: a) Si. Las IPs `192.168.x.x` son rango privado.</answer>
</details>

---

**P0-2.** Como se llama el servicio que da IPs automaticamente en la red de clase?

- a) DNS
- b) DHCP
- c) NAT
- d) HTTP

<details>
<answer>Respuesta: b) DHCP</answer>
</details>

---

**P0-3.** Si tu maquina tiene IP `192.168.50.25` y mascara `255.255.255.0`, cual es la direccion de red?

- a) `192.168.50.0`
- b) `192.168.50.255`
- c) `192.168.0.0`
- d) `255.255.255.0`

<details>
<answer>Respuesta: a) `192.168.50.0`</answer>
</details>

---

**P0-4.** Que significa DNS?

- a) Digital Network System
- b) Domain Name System
- c) Direct Network Service
- d) Data Network Security

<details>
<answer>Respuesta: b) Domain Name System</answer>
</details>

---

**P0-5.** Cual es la IP del servidor DNS de Google?

- a) `192.168.1.1`
- b) `8.8.8.8`
- c) `255.255.255.0`
- d) `1.1.1.1`

<details>
<answer>Respuesta: b) `8.8.8.8`</answer>
</details>

---

## RONDA 1 - Valido o invalido? (10 min, 8 preguntas)

El objetivo: decir SI o NO rapido. Si es invalido, deci POR QUE en una palabra.

| # | IP | Valida? | Por que? |
|---|----|---------|----------|
| 1 | `256.1.2.3` | NO | 256 no existe |
| 2 | `192.168.50.1` | SI | privada |
| 3 | `0.0.0.0` | SI | this network |
| 4 | `192.168.50.0` | NO | direccion de red |
| 5 | `192.168.50.255` | NO | broadcast |
| 6 | `10.10.10.10` | SI | privada |
| 7 | `172.16.0.1` | SI | privada |
| 8 | `224.0.0.1` | SI | multicast |

---

## RONDA 2 - A que red pertenece? (10 min, 6 preguntas)

Dadas una IP y una mascara, direis la direccion de red.

**Regla rapida:** IP AND mascara = red

| # | IP | Mascara | Red |
|---|----|---------|-----|
| 1 | `192.168.50.30` | `255.255.255.0` | `192.168.50.0` |
| 2 | `192.168.50.30` | `255.255.0.0` | `192.168.0.0` |
| 3 | `10.0.5.20` | `255.255.255.0` | `10.0.5.0` |
| 4 | `10.0.5.20` | `255.0.0.0` | `10.0.0.0` |
| 5 | `172.16.100.50` | `255.255.0.0` | `172.16.0.0` |
| 6 | `172.16.100.50` | `255.255.255.0` | `172.16.100.0` |

---

## RONDA 3 - Verdadero o Falso (10 min, 8 preguntas)

Decid TRUE o FALSE. Si es FALSE, dad la correccion en una frase.

1. **La mascara `255.255.0.0` permite mas hosts que `255.255.255.0`.**  
   <details>TRUE - /16 = 65.534 hosts, /24 = 254.</details>

2. **Una IP estatica cambia cada vez que reinicias.**  
   <details>FALSE - es fija.</details>

3. **Todas las IPs que empiezan por 192.168 son privadas.**  
   <details>TRUE.</details>

4. **El gateway siempre tiene la IP .1 de la red.**  
   <details>FALSE - puede ser cualquiera, por convenio se usa .1.</details>

5. **Si cambias tu IP a `192.168.60.1` pero dejas el gateway en `192.168.50.1`, puedes navegar.**  
   <details>FALSE - redes diferentes, no puedes alcanzar el gateway.</details>

6. **El broadcast de `10.0.0.0/24` es `10.0.0.255`.**  
   <details>TRUE.</details>

7. **DNS traduce IPs a nombres de dominio.**  
   <details>TRUE - y viceversa.</details>

8. **Dos maquinas en diferente red pueden comunicarse directamente sin gateway.**  
   <details>FALSE - siempre necesitan gateway.</details>

---

## RONDA 4 - Calcula el numero de hosts (8 min, 5 preguntas)

**Formula:** `hosts = 2^(32 - bits) - 2`

Solo decis el numero. Sin calculadora.

| # | CIDR | Hosts | Respuesta |
|---|------|-------|-----------|
| 1 | `/24` | ~254 | 254 |
| 2 | `/16` | ~65.000 | 65.534 |
| 3 | `/25` | ~126 | 126 |
| 4 | `/26` | ~62 | 62 |
| 5 | `/8` | ~16M | 16.777.214 |

**Bonus:** Una red `/29` tiene... `6` hosts.

---

## RONDA 5 - Picture Round (8 min, 5 preguntas)

El profesor proyecta una salida de comandos. Interpreta.

### PR-1

```
Direccion IPv4: 192.168.50.100
Mascara de subred: 255.255.255.0
Puerta de enlace: 192.168.50.1
```

Esta maquina puede hacer ping a `google.com`?

<details>SI - tiene IP, mascara y gateway.</details>

---

### PR-2

```
2: enp0s3: <BROADCAST,MULTICAST,UP> mtu 1500
    inet 192.168.50.50/24 brd 192.168.50.255
```

Esta IP es DHCP o estatica?

<details>NO SE SABE SOLO CON ESTO.</details>

---

### PR-3

```
IP: 192.168.50.50
Mascara: 255.255.255.0
Gateway: (en blanco)
```

Esta maquina puede navegar por internet?

<details>NO - no tiene gateway para salir de la LAN.</details>

---

### PR-4

```
en0: flags=8863<UP,BROADCAST,SMART,RUNNING,SIMPLEX>
    inet 192.168.50.100/24 broadcast 192.168.50.255
```

Podeis hacer ping a `8.8.8.8`? Y a `192.168.50.30`?

<details>A 8.8.8.8: DEPENDE (sin ver gateway). A 192.168.50.30: SI, misma red.</details>

---

### PR-5

```
default via 192.168.50.1 dev enp0s3 proto dhcp
10.10.10.0/24 dev enp0s8 proto kernel scope link src 10.10.10.10
```

Cuantas interfaces tiene? Cual es el gateway por defecto?

<details>2 interfaces: enp0s3 (gateway 192.168.50.1) y enp0s8 (10.10.10.10).</details>

---

## RONDA 6 - Escenarios (10 min, 4 preguntas)

Situaciones reales. Decid que-linux pasara y por que.

---

**ESC-1: La oficina conectada**

> Router: `10.0.0.1/8`. Informatico pone `10.255.255.254` al receptionist. Cuantos equipos maximo en esa oficina?

<details>~16.7 millones menos 2.</details>

---

**ESC-2: El estudiante挑麻烦了**

> Un alumno cambia su IP a `192.168.50.1` (la misma que el router). Que pasa?

<details>CONFLICTO IP. Ninguno puede comunicarse bien. Posible "IP duplicada".</details>

---

**ESC-3: La cafeteria sin internet**

> DHCP reparte `192.168.0.100-200`. Un cliente pone estatica `192.168.1.50`. Navega?

<details>NO. Redes diferentes (192.168.1.x vs 192.168.0.x).</details>

---

**ESC-4: El administrador paranoico**

> Mascara `255.255.255.252` en link entre 2 routers. Cuantas IPs? Le vale?

<details>/30 = 4 direcciones, 2 usables. Perfecto para 2 routers.</details>

---

## RONDA FINAL - Speed Round (5 min, 10 preguntas)

10 segundos cada una.

| # | Pregunta | Respuesta |
|---|----------|-----------|
| 1 | Broadcast de `192.168.50.0/24` | `192.168.50.255` |
| 2 | Red de `172.16.5.10/16` | `172.16.0.0` |
| 3 | Mascara /26 en decimal | `255.255.255.192` |
| 4 | DNS traduce... | Nombres -> IPs |
| 5 | Gateway es la puerta de... | La LAN hacia fuera |
| 6 | DHCP reparte... | IPs automaticamente |
| 7 | Dos maquinas misma red, sin gateway... pueden comunicarse? | SI |
| 8 | Rango privado Clase B | `172.16.0.0 - 172.31.255.255` |
| 9 | Rango privado Clase A | `10.0.0.0 - 10.255.255.255` |
| 10 | CIDR de mascara `255.255.255.128` | `/25` |

---

## CLASIFICACION FINAL

| Posicion | Equipo | Puntos |
|----------|--------|--------|
| 1 | | |
| 2 | | |
| 3 | | |
| ... | | |

---

## HOJA DE ANOTACIONES DEL EQUIPO

### Ronda 1 - Valido o invalido

| # | Respuesta | Correcta? |
|---|-----------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |
| 6 | | |
| 7 | | |
| 8 | | |

### Ronda 2 - A que red pertenece

| # | Respuesta | Correcta? |
|---|-----------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |
| 6 | | |

### Ronda 3 - V/F

| # | V/F | Correccion | Correcta? |
|---|-----|-----------|-----------|
| 1 | | | |
| 2 | | | |
| 3 | | | |
| 4 | | | |
| 5 | | | |
| 6 | | | |
| 7 | | | |
| 8 | | | |

### Ronda 4 - Hosts

| # | Respuesta | Correcta? |
|---|-----------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |

### Ronda 5 - Picture Round

| # | Interpretacion | Correcta? |
|---|----------------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |

### Ronda 6 - Escenarios

| # | Respuesta | Correcta? |
|---|-----------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |

### Speed Round

| # | Respuesta | Correcta? |
|---|-----------|-----------|
| 1 | | |
| 2 | | |
| 3 | | |
| 4 | | |
| 5 | | |
| 6 | | |
| 7 | | |
| 8 | | |
| 9 | | |
| 10 | | |

---

## PUNTUACION TOTAL

| Equipo | R1 | R2 | R3 | R4 | R5 | R6 | Final | **TOTAL** |
|--------|----|----|----|----|----|----|-------|-----------|
| | | | | | | | | |
| | | | | | | | | |
| | | | | | | | | |
| | | | | | | | | |

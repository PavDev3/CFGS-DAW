# Pokémon Añil — Editor de Partida

Editor de saves para **Pokémon Añil 4.0** (Pokemon Essentials v21, RPG Maker XP / mkxp-z).

---

## Requisitos

- Python 3.8 o superior

---

## Estructura de carpetas

```
saveEditor/
├── save_editor.py    ← script principal
├── PBS/
│   ├── pokemon.txt   ← datos de especies
│   └── moves.txt     ← datos de movimientos
├── saves/            ← PEGA AQUÍ el .rxdata del juego
└── savesGen/         ← el save modificado aparece aquí
```

---

## Uso rápido

1. Copia tu archivo de partida (normalmente `Partida 1.rxdata`) desde:
   ```
   %APPDATA%\Pokemon Anil\
   ```
   y pégalo en la carpeta `saves/`.

2. Ejecuta el editor:
   ```
   python save_editor.py
   ```

3. Elige una opción del menú, realiza los cambios y pulsa **0** para guardar.

4. Copia el archivo resultante de `savesGen/` de vuelta a `%APPDATA%\Pokemon Anil\`  
   (sobreescribiendo el original).

5. Lanza el juego — la partida cargará con los cambios aplicados.

---

## Opciones del menú

| Opción | Descripción |
|--------|-------------|
| 1 | Cambiar dinero (Pokédólares) |
| 2 | Cambiar nivel de un Pokémon del equipo (recalcula stats) |
| 3 | Cambiar medallas obtenidas |
| 4 | Agregar ítem a la bolsa |
| 5 | Ver todos los atributos de un Pokémon |
| 6 | Modificar atributo específico de un Pokémon (número) |
| 7 | Agregar Pokémon al equipo (manual o aleatorio) |
| 8 | Eliminar Pokémon del equipo |
| 9 | Randomizar naturaleza, IVs y habilidad de un Pokémon |
| **10** | **Generar equipo de torneo** (ver más abajo) |
| 0 | Guardar y salir |
| Q | Salir sin guardar |

---

## Opción 10 — Equipo de Torneo

Genera automáticamente un equipo de **6 Pokémon aleatorios** con configuración competitiva:

- Nivel 100, 31 IVs en todos los stats
- EVs: 252 en stat ofensiva principal + 252 en Velocidad + 4 en HP
- Naturaleza aleatoria
- Habilidad aleatoria (incluye habilidad oculta)
- 4 movimientos aleatorios del learnset (nivel-up + tutor)
- Solo Pokémon completamente evolucionados
- **Sin legendarios ni míticos** (pseudo-legendarios permitidos)

> **Nota sobre el nivel:** el juego puede mostrar los stats bloqueados al nivel máximo
> permitido por la historia. El nivel 100 está guardado correctamente en el save,
> pero el juego aplica su propio límite en pantalla.

### Pokémon baneados

#### Legendarios (71)
Articuno, Azelf, Calyrex, Chi-Yu, Chien-Pao, Cobalion, Cosmoem, Cosmog, Cresselia,
Código Cero, Dialga, Enamorus, Entei, Eternatus, Fezandipiti, Giratina, Glastrier,
Groudon, Heatran, Ho-Oh, Koraidon, Kubfu, Kyogre, Kyurem, Landorus, Latias, Latios,
Lugia, Lunala, Mesprit, Mewtwo, Miraidon, Moltres, Munkidori, Necrozma, Ogerpon,
Okidogi, Palkia, Raikou, Rayquaza, Regice, Regidrago, Regieleki, Regigigas, Regirock,
Registeel, Reshiram, Silvally, Solgaleo, Spectrier, Suicune, Tapu Bulu, Tapu Fini,
Tapu Koko, Tapu Lele, Terapagos, Terrakion, Thundurus, Ting-Lu, Tornadus, Urshifu,
Uxie, Virizion, Wo-Chien, Xerneas, Yveltal, Zacian, Zamazenta, Zapdos, Zekrom, Zygarde

#### Míticos (23)
Arceus, Celebi, Darkrai, Deoxys, Diancie, Genesect, Hoopa, Jirachi, Keldeo, Magearna,
Manaphy, Marshadow, Melmetal, Meloetta, Meltan, Mew, Pecharunt, Phione, Shaymin,
Victini, Volcanion, Zarude, Zeraora

#### Pseudo-legendarios — **PERMITIDOS**
Dragonite, Tyranitar, Salamence, Metagross, Garchomp, Hydreigon, Goodra, Kommo-o,
Dragapult, Baxcalibur, Archaludon, Slaking y formas paradoja (BST ≥ 580) pueden
aparecer en el equipo de torneo.

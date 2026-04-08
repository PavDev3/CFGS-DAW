package LigaPokemon;

/*
 * ENUMERADO (enum)
 * ─────────────────
 * Un enum es un tipo especial que define un conjunto FIJO de constantes con nombre.
 * Se usa cuando un atributo solo puede tomar valores concretos y conocidos de antemano.
 *
 * Ventaja frente a usar String: el compilador detecta errores si escribes mal el valor.
 * Ejemplo: Region.KANTO es válido; Region.KANTA daría error de compilación.
 */
public enum Region {
    KANTO,
    JOHTO,
    HOENN,
    SINNOH
}

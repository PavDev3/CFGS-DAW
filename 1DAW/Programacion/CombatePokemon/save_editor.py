#!/usr/bin/env python3
"""
Pokemon Anil - Editor de Partida Guardada
Soporta Pokemon Essentials v21+ (Ruby Marshal 4.8)
"""
import struct
import io
import copy
import os
import shutil
from datetime import datetime

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
SAVES_IN_DIR  = os.path.join(SCRIPT_DIR, 'saves')
SAVES_OUT_DIR = os.path.join(SCRIPT_DIR, 'savesGen')

# ─── Ruby Marshal Parser ────────────────────────────────────────────────────

class RubyHash:
    """Hash de Ruby que soporta cualquier tipo de clave."""
    def __init__(self):
        self.pairs = []  # lista de [key, value]
        self._default = None

    def __setitem__(self, key, value):
        for pair in self.pairs:
            if pair[0] == key:
                pair[1] = value
                return
        self.pairs.append([key, value])

    def __getitem__(self, key):
        for pair in self.pairs:
            if pair[0] == key:
                return pair[1]
        raise KeyError(key)

    def __contains__(self, key):
        return any(pair[0] == key for pair in self.pairs)

    def get(self, key, default=None):
        for pair in self.pairs:
            if pair[0] == key:
                return pair[1]
        return default

    def keys(self):
        return [p[0] for p in self.pairs]

    def values(self):
        return [p[1] for p in self.pairs]

    def items(self):
        return [(p[0], p[1]) for p in self.pairs]

    def __len__(self):
        return len(self.pairs)

    def __repr__(self):
        return f"RubyHash({self.pairs!r})"


class RubyObject:
    def __init__(self, class_name, attributes=None):
        self.class_name = class_name
        self.attributes = attributes or {}
        self._ruby_type = 'o'       # 'o', 'u', 'U', 'C', 'S'
        self._ivar_wrapped = False  # True if originally wrapped in 'I'
        self._ivars = []            # [(key, value)] pairs from the 'I' wrapper

    def __repr__(self):
        return f"<{self.class_name} {self.attributes}>"

    def get(self, key, default=None):
        return self.attributes.get(f"@{key}", self.attributes.get(key, default))

    def set(self, key, value):
        k = f"@{key}" if not key.startswith("@") else key
        self.attributes[k] = value


class RubySymbol(str):
    pass


class _EncodedString(str):
    """str subclass that preserves original raw bytes and IVAR encoding info for round-trip fidelity."""
    __slots__ = ('_raw_bytes', '_ivars')

    def __new__(cls, value, raw_bytes=None, ivars=None):
        inst = super().__new__(cls, value)
        inst._raw_bytes = raw_bytes
        inst._ivars = ivars if ivars is not None else []
        return inst


class _EncodedFloat(float):
    """float subclass that preserves the original Marshal string representation for round-trip fidelity."""
    __slots__ = ('_raw_str',)

    def __new__(cls, value, raw_str=None):
        inst = super().__new__(cls, value)
        inst._raw_str = raw_str
        return inst


class MarshalReader:
    def __init__(self, data: bytes):
        self.buf = io.BytesIO(data)
        self.symbols = []   # symbol table
        self.objects = []   # object table (links)

    def read_byte(self):
        return self.buf.read(1)[0]

    def read_bytes(self, n):
        return self.buf.read(n)

    def read_int(self):
        b = self.read_byte()
        if b == 0:
            return 0
        if b > 0x7F:
            b = b - 256
        if b > 0:
            if b == 1:
                return self.read_byte()
            elif b == 2:
                lo = self.read_byte()
                hi = self.read_byte()
                return lo | (hi << 8)
            elif b == 3:
                lo = self.read_byte()
                mid = self.read_byte()
                hi = self.read_byte()
                return lo | (mid << 8) | (hi << 16)
            elif b == 4:
                lo = self.read_byte()
                m1 = self.read_byte()
                m2 = self.read_byte()
                hi = self.read_byte()
                return lo | (m1 << 8) | (m2 << 16) | (hi << 24)
            else:
                return b - 5
        else:
            if b == -1:
                return self.read_byte() - 256
            elif b == -2:
                lo = self.read_byte()
                hi = self.read_byte()
                v = lo | (hi << 8)
                return v - 65536
            elif b == -3:
                lo = self.read_byte()
                mid = self.read_byte()
                hi = self.read_byte()
                v = lo | (mid << 8) | (hi << 16)
                return v - 16777216
            elif b == -4:
                lo = self.read_byte()
                m1 = self.read_byte()
                m2 = self.read_byte()
                hi = self.read_byte()
                v = lo | (m1 << 8) | (m2 << 16) | (hi << 24)
                if hi >= 128:
                    v = v - (1 << 32)
                return v
            else:
                return b + 5

    def read_string_raw(self):
        length = self.read_int()
        return self.buf.read(length)

    def read_symbol(self):
        length = self.read_int()
        s = self.buf.read(length).decode("utf-8", errors="replace")
        sym = RubySymbol(s)
        self.symbols.append(sym)
        return sym

    def _decode_bytes(self, raw, enc):
        if enc is True:
            try: return raw.decode('utf-8')
            except: return raw.decode('latin-1')
        if isinstance(enc, (bytes, bytearray)):
            try: return raw.decode(enc.decode('ascii'))
            except: return raw.decode('latin-1')
        try: return raw.decode('utf-8')
        except: return raw.decode('latin-1')

    def read(self):
        type_byte = self.read_byte()

        if type_byte == ord('0'):   # nil
            return None
        elif type_byte == ord('T'): # true
            return True
        elif type_byte == ord('F'): # false
            return False
        elif type_byte == ord('i'): # Fixnum (NOT in objects table)
            return self.read_int()
        elif type_byte == ord('l'): # Bignum (IS in objects table)
            v = self.read_bignum()
            self.objects.append(v)
            return v
        elif type_byte == ord('f'): # Float (IS in objects table)
            v = self.read_float()
            self.objects.append(v)
            return v
        elif type_byte == ord('"'): # Binary String
            raw = self.read_string_raw()
            self.objects.append(raw)
            return raw
        elif type_byte == ord(':'): # Symbol
            return self.read_symbol()
        elif type_byte == ord(';'): # Symbol link
            idx = self.read_int()
            return self.symbols[idx]
        elif type_byte == ord('@'): # Object link
            idx = self.read_int()
            return self.objects[idx]
        elif type_byte == ord('['): # Array
            obj = []
            self.objects.append(obj)
            n = self.read_int()
            for _ in range(n):
                obj.append(self.read())
            return obj
        elif type_byte == ord('{'): # Hash
            obj = RubyHash()
            self.objects.append(obj)
            n = self.read_int()
            for _ in range(n):
                k = self.read()
                v = self.read()
                obj[k] = v
            return obj
        elif type_byte == ord('}'): # Hash with default value
            obj = RubyHash()
            self.objects.append(obj)
            n = self.read_int()
            for _ in range(n):
                k = self.read()
                v = self.read()
                obj[k] = v
            obj._default = self.read()
            return obj
        elif type_byte == ord('o'): # Object
            class_sym = self.read()
            obj = RubyObject(str(class_sym))
            self.objects.append(obj)
            n = self.read_int()
            for _ in range(n):
                key = self.read()
                val = self.read()
                obj.attributes[str(key)] = val
            return obj
        elif type_byte == ord('I'): # IVAR — string or regexp with instance vars
            inner_type = self.read_byte()
            if inner_type == ord('"'):
                raw = self.read_string_raw()
                slot = len(self.objects)
                self.objects.append(raw)  # reserve slot
                n = self.read_int()
                enc = None
                all_ivars = []
                for _ in range(n):
                    k = self.read()
                    v = self.read()
                    all_ivars.append((k, v))
                    if str(k) == 'E':
                        enc = v
                decoded = self._decode_bytes(raw, enc)
                result = _EncodedString(decoded, raw, all_ivars)
                self.objects[slot] = result  # update slot to decoded string
                return result
            elif inner_type == ord('/'):
                pattern = self.read_string_raw()
                flags = self.read_byte()
                obj = RubyObject('Regexp')
                obj.attributes['pattern'] = pattern
                obj.attributes['flags'] = flags
                self.objects.append(obj)
                n = self.read_int()
                for _ in range(n):
                    self.read(); self.read()
                return obj
            elif inner_type == ord('u'): # I u — e.g. Time objects
                class_sym = self.read()
                data_len = self.read_int()
                data = self.read_bytes(data_len)
                obj = RubyObject(str(class_sym))
                obj._ruby_type = 'u'
                obj._ivar_wrapped = True
                obj.attributes['__data__'] = data
                self.objects.append(obj)
                n = self.read_int()
                for _ in range(n):
                    k = self.read()
                    v = self.read()
                    obj._ivars.append((k, v))
                return obj
            else:
                # Fallback: treat inner as generic read
                self.buf.seek(self.buf.tell() - 1)
                inner = self.read()
                n = self.read_int()
                for _ in range(n):
                    self.read(); self.read()
                return inner
        elif type_byte == ord('u'): # User-defined (binary blob)
            class_sym = self.read()
            length = self.read_int()
            data = self.read_bytes(length)
            obj = RubyObject(str(class_sym))
            obj._ruby_type = 'u'
            obj.attributes['__data__'] = data
            self.objects.append(obj)
            return obj
        elif type_byte == ord('U'): # User-defined (marshal)
            class_sym = self.read()
            obj = RubyObject(str(class_sym))
            obj._ruby_type = 'U'
            self.objects.append(obj)
            inner = self.read()
            obj.attributes['__marshal__'] = inner
            return obj
        elif type_byte == ord('e'): # Extended with module (transparent to objects table)
            self.read()  # module symbol
            return self.read()
        elif type_byte == ord('C'): # User class (subclass of String/Array/Hash/Regexp)
            class_sym = self.read()
            obj = RubyObject(str(class_sym))
            obj._ruby_type = 'C'
            self.objects.append(obj)  # ONE slot for the whole thing
            inner_type = self.read_byte()
            if inner_type == ord('"'):
                raw = self.read_string_raw()
                obj.attributes['__data__'] = raw
            elif inner_type == ord('['):
                items = []
                n = self.read_int()
                for _ in range(n):
                    items.append(self.read())
                obj.attributes['__data__'] = items
            elif inner_type in (ord('{'), ord('}')):
                h = RubyHash()
                n = self.read_int()
                for _ in range(n):
                    k = self.read()
                    v = self.read()
                    h[k] = v
                obj.attributes['__data__'] = h
            elif inner_type == ord('/'):
                pattern = self.read_string_raw()
                flags = self.read_byte()
                obj.attributes['pattern'] = pattern
                obj.attributes['flags'] = flags
            return obj
        elif type_byte == ord('S'): # Struct
            class_sym = self.read()
            obj = RubyObject(str(class_sym))
            obj._ruby_type = 'S'
            self.objects.append(obj)
            n = self.read_int()
            for _ in range(n):
                k = self.read()
                v = self.read()
                obj.attributes[str(k)] = v
            return obj
        elif type_byte == ord('/'): # Regexp
            pattern = self.read_string_raw()
            flags = self.read_byte()
            obj = RubyObject('Regexp')
            obj.attributes['pattern'] = pattern
            obj.attributes['flags'] = flags
            self.objects.append(obj)
            return obj
        elif type_byte == ord('c'): # Class (IS in objects table)
            length = self.read_int()
            name = self.buf.read(length).decode('utf-8')
            obj = RubyObject(f'Class:{name}')
            self.objects.append(obj)
            return obj
        elif type_byte == ord('m'): # Module (IS in objects table)
            length = self.read_int()
            name = self.buf.read(length).decode('utf-8')
            obj = RubyObject(f'Module:{name}')
            self.objects.append(obj)
            return obj
        elif type_byte == ord('M'): # old Class/Module
            length = self.read_int()
            name = self.buf.read(length).decode('utf-8')
            obj = RubyObject(f'ClassModule:{name}')
            self.objects.append(obj)
            return obj
        elif type_byte == ord('d'): # Data
            class_sym = self.read()
            obj = RubyObject(str(class_sym))
            self.objects.append(obj)
            inner = self.read()
            obj.attributes['__data__'] = inner
            return obj
        else:
            raise ValueError(f"Unknown type byte: 0x{type_byte:02X} at position {self.buf.tell()-1}")

    def read_bignum(self):
        sign = self.read_byte()  # ord('+') or ord('-')
        n = self.read_int()
        digits = self.read_bytes(n * 2)
        value = 0
        for i in range(n * 2 - 1, -1, -1):
            value = (value << 8) | digits[i]
        if sign == ord('-'):
            value = -value
        return value

    def read_float(self):
        raw = self.read_string_raw()
        s = raw.decode('ascii')
        if s in ('inf', 'Inf', 'infinity'):
            return float('inf')
        elif s in ('-inf', '-Inf', '-infinity'):
            return float('-inf')
        elif s in ('nan', 'NaN'):
            return float('nan')
        return _EncodedFloat(float(s), s)


def _ruby_float_str(f):
    """Format a float the way Ruby's Marshal does (shortest round-trip representation)."""
    if f == float('inf'):  return 'inf'
    if f == float('-inf'): return '-inf'
    if f != f:             return 'nan'

    # repr() uses the same shortest-unique algorithm as modern Ruby (Grisu/Ryu)
    s = repr(f)

    # Ruby writes "0" not "0.0" for zero
    if s == '0.0':   return '0'
    if s == '-0.0':  return '-0'

    # Fix exponent format: Python "1.5e+10" → Ruby "1.5e10"; "1.5e-10" stays
    if 'e' in s:
        mantissa, _, exp_raw = s.partition('e')
        sign = '-' if exp_raw.startswith('-') else ''
        digits = exp_raw.lstrip('+-').lstrip('0') or '0'
        return mantissa + 'e' + sign + digits

    return s


class MarshalWriter:
    def __init__(self):
        self.buf = io.BytesIO()
        self.symbols = []
        self.objects = {}  # id(obj) -> index in reader's objects table
        self._idx = 0       # sequential counter matching reader's append order

    def _track(self, obj_id):
        """Assign the next sequential index to this object."""
        self.objects[obj_id] = self._idx
        self._idx += 1

    def write_byte(self, b):
        self.buf.write(bytes([b & 0xFF]))

    def write_bytes(self, b):
        self.buf.write(b)

    def write_int(self, n):
        if n == 0:
            self.write_byte(0)
            return
        if 0 < n < 123:
            self.write_byte(n + 5)
            return
        if -124 < n < 0:
            self.write_byte((n - 5) & 0xFF)
            return
        if 0 <= n <= 0xFF:
            self.write_byte(1); self.write_byte(n); return
        if 0 <= n <= 0xFFFF:
            self.write_byte(2); self.write_byte(n & 0xFF); self.write_byte((n >> 8) & 0xFF); return
        if 0 <= n <= 0xFFFFFF:
            self.write_byte(3); self.write_byte(n & 0xFF); self.write_byte((n >> 8) & 0xFF); self.write_byte((n >> 16) & 0xFF); return
        if -0xFF <= n < 0:
            self.write_byte(0xFF); self.write_byte(n & 0xFF); return
        if -0xFFFF <= n < 0:
            self.write_byte(0xFE); self.write_byte(n & 0xFF); self.write_byte((n >> 8) & 0xFF); return
        if -0xFFFFFF <= n < 0:
            self.write_byte(0xFD); self.write_byte(n & 0xFF); self.write_byte((n >> 8) & 0xFF); self.write_byte((n >> 16) & 0xFF); return
        self.write_byte(4 if n > 0 else 0xFC)
        self.write_byte(n & 0xFF); self.write_byte((n >> 8) & 0xFF)
        self.write_byte((n >> 16) & 0xFF); self.write_byte((n >> 24) & 0xFF)

    def write(self, obj):
        # ── Untracked immediate types ──
        if obj is None:
            self.write_byte(ord('0')); return
        if obj is True:
            self.write_byte(ord('T')); return
        if obj is False:
            self.write_byte(ord('F')); return
        if isinstance(obj, bool):
            return  # already handled

        # ── Symbols (own table, not objects table) ──
        if isinstance(obj, RubySymbol):
            if obj in self.symbols:
                self.write_byte(ord(';'))
                self.write_int(self.symbols.index(obj))
            else:
                self.write_byte(ord(':'))
                encoded = obj.encode('utf-8')
                self.write_int(len(encoded))
                self.write_bytes(encoded)
                self.symbols.append(obj)
            return

        # ── Fixnums (not tracked in objects table) ──
        if isinstance(obj, int) and -0x40000000 <= obj <= 0x3FFFFFFF:
            self.write_byte(ord('i'))
            self.write_int(obj)
            return

        # ── All remaining types ARE tracked — check for already-written link ──
        obj_id = id(obj)
        if obj_id in self.objects:
            self.write_byte(ord('@'))
            self.write_int(self.objects[obj_id])
            return

        # ── Bignum (tracked) ──
        if isinstance(obj, int):
            self._track(obj_id)
            self._write_bignum_data(obj)
            return

        # ── Float (tracked) ──
        if isinstance(obj, float):
            self._track(obj_id)
            self._write_float_data(obj)
            return

        # ── String → I"..." (tracked) ──
        if isinstance(obj, str):
            if isinstance(obj, _EncodedString) and obj._raw_bytes is not None:
                # Preserve original encoding exactly (Latin-1, Windows-1252, etc.)
                self.write_byte(ord('I'))
                self.write_byte(ord('"'))
                raw = obj._raw_bytes
                self.write_int(len(raw))
                self.write_bytes(raw)
                self._track(obj_id)
                self.write_int(len(obj._ivars))
                for k, v in obj._ivars:
                    self.write(k)
                    self.write(v)
            else:
                # New string (set by editor): write as UTF-8
                self.write_byte(ord('I'))
                self.write_byte(ord('"'))
                encoded = obj.encode('utf-8')
                self.write_int(len(encoded))
                self.write_bytes(encoded)
                self._track(obj_id)
                self.write_int(1)
                self.write(RubySymbol('E'))
                self.write(True)
            return

        # ── Bytes → raw string (tracked) ──
        if isinstance(obj, bytes):
            self.write_byte(ord('"'))
            self.write_int(len(obj))
            self.write_bytes(obj)
            self._track(obj_id)
            return

        # ── List (tracked before children) ──
        if isinstance(obj, list):
            self.write_byte(ord('['))
            self._track(obj_id)
            self.write_int(len(obj))
            for item in obj:
                self.write(item)
            return

        # ── Hash / RubyHash (tracked before children) ──
        if isinstance(obj, (dict, RubyHash)):
            has_default = isinstance(obj, RubyHash) and obj._default is not None
            self.write_byte(ord('}') if has_default else ord('{'))
            self._track(obj_id)
            self.write_int(len(obj))
            for k, v in obj.items():
                self.write(k)
                self.write(v)
            if has_default:
                self.write(obj._default)
            return

        # ── RubyObject (tracked before attributes) ──
        if isinstance(obj, RubyObject):
            rtype = getattr(obj, '_ruby_type', 'o')
            ivar_wrapped = getattr(obj, '_ivar_wrapped', False)
            ivars = getattr(obj, '_ivars', [])

            if rtype == 'u':
                data = obj.attributes.get('__data__', b'')
                if ivar_wrapped:
                    self.write_byte(ord('I'))
                self.write_byte(ord('u'))
                self.write(RubySymbol(obj.class_name))
                self._track(obj_id)
                self.write_int(len(data))
                self.write_bytes(data)
                if ivar_wrapped:
                    self.write_int(len(ivars))
                    for k, v in ivars:
                        self.write(k)
                        self.write(v)
            elif rtype == 'U':
                self.write_byte(ord('U'))
                self.write(RubySymbol(obj.class_name))
                self._track(obj_id)
                self.write(obj.attributes.get('__marshal__'))
            elif rtype == 'C':
                self.write_byte(ord('C'))
                self.write(RubySymbol(obj.class_name))
                self._track(obj_id)
                inner = obj.attributes.get('__data__')
                if isinstance(inner, bytes):
                    self.write_byte(ord('"'))
                    self.write_int(len(inner))
                    self.write_bytes(inner)
                elif isinstance(inner, list):
                    self.write_byte(ord('['))
                    self.write_int(len(inner))
                    for item in inner:
                        self.write(item)
                elif isinstance(inner, (RubyHash, dict)):
                    self.write_byte(ord('{'))
                    self.write_int(len(inner))
                    for k, v in inner.items():
                        self.write(k)
                        self.write(v)
            elif rtype == 'S':
                self.write_byte(ord('S'))
                self.write(RubySymbol(obj.class_name))
                self._track(obj_id)
                self.write_int(len(obj.attributes))
                for k, v in obj.attributes.items():
                    self.write(RubySymbol(k))
                    self.write(v)
            else:  # 'o' — generic object
                self.write_byte(ord('o'))
                self.write(RubySymbol(obj.class_name))
                self._track(obj_id)
                self.write_int(len(obj.attributes))
                for k, v in obj.attributes.items():
                    self.write(RubySymbol(k))
                    self.write(v)
            return

        raise TypeError(f"Cannot serialize {type(obj)}: {obj!r}")

    def _write_bignum_data(self, n):
        self.write_byte(ord('l'))
        if n >= 0:
            self.write_byte(ord('+'))
        else:
            self.write_byte(ord('-'))
            n = -n
        digits = []
        tmp = n
        while tmp:
            digits.append(tmp & 0xFF)
            tmp >>= 8
        if len(digits) % 2:
            digits.append(0)
        self.write_int(len(digits) // 2)
        self.write_bytes(bytes(digits))

    def _write_float_data(self, f):
        self.write_byte(ord('f'))
        if isinstance(f, _EncodedFloat) and f._raw_str is not None:
            s = f._raw_str.encode('ascii')
        else:
            s = _ruby_float_str(f).encode('ascii')
        self.write_int(len(s))
        self.write_bytes(s)

    def get_bytes(self):
        return b'\x04\x08' + self.buf.getvalue()


def load_save(path):
    with open(path, 'rb') as f:
        data = f.read()
    assert data[:2] == b'\x04\x08', "No es un archivo Ruby Marshal válido"
    reader = MarshalReader(data[2:])
    return reader.read()


def dump_save(obj, path):
    writer = MarshalWriter()
    writer.write(obj)
    return writer.get_bytes()


# ─── Helper para navegar los datos ──────────────────────────────────────────

def get_attr(obj, key):
    """Obtiene atributo de RubyObject (con o sin @)."""
    if isinstance(obj, RubyObject):
        for prefix in ('', '@'):
            full = prefix + key if not key.startswith('@') else key
            if full in obj.attributes:
                return obj.attributes[full]
    return None

def set_attr(obj, key, value):
    """Fija atributo de RubyObject."""
    if isinstance(obj, RubyObject):
        full = key if key.startswith('@') else f'@{key}'
        obj.attributes[full] = value

def find_key(d, *keys):
    """Busca una clave en RubyHash o dict, probando variantes."""
    for k in keys:
        sym = RubySymbol(k)
        if sym in d:
            return d[sym]
        if k in d:
            return d[k]
    return None

def find_player(save_data):
    if isinstance(save_data, (dict, RubyHash)):
        for k in ('player', 'trainer'):
            v = find_key(save_data, k)
            if v is not None:
                return v
    return None

def find_bag(save_data):
    if isinstance(save_data, (dict, RubyHash)):
        for k in ('bag', 'storage', 'PokemonBag'):
            v = find_key(save_data, k)
            if v is not None:
                return v
    return None

def find_party(save_data):
    # In PE v21+, party is inside the player object
    player = find_player(save_data)
    if player:
        party = get_attr(player, 'party')
        if isinstance(party, list):
            return party
    # fallback: top-level key
    if isinstance(save_data, (dict, RubyHash)):
        for k in ('party', 'pokemon', 'Pokemon'):
            v = find_key(save_data, k)
            if isinstance(v, list):
                return v
    return None


# ─── Mostrar estado actual ───────────────────────────────────────────────────

def show_player_info(player):
    print("\n=== JUGADOR ===")
    name = get_attr(player, 'name') or get_attr(player, 'playername') or '?'
    money = get_attr(player, 'money') or 0
    print(f"  Nombre : {name}")
    print(f"  Dinero : ${money:,}")
    badges = get_attr(player, 'badges')
    if badges:
        if isinstance(badges, list):
            earned = sum(1 for b in badges if b)
            print(f"  Medallas: {earned}/{len(badges)}")
        elif isinstance(badges, RubyObject):
            data = badges.attributes.get('@badges') or badges.attributes.get('__data__')
            print(f"  Medallas: {data}")
    # mostrar todos los atributos simples
    print("  [Atributos disponibles]")
    if isinstance(player, RubyObject):
        for k, v in player.attributes.items():
            if isinstance(v, (int, float, str, bool)) or v is None:
                print(f"    {k} = {v!r}")


def show_party(party):
    print("\n=== EQUIPO POKEMON ===")
    if not party:
        print("  (vacío)")
        return
    for i, poke in enumerate(party):
        if poke is None:
            continue
        if isinstance(poke, RubyObject):
            species  = get_attr(poke, 'species') or '?'
            level    = get_attr(poke, 'level') or '?'
            hp       = get_attr(poke, 'hp') or '?'
            totalhp  = get_attr(poke, 'totalhp') or '?'
            nick     = get_attr(poke, 'name') or ''
            nature   = get_attr(poke, 'nature') or ''
            shiny    = '*' if get_attr(poke, 'shiny') else ''
            moves    = get_attr(poke, 'moves') or []
            move_names = [get_attr(m, 'id') or '?' for m in moves[:4] if isinstance(m, RubyObject)]
            iv = get_attr(poke, 'iv') or RubyHash()
            ev = get_attr(poke, 'ev') or RubyHash()
            print(f"  [{i}] {shiny}{species}  Nv.{level}  HP:{hp}/{totalhp}  {nature}  {nick}")
            print(f"       Movimientos: {', '.join(move_names)}")
            if isinstance(iv, RubyHash):
                iv_vals = '/'.join(str(iv.get(s, 0)) for s in ('HP','ATTACK','DEFENSE','SPECIAL_ATTACK','SPECIAL_DEFENSE','SPEED'))
                print(f"       IVs: {iv_vals}")
            if isinstance(ev, RubyHash):
                ev_vals = '/'.join(str(ev.get(s, 0)) for s in ('HP','ATTACK','DEFENSE','SPECIAL_ATTACK','SPECIAL_DEFENSE','SPEED'))
                print(f"       EVs: {ev_vals}")
        else:
            print(f"  [{i}] {poke!r}")


def show_bag(bag):
    print("\n=== BOLSA (items) ===")
    if bag is None:
        print("  (no encontrada)")
        return
    if isinstance(bag, RubyObject):
        for k, v in bag.attributes.items():
            if isinstance(v, list) and v:
                print(f"  {k}: {len(v)} entradas")
                for entry in v[:5]:
                    print(f"    {entry!r}")
                if len(v) > 5:
                    print(f"    ... ({len(v)-5} más)")
            elif isinstance(v, dict):
                print(f"  {k}: dict con {len(v)} ítems")


# ─── Edición ─────────────────────────────────────────────────────────────────

def edit_money(player, amount):
    set_attr(player, 'money', amount)
    print(f"  [OK] Dinero fijado a ${amount:,}")

ITEM_POCKET_MAP = {
    # pocket 1 = items de batalla/uso general
    1: ['POTION', 'SUPERPOTION', 'HYPERPOTION', 'MAXPOTION', 'FULLRESTORE', 'REVIVE', 'MAXREVIVE',
        'ANTIDOTE', 'BURNHEAL', 'ICEHEAL', 'AWAKENING', 'PARALYZEHEAL', 'FULLHEAL',
        'ETHER', 'MAXETHER', 'ELIXIR', 'MAXELIXIR', 'REPEL', 'SUPERREPEL', 'MAXREPEL',
        'ESCAPEROPE', 'RARECANDY', 'PPUP', 'PPMAX', 'SACREDASH', 'FULLINCENSE',
        'RAREBONE', 'SHOALSALT', 'SHOALSHELL', 'SUNSTONE', 'MOONSTONE', 'FIRESTONE',
        'THUNDERSTONE', 'WATERSTONE', 'LEAFSTONE', 'OVALSTONE', 'SHINYSTONE', 'DUSKSTONE',
        'DAWNSTONE', 'ICESTONE', 'PRISMSCALE', 'DEEPSEATOOTH', 'DEEPSEASCALE',
        'KINGSROCK', 'METALCOAT', 'UPGRADE', 'DUBIOUS_DISC', 'ELECTIRIZER', 'MAGMARIZER',
        'PROTECTOR', 'REAPERCLOTH', 'SACHET', 'WHIPPEDDREAM',
    ],
    2: ['MASTERBALL', 'ULTRABALL', 'GREATBALL', 'POKEBALL', 'PREMIERBALL', 'REPEATBALL',
        'TIMERBALL', 'NESTBALL', 'NETBALL', 'DIVEBALL', 'LUREBALL', 'HEALBALL', 'QUICKBALL',
        'DUSKBALL', 'CHERISHBALL', 'FASTBALL', 'FRIENDBALL', 'HEAVYBALL', 'LEVELBALL',
        'LOVEBALL', 'LUXURYBALL', 'MOONBALL', 'SAFARIBALL', 'SPORTBALL',
    ],
    # Add more as needed
}

def edit_items(bag, item_name, quantity):
    """Agrega o modifica un ítem en la bolsa. Pocket 1 = items, pocket 2 = pokeballs."""
    if bag is None:
        print("  [ERR] No se encontró la bolsa")
        return
    item_key = item_name.upper()
    if not isinstance(bag, RubyObject):
        print("  [ERR] Formato de bolsa no reconocido")
        return

    pockets = bag.attributes.get('@pockets')
    if not isinstance(pockets, list):
        print("  [ERR] No se encontraron los pockets de la bolsa")
        return

    # Search in all pockets first
    for pocket in pockets:
        if not isinstance(pocket, list):
            continue
        for entry in pocket:
            if isinstance(entry, list) and len(entry) >= 2:
                if str(entry[0]).upper() == item_key or entry[0] == RubySymbol(item_key):
                    entry[1] = quantity
                    print(f"  [OK] {item_name} actualizado a x{quantity}")
                    return

    # Not found - determine pocket (1 for balls, else pocket 0)
    pocket_idx = 1  # default: general items pocket
    for pi, names in ITEM_POCKET_MAP.items():
        if item_key in names:
            pocket_idx = pi
            break

    item_sym = RubySymbol(item_key)
    if pocket_idx < len(pockets) and isinstance(pockets[pocket_idx], list):
        pockets[pocket_idx].append([item_sym, quantity])
        print(f"  [OK] {item_name} x{quantity} agregado al pocket {pocket_idx}")
    else:
        for p in pockets:
            if isinstance(p, list):
                p.append([item_sym, quantity])
                print(f"  [OK] {item_name} x{quantity} agregado")
                return
        print(f"  [ERR] No se pudo agregar {item_name}")

def edit_pokemon_level(party, index, new_level):
    if index >= len(party):
        print(f"  [ERR] No hay Pokémon en la posición {index}")
        return
    poke = party[index]
    if isinstance(poke, RubyObject):
        set_attr(poke, 'level', new_level)
        # Actualizar exp también si existe
        print(f"  [OK] Nivel del Pokémon [{index}] fijado a {new_level}")

def edit_badges(player, num_badges):
    badges = get_attr(player, 'badges')
    if isinstance(badges, list):
        for i in range(len(badges)):
            badges[i] = i < num_badges
        print(f"  [OK] {num_badges} medallas activadas")
    else:
        print(f"  Badges: {badges!r}")
        new_badges = [i < num_badges for i in range(8)]
        set_attr(player, 'badges', new_badges)
        print(f"  [OK] {num_badges} medallas activadas")


# ─── PBS Parser y creación de Pokémon ────────────────────────────────────────

PBS_PATH = os.path.join(SCRIPT_DIR, 'PBS', 'pokemon.txt')
MOVES_PBS_PATH = os.path.join(SCRIPT_DIR, 'PBS', 'moves.txt')

# PBS stat order: HP, ATK, DEF, SPD, SPATK, SPDEF
_PBS_STAT_ORDER = ['HP', 'ATTACK', 'DEFENSE', 'SPEED', 'SPECIAL_ATTACK', 'SPECIAL_DEFENSE']

_PBS_CACHE = {}

def _load_pbs(path):
    if path in _PBS_CACHE:
        return _PBS_CACHE[path]
    data = {}
    current_key = None
    current = {}
    try:
        with open(path, encoding='utf-8', errors='replace') as f:
            for line in f:
                line = line.strip()
                if not line or line.startswith('#'):
                    continue
                if line.startswith('[') and line.endswith(']'):
                    if current_key:
                        data[current_key] = current
                    current_key = line[1:-1].upper()
                    current = {}
                elif '=' in line and current_key:
                    k, _, v = line.partition('=')
                    current[k.strip()] = v.strip()
        if current_key:
            data[current_key] = current
    except FileNotFoundError:
        pass
    _PBS_CACHE[path] = data
    return data

def get_all_species():
    """Lista todas las especies disponibles en el PBS."""
    db = _load_pbs(PBS_PATH)
    return sorted(db.keys())

def get_pokemon_data(species):
    db = _load_pbs(PBS_PATH)
    return db.get(species.upper())

def get_move_pp(move_id):
    """Retorna el PP base de un movimiento."""
    db = _load_pbs(MOVES_PBS_PATH)
    entry = db.get(move_id.upper(), {})
    try:
        return int(entry.get('TotalPP', entry.get('PP', '20')))
    except ValueError:
        return 20

# Natures: (boosted_stat, reduced_stat) usando claves de @iv/@ev
_NATURES = {
    'HARDY': (None, None), 'DOCILE': (None, None), 'BASHFUL': (None, None),
    'QUIRKY': (None, None), 'SERIOUS': (None, None),
    'LONELY': ('ATTACK', 'DEFENSE'), 'BRAVE': ('ATTACK', 'SPEED'),
    'ADAMANT': ('ATTACK', 'SPECIAL_ATTACK'), 'NAUGHTY': ('ATTACK', 'SPECIAL_DEFENSE'),
    'BOLD': ('DEFENSE', 'ATTACK'), 'RELAXED': ('DEFENSE', 'SPEED'),
    'IMPISH': ('DEFENSE', 'SPECIAL_ATTACK'), 'LAX': ('DEFENSE', 'SPECIAL_DEFENSE'),
    'TIMID': ('SPEED', 'ATTACK'), 'HASTY': ('SPEED', 'DEFENSE'),
    'JOLLY': ('SPEED', 'SPECIAL_ATTACK'), 'NAIVE': ('SPEED', 'SPECIAL_DEFENSE'),
    'MODEST': ('SPECIAL_ATTACK', 'ATTACK'), 'MILD': ('SPECIAL_ATTACK', 'DEFENSE'),
    'QUIET': ('SPECIAL_ATTACK', 'SPEED'), 'RASH': ('SPECIAL_ATTACK', 'SPECIAL_DEFENSE'),
    'CALM': ('SPECIAL_DEFENSE', 'ATTACK'), 'GENTLE': ('SPECIAL_DEFENSE', 'DEFENSE'),
    'SASSY': ('SPECIAL_DEFENSE', 'SPEED'), 'CAREFUL': ('SPECIAL_DEFENSE', 'SPECIAL_ATTACK'),
}

def calc_stat(base, level, iv=31, ev=0, nature_mod=1.0, is_hp=False):
    ev_contribution = ev // 4
    if is_hp:
        return (2 * base + iv + ev_contribution) * level // 100 + level + 10
    return int(((2 * base + iv + ev_contribution) * level // 100 + 5) * nature_mod)

def calc_exp(growth_rate, level):
    n = level
    gr = growth_rate.upper()
    if gr in ('FAST',):
        return 4 * n**3 // 5
    elif gr in ('MEDIUM', 'MEDIUMFAST'):
        return n**3
    elif gr in ('PARABOLIC', 'MEDIUMSLOW'):
        v = 6 * n**3 // 5 - 15 * n**2 + 100 * n - 140
        return max(0, v)
    elif gr in ('SLOW',):
        return 5 * n**3 // 4
    else:
        return n**3  # fallback

def get_level_moves(species_data, level):
    """Retorna hasta 4 movimientos aprendibles al llegar al nivel dado."""
    moves_str = species_data.get('Moves', '')
    if not moves_str:
        return []
    parts = [p.strip() for p in moves_str.split(',')]
    learned = []
    i = 0
    while i + 1 < len(parts):
        try:
            lv = int(parts[i])
            mv = parts[i+1].strip().upper()
            if lv <= level:
                if mv in learned:
                    learned.remove(mv)
                learned.append(mv)
            i += 2
        except (ValueError, IndexError):
            i += 1
    return learned[-4:]  # keep last 4

def make_move(move_id):
    m = RubyObject('Pokemon::Move')
    pp = get_move_pp(move_id)
    m.attributes['@id']   = RubySymbol(move_id.upper())
    m.attributes['@ppup'] = 0
    m.attributes['@pp']   = pp
    return m

def make_pokemon(species, level, nature='HARDY', shiny=False, ivs_all31=True,
                 player_obj=None, ability=None, move_ids=None, evs=None):
    """Crea un objeto Pokemon completo listo para agregar al equipo."""
    import random, time

    species = species.upper()
    nature  = nature.upper()
    pdata   = get_pokemon_data(species)

    if pdata is None:
        raise ValueError(f"Especie '{species}' no encontrada en PBS")
    if nature not in _NATURES:
        raise ValueError(f"Naturaleza '{nature}' inválida")

    # Base stats (PBS order: HP, ATK, DEF, SPD, SPATK, SPDEF)
    raw_stats = [int(x.strip()) for x in pdata.get('BaseStats', '50,50,50,50,50,50').split(',')]
    if len(raw_stats) < 6:
        raw_stats += [50] * (6 - len(raw_stats))
    base = dict(zip(_PBS_STAT_ORDER, raw_stats))

    # IVs — keys must be RubySymbol (Ruby serializes them as :HP etc.)
    iv_hash = RubyHash()
    for stat in _PBS_STAT_ORDER:
        iv_hash[RubySymbol(stat)] = 31 if ivs_all31 else random.randint(0, 31)

    # EVs — usar los proporcionados o todos en 0
    if evs is not None:
        ev_hash = evs
    else:
        ev_hash = RubyHash()
        for stat in _PBS_STAT_ORDER:
            ev_hash[RubySymbol(stat)] = 0

    # Nature modifier
    boost, reduce = _NATURES[nature]
    def nat_mod(stat):
        if stat == boost:   return 1.1
        if stat == reduce:  return 0.9
        return 1.0

    # Calculate stats (including EVs)
    def ev(stat): return ev_hash.get(RubySymbol(stat), ev_hash.get(stat, 0))
    hp    = calc_stat(base['HP'],              level, iv_hash[RubySymbol('HP')],              ev('HP'),              1.0, is_hp=True)
    atk   = calc_stat(base['ATTACK'],          level, iv_hash[RubySymbol('ATTACK')],          ev('ATTACK'),          nat_mod('ATTACK'))
    def_  = calc_stat(base['DEFENSE'],         level, iv_hash[RubySymbol('DEFENSE')],         ev('DEFENSE'),         nat_mod('DEFENSE'))
    spatk = calc_stat(base['SPECIAL_ATTACK'],  level, iv_hash[RubySymbol('SPECIAL_ATTACK')],  ev('SPECIAL_ATTACK'),  nat_mod('SPECIAL_ATTACK'))
    spdef = calc_stat(base['SPECIAL_DEFENSE'], level, iv_hash[RubySymbol('SPECIAL_DEFENSE')], ev('SPECIAL_DEFENSE'), nat_mod('SPECIAL_DEFENSE'))
    speed = calc_stat(base['SPEED'],           level, iv_hash[RubySymbol('SPEED')],           ev('SPEED'),           nat_mod('SPEED'))

    # Exp
    growth = pdata.get('GrowthRate', 'Medium')
    exp = calc_exp(growth, level)

    # Moves — usar los proporcionados o los del nivel
    if move_ids is None:
        move_ids = get_level_moves(pdata, level)
        if not move_ids:
            move_ids = ['TACKLE']
    moves_list = [make_move(mid) for mid in move_ids]

    # Habilidad — usar la proporcionada o la primera del PBS
    if ability:
        ability_sym = RubySymbol(ability.strip().upper())
    else:
        abilities = pdata.get('Abilities', '').split(',')
        ability_sym = RubySymbol(abilities[0].strip().upper()) if abilities and abilities[0].strip() else None

    # Owner
    if player_obj:
        owner = RubyObject('Pokemon::Owner')
        owner.attributes['@id']       = get_attr(player_obj, 'id') or 0
        owner.attributes['@name']     = get_attr(player_obj, 'name') or 'Player'
        owner.attributes['@gender']   = get_attr(player_obj, 'gender') or 0
        owner.attributes['@language'] = get_attr(player_obj, 'language') or 2
    else:
        owner = RubyObject('Pokemon::Owner')
        owner.attributes['@id']       = 0
        owner.attributes['@name']     = 'Player'
        owner.attributes['@gender']   = 0
        owner.attributes['@language'] = 2

    legacy = RubyHash()
    for k in ('party_time','item_count','move_count','egg_count','trade_count',
              'defeated_count','fainted_count','supereff_count','critical_count',
              'retreat_count','trainer_count','leader_count','legend_count',
              'champion_count','loss_count'):
        legacy[RubySymbol(k)] = 0

    poke = RubyObject('Pokemon')
    a = poke.attributes
    a['@randomized']       = False
    a['@species']          = RubySymbol(species)
    a['@form']             = 0
    a['@forced_form']      = None
    a['@time_form_set']    = None
    a['@exp']              = exp
    a['@level']            = level
    a['@steps_to_hatch']   = 0
    a['@status']           = RubySymbol('NONE')
    a['@statusCount']      = 0
    a['@gender']           = 0
    a['@shiny']            = shiny
    a['@ability_index']    = None
    a['@ability']          = ability_sym
    a['@nature']           = RubySymbol(nature)
    a['@nature_for_stats'] = None
    a['@item']             = None
    a['@mail']             = None
    a['@moves']            = moves_list
    a['@first_moves']      = [RubySymbol(m) for m in move_ids]
    a['@ribbons']          = []
    a['@cool']             = 0
    a['@beauty']           = 0
    a['@cute']             = 0
    a['@smart']            = 0
    a['@tough']            = 0
    a['@sheen']            = 0
    a['@pokerus']          = 0
    a['@name']             = None
    a['@happiness']        = int(pdata.get('Happiness', '70'))
    a['@poke_ball']        = RubySymbol('POKEBALL')
    a['@markings']         = []
    a['@iv']               = iv_hash
    a['@ivMaxed']          = RubyHash()
    a['@ev']               = ev_hash
    a['@evo_move_count']   = RubyHash()
    a['@evo_crest_count']  = RubyHash()
    a['@evo_recoil_count'] = 0
    a['@evo_step_count']   = 0
    a['@owner']            = owner
    a['@obtain_method']    = 0
    a['@obtain_map']       = 0
    a['@obtain_text']      = None
    a['@obtain_level']     = level
    a['@hatched_map']      = 0
    a['@timeReceived']     = int(time.time())
    a['@timeEggHatched']   = None
    a['@fused']            = None
    a['@personalID']       = random.randint(0, 0xFFFFFFFF)
    a['@hp']               = hp
    a['@totalhp']          = hp
    a['@attack']           = atk
    a['@defense']          = def_
    a['@spatk']            = spatk
    a['@spdef']            = spdef
    a['@speed']            = speed
    a['@expshare']         = True
    a['@shiny_leaf']       = 0
    a['@last_update_time'] = 0.0
    a['@legacy_data']      = legacy
    a['@super_shiny']      = False

    return poke


def add_pokemon_to_party(party, player, species, level, nature='HARDY',
                         shiny=False, ivs_all31=True):
    if len(party) >= 6:
        print("  [ERR] El equipo ya tiene 6 Pokémon")
        return False
    try:
        poke = make_pokemon(species, level, nature, shiny, ivs_all31, player)
    except ValueError as e:
        print(f"  [ERR] {e}")
        return False
    party.append(poke)
    s = '*SHINY* ' if shiny else ''
    sp = get_attr(poke, 'species')
    lv = get_attr(poke, 'level')
    hp = get_attr(poke, 'totalhp')
    print(f"  [OK] {s}{sp} Nv.{lv} HP:{hp} agregado al equipo ({len(party)}/6)")
    return True


def randomize_pokemon_stats(poke, player=None):
    """Randomiza IVs, naturaleza, habilidad y recalcula stats de un Pokémon."""
    import random
    if not isinstance(poke, RubyObject):
        return

    species  = str(get_attr(poke, 'species') or '')
    level    = get_attr(poke, 'level') or 50
    pdata    = get_pokemon_data(species)

    # Random nature
    new_nature = RubySymbol(random.choice(list(_NATURES.keys())))
    set_attr(poke, 'nature', new_nature)

    # Random IVs (all between 0-31)
    iv_hash = get_attr(poke, 'iv') or RubyHash()
    for stat in _PBS_STAT_ORDER:
        iv_hash[RubySymbol(stat)] = random.randint(0, 31)
    set_attr(poke, 'iv', iv_hash)

    # Random ability from PBS
    if pdata:
        all_abilities = []
        for key in ('Abilities', 'HiddenAbilities'):
            raw = pdata.get(key, '')
            if raw:
                all_abilities += [a.strip().upper() for a in raw.split(',') if a.strip()]
        if all_abilities:
            new_ability = RubySymbol(random.choice(all_abilities))
            set_attr(poke, 'ability', new_ability)

    # Recalculate stats
    if pdata:
        raw_stats = [int(x.strip()) for x in pdata.get('BaseStats','50,50,50,50,50,50').split(',')]
        if len(raw_stats) < 6:
            raw_stats += [50] * (6 - len(raw_stats))
        base = dict(zip(_PBS_STAT_ORDER, raw_stats))
        boost, reduce = _NATURES[str(new_nature)]
        def nat_mod(stat):
            if stat == boost:   return 1.1
            if stat == reduce:  return 0.9
            return 1.0
        hp    = calc_stat(base['HP'],              level, iv_hash.get('HP',31),              0, 1.0, is_hp=True)
        atk   = calc_stat(base['ATTACK'],          level, iv_hash.get('ATTACK',31),          0, nat_mod('ATTACK'))
        def_  = calc_stat(base['DEFENSE'],         level, iv_hash.get('DEFENSE',31),         0, nat_mod('DEFENSE'))
        spatk = calc_stat(base['SPECIAL_ATTACK'],  level, iv_hash.get('SPECIAL_ATTACK',31),  0, nat_mod('SPECIAL_ATTACK'))
        spdef = calc_stat(base['SPECIAL_DEFENSE'], level, iv_hash.get('SPECIAL_DEFENSE',31), 0, nat_mod('SPECIAL_DEFENSE'))
        speed = calc_stat(base['SPEED'],           level, iv_hash.get('SPEED',31),           0, nat_mod('SPEED'))
        set_attr(poke, 'totalhp', hp);  set_attr(poke, 'hp', hp)
        set_attr(poke, 'attack',  atk); set_attr(poke, 'defense', def_)
        set_attr(poke, 'spatk',   spatk); set_attr(poke, 'spdef', spdef)
        set_attr(poke, 'speed',   speed)

    print(f"  [OK] Stats/habilidad randomizados para {species}")
    print(f"       Naturaleza: {new_nature}  Habilidad: {get_attr(poke,'ability')}")
    iv_h = get_attr(poke, 'iv')
    if isinstance(iv_h, RubyHash):
        vals = '/'.join(str(iv_h.get(s, 0)) for s in ('HP','ATTACK','DEFENSE','SPECIAL_ATTACK','SPECIAL_DEFENSE','SPEED'))
        print(f"       IVs: {vals}")


# ─── Equipo de torneo ────────────────────────────────────────────────────────

def get_eligible_tournament_pokemon():
    """Especies aptas: completamente evolucionadas, sin legendarios ni míticos."""
    db = _load_pbs(PBS_PATH)
    eligible = []
    for species, data in db.items():
        if data.get('Evolutions', '').strip():          # tiene evolución → no es final
            continue
        flags = data.get('Flags', '').lower()
        if 'legendary' in flags or 'mythical' in flags:
            continue
        if not data.get('BaseStats', '').strip():
            continue
        eligible.append(species)
    return eligible

def _tournament_evs(base_dict):
    """252 en stat ofensiva principal + 252 en SPEED + 4 en HP."""
    primary = 'ATTACK' if base_dict.get('ATTACK', 0) >= base_dict.get('SPECIAL_ATTACK', 0) \
              else 'SPECIAL_ATTACK'
    ev_hash = RubyHash()
    for stat in _PBS_STAT_ORDER:
        ev_hash[RubySymbol(stat)] = 0
    ev_hash[RubySymbol(primary)] = 252
    ev_hash[RubySymbol('SPEED')] = 252
    ev_hash[RubySymbol('HP')]    = 4
    return ev_hash

def _tournament_moves(pdata, count=4):
    """Hasta 4 movimientos aleatorios del learnset (nivel-up + tutor)."""
    import random
    moves_db = _load_pbs(MOVES_PBS_PATH)
    pool = []
    for part_key in ('Moves', 'TutorMoves'):
        raw = pdata.get(part_key, '')
        if not raw:
            continue
        parts = [p.strip() for p in raw.split(',')]
        if part_key == 'Moves':
            i = 0
            while i + 1 < len(parts):
                try:
                    int(parts[i])
                    mv = parts[i + 1].upper()
                    if mv in moves_db:
                        pool.append(mv)
                except (ValueError, IndexError):
                    pass
                i += 2
        else:
            for mv in parts:
                mv = mv.upper()
                if mv and mv in moves_db:
                    pool.append(mv)
    pool = list(dict.fromkeys(pool))  # deduplicar
    if not pool:
        return ['TACKLE']
    return random.sample(pool, min(count, len(pool)))

def generate_tournament_team(party, player_obj):
    """Genera un equipo de torneo de 6 Pokémon aleatorios y reemplaza el equipo."""
    import random
    eligible = get_eligible_tournament_pokemon()
    if len(eligible) < 6:
        print(f"[ERR] Solo hay {len(eligible)} especies elegibles (se necesitan 6)")
        return False

    chosen = random.sample(eligible, 6)
    new_party = []
    print()
    for species in chosen:
        pdata = get_pokemon_data(species)
        if not pdata:
            continue
        nature = random.choice(list(_NATURES.keys()))
        all_abilities = [a.strip() for a in pdata.get('Abilities', '').split(',') if a.strip()]
        all_abilities += [a.strip() for a in pdata.get('HiddenAbilities', '').split(',') if a.strip()]
        ability = random.choice(all_abilities) if all_abilities else None
        move_ids = _tournament_moves(pdata, 4)
        raw_stats = [int(x.strip()) for x in pdata.get('BaseStats', '50,50,50,50,50,50').split(',')]
        base = dict(zip(_PBS_STAT_ORDER, raw_stats))
        evs = _tournament_evs(base)
        poke = make_pokemon(species, level=100, nature=nature, ivs_all31=True,
                            ability=ability, move_ids=move_ids, evs=evs,
                            player_obj=player_obj)
        new_party.append(poke)
        print(f"  + {species:<22} {nature:<12} [{ability or '?'}]  {', '.join(move_ids)}")

    party[:] = new_party
    print(f"\n  [OK] Equipo de torneo generado con {len(new_party)} Pokémon")
    return True


def submenus_add_pokemon(party, player):
    """Submenú interactivo para agregar Pokémon."""
    import random

    print("\n  ── AGREGAR POKEMON ──")
    print("  a. Elegir especie manualmente")
    print("  b. Pokemon aleatorio")
    print("  c. Llenar equipo con 5 randoms")
    print("  v. Volver")
    sub = input("  > ").strip().lower()

    if sub == 'v':
        return False

    if sub == 'b' or sub == 'c':
        all_species = get_all_species()
        count = 5 - len(party) if sub == 'c' else 1
        count = max(0, min(count, 6 - len(party)))
        level  = input("  Nivel (Enter=50): ").strip() or '50'
        shiny_s = input("  ¿Shiny? (s/n, Enter=n): ").strip().lower()
        shiny = shiny_s == 's'
        modified = False
        for _ in range(count):
            sp = random.choice(all_species)
            nat = random.choice(list(_NATURES.keys()))
            ok = add_pokemon_to_party(party, player, sp, int(level), nat, shiny, ivs_all31=False)
            if ok:
                modified = True
        return modified

    if sub == 'a':
        species = input("  Especie (ej: PIKACHU): ").strip().upper()
        if not get_pokemon_data(species):
            print(f"  [ERR] Especie '{species}' no encontrada")
            return False
        level   = input("  Nivel (1-100): ").strip()
        print("  Naturalezas: HARDY LONELY BRAVE ADAMANT NAUGHTY BOLD RELAXED IMPISH LAX")
        print("               TIMID HASTY JOLLY NAIVE MODEST MILD QUIET RASH CALM GENTLE SASSY CAREFUL")
        nature  = input("  Naturaleza (Enter=HARDY): ").strip().upper() or 'HARDY'
        shiny_s = input("  ¿Shiny? (s/n): ").strip().lower()
        ivs_s   = input("  IVs: (1=todos 31, 2=aleatorios): ").strip()
        shiny     = shiny_s == 's'
        ivs_all31 = ivs_s != '2'
        try:
            return add_pokemon_to_party(party, player, species, int(level),
                                        nature, shiny, ivs_all31)
        except (ValueError, TypeError) as e:
            print(f"  [ERR] {e}")
            return False

    print("  Opción no válida")
    return False


# ─── Interfaz de usuario ─────────────────────────────────────────────────────

def main():
    import sys
    if hasattr(sys.stdout, 'reconfigure'):
        try:
            sys.stdout.reconfigure(encoding='utf-8')
        except Exception:
            pass
    print("=" * 60)
    print(" POKEMON ANIL - EDITOR DE PARTIDA GUARDADA")
    print("=" * 60)

    # Buscar saves en saves/
    if not os.path.isdir(SAVES_IN_DIR):
        os.makedirs(SAVES_IN_DIR)
    candidates = [f for f in os.listdir(SAVES_IN_DIR) if f.lower().endswith('.rxdata')]

    if not candidates:
        print(f"[ERR] Pon el archivo .rxdata en:\n  {SAVES_IN_DIR}")
        print("Luego vuelve a ejecutar el editor.")
        return

    if len(candidates) == 1:
        save_filename = candidates[0]
    else:
        print("Partidas disponibles:")
        for i, name in enumerate(candidates):
            print(f"  {i+1}. {name}")
        sel = input("Elige número: ").strip()
        try:
            save_filename = candidates[int(sel) - 1]
        except (ValueError, IndexError):
            print("[ERR] Selección inválida.")
            return

    SAVE_PATH = os.path.join(SAVES_IN_DIR, save_filename)
    os.makedirs(SAVES_OUT_DIR, exist_ok=True)
    OUTPUT_PATH = os.path.join(SAVES_OUT_DIR, save_filename)

    print(f"Leyendo  : {SAVE_PATH}")
    print(f"Guardando: {OUTPUT_PATH}")

    print("\nCargando partida...")
    try:
        save_data = load_save(SAVE_PATH)
    except Exception as e:
        print(f"Error al cargar: {e}")
        import traceback; traceback.print_exc()
        return

    print("[OK] Partida cargada correctamente")

    player = find_player(save_data)
    party  = find_party(save_data)
    bag    = find_bag(save_data)

    show_player_info(player)
    show_party(party)
    show_bag(bag)

    print("\n" + "=" * 60)
    print(" ¿QUÉ DESEAS MODIFICAR?")
    print("=" * 60)
    print("  1. Dinero/Pokédólares")
    print("  2. Nivel de un Pokémon del equipo")
    print("  3. Medallas")
    print("  4. Agregar ítem a la bolsa")
    print("  5. Mostrar todos los atributos de un Pokémon")
    print("  6. Modificar atributo específico de un Pokémon")
    print("  7. Agregar Pokémon al equipo  (manual / aleatorio)")
    print("  8. Eliminar Pokémon del equipo")
    print("  9. Randomizar stats/habilidad de un Pokémon")
    print(" 10. Generar equipo de torneo  (6 Pokémon aleatorios, sin legendarios ni pseudo)")
    print("  0. Guardar y salir")
    print("  Q. Salir sin guardar")

    modified = False

    while True:
        choice = input("\n> ").strip()

        if choice == '0':
            if modified:
                save_bytes = dump_save(save_data, OUTPUT_PATH)
                with open(OUTPUT_PATH, 'wb') as f:
                    f.write(save_bytes)
                print(f"[OK] Partida guardada en:")
                print(f"     {OUTPUT_PATH}")
                print(f"Cópiala manualmente a la carpeta de saves del juego.")
            else:
                print("Sin cambios.")
            break

        elif choice.upper() == 'Q':
            print("Saliendo sin guardar.")
            break

        elif choice == '1':
            current = get_attr(player, 'money') if player else 0
            print(f"Dinero actual: ${current:,}")
            amt = input("Nuevo valor (ej: 999999): ").strip()
            try:
                edit_money(player, int(amt))
                modified = True
            except ValueError:
                print("Valor inválido")

        elif choice == '2':
            show_party(party)
            idx = input("Índice del Pokémon (0-5): ").strip()
            lvl = input("Nuevo nivel: ").strip()
            try:
                edit_pokemon_level(party, int(idx), int(lvl))
                modified = True
            except (ValueError, TypeError):
                print("Valor inválido")

        elif choice == '3':
            current_badges = get_attr(player, 'badges')
            earned = sum(1 for b in current_badges if b) if isinstance(current_badges, list) else '?'
            print(f"Medallas actuales: {earned}")
            n = input("Cuántas medallas activar (0-8): ").strip()
            try:
                edit_badges(player, int(n))
                modified = True
            except (ValueError, TypeError):
                print("Valor inválido")

        elif choice == '4':
            item = input("Nombre del ítem (ej: POTION, MASTERBALL): ").strip()
            qty  = input("Cantidad: ").strip()
            try:
                edit_items(bag, item, int(qty))
                modified = True
            except (ValueError, TypeError):
                print("Valor inválido")

        elif choice == '5':
            show_party(party)
            idx = input("Índice del Pokémon: ").strip()
            try:
                poke = party[int(idx)]
                if isinstance(poke, RubyObject):
                    print(f"\nAtributos de Pokémon [{idx}]:")
                    for k, v in poke.attributes.items():
                        print(f"  {k}: {v!r}")
                else:
                    print(f"  {poke!r}")
            except (ValueError, IndexError, TypeError):
                print("Índice inválido")

        elif choice == '6':
            show_party(party)
            idx = input("Índice del Pokémon: ").strip()
            attr = input("Nombre del atributo (sin @, ej: level, hp, totalhp): ").strip()
            val  = input("Nuevo valor (número): ").strip()
            try:
                poke = party[int(idx)]
                set_attr(poke, attr, int(val))
                print(f"  [OK] {attr} = {val}")
                modified = True
            except (ValueError, IndexError, TypeError) as e:
                print(f"Error: {e}")

        elif choice == '7':
            if len(party) >= 6:
                print("  [ERR] El equipo ya está lleno (6/6)")
            else:
                ok = submenus_add_pokemon(party, player)
                if ok:
                    modified = True
                    show_party(party)

        elif choice == '8':
            show_party(party)
            if not party:
                print("  El equipo está vacío")
            else:
                idx = input("  Índice del Pokémon a eliminar: ").strip()
                try:
                    i = int(idx)
                    if 0 <= i < len(party):
                        removed = party.pop(i)
                        sp = get_attr(removed, 'species') if isinstance(removed, RubyObject) else '?'
                        print(f"  [OK] {sp} eliminado del equipo")
                        modified = True
                        show_party(party)
                    else:
                        print("  Índice fuera de rango")
                except (ValueError, TypeError):
                    print("  Valor inválido")

        elif choice == '9':
            show_party(party)
            if not party:
                print("  El equipo está vacío")
            else:
                idx = input("  Índice del Pokémon (o 'todos'): ").strip()
                if idx.lower() == 'todos':
                    for p in party:
                        randomize_pokemon_stats(p, player)
                    modified = True
                    show_party(party)
                else:
                    try:
                        i = int(idx)
                        if 0 <= i < len(party):
                            randomize_pokemon_stats(party[i], player)
                            modified = True
                            show_party(party)
                        else:
                            print("  Índice fuera de rango")
                    except (ValueError, TypeError):
                        print("  Valor inválido")

        elif choice == '10':
            print("\n  ADVERTENCIA: esto reemplazará TODO el equipo actual.")
            confirm = input("  ¿Continuar? (s/n): ").strip().lower()
            if confirm == 's':
                ok = generate_tournament_team(party, player)
                if ok:
                    modified = True
                    show_party(party)

        else:
            print("Opción no válida")


if __name__ == '__main__':
    main()

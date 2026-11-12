// Actividad 3.1.2 (primera parte): versiones "seguras" de las 5 funciones de 3.1.1,
// sustituyendo Math.random() por cryptoRandomFloat().

function cryptoRandomFloat() {
  const buffer = new Uint32Array(1);
  window.crypto.getRandomValues(buffer);
  return buffer[0] / 4294967296;
}

function funcionASegura() {
  const amplitud = 10;
  const enteroBase = Math.floor(cryptoRandomFloat() * (amplitud + 1)) + 10;
  return enteroBase;
}

function funcionBSegura() {
  const amplitud = 10;
  const enteroBase = Math.floor(cryptoRandomFloat() * (amplitud + 1));
  return Number((enteroBase / 10).toFixed(1));
}

function funcionCSegura() {
  const minEscalado = 500;
  const maxEscalado = 1500;
  const enteroBase = Math.floor(cryptoRandomFloat() * (maxEscalado - minEscalado + 1)) + minEscalado;
  return Number((enteroBase / 100).toFixed(2));
}

function funcionDSegura() {
  const amplitud = 5;
  const enteroBase = Math.floor(cryptoRandomFloat() * (amplitud + 1)) + 1;
  return enteroBase;
}

function funcionESegura() {
  const minEscalado = -1000;
  const maxEscalado = 1000;
  const enteroBase = Math.floor(cryptoRandomFloat() * (maxEscalado - minEscalado + 1)) + minEscalado;
  return Number((enteroBase / 1000).toFixed(3));
}

console.log(funcionASegura()); // entero en [10, 20]
console.log(funcionBSegura()); // 1 decimal en [0, 1]
console.log(funcionCSegura()); // 2 decimales en [5, 15]
console.log(funcionDSegura()); // entero en [1, 6]
console.log(funcionESegura()); // 3 decimales en [-1, 1]

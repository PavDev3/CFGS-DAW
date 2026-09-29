console.log("Inicio del script: esta línea se ejecuta con normalidad.");
console.log("Segunda línea: también se ejecuta sin problema.");

// Operación con una variable no declarada previamente
const resultado = variableNoDeclarada + 10;

console.log("Esta línea NUNCA se ejecuta: el intérprete ya se ha detenido en la anterior.");

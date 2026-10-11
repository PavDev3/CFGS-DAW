// Ejercicio 2. Verificación exhaustiva de variables y operadores (Criterio 2.2)

// 1. Demostración de ámbito de bloque e inmutabilidad
var variableGlobal = "Accesible fuera";
{
  let variableLocal = "Aislada en bloque";
  const CONFIG_APP = { version: "1.0.0", entorno: "test" };

  // Mutabilidad de propiedad en objeto declarado con const
  CONFIG_APP.entorno = "produccion";
  console.log("Objeto modificado:", CONFIG_APP);
}

// 2. Comprobaciones con el operador typeof
console.log("typeof 50n:", typeof 50n);             // bigint
console.log("typeof Symbol():", typeof Symbol());   // symbol
console.log("typeof null:", typeof null);           // object (anomalía)
console.log("typeof NaN:", typeof NaN);             // number

// 3. Coerción y comparaciones
console.log("5 == '5':", 5 == "5");                 // true (igualdad abstracta)
console.log("5 === '5':", 5 === "5");               // false (igualdad estricta)
console.log("5 + '5':", 5 + "5");                   // "55" (concatenación)
console.log("5 - '5':", 5 - "5");                   // 0 (conversión numérica)

// 4. Operadores especiales in, delete e instanceof
const alumno = { id: 1001, curso: "2 DAW", activo: true };
console.log("¿Tiene propiedad 'curso'?:", "curso" in alumno); // true
delete alumno.activo;
console.log("Objeto tras borrado:", alumno);
console.log("alumno es instancia de Object:", alumno instanceof Object); // true

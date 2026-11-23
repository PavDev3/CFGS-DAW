// Actividad Práctica 3.1.8: "Gestor de Plazos de Matrícula y Convocatorias con Temporal"
// Requiere un navegador/entorno con soporte nativo de Temporal (o el polyfill temporal-polyfill).

// 1. Definición de fechas base
const fechaPublicacionNotas = Temporal.PlainDate.from({ year: 2027, month: 6, day: 15 });
console.log("Publicación provisional de notas:", fechaPublicacionNotas.toString());

// 2. Cálculo de plazos legales (inmutabilidad)
const finAlegaciones = fechaPublicacionNotas.add({ days: 3 });
console.log("Fin del periodo de alegaciones:", finAlegaciones.toString());

const resolucionDefinitiva = finAlegaciones.add({ weeks: 2 });
console.log("Resolución definitiva:", resolucionDefinitiva.toString());

console.log(
  "¿La fecha original sigue intacta?",
  fechaPublicacionNotas.toString() === "2027-06-15"
); // true: .add() devuelve una instancia nueva, no muta la original

// 3. Auditoría de cuenta atrás
const hoy = Temporal.Now.plainDateISO();
const inicioAlegaciones = fechaPublicacionNotas; // el plazo de alegaciones arranca el día de la publicación
const tiempoRestante = hoy.until(inicioAlegaciones, { largestUnit: "months" });
console.log(
  `Faltan ${tiempoRestante.months} meses y ${tiempoRestante.days} días para el inicio de las alegaciones.`
);

// 4. Sincronización de apertura de sede internacional
// Asunción: la matrícula abre el mismo día de la publicación de notas (no se especifica otra fecha en el enunciado).
const aperturaMadrid = Temporal.ZonedDateTime.from({
  timeZone: "Europe/Madrid",
  year: 2027,
  month: 6,
  day: 15,
  hour: 9,
  minute: 0,
  second: 0,
});
console.log("Apertura en Madrid:", aperturaMadrid.toString());

const aperturaTokyo = aperturaMadrid.withTimeZone("Asia/Tokyo");
console.log("Hora equivalente en Tokio:", aperturaTokyo.toString());

# Sostenibilidad 4.0 en la "Era del IQ" - MWC Barcelona 2026

> Tarea de Filippo Sirgiovanni - SASP (Sostenibilidad Aplicada a Sistemas Productivos)
> Fecha: 5 de marzo de 2026

---

## 1. ¿Qué es el "IQ" del MWC 2026?

El lema del MWC 2026 es **"The IQ Era"** (La Era del IQ/Inteligencia Conectada). A diferencia de años anteriores donde el foco era la velocidad (5G), este año el concepto clave es:

**IQ = Inteligencia + Conectividad + Propósito**

Para un desarrollador web, el IQ no es solo meter una API de ChatGPT en un formulario; es la capacidad de que el software y la red tomen decisiones autónomas para optimizar recursos.

### Sostenibilidad IQ:
Pasar del "medir cuánto contaminamos" al "predecir y evitar la contaminación" mediante código inteligente.

---

## 2. Ejes Técnicos: Del Código a la Resiliencia

### A. Redes "Zero Bit - Zero Watt"

**Concepto:** Si no hay tráfico de datos (Zero Bit), el consumo energético debe ser nulo (Zero Watt).

**Relación con DAW:**
- Crear aplicaciones que no mantengan conexiones activas innecesarias
- Evitar procesos en segundo plano que obliguen al servidor a estar en "high performance" sin necesidad
- Implementar lazy loading y conexiones bajo demanda

### B. Agentic AI y Sostenibilidad

Ya no hablamos de chatbots, sino de **Agentes de IA**.

**Aplicación:** Agentes que gestionan de forma autónoma el ciclo de vida de los datos en un servidor web, moviendo información a "almacenamiento en frío" de bajo consumo cuando detectan baja demanda.

### C. Gemelos Digitales para la Resiliencia Climática

El MWC 26 mostró gemelos digitales de Barcelona para predecir incendios o inundaciones.

**Reto DAW:** Visualizar estos datos en tiempo real mediante dashboards web altamente eficientes que funcionen incluso en redes de baja latencia durante una emergencia (Edge Computing).

---

## 3. Actividad Práctica: "The Green IQ Challenge"

### Propuesta de Web App: AgroTech IQ

#### Sector: Agroalimentario

**Problema identificado:**
- Desperdicio de agua en riegos programados
- Los sistemas actuales riegan según horarios fijos, sin considerar las condiciones reales del suelo o clima

**Solución propuesta: Web App "AgroIQ"**

##### Arquitectura del sistema:

```
┌─────────────────────────────────────────────────────────────┐
│                    AGRO IQ - ARQUITECTURA                    │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│   ┌──────────────┐    ┌──────────────┐    ┌──────────────┐  │
│   │  Satélites   │    │  Sensores    │    │   Weather    │  │
│   │  (NDVI)      │    │  IoT Suelo   │    │   API        │  │
│   └──────┬───────┘    └──────┬───────┘    └──────┬───────┘  │
│          │                   │                   │          │
│          └───────────────────┼───────────────────┘          │
│                              ▼                              │
│                    ┌──────────────────┐                     │
│                    │   Edge Gateway   │                     │
│                    │  (Procesamiento  │                     │
│                    │   Local/5G)      │                     │
│                    └────────┬─────────┘                     │
│                             │                               │
│                             ▼                               │
│                    ┌──────────────────┐                     │
│                    │   Agentic AI     │                     │
│                    │   (Decisiones    │                     │
│                    │   Autónomas)     │                     │
│                    └────────┬─────────┘                     │
│                             │                               │
│              ┌──────────────┴──────────────┐                │
│              ▼                             ▼                │
│      ┌──────────────┐              ┌──────────────┐         │
│      │   Dashboard  │              │   Actuador   │         │
│      │   Web App    │              │   Riego      │         │
│      │  (Frontend)  │              │  (On/Off)    │         │
│      └──────────────┘              └──────────────┘         │
│                                                              │
└─────────────────────────────────────────────────────────────┘
```

##### Funcionalidades principales:

| Módulo | Descripción | Principio IQ |
|--------|-------------|--------------|
| **SensorIQ** | Lee datos de sensores de humedad en tiempo real | Conectividad |
| **PredictIQ** | IA que predice necesidades de riego según clima y tipo de cultivo | Inteligencia |
| **ActuaIQ** | Activa riego solo cuando es necesario con precisión milimétrica | Propósito |
| **Dashboard** | Interfaz web responsive para el agricultor | Accesibilidad |
| **EdgeMode** | Funciona offline con procesamiento local | Resiliencia |

##### Código de ejemplo - Backend eficiente:

```javascript
// Ejemplo de función serverless para decisión de riego
// Solo se ejecuta cuando hay datos nuevos (Zero Bit - Zero Watt)

const { predictIrrigation } = require('./ml-model');
const { getSensorData, getWeatherData } = require('./data-sources');

exports.irrigationDecision = async (event, context) => {
  // Solo procesar si hay datos nuevos (evento-driven)
  if (!event.data) {
    return { action: 'no_data', reason: 'No new sensor data' };
  }
  
  const sensorData = await getSensorData(event.sensorId);
  const weather = await getWeatherData(event.location);
  
  // Agentic AI decision
  const decision = await predictIrrigation({
    soilMoisture: sensorData.humidity,
    temperature: weather.temp,
    forecast: weather.rainProbability,
    cropType: event.cropType
  });
  
  // Solo activar riego si es necesario (ahorro energético)
  if (decision.irrigationNeeded) {
    // Calcular tiempo exacto de riego
    const duration = Math.ceil(decision.waterNeeded / sensorData.flowRate);
    
    return {
      action: 'irrigate',
      duration: duration,
      waterSaved: decision.waterSavedVsScheduled,
      energySaved: decision.energySaved
    };
  }
  
  return { 
    action: 'skip', 
    reason: 'Soil moisture optimal',
    waterSaved: event.scheduledAmount
  };
};
```

##### Frontend Dashboard - Componente React eficiente:

```jsx
// Dashboard con lazy loading y actualización eficiente
import React, { lazy, Suspense } from 'react';
import { useWebSocket } from '../hooks/useWebSocket'; // Solo conecta cuando hay datos

const IrrigationChart = lazy(() => import('./IrrigationChart'));

function AgroIQDashboard() {
  const { sensorData, irrigationStatus } = useWebSocket(); // Conexión bajo demanda
  
  return (
    <div className="dashboard">
      <header>
        <h1>🌾 AgroIQ - Sistema de Riego Inteligente</h1>
        <div className="status">
          <span className={`indicator ${irrigationStatus.active ? 'active' : 'idle'}`}>
            {irrigationStatus.active ? '💧 Regando' : '✅ Óptimo'}
          </span>
        </div>
      </header>
      
      <main>
        {/* Solo carga el gráfico cuando es visible */}
        <Suspense fallback={<div>Cargando...</div>}>
          <IrrigationChart data={sensorData} />
        </Suspense>
        
        <section className="metrics">
          <div className="metric">
            <h3>💧 Agua Ahorrada</h3>
            <p>{sensorData.waterSaved.toLocaleString()} L</p>
          </div>
          <div className="metric">
            <h3>⚡ Energía Ahorrada</h3>
            <p>{sensorData.energySaved} kWh</p>
          </div>
          <div className="metric">
            <h3>🌱 CO2 Evitado</h3>
            <p>{sensorData.co2Saved} kg</p>
          </div>
        </section>
      </main>
    </div>
  );
}

export default AgroIQDashboard;
```

##### Impacto sostenible:

| Métrica | Valor estimado | Cálculo |
|---------|----------------|---------|
| **Agua ahorrada** | 30-50% menos | Riego solo cuando es necesario |
| **Energía ahorrada** | 40% menos bomba | Menos horas de bombeo |
| **CO2 evitado** | 2 kg/ha/día | Menor consumo eléctrico |
| **Coste reducido** | 25% menos | Menor factura eléctrica y agua |

##### Principios "Green Software" aplicados:

1. **Eficiencia de Carbono:** El código se ejecuta solo cuando hay datos nuevos
2. **Eficiencia Energética:** Procesamiento en Edge (más cerca del usuario, menos latencia)
3. **Conciencia de Carbono:** Las tareas pesadas (entrenamiento ML) se ejecutan cuando la red usa renovables
4. **Hardware Efficiency:** Funciona en dispositivos de baja potencia (Edge gateways)

---

## 4. Ética y Gobernanza

### Debate: Soberanía Digital

**Pregunta:** ¿Es sostenible depender de servidores de IA en EE.UU. o China para una app local en Barcelona?

**Respuesta:**

No es sostenible por varias razones:

1. **Huella de carbono del viaje de datos:** Cada petición a servidores en EE.UU. o China implica:
   - Viaje de ida y vuelta de datos por cables submarinos
   - Paso por múltiples centros de datos intermedios
   - Mayor latencia = mayor tiempo de conexión = mayor consumo

2. **Dependencia tecnológica:** Si el servicio externo falla, nuestra app local falla
   - Ejemplo: Cortes de OpenAI que afectan apps en todo el mundo
   - Riesgo para servicios críticos (agricultura, emergencias)

3. **Cumplimiento normativo:** RGPD requiere que datos de ciudadanos europeos se procesen según ley europea
   - Servidores en EE.UU. pueden no cumplir
   - Riesgo de multas y sanciones

**Solución: Sovereign AI Stacks**

Utilizar modelos locales o europeos:
- **Hugging Face** (Europa) con modelos open-source
- **Mistral AI** (Francia) - LLMs europeos
- **Ollama** - Modelos que corren localmente
- **Vertex AI** (Google Europa) - Datos procesados en región europea

**Código ejemplo - IA local vs. remota:**

```javascript
// NO SOSTENIBLE: IA en EE.UU.
async function decisionRemota(datos) {
  const response = await fetch('https://api.openai.com/v1/chat/completions', {
    method: 'POST',
    headers: { 'Authorization': `Bearer ${API_KEY}` },
    body: JSON.stringify({
      model: 'gpt-4',
      messages: [{ role: 'user', content: JSON.stringify(datos) }]
    })
  });
  // Viaje de datos: Barcelona → EE.UU. → Barcelona
  // Latencia: ~500ms, mayor consumo de red
  return response.json();
}

// SOSTENIBLE: IA local/europea
async function decisionLocal(datos) {
  // Usar Ollama o modelo local
  const response = await fetch('http://localhost:11434/api/generate', {
    method: 'POST',
    body: JSON.stringify({
      model: 'llama3',
      prompt: JSON.stringify(datos)
    })
  });
  // Sin viaje de datos, procesamiento local
  // Latencia: ~50ms, menor consumo
  return response.json();
}
```

---

## 5. Conclusiones

> "En la Era del IQ, vuestro trabajo como desarrolladores web no es solo que la web 'funcione', sino que sea consciente. Una línea de código eficiente no solo ahorra milisegundos de carga, ahorra vatios en un centro de datos."

### Principios clave para desarrolladores web sostenibles:

| Principio | Acción | Impacto |
|-----------|--------|---------|
| **Zero Bit - Zero Watt** | Evitar conexiones innecesarias | Menor consumo servidores |
| **Edge Computing** | Procesar cerca del usuario | Menor latencia y energía |
| **Agentic AI** | Automatizar decisiones eficientes | Optimización continua |
| **Green Coding** | Código optimizado | Menor CPU = menor energía |
| **Soberanía Digital** | Servicios locales/europeos | Menor huella de carbono |

---

## 6. Referencias

- MWC Barcelona 2026 - "The IQ Era"
- Green Software Foundation - https://greensoftware.foundation/
- Deutsche Telekom - Zero Bit Zero Watt Initiative
- Hugging Face - Modelos open-source
- Mistral AI - LLMs europeos

---

*Tarea completada - 5 de marzo de 2026*

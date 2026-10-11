# Ejercicio 2. Justificación de Arquitectura

**Dos ventajas técnicas de usar JavaScript nativo en el cliente para reducir el uso de recursos en el servidor:**
1. Al ejecutar en el propio navegador las validaciones y los cálculos de interfaz (ordenar listas, mostrar/ocultar elementos, validar un formulario), el servidor no tiene que procesar esas tareas, reduciendo su consumo de CPU.
2. Al intercambiar solo datos puros (normalmente en JSON) en lugar de documentos HTML completos en cada interacción, se reduce el tráfico de red y el ancho de banda que consume el servidor.

**Zero Plugins:** es el concepto de que JavaScript se ejecuta de forma nativa en cualquier navegador moderno, sin necesidad de instalar ningún complemento externo. Soluciona el problema histórico de tecnologías como los Java Applets o Adobe Flash, que obligaban al usuario a instalar un plugin pesado, no siempre disponible en todos los dispositivos y con importantes problemas de seguridad y rendimiento.

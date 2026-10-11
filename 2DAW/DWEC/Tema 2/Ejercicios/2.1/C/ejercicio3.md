# Ejercicio 3. Matriz de Selección de Dialectos

| Proyecto | Tecnología recomendada | Justificación |
|---|---|---|
| A: plataforma bancaria, 30 desarrolladores, validación estática en desarrollo | **TypeScript** | Su tipado fuerte detecta errores en tiempo de desarrollo/compilación, algo crítico en un equipo grande y en una aplicación financiera donde un fallo puede ser muy costoso. |
| B: script ligero de 50 líneas para un widget flotante en un sitio estático | **Vanilla JS** | Es un proyecto pequeño que no necesita tipado estático ni paso de compilación; JavaScript nativo se ejecuta directamente y es suficiente. |
| C: interfaz interactiva basada en componentes reutilizables con ReactJS | **JSX** | Es la sintaxis propia de React para construir componentes mezclando HTML y JS, pensada exactamente para este tipo de arquitectura. |

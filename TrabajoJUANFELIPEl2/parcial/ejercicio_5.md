# Ejercicio 5 — Crítica técnica de la solución propuesta

### 1. Error conceptual identificado y diferencia entre pila y cola

La solución propuesta confunde dos estructuras de datos completamente
opuestas: la pila y la cola. Afirma que usar una pila "garantiza que
el primero en llegar sea el primero en salir", pero eso es exactamente
lo contrario de cómo funciona una pila.

La diferencia es sencilla si se piensa en ejemplos del día a día.

Una pila funciona como una pila de libros: el último libro que pones
encima es el primero que puedes agarrar. Si quieres el de abajo,
primero tienes que quitar todos los de arriba. Así funciona también
en programación: el último en entrar es el primero en salir.

Una cola en cambio funciona como una fila de personas esperando ser
atendidas: el primero que llega es el primero que pasa. Nadie se cola,
nadie se salta. El primero en entrar es el primero en salir, y eso es
exactamente lo que necesita este problema.

### 2. Ejemplo concreto donde la solución falla

Supongamos que llegan cuatro estudiantes en este orden:
Estudiante A, Estudiante B, Estudiante C, Estudiante D.

Con una pila:
- Se insertan: A, B, C, D
- Al atender con pop, el orden de salida es: D, C, B, A
- El último en llegar es el primero en ser atendido.
- El Estudiante A, que llegó primero, es atendido de último.
Esto es injusto e incorrecto para el problema planteado.

Con una cola:
- Se insertan: A, B, C, D
- Al atender con poll, el orden de salida es: A, B, C, D
- El primero en llegar es el primero en ser atendido.
Esto sí respeta el orden de llegada.

### 3. Corrección de la solución

La estructura correcta para atender estudiantes en orden de llegada
es una cola (Queue). Cada estudiante se agrega al final con offer()
y se atiende desde el frente con poll(). Así se garantiza que el
primero en llegar sea realmente el primero en salir.

En Java:

    Queue<String> cola = new LinkedList<>();
    cola.offer("Estudiante A");
    cola.offer("Estudiante B");
    cola.offer("Estudiante C");
    cola.offer("Estudiante D");
    // Al atender: A, B, C, D en ese orden
    String atendido = cola.poll();

### 4. Reflexión sobre respuestas automáticas

Esta solución generada automáticamente es un ejemplo claro de por qué
no se puede aceptar una respuesta sin analizarla. El texto suena
convincente: usa términos correctos como push, pop y menciona el orden
de llegada. Sin embargo, aplica esos conceptos de forma incorrecta.

Una respuesta puede tener la terminología correcta y aun así estar
completamente equivocada en su lógica. Por eso es importante no solo
leer la respuesta, sino entender qué hace cada operación y verificar
si el comportamiento real coincide con lo que el problema necesita.
En este caso, bastaba con preguntarse: ¿qué elemento saca pop primero?
La respuesta a esa pregunta revela inmediatamente el error.

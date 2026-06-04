# Reflexión Final - Parcial de Estructuras de Datos

**Estudiante:** Juan Felipe González
**Código:** 67064

---

## Reflexión sobre el proceso de solución

Este parcial me permitió aplicar de manera práctica los conceptos de estructuras de datos que hemos visto en el semestre. Al enfrentarme a cada ejercicio, lo primero que hice fue analizar qué tipo de operaciones necesitaba: si iba a insertar y eliminar en cualquier posición, si necesitaba respetar un orden de llegada, o si tenía que rastrear un contexto que cambia con cada acción.

Lo que más me llamó la atención fue darme cuenta de que elegir la estructura correcta no es un detalle menor, sino la base de toda la solución. En el ejercicio 1, por ejemplo, haber elegido un arreglo en lugar de una lista enlazada habría complicado enormemente las operaciones de eliminación. En el ejercicio 2, si hubiera usado una sola estructura para todo, la lógica de validación no habría tenido sentido.

El ejercicio 4 fue el más desafiante porque el backtracking requiere pensar de manera diferente: no se trata solo de encontrar una respuesta, sino de saber cuándo deshacer una decisión y probar otro camino. Eso es algo que no se resuelve de forma intuitiva la primera vez.

---

## Dificultades encontradas

En general todos los ejercicios me generaron cierto nivel de confusión,
pero hubo momentos específicos que me costaron más.

En el ejercicio 1, lo que más me complicó fue la restricción de mover
las solicitudes de prioridad alta al inicio sin usar ningún método de
ordenamiento incorporado. Al principio no entendía cómo hacerlo sin
simplemente ordenar la lista. La solución que encontré fue separar
manualmente las solicitudes en dos listas auxiliares: una para las de
prioridad alta y otra para las demás, y luego unirlas. Parece simple
cuando ya está hecho, pero entender que esa era la forma correcta de
respetar la restricción me tomó un momento.

En el ejercicio 2, me confundí con la regla de que una respuesta solo
es válida si corresponde a la pregunta actualmente abierta. Los ejemplos
de trazas inválidas me ayudaron a entenderlo mejor: si abro la pregunta
1, luego abro la pregunta 2, el contexto activo ya no es la pregunta 1
aunque yo la haya abierto antes. La pila fue clave para representar eso,
porque el tope siempre indica cuál es la pregunta donde estoy parado
en ese momento.

En el ejercicio 3, la función de búsqueda me generó dudas al principio.
No tenía claro cómo recorrer una estructura jerárquica buscando un nombre
sin saber en qué nivel está la actividad. Entender que la recursividad
hace ese trabajo por sí sola, bajando nivel por nivel hasta encontrar el
nodo o agotar todos los caminos, fue lo que me permitió resolver esa parte.

---

## Decisiones algorítmicas importantes

1. **Usar HashSet para eliminar duplicados en O(n):** En lugar de comparar cada elemento contra todos los demás (O(n²)), se llevó un registro de los IDs ya vistos. Esto hace la solución mucho más eficiente con listas grandes.

2. **Cola para orden cronológico, pila para contexto:** En el ejercicio 2, estas dos estructuras representan conceptos distintos del problema y no son intercambiables. La cola garantiza que las acciones se procesen en el orden en que ocurrieron; la pila garantiza que el contexto de navegación funcione como una historia de "páginas visitadas".

3. **Poda temprana en backtracking:** El método esValida() verifica las cuatro restricciones antes de asignar. Esto evita explorar combinaciones que están condenadas al fracaso desde el principio, lo que reduce significativamente el tiempo de búsqueda.

4. **Separación en clases internas:** En los ejercicios 1 y 4 se usaron clases internas (Solicitud, Evaluacion, Sala) para representar los datos de forma estructurada y legible, en lugar de usar arreglos paralelos.

---

## Declaración de uso de herramientas externas

Durante el desarrollo de este parcial utilicé **Claude (Anthropic)** como herramienta de apoyo para:

- Revisar la lógica de mis soluciones y detectar errores.
- Consultar dudas sobre el comportamiento de estructuras de datos en Java.
- Mejorar la redacción de las respuestas de análisis.

El código fue revisado, comprendido y validado por mí. Puedo explicar el funcionamiento de cada método, la razón de cada decisión de diseño y el resultado esperado de cada prueba. El uso de herramientas externas fue declarado de acuerdo con las políticas académicas de honestidad intelectual.

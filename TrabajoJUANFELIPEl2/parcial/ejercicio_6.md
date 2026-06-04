# Ejercicio 6 — Defensa técnica del Ejercicio 1

### 1. ¿Qué problema resolvió?

El ejercicio 1 resuelve un problema de datos sucios. La coordinación
académica tenía una lista de solicitudes con muchos datos basura:
registros duplicados, solicitudes desactualizadas y canceladas que
ya no servían. El objetivo era limpiar esa lista y dejar solo lo útil,
además de reorganizarla para que las solicitudes más urgentes quedaran
primero.

### 2. ¿Qué estructura de datos fue central y por qué?

La estructura central fue LinkedList. La elegí porque el problema
requiere mover datos al inicio, borrarlos en cualquier posición y
reorganizar la lista sin afectar los demás elementos. LinkedList
permite hacer esas operaciones de forma eficiente porque no necesita
desplazar todos los elementos como lo haría un ArrayList. Solo cambia
los punteros entre nodos, lo cual es más rápido cuando se trabaja
con inserciones y eliminaciones frecuentes.

### 3. ¿Por qué esa estructura y no otra?

Un ArrayList habría sido problemático especialmente en el paso de
mover las solicitudes de prioridad alta al inicio, porque cada vez
que se inserta al inicio de un ArrayList, Java desplaza todos los
elementos una posición. Con LinkedList ese movimiento es directo.
La solución además usa un HashSet auxiliar para detectar duplicados
en O(n), evitando comparar cada elemento contra todos los demás
lo cual sería O(n²).

### 4. ¿Cuál es la complejidad temporal y espacial?

La complejidad temporal es O(n) para la eliminación de duplicados
gracias al HashSet, y O(n) para mover prioridades y eliminar
canceladas ya que se recorre la lista una vez en cada operación.
En total el proceso completo es O(n).

La complejidad espacial es O(n) porque se usan listas auxiliares
temporales para reorganizar los datos sin perder información.

### 5. ¿Cuál fue el caso límite más importante?

El caso más importante fue cuando todas las solicitudes eran
duplicadas o canceladas al mismo tiempo. Si la solución estaba
mal diseñada, ese caso podía generar errores al intentar operar
sobre una lista vacía, o podía tomar mucho más tiempo porque
había muchas comparaciones innecesarias. La solución maneja ese
caso correctamente y retorna una lista vacía sin ningún error.

### 6. ¿Qué cambiaría si el tamaño de entrada creciera diez veces?

Si la cantidad de solicitudes creciera diez veces, el uso de
HashSet para eliminar duplicados seguiría siendo eficiente porque
su complejidad es O(n) independientemente del tamaño. Lo que sí
podría volverse un problema es el uso de listas auxiliares que
duplican el espacio en memoria. En ese caso consideraría eliminar
los duplicados directamente sobre la lista original usando
iteradores para no necesitar estructuras adicionales.

### 7. ¿Qué parte considera más vulnerable a errores?

La parte más vulnerable es la eliminación de duplicados. Si se
usa una comparación incorrecta, como comparar objetos con == en
lugar de comparar el idSolicitud directamente, el programa podría
dejar pasar duplicados sin detectarlos. También es vulnerable el
orden de las operaciones: si se eliminan las canceladas antes de
mover las de prioridad alta al inicio, el resultado final puede
ser diferente al esperado.

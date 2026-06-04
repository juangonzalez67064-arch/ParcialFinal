# Parcial Final - Estructuras de Datos

**Estudiante:** Juan Felipe González
**Código:** 67064
**Semestre:** Tercero
**Fecha:** Junio 2026

---

## Instrucciones para ejecutar el proyecto

Cada ejercicio es un archivo `.java` independiente. Para compilar y ejecutar cualquiera de ellos:

```bash
# 1. Entrar a la carpeta del ejercicio
cd src/ejercicio_1

# 2. Compilar
javac AuditoriaAcademica.java

# 3. Ejecutar
java AuditoriaAcademica
```

Repetir el mismo proceso para los demás ejercicios cambiando la carpeta y el nombre del archivo.

---

## Lenguaje utilizado

- **Lenguaje:** Java
- **Versión:** Java 17 (o superior compatible con Java 8+)
- **Entorno de desarrollo:** IntelliJ IDEA
- **Sistema operativo:** Windows / Linux

---

## Descripción breve de cada ejercicio

### Ejercicio 1 - Auditoría de solicitudes académicas (`AuditoriaAcademica.java`)
Se implementó una estructura basada en LinkedList para gestionar solicitudes académicas.
La LinkedList fue elegida porque permite insertar al final, mover elementos al inicio y
eliminar en cualquier posición sin desplazar los demás elementos, lo cual es más eficiente
que un ArrayList para este caso. El programa elimina duplicados usando HashSet en O(n),
mueve las de prioridad alta al inicio conservando su orden relativo, y elimina las canceladas,
generando una lista final depurada.

### Ejercicio 2 - Validador de trazas de navegación (`ValidadorTrazas.java`)
Se implementó un validador que usa una cola para procesar las acciones en orden de llegada (FIFO)
y una pila para controlar el contexto de navegación activo (LIFO). El programa indica si una
traza de acciones de un estudiante en un examen virtual es coherente o no, y reporta la primera
inconsistencia encontrada.

### Ejercicio 3 - Procesamiento recursivo de estructuras académicas (`ProcesadorAcademico.java`)
Se modeló un plan de estudios como una estructura jerárquica de nodos (Programa > Semestre >
Curso > Unidad > Actividad). Se implementaron cuatro funciones recursivas: contar el total de
actividades, calcular la profundidad máxima, buscar una actividad por nombre, e imprimir la
estructura con indentación por nivel.

### Ejercicio 4 - Asignación de salas usando backtracking (`AsignacionSalas.java`)
Se implementó un algoritmo de backtracking para asignar evaluaciones finales a salas
universitarias respetando cuatro restricciones: capacidad, disponibilidad por franja horaria,
requerimiento de computador y no repetición de sala en la misma franja. El programa reporta
la primera asignación válida encontrada o indica que no existe solución.

---

## Decisiones importantes tomadas durante la solución

### Ejercicio 1
- Se eligió LinkedList sobre ArrayList porque permite insertar al inicio y eliminar en cualquier
  posición sin reorganizar memoria, operaciones que este ejercicio requiere frecuentemente.
- Se usó HashSet para eliminar duplicados en O(n) en lugar de comparar todos contra todos en O(n²).
- Se separaron las de prioridad alta en una lista auxiliar para mantener el orden relativo
  sin usar métodos de ordenamiento incorporados.

### Ejercicio 2
- Se usó Queue (cola) para procesar acciones en el orden cronológico exacto en que ocurrieron.
- Se usó Stack (pila) para el contexto de navegación porque el comportamiento LIFO representa
  perfectamente la lógica de "volver a la pregunta anterior".
- Se verificó isEmpty() antes de cada pop() o peek() para evitar EmptyStackException.

### Ejercicio 3
- Cada función tiene un caso base claro: nodo sin hijos retorna 1 (profundidad) o 0 (actividades).
- La recursividad recorre todos los subcomponentes de cada nodo sin necesidad de saber
  en qué nivel se encuentra la actividad buscada.

### Ejercicio 4
- Se eligió backtracking porque el problema requiere explorar combinaciones y deshacer
  decisiones incorrectas, algo que una solución voraz no puede hacer.
- La poda se realiza en el método esValida(), que verifica las cuatro restricciones antes
  de asignar, evitando explorar ramas sin solución.

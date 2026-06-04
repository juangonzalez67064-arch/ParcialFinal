import java.util.*;

public class AsignacionSalas {

    static class Evaluacion {
        String codigo;
        int estudiantes;
        String franja;
        boolean requiereComputador;

        public Evaluacion(String codigo, int estudiantes, String franja, boolean requiereComputador) {
            this.codigo = codigo;
            this.estudiantes = estudiantes;
            this.franja = franja;
            this.requiereComputador = requiereComputador;
        }
    }

    static class Sala {
        String codigo;
        int capacidad;
        List<String> franjas;
        boolean tieneComputador;

        public Sala(String codigo, int capacidad, List<String> franjas, boolean tieneComputador) {
            this.codigo = codigo;
            this.capacidad = capacidad;
            this.franjas = franjas;
            this.tieneComputador = tieneComputador;
        }
    }

    static Map<String, String> asignacion = new LinkedHashMap<>();
    static List<Evaluacion> evaluaciones;
    static List<Sala> salas;

    // Verifica si una sala cumple todas las restricciones para una evaluacion
    static boolean esValida(Evaluacion eval, Sala sala) {
        // Restriccion 1: capacidad suficiente
        if (sala.capacidad < eval.estudiantes) return false;

        // Restriccion 2: sala disponible en esa franja
        if (!sala.franjas.contains(eval.franja)) return false;

        // Restriccion 3: si requiere computador, la sala debe tenerlo
        if (eval.requiereComputador && !sala.tieneComputador) return false;

        // Restriccion 4: no puede haber dos evaluaciones en la misma sala y franja
        for (Map.Entry<String, String> entry : asignacion.entrySet()) {
            if (entry.getValue().equals(sala.codigo)) {
                for (Evaluacion e : evaluaciones) {
                    if (e.codigo.equals(entry.getKey()) && e.franja.equals(eval.franja)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Algoritmo de backtracking recursivo
    static boolean backtracking(int index) {
        // Caso base: todas las evaluaciones fueron asignadas
        if (index == evaluaciones.size()) return true;

        Evaluacion eval = evaluaciones.get(index);

        for (Sala sala : salas) {
            if (esValida(eval, sala)) {
                asignacion.put(eval.codigo, sala.codigo);       // asignar
                if (backtracking(index + 1)) return true;
                asignacion.remove(eval.codigo);                  // deshacer (backtrack)
            }
        }
        return false;
    }

    // Imprime el resultado de una corrida
    static void ejecutar(String titulo) {
        asignacion = new LinkedHashMap<>();
        System.out.println("\n========== " + titulo + " ==========");
        if (backtracking(0)) {
            System.out.println("Asignacion encontrada:");
            for (Map.Entry<String, String> entry : asignacion.entrySet()) {
                System.out.println("  Evaluacion " + entry.getKey() + " -> Sala " + entry.getValue());
            }
        } else {
            System.out.println("No existe una asignacion valida.");
        }
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        // CASO 1 - NORMAL
        // 3 evaluaciones, 3 salas, existe solucion valida.
        // Se espera: asignacion encontrada respetando las 4 restricciones.
        // ══════════════════════════════════════════════════════
        evaluaciones = new ArrayList<>();
        evaluaciones.add(new Evaluacion("EDA",  30, "18:00-20:00", true));
        evaluaciones.add(new Evaluacion("BD",   25, "18:00-20:00", true));
        evaluaciones.add(new Evaluacion("CALC", 40, "20:00-22:00", false));

        salas = new ArrayList<>();
        salas.add(new Sala("A101", 35, Arrays.asList("18:00-20:00", "20:00-22:00"), true));
        salas.add(new Sala("A102", 30, Arrays.asList("18:00-20:00"),               true));
        salas.add(new Sala("B201", 50, Arrays.asList("20:00-22:00"),               false));

        ejecutar("CASO 1: NORMAL (solucion existe)");

        // ══════════════════════════════════════════════════════
        // CASO 2 - LIMITE
        // Una sola evaluacion y una sola sala que cumple todo.
        // Se espera: asignacion directa sin necesidad de backtrack.
        // ══════════════════════════════════════════════════════
        evaluaciones = new ArrayList<>();
        evaluaciones.add(new Evaluacion("MATE", 20, "18:00-20:00", false));

        salas = new ArrayList<>();
        salas.add(new Sala("C101", 30, Arrays.asList("18:00-20:00"), false));

        ejecutar("CASO 2: LIMITE (una evaluacion, una sala)");

        // ══════════════════════════════════════════════════════
        // CASO 3 - ERROR POTENCIAL
        // 3 evaluaciones en la misma franja, solo 2 salas disponibles.
        // Si el algoritmo no retrocede correctamente, podria asignar
        // dos evaluaciones a la misma sala en la misma franja.
        // Se espera: no existe asignacion valida.
        // ══════════════════════════════════════════════════════
        evaluaciones = new ArrayList<>();
        evaluaciones.add(new Evaluacion("EDA", 30, "18:00-20:00", true));
        evaluaciones.add(new Evaluacion("BD",  30, "18:00-20:00", true));
        evaluaciones.add(new Evaluacion("ALG", 30, "18:00-20:00", true));

        salas = new ArrayList<>();
        salas.add(new Sala("A101", 35, Arrays.asList("18:00-20:00"), true));
        salas.add(new Sala("A102", 30, Arrays.asList("18:00-20:00"), true));

        ejecutar("CASO 3: ERROR POTENCIAL (3 evaluaciones, 2 salas, misma franja)");
    }
}

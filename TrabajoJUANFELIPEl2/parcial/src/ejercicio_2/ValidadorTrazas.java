import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ValidadorTrazas {

    // Procesa y valida una traza de acciones
    public static void validarTraza(String[] acciones) {
        Queue<String> cola = new LinkedList<>();
        Stack<Integer> pila = new Stack<>();

        // Cargar todas las acciones en la cola (orden FIFO)
        for (String accion : acciones) {
            cola.offer(accion);
        }

        System.out.println("Procesando traza...\n");

        while (!cola.isEmpty()) {
            String accion = cola.poll();

            if (accion.startsWith("ABRIR_PREGUNTA")) {
                int numPregunta = extraerNumero(accion);
                pila.push(numPregunta);
                System.out.println("OK " + accion + " -> contexto activo: pregunta " + numPregunta);

            } else if (accion.startsWith("RESPONDER")) {
                int numPregunta = extraerNumero(accion);
                if (pila.isEmpty()) {
                    System.out.println("INVALIDO: RESPONDER(" + numPregunta +
                            ") sin ninguna pregunta abierta.");
                    return;
                }
                int contextoActual = pila.peek();
                if (contextoActual != numPregunta) {
                    System.out.println("INVALIDO: Se intenta responder pregunta " + numPregunta +
                            " pero el contexto activo es la pregunta " + contextoActual + ".");
                    return;
                }
                System.out.println("OK " + accion + " -> responde correctamente a pregunta " + contextoActual);

            } else if (accion.startsWith("GUARDAR")) {
                if (pila.isEmpty()) {
                    System.out.println("INVALIDO: GUARDAR sin ninguna pregunta abierta.");
                    return;
                }
                System.out.println("OK " + accion + " -> guardado en contexto de pregunta " + pila.peek());

            } else if (accion.equals("VOLVER")) {
                if (pila.isEmpty()) {
                    System.out.println("INVALIDO: VOLVER sin contexto previo en la pila.");
                    return;
                }
                int saliendo = pila.pop();
                System.out.println("OK VOLVER -> sale de pregunta " + saliendo +
                        (pila.isEmpty() ? ", pila vacia" : ", contexto anterior: pregunta " + pila.peek()));

            } else if (accion.equals("ENVIAR")) {
                System.out.println("OK ENVIAR -> traza finalizada.");
                System.out.println("\nTRAZA VALIDA.");
                return;

            } else {
                System.out.println("INVALIDO: Accion desconocida -> " + accion);
                return;
            }
        }
        System.out.println("\nTRAZA INCOMPLETA: No se encontro accion ENVIAR.");
    }

    // Extrae el numero entre parentesis de una accion
    private static int extraerNumero(String accion) {
        int inicio = accion.indexOf('(') + 1;
        int fin = accion.indexOf(')');
        return Integer.parseInt(accion.substring(inicio, fin));
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        // CASO 1 - NORMAL
        // Flujo completo correcto: abrir, responder, guardar,
        // abrir otra, responder, volver, enviar.
        // Se espera: TRAZA VALIDA.
        // ══════════════════════════════════════════════════════
        System.out.println("========== CASO 1: NORMAL (traza valida) ==========");
        String[] trazaValida = {
            "ABRIR_PREGUNTA(1)",
            "RESPONDER(1)",
            "GUARDAR(1)",
            "ABRIR_PREGUNTA(2)",
            "RESPONDER(2)",
            "VOLVER",
            "ENVIAR"
        };
        validarTraza(trazaValida);

        // ══════════════════════════════════════════════════════
        // CASO 2 - LIMITE
        // Traza minima valida: solo abrir una pregunta y enviar
        // sin responder ni guardar.
        // Se espera: TRAZA VALIDA (no hay ninguna regla que
        // obligue a responder antes de enviar).
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 2: LIMITE (traza minima, solo ABRIR y ENVIAR) ==========");
        String[] trazaMinima = {
            "ABRIR_PREGUNTA(1)",
            "ENVIAR"
        };
        validarTraza(trazaMinima);

        // ══════════════════════════════════════════════════════
        // CASO 3 - ERROR POTENCIAL
        // Se intenta RESPONDER una pregunta diferente a la abierta.
        // Si la solucion no verifica el tope de la pila, aprobaria
        // esta traza incorrectamente.
        // Se espera: INVALIDO con explicacion del contexto activo.
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 3: ERROR POTENCIAL (responder pregunta incorrecta) ==========");
        String[] trazaInvalida = {
            "ABRIR_PREGUNTA(1)",
            "ABRIR_PREGUNTA(2)",
            "RESPONDER(1)", // contexto activo es 2, no 1
            "ENVIAR"
        };
        validarTraza(trazaInvalida);
    }
}

import java.util.HashSet;
import java.util.LinkedList;

public class AuditoriaAcademica {

    static class Solicitud {
        int idSolicitud;
        String codigoEstudiante;
        String tipoSolicitud;
        String prioridad;
        String fechaRegistro;
        String estado;

        public Solicitud(int idSolicitud, String codigoEstudiante, String tipoSolicitud,
                         String prioridad, String fechaRegistro, String estado) {
            this.idSolicitud = idSolicitud;
            this.codigoEstudiante = codigoEstudiante;
            this.tipoSolicitud = tipoSolicitud;
            this.prioridad = prioridad;
            this.fechaRegistro = fechaRegistro;
            this.estado = estado;
        }

        @Override
        public String toString() {
            return "[" + idSolicitud + ", " + codigoEstudiante + ", " + tipoSolicitud +
                   ", " + prioridad + ", " + fechaRegistro + ", " + estado + "]";
        }
    }

    // Paso 1: Insertar una solicitud al final de la lista
    public static void insertarSolicitud(LinkedList<Solicitud> lista, Solicitud s) {
        lista.addLast(s);
    }

    // Paso 2: Eliminar duplicados por idSolicitud, conservando la primera aparicion
    // Se usa HashSet para registrar los ids ya vistos en O(n)
    // Esto es mas eficiente que comparar todos contra todos en O(n^2)
    public static void eliminarDuplicados(LinkedList<Solicitud> lista) {
        HashSet<Integer> vistos = new HashSet<Integer>();
        LinkedList<Solicitud> sinDuplicados = new LinkedList<Solicitud>();

        for (int i = 0; i < lista.size(); i++) {
            Solicitud s = lista.get(i);
            if (vistos.add(s.idSolicitud)) {
                sinDuplicados.addLast(s);
            }
        }

        lista.clear();
        for (int i = 0; i < sinDuplicados.size(); i++) {
            lista.addLast(sinDuplicados.get(i));
        }
    }

    // Paso 3: Mover solicitudes de prioridad "alta" al inicio, manteniendo orden relativo
    // Se separan manualmente en dos listas y se vuelven a unir
    // No se usa ningun metodo de ordenamiento incorporado
    public static void moverAltaAlInicio(LinkedList<Solicitud> lista) {
        LinkedList<Solicitud> altas = new LinkedList<Solicitud>();
        LinkedList<Solicitud> otras = new LinkedList<Solicitud>();

        for (int i = 0; i < lista.size(); i++) {
            Solicitud s = lista.get(i);
            if (s.prioridad.equalsIgnoreCase("alta")) {
                altas.addLast(s);
            } else {
                otras.addLast(s);
            }
        }

        lista.clear();
        for (int i = 0; i < altas.size(); i++) {
            lista.addLast(altas.get(i));
        }
        for (int i = 0; i < otras.size(); i++) {
            lista.addLast(otras.get(i));
        }
    }

    // Paso 4: Eliminar todas las solicitudes con estado "cancelada"
    public static void eliminarCanceladas(LinkedList<Solicitud> lista) {
        LinkedList<Solicitud> activas = new LinkedList<Solicitud>();

        for (int i = 0; i < lista.size(); i++) {
            Solicitud s = lista.get(i);
            if (!s.estado.equalsIgnoreCase("cancelada")) {
                activas.addLast(s);
            }
        }

        lista.clear();
        for (int i = 0; i < activas.size(); i++) {
            lista.addLast(activas.get(i));
        }
    }

    // Imprime el contenido de la lista con un titulo
    public static void imprimirLista(LinkedList<Solicitud> lista, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (lista.isEmpty()) {
            System.out.println("(lista vacia)");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }
        }
    }

    // Ejecuta el flujo completo de depuracion
    public static void ejecutarFlujo(LinkedList<Solicitud> lista) {
        imprimirLista(lista, "Lista inicial");
        eliminarDuplicados(lista);
        imprimirLista(lista, "Despues de eliminar duplicados");
        moverAltaAlInicio(lista);
        imprimirLista(lista, "Despues de mover prioridad alta al inicio");
        eliminarCanceladas(lista);
        imprimirLista(lista, "Lista final depurada");
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        // CASO 1 - NORMAL
        // Solicitudes variadas: duplicado, cancelada, prioridades mixtas.
        // Se espera: eliminar duplicado, subir altas, quitar cancelada.
        // ══════════════════════════════════════════════════════
        System.out.println("========== CASO 1: NORMAL ==========");
        LinkedList<Solicitud> lista1 = new LinkedList<Solicitud>();
        insertarSolicitud(lista1, new Solicitud(101, "20241001", "homologacion", "media",  "2026-06-01", "activa"));
        insertarSolicitud(lista1, new Solicitud(102, "20241002", "cancelacion",  "alta",   "2026-06-01", "activa"));
        insertarSolicitud(lista1, new Solicitud(101, "20241001", "homologacion", "media",  "2026-06-01", "activa")); // duplicado
        insertarSolicitud(lista1, new Solicitud(103, "20241003", "supletorio",   "alta",   "2026-06-02", "cancelada"));
        insertarSolicitud(lista1, new Solicitud(104, "20241004", "reingreso",    "baja",   "2026-06-02", "activa"));
        insertarSolicitud(lista1, new Solicitud(105, "20241005", "validacion",   "alta",   "2026-06-03", "activa"));
        ejecutarFlujo(lista1);

        // ══════════════════════════════════════════════════════
        // CASO 2 - LIMITE
        // Lista con una sola solicitud.
        // Se espera: la lista final tiene esa unica solicitud sin cambios.
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 2: LIMITE (una sola solicitud) ==========");
        LinkedList<Solicitud> lista2 = new LinkedList<Solicitud>();
        insertarSolicitud(lista2, new Solicitud(201, "20241010", "reingreso", "alta", "2026-06-01", "activa"));
        ejecutarFlujo(lista2);

        // ══════════════════════════════════════════════════════
        // CASO 3 - ERROR POTENCIAL
        // Todas las solicitudes son duplicadas o canceladas.
        // Si la solucion esta mal, podria lanzar excepcion con lista vacia.
        // Se espera: lista final vacia sin ningun error.
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 3: ERROR POTENCIAL (todas duplicadas y canceladas) ==========");
        LinkedList<Solicitud> lista3 = new LinkedList<Solicitud>();
        insertarSolicitud(lista3, new Solicitud(301, "20241020", "supletorio", "alta",  "2026-06-01", "cancelada"));
        insertarSolicitud(lista3, new Solicitud(301, "20241020", "supletorio", "alta",  "2026-06-01", "cancelada")); // duplicado
        insertarSolicitud(lista3, new Solicitud(302, "20241021", "validacion", "media", "2026-06-01", "cancelada"));
        ejecutarFlujo(lista3);
    }
}

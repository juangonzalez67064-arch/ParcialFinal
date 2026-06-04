import java.util.ArrayList;

public class ProcesadorAcademico {

    // Clase que representa cualquier nodo del plan de estudios
    // Puede ser: Programa, Semestre, Curso, Unidad o Actividad
    static class Nodo {
        String tipo;
        String nombre;
        ArrayList<Nodo> subcomponentes;

        public Nodo(String tipo, String nombre) {
            this.tipo = tipo;
            this.nombre = nombre;
            this.subcomponentes = new ArrayList<Nodo>();
        }

        // Agrega un hijo a este nodo
        public void agregar(Nodo hijo) {
            subcomponentes.add(hijo);
        }
    }

    // ─────────────────────────────────────────────────────────
    // FUNCION 1: Contar el numero total de actividades
    // Recorre recursivamente todos los nodos.
    // Si el nodo es una actividad, suma 1.
    // Si tiene hijos, los recorre tambien.
    // ─────────────────────────────────────────────────────────
    public static int contarActividades(Nodo nodo) {
        int contador = 0;

        if (nodo.tipo.equalsIgnoreCase("Actividad")) {
            contador = 1;
        }

        for (int i = 0; i < nodo.subcomponentes.size(); i++) {
            contador += contarActividades(nodo.subcomponentes.get(i));
        }

        return contador;
    }

    // ─────────────────────────────────────────────────────────
    // FUNCION 2: Calcular la profundidad maxima de la estructura
    // El nodo raiz tiene profundidad 1.
    // Cada nivel de hijos suma 1 mas.
    // Se elige siempre el camino mas largo.
    // ─────────────────────────────────────────────────────────
    public static int profundidadMaxima(Nodo nodo) {
        if (nodo.subcomponentes.isEmpty()) {
            return 1;
        }

        int maxHijo = 0;

        for (int i = 0; i < nodo.subcomponentes.size(); i++) {
            int profHijo = profundidadMaxima(nodo.subcomponentes.get(i));
            if (profHijo > maxHijo) {
                maxHijo = profHijo;
            }
        }

        return 1 + maxHijo;
    }

    // ─────────────────────────────────────────────────────────
    // FUNCION 3: Buscar si existe una actividad con un nombre dado
    // Recorre recursivamente todos los nodos.
    // Si encuentra el nombre en un nodo de tipo Actividad, retorna true.
    // ─────────────────────────────────────────────────────────
    public static boolean buscarActividad(Nodo nodo, String nombreBuscado) {
        if (nodo.tipo.equalsIgnoreCase("Actividad") &&
            nodo.nombre.equalsIgnoreCase(nombreBuscado)) {
            return true;
        }

        for (int i = 0; i < nodo.subcomponentes.size(); i++) {
            if (buscarActividad(nodo.subcomponentes.get(i), nombreBuscado)) {
                return true;
            }
        }

        return false;
    }

    // ─────────────────────────────────────────────────────────
    // FUNCION 4: Imprimir la estructura con indentacion por nivel
    // El nivel 0 no tiene espacios.
    // Cada nivel agrega 2 espacios de sangria.
    // ─────────────────────────────────────────────────────────
    public static void imprimirEstructura(Nodo nodo, int nivel) {
        String sangria = "";
        for (int i = 0; i < nivel * 2; i++) {
            sangria += " ";
        }

        System.out.println(sangria + nodo.tipo + ": " + nodo.nombre);

        for (int i = 0; i < nodo.subcomponentes.size(); i++) {
            imprimirEstructura(nodo.subcomponentes.get(i), nivel + 1);
        }
    }

    // Construye el plan de estudios del ejemplo conceptual del enunciado
    public static Nodo construirEjemplo() {
        Nodo programa = new Nodo("Programa", "Ingenieria");

        Nodo semestre1 = new Nodo("Semestre", "Semestre 1");

        Nodo cursoProg = new Nodo("Curso", "Programacion");
        Nodo unidadIntro = new Nodo("Unidad", "Introduccion");
        unidadIntro.agregar(new Nodo("Actividad", "Taller 1"));
        unidadIntro.agregar(new Nodo("Actividad", "Quiz 1"));
        cursoProg.agregar(unidadIntro);

        Nodo cursoMate = new Nodo("Curso", "Matematicas");
        Nodo unidadFunc = new Nodo("Unidad", "Funciones");
        unidadFunc.agregar(new Nodo("Actividad", "Taller 2"));
        cursoMate.agregar(unidadFunc);

        semestre1.agregar(cursoProg);
        semestre1.agregar(cursoMate);
        programa.agregar(semestre1);

        return programa;
    }

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════
        // CASO 1 - NORMAL
        // Plan de estudios del enunciado con 3 actividades y
        // profundidad de 5 niveles (Programa > Semestre > Curso
        // > Unidad > Actividad).
        // ══════════════════════════════════════════════════════
        System.out.println("========== CASO 1: NORMAL (ejemplo del enunciado) ==========\n");
        Nodo raiz = construirEjemplo();

        System.out.println("-- Estructura completa --");
        imprimirEstructura(raiz, 0);

        System.out.println("\n-- Total de actividades: " + contarActividades(raiz));
        System.out.println("-- Profundidad maxima: " + profundidadMaxima(raiz) + " niveles");
        System.out.println("-- Buscar 'Quiz 1': " + buscarActividad(raiz, "Quiz 1"));
        System.out.println("-- Buscar 'Parcial': " + buscarActividad(raiz, "Parcial"));

        // ══════════════════════════════════════════════════════
        // CASO 2 - LIMITE
        // Programa con un solo nodo hijo que es directamente
        // una actividad (sin semestres ni cursos intermedios).
        // Se espera: 1 actividad, profundidad 2, busqueda exitosa.
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 2: LIMITE (un solo nodo, profundidad minima) ==========\n");
        Nodo raiz2 = new Nodo("Programa", "Programa Minimo");
        raiz2.agregar(new Nodo("Actividad", "Unica Actividad"));

        System.out.println("-- Estructura completa --");
        imprimirEstructura(raiz2, 0);

        System.out.println("\n-- Total de actividades: " + contarActividades(raiz2));
        System.out.println("-- Profundidad maxima: " + profundidadMaxima(raiz2) + " niveles");
        System.out.println("-- Buscar 'Unica Actividad': " + buscarActividad(raiz2, "Unica Actividad"));

        // ══════════════════════════════════════════════════════
        // CASO 3 - ERROR POTENCIAL
        // Programa sin ningun subcomponente (plan vacio).
        // Si la funcion no maneja nodos hoja correctamente,
        // puede lanzar excepcion o retornar valores incorrectos.
        // Se espera: 0 actividades, profundidad 1, busqueda false.
        // ══════════════════════════════════════════════════════
        System.out.println("\n========== CASO 3: ERROR POTENCIAL (programa sin subcomponentes) ==========\n");
        Nodo raiz3 = new Nodo("Programa", "Programa Vacio");

        System.out.println("-- Estructura completa --");
        imprimirEstructura(raiz3, 0);

        System.out.println("\n-- Total de actividades: " + contarActividades(raiz3));
        System.out.println("-- Profundidad maxima: " + profundidadMaxima(raiz3) + " niveles");
        System.out.println("-- Buscar 'Taller 1': " + buscarActividad(raiz3, "Taller 1"));
    }
}

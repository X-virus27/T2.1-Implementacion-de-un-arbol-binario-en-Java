package negocio;

import java.util.Objects;
import modelo.Nodo;

/** Árbol jerárquico: los datos no se ordenan como en un árbol de búsqueda. */
public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public Nodo<T> getRaiz() { return raiz; }

    public boolean esVacio() { return raiz == null; }

    public Nodo<T> crearRaiz(T dato) {
        if (!esVacio()) {
            throw new IllegalStateException("El árbol ya tiene una raíz.");
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getIzquierdo() != null) {
            throw new IllegalStateException("La posición izquierda de "
                    + padre.getDato() + " está ocupada.");
        }
        Nodo<T> hijo = new Nodo<>(dato);
        padre.setIzquierdo(hijo);
        return hijo;
    }

    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getDerecho() != null) {
            throw new IllegalStateException("La posición derecha de "
                    + padre.getDato() + " está ocupada.");
        }
        Nodo<T> hijo = new Nodo<>(dato);
        padre.setDerecho(hijo);
        return hijo;
    }

    private void validarPadre(Nodo<T> padre) {
        if (padre == null || !contiene(raiz, padre)) {
            throw new IllegalArgumentException("El padre debe pertenecer al árbol.");
        }
    }

    private boolean contiene(Nodo<T> actual, Nodo<T> buscado) {
        if (actual == null) { return false; }
        return actual == buscado || contiene(actual.getIzquierdo(), buscado)
                || contiene(actual.getDerecho(), buscado);
    }

    /** Devuelve la primera coincidencia en preorden, o null si no existe. */
    public Nodo<T> buscar(T dato) { return buscar(raiz, dato); }

    private Nodo<T> buscar(Nodo<T> actual, T dato) {
        if (actual == null) { return null; }
        if (Objects.equals(actual.getDato(), dato)) { return actual; }
        Nodo<T> encontrado = buscar(actual.getIzquierdo(), dato);
        return encontrado != null ? encontrado : buscar(actual.getDerecho(), dato);
    }

    public int contarNodos() { return contarNodos(raiz); }

    private int contarNodos(Nodo<T> actual) {
        if (actual == null) { return 0; }
        return 1 + contarNodos(actual.getIzquierdo()) + contarNodos(actual.getDerecho());
    }

    public int contarHojas() { return contarHojas(raiz); }

    private int contarHojas(Nodo<T> actual) {
        if (actual == null) { return 0; }
        if (actual.esHoja()) { return 1; }
        return contarHojas(actual.getIzquierdo()) + contarHojas(actual.getDerecho());
    }

    /** Altura en aristas: árbol vacío = -1; raíz sin hijos = 0. */
    public int altura() { return altura(raiz); }

    private int altura(Nodo<T> actual) {
        if (actual == null) { return -1; }
        return 1 + Math.max(altura(actual.getIzquierdo()), altura(actual.getDerecho()));
    }

    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(árbol vacío)");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
        System.out.println("[I] = hijo izquierdo; [D] = hijo derecho");
    }

    private void mostrarHijos(Nodo<T> actual, String prefijo) {
        if (actual.getIzquierdo() != null) {
            boolean ultimo = actual.getDerecho() == null;
            System.out.println(prefijo + (ultimo ? "`-- " : "+-- ")
                    + "[I] " + actual.getIzquierdo().getDato());
            mostrarHijos(actual.getIzquierdo(), prefijo + (ultimo ? "    " : "|   "));
        }
        if (actual.getDerecho() != null) {
            System.out.println(prefijo + "`-- [D] " + actual.getDerecho().getDato());
            mostrarHijos(actual.getDerecho(), prefijo + "    ");
        }
    }
}

package negocio;
import modelo.Nodo;

public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void crearRaiz(T dato) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
        } else {
            System.out.println("Error: La raíz ya existe.");
        }
    }

    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        if (padre == null) return null;
        if (padre.getIzquierdo() != null) {
            System.out.println("Error: El nodo '" + padre.getDato() + "' ya tiene una posición izquierda ocupada.");
            return null;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        if (padre == null) return null;
        if (padre.getDerecho() != null) {
            System.out.println("Error: El nodo '" + padre.getDato() + "' ya tiene una posición derecha ocupada.");
            return null;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) return 0;
        if (nodo.esHoja()) return 1;
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return -1; // -1 para que la hoja tenga altura 0 (aristas)
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    // Método auxiliar para buscar un nodo y facilitar la inserción/consultas
    public Nodo<T> buscar(T dato) {
        return buscar(raiz, dato);
    }

    private Nodo<T> buscar(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;
        if (nodo.getDato().equals(dato)) return nodo;
        
        Nodo<T> encontradoIzq = buscar(nodo.getIzquierdo(), dato);
        if (encontradoIzq != null) return encontradoIzq;
        
        return buscar(nodo.getDerecho(), dato);
    }

    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("El árbol está vacío.");
        } else {
            mostrarArbolRecursivo(raiz, "", true);
        }
    }

    private void mostrarArbolRecursivo(Nodo<T> nodo, String prefijo, boolean esIzquierdo) {
        if (nodo != null) {
            System.out.println(prefijo + (esIzquierdo ? "├── " : "└── ") + nodo.getDato());
            mostrarArbolRecursivo(nodo.getIzquierdo(), prefijo + (esIzquierdo ? "│   " : "    "), true);
            mostrarArbolRecursivo(nodo.getDerecho(), prefijo + (esIzquierdo ? "│   " : "    "), false);
        }
    }
}
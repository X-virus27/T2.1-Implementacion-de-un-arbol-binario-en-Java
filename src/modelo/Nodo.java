package modelo;

/** Un nodo genérico con un máximo de dos hijos. */
public class Nodo<T> {
    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
    }

    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    public int grado() {
        return (izquierdo == null ? 0 : 1) + (derecho == null ? 0 : 1);
    }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }
    public Nodo<T> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(Nodo<T> izquierdo) { this.izquierdo = izquierdo; }
    public Nodo<T> getDerecho() { return derecho; }
    public void setDerecho(Nodo<T> derecho) { this.derecho = derecho; }
}

package modelo;

public class Nodo<T> {
    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public T getDato() { return dato; }
    public void setDato(T dato) { this.dato = dato; }

    public Nodo<T> getIzquierdo() { return izquierdo; }
    public void setIzquierdo(Nodo<T> izquierdo) { this.izquierdo = izquierdo; }

    public Nodo<T> getDerecho() { return derecho; }
    public void setDerecho(Nodo<T> derecho) { this.derecho = derecho; }

    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    public int grado() {
        int g = 0;
        if (izquierdo != null) g++;
        if (derecho != null) g++;
        return g;
    }
}
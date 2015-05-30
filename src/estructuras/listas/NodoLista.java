package estructuras.listas;

public class NodoLista {

    private Object dato;
    private NodoLista siguiente;

    public NodoLista(Object n) {
        this.dato = n;
        this.siguiente = null;
    }

    public NodoLista(Object n, NodoLista sigNodo) {
        this.dato = n;
        this.siguiente = sigNodo;
    }

    public Object getDato() {
        return this.dato;
    }

    public void setDato(Object n) {
        this.dato = n;
    }

    public NodoLista getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoLista elemento) {
        this.siguiente = elemento;
    }
}

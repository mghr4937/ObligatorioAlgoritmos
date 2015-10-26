package estructuras.listas;

import java.util.Iterator;

public final class IteratorNodoListas implements Iterator<Object> {

    private NodoLista aux;

    public IteratorNodoListas(NodoLista inicio) {
        super();
        this.aux = inicio;
    }

    @Override
    public boolean hasNext() {
        return aux != null;
    }

    @Override
    public Object next() {
        Object ret = aux.getDato();
        aux = aux.getSiguiente();
        return ret;
    }

    @Override
    public void remove() {
        //Llamaría al método "borrar" de la ILista con el elemento actual
    }
}

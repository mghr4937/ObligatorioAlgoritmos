package estructuras.cola;

import estructuras.listas.NodoLista;

public class NodoCola {

	private Object dato;
	private NodoCola siguiente;
	
	public NodoCola(Object o) {
		this.dato = o;
		this.siguiente = null;
	}
	
	 public NodoCola(Object n, NodoCola sigNodo) {
	        this.dato = n;
	        this.siguiente = sigNodo;
	    }

	public Object getDato() {
		return dato;
	}

	public void setDato(Object o) {
		this.dato = o;
	}

	public NodoCola getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoCola next) {
		this.siguiente = next;
	}

	
}

package estructuras.comunes;

public class NodoObject {
	
	private Object dato;
	private NodoObject siguiente;

	public Object getDato() {
		return dato;
	}

	public void setDato(Object o) {
		this.dato = o;
	}

	public NodoObject getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(NodoObject siguiente) {
		this.siguiente = siguiente;
	}

	public NodoObject(Object o) {
		this.dato = o;
		this.siguiente = null;
	}

}

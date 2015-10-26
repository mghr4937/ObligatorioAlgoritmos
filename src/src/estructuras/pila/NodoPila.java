package estructuras.pila;

public class NodoPila {
	
	private int dato;
	private NodoPila sig;

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public NodoPila getSig() {
		return sig;
	}

	public void setSig(NodoPila sig) {
		this.sig = sig;
	}

	public NodoPila(int dato) {
		this.dato = dato;
		this.sig = null;
	}
}

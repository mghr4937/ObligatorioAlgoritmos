package estructuras.arbol;

public class NodoABB {
	
	private int clave;
	private Object dato;
	private NodoABB izq;
	private NodoABB der;
	
	public NodoABB(int clave, String dato, NodoABB izq, NodoABB der) {
		this.clave = clave;
		this.dato = dato;
		this.izq = izq;
		this.der = der;
	}
	
	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	public NodoABB getIzq() {
		return izq;
	}

	public void setIzq(NodoABB izq) {
		this.izq = izq;
	}

	public NodoABB getDer() {
		return der;
	}

	public void setDer(NodoABB der) {
		this.der = der;
	}
}

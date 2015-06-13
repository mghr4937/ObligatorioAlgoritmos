package estructuras.cola;

import estructuras.comunes.*;
import estructuras.listas.NodoLista;

public class ColaImpl extends MetodosComunes implements ICola {

	private NodoCola inicio;
	private NodoCola ultimo;
	private int tamanio;

	public NodoCola getPrimero() {
		return inicio;
	}

	public void setPrimero(NodoCola primero) {
		this.inicio = primero;
	}

	public NodoCola getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoCola ultimo) {
		this.ultimo = ultimo;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public ColaImpl() {
		super();
		this.inicio = null;
		this.ultimo = null;
		this.tamanio = 0;
	}

	/**
	 * Pre.: La cola no esta llena. Pos.: Inserta el carácter pasado como
	 * argumento en la cola.
	 * 
	 * @param o
	 */
	@Override
	public void enColar(Object o) {
		NodoCola nuevo = new NodoCola(o);
		if(this.getPrimero() == null){
			this.setPrimero(nuevo);
			this.setUltimo(nuevo);
		}else{
			this.getUltimo().setSiguiente(nuevo);
			this.setUltimo(nuevo);	
		}
			
		this.setTamanio(this.getTamanio() + 1);

	}
	@Override
	public boolean esVacia(){
		if (this.inicio == null)
			return true;
		else
			return false;
	}

	/**
	 * Pos.: Retorna true si y solo si la cola esta llena.
	 * 
	 * @return true si y solo si la cola esta llena
	 */
	@Override
	public boolean estaLlena() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Pre.: La cola no es vacía. Pos.: Retorna el elemento ubicado en el frente
	 * de la cola.
	 * 
	 * @return elemento ubicado en el frente de la cola
	 */
	/*@Override
	public NodoCola inicio() {
		if (primero == null)
			return null;
		else
			return (NodoCola) primero.getDato();
	}*/
	
	  public NodoCola ObtenerElementoPrimero() {
	        NodoCola primero = this.inicio;
	        return primero;
	    }

	/**
	 * Pre.: La cola no es vacía. Pos.: Elimina el elemento ubicado en el frente
	 * de la cola.
	 */
	@Override
	public NodoCola desEncolar() {
		NodoCola retorno = null;
		if (!this.esVacia()) {
			retorno = this.ultimo;
			if (this.inicio == this.ultimo) {
				this.inicio = null;
				this.ultimo = null;
			} else {
				NodoCola aux = this.inicio;
				while (aux.getSiguiente() != null) {
					aux = aux.getSiguiente();
				}
				this.ultimo = aux;
				this.ultimo.setSiguiente(null);				
			}
			this.setTamanio(this.getTamanio() - 1);
		}

		return retorno;
	}
	


	@Override
	public void vaciar() {
		this.inicio = null;
		this.ultimo = null;
	}

	@Override
	public void mostrar() {
		NodoCola aux = this.inicio;
		while (aux != null) {
			System.out.println(aux.getDato());
			aux = aux.getSiguiente();
		}
	}

}

package estructuras.pila;

import estructuras.comunes.*;

public class PilaImpl extends MetodosComunes implements IPila {
	
	private NodoPila inicio;
	private int tamanio = 0;

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	public void setInicio(NodoPila inicio) {
		this.inicio = inicio;
	}

	public PilaImpl() {
		this.inicio = null;
	}

	@Override
	public boolean esVacia() {
		if (this.inicio == null)
			return true;
		else
			return false;
	}

	@Override
	public void apilar(int n) {
		NodoPila nuevo = new NodoPila(n);
		nuevo.setSig(inicio);
		this.inicio = nuevo;
		this.setTamanio(this.getTamanio() + 1);
	}

	@Override
	public NodoPila desapilar() {
		NodoPila retorno = this.inicio;
		if (!this.esVacia()) {
			this.inicio = this.inicio.getSig();
			this.setTamanio(this.getTamanio() - 1);
		}

		return retorno;
	}

	@Override
	public void vaciar() {
		this.inicio = null;
	}

	@Override
	public void mostrar() {
		NodoPila aux = this.inicio;
		while (aux != null) {
			System.out.println(aux.getDato());
			aux = aux.getSig();
		}

	}

	@Override
	public int largo() {
		return this.getTamanio();
	}

	@Override
	public int tope() {
		return (int)this.getInicio().getDato();
	}

}

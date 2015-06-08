package dominio.clases;

import dominio.clases.Ciudad;
import estructuras.listas.ILista;
import estructuras.listas.ListaSimplementeEncadenada;

public class Ambulancia implements Comparable<Ambulancia> {

	private String sIdAmbulancia;
	private EstadoAmbulancia eEstado;
	private boolean bAsignada;
	private Ciudad ciudadActual;
	private ILista listaChoferes;
	
	
	public enum EstadoAmbulancia {
		DISPONIBLE,
		BUEN_ESTADO,
		NO_DISPONIBLE,
		EN_REPARACION,
		SIN_ARREGLO,
		ASIGNADA;
	}

	public String getsIdAmbulancia() {
		return sIdAmbulancia;
	}

	public void setsIdAmbulancia(String sIdAmbulancia) {
		this.sIdAmbulancia = sIdAmbulancia;
	}

	public boolean isbAsignada() {
		return bAsignada;
	}

	public void setbAsignada(boolean bAsignada) {
		this.bAsignada = bAsignada;
	}

	public Ciudad getCiudadActual() {
		return ciudadActual;
	}

	public void setCiudadActual(Ciudad ciudadActual) {
		this.ciudadActual = ciudadActual;
	}

	public Ambulancia(String sIdAmbulancia) {
		this.sIdAmbulancia = sIdAmbulancia;
	}
	
	public EstadoAmbulancia geteEstado() {
		return eEstado;
	}

	public void seteEstado(EstadoAmbulancia eEstado) {
		this.eEstado = eEstado;
	}

	public ILista getListaChoferes() {
		return listaChoferes;
	}

	public void setListaChoferes(ILista listaChoferes) {
		this.listaChoferes = listaChoferes;
	}

	public Ambulancia(String sIdAmbulancia, Ciudad ciudadActual,
			boolean asignada, EstadoAmbulancia estado) {
		this.sIdAmbulancia = sIdAmbulancia;
		this.ciudadActual = ciudadActual;
		this.bAsignada = asignada;
		this.eEstado = estado;
		this.listaChoferes = new ListaSimplementeEncadenada();
	}
	
	public Ambulancia(String sIdAmbulancia, Ciudad ciudadActual,
			boolean asignada) {
		this.sIdAmbulancia = sIdAmbulancia;
		this.ciudadActual = ciudadActual;
		this.bAsignada = asignada;getClass();
		this.listaChoferes = new ListaSimplementeEncadenada();
	}
	

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ambulancia)) {
			return false;
		}
		Ambulancia that = (Ambulancia) other;

		return this.getsIdAmbulancia().equals(that.getsIdAmbulancia());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bAsignada ? 1231 : 1237);
		result = prime * result
				+ ((ciudadActual == null) ? 0 : ciudadActual.hashCode());
		result = prime * result + ((eEstado == null) ? 0 : eEstado.hashCode());
		result = prime * result
				+ ((sIdAmbulancia == null) ? 0 : sIdAmbulancia.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Datos Ambulancia " + this.sIdAmbulancia + "\n" + 
			   "Estado: " + this.eEstado + "\n" +
			   "Ciudad " + this.ciudadActual;
	}

	@Override
	public int compareTo(Ambulancia other) {
		String primerMatricula = this.sIdAmbulancia;
		String segundaMatricula = other.sIdAmbulancia;

		return (primerMatricula).compareTo(segundaMatricula);
	}

}

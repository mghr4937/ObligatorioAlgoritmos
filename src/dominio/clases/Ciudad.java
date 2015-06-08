package dominio.clases;

import estructuras.listas.ILista;
import estructuras.listas.ListaSimplementeEncadenada;

public class Ciudad implements Comparable<Ciudad>{

	private int iCiudadId;
	private String sNombreCiudad;
	private ILista listaRutas;

	private static int numeradora = 0;

	public int getiCiudadId() {
		return iCiudadId;
	}

	private void setICiudadId(int iCiudadId) {
		this.iCiudadId = iCiudadId;
	}

	public String getSNombreCiudad() {
		return sNombreCiudad;
	}

	public void setSNombreCiudad(String sNombreCiudad) {
		this.sNombreCiudad = sNombreCiudad;
	}

	public static int getNumeradora() {
		return numeradora;
	}

	private static void setNumeradora(int numeradora) {
		Ciudad.numeradora = numeradora;
	}
	
	public ILista getListaRutas() {
		return listaRutas;
	}

	public void setListaRutas(ILista listaRutas) {
		this.listaRutas = listaRutas;
	}

	private void NumerarCiudad() {
		this.iCiudadId = ++Ciudad.numeradora;
	}

	public Ciudad() {
		this.NumerarCiudad();
	}
	public Ciudad(String sNombreCiudad) {
		this.sNombreCiudad = sNombreCiudad;		
		this.NumerarCiudad();
		this.listaRutas = new ListaSimplementeEncadenada();
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ciudad)) {
			return false;
		}
		Ciudad that = (Ciudad) other;
		return this.getiCiudadId() == that.getiCiudadId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + iCiudadId;		
		result = prime * result
				+ ((sNombreCiudad == null) ? 0 : sNombreCiudad.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return this.getiCiudadId() + " - "
				+ this.getSNombreCiudad();
	}
	
	@Override
	public int compareTo(Ciudad other) {
		int primerId = (int)this.getiCiudadId();
		int segundoId = (int)other.getiCiudadId();

		return ((Integer)(primerId)).compareTo((Integer)segundoId);
	}

}

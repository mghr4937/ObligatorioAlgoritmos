package dominio.comparadores;

import java.util.Comparator;

import dominio.clases.Ciudad;
import dominio.clases.Ruta;


public class RutaComparatorByMinutos implements Comparator<Object>{

	@Override
	public int compare(Object rutaUno, Object rutaDos) {

		Ruta unaRuta = (Ruta) rutaUno;
		Ruta otraRuta = (Ruta) rutaUno;

		return ((Integer)unaRuta.getiMinutosViaje()).compareTo((Integer)otraRuta.getiMinutosViaje());
	}
	
	
}

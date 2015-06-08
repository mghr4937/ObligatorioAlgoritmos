package dominio.comparadores;

import java.util.Comparator;

import dominio.clases.Ciudad;

public class CiudadComparatorById implements Comparator<Object> {

	@Override
	public int compare(Object ciudadUno, Object ciudadDos) {

		Ciudad unaCiudad = (Ciudad) ciudadUno;
		Ciudad otraCiudad = (Ciudad) ciudadDos;

		return ((Integer)unaCiudad.getiCiudadId()).compareTo((Integer)otraCiudad.getiCiudadId());
	}
}

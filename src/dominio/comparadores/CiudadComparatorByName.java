package dominio.comparadores;

import java.util.Comparator;

import dominio.clases.Ciudad;

public class CiudadComparatorByName implements Comparator<Object> {

	@Override
	public int compare(Object ciudadUno, Object ciudadDos) {

		Ciudad unaCiudad = (Ciudad) ciudadUno;
		Ciudad otraCiudad = (Ciudad) ciudadUno;

		return unaCiudad.compareTo(otraCiudad);
	}
}

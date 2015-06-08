package dominio.comparadores;

import java.util.Comparator;
import dominio.clases.Ambulancia;

public class AmbulanciaComparatorById implements Comparator<Object> {
	
	@Override
	public int compare(Object ambulanciaUno, Object ambulanciaDos) {

		Ambulancia unaAmbulancia = (Ambulancia) ambulanciaUno;
		Ambulancia otraAmbulancia = (Ambulancia) ambulanciaDos;

		return unaAmbulancia.getsIdAmbulancia().compareTo(otraAmbulancia.getsIdAmbulancia());
	}
}

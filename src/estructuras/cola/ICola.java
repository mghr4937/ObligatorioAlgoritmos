package estructuras.cola;

import estructuras.comunes.*;

public interface ICola extends IMetodosComunes{

	void enColar(Object o);
	
	boolean esVacia();
	
	boolean estaLlena();

	//Object inicio();

	NodoCola desEncolar();

	void vaciar();

	void mostrar();
	
	NodoCola ObtenerElementoPrimero();

}

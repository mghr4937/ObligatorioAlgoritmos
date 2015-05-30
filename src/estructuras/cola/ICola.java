package estructuras.cola;

import estructuras.comunes.*;

public interface ICola extends IMetodosComunes{

	public void enColar(Object o);
	
	public boolean esVacia();
	
	public boolean estaLlena();

	public Object inicio();

	public NodoCola desEncolar();

	public void vaciar();

	public void mostrar();

}

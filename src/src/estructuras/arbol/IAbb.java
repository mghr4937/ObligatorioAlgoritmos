package estructuras.arbol;

public interface IAbb {

	void add(int clave, String dato);
	
	boolean esVacia();
	
	boolean isHoja();
	
	boolean isEquilibrado();
	
	boolean existe(int clave);
	
	int nivel(int clave);
	
	int altura();
	
	int max(int a, int b);
	
	int peso();
	
	int largo();
	
	void preOrden();
	
	void inOrden();
	
	void postOrden();
	
	void caminos();
	
	boolean estaLlena();
	
	void borrarElemento(int clave);
	
	int cantidadHojas();
	
	void listarUnNivel(int nivel);
}

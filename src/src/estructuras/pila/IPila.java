package estructuras.pila;

public interface IPila {

	public boolean esVacia();

	public void apilar(int n);

	public NodoPila desapilar();

	public void vaciar();

	public void mostrar();

	public int largo();

	public int tope();
}

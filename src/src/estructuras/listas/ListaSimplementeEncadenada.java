package estructuras.listas;

import java.util.Iterator;

import estructuras.comunes.*;

public class ListaSimplementeEncadenada /*extends MetodosComunes*/ implements ILista {

    
    private NodoLista inicio;
  
    
    public ListaSimplementeEncadenada() {
        this.inicio = null;
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Retorna un int con la cantidad de elementos de la lista
    @Override
    public int largo() {
        NodoLista auxiliar = this.inicio;

        int contadorLongitud = 0;

        if (this.esVacia()) {
            return 0;
        } else {
            while (auxiliar != null) {
                contadorLongitud++;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return contadorLongitud;
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Elimina el primer Nodo de la lista
    @Override
    public void borrarPrincipio() {
        if (!esVacia()) {
            this.inicio = this.inicio.getSiguiente();
        }
    }
   
    //Precondicion: El elemento pasado como parametro pertenece a la lista
    //Postcondicion: Elimina de la lista la primer ocurrencia del elemento pasado por parámetro
    @Override
    public boolean borrarElemento(Object elemento) {
        NodoLista auxiliar = this.inicio;

        if (auxiliar.getDato().equals(elemento)) {
            this.borrarPrincipio();
            return true;
        } else {
            NodoLista anterior = new NodoLista(elemento);

            while (auxiliar != null) {
                if (auxiliar.getDato().equals(elemento)) {
                    NodoLista borrar = auxiliar.getSiguiente();
                    auxiliar.setDato(borrar);
                    anterior.setSiguiente(auxiliar.getSiguiente());
                    return true;
                }
                anterior = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return false;
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Elimina todos los Nodos de la lista, en caso de que la misma tuviera 1 o mas de uno
    @Override
    public void vaciarLista() {
        while (this.inicio != null) {
            borrarPrincipio();
        }
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Inserta el elemento en la primer posicion de la lista
    @Override
    public void insertarAlPrincipio(Object objeto) {
        NodoLista nuevo = new NodoLista(objeto);

        if (this.esVacia()) {
            this.inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            this.inicio = nuevo;
        }
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Inserta al elemento en la ultima posicion de la lista
    @Override
    public void insertarAlFinal(Object objeto) {
        NodoLista nuevo = new NodoLista(objeto);
        NodoLista auxiliar = this.inicio;

        if (this.esVacia()) {
            this.inicio = nuevo;
        } else {
            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            auxiliar.setSiguiente(nuevo);
        }
    }
   
    @Override
    public void insertarOrdenado(Object nuevo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Muestra por consola el elemento llamado
    @Override
    public void MostrarDatoLista() {
        NodoLista auxiliar = this.inicio;

        while (auxiliar != null) {
            System.out.println(auxiliar.getDato().toString());
            auxiliar = auxiliar.getSiguiente();
        }
    }
    
    //Precondicion: No existen precondiciones
    //Postcondicion: Obtiene el primer nodo de la lista
    @Override
    public NodoLista ObtenerElementoPrimero() {
        NodoLista primero = this.inicio;
        return primero;
    }
   
    //Precondicion: No existen precondiciones
    //Postcondicion: Devuelve el elemento de una lista
    @Override
    public Object buscar(Object dato) {
        NodoLista auxiliar = this.inicio;

        while (auxiliar != null) {
            if (auxiliar.getDato().equals(dato)) {
                return auxiliar.getDato();
            } else {
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return null;
    }
  
    //Precondicion: No existen precondiciones
    //Postcondicion: Devuelve la cantida de elementos de la lista
    @Override
    public int contador() {
        NodoLista auxiliar = this.inicio;
        int contador = 0;

        while (auxiliar != null) {
            contador++;
            auxiliar = auxiliar.getSiguiente();
        }
        return contador;
    }
   
    
	@Override
    public Iterator iterator() {
        return new IteratorNodoListas(inicio);
    }
	
	@Override
	public boolean esVacia(){
		if (this.inicio == null)
			return true;
		else
			return false;
	}
	
	 //Precondicion: No existen precondiciones
    //Postcondicion: Retorna TRUE si el dato pasado como parametro pertenece a la lista
    public boolean pertenece(Object elemento) {
        return perteneceAuxiliar(elemento, inicio);
    }

    private boolean perteneceAuxiliar(Object elemento, NodoLista inicio2) {
        if (inicio2 == null) {
            return false;
        } else {
            if (inicio2.getDato().equals(elemento)) {
                return true;
            } else {
                return perteneceAuxiliar(elemento, inicio2.getSiguiente());
            }
        }
    }
	

    

}

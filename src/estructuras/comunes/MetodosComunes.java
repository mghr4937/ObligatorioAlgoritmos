package estructuras.comunes;

public class MetodosComunes implements IMetodosComunes{
	
	public NodoObject nodoObject;
	public NodoObject inicio;
	
	public NodoObject getNodoObject() {
		return nodoObject;
	}

	public void setNodoObject(NodoObject nodoObject) {
		this.nodoObject = nodoObject;
	}

	public NodoObject getInicio() {
		return inicio;
	}

	public void setInicio(NodoObject inicio) {
		this.inicio = inicio;
	}

	public MetodosComunes(){
		this.nodoObject = null;
		this.inicio = null;
	}
	
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

    private boolean perteneceAuxiliar(Object elemento, NodoObject nodoObject) {
        if (nodoObject == null) {
            return false;
        } else {
            if (nodoObject.getDato().equals(elemento)) {
                return true;
            } else {
                return perteneceAuxiliar(elemento, nodoObject.getSiguiente());
            }
        }
    }

}

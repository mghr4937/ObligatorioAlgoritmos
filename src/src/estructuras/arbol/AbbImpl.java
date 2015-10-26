package estructuras.arbol;

import estructuras.comunes.*;

public class AbbImpl extends MetodosComunes implements IAbb {
	
	public NodoABB raiz;

	public AbbImpl() {
		this.raiz = null;
	}
	
	public void add(int clave, String dato){
		if(this.esVacia()){
			NodoABB nuevo = new NodoABB(clave, dato, null, null);
			this.raiz = nuevo;
		}else{
			AbbImpl aux = new AbbImpl();
			if (clave<this.raiz.getClave()){
				aux.raiz = this.raiz.getIzq();
				aux.add(clave, dato);
				this.raiz.setIzq(aux.raiz);
			}
			if (clave>this.raiz.getClave()){
				aux.raiz = this.raiz.getDer();
				aux.add(clave, dato);
				this.raiz.setDer(aux.raiz);
			}
			
		}
	}
	
	//Pre: Arbol no vacio
	public boolean isHoja(){
		return ((this.raiz.getIzq() == null) && (this.raiz.getDer() == null));
	}
	
	/*
	 * Árbol Binario Equilibrado: Es un árbol B en el que en todos sus nodos se cumple la siguiente propiedad:
	 *  | altura(subárbol_izq) – altura(subárbol_der) | <= 1
	 */	
	public boolean isEquilibrado(){
		if(!this.esVacia()){
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			return Math.abs(auxDer.altura() - auxIzq.altura())<=1;
		}
		return true;
	}
	
	public boolean existe(int clave){
		if(!this.esVacia()){
			AbbImpl aux = new AbbImpl();
			if (clave==this.raiz.getClave()){
				return true;
			}
			if (clave<this.raiz.getClave()){
				aux.raiz = this.raiz.getIzq();
			}
			if (clave>this.raiz.getClave()){
				aux.raiz = this.raiz.getDer();
			}
			return aux.existe(clave);
		}
		return false;
	}
	
	//Post: 0 no existe la clave en el arbol. Si la clave corresponde a la raiz retorna 1.
	public int nivel(int clave){
		int respuesta = 0;
		if(!this.esVacia()){
			AbbImpl aux = new AbbImpl();
			if (clave==this.raiz.getClave()){
				return 1;
			}
			
			if (clave<this.raiz.getClave()){
				aux.raiz = this.raiz.getIzq();
			}
			if (clave>this.raiz.getClave()){
				aux.raiz = this.raiz.getDer();
			}
			respuesta = aux.nivel(clave);
			if (respuesta >0){ 
				// si existe
				return respuesta+1;
			}
		}
		return respuesta;
	}
	
	/*
	 * 	Altura. La altura de un árbol se define como el nivel del nodo de mayor nivel. 
	 */
	public int altura(){
		if(!this.esVacia()){
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			return max(auxIzq.altura(), auxDer.altura())+1;
		}
		return 0;
	}
	
	public int max(int a, int b){
		if (a<b){
			return b;
		}
		return a;
	}
	
	/*
	 * Peso. Es el número de nodos del árbol sin contar la raíz 
	 */
	public int peso(){
		if(!this.esVacia()){
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			return auxIzq.largo() + auxDer.largo();
		}
		return 0;
	}
	
	public int largo(){
		if(!this.esVacia()){
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			return auxIzq.largo() + auxDer.largo() + 1;
		}
		return 0;
	}
	
	public void preOrden(){
		if(!this.esVacia()){
			System.out.print(this.raiz.getClave() + ":"+ this.raiz.getDato() +" - ");
		
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			auxIzq.preOrden();
		
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			auxDer.preOrden();
		}
	}
	
	public void inOrden(){
		if(!this.esVacia()){
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			auxIzq.inOrden();
			
			System.out.print(this.raiz.getClave() + ":"+ this.raiz.getDato() +" - ");
			
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			auxDer.inOrden();
		}
	}
	
	public void postOrden(){
		if(!this.esVacia()){
			AbbImpl auxIzq = new AbbImpl();
			auxIzq.raiz = this.raiz.getIzq();
			auxIzq.postOrden();
			
			AbbImpl auxDer = new AbbImpl();
			auxDer.raiz = this.raiz.getDer();
			auxDer.postOrden();
			
			System.out.print(this.raiz.getClave() + ":"+ this.raiz.getDato() +" - ");
		}
	}
	
	/* 	
	 * METODOS RECOMENDADOS PARA IMPLEMENTAR
	 */
	
	
	 /* 
	  * Camino. Es una secuencia de nodos, en el que dos nodos consecutivos cualesquiera son padre e hijo. 
	  * Imprime todos los caminos del arbol
	  */
	public void caminos(){
		
	}
	
	
	/*
	 * Árbol Binario Completo: Es un árbol en el que todos los nodos tienen dos hijos y todas las hojas están en el mismo nivel.  
	 */
	public boolean estaLlena(){
		return false;
	}

	
	/*
	 * Post: Si existe el elemento con igual clave lo elimina. Luego de la eliminacion el arbol debe ser ABB
	 */
	public void borrarElemento(int clave){
		
	}
	
	
	public int cantidadHojas(){
		return 0;
	}
	
	
	public void listarUnNivel(int nivel){
		
	}
}

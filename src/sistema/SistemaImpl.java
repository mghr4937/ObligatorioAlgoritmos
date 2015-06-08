package sistema;

import interfazObligatorio.*;
import dominio.clases.*;
import dominio.clases.Ambulancia.EstadoAmbulancia;
import estructuras.arbol.*;
import estructuras.cola.*;
import dominio.comparadores.*;
import estructuras.listas.*;
import estructuras.pila.*;

public class SistemaImpl implements ISistema {

	private ListaOrdenada listaAmbulancias;
	private ListaOrdenada listaCiudades;
	private ICola listaEmergencias;
	private ILista listaViajes;	

	// va a venir por parametros en el juego de pruebas, cuando se inicie el
	// sistema se debe setear
	public int iCantCiudad;

	public int getCantCiudad() {
		return iCantCiudad;
	}

	public void setCantCiudad(int cantCiudad) {
		this.iCantCiudad = cantCiudad;
	}

	public SistemaImpl() {
		this.listaAmbulancias = new ListaOrdenada(
				new AmbulanciaComparatorById());
		this.listaCiudades = new ListaOrdenada(new CiudadComparatorById());
		this.listaEmergencias = new ColaImpl();
		this.listaViajes = new ListaSimplementeEncadenada();
	}

	@Override
	public TipoRet crearSistemaDeEmergencias(int cantidadCiudades) {

		if (cantidadCiudades <= 0) {
			System.out.println("La cantidad de ciudades es inferior a 1");
			return TipoRet.ERROR1;
		}
		this.setCantCiudad(cantidadCiudades);
		return TipoRet.OK;
	}

	@Override
	public TipoRet destruirSistemaEmergencias() {
		this.listaAmbulancias.vaciarLista();
		this.listaCiudades.vaciarLista();
		this.listaEmergencias.vaciar();
		this.listaViajes.vaciarLista();

		return TipoRet.OK;
	}

	@Override
	public TipoRet registrarAmbulancia(String ambulanciaId, int ciudadID) {

		Ciudad laCiudad = getCiudad(ciudadID);

		if (laCiudad != null) {
			Ambulancia nuevaAmbulancia = new Ambulancia(ambulanciaId, laCiudad,
					false);
			nuevaAmbulancia.seteEstado(EstadoAmbulancia.BUEN_ESTADO);

			if (listaAmbulancias.pertenece(nuevaAmbulancia)) {
				System.out
						.println("Ya existe una ambulancia con identificador ambulanciaID");
				return TipoRet.ERROR2;
			}
			listaAmbulancias.insertarOrdenado(nuevaAmbulancia);
		} else {
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet deshabilitarAmbulancia(String ambulanciaId) {

		Ambulancia laAmbulancia = getAmbulancia(ambulanciaId);

		if (laAmbulancia == null) {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}

		if (laAmbulancia.geteEstado().equals(EstadoAmbulancia.NO_DISPONIBLE)) {
			System.out
					.println("�La ambulancia ambulanciaID ya est� en estado NO_DISPONIBLE");
			return TipoRet.ERROR2;
		}

		if (laAmbulancia.geteEstado().equals(EstadoAmbulancia.ASIGNADA)) {
			System.out
					.println("No es posible deshabilitar la ambulancia ambulanciaID�.");
			return TipoRet.ERROR3;
		}

		laAmbulancia.seteEstado(EstadoAmbulancia.NO_DISPONIBLE);
		return TipoRet.OK;
	}

	@Override
	public TipoRet habilitarAmbulancia(String ambulanciaID) {

		Ambulancia ambulanciaHabilitar = getAmbulancia(ambulanciaID);

		if (ambulanciaHabilitar != null) {
			if (ambulanciaHabilitar.geteEstado().equals(
					EstadoAmbulancia.DISPONIBLE)) {
				System.out
						.println("La ambulancia ambulanciaID ya est� habilitada");
				return TipoRet.ERROR2;
			} else {
				ambulanciaHabilitar.seteEstado(EstadoAmbulancia.DISPONIBLE);
				return TipoRet.OK;
			}
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet eliminarAmbulancia(String ambulanciaID) {
		Ambulancia aux = this.getAmbulancia(ambulanciaID);
		if (aux != null) {
			if (!aux.geteEstado().equals(EstadoAmbulancia.ASIGNADA)) {
				this.listaAmbulancias.borrarElemento(aux);
				return TipoRet.OK;
			} else {
				System.out
						.println("No es posible deshabilitar la ambulancia ambulanciaID");
				return TipoRet.ERROR2;
			}
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet buscarAmbulancia(String ambulanciaID) {

		Ambulancia ambulanciaBuscada = getAmbulancia(ambulanciaID);

		if (ambulanciaBuscada != null) {
			System.out.println(ambulanciaBuscada.toString());

			int cantEmergencias = getCantEmergenciasPorAmbulancia(ambulanciaID);

			System.out.println("#Emergencias: " + cantEmergencias);
			System.out.println("");
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet informeAmbulancia() {

		if (this.listaAmbulancias.esVacia()) {
			System.out.println("Listado de Ambulancias:");
			System.out.println("No se han registrado ambulancias");
		} else {
			NodoLista aux = listaAmbulancias.ObtenerElementoPrimero();

			while (aux != null) {
				Ambulancia ambulancia = (Ambulancia) aux.getDato();
				System.out.println(ambulancia.getsIdAmbulancia() + " - "
						+ ambulancia.geteEstado() + " - "
						+ ambulancia.getCiudadActual());

				aux = aux.getSiguiente();
			}
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet informeAmbulancia(int ciudadID) {
		Ciudad ciudad = this.getCiudad(ciudadID);
		int contador = 0;
		int disponibles = 0;
		if (ciudad != null) {
			NodoLista aux = (NodoLista) this.listaAmbulancias
					.ObtenerElementoPrimero();
			Ambulancia ambulanciaBuscada;
			System.out.println("Informe de ambulancias: " + ciudad.toString());
			while (aux != null) {
				ambulanciaBuscada = (Ambulancia) aux.getDato();
				if (ambulanciaBuscada.getCiudadActual().equals(ciudad)) {
					contador++;
					if (ambulanciaBuscada.geteEstado().equals(
							EstadoAmbulancia.DISPONIBLE)) {
						System.out
								.println(ambulanciaBuscada.getsIdAmbulancia());
						disponibles++;
					}
				}
				aux = aux.getSiguiente();
			}
			System.out.println("Total Ambulancias disponibles: " + disponibles);
			return TipoRet.OK;
		} else {
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}
	}

	// Precondici�n: S�lo se agendar�n emergencias a ciudades que est�n
	// contiguas, y no se le agendar�n dos emergencias en la misma ciudad
	// consecutivamente.
	@Override
	public TipoRet recibirEmergencia(String ambulanciaID, int ciudadID) {

		Ambulancia ambulancia = getAmbulancia(ambulanciaID);
		Ciudad ciudad = getCiudad(ciudadID);

		if (ciudad != null) {
			if (ambulancia != null) {
				if (ambulancia.geteEstado().equals(EstadoAmbulancia.DISPONIBLE)) {
					ambulancia.setCiudadActual(ciudad);
					ambulancia.seteEstado(EstadoAmbulancia.ASIGNADA);

					Emergencia nuevaEmergencia = new Emergencia(ciudad,
							ambulanciaID);
					this.listaEmergencias.enColar(nuevaEmergencia);

					cambiarUbicacion(ambulanciaID, ciudadID);
				} else {
					System.out.println("La ambulancia no est� disponible");
					return TipoRet.ERROR3;
				}
			} else {
				System.out
						.println("No existe una ambulancia con identificador ambulanciaID");
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet cambiarUbicacion(String ambulanciaID, int ciudadID) {
		Ciudad ciudad = this.getCiudad(ciudadID);
		if (ciudad != null) {
			Ambulancia ambulancia = this.getAmbulancia(ambulanciaID);
			if (ambulancia != null) {
				ambulancia.setCiudadActual(this.getCiudad(ciudadID));
				return TipoRet.OK;
			} else {
				System.out
						.println("No existe una ambulancia con identificador "
								+ ambulanciaID);
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("La ciudad " + ciudadID + "no existe");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet agregarCiudad(String ciudadNombre) {
		if (this.getCantCiudad() < this.listaCiudades.largo()) {
			System.out
					.println("No se puede ingresar la ciudadNombre al sistema por no tener mas capacidad");
			return TipoRet.ERROR1;
		} else {
			Ciudad nuevaCiudad = new Ciudad(ciudadNombre);
			if (this.listaCiudades.pertenece(nuevaCiudad)) {
				System.out
						.println("Ya existe una ciudad con el nombre Ingresado");
				return TipoRet.ERROR2;
			} else {
				this.listaCiudades.insertarOrdenado(nuevaCiudad);
				return TipoRet.OK;
			}
		}
	}

	@Override
	public TipoRet listarCiudades() {

		if (this.listaCiudades.esVacia()) {
			System.out.println("No existen ciudades en el mapa");
		} else {
			NodoLista aux = this.listaCiudades.ObtenerElementoPrimero();

			while (aux != null) {
				Ciudad ciudad = (Ciudad) aux.getDato();
				System.out.println(ciudad.toString());
				aux = aux.getSiguiente();
			}
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet agregarRuta(int ciudadOrigen, int ciudadDestino,
			int minutosViaje) {

		if (minutosViaje <= 0) {
			System.out.println("La duraci�n del viaje debe ser mayor que 0");
			return TipoRet.ERROR1;
		} else {
			Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen);
			Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);

			if (ciudadOrigenObject == null) {
				System.out.println("La ciudad ciudadOrigen no existe");
				return TipoRet.ERROR2;
			}
			if (ciudadDestinoObject == null) {
				System.out.println("La ciudad ciudadDestino no existe");
				return TipoRet.ERROR3;
			}

			Ruta nuevaRutaOrigenDestino = new Ruta(ciudadOrigenObject,
					ciudadDestinoObject, minutosViaje);
			Ruta nuevaRutaCaminoInvertido = new Ruta(ciudadDestinoObject,
					ciudadOrigenObject, minutosViaje);

			// En la ciudad1 --> de A a B y de B a A
			// En la ciudad2 --> de A a B y de B a A
			ciudadOrigenObject.getListaRutas().insertarAlFinal(
					nuevaRutaOrigenDestino);
			ciudadOrigenObject.getListaRutas().insertarAlFinal(
					nuevaRutaCaminoInvertido);
			ciudadDestinoObject.getListaRutas().insertarAlFinal(
					nuevaRutaOrigenDestino);
			ciudadDestinoObject.getListaRutas().insertarAlFinal(
					nuevaRutaCaminoInvertido);
			return TipoRet.OK;
		}
	}

	// Precondicion: Las ciudades deben ser limitrofes para setear su conexion
	// en minutos desde este metodo
	@Override
	public TipoRet modificarDemora(int ciudadOrigen, int ciudadDestino, int minutosViaje) {

		int contadorSeguridad = 0;

		if (minutosViaje <= 0) {
			System.out.println("La duraci�n del viaje debe ser mayor que 0");
			return TipoRet.ERROR1;
		} else{
			Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen);
			Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);

			if (ciudadOrigenObject == null) {
				System.out.println("La ciudad ciudadOrigen no existe");
				return TipoRet.ERROR2;
			}

			if (ciudadDestinoObject == null) {
				System.out.println("La ciudad ciudadDestino no existe");
				return TipoRet.ERROR3;
			}

			Ruta laRutaOrigenDestino = getRuta(ciudadOrigenObject, ciudadDestinoObject);
			if (laRutaOrigenDestino != null) {
				laRutaOrigenDestino.setiMinutosViaje(minutosViaje);
				System.out.println(laRutaOrigenDestino.toString());
				contadorSeguridad++;
			}

			Ruta laRutaDestinoOrigen = getRuta(ciudadDestinoObject, ciudadOrigenObject);
			if (laRutaOrigenDestino != null) {
				laRutaDestinoOrigen.setiMinutosViaje(minutosViaje);
				System.out.println(laRutaDestinoOrigen.toString());
				contadorSeguridad++;
			}
			if (contadorSeguridad == 2){
				System.out.println("ContadorSeguridad " + contadorSeguridad);
				return TipoRet.OK;
			}
		}
		System.out.println("Error inesperado");
		return TipoRet.ERROR4;
	}

	@Override
	public TipoRet ambulanciaMasCercana(int ciudadID) {

		Ciudad ciudad = getCiudad(ciudadID);
		
		if (ciudad != null) {
			NodoLista auxAmbulancia = listaAmbulancias.ObtenerElementoPrimero();
			Ambulancia ambulanciaEvaluada = null;
			while (auxAmbulancia != null) {
				ambulanciaEvaluada = (Ambulancia) auxAmbulancia.getDato();

				if (ambulanciaEvaluada.getCiudadActual().equals(ciudad)) {
					mostrarDatosAmbulanciaMasCercana(ambulanciaEvaluada,ciudad, ciudad, 0);
					return TipoRet.OK;
				}
				auxAmbulancia = auxAmbulancia.getSiguiente();
			}

			// Si no hay ninguna ambulancia en la ciudad, empiezo a buscar la mas cercana en ciudades limitrofes.
			
			NodoLista nodoRuta = ciudad.getListaRutas().ObtenerElementoPrimero();
			ILista listaTemporalRutas = new ListaSimplementeEncadenada();
			Ambulancia ambulanciaMasCercana = null;
			
			while(ambulanciaMasCercana == null){
				while(nodoRuta != null){
					Ruta auxRuta = (Ruta) nodoRuta.getDato();
					if (auxRuta.getCiudadOrigen().equals(ciudad)){
						listaTemporalRutas.insertarAlFinal(auxRuta);
					}
					nodoRuta = nodoRuta.getSiguiente();
				}
				
				NodoLista auxListaTemp = listaTemporalRutas.ObtenerElementoPrimero();
				Ruta rutaEvaluada = new Ruta();
				int menorDistancia = 9999999;
				Ciudad ciudadEnLaQuePuedeHaberAmbulancia = new Ciudad();
				while(auxListaTemp != null){
					 rutaEvaluada = (Ruta) auxListaTemp.getDato();
					if(rutaEvaluada.getiMinutosViaje() < menorDistancia){
						menorDistancia = rutaEvaluada.getiMinutosViaje();
						ciudadEnLaQuePuedeHaberAmbulancia = rutaEvaluada.getCiudadDestino();
					}
					auxListaTemp = auxListaTemp.getSiguiente();
				}
				
				ILista listaAmbulanciasCompararIds = new ListaOrdenada(new AmbulanciaComparatorById());
				NodoLista ambulanciasDeCiudadLimitrofe = this.listaAmbulancias.ObtenerElementoPrimero();
				while(ambulanciasDeCiudadLimitrofe != null){
					Ambulancia ambulanciaConsiderada = (Ambulancia) ambulanciasDeCiudadLimitrofe.getDato();
					if(ambulanciaConsiderada.getCiudadActual().equals(ciudadEnLaQuePuedeHaberAmbulancia)){
						listaAmbulanciasCompararIds.insertarOrdenado(ambulanciaConsiderada);
					}
					ambulanciasDeCiudadLimitrofe = ambulanciasDeCiudadLimitrofe.getSiguiente();
				}
			ambulanciaMasCercana = (Ambulancia) listaAmbulanciasCompararIds.ObtenerElementoPrimero().getDato();
			mostrarDatosAmbulanciaMasCercana(ambulanciaMasCercana,ciudad, ciudadEnLaQuePuedeHaberAmbulancia, rutaEvaluada.getiMinutosViaje());
			}
			return TipoRet.OK;
			} else {
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}
	}


	private void mostrarDatosAmbulanciaMasCercana(Ambulancia ambulancia,Ciudad ciudadIndicada, Ciudad ciudadAmbulancia, int demora) {
		System.out.println("La ambulancia m�s cercana a " + ciudadIndicada + " se encuentra en "
				+ ambulancia.getCiudadActual().toString());
		System.out.println("Matr�cula: " + ambulancia.getsIdAmbulancia());
		System.out.println("Demora del viaje: " + demora + " minutos");
	}

	private void inicilizarVectores(boolean [] visitadas, int []distancia, String [ ]previo){
		for( int i = 0 ; i <= this.getCantCiudad() ; ++i ){
	        distancia[ i ] = 999999; 
	        visitadas[ i ] = false; 
	        previo[ i ] = null;      
	    }
	}	
	
	@Override
	public TipoRet rutaMasRapida(int ciudadOrigen, int ciudadDestino) {
		boolean [] visitadas  = new boolean[this.getCantCiudad()];
		int [] distancia  = new int[99];
		String [] previo = new String [99];				
		ColaImpl colaPrioridad = new ColaImpl();
		this.inicilizarVectores(visitadas, distancia, previo);
		int actual;
		int adyacente;
		int peso;
		int ciudadCosto [] = {ciudadOrigen, 0};
		NodoCola nodoCola = new NodoCola(ciudadCosto);
		colaPrioridad.enColar(nodoCola);
		distancia[ciudadOrigen] = 0;
		while (!colaPrioridad.esVacia()) {
			actual = (int) colaPrioridad.desEncolar().getDato();
			Ciudad ciudadActual = this.getCiudad(actual);
			if (visitadas[actual] == false) {
				visitadas[actual] = true;
				NodoLista nodoLista = ciudadActual.getListaRutas().ObtenerElementoPrimero();
				while (nodoLista != null) {
					Ruta rutaAux = (Ruta) nodoLista.getDato();
					adyacente = rutaAux.getCiudadDestino().getiCiudadId();
					peso = rutaAux.getiMinutosViaje();
					if (visitadas[adyacente] == false) {
						if (distancia[actual] + peso < distancia[adyacente]) {
							distancia[adyacente] = distancia[actual] + peso;
							//int ciudadCostoAct[] = {actual, distancia[actual]};
							previo[adyacente] = actual + "-" + distancia[actual] ;
							int ciudadCostoAdy[] = { adyacente,	distancia[adyacente] };
							colaPrioridad.enColar(new NodoCola(ciudadCostoAdy));
						}
					}
					nodoLista = nodoLista.getSiguiente();
				}
			}
		}
		System.out.println("Ruta m�s r�pida:");		
	    imprimirRuta(ciudadDestino, previo);	    
		return TipoRet.OK;		
	}

	private void imprimirRuta( int destino, String previo[] ){
	    if( previo [ destino ]!=null){    //si aun poseo un vertice previo
	    	String str =  previo [ destino ];
	    	int indexSeparador = str.indexOf("-");
	    	int siguiente = Integer.parseInt(str.substring(0,indexSeparador-1));
	    	imprimirRuta(siguiente , previo );
	    }//recursivamente sigo explorando	   
	    	System.out.printf(previo[destino]);        //terminada la recursion imprimo los vertices recorridos
	 }
	
				
	@Override
	public TipoRet informeCiudades() {
		Ciudad ciudad;
		Ruta rutaDirecta;
		int amb;
		NodoLista nodoCiudad = this.listaCiudades.ObtenerElementoPrimero();
		while (nodoCiudad  !=null){
			ciudad = (Ciudad)nodoCiudad .getDato();
			System.out.println("Informe Ciudad: " + ciudad.getiCiudadId());
			NodoLista nodoRutaDir = ciudad.getListaRutas().ObtenerElementoPrimero();
			while (nodoRutaDir != null){
				rutaDirecta = (Ruta)nodoRutaDir.getDato();
				System.out.println("Ruta directa a " + rutaDirecta.getCiudadDestino().getiCiudadId() +
						",minutos " + rutaDirecta.getiMinutosViaje());				
			}
			amb = this.getAmbulanciasCiudadEstado(ciudad.getiCiudadId(), EstadoAmbulancia.DISPONIBLE).contador();
			System.out.println("Ambulancias disponibles: " + amb);
			amb = this.getAmbulanciasCiudadEstado(ciudad.getiCiudadId(), EstadoAmbulancia.NO_DISPONIBLE).contador();
			System.out.println("Ambulancias no disponibles: " + amb);
			nodoCiudad = nodoCiudad.getSiguiente();
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet ciudadesEnRadio(int ciudadID, int duracionViaje) {
		Ciudad ciudad = this.getCiudad(ciudadID);		
		if(ciudad != null){
			if(duracionViaje > 0){
				Ruta ruta;
				System.out.println("Ciudades en radio de " + duracionViaje + " minutos:");
				NodoLista nodoRuta = ciudad.getListaRutas().ObtenerElementoPrimero();
				while(nodoRuta != null){
					ruta = (Ruta)nodoRuta.getDato();
					if(ruta.getiMinutosViaje() <= duracionViaje){
						System.out.println("Ciudad " + ruta.getCiudadDestino().getiCiudadId() + 
								" a " + ruta.getiMinutosViaje() + " minutos.");
					}
					nodoRuta = nodoRuta.getSiguiente();
				}				
				return TipoRet.OK;
			}else{
				System.out.println("La duracion del viaje debe ser mayor que 0");
				return TipoRet.ERROR2;
			}			
		}else{
			System.out.println("La ciudad " + ciudadID + " no existe.");
			return TipoRet.ERROR1;
		}	
	}

	// Precondici�n: No existe un chofer de c�dula cedula como chofer habilitado
	// para conducir la ambulancia ambulanciaID
	@Override
	public TipoRet registrarChofer(String ambulanciaID, String nombre,
			String cedula) {

		NodoLista auxAmbulancia = listaAmbulancias.ObtenerElementoPrimero();
		Ambulancia miAmbulancia = (Ambulancia) auxAmbulancia.getDato();

		if (miAmbulancia != null) {
			Chofer nuevoChofer = new Chofer(cedula, nombre, miAmbulancia);
			miAmbulancia.getListaChoferes().insertarAlFinal(nuevoChofer);
			return TipoRet.OK;
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet eliminarChofer(String ambulanciaID, String cedula) {

		Ambulancia ambulancia = getAmbulancia(ambulanciaID);

		if (ambulancia != null) {
			NodoLista auxChofer = ambulancia.getListaChoferes()
					.ObtenerElementoPrimero();

			while (ambulancia.getListaChoferes() != null) {
				Chofer chofer = (Chofer) auxChofer.getDato();
				if (chofer.getsCedula().equals(cedula)) {
					ambulancia.getListaChoferes().borrarElemento(chofer);
					return TipoRet.OK;
				}
			}
			auxChofer = auxChofer.getSiguiente();
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
		System.out
				.println("El chofer ingresado no est� asociado a la ambulancia");
		return TipoRet.ERROR2;
	}

	@Override
	public TipoRet informeChoferes(String ambulanciaID) {

		Ambulancia ambulancia = getAmbulancia(ambulanciaID);

		if (ambulancia != null) {

			NodoLista auxChofer = ambulancia.getListaChoferes()
					.ObtenerElementoPrimero();

			while (auxChofer != null) {
				Chofer iteradorChoferes = (Chofer) auxChofer.getDato();
				System.out.println(iteradorChoferes.toString());
			}
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	public Ciudad getCiudad(int ciudadID) {

		Ciudad unaCiudad;

		if (this.listaCiudades.esVacia()) {
			return null;
		} else {
			NodoLista auxiliar = this.listaCiudades.ObtenerElementoPrimero();

			while (auxiliar != null) {
				unaCiudad = (Ciudad) auxiliar.getDato();

				if (unaCiudad.getiCiudadId() == ciudadID) {
					return unaCiudad;
				}
				auxiliar = auxiliar.getSiguiente();
			}
		}
		return null;
	}

	public Ambulancia getAmbulancia(String ambulanciaId) {

		Ambulancia ambulanciaBuscada;

		if (this.listaAmbulancias.esVacia())
			return null;
		else {
			NodoLista aux = (NodoLista) this.listaAmbulancias
					.ObtenerElementoPrimero();

			while (aux != null) {
				ambulanciaBuscada = (Ambulancia) aux.getDato();

				if (ambulanciaBuscada.getsIdAmbulancia().equals(ambulanciaId))
					return ambulanciaBuscada;
				else
					aux = aux.getSiguiente();
			}
		}
		return null;
	}

	private int getCantEmergenciasPorAmbulancia(String ambulanciaID) {

		int retorno = 0;
		Emergencia emergencia;
		Ambulancia ambulancia = getAmbulancia(ambulanciaID);

		NodoCola aux = (NodoCola) this.listaEmergencias.inicio();

		while (aux != null) {
			emergencia = (Emergencia) aux.getDato();

			if (emergencia.getsAmbulanciaId().equals(ambulancia)) {
				retorno++;
			}
		}
		return retorno;
	}

	private Ruta getRuta(Ciudad ciudadOrigenObject, Ciudad ciudadDestinoObject) {

		NodoLista iteradorRutas = ciudadOrigenObject.getListaRutas().ObtenerElementoPrimero();

		if (iteradorRutas != null) {
			while (iteradorRutas != null) {
				Ruta ruta = (Ruta) iteradorRutas.getDato();
				if (ruta.getCiudadOrigen().equals(ciudadOrigenObject)&& ruta.getCiudadDestino().equals(ciudadDestinoObject)) {
					return ruta;
				}
				iteradorRutas = iteradorRutas.getSiguiente();
			}
		}
		return null;
	}
	
	public ILista getAmbulanciasCiudadEstado(int ciudadID, EstadoAmbulancia estado){
		ILista ret = new ListaSimplementeEncadenada();
		Ambulancia amb;
		NodoLista nodoAmb = this.listaAmbulancias.ObtenerElementoPrimero();
		while (nodoAmb != null){
			amb = (Ambulancia)nodoAmb.getDato();
			if(amb.getCiudadActual().getiCiudadId() == ciudadID && amb.geteEstado() == estado){
				ret.insertarOrdenado(nodoAmb);
			}			
			nodoAmb = nodoAmb.getSiguiente();
		}	
		return ret;		
	}
	
	//pre-condicion: las ciudades una vez generadas no se pueden eliminar
	private int[][] GenerarMatrizRutas() {
		ListaSimplementeEncadenada listaRutas = this.cargarListaRutas();		
		int largo = this.listaCiudades.largo() - 1;
		int x = 0;
		int y = 0;		
		Ruta ruta = new Ruta();
		int[][] matriz = new int[largo][largo];
		//NodoLista nodoRuta = (NodoLista) listaRutas.ObtenerElementoPrimero();
		while (x <= largo) {
			while(y <=largo ){
				ruta.setCiudadOrigen(this.getCiudad(x));
				ruta.setCiudadDestino(this.getCiudad(y));
				ruta = (Ruta) listaRutas.buscar(ruta);
				if (ruta!=null) {					
					matriz[x][y] = ruta.getiMinutosViaje();
				} else {
					matriz[x][y] = 0;
				}
				y++;
			}			
			x++;
		}
		return matriz;
	}
	
	private ListaSimplementeEncadenada cargarListaRutas() {
		ListaSimplementeEncadenada rutas = new ListaSimplementeEncadenada();
		NodoLista nodoCiudad = (NodoLista) this.listaCiudades
				.ObtenerElementoPrimero();
		NodoLista nodoRuta;
		Ruta ruta;
		Ciudad ciudad;
		while (nodoCiudad != null) {
			ciudad = (Ciudad) nodoCiudad.getDato();
			nodoRuta = ciudad.getListaRutas().ObtenerElementoPrimero();
			while (nodoRuta != null) {
				ruta = (Ruta) nodoRuta.getDato();
				if (!rutas.pertenece(ruta)) {
					rutas.insertarAlPrincipio(nodoRuta);
				}
				nodoRuta = nodoRuta.getSiguiente();
			}
			nodoCiudad = nodoCiudad.getSiguiente();
		}
		return rutas;
	}

	/*
	 * private Viaje getViaje(int ciudadOrigen, int ciudadDestino) {
	 * 
	 * Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen); Ciudad
	 * ciudadDestinoObject = getCiudad(ciudadDestino); Viaje viajeBuscado = new
	 * Viaje();
	 * 
	 * if (this.listaViajes.esVacia()) return null; else { NodoLista aux =
	 * (NodoLista) this.listaViajes.ObtenerElementoPrimero();
	 * 
	 * Viaje iteradorViaje;
	 * 
	 * while (aux != null) { iteradorViaje = (Viaje) aux.getDato();
	 * 
	 * if (viajeBuscado.equals(iteradorViaje)) { return viajeBuscado; } else aux
	 * = aux.getSiguiente(); } } return null; }
	 */

}

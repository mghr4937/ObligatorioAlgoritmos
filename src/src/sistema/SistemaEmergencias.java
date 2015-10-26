package sistema;

import interfazObligatorio.*;
import dominio.clases.*;
import dominio.clases.Ambulancia.EstadoAmbulancia;
import estructuras.arbol.*;
import estructuras.cola.*;
import dominio.comparadores.*;
import estructuras.listas.*;
import estructuras.pila.*;

public class SistemaEmergencias implements Sistema {

	private ListaOrdenada listaAmbulancias;
	private ListaOrdenada listaCiudades;
	private ICola listaEmergencias;
	private ILista listaViajes;

	public int iCantCiudad;

	public int getCantCiudad() {
		return iCantCiudad;
	}

	public void setCantCiudad(int cantCiudad) {
		this.iCantCiudad = cantCiudad;
	}

	public SistemaEmergencias() {
		this.listaAmbulancias = new ListaOrdenada(new AmbulanciaComparatorById());
		this.listaCiudades = new ListaOrdenada(new CiudadComparatorById());
		this.listaEmergencias = new ColaImpl();
		this.listaViajes = new ListaSimplementeEncadenada();
	}

	//Pre – condiciones: cantCiudades debe ser mayor 0.
	//Post - condiciones: Crea el sistema con una cantidad limite de ciudades igual a cantCiudades.
	@Override
	public TipoRet crearSistemaDeEmergencias(int cantidadCiudades) {

		if (cantidadCiudades <= 0) {
			System.out.println("La cantidad de ciudades es inferior a 1");
			return TipoRet.ERROR1;
		}
		this.setCantCiudad(cantidadCiudades);
		return TipoRet.OK;
	}

	//Pre – condiciones: Debe existir una instancia del sistema de emergencia ya creada.
	//Post - condiciones: Destruye el sistema de emergencia y todos sus elementos liberando la memoria utilizada.
	@Override
	public TipoRet destruirSistemaEmergencias() {
		this.listaAmbulancias.vaciarLista();
		this.listaCiudades.vaciarLista();
		this.listaEmergencias.vaciar();
		this.listaViajes.vaciarLista();

		return TipoRet.OK;
	}

	//Pre – condiciones: ciudadID debe ser una ciudadID valida.
	//Post - condiciones: Crea una nueva ambulancia con id ambulanciaID, estado DISPONIBLE y le asigna la ciudad pasada como parametro ciudadID.
	@Override
	public TipoRet registrarAmbulancia(String ambulanciaId, int ciudadID) {

		Ciudad laCiudad = getCiudad(ciudadID);

		if (laCiudad != null) {
			Ambulancia nuevaAmbulancia = new Ambulancia(ambulanciaId, laCiudad);
			nuevaAmbulancia.seteEstado(EstadoAmbulancia.DISPONIBLE);

			if (listaAmbulancias.pertenece(nuevaAmbulancia)) {
				System.out
						.println("Ya existe una ambulancia con identificador "
								+ ambulanciaId);
				return TipoRet.ERROR2;
			}
			listaAmbulancias.insertarOrdenado(nuevaAmbulancia);
		} else {
			System.out.println("La ciudad " + ciudadID + " no existe");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	//Pre – condiciones: La ambulancia identificada con ambulanciaID debe de existir previamente en el sistema.
	//Post - condiciones: Cambia el estado de la ambulancia a NO_DISPONIBLE.
	@Override
	public TipoRet deshabilitarAmbulancia(String ambulanciaId) {

		Ambulancia laAmbulancia = getAmbulancia(ambulanciaId);

		if (laAmbulancia == null) {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaId);
			return TipoRet.ERROR1;
		}

		if (laAmbulancia.geteEstado().equals(EstadoAmbulancia.NO_DISPONIBLE)) {
			System.out.println("“La ambulancia " + ambulanciaId
					+ " ya está en estado NO_DISPONIBLE");
			return TipoRet.ERROR2;
		}

		if (laAmbulancia.geteEstado().equals(EstadoAmbulancia.ASIGNADA)) {
			System.out.println("No es posible deshabilitar la ambulancia "
					+ ambulanciaId);
			return TipoRet.ERROR3;
		}

		laAmbulancia.seteEstado(EstadoAmbulancia.NO_DISPONIBLE);
		return TipoRet.OK;
	}

	//Pre – condiciones: La ambulancia identificada con ambulanciaID debe de existir previamente en el sistema.
	//Post - condiciones: Cambia el estado de la ambulancia a DISPONIBLE.
	@Override
	public TipoRet habilitarAmbulancia(String ambulanciaID) {

		Ambulancia ambulanciaHabilitar = getAmbulancia(ambulanciaID);

		if (ambulanciaHabilitar != null) {
			if (ambulanciaHabilitar.geteEstado().equals(
					EstadoAmbulancia.DISPONIBLE)) {
				System.out.println("La ambulancia " + ambulanciaID
						+ " ya está habilitada");
				return TipoRet.ERROR2;
			} else {
				ambulanciaHabilitar.seteEstado(EstadoAmbulancia.DISPONIBLE);
				return TipoRet.OK;
			}
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
	}

	//Pre – condiciones: La ambulancia identificada con ambulanciaID debe de existir previamente en el sistema.
	//Post - condiciones: Destruye la ambulancia identificada con ambulanciaID con todos sus elementos y referencias.
	@Override
	public TipoRet eliminarAmbulancia(String ambulanciaID) {
		Ambulancia aux = this.getAmbulancia(ambulanciaID);
		if (aux != null) {
			if (!aux.geteEstado().equals(EstadoAmbulancia.ASIGNADA)) {
				this.listaAmbulancias.borrarElemento(aux);
				return TipoRet.OK;
			} else {
				System.out.println("No es posible deshabilitar la ambulancia "
						+ ambulanciaID);
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
	}

	//Pre – condiciones: La ambulancia identificada con ambulanciaID debe de existir previamente en el sistema.
	//Post - condiciones: Imprime en pantalla los datos de la ambulancia requerida.
	@Override
	public TipoRet buscarAmbulancia(String ambulanciaID) {

		Ambulancia ambulanciaBuscada = getAmbulancia(ambulanciaID);

		if (ambulanciaBuscada != null) {
			System.out.println(ambulanciaBuscada.toString());

			int cantEmergencias = getCantEmergenciasPorAmbulancia(ambulanciaBuscada);

			System.out.println("#Emergencias: " + cantEmergencias);
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	//Pre – condiciones: -
	//Post - condiciones: Imprime en pantalla todas las ambulancias ordenadas según su identificador (ambulanciaID), si no existen ambulancias se imprimira “No se han registrado ambulancias”.
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
						+ ambulancia.getCiudadActual().getSNombreCiudad());

				aux = aux.getSiguiente();
			}
		}
		return TipoRet.OK;
	}

	//Pre – condiciones: La ciudad identificada como ciudadID debe existir en el sistema.
	//Post – condiciones: Imprime en pantalla todas las ambuancias disponibes en la ciudad identificada como ciudadID. 
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
						System.out.println("        Ambulancia:   "+ ambulanciaBuscada.getsIdAmbulancia());
						disponibles++;
					}
				}
				aux = aux.getSiguiente();
			}
			System.out.println("Total Ambulancias disponibles: " + disponibles);
			return TipoRet.OK;
		} else {
			System.out.println("La ciudad " + ciudadID + " no existe");
			return TipoRet.ERROR1;
		}
	}

	// Precondición: Sólo se agendarán emergencias a ciudades que estén contiguas, y no se le agendarán dos emergencias en la misma ciudad consecutivamente.
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
					System.out.println("La ambulancia no está disponible");
					return TipoRet.ERROR3;
				}
			} else {
				System.out
						.println("No existe una ambulancia con identificador "
								+ ambulanciaID);
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("La ciudad " + ciudadID + " no existe");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	//Pre – condiciones: Tanto la ambulancia identificada por ambulanciaID como la ciudad identificada por ciudadID deben de existir previamente en el sistema.
	//Post - condiciones: Cambia a ubicación de la ambulancia asignandola a la ciudad identificada con ciudadID, a su vez se quita la ambulancia de la ciudad anterior.
	@Override
	public TipoRet cambiarUbicacion(String ambulanciaID, int ciudadID) {
		Ciudad ciudad = this.getCiudad(ciudadID);
		if (ciudad != null) {
			Ambulancia ambulancia = this.getAmbulancia(ambulanciaID);
			if (ambulancia != null) {
				if(!ambulancia.getCiudadActual().equals(ciudad)){
					ambulancia.setCiudadActual(this.getCiudad(ciudadID));
					return TipoRet.OK;
				}else{
					return TipoRet.ERROR3;
				}				
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

	//Pre – condiciones: No debe existit ciudadNombre en el sistema.
	//Post - condiciones: Crea la ciudad asignandole un id unico (entre 1 y cantCiudades) y con el nombre indicado en el parametro ciudadNombre.
	@Override
	public TipoRet agregarCiudad(String ciudadNombre) {
		if (this.getCantCiudad() < this.listaCiudades.largo() + 1) {
			System.out.println("No se puede ingresar la ciudad de "
					+ ciudadNombre + " al sistema por no tener mas capacidad");
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

	//Pre – condiciones: Deben de existir ciudades en el sistema.
	//Post - condiciones: Imprime en pantalla las ciudades ordenadas en forma ascendente por ciudadID.
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

	//Pre – condiciones: las ciudades identificadas por ciudadOrigen y ciudadDestino deben existir previamente en el sistema.
	//Post - condiciones: Ingresa la ruta desde ciudadOrigen a ciudadDestino con la demora indicada en minutosViaje.
	@Override
	public TipoRet agregarRuta(int ciudadOrigen, int ciudadDestino,int minutosViaje) {

		if (minutosViaje <= 0) {
			System.out.println("La duración del viaje debe ser mayor que 0");
			return TipoRet.ERROR1;
		} else {
			Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen);
			Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);

			if (ciudadOrigenObject == null) {
				System.out.println("La ciudad " + ciudadOrigen + " no existe");
				return TipoRet.ERROR2;
			}
			if (ciudadDestinoObject == null) {
				System.out.println("La ciudad " + ciudadDestino + " no existe");
				return TipoRet.ERROR3;
			}

			Ruta nuevaRutaOrigenDestino = new Ruta(ciudadOrigenObject,
					ciudadDestinoObject, minutosViaje);
			Ruta nuevaRutaCaminoInvertido = new Ruta(ciudadDestinoObject,
					ciudadOrigenObject, minutosViaje);

			// En la ciudad1 --> de A a B y de B a A
			// En la ciudad2 --> de A a B y de B a A
			ciudadOrigenObject.getListaRutas().insertarAlFinal(nuevaRutaOrigenDestino);
			//ciudadOrigenObject.getListaRutas().insertarAlFinal(nuevaRutaCaminoInvertido);
			ciudadDestinoObject.getListaRutas().insertarAlFinal(nuevaRutaCaminoInvertido);
			//ciudadDestinoObject.getListaRutas().insertarAlFinal(nuevaRutaCaminoInvertido);
			return TipoRet.OK;
		}
	}

	//Pre – condiciones: La ruta a modificar debe de existir previamente en el sistema. Las ciudades deben ser limitrofes
	//Post – condiciones: Modifica la demora del viaje desde ciudadOrigen a   ciudadDestino. 
	@Override
	public TipoRet modificarDemora(int ciudadOrigen, int ciudadDestino, int minutosViaje) {

		if (minutosViaje <= 0) {
			System.out.println("La duración del viaje debe ser mayor que 0");
			return TipoRet.ERROR1;
		}
			Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen);
			Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);

			if (ciudadOrigenObject == null) {
				System.out.println("La ciudad " + ciudadOrigen + " no existe");
				return TipoRet.ERROR2;
			}

			if (ciudadDestinoObject == null) {
				System.out.println("La ciudad " + ciudadDestino + " no existe");
				return TipoRet.ERROR3;
			}

			Ruta laRutaOrigenDestino = getRuta(ciudadOrigenObject,ciudadDestinoObject);
			if (laRutaOrigenDestino != null) {
				laRutaOrigenDestino.setiMinutosViaje(minutosViaje);
				System.out.println(laRutaOrigenDestino.toString());
			}

			Ruta laRutaDestinoOrigen = getRuta(ciudadDestinoObject,ciudadOrigenObject);
			if (laRutaDestinoOrigen != null) {
				laRutaDestinoOrigen.setiMinutosViaje(minutosViaje);
				System.out.println(laRutaDestinoOrigen.toString());
			}
				return TipoRet.OK;
	}

	//Pre – condiciones: Deben de existir ciudades y ambulancias previamente definidas en el sistema.
	//Post - condiciones: Imprime en pantalla los datos de la ambulancia mas cercana a la ciudad identificada por ciudadID y el tiempo que demoraria en llegar.
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

			while (ambulanciaMasCercana == null) {
				while (nodoRuta != null) {
					Ruta auxRuta = (Ruta) nodoRuta.getDato();
					if (auxRuta.getCiudadOrigen().equals(ciudad)) {
						listaTemporalRutas.insertarAlFinal(auxRuta);
					}
					nodoRuta = nodoRuta.getSiguiente();
				}

				NodoLista auxListaTemp = listaTemporalRutas.ObtenerElementoPrimero();
				Ruta rutaEvaluada = new Ruta();
				int menorDistancia = 9999999;
				Ciudad ciudadEnLaQuePuedeHaberAmbulancia = new Ciudad();
				while (auxListaTemp != null) {
					rutaEvaluada = (Ruta) auxListaTemp.getDato();
					if (rutaEvaluada.getiMinutosViaje() < menorDistancia) {
						menorDistancia = rutaEvaluada.getiMinutosViaje();
						ciudadEnLaQuePuedeHaberAmbulancia = rutaEvaluada.getCiudadDestino();
					}
					auxListaTemp = auxListaTemp.getSiguiente();
				}

				ILista listaAmbulanciasCompararIds = new ListaOrdenada(new AmbulanciaComparatorById());
				NodoLista ambulanciasDeCiudadLimitrofe = this.listaAmbulancias.ObtenerElementoPrimero();
				while (ambulanciasDeCiudadLimitrofe != null) {
					Ambulancia ambulanciaConsiderada = (Ambulancia) ambulanciasDeCiudadLimitrofe.getDato();
					if (ambulanciaConsiderada.getCiudadActual().equals(ciudadEnLaQuePuedeHaberAmbulancia)) {
						listaAmbulanciasCompararIds.insertarOrdenado(ambulanciaConsiderada);
					}
					ambulanciasDeCiudadLimitrofe = ambulanciasDeCiudadLimitrofe.getSiguiente();
				}
				if(listaAmbulanciasCompararIds.ObtenerElementoPrimero() != null){
					ambulanciaMasCercana = (Ambulancia) listaAmbulanciasCompararIds.ObtenerElementoPrimero().getDato();
					mostrarDatosAmbulanciaMasCercana(ambulanciaMasCercana, ciudad,ciudadEnLaQuePuedeHaberAmbulancia,rutaEvaluada.getiMinutosViaje());
				}				
			}
			return TipoRet.OK;
		} else {
			System.out.println("La ciudad " + ciudadID + " no existe");
			return TipoRet.ERROR1;
		}
	}

	private void mostrarDatosAmbulanciaMasCercana(Ambulancia ambulancia,Ciudad ciudadIndicada, Ciudad ciudadAmbulancia, int demora) {
		System.out
				.println("La ambulancia más cercana a " + ciudadIndicada
						+ " se encuentra en "
						+ ambulancia.getCiudadActual().toString());
		System.out.println("Matrícula: " + ambulancia.getsIdAmbulancia());
		System.out.println("Demora del viaje: " + demora + " minutos");
	}
	
	class DistanciaCiudad{
		int distancia;
		int idCiudadAnterior;
		int tramo;
		
		public DistanciaCiudad(int dist, int idCiudAnt, int tramo) {
			this.distancia = dist;
			this.idCiudadAnterior = idCiudAnt;
			this.tramo = tramo;
		}

		public DistanciaCiudad() {
			this.distancia = 0;
		}
	}
	
	//Pre – condiciones: Deben existir rutas y ciudades previamente defnidas en el sistema.
	//Post - condiciones: Muestra en pantalla la ruta mas rapida entre los dos destinos asi como los minutos que le llevaria a una ambulancia recorrer dicho camino.
	@Override
	public TipoRet rutaMasRapida(int ciudadOrigen, int ciudadDestino) {
		Ciudad origen = this.getCiudad(ciudadOrigen);
		Ciudad destino = this.getCiudad(ciudadDestino);
		if (origen == null) {
			System.out.println("La ciudad origen (" + ciudadOrigen + ") no existe");
			return TipoRet.ERROR1;
		} else if (destino == null) {
			System.out.println("La ciudad destino (" + ciudadDestino + ") no existe");
			return TipoRet.ERROR2;
		} else if (ciudadOrigen == ciudadDestino) {
			System.out.println("CiudadOrigen y CiudadDestino deben ser distintas");
			return TipoRet.ERROR3;
		}
		ListaSimplementeEncadenada listaRutas = this.cargarListaRutas();
		if(!listaRutas.esVacia()){
			int[][] matrizRutas = this.GenerarMatrizRutas(listaRutas);
			DistanciaCiudad[][] caminos = this.rutasDesdeCiudad(ciudadOrigen,matrizRutas);
			if (this.existeRuta(ciudadOrigen, ciudadDestino, caminos) == false) {
				System.out.println("No hay rutas entre "  + origen.getSNombreCiudad() + " y " + destino.getSNombreCiudad());
				return TipoRet.ERROR4;
			} else {
				System.out.println("Ruta mas rapida:");
				System.out.println(origen.getSNombreCiudad() + " - " + 0);
				this.imprimirRutaMinima(ciudadOrigen, ciudadDestino, caminos);	
				System.out.println("Demora Total Ambulancias: " + this.demoraTotal(caminos, ciudadDestino)); 
				return TipoRet.OK;
			}		 
		}else{
			System.out.println("No hay rutas en el Sistema");
			return TipoRet.ERROR5;
		}
		
	}
	
	private boolean existeRuta(int ciudadOrigen, int ciudadDestino, DistanciaCiudad [][] caminos){
		DistanciaCiudad distCiudad = null;
		for(int numeroDePaso = 1; numeroDePaso < this.iCantCiudad; numeroDePaso++){
			if(caminos[ciudadDestino][numeroDePaso] !=null){
				distCiudad = caminos[ciudadDestino][numeroDePaso];
			}				
		}
		if(distCiudad != null){
			return true;
		}else{
			return false;
		}
	}
	
	private int demoraTotal(DistanciaCiudad [][] caminos,int ciudadDestino){
		int acumulado = 0;
		for(int numeroDePaso = 1; numeroDePaso < this.iCantCiudad; numeroDePaso++){
			if(caminos[ciudadDestino][numeroDePaso] !=null){
				acumulado = caminos[ciudadDestino][numeroDePaso].distancia;
			}				
		}
		return acumulado;
	}
	
	private void imprimirRutaMinima (int ciudadOrigen, int ciudadDestino, DistanciaCiudad [][] caminos){
		DistanciaCiudad distCiudad = null;
			for(int numeroDePaso = 1; numeroDePaso < this.iCantCiudad; numeroDePaso++){
				if(caminos[ciudadDestino][numeroDePaso] !=null){
					distCiudad = caminos[ciudadDestino][numeroDePaso];
				}				
			}		
			if(ciudadDestino != ciudadOrigen){
				this.imprimirRutaMinima(ciudadOrigen, distCiudad.idCiudadAnterior,  caminos );				
				Ciudad destino = this.getCiudad(ciudadDestino);
				System.out.println(destino.getSNombreCiudad() + " - " + (distCiudad.tramo));
			}
	}
	
	private DistanciaCiudad[][] rutasDesdeCiudad(int ciudadOrigen, int [][] matrizRutas) {		
		DistanciaCiudad minimo = new DistanciaCiudad(99999, -1, -1) ; // me guardo el nodo de menor costo
		int min = 999999;
		int ciudadSig = -1;
		boolean [] visitadas = new boolean [this.iCantCiudad+1];
		int ciudadActual = ciudadOrigen;
		DistanciaCiudad[][] caminos = new DistanciaCiudad[this.iCantCiudad+1][this.iCantCiudad+1];// [x]=idCiudad [y]=paso
		int[] paso = new int[1]; // [0]=minutos [1]= idCiudad de donde viene [2]=costo tramo
		DistanciaCiudad distanciaCiudad;
		for (int numeroDePaso = 1; numeroDePaso < this.iCantCiudad+1; numeroDePaso++) {
			for (int destino = 1; destino < this.iCantCiudad+1; destino++) {
				if (matrizRutas[ciudadActual][destino] > 0) {
					if (visitadas[destino] != true) {
						visitadas[ciudadActual] = true;
						distanciaCiudad = new DistanciaCiudad();
						distanciaCiudad.idCiudadAnterior = ciudadActual;
						distanciaCiudad.tramo = matrizRutas[ciudadActual][destino];
						if (numeroDePaso == 1) {//si es el primer paso no hay acumulado
							distanciaCiudad.distancia = matrizRutas[ciudadActual][destino];
						} else {
							distanciaCiudad.distancia = (minimo.distancia + matrizRutas[ciudadActual][destino]);
						}
						DistanciaCiudad aux = caminos[destino][numeroDePaso - 1];
						if (aux == null	|| aux.distancia > distanciaCiudad.distancia) {//comparo con la distancia del paso anterior al mismo destino
							caminos[destino][numeroDePaso] = distanciaCiudad;	//si es menor la guardo				
						} else {
							caminos[destino][numeroDePaso] = aux; // si es mayor me quedo con la anterior
						}
						if (min > caminos[destino][numeroDePaso].distancia) {//me quedo con el de menor distancia de ese paso
							minimo = new DistanciaCiudad();
							minimo = caminos[destino][numeroDePaso];
							min = minimo.distancia;
							ciudadSig = destino;
						}
					}
				}				
			}
			ciudadActual = ciudadSig;			
			min = 9999999;
		}		

		return caminos;	
	}
		
	//Pre-condicion: las ciudades una vez generadas no se pueden eliminar
	private int[][] GenerarMatrizRutas(ListaSimplementeEncadenada listaRutas) {
		//ListaSimplementeEncadenada listaRutas = this.cargarListaRutas();		
		int largo = this.listaCiudades.largo()+1;
		int x = 1;
			
		int[][] matriz = new int[largo+1][largo+1];
		while (x < largo) {
			int y = 1;	
			while (y < largo) {
				Ruta ruta = new Ruta();
				ruta.setCiudadOrigen(this.getCiudad(x));
				ruta.setCiudadDestino(this.getCiudad(y));
				ruta = (Ruta) listaRutas.buscar(ruta);
				if (ruta != null) {
					matriz[x][y] = ruta.getiMinutosViaje();
					matriz[y][x] = ruta.getiMinutosViaje();
				} else if (x == y) {
					matriz[x][y] = 0;
					matriz[y][x] = 0;
				} else {
					matriz[x][y] = -1;
					matriz[y][x] = -1;
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
					rutas.insertarAlPrincipio(ruta);
				}
				nodoRuta = nodoRuta.getSiguiente();
			}
			nodoCiudad = nodoCiudad.getSiguiente();
		}
		return rutas;
	}
	
	//Pre – condiciones: Deben existir ciudades, rutas y ambulancias previamente definidas en el sistema.
	//Post – condiciones: Se imprimen en pantalla todas las ciudades detallando para cada una sus rutas directas, demoras entre dichas rutas y la cantidad de ambulancias según su estado que se encuentran en la ciudad. 
	@Override
	public TipoRet informeCiudades() {
		Ciudad ciudad;
		Ruta rutaDirecta;
		int cantAmbDisponibles = 0;
		int cantAmbNODisponibles = 0;
		NodoLista nodoCiudad = this.listaCiudades.ObtenerElementoPrimero();
		while (nodoCiudad != null) {
			ciudad = (Ciudad) nodoCiudad.getDato();
			System.out.println("Informe Ciudad: " + ciudad.toString());
			NodoLista nodoRutaDir = ciudad.getListaRutas()
					.ObtenerElementoPrimero();
			while (nodoRutaDir != null) {
				rutaDirecta = (Ruta) nodoRutaDir.getDato();
				if (!rutaDirecta.getCiudadDestino().equals(ciudad)) {
					System.out.println("Ruta directa a ["
							+ rutaDirecta.getCiudadDestino().toString()
							+ "], minutos: " + rutaDirecta.getiMinutosViaje());
				}
				nodoRutaDir = nodoRutaDir.getSiguiente();
			}
			cantAmbDisponibles = getAmbulanciasCiudadEstado(
					ciudad.getiCiudadId(), EstadoAmbulancia.DISPONIBLE);
			System.out
					.println("Ambulancias disponibles: " + cantAmbDisponibles);
			cantAmbNODisponibles = getAmbulanciasCiudadEstado(
					ciudad.getiCiudadId(), EstadoAmbulancia.NO_DISPONIBLE);
			System.out.println("Ambulancias no disponibles: "
					+ cantAmbNODisponibles);
			nodoCiudad = nodoCiudad.getSiguiente();
			System.out.println();
		}
		return TipoRet.OK;
	}

	//Pre – condiciones: El sistema debe contar con ciudades y rutas previamente definidas.
	//Post - condiciones: Imprime en pantalla todas las ciudades que se encuentren duracionViaje minutos o menos de la ciudad identificada por ciudadID
	@Override
	public TipoRet ciudadesEnRadio(int ciudadID, int duracionViaje) {
		ILista lstCiudadesLimitrofes = new ListaSimplementeEncadenada();
		Ciudad ciudad = this.getCiudad(ciudadID);
		if (ciudad != null) {
			if (duracionViaje > 0) {
				Ruta ruta;
				System.out.println("Ciudades en un radio de " + duracionViaje + " minutos, con respecto a " + ciudad.toString() + ":");
				NodoLista nodoRuta = ciudad.getListaRutas().ObtenerElementoPrimero();
				while (nodoRuta != null) {
					ruta = (Ruta) nodoRuta.getDato();
					if (!ruta.getCiudadDestino().equals(ciudad)) {
						if (ruta.getiMinutosViaje() <= duracionViaje) {
							lstCiudadesLimitrofes.insertarAlFinal(ruta.getCiudadDestino());
							System.out.println("Ciudad " + ruta.getCiudadDestino().toString() + " a " 
									+ ruta.getiMinutosViaje() + " minutos.");
						}
					}
					nodoRuta = nodoRuta.getSiguiente();
				}
				evaluarCiudadesEnRadioConCiudadesLimitrofes(lstCiudadesLimitrofes, ciudad, duracionViaje);
				return TipoRet.OK;
			} else {
				System.out.println("La duracion del viaje debe ser mayor que 0");
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("La ciudad " + ciudadID + " no existe.");
			return TipoRet.ERROR1;
		}
	}
	
	private void evaluarCiudadesEnRadioConCiudadesLimitrofes(ILista lstCiudadesLimitrofes, Ciudad ciudad, int duracionViaje){
		System.out.println("Aquí comienza la consideración de conexiones con ciudades limítrofes");
		
		int distancia = 0;
		NodoLista nodoCiudad = lstCiudadesLimitrofes.ObtenerElementoPrimero();
		
		while(nodoCiudad != null){
			distancia = 0;
		Ciudad iteradorCiudades = (Ciudad) nodoCiudad.getDato();
		
		NodoLista iteradorRutasDeIteradorCiudad = iteradorCiudades.getListaRutas().ObtenerElementoPrimero();
		
		if(iteradorRutasDeIteradorCiudad != null){
			Ruta rutaLimitrofe = (Ruta) iteradorRutasDeIteradorCiudad.getDato();
			
			//Hasta aca lo que logre es recuperar la distancia entre la ciudad a y b. Me falta evaluar b y C que es lo 
			//pertinente a este metodo. Lo haré sumando estos minutos a los de la conexion b y c.
			distancia += rutaLimitrofe.getiMinutosViaje();
			
			//Ahora hay recorrer todas las rutas de b, para saber cuales de ellas me llevan a todas las c posibles
			//sin que "distancia" supere al parámetro "duracionViaje"
			Ciudad ciudadB =  rutaLimitrofe.getCiudadDestino();
			
			NodoLista rutaNoLimitrofeNodo = ciudadB.getListaRutas().ObtenerElementoPrimero();
			while(rutaNoLimitrofeNodo != null){
				Ruta rutaNoLimitrofe = (Ruta) rutaNoLimitrofeNodo.getDato();
				if(rutaNoLimitrofe.getCiudadOrigen().equals(ciudadB) && !rutaNoLimitrofe.getCiudadDestino().equals(ciudad) && !rutaNoLimitrofe.getCiudadDestino().equals(ciudadB)){
					distancia += rutaNoLimitrofe.getiMinutosViaje();
					if(distancia <= duracionViaje){
						System.out.println("Ciudad " + rutaNoLimitrofe.getCiudadDestino().toString() + " a " 
								+ distancia + " minutos (pasando por " + ciudadB.toString() + ")");
						distancia = rutaLimitrofe.getiMinutosViaje();
					}
				}
				rutaNoLimitrofeNodo = rutaNoLimitrofeNodo.getSiguiente();
			}
			distancia = 0;
		}
		nodoCiudad = nodoCiudad.getSiguiente();
		}
	}

	//Pre – condiciones: La ambulancia definida por ambulanciaID debe de estar previamente definida en el sistema.
	//Pre - condiciones: No existe un chofer de cédula cedula como chofer habilitado para conducir la ambulancia ambulanciaID
	//Post - condiciones: Registra el chofer ingresado como habilitado para conducir la ambulancia identificada por ambuanciaID.
	@Override
	public TipoRet registrarChofer(String ambulanciaID, String nombre,
			String cedula) {

		Ambulancia miAmbulancia = getAmbulancia(ambulanciaID);

		if (miAmbulancia != null) {
			Chofer nuevoChofer = new Chofer(cedula, nombre, miAmbulancia);
			miAmbulancia.getListaChoferes().insertarAlFinal(nuevoChofer);
			return TipoRet.OK;
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
	}

	//Pre – condiciones:  La ambulancia definida por ambulanciaID y e chofer referenciado por cedula deben de estar previamente definidos en el sistema.
	//Post – condiciones: Elimina al chofer de cedula cedula de la lista de los choferes habilitados en la ambulancia identificada como ambulanciaID.
	@Override
	public TipoRet eliminarChofer(String ambulanciaID, String cedula) {

		Ambulancia ambulancia = getAmbulancia(ambulanciaID);

		if (ambulancia != null) {
			NodoLista auxChofer = ambulancia.getListaChoferes()
					.ObtenerElementoPrimero();

			while (auxChofer != null) {
				Chofer chofer = (Chofer) auxChofer.getDato();
				if (chofer.getsCedula().equals(cedula)) {
					ambulancia.getListaChoferes().borrarElemento(chofer);
					return TipoRet.OK;
				}
				auxChofer = auxChofer.getSiguiente();
			}
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
		System.out
				.println("El chofer ingresado no está asociado a la ambulancia");
		return TipoRet.ERROR2;
	}

	//Pre – condiciones: La ambuancia identificada por ambulanciaID debe de estar previamente ingresada.
	//Post – condiciones: Imprime en pantalla todos los choferes habilitados para conducir dicha ambulancia.
	@Override
	public TipoRet informeChoferes(String ambulanciaID) {

		Ambulancia ambulancia = getAmbulancia(ambulanciaID);

		if (ambulancia != null) {

			NodoLista auxChofer = ambulancia.getListaChoferes()
					.ObtenerElementoPrimero();

			System.out.println("Informe choferes de ambulancia " + ambulanciaID
					+ ":");
			while (auxChofer != null) {
				Chofer iteradorChoferes = (Chofer) auxChofer.getDato();
				System.out.println(iteradorChoferes.toString());
				auxChofer = auxChofer.getSiguiente();
			}
		} else {
			System.out.println("No existe una ambulancia con identificador "
					+ ambulanciaID);
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}

	private Ciudad getCiudad(int ciudadID) {

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

	private Ambulancia getAmbulancia(String ambulanciaId) {

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

	private int getCantEmergenciasPorAmbulancia(Ambulancia ambulancia) {

		int retorno = 0;
		Emergencia emergencia = new Emergencia();

		NodoCola aux = (NodoCola) this.listaEmergencias
				.ObtenerElementoPrimero();

		while (aux != null) {
			emergencia = (Emergencia) aux.getDato();

			if (emergencia.getsAmbulanciaId().equals(
					ambulancia.getsIdAmbulancia())) {
				retorno++;
			}
			aux = aux.getSiguiente();
		}
		return retorno;
	}

	private Ruta getRuta(Ciudad ciudadOrigenObject, Ciudad ciudadDestinoObject) {

		NodoLista iteradorRutas = ciudadOrigenObject.getListaRutas()
				.ObtenerElementoPrimero();

		if (iteradorRutas != null) {
			while (iteradorRutas != null) {
				Ruta ruta = (Ruta) iteradorRutas.getDato();
				if (ruta.getCiudadOrigen().equals(ciudadOrigenObject)
						&& ruta.getCiudadDestino().equals(ciudadDestinoObject)) {
					return ruta;
				}
				iteradorRutas = iteradorRutas.getSiguiente();
			}
		}
		return null;
	}

	private int getAmbulanciasCiudadEstado(int ciudadID, EstadoAmbulancia estado) {
		int cantidad = 0;
		Ambulancia amb;
		NodoLista nodoAmb = this.listaAmbulancias.ObtenerElementoPrimero();
		while (nodoAmb != null) {
			amb = (Ambulancia) nodoAmb.getDato();
			if (amb.getCiudadActual().getiCiudadId() == ciudadID
					&& amb.geteEstado().equals(estado)) {
				cantidad++;
			}
			nodoAmb = nodoAmb.getSiguiente();
		}
		return cantidad;
	}

	public void cambiarEstadoAmbulancia(String ambulanciaID,EstadoAmbulancia nuevoEstado) {
		Ambulancia laAmbulancia = getAmbulancia(ambulanciaID);

		if (laAmbulancia != null) {
			laAmbulancia.seteEstado(nuevoEstado);
		} else {
			System.out.println("No se pudo cambiar el estado de la ambulancia "
					+ ambulanciaID);
		}
	}

	public void volverCeroNumeradoraCiudades() {
		Ciudad.setNumeradora(0);
	}

}

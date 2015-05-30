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
		this.listaCiudades = new ListaOrdenada(new CiudadComparatorByName());
		this.listaEmergencias = new ColaImpl();
		this.listaViajes = new ListaSimplementeEncadenada();
	}

	@Override
	public TipoRet crearSistemaDeEmergencias(int cantidadCiudades) {

		if (cantidadCiudades <= 0) {
			System.out.println("La cantidad de ciudades es inferior a 1");
			return TipoRet.ERROR1;
		}
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
			listaAmbulancias.insertarAlFinal(nuevaAmbulancia);
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
					.println("“La ambulancia ambulanciaID ya está en estado NO_DISPONIBLE");
			return TipoRet.ERROR2;
		}

		if (laAmbulancia.geteEstado().equals(EstadoAmbulancia.ASIGNADA)) {
			System.out
					.println("No es posible deshabilitar la ambulancia ambulanciaID”.");
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
						.println("La ambulancia ambulanciaID ya está habilitada");
				return TipoRet.ERROR2;
			}
		} else {
			System.out
					.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
		return TipoRet.NO_IMPLEMENTADA;
	}

	@Override
	public TipoRet eliminarAmbulancia(String ambulanciaID) {
		Ambulancia aux = this.getAmbulancia(ambulanciaID);
	if (aux != null) {
		if(!aux.geteEstado().equals(EstadoAmbulancia.ASIGNADA)){
			this.listaAmbulancias.borrarElemento(aux);
			return TipoRet.OK;
		}else{
			System.out.println("No es posible deshabilitar la ambulancia ambulanciaID”.");
			return TipoRet.ERROR2;
		}
	} else {
		System.out.println("No existe una ambulancia con identificador ambulanciaID");
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
			}
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet informeAmbulancia(int ciudadID) {
		Ciudad ciudad = this.getCiudad(ciudadID);
		int contador = 0; int disponibles = 0; 
		if (ciudad != null){
			NodoLista aux = (NodoLista) this.listaAmbulancias.ObtenerElementoPrimero();
			Ambulancia ambulanciaBuscada;
			System.out.println("Informe de ambulancias: " + ciudad.toString());
			while (aux != null) {
				ambulanciaBuscada = (Ambulancia) aux.getDato();
				if (ambulanciaBuscada.getCiudadActual().equals(ciudad)){					
					contador++;
					if(ambulanciaBuscada.geteEstado().equals(EstadoAmbulancia.DISPONIBLE)){
						System.out.println(ambulanciaBuscada.getsIdAmbulancia());
						disponibles++;
					}
				}else{
					aux = aux.getSiguiente();
				}					
			}
			System.out.println("Total Ambulancias disponibles: " + disponibles);
			return TipoRet.OK;
		}else{
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}	
	}

	// Precondición: Sólo se agendarán emergencias a ciudades que estén
	// contiguas, y no se le agendarán dos emergencias en la misma ciudad
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

					NodoCola primerElemento = (NodoCola) listaEmergencias
							.inicio();
					Emergencia laEmergencia = (Emergencia) primerElemento
							.getDato();
					laEmergencia.setCiudadEmergencia(ciudad);
					laEmergencia.setsAmbulanciaId(ambulanciaID);
					cambiarUbicacion(ambulanciaID, ciudadID);
					this.listaEmergencias.desEncolar();

				} else {
					System.out.println("La ambulancia ya fue asignada");
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
				System.out.println("No existe una ambulancia con identificador " + ambulanciaID);
				return TipoRet.ERROR2;
			}
		} else {
			System.out.println("La ciudad " + ciudadID + "no existe");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet agregarCiudad(String ciudadNombre) {
			if (this.verificarCiudadNomnbre(ciudadNombre)) {
		if (this.getCantCiudad() > this.getCantCiudad()) {
			Ciudad aux = new Ciudad(ciudadNombre);
			this.listaCiudades.insertarOrdenado(aux);;
			return TipoRet.OK;
		} else {
			System.out
			.println("No se puede ingresar la ciudadNombre al sistema por no tener mas capacidad");
			return TipoRet.ERROR2;
		}
	} else {
		System.out.println("Ya existe una ciudad con el nombre Ingresado");
		return TipoRet.ERROR1;
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
			}
		}
		return TipoRet.OK;
	}

	@Override
	public TipoRet agregarRuta(int ciudadOrigen, int ciudadDestino,
			int minutosViaje) {

		if (minutosViaje <= 0) {
			System.out.println("La duración del viaje debe ser mayor que 0");
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
					nuevaRutaOrigenDestino);
			return TipoRet.OK;
		}
	}

	@Override
	public TipoRet modificarDemora(int ciudadOrigen, int ciudadDestino,
			int minutosViaje) {

		int contadorSeguridad = 0;

		if (minutosViaje <= 0) {
			System.out.println("La duración del viaje debe ser mayor que 0");
			return TipoRet.ERROR1;
		} else {

			Ciudad ciudadOrigenObject = getCiudad(ciudadDestino);
			Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);

			if (ciudadOrigenObject == null) {
				System.out.println("La ciudad ciudadOrigen no existe");
				return TipoRet.ERROR2;
			}

			if (ciudadDestinoObject == null) {
				System.out.println("La ciudad ciudadDestino no existe");
				return TipoRet.ERROR3;
			}

			Viaje elViaje = getViaje(ciudadOrigen, ciudadDestino);
			NodoLista aux = this.listaViajes.ObtenerElementoPrimero();

			/*
			 * Se implementa un contador para ciudad la integridad de los datos.
			 * Si modifica un viaje, DEBE modificar dos. Por eso solo retorna ok
			 * si este contador = 2
			 */

			while (aux != null) {
				Viaje iteradorViaje = (Viaje) aux.getDato();
				if (elViaje.equals(iteradorViaje)) {
					elViaje.setDemoraMinutos(minutosViaje);
					contadorSeguridad++;
				}
				aux.getSiguiente();
			}
			if (contadorSeguridad == 2) {
				return TipoRet.OK;
			} else {
				return TipoRet.ERROR4;
			}
		}
	}

	@Override
	public TipoRet ambulanciaMasCercana(int ciudadID) {

		Ciudad ciudad = getCiudad(ciudadID);

		if (ciudad != null) {
			Ambulancia ambulanciaMasCercana;
			NodoLista auxAmbulancia = listaAmbulancias.ObtenerElementoPrimero();

			while (auxAmbulancia != null) {
				ambulanciaMasCercana = (Ambulancia) auxAmbulancia.getDato();

				if (ambulanciaMasCercana.getCiudadActual().equals(ciudad)) {
					mostrarDatosAmbulanciaMasCercana(ambulanciaMasCercana, ciudad, 0);
					return TipoRet.OK;
				}
				auxAmbulancia.getSiguiente();
			}

			// Si no hay ninguna ambulancia en la ciudad, empiezo a buscar la mas cercana en ciudades limitrofes.
			Viaje viaje;
			NodoLista auxViaje = listaViajes.ObtenerElementoPrimero();
			int minutosDemora = -1;
			int contador = 0;

			while (auxViaje != null) {
				viaje = (Viaje) auxViaje.getDato();

				if (viaje.getCiudadDestino().equals(ciudad) && viaje.getDemoraMinutos() < minutosDemora) {
					ambulanciaMasCercana = (Ambulancia) viaje.getAmbulancia();
					minutosDemora = viaje.getDemoraMinutos();
					contador++;
					if(listaViajes.largo() == contador){
						mostrarDatosAmbulanciaMasCercana(ambulanciaMasCercana, ciudad, minutosDemora);
					}
				}
				auxViaje = auxViaje.getSiguiente();
			}
		} else {
			System.out.println("La ciudad ciudadID no existe");
			return TipoRet.ERROR1;
		}
		return TipoRet.OK;
	}
	
	private void mostrarDatosAmbulanciaMasCercana(Ambulancia ambulancia, Ciudad ciudad, int demora){
		System.out.println("Ambulancia más cercana a: "
				+ ambulancia.getCiudadActual().toString());
				System.out.println("Ambulancia: " + ambulancia.getsIdAmbulancia());
				System.out.println("Demora del viaje: " + demora);
	}

	@Override
	public TipoRet rutaMasRapida(int ciudadOrigen, int ciudadDestino) {
		// TODO Auto-generated method stub
		return TipoRet.NO_IMPLEMENTADA;
	}

	@Override
	public TipoRet informeCiudades() {
		// TODO Auto-generated method stub
		return TipoRet.NO_IMPLEMENTADA;
	}

	@Override
	public TipoRet ciudadesEnRadio(int ciudadID, int duracionViaje) {
		// TODO Auto-generated method stub
		return TipoRet.NO_IMPLEMENTADA;
	}

	//Precondición: No existe un chofer de cédula cedula como chofer habilitado para conducir la ambulancia ambulanciaID
	@Override
	public TipoRet registrarChofer(String ambulanciaID, String nombre,
			String cedula) {
		
		NodoLista auxAmbulancia = listaAmbulancias.ObtenerElementoPrimero();
		Ambulancia miAmbulancia = (Ambulancia) auxAmbulancia.getDato();
		
		if(miAmbulancia != null){
		Chofer nuevoChofer = new Chofer(cedula, nombre, miAmbulancia);
		miAmbulancia.getListaChoferes().insertarAlFinal(nuevoChofer);
		return TipoRet.OK;
		}else{
			System.out.println("No existe una ambulancia con identificador ambulanciaID");
			return TipoRet.ERROR1;
		}
	}

	@Override
	public TipoRet eliminarChofer(String ambulanciaID, String cedula) {
		// TODO Auto-generated method stub
		return TipoRet.NO_IMPLEMENTADA;
	}

	@Override
	public TipoRet informeChoferes(String ambulanciaID) {
		// TODO Auto-generated method stub
		return TipoRet.NO_IMPLEMENTADA;
	}

	private boolean verificarCiudadNomnbre(String ciudadNombre) {
		// TODO Auto-generated method stub
		return false;
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

	private Viaje getViaje(int ciudadOrigen, int ciudadDestino) {

		Ciudad ciudadOrigenObject = getCiudad(ciudadOrigen);
		Ciudad ciudadDestinoObject = getCiudad(ciudadDestino);
		Viaje viajeBuscado = new Viaje();
		viajeBuscado.setCiudadOrigen(ciudadOrigenObject);
		viajeBuscado.setCiudadDestino(ciudadDestinoObject);

		if (this.listaViajes.esVacia())
			return null;
		else {
			NodoLista aux = (NodoLista) this.listaViajes
					.ObtenerElementoPrimero();

			Viaje iteradorViaje;

			while (aux != null) {
				iteradorViaje = (Viaje) aux.getDato();

				if (viajeBuscado.equals(iteradorViaje)) {
					return viajeBuscado;
				} else
					aux = aux.getSiguiente();
			}
		}
		return null;
	}

}

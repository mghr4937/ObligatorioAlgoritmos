package sistema;

import org.junit.experimental.theories.Theories;

import interfazObligatorio.Sistema;
import testing.Prueba;

public class Main {

	public static void main(String[] args) {
		SistemaEmergencias s = new SistemaEmergencias();
	        Prueba p = new Prueba();
	        //-----------------------PRUEBAS OK---------------------------//
	        //PruebaOK_1(s,p);
	         //PruebaOK_2(s,p);
	         //-----------------------PRUEBAS ERROR------------------------//
	        //PruebaERROR_1(s,p);
	       //PruebaERROR_2(s,p);
	        PruebaERROR_3(s,p);
	        //PruebaERROR_4(s,p);
	        p.imprimirResultadosPrueba();
		 
	}
	
	//-------------PRUEBAS OK-----------------//
		public static void PruebaOK_1(Sistema s, Prueba p){
	                   s.crearSistemaDeEmergencias(11);
	    	System.out.println("Se creó el sistema de emergencias vacío.");
	    	tituloPrueba("PRUEBA OK 1");
	    	tituloPrueba("AGREGAR CIUDADES");
	    	agregarCiudadesAlMapa(s,p);

	    	tituloPrueba("LISTAR CIUDADES");
	    	s.listarCiudades();
	    	
	    	tituloPrueba("REGISTRO DE AMBULANCIAS");
	    	p.ver(s.registrarAmbulancia("LIU5687",1), Sistema.TipoRet.OK, "Se agregó la ambulancia Liu5687");
	    	p.ver(s.registrarAmbulancia("JKA5649",5), Sistema.TipoRet.OK, "Se agregó la ambulancia JKA5649");
	    	p.ver(s.registrarAmbulancia("POL8974",2), Sistema.TipoRet.OK, "Se agregó la ambulancia POL8974");
	    	p.ver(s.registrarAmbulancia("APM5464",5), Sistema.TipoRet.OK, "Se agregó la ambulancia APM5464");
	    	p.ver(s.registrarAmbulancia("LOJ2457",8), Sistema.TipoRet.OK, "Se agregó la ambulancia LOJ2457");
	    	p.ver(s.registrarAmbulancia("DFR6584",9), Sistema.TipoRet.OK, "Se agregó la ambulancia DFR6584");
	    	p.ver(s.registrarAmbulancia("BGY9834",1), Sistema.TipoRet.OK, "Se agregó la ambulancia BGY9834");
	    	p.ver(s.registrarAmbulancia("VCB5689",2), Sistema.TipoRet.OK, "Se agregó la ambulancia VCB5689");
	    	p.ver(s.registrarAmbulancia("NGH9984",3), Sistema.TipoRet.OK, "Se agregó la ambulancia NGH9984");
	    	p.ver(s.registrarAmbulancia("IOY4287",4), Sistema.TipoRet.OK, "Se agregó la ambulancia IOY4287");
	    	p.ver(s.registrarAmbulancia("QWE4579",5), Sistema.TipoRet.OK, "Se agregó la ambulancia QWE4579");
	    	p.ver(s.registrarAmbulancia("XCV2347",6), Sistema.TipoRet.OK, "Se agregó la ambulancia XCV2347");
	    	p.ver(s.registrarAmbulancia("NBM2689",7), Sistema.TipoRet.OK, "Se agregó la ambulancia NBM2689");
	    	p.ver(s.registrarAmbulancia("TYR4597",8), Sistema.TipoRet.OK, "Se agregó la ambulancia TYR4597");
	    	p.ver(s.registrarAmbulancia("LKJ6842",3), Sistema.TipoRet.OK, "Se agregó la ambulancia LKJ6842");
	    	p.ver(s.registrarAmbulancia("POU6987",4), Sistema.TipoRet.OK, "Se agregó la ambulancia POU6987");
	    	p.ver(s.registrarAmbulancia("VCS6984",7), Sistema.TipoRet.OK, "Se agregó la ambulancia VCS6984");
	    	p.ver(s.registrarAmbulancia("LÑJ2143",9), Sistema.TipoRet.OK, "Se agregó la ambulancia LÑJ2143");
	    	p.ver(s.registrarAmbulancia("WQW5646",8), Sistema.TipoRet.OK, "Se agregó la ambulancia WQW5646");
	    	p.ver(s.registrarAmbulancia("QPQ6598",2), Sistema.TipoRet.OK, "Se agregó la ambulancia QPQ6598");
	    	
	    	//s.informeAmbulancias();
	    	s.informeAmbulancia();
	    	tituloPrueba("DESHABILITAR AMBULANCIAS");
	    	p.ver(s.deshabilitarAmbulancia("IOY4287"), Sistema.TipoRet.OK, "Se deshabilitó la ambulancia IOY4287");
	    	p.ver(s.deshabilitarAmbulancia("QWE4579"), Sistema.TipoRet.OK, "Se deshabilitó la ambulancia QWE4579");
	    	//s.informeAmbulancias();
	        s.informeAmbulancia();
	    	
	    	tituloPrueba("HABILITAR AMBULANCIAS");
	    	p.ver(s.habilitarAmbulancia("IOY4287"), Sistema.TipoRet.OK, "Se habilitó la ambulancia IOY4287");
	    	p.ver(s.habilitarAmbulancia("QWE4579"), Sistema.TipoRet.OK, "Se habilitó la ambulancia QWE4579");
	    	s.informeAmbulancia();
	    	
	    	tituloPrueba("ELIMINAR AMBULANCIA E INFORME AMBULANCIAS POR CIUDAD");
	    	s.informeAmbulancia(2);
	    	s.informeAmbulancia(8);
	    	p.ver(s.eliminarAmbulancia("POL8974"), Sistema.TipoRet.OK, "Se eliminó la ambulancia POL8974");
	    	p.ver(s.eliminarAmbulancia("LOJ2457"), Sistema.TipoRet.OK, "Se eliminó la ambulancia LOJ2457");
	    	s.informeAmbulancia(2);
	    	s.informeAmbulancia(8);
	    	
	    	tituloPrueba("BUSCAR AMBULANCIA");
			s.buscarAmbulancia("JKA5649");
	    	tituloPrueba("INFORME AMBULANCIAS SISTEMA");
			s.informeAmbulancia();
			tituloPrueba("CAMBIO UBICACION AMBULANCIA");
			s.informeAmbulancia(5);
			s.informeAmbulancia(2);
			p.ver(s.cambiarUbicacion("JKA5649",2), Sistema.TipoRet.OK, "Se cambió de ubicación la ambulancia JKA5649 de Salto a Flores");
			s.informeAmbulancia(5);
			s.informeAmbulancia(2);
			
			s.destruirSistemaEmergencias();
			finPrueba(" Fin PRUEBA OK 1");
		}
		
		public static void PruebaOK_2(Sistema s, Prueba p){
			s.crearSistemaDeEmergencias(11);
	    	System.out.println("Se creó el sistema de emergencias vacío.");
	    	tituloPrueba("PRUEBA OK 2");
	    	agregarCiudadesAlMapa(s,p);
	    	agregarRutas(s,p);
	    	registrarAmbulancias(s,p);
	    	tituloPrueba("REGISTRAR CHOFERES");
	    	p.ver(s.registrarChofer("APM5464", "Jorge Perez", "15648965"), Sistema.TipoRet.OK, "Registrar chofer Jorge Perez");	
			p.ver(s.registrarChofer("BGY9834", "Maria Lopez", "52369874"), Sistema.TipoRet.OK, "Registrar chofer Maria Lopez");
			tituloPrueba("2.4.2 ELIMINAR CHOFER");
			p.ver(s.eliminarChofer("APM5464", "15648965"), Sistema.TipoRet.OK, "Eliminar chofer Jorge Perez");
			tituloPrueba("2.4.3 INFORME CHOFERES");
			s.informeChoferes("APM5464");
			s.informeChoferes("BGY9834");
			s.destruirSistemaEmergencias();
			finPrueba(" Fin PRUEBA OK 4");
		}
		
		//-------------PRUEBAS ERROR-----------------//
		public static void PruebaERROR_1(Sistema s, Prueba p){
			
			tituloPrueba("Prueba ERROR_1");
	    	p.ver(s.crearSistemaDeEmergencias(0), Sistema.TipoRet.ERROR1, "Crear sistema con cero ciudades");
	    	s.crearSistemaDeEmergencias(4);
	    	System.out.println("Se creó el sistema de emergencias vacío.");
	     	p.ver(s.agregarCiudad("Pando"), Sistema.TipoRet.OK, "Se agregó la ciudad Pando");
	    	p.ver(s.agregarCiudad("Flores"), Sistema.TipoRet.OK, "Se agregó la ciudad Flores");
	    	p.ver(s.agregarCiudad("Rocha"), Sistema.TipoRet.OK, "Agregar ciudad Rocha");
	    	p.ver(s.agregarCiudad("Salto"), Sistema.TipoRet.OK, "Agregar ciudad Salto");
	    	p.ver(s.agregarCiudad("Mvd"), Sistema.TipoRet.ERROR1, "Agregar ciudad Mvd");
	    	tituloPrueba("2.3.2 LISTAR CIUDADES");
	    	s.listarCiudades();
		}
		
		public static void PruebaERROR_2(Sistema s, Prueba p){
			s.crearSistemaDeEmergencias(4);
	    	System.out.println("Se creó el sistema de emergencias vacío.");
	    	tituloPrueba("PRUEBA ERROR_2");
	    	p.ver(s.agregarCiudad("Pando"), Sistema.TipoRet.OK, "Se agregó la ciudad Pando");
	    	p.ver(s.agregarCiudad("Flores"), Sistema.TipoRet.OK, "Se agregó la ciudad Flores");
	    	p.ver(s.agregarCiudad("Rocha"), Sistema.TipoRet.OK, "Agregar ciudad Rocha");
	    	p.ver(s.agregarCiudad("Salto"), Sistema.TipoRet.OK, "Agregar ciudad Salto");
	    	
	   
			tituloPrueba("REGISTRO AMBULANCIAS");
	    	p.ver(s.registrarAmbulancia("APM5464",4), Sistema.TipoRet.OK, "Se agregó la ambulancia APM5464");
	    	p.ver(s.registrarAmbulancia("JKA5649",4), Sistema.TipoRet.OK, "Se agregó la ambulancia JKA5649");
	    	p.ver(s.registrarAmbulancia("APM5464",2), Sistema.TipoRet.ERROR2, "Agregar ambulancia repetida APM5464");
	    	p.ver(s.registrarAmbulancia("POU9859",8), Sistema.TipoRet.ERROR1, "Agregar ambulancia POU9859 a la ciudad 8 - ciudad 8 no existe");
	    	p.ver(s.registrarAmbulancia("LKU9874",5), Sistema.TipoRet.ERROR1, "Agregar ambulancia LKU9874 a la ciudad 5 - ciudad 5 no existe");
	    	tituloPrueba("DESHABILITAR AMBULANCIA");
	    	p.ver(s.deshabilitarAmbulancia("JKA5649"), Sistema.TipoRet.OK, "Deshabilitar la ambulancia JKA5649");
	    	p.ver(s.deshabilitarAmbulancia("SADF65"), Sistema.TipoRet.ERROR1, "Deshabilitar la ambulancia SADF65 inexistente");
	    	p.ver(s.deshabilitarAmbulancia("JKA5649"), Sistema.TipoRet.ERROR2, "Deshabilitar la ambulancia JKA5649 inhabilitada");
	    	tituloPrueba("HABILITAR AMBULANCIA");
	    	p.ver(s.habilitarAmbulancia("JKA5649"), Sistema.TipoRet.OK, "Habilitar la ambulancia JKA5649");
	    	p.ver(s.habilitarAmbulancia("JKA5649"), Sistema.TipoRet.ERROR2, "Habilitar la ambulancia JKA5649 ya habilitada");
	    	p.ver(s.habilitarAmbulancia("SDF454"), Sistema.TipoRet.ERROR1, "Habilitar la ambulancia SDF454 inexistente");
	    	tituloPrueba("ELIMINAR AMBULANCIA");
	    	p.ver(s.eliminarAmbulancia("DSAF54"), Sistema.TipoRet.ERROR1, "Eliminar la ambulancia DSAF54 inexistente");
	    	tituloPrueba("BUSCAR AMBULANCIA");
	    	p.ver(s.buscarAmbulancia("JKA5649"), Sistema.TipoRet.OK, "Buscar ambulancia JKA5649");
	    	p.ver(s.buscarAmbulancia("SDF547"), Sistema.TipoRet.ERROR1, "Buscar ambulancia SDF547 inexistente");
	    	tituloPrueba("INFORME AMBULANCIAS");
	    	p.ver(s.informeAmbulancia(), Sistema.TipoRet.OK, "Informe ambulancia");
	    	tituloPrueba("INFORME AMBULANCIA POR CIUDAD");
	    	p.ver(s.informeAmbulancia(8), Sistema.TipoRet.ERROR1, "Informe ambulancia ciudad 8");
	    	p.ver(s.informeAmbulancia(1), Sistema.TipoRet.OK, "Informe ambulancia ciudad 1");
	    	p.ver(s.informeAmbulancia(2), Sistema.TipoRet.OK, "Informe ambulancia ciudad 2");
	    	p.ver(s.informeAmbulancia(3), Sistema.TipoRet.OK, "Informe ambulancia ciudad 3");
	    	p.ver(s.informeAmbulancia(4), Sistema.TipoRet.OK, "Informe ambulancia ciudad 4");
	    	tituloPrueba("CAMBIAR UBICACION");
	    	p.ver(s.cambiarUbicacion("JKA5649", 9), Sistema.TipoRet.ERROR1, "Cambiar ubicacion JKA5649 a ciudad 9 inexistente");
	    	p.ver(s.cambiarUbicacion("JKA5649", 4), Sistema.TipoRet.ERROR3, "Cambiar ubicacion JKA5649 a la misma ciudad");
	    	p.ver(s.cambiarUbicacion("ASDF985", 2), Sistema.TipoRet.ERROR2, "Cambiar ubicacion inexistente ASDF985 a ciudad 2");
		}

			
		public static void PruebaERROR_3(Sistema s, Prueba p){
		s.crearSistemaDeEmergencias(4);	

		
		p.ver(s.agregarCiudad("Pando"), Sistema.TipoRet.OK, "Se agregó la ciudad Pando");
	    	p.ver(s.agregarCiudad("Flores"), Sistema.TipoRet.OK, "Se agregó la ciudad Flores");
	    	p.ver(s.agregarCiudad("Rocha"), Sistema.TipoRet.OK, "Agregar ciudad Rocha");
	    	p.ver(s.agregarCiudad("Salto"), Sistema.TipoRet.OK, "Agregar ciudad Salto");
	    	
			p.ver(s.agregarRuta(1, 2, 20), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(1, 3, 4), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(3, 2, 5), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(2, 4, 7), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	
		p.ver(s.registrarAmbulancia("APM5464",1), Sistema.TipoRet.OK, "Se agregó la ambulancia APM5464");
	    	p.ver(s.registrarAmbulancia("JKA5649",2), Sistema.TipoRet.OK, "Se agregó la ambulancia JKA5649");
	    	
	    	p.ver(s.ambulanciaMasCercana(1), Sistema.TipoRet.OK, "Ambulancia mas cercana a ciudad 1");
	    	p.ver(s.ambulanciaMasCercana(2), Sistema.TipoRet.OK, "Ambulancia mas cercana a ciudad 2");
	    	//p.ver(s.ambulanciaMasCercana(3), Sistema.TipoRet.OK, "Ambulancia mas cercana a ciudad 3");
	    	//p.ver(s.ambulanciaMasCercana(4), Sistema.TipoRet.OK, "Ambulancia mas cercana a ciudad 4");
	    	
	    	tituloPrueba("2.2.2 PRUEBA DESHABILITAR Y ELIMINAR AMBULANCIA ASIGNADA A VIAJE");
	    	p.ver(s.recibirEmergencia("JKA5649", 2), Sistema.TipoRet.OK,"Ambulancia 1 asignada a ciudad 2");
	    	p.ver(s.recibirEmergencia("APM5464", 2), Sistema.TipoRet.OK,"Ambulancia 2 asignada a ciudad 2");
	    	p.ver(s.deshabilitarAmbulancia("JKA5649"), Sistema.TipoRet.ERROR3, "Deshabilitar la ambulancia jka5649 asignada a un viaje");
	    	p.ver(s.eliminarAmbulancia("APM5464"), Sistema.TipoRet.ERROR2, "Eliminar la ambulancia APM5464 asignada a un viaje");
	    	tituloPrueba("2.3.6 RUTA MAS RAPIDA");
	    	p.ver(s.rutaMasRapida(0, 1), Sistema.TipoRet.ERROR1, "Ruta mas rapida desde ciudad 0 inexistente");
	    	p.ver(s.rutaMasRapida(1, 0), Sistema.TipoRet.ERROR2, "Ruta mas rapida hacia ciudad 0 inexistente");
	    	p.ver(s.rutaMasRapida(1, 5), Sistema.TipoRet.ERROR2, "Ruta mas rapida hacia ciudad 5 inexistente");
	    	
	    	p.ver(s.rutaMasRapida(1, 2), Sistema.TipoRet.OK, "Ruta mas rapida a ciudad 1 a 2");
	    	p.ver(s.rutaMasRapida(2, 1), Sistema.TipoRet.OK, "Ruta mas rapida a ciudad 2 a 1");
	    	p.ver(s.rutaMasRapida(1, 3), Sistema.TipoRet.OK, "Ruta mas rapida a ciudad 1 a 3");
	    	p.ver(s.rutaMasRapida(1, 4), Sistema.TipoRet.OK, "Ruta mas rapida a ciudad 1 a 4");
	    	
	    	tituloPrueba("INFORME CIUDADES");
	    	s.informeCiudades();
		}
		
		public static void PruebaERROR_4(Sistema s, Prueba p){
		s.crearSistemaDeEmergencias(4);
		
		p.ver(s.agregarCiudad("Pando"), Sistema.TipoRet.OK, "Se agregó la ciudad Pando");
	    	p.ver(s.agregarCiudad("Flores"), Sistema.TipoRet.OK, "Se agregó la ciudad Flores");
	    	p.ver(s.agregarCiudad("Rocha"), Sistema.TipoRet.OK, "Agregar ciudad Rocha");
	    	p.ver(s.agregarCiudad("Salto"), Sistema.TipoRet.OK, "Agregar ciudad Salto");
	    	
		p.ver(s.registrarAmbulancia("APM5464",4), Sistema.TipoRet.OK, "Se agregó la ambulancia APM5464");
	    	p.ver(s.registrarAmbulancia("JKA5649",4), Sistema.TipoRet.OK, "Se agregó la ambulancia JKA5649");
	    	
	    	p.ver(s.registrarChofer("ASDF65", "Pepe", "654988"), Sistema.TipoRet.ERROR1, "Registrar chofer en ambulancia ASDF65 inexistente.");
	    	p.ver(s.registrarChofer("JKA5649", "Pepe", "654988"), Sistema.TipoRet.OK, "Registrar chofer en ambulancia JKA5649.");
	    	p.ver(s.registrarChofer("JKA5649", "Juan", "32579"), Sistema.TipoRet.OK, "Registrar chofer en ambulancia JKA5649.");
	    	
		tituloPrueba("INFORME CHOFERES");
	    	p.ver(s.informeChoferes("ASDF65"), Sistema.TipoRet.ERROR1, "Informe chofer en ambulancia asdf65 inexistente.");
	    	p.ver(s.informeChoferes("JKA5649"), Sistema.TipoRet.OK, "Informe chofer en ambulancia JKA5649.");
	    	
	    	tituloPrueba("ELIMINAR CHOFER");
	    	p.ver(s.eliminarChofer("iuoiu98", "654988"), Sistema.TipoRet.ERROR1, "Eliminar chofer en ambulancia iuoiu98 inexistente.");
	    	p.ver(s.eliminarChofer("APM5464", "235784"), Sistema.TipoRet.ERROR2, "Eliminar chofer inexistente en ambulancia.");
	    	p.ver(s.eliminarChofer("APM5464", "654988"), Sistema.TipoRet.ERROR2, "Eliminar chofer de otra ambulancia.");
	    	
	    	p.ver(s.informeChoferes("JKA5649"), Sistema.TipoRet.OK, "Informe chofer en ambulancia JKA5649.");
		finPrueba(" Fin Prueba ERROR_5");
		}

		public static void tituloPrueba(String s){
	    	System.out.println("");   
	        System.out.println("********************************************************************************************************");
	        System.out.println("  "+ s );
	        System.out.println("********************************************************************************************************");
	    }

	    public static void finPrueba(String s){
	        System.out.println("**************************************** " + s +" ***************************************");
	        System.out.println("********************************************************************************************************");
	        System.out.println();
	    }
	    
	    public static void agregarCiudadesAlMapa(Sistema s, Prueba p){
	    	p.ver(s.agregarCiudad("Maldonado"), Sistema.TipoRet.OK, "Se agregó la ciudad Maldonado");
	    	p.ver(s.agregarCiudad("Flores"), Sistema.TipoRet.OK, "Se agregó la ciudad Flores");
	    	p.ver(s.agregarCiudad("Montevideo"), Sistema.TipoRet.OK, "Se agregó la ciudad Montevideo");
	    	p.ver(s.agregarCiudad("Melo"), Sistema.TipoRet.OK, "Se agregó la ciudad Melo");
	    	p.ver(s.agregarCiudad("Salto"), Sistema.TipoRet.OK, "Se agregó la ciudad Salto");
	    	p.ver(s.agregarCiudad("Paysandú"), Sistema.TipoRet.OK, "Se agregó la ciudad Paysandú");
	    	p.ver(s.agregarCiudad("Soriano"), Sistema.TipoRet.OK, "Se agregó la ciudad Soriano");
	    	p.ver(s.agregarCiudad("Rivera"), Sistema.TipoRet.OK, "Se agregó la ciudad Rivera");
	    	p.ver(s.agregarCiudad("Minas"), Sistema.TipoRet.OK, "Se agregó la ciudad Minas");
	    	p.ver(s.agregarCiudad("Colonia"), Sistema.TipoRet.OK, "Se agregó la ciudad Colonia");
	    	p.ver(s.agregarCiudad("Rocha"), Sistema.TipoRet.OK, "Se agregó la ciudad Rocha");
	    }
	    
	    public static void agregarRutas(Sistema s, Prueba p){
	    	p.ver(s.agregarRuta(1, 2, 20), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(1, 3, 4), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(3, 2, 4), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(2, 5, 7), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(3, 4, 10), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(3, 8, 8), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(8, 9, 18), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(3, 10, 12), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(4, 9, 19), Sistema.TipoRet.OK, "Se agregó la ruta"); 
	    	p.ver(s.agregarRuta(10, 6, 32), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(2, 7, 14), Sistema.TipoRet.OK, "Se agregó la ruta");
	    	p.ver(s.agregarRuta(4, 6, 29), Sistema.TipoRet.OK, "Se agregó la ruta"); 
	    }
	    
	    public static void registrarAmbulancias(Sistema s, Prueba p){
		    p.ver(s.registrarAmbulancia("Liu5687",1), Sistema.TipoRet.OK, "Se agregó la ambulancia Liu5687");
			p.ver(s.registrarAmbulancia("JKA5649",5), Sistema.TipoRet.OK, "Se agregó la ambulancia JKA5649");
			p.ver(s.registrarAmbulancia("POL8974",2), Sistema.TipoRet.OK, "Se agregó la ambulancia POL8974");
			p.ver(s.registrarAmbulancia("APM5464",5), Sistema.TipoRet.OK, "Se agregó la ambulancia APM5464");
			p.ver(s.registrarAmbulancia("LOJ2457",8), Sistema.TipoRet.OK, "Se agregó la ambulancia LOJ2457");
			p.ver(s.registrarAmbulancia("DFR6584",9), Sistema.TipoRet.OK, "Se agregó la ambulancia DFR6584");
			p.ver(s.registrarAmbulancia("BGY9834",1), Sistema.TipoRet.OK, "Se agregó la ambulancia BGY9834");
			p.ver(s.registrarAmbulancia("VCB5689",2), Sistema.TipoRet.OK, "Se agregó la ambulancia VCB5689");
			p.ver(s.registrarAmbulancia("NGH9984",3), Sistema.TipoRet.OK, "Se agregó la ambulancia NGH9984");
			p.ver(s.registrarAmbulancia("IOY4287",4), Sistema.TipoRet.OK, "Se agregó la ambulancia IOY4287");
			p.ver(s.registrarAmbulancia("QWE4579",5), Sistema.TipoRet.OK, "Se agregó la ambulancia QWE4579");
	    }

}

package pruebasJUnit;
import static org.junit.Assert.*;
import interfazObligatorio.ISistema.TipoRet;

import org.junit.Test;

import dominio.clases.Ambulancia.EstadoAmbulancia;
import sistema.SistemaImpl;


public class JUnitTest {

	/*
	@Test
	public void testCrearSistemaDeEmergencias() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(19), TipoRet.OK);
	}
	
	@Test
	public void testCrearSistemaDeEmergenciasFailNumeroCiudades() {
		SistemaImpl s = new SistemaImpl();
		System.out.println();
		System.out.println("Retorno testCrearSistemaDeEmergenciasFailNumeroCiudades:");
		assertEquals(s.crearSistemaDeEmergencias(0), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testDestruirSistemaEmergencias() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(19), TipoRet.OK);
		assertEquals(s.destruirSistemaEmergencias(), TipoRet.OK);
	}

	@Test
	public void testRegistrarAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 2), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1991", 2), TipoRet.OK);
	}
	
	@Test
	public void testRegistrarAmbulancia_CiudadNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		System.out.println();
		System.out.println("Retorno testRegistrarAmbulancia_CiudadNoExiste:");
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.ERROR1);
		System.out.println();
	}
	
	@Test
	public void testRegistrarAmbulancia_YaExisteAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		System.out.println("Retorno testRegistrarAmbulancia_YaExisteAmbulancia:");
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.ERROR2);
		System.out.println();
	}

	@Test
	public void testDeshabilitarAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.deshabilitarAmbulancia("SAM 1989"), TipoRet.OK);
	}

	@Test
	public void testDeshabilitarAmbulancia_noExisteAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		System.out.println();
		System.out.println("Retorno testDeshabilitarAmbulancia_noExisteAmbulancia");
		assertEquals(s.deshabilitarAmbulancia("SAM 1989"), TipoRet.ERROR1);
		System.out.println();
	}
	
	@Test
	public void testHabilitarAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
	}

	@Test
	public void testHabilitarAmbulancia_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		System.out.println("Retorno testHabilitarAmbulancia_AmbulanciaNoExiste:");
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.ERROR1);
		System.out.println();
	}
	
	@Test
	public void testHabilitarAmbulancia_AmbulanciaYaHabilitada() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testHabilitarAmbulanciaAmbulancia_YaHabilitada:");
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testEliminarAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.eliminarAmbulancia("SAM 1989"), TipoRet.OK);
	}

	@Test
	public void testEliminarAmbulancia_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		System.out.println("Retorno testEliminarAmbulancia_AmbulanciaNoExiste:");
		assertEquals(s.eliminarAmbulancia("SAM 1989"), TipoRet.ERROR1);
		System.out.println();
	}
	
	@Test
	public void testEliminarAmbulancia_AmbulanciaAsignadaViaje() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 10), TipoRet.OK);
		assertEquals(s.recibirEmergencia("SAM 1989", 2), TipoRet.OK);
		System.out.println("Retorno testEliminarAmbulancia_AmbulanciaAsignadaViaje:");
		assertEquals(s.eliminarAmbulancia("SAM 1989"), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testBuscarAmbulancia_SinEmergencias() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		System.out.println("Retorno testBuscarAmbulancia_SinEmergencias:");
		assertEquals(s.buscarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testBuscarAmbulancia_ConEmergencias() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(3), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Maldonado"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.recibirEmergencia("SAM 1989", 2), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.recibirEmergencia("SAM 1989", 3), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testBuscarAmbulancia_ConEmergencias:");
		assertEquals(s.buscarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println();
	}

	@Test
	public void testBuscarAmbulancia_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		System.out.println("Retorno testBuscarAmbulancia_AmbulanciaNoExiste:");
		assertEquals(s.buscarAmbulancia("SAM 1989"), TipoRet.ERROR1);
	}
	
	@Test
	public void testInformeAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(3), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Maldonado"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1991", 2), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1991"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1992", 2), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1992"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1993", 3), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1993"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1994", 3), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1994"), TipoRet.OK);
		System.out.println("Retorno testInformeAmbulancia:");
		assertEquals(s.informeAmbulancia(), TipoRet.OK);
		System.out.println();
	}

	@Test
	public void testInformeAmbulanciaByCiudadId() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1992", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1992"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1991", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1991"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1995", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1993", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1994", 1), TipoRet.OK);
		System.out.println("Retorno testInformeAmbulanciaByCiudadId:");
		assertEquals(s.informeAmbulancia(1), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testInformeAmbulancia_ByCiudadId_CiudadNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1992", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1992"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1991", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1991"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1995", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1993", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1994", 1), TipoRet.OK);
		System.out.println("Retorno testInformeAmbulancia_ByCiudadId:");
		assertEquals(s.informeAmbulancia(2), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testRecibirEmergencia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		assertEquals(s.recibirEmergencia("SAM 1990", 2), TipoRet.OK);
	}
	
	@Test
	public void testRecibirEmergencia_CiudadNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		System.out.println("Retorno testRecibirEmergencia_CiudadNoExiste:");
		assertEquals(s.recibirEmergencia("SAM 1990", 3), TipoRet.ERROR1);
	}
	
	@Test
	public void testRecibirEmergencia_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		System.out.println("Retorno testRecibirEmergencia_AmbulanciaNoExiste:");
		assertEquals(s.recibirEmergencia("SAM 1991", 2), TipoRet.ERROR2);
		System.out.println();
	}

	@Test
	public void testCambiarUbicacion() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		assertEquals(s.cambiarUbicacion("SAM 1990", 2), TipoRet.OK);
	}
	
	@Test
	public void testCambiarUbicacion_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		System.out.println("Retorno testCambiarUbicacion_AmbulanciaNoExiste:");
		assertEquals(s.cambiarUbicacion("SAM 1991", 2), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testCambiarUbicacion_CiudadNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1990"), TipoRet.OK);
		System.out.println("Retorno testCambiarUbicacion_CiudadNoExiste:");
		assertEquals(s.cambiarUbicacion("SAM 1990", 3), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testAgregarCiudad() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
	}
	
	@Test
	public void testAgregarCiudad_faltaCapacidad() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		System.out.println("Retorno testAgregarCiudad_faltaCapacidad:");
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.ERROR1);
	}
	
	@Test
	public void testAgregarCiudad_YaExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		System.out.println("Retorno testAgregarCiudad_YaExiste:");
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testListarCiudades() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(19), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Paysandu"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Rio Negro"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Soriano"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Colonia"), TipoRet.OK);
		assertEquals(s.agregarCiudad("San Jose"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Flores"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Durazno"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Tacuarembo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Rivera"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Cerro Largo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Treinta y Tres"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Lavalleja"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Maldonado"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Rocha"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Florida"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		System.out.println("Retorno testListarCiudades:");
		assertEquals(s.listarCiudades(), TipoRet.OK);
		System.out.println();
	}

	@Test
	public void testAgregarRuta() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
	}
	
	@Test
	public void testAgregarRuta_CiudadDestinoNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		System.out.println("Retorno testAgregarRuta_CiudadDestinoNoExiste:");
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.ERROR3);
		System.out.println();
	}
	
	@Test
	public void testAgregarRuta_CiudadOrigenNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		System.out.println("Retorno testAgregarRuta_CiudadOrigenNoExiste:");
		assertEquals(s.agregarRuta(3, 1, 30), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testAgregarRuta_DuracionInvalida() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		System.out.println("Retorno testAgregarRuta_DuracionInvalida:");
		assertEquals(s.agregarRuta(3, 1, -5), TipoRet.ERROR1);
		System.out.println();
	}

	
	@Test
	public void testModificarDemora() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		System.out.println("Retorno testModificarDemora:");
		assertEquals(s.modificarDemora(1, 2, 45), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testModificarDemora_CiudadOrigenNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		System.out.println("Retorno testModificarDemora_CiudadOrigenNoExiste:");
		assertEquals(s.modificarDemora(3, 1, 45), TipoRet.ERROR2);
		System.out.println();
	}
	
	@Test
	public void testModificarDemora_CiudadDestinoNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		System.out.println("Retorno testModificarDemora_CiudadDestinoNoExiste:");
		assertEquals(s.modificarDemora(1, 3, 45), TipoRet.ERROR3);
		System.out.println();
	}
	
	@Test
	public void testModificarDemora_DuracionInvalida() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		System.out.println("Retorno testModificarDemora_DuracionInvalida:");
		assertEquals(s.modificarDemora(1, 2, -1), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testAmbulanciaMasCercana_MismaCiudad() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testAmbulanciaMasCercana_MismaCiudad:");
		assertEquals(s.ambulanciaMasCercana(1), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testAmbulanciaMasCercana_CiudadLimitrofe() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testAmbulanciaMasCercana_CiudadLimitrofe:");
		assertEquals(s.ambulanciaMasCercana(2), TipoRet.OK);
		System.out.println();
	}*/
	
	@Test
	public void testAmbulanciaMasCercana_NO_CiudadLimitrofe() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(3), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Paysandu"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testAmbulanciaMasCercana_MismaCiudad:");
		assertEquals(s.ambulanciaMasCercana(3), TipoRet.OK);
		System.out.println();
	}
	
	/*
	
	@Test
	public void testAmbulanciaMasCercana_MasDeUnaAmbulanciaDisponible() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1995", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1995"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testAmbulanciaMasCercana_MasDeUnaAmbulanciaDisponible:");
		assertEquals(s.ambulanciaMasCercana(2), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testAmbulanciaMasCercana_CiudadNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(2), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Salto"), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testAmbulanciaMasCercana_CiudadNoExiste:");
		assertEquals(s.ambulanciaMasCercana(3), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testRegistrarChofer() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "NombreChofer", "1.111.111-1"), TipoRet.OK);
	}
	
	@Test
	public void testRegistrarChofer_AmbulanciaNoExiste() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		System.out.println("Retorno testRegistrarChofer_AmbulanciaNoExiste:");
		assertEquals(s.registrarChofer("SAM 1990", "NombreChofer", "1.111.111-1"), TipoRet.ERROR1);
		System.out.println();
	}

	@Test
	public void testEliminarChofer() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "NombreChofer", "1.111.111-1"), TipoRet.OK);
		assertEquals(s.eliminarChofer("SAM 1989", "1.111.111-1"), TipoRet.OK);
	}
	
	@Test
	public void testEliminarChofer_NoExisteAmbulancia() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "NombreChofer", "1.111.111-1"), TipoRet.OK);
		System.out.println("Retorno testRegistrarChofer_AmbulanciaNoExiste:");
		assertEquals(s.eliminarChofer("SAM 1990", "1.111.111-1"), TipoRet.ERROR1);
		System.out.println();
	}
	
	@Test
	public void testEliminarChofer_ChoferNoAsociado() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "NombreChofer", "1.111.111-1"), TipoRet.OK);
		System.out.println("Retorno testEliminarChofer_ChoferNoAsociado:");
		assertEquals(s.eliminarChofer("SAM 1989", "1.111.111-2"), TipoRet.ERROR2);
		System.out.println();
	}

	@Test
	public void testInformeChoferes() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(1), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.habilitarAmbulancia("SAM 1989"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "Silvina", "1.111.111-1"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "Laura", "1.222.222-2"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "Jorge", "1.333.333-3"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "Mauro", "1.444.444-4"), TipoRet.OK);
		assertEquals(s.registrarChofer("SAM 1989", "Catalina", "1.555.555-5"), TipoRet.OK);
		System.out.println("Retorno testEliminarChofer_ChoferNoAsociado:");
		assertEquals(s.informeChoferes("SAM 1989"), TipoRet.OK);
		System.out.println();
	}
	
	
	@Test
	public void testInformeCiudades() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(4), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		
		//CANELONES
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1989", 1), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1990", 1), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1990", EstadoAmbulancia.DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1991", 1), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1991", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1992", 1), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1992", EstadoAmbulancia.DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1993", 1), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1993", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1994", 1), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1994", EstadoAmbulancia.DISPONIBLE);
		
		//MALDONADO
		assertEquals(s.agregarCiudad("Maldonado"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1995", 2), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 1996", 2), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1996", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1997", 2), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1997", EstadoAmbulancia.DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1998", 2), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1998", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 1999", 2), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 1999", EstadoAmbulancia.DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 2000", 2), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2000", EstadoAmbulancia.NO_DISPONIBLE);
		
		//ROCHA
		assertEquals(s.agregarCiudad("Rocha"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 2001", 3), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 2002", 3), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2002", EstadoAmbulancia.ASIGNADA);
		assertEquals(s.registrarAmbulancia("SAM 2003", 3), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2003", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 2004", 3), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2004", EstadoAmbulancia.EN_REPARACION);
		assertEquals(s.registrarAmbulancia("SAM 2005", 3), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2005", EstadoAmbulancia.SIN_ARREGLO);
		assertEquals(s.registrarAmbulancia("SAM 2006", 3), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2006", EstadoAmbulancia.DISPONIBLE);
		
		//TREINTA Y TRES
		assertEquals(s.agregarCiudad("Treinta y Tres"), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 2007", 4), TipoRet.OK);
		assertEquals(s.registrarAmbulancia("SAM 2008", 4), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2008", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 2009", 4), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2009", EstadoAmbulancia.NO_DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 2010", 4), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2010", EstadoAmbulancia.EN_REPARACION);
		assertEquals(s.registrarAmbulancia("SAM 2011", 4), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2011", EstadoAmbulancia.DISPONIBLE);
		assertEquals(s.registrarAmbulancia("SAM 2012", 4), TipoRet.OK);
		s.cambiarEstadoAmbulancia("SAM 2012", EstadoAmbulancia.DISPONIBLE);

		assertEquals(s.agregarRuta(1, 2, 5), TipoRet.OK);
		assertEquals(s.agregarRuta(2, 3, 10), TipoRet.OK);
		assertEquals(s.agregarRuta(3, 4, 15), TipoRet.OK);
		assertEquals(s.agregarRuta(4, 1, 20), TipoRet.OK);
		
		System.out.println("Retorno testInformeCiudades:");
		assertEquals(s.informeCiudades(), TipoRet.OK);
		System.out.println();
	}
	
	@Test
	public void testCiudadesEnRadio() {
		SistemaImpl s = new SistemaImpl();
		assertEquals(s.crearSistemaDeEmergencias(7																																																																																																																																																																																																		), TipoRet.OK);
		s.volverCeroNumeradoraCiudades();
		
		//Desde Montevideo se puede llegar a Canelones pero no a Maldonado. Para ir a Maldonado hay que pasar x Canelones
		assertEquals(s.agregarCiudad("Montevideo"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Canelones"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Maldonado"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Rocha"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Colonia"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Artigas"), TipoRet.OK);
		assertEquals(s.agregarCiudad("Pelotas"), TipoRet.OK);
		
		assertEquals(s.agregarRuta(1, 2, 30), TipoRet.OK);
		assertEquals(s.agregarRuta(2, 3, 15), TipoRet.OK);
		assertEquals(s.agregarRuta(2, 4, 10), TipoRet.OK);
		assertEquals(s.agregarRuta(1, 5, 30), TipoRet.OK);
		assertEquals(s.agregarRuta(5, 6, 5), TipoRet.OK);
		assertEquals(s.agregarRuta(6, 7, 1), TipoRet.OK);
		
		System.out.println("Retorno testCiudadesEnRadio:");
		assertEquals(s.ciudadesEnRadio(1, 60), TipoRet.OK);
		System.out.println();
	}
																			
	
	
	/*
	@Test
	public void testRutaMasRapida() {
		fail("Not yet implemented");
	}
	 */
}

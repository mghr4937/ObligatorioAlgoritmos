package sistema;

public class Main {

	public static void main(String[] args) {
		
		 SistemaImpl sistema = new SistemaImpl();
		 
		 //PUNTO 2.1.1 - Crear Sistema de Emergencias
		 sistema.crearSistemaDeEmergencias(19);
		 
		//PUNTO 2.1.2 - Destruir Sistema de Emergencias
		 //sistema.destruirSistemaEmergencias();
		 
		 //PUNTO 2.3.1 - Agregar Ciudad al Mapa
		 /*Ciudad 1: */sistema.agregarCiudad("Artigas");
		 /*Ciudad 2: */sistema.agregarCiudad("Salto");
		 /*Ciudad 3: */sistema.agregarCiudad("Paysandu");
		 /*Ciudad 4: */sistema.agregarCiudad("Rio Negro");
		 /*Ciudad 5: */sistema.agregarCiudad("Soriano");
		 /*Ciudad 6: */sistema.agregarCiudad("Colonia");
		 /*Ciudad 7: */sistema.agregarCiudad("San Jose");
		 /*Ciudad 8: */sistema.agregarCiudad("Flores");
		 /*Ciudad 9: */sistema.agregarCiudad("Durazno");
		 /*Ciudad 10: */sistema.agregarCiudad("Tacuarembo");
		 /*Ciudad 11: */sistema.agregarCiudad("Rivera");
		 /*Ciudad 12: */sistema.agregarCiudad("Cerro Largo");
		 /*Ciudad 13: */sistema.agregarCiudad("Treinta y Tres");
		 /*Ciudad 14: */sistema.agregarCiudad("Lavalleja");
		 /*Ciudad 15: */sistema.agregarCiudad("Maldonado");
		 /*Ciudad 16: */sistema.agregarCiudad("Rocha");
		 /*Ciudad 17: */sistema.agregarCiudad("Canelones");
		 /*Ciudad 18: */sistema.agregarCiudad("Florida");
		 /*Ciudad 19: */sistema.agregarCiudad("Montevideo");
		 
		//PUNTO 2.3.2 - Listar Ciudades
		 //sistema.listarCiudades();
		 
		//PUNTO 2.3.3 - Agregar Ruta al Mapa
		 /* Montevideo - Canelones */		sistema.agregarRuta(19, 17, 10);
		 /* Canelones - Maldonado */		sistema.agregarRuta(17, 15, 10);
		 /* Maldonado - Rocha */			sistema.agregarRuta(15, 16, 10);
		 /* Rocha - Treinta y Tres */		sistema.agregarRuta(16, 13, 10);
		 /* Treinta y Tres - Cerro Largo */	sistema.agregarRuta(13, 12, 10);
		 /* Cerro Largo - Rivera */			sistema.agregarRuta(12, 11, 10);
		 /* Montevideo - San Jose */		sistema.agregarRuta(19, 7, 10);
		 /* San Jose - Flores */			sistema.agregarRuta(7, 8, 10);
		 /* Flores - Durazno */				sistema.agregarRuta(8, 9, 10);
		 /* Durazno - Cerro Largo */		sistema.agregarRuta(9, 12, 10);
		 /* Rivera - Salto */				sistema.agregarRuta(11, 2, 10);
		 /* Salto - Artigas */				sistema.agregarRuta(2, 1, 10);
		 /* Canelones - Lavalleja */		sistema.agregarRuta(17, 14, 10);
		 /* Lavalleja - Treinta y Tres */	sistema.agregarRuta(14,13 , 10);
		 /* Treinta y Tres - Durazno */		sistema.agregarRuta(13, 9, 10);
		 /* Canelones - Florida */			sistema.agregarRuta(17, 18, 10);
		 /* Florida - Durazno */			sistema.agregarRuta(18, 9, 10);
		 /* Florida - Flores */				sistema.agregarRuta(18, 8, 10);
		 /* Flores - Soriano */				sistema.agregarRuta(8, 5, 10);
		 /* Soriano - Rio Negro */			sistema.agregarRuta(5, 4, 10);
		 /* Rio Negro - Paysandu */			sistema.agregarRuta(4, 3, 10);
		 /* Paysandu - Salto */				sistema.agregarRuta(3, 2, 10);
		 /* Salto - Tacuarembo */			sistema.agregarRuta(2, 10, 10);
		 /* Canelones - San Jose */			sistema.agregarRuta(17, 7, 10);
		 /* Maldonado - Lavalleja */		sistema.agregarRuta(15, 14, 10);
		 /* Rocha - Lavalleja */			sistema.agregarRuta(16, 14, 10);
		 /* Lavalleja - Florida */			sistema.agregarRuta(14, 18, 10);
		 /* Florida - Treinta y Tres */		sistema.agregarRuta(18, 13, 10);
		 /* Florida - San Jose */			sistema.agregarRuta(18, 7, 10);
		 /* San Jose - Colonia */			sistema.agregarRuta(7, 6, 10);
		 /* San Jose - Soriano */			sistema.agregarRuta(7, 5, 10);
		 /* Colonia - Soriano */			sistema.agregarRuta(6, 5, 10);
		 /* Colonia - Flores */				sistema.agregarRuta(6, 8, 10);
		 /* Rio Negro - Tacuarembo */		sistema.agregarRuta(4, 10, 10);
		 /* Durazno - Tacuarembo */			sistema.agregarRuta(9, 10, 10);
		 /* Paysandu - Tacuarembo */		sistema.agregarRuta(3, 10, 10);
		 /* Rivera - Tacuarembo */			sistema.agregarRuta(11, 10, 10);
		 /* Tacuarembo - Cerro Largo */		sistema.agregarRuta(10, 12, 10);
		 
		 //PUNTO 2.2.1 - Registrar Ambulancia
		 //sistema.registrarAmbulancia("SAM 1989", 1);
		 sistema.registrarAmbulancia("SAM 1990", 2);
		 sistema.registrarAmbulancia("SAM 1991", 3);
		 sistema.registrarAmbulancia("SAM 1992", 4);
		 sistema.registrarAmbulancia("SAM 1993", 5);
		 sistema.registrarAmbulancia("SAM 1994", 6);
		 sistema.registrarAmbulancia("SAM 1995", 7);
		 sistema.registrarAmbulancia("SAM 1996", 8);
		 sistema.registrarAmbulancia("SAM 1997", 9);
		 sistema.registrarAmbulancia("SAM 1998", 10);
		 sistema.registrarAmbulancia("SAM 1999", 11);
		 sistema.registrarAmbulancia("SAM 2000", 12);
		 sistema.registrarAmbulancia("SAM 2001", 13);
		 sistema.registrarAmbulancia("SAM 2002", 14);
		 sistema.registrarAmbulancia("SAM 2003", 15);
		 sistema.registrarAmbulancia("SAM 2004", 16);
		 sistema.registrarAmbulancia("SAM 2005", 17);
		 sistema.registrarAmbulancia("SAM 2006", 18);
		 sistema.registrarAmbulancia("SAM 2007", 19);
		 
		//PUNTO 2.2.2 - Deshabilitar Ambulancia
		 //sistema.deshabilitarAmbulancia("SAM 1989");
		 
		 //PUNTO 2.2.3 - Habilitar Ambulancia
		 //sistema.habilitarAmbulancia("SAM 1989");
		 
		//PUNTO 2.2.4 - Eliminar Ambulancia
		 //sistema.eliminarAmbulancia("SAM 1989");
		 //sistema.registrarAmbulancia("SAM 1989", 1); // <-- La vuelvo a crear
		 
		//PUNTO 2.2.5 - Buscar Ambulancia
		 //sistema.buscarAmbulancia("SAM 1989");
		 
		 //PUNTO 2.2.6 - Informe de Ambulancia
		 //sistema.informeAmbulancia();
		 
		//PUNTO 2.2.7 - Ambulancias por Ciudad
		 /*sistema.informeAmbulancia(1);
		 sistema.informeAmbulancia(2);
		 sistema.informeAmbulancia(3);
		 sistema.informeAmbulancia(4);
		 sistema.informeAmbulancia(5);
		 sistema.informeAmbulancia(6);
		 sistema.informeAmbulancia(7);
		 sistema.informeAmbulancia(8);
		 sistema.informeAmbulancia(9);
		 sistema.informeAmbulancia(10);
		 sistema.informeAmbulancia(11);
		 sistema.informeAmbulancia(12);
		 sistema.informeAmbulancia(13);
		 sistema.informeAmbulancia(14);
		 sistema.informeAmbulancia(15);
		 sistema.informeAmbulancia(16);
		 sistema.informeAmbulancia(17);
		 sistema.informeAmbulancia(18);
		 sistema.informeAmbulancia(19);*/

		 //PUNTO 2.2.8 - Recibir Emergencia
		 /*sistema.recibirEmergencia("SAM 1989", 19);
		 sistema.recibirEmergencia("SAM 1990", 1);
		 sistema.recibirEmergencia("SAM 1991", 2);
		 sistema.recibirEmergencia("SAM 1992", 3);
		 sistema.recibirEmergencia("SAM 1993", 4);
		 sistema.recibirEmergencia("SAM 1994", 5);
		 sistema.recibirEmergencia("SAM 1995", 6);
		 sistema.recibirEmergencia("SAM 1996", 7);
		 sistema.recibirEmergencia("SAM 1997", 8);
		 sistema.recibirEmergencia("SAM 1998", 9);
		 sistema.recibirEmergencia("SAM 1999", 10);
		 sistema.recibirEmergencia("SAM 2000", 11);
		 sistema.recibirEmergencia("SAM 2001", 12);
		 sistema.recibirEmergencia("SAM 2002", 13);
		 sistema.recibirEmergencia("SAM 2003", 14);
		 sistema.recibirEmergencia("SAM 2004", 15);
		 sistema.recibirEmergencia("SAM 2005", 16);
		 sistema.recibirEmergencia("SAM 2006", 17);
		 sistema.recibirEmergencia("SAM 2007", 18);*/
//El recibir emergencia llama al cambiarUbicacion, por eso llamo pasandole otra ciudad.
		 
		 
		 /* ANTES DE PROBAR EL MODIFICAR DEMORA, PREGUNTAR POR COMPOSICION DE VIAJE (RUTA + AMBULANCIA) */
		 /* SI ESA IMPLEMENTACION ES CORRECTA, HACER METODO AUXILIAR AGREGAR VIAJE*/
		 
		 //PUNTO 2.3.4 - Modificar demora de viaje
		 /*sistema.modificarDemora(19, 17, 20);
		 sistema.modificarDemora(17, 15, 20);
		 sistema.modificarDemora(15, 16, 20);*/
		 
		 
		 
		 
		 
		 						/************** QUEDE ACÁ **************/
/* FUNCIONA BIEN SI HAY UNA AMBULANCIA EN LA CIUDAD. PERO SI HAY QUE BUSCAR LA MAS CERCANA EN OTRA CIUDAD NO RETORNA NADA */
		 
		 
		 
		 
		 //PUNTO 2.3.5 - Ambulancia más cercana
		 sistema.ambulanciaMasCercana(1);
		 /*sistema.ambulanciaMasCercana(2);
		 sistema.ambulanciaMasCercana(3);
		 sistema.ambulanciaMasCercana(4);
		 sistema.ambulanciaMasCercana(5);*/
		 
		 //PUNTO 2.3.6 - Ruta más rápida
		 
		 //PUNTO 2.3.7 - Informe de Ciudades
		 
		 //PUNTO 2.3.8 - Informe de Ciudades en un radio
		 
		 
		 //PUNTO 2.4.1 - Registrar Chofer
		 /*sistema.registrarChofer("SAM 1989", "Maria", "1.000.001-9");
		 sistema.registrarChofer("SAM 1989", "Lucia", "1.000.002-9");
		 sistema.registrarChofer("SAM 1990", "Isabel", "1.000.003-9");
		 sistema.registrarChofer("SAM 1990", "Claudia", "1.000.004-9");
		 sistema.registrarChofer("SAM 1991", "Roxana", "1.000.005-9");
		 sistema.registrarChofer("SAM 1991", "Mariana", "1.000.006-9");
		 sistema.registrarChofer("SAM 1992", "Fiorella", "1.000.007-9");
		 sistema.registrarChofer("SAM 1992", "Romina", "1.000.008-9");
		 sistema.registrarChofer("SAM 1993", "Luisa", "1.000.009-9");
		 sistema.registrarChofer("SAM 1993", "Ana", "1.000.010-9");
		 sistema.registrarChofer("SAM 1994", "Paula", "1.000.011-9");
		 sistema.registrarChofer("SAM 1994", "Natalie", "1.000.012-9");
		 sistema.registrarChofer("SAM 1995", "Beatriz", "1.000.013-9");
		 sistema.registrarChofer("SAM 1995", "Rosinna", "1.000.014-9");
		 sistema.registrarChofer("SAM 1996", "Rosario", "1.000.015-9");
		 sistema.registrarChofer("SAM 1996", "Miriam", "1.000.016-9");
		 sistema.registrarChofer("SAM 1997", "Victoria", "1.000.017-9");
		 sistema.registrarChofer("SAM 1997", "Florencia", "1.000.018-9");
		 sistema.registrarChofer("SAM 1998", "Alejandra", "1.000.019-9");
		 sistema.registrarChofer("SAM 1998", "Josefina", "1.000.020-9");
		 sistema.registrarChofer("SAM 1999", "Natalia", "1.000.021-9");
		 sistema.registrarChofer("SAM 1999", "Belen", "1.000.022-9");
		 sistema.registrarChofer("SAM 2000", "Eugenia", "1.000.023-9");
		 sistema.registrarChofer("SAM 2000", "Aldana", "1.000.024-9");
		 sistema.registrarChofer("SAM 2001", "Alessandra", "1.000.025-9");
		 sistema.registrarChofer("SAM 2001", "Ana Clara", "1.000.026-9");
		 sistema.registrarChofer("SAM 2002", "Analia", "1.000.027-9");
		 sistema.registrarChofer("SAM 2002", "Maite", "1.000.028-9");
		 sistema.registrarChofer("SAM 2003", "Angeles", "1.000.029-9");
		 sistema.registrarChofer("SAM 2003", "Antonella", "1.000.030-9");
		 sistema.registrarChofer("SAM 2004", "Mathias", "1.000.001-9");
		 sistema.registrarChofer("SAM 2004", "Matias", "1.000.001-9");
		 sistema.registrarChofer("SAM 2005", "Franco", "1.000.001-9");
		 sistema.registrarChofer("SAM 2005", "Rodrigo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2006", "Fabricio", "1.000.001-9");
		 sistema.registrarChofer("SAM 2006", "Aldo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2007", "Leandro", "1.000.001-9");
		 sistema.registrarChofer("SAM 2007", "Marcelo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2008", "Mauro", "1.000.001-9");
		 sistema.registrarChofer("SAM 2008", "Mauricio", "1.000.001-9");
		 sistema.registrarChofer("SAM 2009", "Roberto", "1.000.001-9");
		 sistema.registrarChofer("SAM 2009", "Jorge", "1.000.001-9");
		 sistema.registrarChofer("SAM 2010", "Alejandro", "1.000.001-9");
		 sistema.registrarChofer("SAM 2010", "Carlos", "1.000.001-9");
		 sistema.registrarChofer("SAM 2011", "Alejo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2011", "Ignacio", "1.000.001-9");
		 sistema.registrarChofer("SAM 2012", "Ricardo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2012", "Pablo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2013", "Paulo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2013", "Gonzalo", "1.000.001-9");
		 sistema.registrarChofer("SAM 2014", "Martin", "1.000.001-9");
		 sistema.registrarChofer("SAM 2014", "Santiago", "1.000.001-9");
		 sistema.registrarChofer("SAM 2015", "Fabio", "1.000.001-9");
		 sistema.registrarChofer("SAM 2015", "Leslier", "1.000.001-9");
		 sistema.registrarChofer("SAM 2016", "Joaquin", "1.000.001-9");
		 sistema.registrarChofer("SAM 2016", "Marcos", "1.000.001-9");
		 sistema.registrarChofer("SAM 2017", "Pancracio", "1.000.001-9");
		 sistema.registrarChofer("SAM 2017", "Kleber", "1.000.001-9");
		 sistema.registrarChofer("SAM 2018", "Rafael", "1.000.001-9");
		 sistema.registrarChofer("SAM 2018", "Fernando", "1.000.001-9");*/
		 
		 //PUNTO 2.4.2 - Eliminar Chofer
		 /*sistema.eliminarChofer("SAM 1989", "1.000.001-9");
		 sistema.registrarChofer("SAM 1989", "Maria", "1.000.001-9"); //<-- La vuelvo a crear*/
		 
		 //PUNTO 2.4.3 - Informe de Choferes Habilitados
		 //sistema.informeChoferes("SAM 1989");
	}

}

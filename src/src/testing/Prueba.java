package testing;

//import Obligatorio.Sistema.TipoRet;;

import interfazObligatorio.Sistema.TipoRet;


public class Prueba {

	static int	cantCorrectas, cantIncorrectas, cantNoImplementadas;

	void inicializarResultadosPrueba() {
		cantCorrectas = cantIncorrectas = cantNoImplementadas = 0;
	}

	public void ver(TipoRet TipoRet, TipoRet TipoRetEsperado, String comentario)
	{
		System.out.println();

		System.out.println( "----------------------------- Testeo --------------------------------");

		imprimirComentario(comentario);

		imprimirTipoRet(TipoRet,TipoRetEsperado);

		System.out.println("---------------------------------------------------------------------");
	    System.out.println();

	    if (TipoRet.equals(TipoRetEsperado))
			cantCorrectas++;
		else
		{
			if (TipoRet.equals(TipoRet.NO_IMPLEMENTADA))
				cantNoImplementadas++;
			else
				cantIncorrectas++;

		}
	}

	void imprimirComentario(String comentario)
	{
		if ( comentario!=null || !comentario.isEmpty())
		{
			System.out.println("\n  Comentario: " + comentario );
			System.out.println();
		}
	}

	public void imprimirTipoRet(TipoRet TipoRet, TipoRet TipoRetEsperado)
	{
		System.out.println("  TipoRet de la app.: "+ TipoRet +" ->\t" + getStringTipoRet(TipoRet));

		if ( TipoRet == TipoRetEsperado )
		{
			System.out.println( "\t\t.........OK........." );
		}
		else
		{
			System.out.println("  Se esperaba.......: " + TipoRetEsperado + " ->\t" + getStringTipoRet(TipoRetEsperado));
		}
	}

	public String getStringTipoRet(TipoRet TipoRet)
	{
		switch( TipoRet )
		{
			case OK:
				return "OK";
			case ERROR:
				return "ERROR";
			case ERROR1:
				return "ERROR1";
			case ERROR2:
				return "ERROR2";
			case ERROR3:
				return "ERROR3";
			case ERROR4:
				return "ERROR4";
			case ERROR5:
				return "ERROR5";
			case NO_IMPLEMENTADA:
				return "NO_IMPLEMENTADA";
			default:
				return "NO_IMPLEMENTADA";
		}
	}

	public void imprimirResultadosPrueba()
	{
		System.out.println();
		System.out.println( "  +------------------------------+");
		System.out.println("    RESULTADOS DE LA PRUEBA    ");
		System.out.println("    Pruebas Correctas: " + cantCorrectas);
		System.out.println("    Pruebas Incorrectas: " + cantIncorrectas );
		System.out.println("    Pruebas NI: " + cantNoImplementadas);
		System.out.println("  +------------------------------+");
		System.out.println();
	}
}

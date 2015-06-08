package dominio.clases;

public class Emergencia {
	
	private int iEmergenciaId;
	private Ciudad ciudadEmergencia;
	private String sComentarioEmergencia;
	private String sAmbulanciaId;
	private static int numeradora = 0;
	
	public static int getNumeradora() {
		return numeradora;
	}
	private static void setNumeradora(int numeradora) {
		Emergencia.numeradora = numeradora;
	}
	public int getiEmergenciaId() {
		return iEmergenciaId;
	}
	public void setiEmergenciaId(int iEmergenciaId) {
		this.iEmergenciaId = iEmergenciaId;
	}
	public Ciudad getCiudadEmergencia() {
		return ciudadEmergencia;
	}
	public void setCiudadEmergencia(Ciudad ciudadEmergencia) {
		this.ciudadEmergencia = ciudadEmergencia;
	}
	public String getsComentarioEmergencia() {
		return sComentarioEmergencia;
	}
	public void setsComentarioEmergencia(String sComentarioEmergencia) {
		this.sComentarioEmergencia = sComentarioEmergencia;
	}
	public String getsAmbulanciaId() {
		return sAmbulanciaId;
	}
	public void setsAmbulanciaId(String sAmbulanciaId) {
		this.sAmbulanciaId = sAmbulanciaId;
	}
	
	private void NumerarEmergencia() {
		this.iEmergenciaId = ++Emergencia.numeradora;
	}
	
	public Emergencia(Ciudad ciudadEmergencia, String ambulanciaId){
		NumerarEmergencia();
		this.ciudadEmergencia = ciudadEmergencia;
		this.sAmbulanciaId = ambulanciaId;
	}
	
	public Emergencia(Ciudad ciudadEmergencia, String ambulanciaId, String comentario){
		NumerarEmergencia();
		this.ciudadEmergencia = ciudadEmergencia;
		this.sAmbulanciaId = ambulanciaId;
		this.sComentarioEmergencia = comentario;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Emergencia)) {
			return false;
		}
		Emergencia that = (Emergencia) other;
		return this.getiEmergenciaId() == that.getiEmergenciaId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((ciudadEmergencia == null) ? 0 : ciudadEmergencia.hashCode());
		result = prime * result + iEmergenciaId;
		result = prime * result
				+ ((sAmbulanciaId == null) ? 0 : sAmbulanciaId.hashCode());
		result = prime
				* result
				+ ((sComentarioEmergencia == null) ? 0 : sComentarioEmergencia
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Emergencia numero " + this.iEmergenciaId + " ocurrió en la ciudad de " + this.ciudadEmergencia.getSNombreCiudad()
				+ " y fue asistida por la ambulancia de matrícula " + this.sAmbulanciaId;
	}
	
	
	
	
	
	
}

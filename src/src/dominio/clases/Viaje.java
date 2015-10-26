package dominio.clases;

public class Viaje {

	private Ruta ruta;
	private Ambulancia ambulancia;

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Viaje() {
	}

	public Viaje(Ambulancia laAmbulancia, Ruta ruta) {
		this.ruta = ruta;
		this.ambulancia = laAmbulancia;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Viaje)) {
			return false;
		}
		Viaje that = (Viaje) other;
		return this.getAmbulancia().equals(that.getAmbulancia())
				&& this.getRuta().equals(that.getRuta());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ambulancia == null) ? 0 : ambulancia.hashCode());
		result = prime * result + ((ruta == null) ? 0 : ruta.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "CiudadOrigen: " + this.ruta.getCiudadOrigen()
				+ ", ciudadDestino: " + this.getRuta().getCiudadDestino()
				+ ", demoraMinutos: " + this.ruta.getiMinutosViaje()
				+ ", ambulancia matrícula "
				+ this.ambulancia.getsIdAmbulancia();
	}
}

package dominio.clases;

public class Ruta {
	
	private Ciudad CiudadOrigen;
	private Ciudad CiudadDestino;
	private int iMinutosViaje;

	public Ciudad getCiudadOrigen() {
		return CiudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		CiudadOrigen = ciudadOrigen;
	}

	public Ciudad getCiudadDestino() {
		return CiudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {
		CiudadDestino = ciudadDestino;
	}

	public int getiMinutosViaje() {
		return iMinutosViaje;
	}
	
	public void setiMinutosViaje(int iMinutosViaje) {
		this.iMinutosViaje = iMinutosViaje;
	}
	
	public Ruta(){
		
	}
	
	public Ruta(Ciudad CiudadOrigen, Ciudad CiudadDestino, int iMinutosViaje) {
		this.CiudadOrigen = CiudadOrigen;
		this.CiudadDestino = CiudadDestino;
		this.iMinutosViaje = iMinutosViaje;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((CiudadDestino == null) ? 0 : CiudadDestino.hashCode());
		result = prime * result
				+ ((CiudadOrigen == null) ? 0 : CiudadOrigen.hashCode());
		result = prime * result + iMinutosViaje;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ruta)) {
			return false;
		}
		Ruta that = (Ruta) other;
		return this.CiudadOrigen.equals(that.CiudadOrigen) && this.CiudadDestino.equals(that.CiudadDestino)
			|| this.CiudadOrigen.equals(that.CiudadDestino) && this.CiudadDestino.equals(that.CiudadOrigen);	
	}
	
	@Override
	public String toString() {
		return "Ciudad Origen: " + this.getCiudadOrigen().getSNombreCiudad() + " - Ciudad Destino: " + 
						this.getCiudadDestino().getSNombreCiudad() + " - "+ this.getiMinutosViaje()  + " Minutos";
	}
	
	
	
	
	

}

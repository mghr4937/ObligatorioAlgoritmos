package dominio.clases;

public class Ruta {
	
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private int iMinutosViaje;

	public Ciudad getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(Ciudad ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public Ciudad getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(Ciudad ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
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
		this.ciudadOrigen = CiudadOrigen;
		this.ciudadDestino = CiudadDestino;
		this.iMinutosViaje = iMinutosViaje;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ciudadDestino == null) ? 0 : ciudadDestino.hashCode());
		result = prime * result
				+ ((ciudadOrigen == null) ? 0 : ciudadOrigen.hashCode());
		result = prime * result + iMinutosViaje;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ruta)) {
			return false;
		}
		Ruta that = (Ruta) other;
		return this.ciudadOrigen.equals(that.ciudadOrigen) && this.ciudadDestino.equals(that.ciudadDestino)
			|| this.ciudadOrigen.equals(that.ciudadDestino) && this.ciudadDestino.equals(that.ciudadOrigen);	
	}
	
	@Override
	public String toString() {
		return "Ciudad Origen: " + this.getCiudadOrigen().getSNombreCiudad() + " - Ciudad Destino: " + 
						this.getCiudadDestino().getSNombreCiudad() + " - "+ this.getiMinutosViaje()  + " Minutos";
	}
}

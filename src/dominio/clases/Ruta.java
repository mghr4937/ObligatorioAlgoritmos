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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ruta other = (Ruta) obj;
		if (CiudadDestino == null) {
			if (other.CiudadDestino != null)
				return false;
		} else if (!CiudadDestino.equals(other.CiudadDestino))
			return false;
		if (CiudadOrigen == null) {
			if (other.CiudadOrigen != null)
				return false;
		} else if (!CiudadOrigen.equals(other.CiudadOrigen))
			return false;
		if (iMinutosViaje != other.iMinutosViaje)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ciudad Origen:" + this.getCiudadOrigen().getSNombreCiudad() + " - Ciudad Destino:" + 
						this.getCiudadDestino().getSNombreCiudad() +" - "+ this.getiMinutosViaje()  + "Minutos";
	}
	
	
	
	
	

}

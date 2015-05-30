package dominio.clases;

public class Viaje {
	
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private int demoraMinutos;
	private Ambulancia ambulancia;
	
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
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
	public int getDemoraMinutos() {
		return demoraMinutos;
	}
	public void setDemoraMinutos(int demoraMinutos) {
		this.demoraMinutos = demoraMinutos;
	}
	
	public Viaje(){
		
	}
	
	public Viaje (Ciudad or, Ciudad des, int min){
		this.ciudadOrigen = or;
		this.ciudadDestino = des;
		this.demoraMinutos = min;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Viaje)) {
			return false;
		}
		Viaje that = (Viaje) other;
		return this.getCiudadDestino().getiCiudadId() == that.getCiudadDestino().getiCiudadId()  ||
				this.getCiudadOrigen().getiCiudadId() == that.getCiudadOrigen().getiCiudadId();
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ciudadDestino == null) ? 0 : ciudadDestino.hashCode());
		result = prime * result
				+ ((ciudadOrigen == null) ? 0 : ciudadOrigen.hashCode());
		result = prime * result + demoraMinutos;
		return result;
	}
	
	@Override
	public String toString() {
		return "CiudadOrigen: " + this.ciudadOrigen + ", ciudadDestino: "
				+ this.ciudadDestino + ", demoraMinutos: " + this.demoraMinutos;
	}
	
	

	
	
}

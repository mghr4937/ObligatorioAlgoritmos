package dominio.clases;

public class Chofer {

	private String sCedula;
	private String sNombre;
	private Ambulancia miAmbulancia;
	
	public String getsNombre() {
		return sNombre;
	}
	public void setsNombre(String sNombre) {
		this.sNombre = sNombre;
	}
	public String getsCedula() {
		return sCedula;
	}
	public void setsCedula(String sCedula) {
		this.sCedula = sCedula;
	}
	public Ambulancia getMiAmbulancia() {
		return miAmbulancia;
	}
	public void setMiAmbulancia(Ambulancia miAmbulancia) {
		this.miAmbulancia = miAmbulancia;
	}
	
	public Chofer(){
		
	}
	
	public Chofer(String cedula, String nombre){
		this.sCedula = cedula;
		this.sNombre = nombre;
	}
	
	public Chofer(String cedula, String nombre, Ambulancia ambulancia){
		this.sCedula = cedula;
		this.sNombre = nombre;
		this.miAmbulancia = ambulancia;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Chofer)) {
			return false;
		}
		Chofer that = (Chofer) other;
		return this.getsCedula().equals(that.getsCedula());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sCedula == null) ? 0 : sCedula.hashCode());
		result = prime * result + ((sNombre == null) ? 0 : sNombre.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Nombre: " + this.sNombre + ", " + "Cédula " + this.sCedula;
	}
		
}

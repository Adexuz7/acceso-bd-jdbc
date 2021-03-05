package aed.db.model;

public class Team {

	private int codEquipo;
	private String nomEquipo;
	private String codLiga;
	private String localidad;
	private Boolean internacional;

	public Team() {
		
	}
	
	public Team(String nomEquipo, String codLiga, String localidad, Boolean internacional) {
		this.codEquipo = 0;		
		this.nomEquipo = nomEquipo;
		this.codLiga = codLiga;
		this.localidad = localidad;
		this.internacional = internacional;
	}

	public Team(int codEquipo, String nomEquipo, String codLiga, String localidad, Boolean internacional) {
		this(nomEquipo, codLiga, localidad, internacional);
		this.codEquipo = codEquipo;
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public String getNomEquipo() {
		return nomEquipo;
	}

	public void setNomEquipo(String nomEquipo) {
		this.nomEquipo = nomEquipo;
	}

	public String getCodLiga() {
		return codLiga;
	}

	public void setCodLiga(String codLiga) {
		this.codLiga = codLiga;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Boolean getInternacional() {
		return internacional;
	}

	public void setInternacional(Boolean internacional) {
		this.internacional = internacional;
	}

	public String toString() {
		return "Código: " + codEquipo + " | " + 
		"Nombre: " + nomEquipo + " | " +
		"Liga: " + codLiga + " | " +
		"Localidad: " + localidad + " | " +
		"Internacional: " + internacional;
	}

}

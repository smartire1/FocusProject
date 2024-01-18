package dipendenti.bean;

public class Turno {
	private int id;
	private String giorno;
	private String oraInizio;
	private String oraFine;

	// Costruttore
	public Turno(int id, String giorno, String oraInizio, String oraFine) {
		this.id = id;
		this.giorno = giorno;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
	}

	// Metodi di accesso (getter e setter)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGiorno() {
		return giorno;
	}

	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}

	public String getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}

	public String getOraFine() {
		return oraFine;
	}

	public void setOraFine(String oraFine) {
		this.oraFine = oraFine;
	}


	@Override
	public String toString() {
		return "Turno{" + "id=" + id + ", giorno=" + giorno + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine
				+ '\'' + '}';
	}
}
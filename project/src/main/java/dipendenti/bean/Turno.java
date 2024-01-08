package dipendenti.bean;

import java.sql.Date;
import java.sql.Time;

public class Turno {
	private int id;
	private Date giorno;
	private Time oraInizio;
	private Time oraFine;
	private String assegnatoAEmail;

	// Costruttore
	public Turno(int id, Date giorno, Time oraInizio, Time oraFine, String assegnatoAEmail) {
		this.id = id;
		this.giorno = giorno;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.assegnatoAEmail = assegnatoAEmail;
	}

	// Metodi di accesso (getter e setter)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGiorno() {
		return giorno;
	}

	public void setGiorno(Date giorno) {
		this.giorno = giorno;
	}

	public Time getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(Time oraInizio) {
		this.oraInizio = oraInizio;
	}

	public Time getOraFine() {
		return oraFine;
	}

	public void setOraFine(Time oraFine) {
		this.oraFine = oraFine;
	}

	public String getAssegnatoAEmail() {
		return assegnatoAEmail;
	}

	public void setAssegnatoAEmail(String assegnatoAEmail) {
		this.assegnatoAEmail = assegnatoAEmail;
	}

	@Override
	public String toString() {
		return "Turno{" + "id=" + id + ", giorno=" + giorno + ", oraInizio=" + oraInizio + ", oraFine=" + oraFine
				+ ", assegnatoAEmail='" + assegnatoAEmail + '\'' + '}';
	}
}
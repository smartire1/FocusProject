package progetto.bean;

import java.util.Date;

public class Progetto {
	private int idProgetto;
	private String nome;
	private String descrizione;
	private String obbiettivi;
	private boolean stato;
	private Date scadenza;
	private double budget;
	private String avvisi;
	private int numDipendenti;
	private String piva;

	// Costruttore
	public Progetto(int idProgetto, String nome, String descrizione, String obbiettivi, boolean stato, Date scadenza,
			double budget, String avvisi, int numDipendenti, String piva) {
		this.idProgetto = idProgetto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.obbiettivi = obbiettivi;
		this.stato = stato;
		this.scadenza = scadenza;
		this.budget = budget;
		this.avvisi = avvisi;
		this.numDipendenti = numDipendenti;
		this.piva = piva;
	}

	// Metodi di accesso (getter e setter)
	public int getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getObbiettivi() {
		return obbiettivi;
	}

	public void setObbiettivi(String obbiettivi) {
		this.obbiettivi = obbiettivi;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getAvvisi() {
		return avvisi;
	}

	public void setAvvisi(String avvisi) {
		this.avvisi = avvisi;
	}

	public int getNumDipendenti() {
		return numDipendenti;
	}

	public void setNumDipendenti(int numDipendenti) {
		this.numDipendenti = numDipendenti;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	@Override
	public String toString() {
		return "Progetto [idProgetto=" + idProgetto + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", obbiettivi=" + obbiettivi + ", stato=" + stato + ", scadenza=" + scadenza + ", budget=" + budget
				+ ", avvisi=" + avvisi + ", numDipendenti=" + numDipendenti + ", piva=" + piva + "]";
	}
}
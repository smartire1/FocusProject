package progetto.bean;


public class Progetto {
	private int idProgetto;
	private String nome;
	private String descrizione;
	private String obbiettivi;
	private boolean stato;
	private String scadenza;
	private double budget;
	private String avvisi;
	private int numDipendenti;
	private String piva;
	private String responsabile_email;

	// Costruttore
	public Progetto(int idProgetto, String nome, String descrizione, String obbiettivi, boolean stato, String scadenza,
			double budget, String avvisi, int numDipendenti, String piva, String responsabile_email) {
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
		this.responsabile_email= responsabile_email;
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

	public boolean getStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
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
	
	public String getResponsabile_email() {
		return responsabile_email;
	}

	public void setResponsabile_email(String responsabile_email) {
		this.responsabile_email = responsabile_email;
	}

	@Override
	public String toString() {
		return "Progetto [idProgetto=" + idProgetto + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", obbiettivi=" + obbiettivi + ", stato=" + stato + ", scadenza=" + scadenza + ", budget=" + budget
				+ ", avvisi=" + avvisi + ", numDipendenti=" + numDipendenti + ", piva=" + piva + ", responsabile_email="
				+ responsabile_email + "]";
	}

	
}
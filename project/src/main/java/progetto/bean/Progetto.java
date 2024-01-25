package progetto.bean;

public class Progetto {
	private int idProgetto;
	private String nome;
	private String descrizione;
	private String obbiettivi;
	private String scadenza;
	private String avvisi;
	private double budget;
	private String responsabile_email;
	private boolean stato;
	private String idAzienda;

	// Costruttore
	public Progetto(int idProgetto, String nome, String descrizione, String obbiettivi, String scadenza, String avvisi,
			double budget, String responsabile_email, boolean stato, String idAzienda) {
		this.idProgetto = idProgetto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.obbiettivi = obbiettivi;
		this.scadenza = scadenza;
		this.avvisi = avvisi;
		this.budget = budget;
		this.responsabile_email = responsabile_email;
		this.stato = stato;
		this.idAzienda = idAzienda;
	}

	// Metodi Getters e Setters
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

	public String getScadenza() {
		return scadenza;
	}

	public void setScadenza(String scadenza) {
		this.scadenza = scadenza;
	}

	public String getAvvisi() {
		return avvisi;
	}

	public void setAvvisi(String avvisi) {
		this.avvisi = avvisi;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getResponsabile_email() {
		return responsabile_email;
	}

	public void setResponsabile_email(String responsabile_email) {
		this.responsabile_email = responsabile_email;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public String getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.idAzienda = idAzienda;
	}

	@Override
	public String toString() {
		return "Progetto [idProgetto=" + idProgetto + ", nome=" + nome + ", descrizione=" + descrizione
				+ ", obbiettivi=" + obbiettivi + ", scadenza=" + scadenza + ", avvisi=" + avvisi + ", budget=" + budget
				+ ", " + ", responsabile_email=" + responsabile_email + ", stato=" + stato
				+ ", idAzienda=" + idAzienda + "]";
	}

}
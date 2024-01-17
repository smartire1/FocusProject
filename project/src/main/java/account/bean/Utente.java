package account.bean;

public class Utente {
	private String email;
	private String password;	
	private String nome;
	private String cognome;
	private String idAzienda;
	private boolean stato;
	private String ruolo;
	
	public Utente(String email, String password, String nome, String cognome, String idAzienda, boolean stato,
			String ruolo) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.idAzienda = idAzienda;
		this.stato = stato;
		this.ruolo = ruolo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(String idAzienda) {
		this.idAzienda = idAzienda;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public boolean isValidInput(String nome, String cognome, String email, String password) {
		if (!isValidName(nome)) {
			System.out.println("Il nome non è valido.");
			return false;
		}

		if (!isValidName(cognome)) {
			System.out.println("Il cognome non è valido.");
			return false;
		}

		if (!isValidEmail(email)) {
			System.out.println("L'email non è valida.");
			return false;
		}

		if (!isValidPassword(password)) {
			System.out.println("La password non è valida.");
			return false;
		}

		return true;
	}

	private boolean isValidName(String name) {
		return name.matches("^[a-zA-Z]+$");
	}

	private boolean isValidEmail(String email) {
		return email.matches("\\S+@\\S+\\.\\S+");
	}

	private boolean isValidPassword(String password) {
		return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.\\-_])[A-Za-z\\d@$!%*?&.\\-_]+$");
	}	

	@Override
	public String toString() {
		return "Utente [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", idAzienda=" + idAzienda + ", stato=" + stato + ", ruolo=" + ruolo + "]";
	}
	
}
package account.bean;

public class Utente {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String piva;

	// Costruttore
	public Utente(String nome, String cognome, String email, String password, String piva) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.piva = piva;
	}

	// Metodi di accesso (getter e setter)
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

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public boolean isValidInput(String nome, String cognome, String email, String password, String piva) {
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

		if (!isValidPiva(piva)) {
			System.out.println("La partita IVA non è valida.");
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

	private boolean isValidPiva(String piva) {
		return piva.matches("^\\d{11}$");
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", password=" + password
				+ ", piva=" + piva + "]";
	}
}
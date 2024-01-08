package account.bean;

public class Dirigente {
	private String email;
	private String nomeAzienda;
	private String piva;

	// Costruttore
	public Dirigente(String email, String nomeAzienda, String piva) {
		this.email = email;
		this.nomeAzienda = nomeAzienda;
		this.piva = piva;
	}

	// Metodi di accesso (getter e setter)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeAzienda() {
		return nomeAzienda;
	}

	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public boolean isValidInput(String piva, String nome_azienda) {
		if (!isValidPiva(piva)) {
			System.out.println("La partita IVA non è valida.");
			return false;
		}

		if (!isValidCompanyName(nome_azienda)) {
			System.out.println("Il nome azienda non è valido.");
			return false;
		}

		return true;
	}

	private boolean isValidPiva(String piva) {
		return piva.matches("^\\d{11}$");
	}

	private boolean isValidCompanyName(String company) {
		return company.matches("^[a-zA-Z0-9]+$");
	}

	@Override
	public String toString() {
		return "Dirigente{" + "email='" + email + '\'' + ", nomeAzienda='" + nomeAzienda + '\'' + ", piva='" + piva
				+ '\'' + '}';
	}
}
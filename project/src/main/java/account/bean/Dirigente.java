package account.bean;

public class Dirigente {
	private String email;
	private String nomeAzienda;

	// Costruttore
	public Dirigente(String email, String nomeAzienda) {
		this.email = email;
		this.nomeAzienda = nomeAzienda;
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

	public boolean isValidInput(String nome_azienda) {
		if (!isValidCompanyName(nome_azienda)) {
			System.out.println("Il nome azienda non è valido.");
			return false;
		}

		return true;
	}

	private boolean isValidCompanyName(String company) {
		return company.matches("^[a-zA-Z0-9]+$");
	}

	@Override
	public String toString() {
		return "Dirigente{" + "email='" + email + '\'' + ", nomeAzienda='" + nomeAzienda + '\'' + '}';
	}
}
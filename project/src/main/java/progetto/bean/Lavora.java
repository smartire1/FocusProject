package progetto.bean;

public class Lavora {
	private String email;
	private int id_progetto;

	public Lavora(String email, int id_progetto) {
		this.email = email;
		this.id_progetto = id_progetto;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_progetto() {
		return id_progetto;
	}

	public void setId_progetto(int id_progetto) {
		this.id_progetto = id_progetto;
	}
	
}

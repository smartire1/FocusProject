package dipendenti.bean;

public class AssegnatoA {
	private int id_turno;
	private String id_utente;
	
	public AssegnatoA(int id_turno, String id_utente) {
		super();
		this.id_turno = id_turno;
		this.id_utente = id_utente;
	}

	public int getId_turno() {
		return id_turno;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}

	public String getId_utente() {
		return id_utente;
	}

	public void setId_utente(String id_utente) {
		this.id_utente = id_utente;
	}
	
	
}

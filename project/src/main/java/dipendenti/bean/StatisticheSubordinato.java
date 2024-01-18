package dipendenti.bean;

public class StatisticheSubordinato {
    private String email;
    private int num_progetti_completati;
    private int num_progetti_in_corso;
    private int num_permessi_richiesti;
    private int num_task_completati;
    private int num_task_in_corso;
    
	public StatisticheSubordinato(String email, int num_progetti_completati, int num_progetti_in_corso,
			int num_permessi_richiesti, int num_task_completati, int num_task_in_corso) {
		super();
		this.email = email;
		this.num_progetti_completati = num_progetti_completati;
		this.num_progetti_in_corso = num_progetti_in_corso;
		this.num_permessi_richiesti = num_permessi_richiesti;
		this.num_task_completati = num_task_completati;
		this.num_task_in_corso = num_task_in_corso;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNum_progetti_completati() {
		return num_progetti_completati;
	}

	public void setNum_progetti_completati(int num_progetti_completati) {
		this.num_progetti_completati = num_progetti_completati;
	}

	public int getNum_progetti_in_corso() {
		return num_progetti_in_corso;
	}

	public void setNum_progetti_in_corso(int num_progetti_in_corso) {
		this.num_progetti_in_corso = num_progetti_in_corso;
	}

	public int getNum_permessi_richiesti() {
		return num_permessi_richiesti;
	}

	public void setNum_permessi_richiesti(int num_permessi_richiesti) {
		this.num_permessi_richiesti = num_permessi_richiesti;
	}

	public int getNum_task_completati() {
		return num_task_completati;
	}

	public void setNum_task_completati(int num_task_completati) {
		this.num_task_completati = num_task_completati;
	}

	public int getNum_task_in_corso() {
		return num_task_in_corso;
	}

	public void setNum_task_in_corso(int num_task_in_corso) {
		this.num_task_in_corso = num_task_in_corso;
	}

	@Override
	public String toString() {
		return "StatisticheSubordinato [email=" + email + ", num_progetti_completati=" + num_progetti_completati
				+ ", num_progetti_in_corso=" + num_progetti_in_corso + ", num_permessi_richiesti="
				+ num_permessi_richiesti + ", num_task_completati=" + num_task_completati + ", num_task_in_corso="
				+ num_task_in_corso + "]";
	}
    
    
}
package dipendenti.bean;

public class StatisticheResponsabile {
    private String email;
    private int num_progetti_completati;
    private int num_progetti_in_corso;
    private int num_permessi_richiesti;
    private int num_subordinati_gestiti;
    //private int num_scadenze_rispettate;
    
	public StatisticheResponsabile(String email, int num_progetti_completati, int num_progetti_in_corso,
								   int num_permessi_richiesti, int num_subordinati_gestiti /*, int num_scadenze_rispettate*/) {
		this.email = email;
		this.num_progetti_completati = num_progetti_completati;
		this.num_progetti_in_corso = num_progetti_in_corso;
		this.num_permessi_richiesti = num_permessi_richiesti;
		this.num_subordinati_gestiti = num_subordinati_gestiti;
		//this.num_scadenze_rispettate = num_scadenze_rispettate;
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

	public int getNum_subordinati_gestiti() {
		return num_subordinati_gestiti;
	}

	public void setNum_subordinati_gestiti(int num_subordinati_gestiti) {
		this.num_subordinati_gestiti = num_subordinati_gestiti;
	}
	
	/* public int getNum_scadenze_rispettate() {
		return num_scadenze_rispettate;
	}

	public void setNum_scadenze_rispettate(int num_scadenze_rispettate) {
		this.num_scadenze_rispettate = num_scadenze_rispettate;
	} */

	@Override
	public String toString() {
		return "StatisticheResponsabile [email=" + email + ", num_progetti_completati=" + num_progetti_completati
				+ ", num_progetti_in_corso=" + num_progetti_in_corso + ", num_permessi_richiesti="
				+ num_permessi_richiesti + ", num_subordinati_gestiti=" + num_subordinati_gestiti
				+ ", num_scadenze_rispettate=" /*+ num_scadenze_rispettate*/ + "]";
	}

}
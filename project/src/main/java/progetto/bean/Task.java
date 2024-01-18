package progetto.bean;

public class Task {
    private int idTask;
    private String descrizione;
    private boolean stato;
    private int idProgetto;
    private String subordinatoEmail;

    // Costruttore
    public Task(int idTask, String descrizione, boolean stato, int idProgetto, String subordinatoEmail) {
        this.idTask = idTask;
        this.descrizione = descrizione;
        this.stato = stato;
        this.idProgetto = idProgetto;
        this.subordinatoEmail = subordinatoEmail;
    }

    // Metodi getter e setter
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public int getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(int idProgetto) {
        this.idProgetto = idProgetto;
    }

    public String getSubordinatoEmail() {
        return subordinatoEmail;
    }

    public void setSubordinatoEmail(String subordinatoEmail) {
        this.subordinatoEmail = subordinatoEmail;
    }

	@Override
	public String toString() {
		return "Task [idTask=" + idTask + ", descrizione=" + descrizione + ", stato=" + stato + ", idProgetto="
				+ idProgetto + ", subordinatoEmail=" + subordinatoEmail + "]";
	}
    
}

package progetto.bean;

public class Task {
    private int idTask;
    private int idProgetto;
    private String descrizione;
    private boolean stato;
    private String assegnatoAEmail;

    // Costruttore
    public Task(int idTask, int idProgetto, String descrizione, boolean stato, String assegnatoAEmail) {
        this.idTask = idTask;
        this.idProgetto = idProgetto;
        this.descrizione = descrizione;
        this.stato = stato;
        this.assegnatoAEmail = assegnatoAEmail;
    }

    // Metodi di accesso (getter e setter)
    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdProgetto() {
        return idProgetto;
    }

    public void setIdProgetto(int idProgetto) {
        this.idProgetto = idProgetto;
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
    
	public String getAssegnatoAEmail() {
		return assegnatoAEmail;
	}

	public void setAssegnatoAEmail(String assegnatoAEmail) {
		this.assegnatoAEmail = assegnatoAEmail;
	}

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", idProgetto=" + idProgetto +
                ", descrizione='" + descrizione + '\'' +
                ", stato=" + stato +
                ", assegnatoAEmail=" + assegnatoAEmail +
                '}';
    }
}
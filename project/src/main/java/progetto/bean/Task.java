package progetto.bean;

public class Task {
    private int idTask;
    private int idProgetto;
    private String descrizione;
    private boolean stato;

    // Costruttore
    public Task(int idTask, int idProgetto, String descrizione, boolean stato) {
        this.idTask = idTask;
        this.idProgetto = idProgetto;
        this.descrizione = descrizione;
        this.stato = stato;
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

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "Task{" +
                "idTask=" + idTask +
                ", idProgetto=" + idProgetto +
                ", descrizione='" + descrizione + '\'' +
                ", stato=" + stato +
                '}';
    }
}
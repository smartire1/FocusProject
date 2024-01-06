package dipendenti.bean;

public class StatistichePersonale {
    private String email;
    private int numProgettiCompletati;
    private int numProgettiInCorso;
    private int numPermessiRichiesti;

    // Costruttore
    public StatistichePersonale(String email, int numProgettiCompletati, int numProgettiInCorso, int numPermessiRichiesti) {
        this.email = email;
        this.numProgettiCompletati = numProgettiCompletati;
        this.numProgettiInCorso = numProgettiInCorso;
        this.numPermessiRichiesti = numPermessiRichiesti;
    }

    // Metodi di accesso (getter e setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumProgettiCompletati() {
        return numProgettiCompletati;
    }

    public void setNumProgettiCompletati(int numProgettiCompletati) {
        this.numProgettiCompletati = numProgettiCompletati;
    }

    public int getNumProgettiInCorso() {
        return numProgettiInCorso;
    }

    public void setNumProgettiInCorso(int numProgettiInCorso) {
        this.numProgettiInCorso = numProgettiInCorso;
    }

    public int getNumPermessiRichiesti() {
        return numPermessiRichiesti;
    }

    public void setNumPermessiRichiesti(int numPermessiRichiesti) {
        this.numPermessiRichiesti = numPermessiRichiesti;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "StatistichePersonale{" +
                "email='" + email + '\'' +
                ", numProgettiCompletati=" + numProgettiCompletati +
                ", numProgettiInCorso=" + numProgettiInCorso +
                ", numPermessiRichiesti=" + numPermessiRichiesti +
                '}';
    }
}
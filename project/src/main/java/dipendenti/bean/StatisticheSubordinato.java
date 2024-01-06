package dipendenti.bean;

public class StatisticheSubordinato {
    private String email;
    private int numTaskCompletati;
    private int numTaskInCorso;

    // Costruttore
    public StatisticheSubordinato(String email, int numTaskCompletati, int numTaskInCorso) {
        this.email = email;
        this.numTaskCompletati = numTaskCompletati;
        this.numTaskInCorso = numTaskInCorso;
    }

    // Metodi di accesso (getter e setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTaskCompletati() {
        return numTaskCompletati;
    }

    public void setNumTaskCompletati(int numTaskCompletati) {
        this.numTaskCompletati = numTaskCompletati;
    }

    public int getNumTaskInCorso() {
        return numTaskInCorso;
    }

    public void setNumTaskInCorso(int numTaskInCorso) {
        this.numTaskInCorso = numTaskInCorso;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "StatisticheSubordinato{" +
                "email='" + email + '\'' +
                ", numTaskCompletati=" + numTaskCompletati +
                ", numTaskInCorso=" + numTaskInCorso +
                '}';
    }
}
package dipendenti.bean;

public class StatisticheResponsabile {
    private String email;
    private int numSubordinatiGestiti;
    private int numScadenzeRispettate;

    // Costruttore
    public StatisticheResponsabile(String email, int numSubordinatiGestiti, int numScadenzeRispettate) {
        this.email = email;
        this.numSubordinatiGestiti = numSubordinatiGestiti;
        this.numScadenzeRispettate = numScadenzeRispettate;
    }

    // Metodi di accesso (getter e setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumSubordinatiGestiti() {
        return numSubordinatiGestiti;
    }

    public void setNumSubordinatiGestiti(int numSubordinatiGestiti) {
        this.numSubordinatiGestiti = numSubordinatiGestiti;
    }

    public int getNumScadenzeRispettate() {
        return numScadenzeRispettate;
    }

    public void setNumScadenzeRispettate(int numScadenzeRispettate) {
        this.numScadenzeRispettate = numScadenzeRispettate;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "StatisticheResponsabile{" +
                "email='" + email + '\'' +
                ", numSubordinatiGestiti=" + numSubordinatiGestiti +
                ", numScadenzeRispettate=" + numScadenzeRispettate +
                '}';
    }
}
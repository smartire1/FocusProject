package account.bean;

public class Responsabile {
    private String email;

    // Costruttore
    public Responsabile(String email) {
        this.email = email;
    }

    // Metodi di accesso (getter e setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "Responsabile{" +
                "email='" + email + '\'' +
                '}';
    }
}
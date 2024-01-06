package account.bean;

public class Subordinato {
    private String email;

    // Costruttore
    public Subordinato(String email) {
        this.email = email;
    }

    // Metodi di accesso (getter e setter)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Subordinato{" +
                "email='" + email + '\'' +
                '}';
    }
}
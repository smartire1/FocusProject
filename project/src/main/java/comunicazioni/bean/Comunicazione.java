package comunicazioni.bean;

public class Comunicazione {
    private int id;
    private String titolo;
    private String corpo;
    private String mittenteEmail;

    // Costruttore
    public Comunicazione(int id, String titolo, String corpo, String mittenteEmail) {
        this.id = id;
        this.titolo = titolo;
        this.corpo = corpo;
        this.mittenteEmail = mittenteEmail;
    }

    // Metodi di accesso (getter e setter)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getMittenteEmail() {
        return mittenteEmail;
    }

    public void setMittenteEmail(String mittenteEmail) {
        this.mittenteEmail = mittenteEmail;
    }

    @Override
    public String toString() {
        return "Comunicazione{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", corpo='" + corpo + '\'' +
                ", mittenteEmail='" + mittenteEmail + '\'' +
                '}';
    }
}
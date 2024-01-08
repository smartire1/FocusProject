package comunicazioni.bean;

import java.util.Date;

public class Permesso {
    private int id;
    private String giorno;
    private String motivo;
    private boolean stato;
    private String richiedenteEmail;

    // Costruttore
    public Permesso(int id, String giorno, String motivo, boolean stato, String richiedenteEmail) {
        this.id = id;
        this.giorno = giorno;
        this.motivo = motivo;
        this.stato = stato;
        this.richiedenteEmail = richiedenteEmail;
    }

    // Metodi di accesso (getter e setter)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public String getRichiedenteEmail() {
        return richiedenteEmail;
    }

    public void setRichiedenteEmail(String richiedenteEmail) {
        this.richiedenteEmail = richiedenteEmail;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "Permesso{" +
                "id=" + id +
                ", giorno=" + giorno +
                ", motivo='" + motivo + '\'' +
                ", stato=" + stato +
                ", richiedenteEmail='" + richiedenteEmail + '\'' +
                '}';
    }
}
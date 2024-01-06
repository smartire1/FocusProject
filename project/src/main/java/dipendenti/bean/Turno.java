package dipendenti.bean;

import java.sql.Date;
import java.sql.Time;

public class Turno {
    private int id;
    private Date giorno;
    private Time oraInizio;
    private Time oraFine;
    private String responsabileEmail;
    private String subordinatoEmail;

    // Costruttore
    public Turno(int id, Date giorno, Time oraInizio, Time oraFine, String responsabileEmail, String subordinatoEmail) {
        this.id = id;
        this.giorno = giorno;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.responsabileEmail = responsabileEmail;
        this.subordinatoEmail = subordinatoEmail;
    }

    // Metodi di accesso (getter e setter)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getGiorno() {
        return giorno;
    }

    public void setGiorno(Date giorno) {
        this.giorno = giorno;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public String getResponsabileEmail() {
        return responsabileEmail;
    }

    public void setResponsabileEmail(String responsabileEmail) {
        this.responsabileEmail = responsabileEmail;
    }

    public String getSubordinatoEmail() {
        return subordinatoEmail;
    }

    public void setSubordinatoEmail(String subordinatoEmail) {
        this.subordinatoEmail = subordinatoEmail;
    }

    // Altri metodi se necessari

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", giorno=" + giorno +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", responsabileEmail='" + responsabileEmail + '\'' +
                ", subordinatoEmail='" + subordinatoEmail + '\'' +
                '}';
    }
}
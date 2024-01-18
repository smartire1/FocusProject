package comunicazioni.bean;

public class Permesso {
    private int id;
    private String dalGiorno;
    private String alGiorno;
    private String motivo;
    private boolean stato;
    private String richiedenteEmail;

    public Permesso(int id, String dalGiorno, String alGiorno, String motivo, boolean stato, String richiedenteEmail) {
        this.id = id;
        this.dalGiorno = dalGiorno;
        this.alGiorno = alGiorno;
        this.motivo = motivo;
        this.stato = stato;
        this.richiedenteEmail = richiedenteEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDalGiorno() {
        return dalGiorno;
    }

    public void setDalGiorno(String dalGiorno) {
        this.dalGiorno = dalGiorno;
    }

    public String getAlGiorno() {
        return alGiorno;
    }

    public void setAlGiorno(String alGiorno) {
        this.alGiorno = alGiorno;
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

	@Override
	public String toString() {
		return "Permesso [id=" + id + ", dalGiorno=" + dalGiorno + ", alGiorno=" + alGiorno + ", motivo=" + motivo
				+ ", stato=" + stato + ", richiedenteEmail=" + richiedenteEmail + "]";
	}
    
}

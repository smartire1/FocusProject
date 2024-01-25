package account.bean;

public class Azienda {
	private String piva;
	private String nome;
	
	public Azienda(String piva, String nome) {
		this.piva = piva;
		this.nome = nome;
	}
	public String getPiva() {
		return piva;
	}
	public void setPiva(String piva) {
		this.piva = piva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	boolean isValidPiva(String piva) {
		return piva.matches("^\\d{11}$");
	}
	
	boolean isValidCompanyName(String name) {
		return name.matches("^[a-zA-Z0-9]+$");
	}
	
	public boolean isValidInput(String piva, String name) {
		if(!isValidPiva(piva)) {
			System.out.println("partita iva non valida");
			return false;
		}
		if(!isValidCompanyName(name)) {
			System.out.println("nome azienda non valido");
			return false;
		}	
		return true;
	}
	@Override
	public String toString() {
		return "Azienda [piva=" + piva + ", nome=" + nome + "]";
	}	
}

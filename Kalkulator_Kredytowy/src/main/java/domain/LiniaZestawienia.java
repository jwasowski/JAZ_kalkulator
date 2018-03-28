package domain;

public class LiniaZestawienia {
	private int licznikRat;
	private String kwotaKapitalu;
	private String kwotaOdsetek;
	private String oplataStala;
	private String calkowitaRata;

	public int getLicznikRat() {
		return licznikRat;
	}

	public void setLicznikRat(int licznikRat) {
		this.licznikRat = licznikRat;
	}

	public String getKwotaKapitalu() {
		return kwotaKapitalu;
	}

	public void setKwotaKapitalu(String kwotaKapitalu) {
		this.kwotaKapitalu = kwotaKapitalu;
	}

	public String getKwotaOdsetek() {
		return kwotaOdsetek;
	}

	public void setKwotaOdsetek(String kwotaOdsetek) {
		this.kwotaOdsetek = kwotaOdsetek;
	}

	public String getOplataStala() {
		return oplataStala;
	}

	public void setOplataStala(String oplataStala) {
		this.oplataStala = oplataStala;
	}

	public String getCalkowitaRata() {
		return calkowitaRata;
	}

	public void setCalkowitaRata(String calkowitaRata) {
		this.calkowitaRata = calkowitaRata;
	}

	public int getIloscPol() {

		return getClass().getDeclaredFields().length;
	}

}

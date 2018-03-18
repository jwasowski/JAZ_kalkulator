package servlets;

public class Kalkulator {

	public static String[][] stalaRata(String kwotaKredytu, String iloscRat, String oprocentowanie,
			String oplataStala) {
		double kwotaKredytuKonw = Double.parseDouble(kwotaKredytu);
		int iloscRatKonw = Integer.parseInt(iloscRat), iloscMiesiecy = 12, licznikRat = 1, szerTablicy = 5, i, j,
				wykladnikRat = 0, jeden = 1, sto = 100;
		double oprocentowanieKonw = Double.parseDouble(oprocentowanie);
		double oplataStalaKonw = Double.parseDouble(oplataStala);
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0;
		String[][] tabela = new String[iloscRatKonw][szerTablicy];
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		oprocentowanieKonw = oprocentowanieKonw / sto;
		p = oprocentowanieKonw / iloscMiesiecy;
		wykladnikRat = iloscRatKonw;
		for (i = 0; i < iloscRatKonw; i++) {
			kwotaOdsetek = kwotaKredytuKonw * p;
			calkowitaRata = kwotaKredytuKonw
					* (p * Math.pow((jeden + p), wykladnikRat) / (Math.pow(jeden + p, wykladnikRat) - jeden));
			kwotaKapitalu = calkowitaRata - kwotaOdsetek;
			calkowitaRata = calkowitaRata + oplataStalaKonw;
			kwotaKredytuKonw = kwotaKredytuKonw - kwotaKapitalu;
			wykladnikRat--;
			for (j = 0; j < szerTablicy; j++) {
				switch (j) {
				case 0:
					tabela[i][j] = String.valueOf(licznikRat);
					break;
				case 1:
					tabela[i][j] = df.format(kwotaKapitalu);
					break;
				case 2:
					tabela[i][j] = df.format(kwotaOdsetek);
					break;
				case 3:
					tabela[i][j] = df.format(oplataStalaKonw);
					break;
				case 4:
					tabela[i][j] = df.format(calkowitaRata);
					break;
				}
			}
			licznikRat++;
		}
		return tabela;
	}

	public static String[][] malejacaRata(String kwotaKredytu, String iloscRat, String oprocentowanie,
			String oplataStala) {
		double kwotaKredytuKonw = Double.parseDouble(kwotaKredytu);
		int iloscRatKonw = Integer.parseInt(iloscRat), iloscMiesiecy = 12, licznikRat = 1, szerTablicy = 5, i, j,
				sto = 100;
		double oprocentowanieKonw = Double.parseDouble(oprocentowanie);
		double oplataStalaKonw = Double.parseDouble(oplataStala);
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0;
		String[][] tabela = new String[iloscRatKonw][szerTablicy];
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		oprocentowanieKonw = oprocentowanieKonw / sto;
		p = oprocentowanieKonw / iloscMiesiecy;
		kwotaKapitalu = kwotaKredytuKonw / iloscRatKonw;
		for (i = 0; i < iloscRatKonw; i++) {
			kwotaOdsetek = kwotaKredytuKonw * p;
			calkowitaRata = kwotaKapitalu + kwotaOdsetek + oplataStalaKonw;
			kwotaKredytuKonw = kwotaKredytuKonw - kwotaKapitalu;
			for (j = 0; j < szerTablicy; j++) {
				switch (j) {
				case 0:
					tabela[i][j] = String.valueOf(licznikRat);
					break;
				case 1:
					tabela[i][j] = df.format(kwotaKapitalu);
					break;
				case 2:
					tabela[i][j] = df.format(kwotaOdsetek);
					break;
				case 3:
					tabela[i][j] = df.format(oplataStalaKonw);
					break;
				case 4:
					tabela[i][j] = df.format(calkowitaRata);
					break;
				}
			}
			licznikRat++;
		}

		return tabela;
	}

	public static String drukTabeli(String[][] tabela, String kwotaKredytu, String iloscRat, String oprocentowanie,
			String oplataStala, String rodzajRat) { 
		String zwrot = "";
		int wys = tabela.length, i, szer = 0, j;
		zwrot = zwrot
				.concat("<html><head><style>table, th, td {border: 1px solid black;}</style></head><body>Kwota kredytu: "
						+ kwotaKredytu + " zl<br>Ilosc rat: " + iloscRat + "</br>Oprocentowanie: " 
						+ oprocentowanie + "%<br>Oplata stala: " + oplataStala + " zl</br> Rodzaj rat: " 
						+ rodzajRat	+ "<table><tr><th>Nr Raty</th><th>Kwota Kapitalowa</th><th>Kwota Odsetki</th><th>Oplaty Stale</th><th>Calkowita Rata</th></tr>");
		for (i = 0; i < wys; i++) {
			szer = tabela[i].length;
			zwrot = zwrot.concat("<tr>");
			for (j = 0; j < szer; j++) {
				zwrot = zwrot.concat("<td>" + tabela[i][j] + "</td>");

			}
			zwrot = zwrot.concat("</tr>");
		}
		zwrot = zwrot.concat("</table></body></html>");
		return zwrot;
	}
}

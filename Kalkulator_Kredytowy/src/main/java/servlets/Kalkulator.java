package servlets;

import domain.DaneWprowadzone;

public class Kalkulator {
	public static DaneWprowadzone ParsowanieDanych(String kwotaKredytu, String iloscRat, String oprocentowanie,
			String oplataStala, String rodzajRat) {
		DaneWprowadzone dane = new DaneWprowadzone();
		dane.setKwotaKredytu(Double.parseDouble(kwotaKredytu));
		dane.setIloscRat(Integer.parseInt(iloscRat));
		dane.setOprocentowanie(Double.parseDouble(oprocentowanie));
		dane.setOplataStala(Double.parseDouble(oplataStala));
		dane.setRodzajRat(rodzajRat);
		return dane;
	}

	// Metoda tworzenia szeregów danych dla raty stalej (oblicza poszczegolne
	// wyniki i tworzy odpowiednia strukture tabeli)
	public static String[][] stalaRata(DaneWprowadzone dane) {
		int iloscMiesiecy = 12, licznikRat = 1, szerTablicy = 5, i, j, wykladnikRat = 0, jeden = 1, sto = 100;
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0, kwotaKredytuRob = dane.getKwotaKredytu(),
				oprocentowanieRob = dane.getOprocentowanie() / sto;
		String[][] tabela = new String[dane.getIloscRat()][szerTablicy];
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		p = oprocentowanieRob / iloscMiesiecy;
		wykladnikRat = dane.getIloscRat();

		for (i = 0; i < dane.getIloscRat(); i++) {
			kwotaOdsetek = kwotaKredytuRob * p;
			calkowitaRata = kwotaKredytuRob
					* (p * Math.pow((jeden + p), wykladnikRat) / (Math.pow(jeden + p, wykladnikRat) - jeden));
			kwotaKapitalu = calkowitaRata - kwotaOdsetek;
			calkowitaRata = calkowitaRata + dane.getOplataStala();
			kwotaKredytuRob = kwotaKredytuRob - kwotaKapitalu;
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
					tabela[i][j] = df.format(dane.getOplataStala());
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

	// Metoda tworzenia szeregów danych dla raty malejacej (oblicza poszczegolne
	// wyniki i tworzy odpowiednia strukture tabeli)
	public static String[][] malejacaRata(DaneWprowadzone dane) {
		int iloscMiesiecy = 12, licznikRat = 1, szerTablicy = 5, i, j, sto = 100;
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0, kwotaKredytuRob = dane.getKwotaKredytu(), oprocentowanieRob = dane.getOprocentowanie() / sto;
		String[][] tabela = new String[dane.getIloscRat()][szerTablicy];

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		p = oprocentowanieRob / iloscMiesiecy;
		kwotaKapitalu = kwotaKredytuRob / dane.getIloscRat();
		for (i = 0; i < dane.getIloscRat(); i++) {
			kwotaOdsetek = kwotaKredytuRob * p;
			calkowitaRata = kwotaKapitalu + kwotaOdsetek + dane.getOplataStala();
			kwotaKredytuRob = kwotaKredytuRob - kwotaKapitalu;
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
					tabela[i][j] = df.format(dane.getOplataStala());
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

	// Metoda przetwarzajaca tabele jako kod HTML
	public static String drukTabeli(String[][] tabela, DaneWprowadzone dane) {
		String zwrot = "";
		int wys = tabela.length, i, szer = 0, j;
		zwrot = zwrot
				.concat("<html><head><style>table, th, td {border: 1px solid black;}</style></head><body>Kwota kredytu: "
						+ dane.getKwotaKredytu() + " zl<br>Ilosc rat: " + dane.getIloscRat() + "</br>Oprocentowanie: "
						+ dane.getOprocentowanie() + " %<br>Oplata stala: " + dane.getOplataStala()
						+ " zl</br> Rodzaj rat: " + dane.getRodzajRat()
						+ "<table><tr><th>Nr Raty</th><th>Kwota Kapitalowa</th><th>Kwota Odsetki</th><th>Oplaty Stale</th><th>Calkowita Rata</th></tr>");
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

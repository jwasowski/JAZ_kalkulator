package servlets;

import java.text.DecimalFormat;
import java.util.List;

import domain.DaneWprowadzone;
import domain.LiniaZestawienia;

//W tej klasie znajduje sie logika tworzenia zestawien kredytowych
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
	// wyniki i tworzy odpowiedniobiekt)
	public static List<LiniaZestawienia> stalaRata(DaneWprowadzone dane, List<LiniaZestawienia> lista) {
		int iloscMiesiecy = 12, licznikRat = 1, i, wykladnikRat = 0, jeden = 1, sto = 100;
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0, kwotaKredytuRob = dane.getKwotaKredytu(),
				oprocentowanieRob = dane.getOprocentowanie() / sto, oplataStala = dane.getOplataStala(),
				iloscRat = dane.getIloscRat();
		DecimalFormat df = new DecimalFormat("#.00");
		p = oprocentowanieRob / iloscMiesiecy;
		wykladnikRat = dane.getIloscRat();
		for (i = 0; i < iloscRat; i++) {
			kwotaOdsetek = kwotaKredytuRob * p;
			calkowitaRata = kwotaKredytuRob
					* (p * Math.pow((jeden + p), wykladnikRat) / (Math.pow(jeden + p, wykladnikRat) - jeden));
			kwotaKapitalu = calkowitaRata - kwotaOdsetek;
			calkowitaRata = calkowitaRata + oplataStala;
			kwotaKredytuRob = kwotaKredytuRob - kwotaKapitalu;
			wykladnikRat--;
			LiniaZestawienia linia = new LiniaZestawienia();
			linia.setLicznikRat(licznikRat);
			linia.setKwotaKapitalu(df.format(kwotaKapitalu));
			linia.setKwotaOdsetek(df.format(kwotaOdsetek));
			linia.setOplataStala(df.format(oplataStala));
			linia.setCalkowitaRata(df.format(calkowitaRata));
			lista.add(linia);
			licznikRat++;
		}
		return lista;
	}

	// Metoda tworzenia szeregów danych dla raty malejacej (oblicza poszczegolne
	// wyniki i tworzy odpowiedni obiekt)
	public static List<LiniaZestawienia> malejacaRata(DaneWprowadzone dane, List<LiniaZestawienia> lista) {
		int iloscMiesiecy = 12, licznikRat = 1, i, sto = 100;
		double kwotaKapitalu = 0, kwotaOdsetek = 0, calkowitaRata = 0, p = 0, kwotaKredytuRob = dane.getKwotaKredytu(),
				oprocentowanieRob = dane.getOprocentowanie() / sto, oplataStala = dane.getOplataStala(),
				iloscRat = dane.getIloscRat();

		DecimalFormat df = new DecimalFormat("#.00");
		p = oprocentowanieRob / iloscMiesiecy;
		kwotaKapitalu = kwotaKredytuRob / dane.getIloscRat();
		for (i = 0; i < iloscRat; i++) {
			kwotaOdsetek = kwotaKredytuRob * p;
			calkowitaRata = kwotaKapitalu + kwotaOdsetek + oplataStala;
			kwotaKredytuRob = kwotaKredytuRob - kwotaKapitalu;
			LiniaZestawienia linia = new LiniaZestawienia();
			linia.setLicznikRat(licznikRat);
			linia.setKwotaKapitalu(df.format(kwotaKapitalu));
			linia.setKwotaOdsetek(df.format(kwotaOdsetek));
			linia.setOplataStala(df.format(oplataStala));
			linia.setCalkowitaRata(df.format(calkowitaRata));
			lista.add(linia);
			licznikRat++;
		}
		return lista;
	}

	// Metoda przetwarzajaca liste jako kod HTML
	public static String drukTabeliObiektowy(List<LiniaZestawienia> lista, DaneWprowadzone dane) {
		String zwrot = "";
		int iloscWpisow = lista.size(), i, j, szer;
		zwrot = zwrot
				.concat("<html><head><style>table, th, td {border: 1px solid black;}</style></head><body>Kwota kredytu: "
						+ dane.getKwotaKredytu() + " zl<br>Ilosc rat: " + dane.getIloscRat() + "</br>Oprocentowanie: "
						+ dane.getOprocentowanie() + " %<br>Oplata stala: " + dane.getOplataStala()
						+ " zl</br> Rodzaj rat: " + dane.getRodzajRat()
						+ "<table><tr><th>Nr Raty</th><th>Kwota Kapitalowa</th><th>Kwota Odsetki</th><th>Oplaty Stale</th><th>Calkowita Rata</th></tr>");
		for (i = 0; i < iloscWpisow; i++) {
			szer = lista.get(i).getIloscPol();
			zwrot = zwrot.concat("<tr>");
			for (j = 0; j < szer; j++) {
				switch (j) {
				case 0:
					zwrot = zwrot.concat("<td>" + lista.get(i).getLicznikRat() + "</td>");
					break;
				case 1:
					zwrot = zwrot.concat("<td>" + lista.get(i).getKwotaKapitalu() + "</td>");
					break;
				case 2:
					zwrot = zwrot.concat("<td>" + lista.get(i).getKwotaOdsetek() + "</td>");
					break;
				case 3:
					zwrot = zwrot.concat("<td>" + lista.get(i).getOplataStala() + "</td>");
					break;
				case 4:
					zwrot = zwrot.concat("<td>" + lista.get(i).getCalkowitaRata() + "</td>");
					break;
				}
			}
			zwrot = zwrot.concat("</tr>");
		}
		zwrot = zwrot.concat("</table></body></html>");
		return zwrot;
	}
}

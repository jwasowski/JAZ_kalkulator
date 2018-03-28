package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DaneWprowadzone;
import domain.LiniaZestawienia;

@WebServlet("/kalkulator")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// G³ówna klasa servletu przetwarzajaca formularz (przetworzy metode post i
	// get)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		DaneWprowadzone dane = new DaneWprowadzone();
		List<LiniaZestawienia> lista = new ArrayList<LiniaZestawienia>();
		// Sprawdzenie czy pola formularza nie sa puste
		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}
		dane = Kalkulator.ParsowanieDanych(kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat);
		response.setContentType("text/html");

		if (dane.getRodzajRat().equals("stala")) {
			// Obliczenie raty
			lista = Kalkulator.stalaRata(dane, lista);
			// Tworzenie Stringa z elementami html
			response.getWriter().print(Kalkulator.drukTabeliObiektowy(lista, dane));

		} else {
			// Obliczenie raty
			lista = Kalkulator.malejacaRata(dane, lista);
			// Tworzenie Stringa z elementami html
			response.getWriter().print(Kalkulator.drukTabeliObiektowy(lista, dane));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		DaneWprowadzone dane = new DaneWprowadzone();
		List<LiniaZestawienia> lista = new ArrayList<LiniaZestawienia>();
		// Sprawdzenie czy pola formularza nie sa puste
		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}
		dane = Kalkulator.ParsowanieDanych(kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat);
		response.setContentType("text/html");

		if (dane.getRodzajRat().equals("stala")) {
			// Obliczenie raty
			lista = Kalkulator.stalaRata(dane, lista);
			// Tworzenie Stringa z elementami html
			response.getWriter().print(Kalkulator.drukTabeliObiektowy(lista, dane));

		} else {
			// Obliczenie raty
			lista = Kalkulator.malejacaRata(dane, lista);
			// Tworzenie Stringa z elementami html
			response.getWriter().print(Kalkulator.drukTabeliObiektowy(lista, dane));

		}
	}

}

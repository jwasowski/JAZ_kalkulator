package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DaneWprowadzone;

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
		String[][] tabela;
		DaneWprowadzone dane = new DaneWprowadzone();
		// Sprawdzenie czy pola formularza nie sa puste
		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}
		dane = Kalkulator.ParsowanieDanych(kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat);
		response.setContentType("text/html");

		if (dane.getRodzajRat().equals("stala")) {
			tabela = Kalkulator.stalaRata(dane); // Obliczenie
													// raty
			response.getWriter().print(Kalkulator.drukTabeli(tabela, dane)); // Tworzenie
																				// String'a
																				// zawierajacego
																				// kod
																				// html

		} else {
			tabela = Kalkulator.malejacaRata(dane); // Obliczenie
													// raty
			response.getWriter().print(Kalkulator.drukTabeli(tabela, dane)); // Tworzenie
																				// String'a
																				// zawierajacego
																				// kod
																				// html

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String[][] tabela;
		DaneWprowadzone dane = new DaneWprowadzone();
		// Sprawdzenie czy pola formularza nie sa puste
		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}
		dane = Kalkulator.ParsowanieDanych(kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat);
		response.setContentType("text/html");

		if (dane.getRodzajRat().equals("stala")) {
			tabela = Kalkulator.stalaRata(dane); // Obliczenie
													// raty
			response.getWriter().print(Kalkulator.drukTabeli(tabela, dane)); // Tworzenie
																				// String'a
																				// zawierajacego
																				// kod
																				// html

		} else {
			tabela = Kalkulator.malejacaRata(dane); // Obliczenie
													// raty
			response.getWriter().print(Kalkulator.drukTabeli(tabela, dane)); // Tworzenie
																				// String'a
																				// zawierajacego
																				// kod
																				// html

		}
	}

}

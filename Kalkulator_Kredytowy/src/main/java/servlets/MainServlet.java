package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kalkulator")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String[][] tabela;

		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}
		response.setContentType("text/html");

		if (rodzajRat.equals("stala")) {
			tabela = Kalkulator.stalaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			response.getWriter().print(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));

		} else {
			tabela = Kalkulator.malejacaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			response.getWriter().print(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String[][] tabela;

		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}

		response.setContentType("text/html");

		if (rodzajRat.equals("stala")) {
			tabela = Kalkulator.stalaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			response.getWriter().print(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));

		} else {
			tabela = Kalkulator.malejacaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			response.getWriter().print(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));

		}
	}

}

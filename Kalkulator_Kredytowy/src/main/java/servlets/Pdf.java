package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import domain.DaneWprowadzone;
import domain.LiniaZestawienia;

@WebServlet("/pdf")
public class Pdf extends MainServlet {
	private static final long serialVersionUID = 1L;

	// Klasa przetwarzajaca servletu dla tworzenia pdf'u (przetwarza metode post
	// i get)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String listaXML = "";
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

		if (rodzajRat.equals("stala")) {
			// Obliczenie raty
			lista = Kalkulator.stalaRata(dane, lista); 
			// Tworzenie Stringa z elementami html
			listaXML = listaXML.concat(Kalkulator.drukTabeliObiektowy(lista, dane)); 
			// Tworzenie dokumentu PDF
			budujPDF(request, response, listaXML); 

		} else {
			// Obliczenie raty
			lista = Kalkulator.malejacaRata(dane, lista); 
			// Tworzenie Stringa z elementami html
			listaXML = listaXML.concat(Kalkulator.drukTabeliObiektowy(lista, dane)); 
			// Tworzenie dokumentu PDF
			budujPDF(request, response, listaXML); 
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String listaXML = "";
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

		if (rodzajRat.equals("stala")) {
			// Obliczenie raty
			lista = Kalkulator.stalaRata(dane, lista);

			listaXML = listaXML.concat(
					// Tworzenie Stringa z elementami html
					Kalkulator.drukTabeliObiektowy(lista, dane));
			// Tworzenie dokumentu PDF
			budujPDF(request, response, listaXML);

		} else {
			// Obliczenie raty
			lista = Kalkulator.malejacaRata(dane, lista);
			// Tworzenie Stringa z elementami html
			listaXML = listaXML.concat(Kalkulator.drukTabeliObiektowy(lista, dane));
			// Tworzenie dokumentu PDF
			budujPDF(request, response, listaXML);
		}

	}

	// Metoda tworzaca dokument PDF
	protected void budujPDF(HttpServletRequest request, HttpServletResponse response, String tabelaXML)
			throws ServletException, IOException {
		// Dokument tworzymy i transmitujemy jako odpowiedz servletu, do
		// przetworzenia korzystamy z XMLworkerhelpera (przetwarza kod html/xml
		// dla pdf)
		response.setContentType("application/pdf");
		OutputStream wyjscie = response.getOutputStream();
		try {
			Document dokument = new Document(PageSize.A4);
			PdfWriter pdfWriter = PdfWriter.getInstance(dokument, wyjscie);
			dokument.open();
			dokument.addCreationDate();
			dokument.addTitle("Harmonogram sp�aty");
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			worker.parseXHtml(pdfWriter, dokument, new StringReader(tabelaXML));
			dokument.close();
		} catch (DocumentException wyjatek) {
			throw new IOException(wyjatek.getMessage());
		} finally {
			wyjscie.close();
		}
	}
}

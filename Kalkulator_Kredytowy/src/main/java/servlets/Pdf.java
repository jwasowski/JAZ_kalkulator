package servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@WebServlet("/pdf")
public class Pdf extends MainServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String[][] tabela;
		String tabelaXML = "";

		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}

		response.setContentType("text/html");

		if (rodzajRat.equals("stala")) {
			tabela = Kalkulator.stalaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			tabelaXML = tabelaXML.concat(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));
			budujPDF(request, response, tabelaXML);

		} else {
			tabela = Kalkulator.malejacaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			tabelaXML = tabelaXML.concat(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));
			budujPDF(request, response, tabelaXML);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String kwotaKredytu = request.getParameter("kwotakredytu");
		String iloscRat = request.getParameter("iloscrat");
		String oprocentowanie = request.getParameter("oprocentowanie");
		String oplataStala = request.getParameter("oplatastala");
		String rodzajRat = request.getParameter("rodzajrat");
		String[][] tabela;
		String tabelaXML = "";

		if (kwotaKredytu == null || kwotaKredytu.equals("") || iloscRat == null || iloscRat.equals("")
				|| oprocentowanie == null || oprocentowanie.equals("") || oplataStala == null
				|| oplataStala.equals("")) {
			response.sendRedirect("/");
		}

		response.setContentType("text/html");

		if (rodzajRat.equals("stala")) {
			tabela = Kalkulator.stalaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			tabelaXML = tabelaXML.concat(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));
			budujPDF(request, response, tabelaXML);

		} else {
			tabela = Kalkulator.malejacaRata(kwotaKredytu, iloscRat, oprocentowanie, oplataStala);
			tabelaXML = tabelaXML.concat(
					Kalkulator.drukTabeli(tabela, kwotaKredytu, iloscRat, oprocentowanie, oplataStala, rodzajRat));
			budujPDF(request, response, tabelaXML);
		}

	}

	protected void budujPDF(HttpServletRequest request, HttpServletResponse response, String tabelaXML)
			throws ServletException, IOException {

		response.setContentType("application/pdf");
		OutputStream wyjscie = response.getOutputStream();
		try {
			Document dokument = new Document(PageSize.A4);
			PdfWriter pdfWriter = PdfWriter.getInstance(dokument, wyjscie);
			dokument.open();
			dokument.addCreationDate();
			dokument.addTitle("Harmonogram sp³aty");
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

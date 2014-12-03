package opp.parica.megafon.web.servleti;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.Slika;
import opp.parica.megafon.web.servlets.forme.OglasForma;

@WebServlet("/servleti/dodajOglas")
@MultipartConfig
public class DodajOglasServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("user") == null || req.getSession().getAttribute("admin") != null
			|| "Odustani".equals(req.getParameter("metoda"))) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		if (req.getParameter("kategorija") == null) {
			Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("user");
			List<Kategorija> kategorije = new ArrayList<Kategorija>();

			if (oglasivac.getTipClanstva().getNaziv().equals("Besplatni")) {
				for (Kategorija k : DAOProvider.getDAO().dohvatiSveKategorije()) {
					if (k.getJeBesplatna()) {
						kategorije.add(k);
					}
				}
			} else {
				kategorije = DAOProvider.getDAO().dohvatiSveKategorije();
			}
			req.setAttribute("kategorije", kategorije);
			req.getRequestDispatcher("/WEB-INF/pages/OdabirKategorije.jsp").forward(req, resp);
			return;
		}
		Kategorija kat;
		try {
			long idKategorije = Long.parseLong(req.getParameter("kategorija"));
			kat = DAOProvider.getDAO().dohvatiKategoriju(idKategorije);
			if (kat == null) {
				throw new NullPointerException();
			}
		} catch (Exception ex) {
			req.setAttribute("msg", "Odabrana nepostojeća kategorija");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		System.out.println("Odabrana kategorija " + kat);
		OglasForma forma = new OglasForma();
		forma.setKategorijaID(kat.getId());
		req.setAttribute("zapis", forma);
		String nazivKategorije = kat.getNaziv();
		if (kat.getNadkategorija() != null) {
			nazivKategorije = kat.getNadkategorija().getNaziv() + " - " + nazivKategorije;
		}
		req.setAttribute("nazivKategorije", nazivKategorije);
		req.getRequestDispatcher("/WEB-INF/pages/StvoriOglas.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		OglasForma forma = new OglasForma();
		forma.fillFromHttpRequest(req);
		forma.validate();

		if (!forma.hasError()) {

			Oglas oglas = new Oglas();
			forma.fillToObject(oglas);
			oglas.setDatumObjave(new Date());
			oglas.setJeSkrivern(false);
			oglas.setAutor((Oglasivac) req.getSession().getAttribute("user"));

			DAOProvider.getDAO().dodajOglas(oglas);
			System.out.println("ID je" + oglas.getId());

			uploadajSlike(req, oglas, forma);
			if (!forma.hasError()) {
				forma.spremiStavke(oglas);
				String msg = "Oglas " + oglas.getNaslov() + " dodan";

				req.setAttribute("msg", msg);
				req.setAttribute("title", "Uspješno");

				req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
				return;
			}
		}
		req.setAttribute("nazivKategorije", DAOProvider.getDAO().dohvatiKategoriju(forma.getKategorijaID())
			.getNaziv());
		req.setAttribute("zapis", forma);
		req.getRequestDispatcher("/WEB-INF/pages/StvoriOglas.jsp").forward(req,
			resp);

	}

	private void uploadajSlike(final HttpServletRequest req, final Oglas o, final OglasForma forma)
		throws IOException, ServletException {
		String applicationPath = req.getServletContext().getRealPath("");
		String uploadFilePath = applicationPath + File.separator + "tmp";
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());

		String fileName = null;
		for (Part part : req.getParts()) {
			fileName = getFileName(part, forma);
			if (fileName.isEmpty()) {
				continue;
			}
			part.write(uploadFilePath + File.separator + fileName);
			String ekstenzija = fileName.substring(fileName.lastIndexOf('.') + 1);
			Slika s = new Slika();
			s.setSlika(Files.readAllBytes(Paths.get(uploadFilePath + File.separator + fileName)));
			s.setPripadaOglasu(o);
			s.setEkstenzija(ekstenzija);
			DAOProvider.getDAO().dodajSliku(s);

			//File f = new File(uploadFilePath + File.separator + fileName);
			//f.delete();
		}
	}

	private String getFileName(final Part part, final OglasForma forma) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");
		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
				if (fileName.matches("(?i).*[.](png|jpg|bmp)")) {
					return fileName;
				} else {
					forma.getErrors().put("slike", "Podrzani formati slika su png, jpg i bmp");
				}
			}
		}
		return "";
	}

}

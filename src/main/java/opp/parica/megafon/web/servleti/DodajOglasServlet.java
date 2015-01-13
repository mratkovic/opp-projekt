package opp.parica.megafon.web.servleti;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import opp.parica.megafon.web.servleti.forme.OglasForma;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet({ "/servleti/dodajOglas" })
@MultipartConfig
public class DodajOglasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if ((req.getSession().getAttribute("user") == null) || (req.getSession().getAttribute("admin") != null) ||
			("Odustani".equals(req.getParameter("metoda")))) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
		if (req.getParameter("kategorija") == null) {
			boolean prikaziSveKategorije = false;
			Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("user");
			List<Kategorija> kategorije = new ArrayList<Kategorija>();

			if (oglasivac.getTipClanstva().getNaziv().equals("Besplatni")) {
				for (Kategorija k : DAOProvider.getDAO().dohvatiSveKategorije()) {
					if (k.getJeBesplatna().booleanValue()) {
						kategorije.add(k);
					}
				}
			}
			else {
				prikaziSveKategorije = true;
				kategorije = DAOProvider.getDAO().dohvatiSveKategorije();

			}
			req.setAttribute("prikaziSve", prikaziSveKategorije);
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
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
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
			oglas.setJeSkriven(Boolean.valueOf(false));
			oglas.setAutor((Oglasivac) req.getSession().getAttribute("user"));

			DAOProvider.getDAO().dodajOglas(oglas);
			System.out.println("ID je" + oglas.getId());

			uploadajSlike(req, oglas, forma);
			if (!forma.hasError()) {
				System.out.println("Nema greske, spremi oglas");
				forma.spremiStavke(oglas);
				String msg = "Oglas " + oglas.getNaslov() + " dodan";

				req.setAttribute("msg", msg);
				req.setAttribute("title", "Uspješno");

				req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
				return;
			}
			DAOProvider.getDAO().izbrisiOglas(oglas.getId().longValue());
		}

		req.setAttribute("nazivKategorije",
			DAOProvider.getDAO().dohvatiKategoriju(forma.getKategorijaID().longValue())
				.getNaziv());
		req.setAttribute("zapis", forma);
		req.getRequestDispatcher("/WEB-INF/pages/StvoriOglas.jsp").forward(req,
			resp);
	}

	public static Map<String, String> getFields(final List<FileItem> fileItems) {
		HashMap<String, String> fields = new HashMap<>();
		for (FileItem item : fileItems) {
			if (item.isFormField()) {
				try {
					fields.put(item.getFieldName(), item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					System.err.println("UNICODE");
					e.printStackTrace();
				}
			}

		}
		return fields;
	}

	public static void uploadajSlike(final HttpServletRequest req, final Oglas o, final OglasForma forma)
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
			System.out.println("REQ -- " + part.getName());
			fileName = getFileName(part, forma);
			if (!fileName.isEmpty()) {
				System.out.println("File OK");
				part.write(uploadFilePath + File.separator + fileName);
				String ekstenzija = fileName.substring(fileName.lastIndexOf('.') + 1);
				Slika s = new Slika();
				s.setSlika(Files.readAllBytes(Paths.get(uploadFilePath + File.separator + fileName, new String[0])));
				s.setPripadaOglasu(o);
				s.setEkstenzija(ekstenzija);
				DAOProvider.getDAO().dodajSliku(s);
			}
		}
	}

	public static void uploadajSlikeKoristeciApache(final HttpServletRequest req, final List<FileItem> fileItems,
		final OglasForma forma, final Oglas o)
	{

		for (FileItem item : fileItems) {

			if (!item.isFormField()) {
				String fileName = new File(item.getName()).getName();
				System.out.println("------" + fileName);
				if (fileName.matches("(?i).*[.](png|jpg|bmp)")) {

					String applicationPath = req.getServletContext().getRealPath("");
					String uploadFilePath = applicationPath + File.separator + "tmp";

					String filePath = uploadFilePath + File.separator + fileName;
					File storeFile = new File(filePath);

					try {
						item.write(storeFile);
					} catch (Exception e) {
						System.out.println("Iden zapisat");
						e.printStackTrace();
					}

					String ekstenzija = fileName.substring(fileName.lastIndexOf('.') + 1);
					Slika s = new Slika();
					try {
						Path p = Paths.get(filePath);
						System.out.println(p.toAbsolutePath());
						File saved = p.toFile();
						s.setSlika(Files.readAllBytes(p));
					} catch (IOException e) {
						System.out.println("Iden procitat");
						e.printStackTrace();
					}
					s.setPripadaOglasu(o);
					s.setEkstenzija(ekstenzija);
					DAOProvider.getDAO().dodajSliku(s);

				} else if (!fileName.isEmpty()) {
					forma.getErrors().put("slike", "Podrzani formati slika su png, jpg i bmp");
				}

			}
		}

	}

	private static String getFileName(final Part part, final OglasForma forma) {
		String contentDisp = part.getHeader("content-disposition");
		String[] tokens = contentDisp.split(";");

		for (String token : tokens) {
			if (token.trim().startsWith("filename")) {
				String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
				if (fileName.matches("(?i).*[.](png|jpg|bmp)")) {
					return fileName;
				}
				System.out.println("LOS FORMAT");
				forma.getErrors().put("slike", "Podrzani formati slika su png, jpg i bmp");
			}
		}

		return "";
	}

	public static List<FileItem> getFileItems(final HttpServletRequest req, final HttpServlet reference) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			return null;
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// String applicationPath = req.getServletContext().getRealPath("");
		String applicationPath = req.getServletContext().getRealPath("");
		String uploadFilePath = applicationPath + File.separator + "tmp";

		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		factory.setRepository(fileSaveDir);
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> items = upload.parseRequest(req);
			return items;
		} catch (FileUploadException e) {
			return null;
		}

	}
}
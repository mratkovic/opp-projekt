package opp.parica.megafon.web.servleti;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.Slika;
import opp.parica.megafon.pomocno.Potpora;
import opp.parica.megafon.web.servleti.forme.OglasForma;
import opp.parica.megafon.web.servleti.forme.OglasKratkaForma;

@WebServlet("/servleti/prikaz/*")
public class PrikaziServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		String param = provjeriZahtjev(req, resp);
		if (param == null) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);

		} else if (param.equals("oglasivac")) {
			prikazOglasivacDoGet(req, resp);

		} else if (param.equals("slika")) {
			prikaziSlikuGet(req, resp);

		} else if (param.equals("lista_oglasivaca")) {
			izlistajOglasivace(req, resp);

		} else if (param.equals("oglas")) {
			prikaziOglasDoGet(req, resp);

		} else if (param.equals("oglasi_oglasivaca")) {
			prikaziSveOglaseOglasivacaDoGet(req, resp);

		}
	}

	private String provjeriZahtjev(final HttpServletRequest req, final HttpServletResponse resp)
		throws UnsupportedEncodingException, ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		System.out.println(req.getPathInfo());
		String[] params;
		try {
			params = req.getPathInfo().substring(1).split("/");
		} catch (NullPointerException e) {
			params = new String[2];
		}
		if (params.length != 1 || !params[0].matches("oglasivac|slika|lista_oglasivaca|oglas|oglasi_oglasivaca")) {
			return null;
		}
		return params[0];
	}

	protected final void prikazOglasivacDoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		Oglasivac o = null;
		long id;
		try {
			id = Long.parseLong(req.getParameter("id"));
		} catch (Exception ex) {
			id = -1L;
		}

		long current;
		try {
			current = ((Long) req.getSession().getAttribute("id")).longValue();
		} catch (Exception ex) {
			current = -1L;
		}

		if ((current == -1L) || ((req.getSession().getAttribute("user") != null) && (id != current))) {
			req.setAttribute("msg", "Nedovoljna razina prava pristupa");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		if ((req.getSession().getAttribute("admin") != null) && (id == -1L)) {
			List<Oglasivac> oglasivaci = DAOProvider.getDAO().dohvatiSveOglasavace();
			req.setAttribute("oglasivaci", oglasivaci);
			req.getRequestDispatcher("/WEB-INF/pages/IzlistajOglasivace.jsp").forward(req, resp);
			return;
		}
		if ((current != -1L) && (id == current)) {
			o = (Oglasivac) req.getSession().getAttribute("user");
		} else {
			o = DAOProvider.getDAO().dohvatiOglasivaca(id);
		}
		if (o == null) {
			req.setAttribute("msg", "Nepostoji oglašivač s traženim id");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else {
			req.setAttribute("korisnik", o);
			if ((o instanceof PravnaOsoba)) {
				req.setAttribute("pravna", o);
			} else if ((o instanceof FizickaOsoba)) {
				req.setAttribute("fizicka", o);
			}
			UrediPodatkeServlet.clanstvoStatus(req, o);
			req.setAttribute("datumRegistracije", Potpora.formatirajDatum(o.getDatumRegistracije()));
			req.getRequestDispatcher("/WEB-INF/pages/PrikazOglasivaca.jsp").forward(req, resp);
		}
	}

	protected final void prikaziSlikuGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Slika s;
		try {
			Long slikaID = Long.valueOf(Long.parseLong(req.getParameter("id")));
			s = DAOProvider.getDAO().dohvatiSliku(slikaID);
			if (s == null) {
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			req.setAttribute("msg", "Neispravan id slike");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(s.getSlika()));

		if ((req.getParameter("x") == null) || (req.getParameter("y") == null)) {
			ImageIO.write(bufferedImage, s.getEkstenzija(), resp.getOutputStream());
		}
		int x, y;
		try {
			x = Integer.parseInt(req.getParameter("x"));
			y = Integer.parseInt(req.getParameter("y"));
		} catch (Exception e) {
			req.setAttribute("msg", "Neispravna velcina slike");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		ImageIO.write(resize(bufferedImage, x, y), s.getEkstenzija(), resp.getOutputStream());
	}

	private static BufferedImage resize(final BufferedImage img, final int newW, final int newH) {
		Image tmp = img.getScaledInstance(newW, newH, 4);
		BufferedImage dimg = new BufferedImage(newW, newH, 5);

		Graphics2D g2d = dimg.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, newW, newH);
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	protected final void izlistajOglasivace(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
		} else {
			List<Oglasivac> oglasivaci = DAOProvider.getDAO().dohvatiSveOglasavace();
			req.setAttribute("oglasivaci", oglasivaci);
			req.getRequestDispatcher("/WEB-INF/pages/IzlistajOglasivace.jsp").forward(req, resp);
		}
	}

	protected final void prikaziOglasDoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg",
				"Neispravan poziv. Nedostaje parametar 'id' koji oznacava oglas koji se treba prikazati.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException e) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv. Parametar 'id' koji oznacava oglas"
					+ " je neispravnog formata. Ocekivana cijelobrojna vrijednost.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		Oglas o = DAOProvider.getDAO().dohvatiOglas(id);
		boolean imaPravaPrikaza = false;
		if (o != null && o.getJeSkriven()) {

			imaPravaPrikaza = req.getSession().getAttribute("admin") != null;
			if (req.getSession().getAttribute("user") != null) {
				Oglasivac logiraniOglasivac = (Oglasivac) req.getSession().getAttribute("user");
				imaPravaPrikaza = o.getAutor().getId().equals(logiraniOglasivac.getId());
			}
		}
		if (o == null || (o.getJeSkriven() && !imaPravaPrikaza)) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv. Nepostoji traženi oglas");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		OglasForma forma = new OglasForma();
		forma.fillFromObject(o);
		req.setAttribute("zapis", forma);
		if (o.getAutor() instanceof PravnaOsoba) {
			req.setAttribute("autorPO", o.getAutor());
		} else if (o.getAutor() instanceof FizickaOsoba) {
			req.setAttribute("autorFO", o.getAutor());
		}
		req.getRequestDispatcher("/WEB-INF/pages/PrikaziOglas.jsp").forward(req, resp);

	}

	protected final void prikaziSveOglaseOglasivacaDoGet(final HttpServletRequest req,
		final HttpServletResponse resp)
		throws ServletException, IOException {
		String idParam = req.getParameter("id");
		boolean korisnikPrikazujeSvoje = false;

		if (idParam == null && req.getSession().getAttribute("user") == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg",
				"Neispravan poziv. Nedostaje parametar 'id' koji oznacava oglasivaca ciji se oglasi prikazuju.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		} else if (idParam == null && req.getSession().getAttribute("user") != null) {
			// oglasivac zeli svoje oglase prikazat
			korisnikPrikazujeSvoje = true;
			idParam = ((Oglasivac) req.getSession().getAttribute("user")).getId().toString();
		}
		long id;
		try {
			id = Long.parseLong(idParam);
		} catch (NumberFormatException e) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv. Parametar 'id' koji oznacava oglasivaca"
					+ " je neispravnog formata. Ocekivana cijelobrojna vrijednost.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		if (req.getSession().getAttribute("admin") == null && !korisnikPrikazujeSvoje) {
			// za korisnike, prikaz oglasa
			Oglasivac oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(id);
			List<Oglas> oglasi = DAOProvider.getDAO().dohvatiJavneOglaseOglasivaca(oglasivac);

			req.setAttribute("oglasi", OglasKratkaForma.prilagodiZaPrikaz(oglasi, 30, 120));
			req.setAttribute("autor", oglasivac.getUsername());
			req.getRequestDispatcher("/WEB-INF/pages/PrikaziOglaseOglasivaca.jsp").forward(req, resp);
		} else {
			// za admina, prikaz liste oglasa i mogucnost brisanja ili za
			// logiranog autora

			Oglasivac oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(id);
			List<Oglas> oglasi = DAOProvider.getDAO().dohvatiOglaseOglasivaca(oglasivac);

			req.setAttribute("oglasi", oglasi);
			req.setAttribute("autor", oglasivac.getUsername());
			req.getRequestDispatcher("/WEB-INF/pages/IzlistajOglaseOglasivaca.jsp").forward(req, resp);
		}

	}
}

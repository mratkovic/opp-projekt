package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.hash.SHA1;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Korisnik;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.Slika;
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.pomocno.Potpora;
import opp.parica.megafon.web.servleti.forme.FizickaOsobaRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.OglasForma;
import opp.parica.megafon.web.servleti.forme.OglasivacRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.PravnaOsobaRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.PromjenaLozinkeForma;
import opp.parica.megafon.web.servleti.forme.RegistracijaAdminaForma;

import org.apache.commons.fileupload.FileItem;

@WebServlet("/servleti/uredi/*")
public class UrediPodatkeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String param = provjeriZahtjev(req, resp);
		if (param == null) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else if (param.equals("lozinka")) {
			promjenaLozinkeoGet(req, resp);
		} else if (param.equals("tip")) {
			promjeniTipClanstvaDoGet(req, resp);
		} else if (param.equals("korisnik")) {
			urediPodatkeDoGet(req, resp);
		} else if (param.equals("oglas")) {
			urediOglasDoGet(req, resp);
		}

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		String param = provjeriZahtjev(req, resp);
		if (param == null) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else if (param.equals("lozinka")) {
			promjenaLozinkeDoPost(req, resp);
		} else if (param.equals("tip")) {
			promjeniTipClanstvaDoPost(req, resp);
		} else if (param.equals("korisnik")) {
			urediPodatkeDoPost(req, resp);
		} else if (param.equals("oglas")) {
			urediOglasDoPost(req, resp);
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
		if (params.length != 1 || !params[0].matches("lozinka|korisnik|tip|oglas")) {
			return null;
		}
		return params[0];
	}

	private void urediPodatkeDoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException,
		IOException {
		if (req.getSession().getAttribute("logged") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		String idParam = req.getParameter("id");
		if (idParam == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg",
				"Neispravan poziv. Nedostaje parametar 'id' koji oznacava korisnika koji se treba editirati.");
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
				"Neispravan poziv. Parametar 'id' koji oznacava korisnika"
					+ " je neispravnog formata. Ocekivana cijelobrojna vrijednost.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		Korisnik korisnik = DAOProvider.getDAO().dohvatiKorisnika(id);
		if (korisnik == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv. Parametar 'id' koji oznacava korisnika"
					+ " ne postoji.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		Oglasivac o = (Oglasivac) req.getSession().getAttribute("user");
		if (o != null && !o.getId().equals(id)) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		if (korisnik instanceof Admin) {
			// uredi nekog admina
			RegistracijaAdminaForma form = new RegistracijaAdminaForma();
			form.fillFromObject(korisnik);
			req.setAttribute("zapis", form);
			req.getRequestDispatcher("/WEB-INF/pages/UrediAdmina.jsp").forward(req,
				resp);
			return;

		} else if (korisnik instanceof PravnaOsoba) {
			PravnaOsobaRegistracijaForma form = new PravnaOsobaRegistracijaForma();
			form.fillFromObject(korisnik);
			req.setAttribute("zapisPO", form);
			req.setAttribute("jePravna", true);

		} else {
			FizickaOsobaRegistracijaForma form = new FizickaOsobaRegistracijaForma();
			form.fillFromObject(korisnik);
			req.setAttribute("zapisFO", form);
			req.setAttribute("jePravna", false);

		}
		req.getRequestDispatcher("/WEB-INF/pages/UrediKorisnika.jsp").forward(req,
			resp);

	}

	private final void urediOglasDoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		String idParam = req.getParameter("id");
		if (idParam == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg",
				"Neispravan poziv. Nedostaje parametar 'id' koji oznacava oglas koji se ureduje");
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
		Oglas oglas = DAOProvider.getDAO().dohvatiOglas(id);
		if (oglas == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv. Parametar 'id' koji oznacava oglas"
					+ " ne postoji.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		Oglasivac o = (Oglasivac) req.getSession().getAttribute("user");
		if (o != null && !o.getId().equals(oglas.getAutor().getId())) {
			// korisnik nema prava editirati file
			req.setAttribute("title", "Greška");
			req.setAttribute(
				"msg",
				"Neispravan poziv.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		// admin ili vlasnik oglasa
		List<Kategorija> kategorije = new ArrayList<Kategorija>();
		if (oglas.getAutor().getTipClanstva().getNaziv().equals("Besplatni")) {
			for (Kategorija k : DAOProvider.getDAO().dohvatiSveKategorije()) {
				if (k.getJeBesplatna().booleanValue()) {
					kategorije.add(k);
				}
			}
		}
		else {
			kategorije = DAOProvider.getDAO().dohvatiSveKategorije();
		}
		OglasForma zapis = new OglasForma();
		zapis.fillFromObject(oglas);
		req.setAttribute("zapis", zapis);

		req.setAttribute("kategorije", kategorije);
		req.getRequestDispatcher("/WEB-INF/pages/UrediOglas.jsp").forward(req, resp);
		return;
	}

	private final void urediOglasDoPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		List<FileItem> fileItems = DodajOglasServlet.getFileItems(req, this);
		if (fileItems == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
		Map<String, String> fields = DodajOglasServlet.getFields(fileItems);
		String metoda = fields.get("metoda");


		if ("Ukloni".equals(metoda)) {
			String id = fields.get("id");
			Oglas o = DAOProvider.getDAO().dohvatiOglas(Long.parseLong(id));
			DAOProvider.getDAO().izbrisiOglas(o.getId());
			req.setAttribute("title", "Uspješno");
			req.setAttribute("msg", "Oglas '" + o.getNaslov() + "' izbrisan iz baze podataka");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		OglasForma forma = new OglasForma();
		forma.fillFromFields(fields);
		forma.validate();

		Oglas oglas = DAOProvider.getDAO().dohvatiOglas(Long.parseLong(forma.getId()));
		req.setAttribute("zapis", forma);
		List<Kategorija> kategorije = new ArrayList<Kategorija>();
		if (oglas.getAutor().getTipClanstva().getNaziv().equals("Besplatni")) {
			for (Kategorija k : DAOProvider.getDAO().dohvatiSveKategorije()) {
				if (k.getJeBesplatna().booleanValue()) {
					kategorije.add(k);
				}
			}
		}
		else {
			kategorije = DAOProvider.getDAO().dohvatiSveKategorije();
		}

		if (forma.hasError()) {

			forma.popuniSlike(oglas);
			req.setAttribute("zapis", forma);
			req.setAttribute("kategorije", kategorije);
			req.getRequestDispatcher("/WEB-INF/pages/UrediOglas.jsp").forward(req, resp);
			return;
		}
		List<Slika> slike = forma.dohvatiSlike(oglas.getSlike(), fields);
		for (Slika s : slike) {
			System.out.println("Brisi sliku" + s.getId());
			oglas.getSlike().remove(s);
			DAOProvider.getDAO().izbrisiSliku(s.getId());
		}
		forma.fillToObject(oglas);
		System.out.println("UREDENI " + oglas);
		DAOProvider.getDAO().dodajOglas(oglas);

		DodajOglasServlet.uploadajSlikeKoristeciApache(req, fileItems, forma, oglas);

		if (!forma.hasError()) {
			System.out.println("Nema greske, spremi oglas");
			forma.spremiStavke(oglas);
			String msg = "Oglas " + oglas.getNaslov() + " dodan";

			req.setAttribute("msg", msg);
			req.setAttribute("title", "Uspješno");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else {
			forma.popuniSlike(oglas);
			req.setAttribute("zapis", forma);
			req.setAttribute("kategorije", kategorije);
			req.getRequestDispatcher("/WEB-INF/pages/UrediOglas.jsp").forward(req, resp);
			return;

		}

	}

	private final void urediPodatkeDoPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");

		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		String tipOglasivaca = req.getParameter("odabraniTip");
		System.out.println(tipOglasivaca);
		if (tipOglasivaca == null) {
			RegistracijaAdminaForma regForm = new RegistracijaAdminaForma();
			regForm.fillFromHttpRequest(req);
			regForm.validate();
			regForm.getErrors().remove("username");
			regForm.getErrors().remove("password");
			if (regForm.hasError()) {
				req.getRequestDispatcher("/WEB-INF/pages/UrediAdmina.jsp").forward(req, resp);
			} else {
				long id = Long.parseLong(regForm.getId());
				Admin a = (Admin) DAOProvider.getDAO().dohvatiKorisnika(id);
				regForm.fillToObject(a);
				DAOProvider.getDAO().dodajAdmina(a);
				req.getSession().invalidate();
				PrijavaServlet.loginMethod(req, a);
				req.setAttribute("msg", "Uspješno izmjenjeni podaci");
				req.setAttribute("title", "Uspješno");
				req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
				return;
			}
		}

		OglasivacRegistracijaForma regForm;
		Oglasivac oglasivac;
		if (tipOglasivaca.equals("fo")) {
			regForm = new FizickaOsobaRegistracijaForma();
			regForm.fillFromHttpRequest(req);
			regForm.validate();

			req.setAttribute("jePravna", false);
			req.setAttribute("zapisFO", regForm);

		} else {
			regForm = new PravnaOsobaRegistracijaForma();
			regForm.fillFromHttpRequest(req);
			regForm.validate();

			req.setAttribute("jePravna", true);
			req.setAttribute("zapisPO", regForm);
		}
		// smiju biti prazni
		regForm.getErrors().remove("username_" + tipOglasivaca);
		regForm.getErrors().remove("password_" + tipOglasivaca);
		regForm.getErrors().remove("prihvacam_" + tipOglasivaca);

		if (regForm.hasError()) {
			req.getRequestDispatcher("/WEB-INF/pages/UrediKorisnika.jsp").forward(req, resp);
		} else {
			long id = Long.parseLong(regForm.getId());
			oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(id);
			regForm.fillToObject(oglasivac);
			DAOProvider.getDAO().dodajOglasivaca(oglasivac);

			if (req.getSession().getAttribute("admin") == null) {
				req.getSession().invalidate();
				PrijavaServlet.loginMethod(req, oglasivac);
			}

			req.setAttribute("msg", "Uspješno izmjenjeni podaci");
			req.setAttribute("title", "Registracija uspješna");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
	}

	private void promjeniTipClanstvaDoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") == null || req.getSession().getAttribute("user") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		} else {
			clanstvoStatus(req);
			req.setAttribute("tipoviRacuna", DAOProvider.getDAO().dohvatiSveTipoveClanstva());
			req.getRequestDispatcher("/WEB-INF/pages/PromjenaTipaClanstva.jsp").forward(req, resp);
		}
	}

	private void promjeniTipClanstvaDoPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Promjeni tip".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		String odabraniTip = req.getParameter("odabraniTip");
		TipClanstva tr = DAOProvider.getDAO().dohvatiTipClanstva(odabraniTip);

		Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("user");
		if (tr.getNaziv().equals("Besplatni")) {

			oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(oglasivac.getId());
			oglasivac.setDatumIstekaClanarine(null);
			oglasivac.setTipClanstva(tr);

			DAOProvider.getDAO().dodajOglasivaca(oglasivac);
			req.getSession().invalidate();
			PrijavaServlet.loginMethod(req, oglasivac);

			clanstvoStatus(req);
			req.setAttribute("msg",
				"Promjena tipa članstva uspjesno realizirana.");
			req.getRequestDispatcher("/WEB-INF/pages/ClanstvoStatus.jsp").forward(req, resp);
			return;
		}

		oglasivac = (Oglasivac) req.getSession().getAttribute("user");
		String buyer = oglasivac.informacijeOOglasivacu();
		String seller = "Megafon, portal za oglašavanje";
		String price = String.format("%.2f", tr.getClanarina()) + " HRK";
		String item = tr.getNaziv() + " tip racuna, 1 mjesec";
		String total = price;

		req.setAttribute("id_novi_tip", tr.getId());
		req.setAttribute("buyer", buyer);
		req.setAttribute("item", item);
		req.setAttribute("seller", seller);
		req.setAttribute("price", price);
		req.setAttribute("qty", 1);
		req.setAttribute("total", total);
		// preusmjeri na banku
		req.getRequestDispatcher("/WEB-INF/pages/BankaSucelje.jsp").forward(req, resp);
	}

	public static void clanstvoStatus(final HttpServletRequest req, final Oglasivac oglasivac) {
		req.setAttribute("trenutniTip", oglasivac.getTipClanstva().getNaziv());
		if (oglasivac.getDatumIstekaClanarine() == null) {
			req.setAttribute("datumIsteka", "odabrani tip računa je trajan");
		} else {
			req.setAttribute("datumIsteka", Potpora.formatirajDatum(oglasivac.getDatumIstekaClanarine()));

		}
	}

	public static void clanstvoStatus(final HttpServletRequest req) {
		clanstvoStatus(req, (Oglasivac) req.getSession().getAttribute("user"));
	}

	private void promjenaLozinkeoGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			System.out.println(DAOProvider.getDAO().dohvatiSveKategorije());
		} else {
			req.setAttribute("zapis", new PromjenaLozinkeForma());
			req.getRequestDispatcher("/WEB-INF/pages/PromjenaLozinke.jsp").forward(req, resp);
		}
	}

	private void promjenaLozinkeDoPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}
		PromjenaLozinkeForma form = new PromjenaLozinkeForma();
		form.fillFromHttpRequest(req);
		form.validate();
		if (!form.hasError()) {
			Admin admin = (Admin) req.getSession().getAttribute("admin");
			Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("user");

			if (admin != null &&
				admin.getPasswordHash().equals(new SHA1(form.getOldPass()).getHexDigest())) {
				// admin mjenja pass
				admin.setPasswordHash(new SHA1(form.getPassword1()).getHexDigest());
				DAOProvider.getDAO().dodajAdmina(admin);
				req.getSession().invalidate();
				PrijavaServlet.loginMethod(req, admin);

				req.setAttribute("msg", "Promjena lozinke uspješna");
				req.setAttribute("title", "Promjena lozinke");
				req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
				return;

			} else if (oglasivac != null &&
				oglasivac.getPasswordHash().equals(new SHA1(form.getOldPass()).getHexDigest())) {
				// oglasivac
				oglasivac.setPasswordHash(new SHA1(form.getPassword1()).getHexDigest());
				DAOProvider.getDAO().dodajOglasivaca(oglasivac);
				req.getSession().invalidate();
				PrijavaServlet.loginMethod(req, oglasivac);

				req.setAttribute("msg", "Promjena lozinke uspješna");
				req.setAttribute("title", "Promjena lozinke");
				req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
				return;

			} else {
				// nepoklapa se s nikom
				form.getErrors().put("oldPass", "Stara lozinka je pogrešna");
				form.obrisiPolja();
				req.setAttribute("zapis", form);
			}

		} else {
			form.obrisiPolja();
			req.setAttribute("zapis", form);
		}

		req.getRequestDispatcher("/WEB-INF/pages/PromjenaLozinke.jsp").forward(req,
			resp);
	}
}

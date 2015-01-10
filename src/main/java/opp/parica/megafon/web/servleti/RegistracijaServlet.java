package opp.parica.megafon.web.servleti;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.web.servleti.forme.FizickaOsobaRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.OglasivacRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.PravnaOsobaRegistracijaForma;
import opp.parica.megafon.web.servleti.forme.RegistracijaAdminaForma;

@WebServlet("/servleti/registracija/*")
public class RegistracijaServlet extends HttpServlet {
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String param = provjeriZahtjev(req, resp);

		if (param == null) {
			return;
		} else if (param.equals("admin")) {
			registracijaAdminGet(req, resp);
		} else if (param.equals("oglasivac")) {
			registracijaOglasivacGet(req, resp);
		}

	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		String param = provjeriZahtjev(req, resp);

		if (param == null) {
			return;
		} else if (param.equals("admin")) {
			registracijaAdminPost(req, resp);
		} else if (param.equals("oglasivac")) {
			registracijaOglasivacPost(req, resp);
		}
	}

	private void registracijaAdminGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws IOException, ServletException {
		boolean postojiAdmin = DAOProvider.getDAO().postojiAdmin();
		if (postojiAdmin && req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
		} else {
			System.out.println("Nepostoji admin ili se stvara novi!");
			// registrirajAdmina i ostala sranja koja vec imas za napravit
			req.getRequestDispatcher("/WEB-INF/pages/RegistracijaAdminaForma.jsp").forward(req, resp);
		}
	}

	private void registracijaAdminPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		RegistracijaAdminaForma regForm = new RegistracijaAdminaForma();
		regForm.fillFromHttpRequest(req);
		regForm.validate();

		if (!regForm.hasError()) {
			Admin admin = new Admin();
			regForm.fillToObject(admin);

			DAOProvider.getDAO().dodajAdmina(admin);
			String msg = "Administrator " + admin.getUsername() + " ubacen u bazu podataka";

			req.setAttribute("msg", msg);
			req.setAttribute("title", "Registracija uspješna");
			if (req.getSession().getAttribute("admin") == null) {
				System.out.println("Inicijalizacija baze.");
				napuniBazu(req);
				PrijavaServlet.loginMethod(req, admin);
			}

			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;

		} else {
			req.setAttribute("zapis", regForm);
			req.getRequestDispatcher("/WEB-INF/pages/RegistracijaAdminaForma.jsp").forward(req,
				resp);
		}
	}

	private void registracijaOglasivacGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		if (req.getSession().getAttribute("logged") != null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg", "Već ste prijavljeni! "
				+ "Ukoliko želite stvoriti novi račun prethodno se odjavite.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		PravnaOsobaRegistracijaForma pravnaRegForm = new PravnaOsobaRegistracijaForma();
		pravnaRegForm.fillFromObject(new PravnaOsoba());
		FizickaOsobaRegistracijaForma fizickaRegForm = new FizickaOsobaRegistracijaForma();
		fizickaRegForm.fillFromObject(new FizickaOsobaRegistracijaForma());

		req.setAttribute("zapisPO", pravnaRegForm);
		req.setAttribute("zapisFO", fizickaRegForm);
		req.setAttribute("jePravna", false);
		req.setAttribute("jeFizicka", false);
		req.getRequestDispatcher("/WEB-INF/pages/RegistracijaOglasivaca.jsp").forward(req, resp);

	}

	private void registracijaOglasivacPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");

		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		String tipOglasivaca = req.getParameter("odabraniTip");
		OglasivacRegistracijaForma regForm;
		Oglasivac oglasivac;

		if (tipOglasivaca.equals("fo")) {
			regForm = new FizickaOsobaRegistracijaForma();
			oglasivac = new FizickaOsoba();
			regForm.fillFromHttpRequest(req);
			regForm.validate();
			req.setAttribute("jePravna", false);
			req.setAttribute("jeFizicka", true);
			req.setAttribute("zapisFO", regForm);

		} else {
			regForm = new PravnaOsobaRegistracijaForma();
			oglasivac = new PravnaOsoba();
			regForm.fillFromHttpRequest(req);
			regForm.validate();
			req.setAttribute("jePravna", true);
			req.setAttribute("jeFizicka", false);
			req.setAttribute("zapisPO", regForm);
		}

		if (regForm.hasError()) {
			req.getRequestDispatcher("/WEB-INF/pages/RegistracijaOglasivaca.jsp").forward(req, resp);
		} else {
			regForm.fillToObject(oglasivac);
			oglasivac.setDatumRegistracije(new Date());
			oglasivac.setTipClanstva(DAOProvider.getDAO().dohvatiTipClanstva("Besplatni"));
			oglasivac.setDatumIstekaClanarine(null);
			oglasivac.setSviOglasi(new ArrayList<Oglas>());

			DAOProvider.getDAO().dodajOglasivaca(oglasivac);
			if (req.getSession().getAttribute("admin") == null) {
				PrijavaServlet.loginMethod(req, oglasivac);
			}
			req.getRequestDispatcher("/WEB-INF/pages/RegistriranOglasivac.jsp").forward(req, resp);
		}

	}

	private String provjeriZahtjev(final HttpServletRequest req, final HttpServletResponse resp)
		throws UnsupportedEncodingException, ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String[] params;
		try {
			 params = req.getPathInfo().substring(1).split("/");
		} catch (NullPointerException e) {
			params = new String[2];
		}
		if (params.length != 1 || !params[0].matches("oglasivac|admin")) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return null;
		}
		return params[0];
	}

	private void napuniBazu(final HttpServletRequest req) {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(
				req.getServletContext().getRealPath("/WEB-INF/defaults.properties"));
			prop.load(input);
			int brTipova = Integer.parseInt(prop.getProperty("brTipova").trim());
			for (int i = 1; i <= brTipova; ++i) {
				String naziv = prop.getProperty("tip" + i + "_naziv").trim();
				Double cijena = Double.parseDouble(prop.getProperty("tip" + i + "_cijena").trim());
				TipClanstva t = new TipClanstva();
				t.setNaziv(naziv);
				t.setClanarina(cijena);

				DAOProvider.getDAO().dodajTipClanstva(t);
			}

			int brKategorija = Integer.parseInt(prop.getProperty("brKategorija").trim());
			HashMap<String, Kategorija> kategorije = new HashMap<String, Kategorija>();
			for (int i = 1; i <= brKategorija; ++i) {
				// naziv/nadkategorija/Besplatna/Dodatno
				String linija = prop.getProperty("kategorija" + i).trim();
				String[] chunks = linija.split("/");
				String naziv = chunks[0].trim();
				String nazivNadkategorija = chunks[1].trim();
				Boolean free = Boolean.parseBoolean(chunks[2].trim());
				String dodatno = chunks[3].trim();

				Kategorija k = new Kategorija();
				k.setNaziv(naziv);
				k.setDodatneStavke(dodatno);
				k.setNadkategorija(kategorije.get(nazivNadkategorija));
				k.setJeBesplatna(free);
				kategorije.put(naziv, k);
				DAOProvider.getDAO().dodajKategoriju(k);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(DAOProvider.getDAO().dohvatiSveTipoveClanstva());
		System.out.println(DAOProvider.getDAO().dohvatiSveKategorije());
	}
}

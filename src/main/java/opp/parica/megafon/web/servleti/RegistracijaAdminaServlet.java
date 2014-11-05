package opp.parica.megafon.web.servleti;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.Kategorija;
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.web.servlets.forme.RegistracijaAdminaForma;

@WebServlet("/servleti/createAdmin")
public class RegistracijaAdminaServlet extends HttpServlet {
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		boolean postojiAdmin = DAOProvider.getDAO().postojiAdmin();
		if (postojiAdmin && req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
		} else {
			System.out.println("Nepostoji admin ili se stvara novi!");
			// registrirajAdmina i ostala sranja koja vec imas za napravit
			req.getRequestDispatcher("/WEB-INF/pages/RegistracijaAdminaForma.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
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
				LoginServlet.loginMethod(req, admin);
			}

			req.getRequestDispatcher("/WEB-INF/pages/DisplayMsg.jsp").forward(req, resp);
			return;

		} else {
			req.setAttribute("zapis", regForm);
			req.getRequestDispatcher("/WEB-INF/pages/RegistracijaAdminaForma.jsp").forward(req,
				resp);
		}

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
				String cijena = prop.getProperty("tip" + i + "_cijena").trim();
				TipClanstva t = new TipClanstva();
				t.setNaziv(naziv);
				t.setClanarina(cijena);

				DAOProvider.getDAO().dodajTipRacuna(t);
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
		System.out.println(DAOProvider.getDAO().dohvatiSveTipoveRacuna());
		System.out.println(DAOProvider.getDAO().dohvatiSveKategorije());
	}
}

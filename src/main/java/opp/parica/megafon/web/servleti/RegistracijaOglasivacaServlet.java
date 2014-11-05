package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import opp.parica.megafon.model.TipClanstva;
import opp.parica.megafon.web.servlets.forme.FizickaOsobaRegistracijaForma;
import opp.parica.megafon.web.servlets.forme.OglasivacRegistracijaForma;
import opp.parica.megafon.web.servlets.forme.PravnaOsobaRegistracijaForma;

/**
 * Servlet zaduzen za registraciju novih korisnika/autora na blog.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@WebServlet("/servleti/register")
public class RegistracijaOglasivacaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		if (req.getSession().getAttribute("logged") != null) {
			req.setAttribute("msg", "Već ste prijavljeni! "
				+ "Ukoliko želite stvoriti novi račun prethodno se odjavite.");
			req.getRequestDispatcher("/WEB-INF/pages/DisplayMsg.jsp").forward(req, resp);
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
		req.getRequestDispatcher("/WEB-INF/pages/RegistrationForm.jsp").forward(req, resp);

	}

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");

		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
			return;
		}

		String tipOglasivaca = req.getParameter("odabraniTip");
		OglasivacRegistracijaForma regForm;
		Oglasivac oglasivac;
		TipClanstva t;

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
			req.getRequestDispatcher("/WEB-INF/pages/RegistrationForm.jsp").forward(req, resp);
		} else {
			regForm.fillToObject(oglasivac);
			oglasivac.setDatumRegistracije(new Date());
			oglasivac.setTipRacuna(DAOProvider.getDAO().dohvatiTipRacuna("Besplatni"));
			oglasivac.setDatumIstekaClanarine(null);
			oglasivac.setSviOglasi(new ArrayList<Oglas>());

			DAOProvider.getDAO().dodajOglasivaca(oglasivac);
			LoginServlet.loginMethod(req, oglasivac);
			req.getRequestDispatcher("/WEB-INF/pages/RegistriranOglasivac.jsp").forward(req, resp);

		}

	}
}

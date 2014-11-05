package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.TipClanstva;

public class PromjenaTipaRacunaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") == null || req.getSession().getAttribute("admin") != null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");

		} else {
			Oglasivac o = (Oglasivac) req.getSession().getAttribute("user");
			req.setAttribute("trenutniTip", o.getTipRacuna().getNaziv());
			req.setAttribute("datumIsteka", o.getDatumIstekaClanarine());
			if(o.getDatumIstekaClanarine() == null) {
				req.setAttribute("datumIsteka", "odabrani tip računa je trajan");
			}
			req.setAttribute("tipoviRacuna", DAOProvider.getDAO().dohvatiSveTipoveRacuna());
			req.getRequestDispatcher("/WEB-INF/pages/PromjenaTipaRacuna.jsp").forward(req, resp);
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

		String odabraniTip = req.getParameter("odabraniTip");
		Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("oglasivac");
		String tko = oglasivac.informacijeOOglasivacu();
		String komu = "Megafon, portal za oglašavanje";
		TipClanstva tr = DAOProvider.getDAO().dohvatiTipRacuna(odabraniTip);
		String iznos = tr.getClanarina() + ",00 HRK";
		String sto = tr.getNaziv() + " tip racuna, 1 mjesec";

		req.setAttribute("tko", tko);
		req.setAttribute("sto", sto);
		req.setAttribute("komu", komu);
		req.setAttribute("iznos", iznos);
		// preusmjeri na banku
	}
}

package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.List;

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
import opp.parica.megafon.pomocno.Potpora;

@WebServlet("/servleti/postavkeRacuna")
public class DodatneOpcijeServlet extends HttpServlet {
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		if (req.getSession().getAttribute("logged") == null || req.getSession().getAttribute("id") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		if (req.getSession().getAttribute("admin") != null) {
			req.getRequestDispatcher("/WEB-INF/pages/AdminPanel.jsp").forward(req, resp);
		} else {
			Long id = (Long) req.getSession().getAttribute("id");

			Oglasivac o = DAOProvider.getDAO().dohvatiOglasivaca(id);
			req.setAttribute("korisnik", o);
			if ((o instanceof PravnaOsoba)) {
				req.setAttribute("pravna", o);
			} else if ((o instanceof FizickaOsoba)) {
				req.setAttribute("fizicka", o);
			}
			UrediPodatkeServlet.clanstvoStatus(req, o);
			req.setAttribute("datumRegistracije", Potpora.formatirajDatum(o.getDatumRegistracije()));

			List<Oglas> oglasi = DAOProvider.getDAO().dohvatiOglaseOglasivaca(o);
			req.setAttribute("oglasi", oglasi);
			req.getRequestDispatcher("/WEB-INF/pages/OglasivacPanel.jsp").forward(req, resp);
		}

	}
}

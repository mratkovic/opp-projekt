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
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.pomocno.Potpora;

@WebServlet({ "/servleti/prikazOglasivaca" })
public class PrikazOglasivacaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
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
}
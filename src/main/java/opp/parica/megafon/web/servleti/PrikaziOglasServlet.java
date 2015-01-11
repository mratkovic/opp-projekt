package opp.parica.megafon.web.servleti;

import java.io.IOException;

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
import opp.parica.megafon.web.servleti.forme.OglasForma;

@WebServlet("/servleti/prikaziOglas")
public class PrikaziOglasServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
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

}

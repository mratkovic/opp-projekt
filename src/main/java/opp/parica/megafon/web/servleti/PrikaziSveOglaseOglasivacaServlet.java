package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;

@WebServlet("/servleti/prikaziOglaseOglasivaca")
public class PrikaziSveOglaseOglasivacaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
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
		Oglasivac oglasivac = DAOProvider.getDAO().dohvatiOglasivaca(id);
		List<Oglas> oglasi = DAOProvider.getDAO().dohvatiOglaseOglasivaca(oglasivac);

		req.setAttribute("oglasi", oglasi);
		req.setAttribute("autor", oglasivac.getUsername());
		if (req.getSession().getAttribute("admin") == null && !korisnikPrikazujeSvoje) {
			// za korisnike, prikaz oglasa
			req.getRequestDispatcher("/WEB-INF/pages/PrikaziOglaseOglasivaca.jsp").forward(req, resp);
		} else {
			// za admina, prikaz liste oglasa i mogucnost brisanja ili za
			// logiranog autora
			req.getRequestDispatcher("/WEB-INF/pages/IzlistajOglaseOglasivaca.jsp").forward(req, resp);
		}

	}
}

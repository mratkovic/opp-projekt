package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglasivac;
@WebServlet("/servleti/izbrisiOglasivac")
public class IzbrisiOglasivacaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String idParam = req.getParameter("id");
		if (idParam == null) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg",
				"Neispravan poziv. Nedostaje parametar 'id' koji oznacava oglasivaca koji se brise.");
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
				"Neispravan poziv. Parametar 'id' koji oznacava oglasivaca"
					+ " je neispravnog formata. Ocekivana cijelobrojna vrijednost.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		boolean canDelete = req.getSession().getAttribute("admin") != null;
		if (req.getSession().getAttribute("user") != null) {
			Oglasivac o = (Oglasivac) req.getSession().getAttribute("user");
			if(o.getId().equals(id)) {
				req.getSession().invalidate();
				canDelete = true;
			}
		}

		if (!canDelete) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg", "Nedovoljna razina prava za izmjenu sadrzaja.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		Oglasivac o = DAOProvider.getDAO().dohvatiOglasivaca(id);
		DAOProvider.getDAO().izbrisiOglasivaca(id);
		req.setAttribute("title", "Brisanje uspjsno");
		req.setAttribute("msg", "Oglašivač '" + o.getUsername() + "' izbrisan iz baze podataka");
		req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);

	}

}

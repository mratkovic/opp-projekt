package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglas;
import opp.parica.megafon.model.Oglasivac;

@WebServlet("/servleti/izbrisi/*")
public class IzbrisiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		String param = provjeriZahtjev(req, resp);
		if (param == null) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else if (param.equals("oglas")) {
			izbrisiOglasGet(req, resp);
		} else if (param.equals("oglasivac")) {
			izbrisiOglasivacaGET(req, resp);
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
		if (params.length != 1 || !params[0].matches("oglas|oglasivac")) {
			return null;
		}
		return params[0];
	}

	protected final void izbrisiOglasGet(final HttpServletRequest req, final HttpServletResponse resp)
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
				"Neispravan poziv. Parametar 'id' koji oznacava oglas koji se brise"
					+ " je neispravnog formata. Ocekivana cijelobrojna vrijednost.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}

		boolean canDelete = req.getSession().getAttribute("admin") != null;
		if (req.getSession().getAttribute("user") != null) {
			Oglasivac o = (Oglasivac) req.getSession().getAttribute("user");
			Oglas oglas = DAOProvider.getDAO().dohvatiOglas(id);
			if (oglas != null && o.equals(oglas.getAutor())) {
				canDelete = true;
			}
		}

		if (!canDelete) {
			req.setAttribute("title", "Greška");
			req.setAttribute("msg", "Nedovoljna razina prava za izmjenu sadržaja.");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
			return;
		}
		Oglas o = DAOProvider.getDAO().dohvatiOglas(id);
		DAOProvider.getDAO().izbrisiOglas(id);
		req.setAttribute("title", "Uspješno");
		req.setAttribute("msg", "Oglas '" + o.getNaslov() + "' izbrisan iz baze podataka");
		req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);

	}

	protected final void izbrisiOglasivacaGET(final HttpServletRequest req, final HttpServletResponse resp)
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
			if (o.getId().equals(id)) {
				req.getSession().invalidate();
				canDelete = true;
			}
		}

		if (!canDelete) {
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

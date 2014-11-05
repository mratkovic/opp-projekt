package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Oglasivac;

@WebServlet("/servleti/prikaziOglasivace")
public class PrikaziSveOglasivaceServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("admin") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
		} else {
			List<Oglasivac> oglasivaci = DAOProvider.getDAO().dohvatiSveOglasavace();
			req.setAttribute("oglasivaci", oglasivaci);
			req.getRequestDispatcher("/WEB-INF/pages/IzlistajOglasivace.jsp").forward(req, resp);
		}
	}

}

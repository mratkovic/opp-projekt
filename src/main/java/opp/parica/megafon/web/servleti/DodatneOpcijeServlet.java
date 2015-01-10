package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/postavkeRacuna")
public class DodatneOpcijeServlet extends HttpServlet {
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		if (req.getSession().getAttribute("logged") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
			return;
		}

		if (req.getSession().getAttribute("admin") != null) {
			req.getRequestDispatcher("/WEB-INF/pages/AdminPanel.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/pages/OglasivacPanel.jsp").forward(req, resp);
		}

	}
}

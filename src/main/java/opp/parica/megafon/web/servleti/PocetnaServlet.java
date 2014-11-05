package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Klasa koja nasljeduje {@link HttpServlet} i predstavlja pocetnu stranicu
 * portala.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@WebServlet("/servleti/main")
public class PocetnaServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/pages/Index.jsp").forward(req,
			resp);
	}

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
	}

}

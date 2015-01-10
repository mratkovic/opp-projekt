package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet zaduzen samo za prosljedivanje na Main servlet.
 *
 * @author Marko Ratkovic
 * @version 1.0
 */
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
	/** *Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		
		resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
	}

}

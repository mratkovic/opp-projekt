package opp.parica.megafon.web.servleti;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servleti/info/*")
public class InfoServlet extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		String param = provjeriZahtjev(req, resp);
		if (param == null) {
			req.setAttribute("msg", "Neispravan URL");
			req.setAttribute("title", "Greška");
			req.getRequestDispatcher("/WEB-INF/pages/PrikazPoruke.jsp").forward(req, resp);
		} else if (param.equals("onama")) {
			req.getRequestDispatcher("/WEB-INF/pages/Onama.jsp").forward(req, resp);
		} else if (param.equals("uvjeti")) {
			req.getRequestDispatcher("/WEB-INF/pages/UvjetiKoristenja.jsp").forward(req, resp);
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
		if (params.length != 1 || !params[0].matches("onama|uvjeti")) {
			return null;
		}
		return params[0];
	}
}

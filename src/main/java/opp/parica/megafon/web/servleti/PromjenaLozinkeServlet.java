package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.hash.SHA1;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.web.servlets.forme.PromjenaLozinkeForma;
@WebServlet("/servleti/promjenaLozinke")
public class PromjenaLozinkeServlet  extends HttpServlet {
	/** Defaultni serial version UID. */
	private static final long serialVersionUID = 1L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
			System.out.println(DAOProvider.getDAO().dohvatiSveKategorije());
		} else {
			req.setAttribute("zapis", new PromjenaLozinkeForma());
			req.getRequestDispatcher("/WEB-INF/pages/PromjenaLozinke.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		req.setCharacterEncoding("UTF-8");
		String metoda = req.getParameter("metoda");
		if (!"Pohrani".equals(metoda)) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
			return;
		}
		PromjenaLozinkeForma form = new PromjenaLozinkeForma();
		form.fillFromHttpRequest(req);
		form.validate();
		if (!form.hasError()) {
			Admin admin = (Admin) req.getSession().getAttribute("admin");
			Oglasivac oglasivac = (Oglasivac) req.getSession().getAttribute("user");

			if (admin != null &&
				admin.getPasswordHash().equals(new SHA1(form.getOldPass()).getHexDigest())) {
				// admin mjenja pass
				admin.setPasswordHash(new SHA1(form.getPassword1()).getHexDigest());
				DAOProvider.getDAO().dodajAdmina(admin);
				req.getSession().invalidate();
				LoginServlet.loginMethod(req, admin);

				req.setAttribute("msg", "Promjena lozinke uspješna");
				req.setAttribute("title", "Promjena lozinke");
				req.getRequestDispatcher("/WEB-INF/pages/DisplayMsg.jsp").forward(req,resp);
				return;

			} else if (oglasivac != null &&
				oglasivac.getPasswordHash().equals(new SHA1(form.getOldPass()).getHexDigest())) {
				//oglasivac
				oglasivac.setPasswordHash(new SHA1(form.getPassword1()).getHexDigest());
				DAOProvider.getDAO().dodajOglasivaca(oglasivac);
				req.getSession().invalidate();
				LoginServlet.loginMethod(req, oglasivac);

				req.setAttribute("msg", "Promjena lozinke uspješna");
				req.setAttribute("title", "Promjena lozinke");
				req.getRequestDispatcher("/WEB-INF/pages/DisplayMsg.jsp").forward(req,resp);
				return;

			} else {
				// nepoklapa se s nikom
				form.getErrors().put("oldPass", "Stara lozinka je pogrešna");
				form.obrisiPolja();
				req.setAttribute("zapis", form);
			}

		} else {
			form.obrisiPolja();
			req.setAttribute("zapis", form);
		}

		req.getRequestDispatcher("/WEB-INF/pages/PromjenaLozinke.jsp").forward(req,
			resp);
	}

}

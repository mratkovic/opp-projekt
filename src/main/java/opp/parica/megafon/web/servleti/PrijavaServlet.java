package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;
import opp.parica.megafon.model.Admin;
import opp.parica.megafon.model.FizickaOsoba;
import opp.parica.megafon.model.Korisnik;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.web.servleti.forme.PrijavaKorisnikaForma;
@WebServlet("/servleti/login")
public class PrijavaServlet extends HttpServlet {

	private static final long serialVersionUID = -2642786903348555772L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") != null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
		} else {
			req.setAttribute("zapis", new PrijavaKorisnikaForma());
			req.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(req, resp);
		}
	}

	@Override
	protected final void doPost(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		PrijavaKorisnikaForma loginForm = new PrijavaKorisnikaForma();
		loginForm.fillFromHttpRequest(req);
		loginForm.validate();

		if (!loginForm.hasError()) {
			Admin tmp = new Admin();
			loginForm.fillToObject(tmp);
			// provjera koji je tip korisnika
			Admin admin = DAOProvider.getDAO().dohvatiAdmin(tmp.getUsername(), tmp.getPasswordHash());
			PravnaOsoba oglasivacP = DAOProvider.getDAO().dohvatiPravnaOsoba(tmp.getUsername(),
				tmp.getPasswordHash());
			FizickaOsoba oglasivacF = DAOProvider.getDAO().dohvatiFizickaOsoba(tmp.getUsername(),
				tmp.getPasswordHash());

			boolean found = false;
			if (admin != null) {
				loginMethod(req, admin);
				found = true;
			} else if (oglasivacP != null) {
				loginMethod(req, oglasivacP);
				found = true;
			} else if (oglasivacF != null) {
				loginMethod(req, oglasivacF);
				found = true;
			} else {
				req.setAttribute("msg", "Username i/ili password je pogrešan");
				loginForm.setPassword("");
			}

			if (found) {
				resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
				return;
			}

		} else {
			// isprazni polje pass
			loginForm.setPassword("");
		}

		req.setAttribute("zapis", loginForm);
		req.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(req,
			resp);
	}

	static final void loginMethod(final HttpServletRequest req, final Korisnik user) {
		if (user instanceof PravnaOsoba) {
			PravnaOsoba po = (PravnaOsoba) user;
			req.getSession().setAttribute("user", po);
		} else if (user instanceof FizickaOsoba) {
			FizickaOsoba fo = (FizickaOsoba) user;
			req.getSession().setAttribute("user", fo);
		} else {
			req.getSession().setAttribute("admin", user);
		}
		req.getSession().setAttribute("logged", user.dohvatiKorisnikInfo());
		req.getSession().setAttribute("id", user.getId());


	}
}

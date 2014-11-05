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
import opp.parica.megafon.model.Oglasivac;
import opp.parica.megafon.model.PravnaOsoba;
import opp.parica.megafon.web.servlets.forme.PrijavaKorisnikaForma;

@WebServlet("/servleti/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2642786903348555772L;

	@Override
	protected final void doGet(final HttpServletRequest req, final HttpServletResponse resp)
		throws ServletException, IOException {
		if (req.getSession().getAttribute("logged") != null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
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
			Admin admin = DAOProvider.getDAO().dohvatiAdmin(tmp.getUsername(), tmp.getPasswordHash());

			PravnaOsoba oglasivacP = DAOProvider.getDAO().dohvatiPravnaOsoba(tmp.getUsername(),
				tmp.getPasswordHash());
			FizickaOsoba oglasivacF = DAOProvider.getDAO().dohvatiFizickaOsoba(tmp.getUsername(),
				tmp.getPasswordHash());

			if (admin != null) {
				loginMethod(req, admin);
				resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
				return;
			} else if (oglasivacP != null) {
				loginMethod(req, oglasivacP);
				resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
				return;
			} else if (oglasivacF != null) {
				loginMethod(req, oglasivacF);
				resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/main");
				return;
			} else {
				req.setAttribute("msg",
					"Username i/ili password je pogrešan");
				loginForm.setPassword("");
				req.setAttribute("zapis", loginForm);
			}

		} else {
			req.setAttribute("zapis", loginForm);
		}

		req.getRequestDispatcher("/WEB-INF/pages/Login.jsp").forward(req,
			resp);
	}

	static final void loginMethod(final HttpServletRequest req, final Admin user) {
		req.getSession().setAttribute("admin", user);
		req.getSession().setAttribute("logged", user.getUsername());
	}

	static final void loginMethod(final HttpServletRequest req, final Oglasivac user) {
		if (user instanceof PravnaOsoba) {
			PravnaOsoba po = (PravnaOsoba) user;
			req.getSession().setAttribute("user", po);
			req.getSession().setAttribute("logged",
				((PravnaOsoba) user).getNaziv() + " '" + user.getUsername()
					+ "' ");

		} else if (user instanceof FizickaOsoba) {
			FizickaOsoba fo = (FizickaOsoba) user;
			req.getSession().setAttribute("user", fo);
			req.getSession().setAttribute("logged",
				((FizickaOsoba) user).getIme() + " '" + user.getUsername()
					+ "' " + ((FizickaOsoba) user).getPrezime());

		}

	}

}

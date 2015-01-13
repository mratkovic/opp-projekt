package opp.parica.megafon.web.servleti;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opp.parica.megafon.dao.DAOProvider;

@WebServlet("/servleti/init")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
		IOException {
		boolean postojiAdmin = DAOProvider.getDAO().postojiAdmin();
		if (postojiAdmin) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/pocetna");
		} else {
			System.out.println("Nepostoji admin ili se stvara novi!");
			resp.sendRedirect(req.getServletContext().getContextPath() + "/servleti/registracija/admin");
		}
	}
}
